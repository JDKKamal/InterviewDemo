package com.jdkgroup.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class DesEncrypter {
	static Logger logger = Logger.getAnonymousLogger();
	Cipher ecipher;
	Cipher dcipher;

	// 8-byte Salt
	byte[] salt = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x35, (byte) 0xE3,
			(byte) 0x03 };

	// Iteration count
	int iterationCount = 19;

	public DesEncrypter(String passPhrase) {
		try {
			// Create the key
			KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
			ecipher = Cipher.getInstance(key.getAlgorithm());
			dcipher = Cipher.getInstance(key.getAlgorithm());

			// Prepare the parameter to the ciphers
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

			// Create the ciphers
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
		} catch (java.security.InvalidAlgorithmParameterException e) {
		} catch (InvalidKeySpecException e) {
		} catch (NoSuchPaddingException e) {
		} catch (NoSuchAlgorithmException e) {
		} catch (InvalidKeyException e) {
		}
	}

	public String encrypt(String str) {
		try {
			// Encode the string into bytes using utf-8
			byte[] utf8 = str.getBytes("UTF8");

			// Encrypt
			byte[] enc = ecipher.doFinal(utf8);

			// Encode bytes to base64 to get a string

			String encryptedString =  android.util.Base64.encodeToString(enc, android.util.Base64.DEFAULT);

			System.out.println("Tag -----encryptedString-----" + encryptedString);
			encryptedString = encryptedString.replace("+", "12345678");
			System.out.println("-----encryptedString-----" + encryptedString);
			return encryptedString;
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			// Decode base64 to get bytes

			logger.info("-----you are in decrypt-----" + str);
			byte[] dec = android.util.Base64.decode(str.replace("12345678", "+"), android.util.Base64.DEFAULT);

			// Decrypt
			byte[] utf8 = dcipher.doFinal(dec);

			// Decode using utf-8
			// return new String(utf8, "UTF8");
			logger.info("-----utf8 in decrypt-----" + utf8);

			String decryptedString = new String(utf8, "UTF8");
			logger.info("-----decryptedString-----" + decryptedString);

			return decryptedString;

		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		try {
			// Create encrypter/decrypter class
			System.out.println("111111111111111111");
			String arg = args[0];

			DesEncrypter encrypter = new DesEncrypter("My Pass Phrase!");
			// Don't tell anybody!
			// Encrypt
			String encrypted = encrypter.encrypt(arg);
			System.out.println("encrypted=" + encrypted);

			// Decrypt
			DesEncrypter encrypter1 = new DesEncrypter("My Pass Phrase!");
			String decrypted = encrypter1.decrypt(encrypted);
			System.out.println("decrypted1=" + decrypted);
		} catch (Exception e) {
		}

	}
}
