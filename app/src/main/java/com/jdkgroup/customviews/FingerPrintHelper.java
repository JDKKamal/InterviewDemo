package com.jdkgroup.customviews;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerPrintHelper
{
    private static final String KEY_NAME = "helper_key";

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private KeyStore keyStore;
    private Cipher cipher;
    private Context context;
    private boolean isCipherReady = false;
    private AuthenticationListener callback;
    private CancellationSignal cancellationSignal;

    public interface AuthenticationListener
    {
        void onAuthError(int errorCode, String errorMsg);
        void onAuthFailed();
        void onAuthSuccess();
        void onAuthCancelled();
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    public void init(Context context) throws RuntimeException
    {
        this.context = context;

        fingerprintManager = (FingerprintManager) context.getSystemService(FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) context.getSystemService(KEYGUARD_SERVICE);

        setupFingerprintManager();
    }

    public void stopListening()
    {
        if (cancellationSignal != null)
            cancellationSignal.cancel();
    }

    @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
    public void startListening(AuthenticationListener listener)
    {
        callback = listener;

        if (isCipherReady)
        {
            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
            cancellationSignal = new CancellationSignal();
            //noinspection MissingPermission
            fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, new AuthCallback(), null);
        }
        else
            throw new RuntimeException("Cipher not ready");
    }

    //only called from within init() which requires the permission
    @SuppressWarnings("MissingPermission")
    private void setupFingerprintManager() throws RuntimeException
    {
        if (!keyguardManager.isKeyguardSecure())
        {
            throw new RuntimeException("Keyguard not secure");
        }

        if (!fingerprintManager.hasEnrolledFingerprints())
        {
            throw new RuntimeException("No fingerprints stored");
        }

        generateKey();
        cipherInit();
    }

    private void generateKey() throws RuntimeException
    {
        try
        {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        }
        catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException | IOException | InvalidAlgorithmParameterException e)
        {
            throw new RuntimeException(e.toString(), e);
        }
    }

    private void cipherInit() throws RuntimeException
    {
        try
        {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            keyStore.load(null);

            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            isCipherReady = true;
        }
        catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException | KeyStoreException | InvalidKeyException | UnrecoverableKeyException | CertificateException e)
        {
            isCipherReady = false;
            throw new RuntimeException("Failed to get Cipher", e);
        }
    }


    private class AuthCallback extends FingerprintManager.AuthenticationCallback
    {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString)
        {
            if (callback != null)
            {
                if (errMsgId == FingerprintManager.FINGERPRINT_ERROR_CANCELED)
                    callback.onAuthCancelled();
                else
                    callback.onAuthError(errMsgId, errString.toString());
            }
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString)
        {
            Toast.makeText(context, helpString, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAuthenticationFailed()
        {
            if (callback != null)
                callback.onAuthFailed();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result)
        {
            if (callback != null)
                callback.onAuthSuccess();
        }
    }
}
