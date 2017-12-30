package com.jdkgroup.interviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.customviews.socialintegration.facebookintegration.FacebookLoginHelper;
import com.jdkgroup.customviews.socialintegration.facebookintegration.FacebookLoginListener;
import com.jdkgroup.customviews.socialintegration.facebookintegration.FacebookLoginModel;
import com.jdkgroup.customviews.socialintegration.googleintegration.GoogleLoginHelper;
import com.jdkgroup.customviews.socialintegration.googleintegration.GoogleLoginListener;
import com.jdkgroup.customviews.socialintegration.googleintegration.GoogleLoginModel;
import com.jdkgroup.presenter.LoginPresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.utils.PreferenceUtils;
import com.jdkgroup.utils.Validator;
import com.jdkgroup.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends SimpleMVPActivity<LoginPresenter, LoginView> implements LoginView, FacebookLoginListener, GoogleLoginListener, View.OnClickListener {

    private Gson gson;

    private FacebookLoginHelper facebookLoginHelper;
    private GoogleLoginHelper googleLoginHelper;

    private AppCompatImageView ivAppFacebookLogin, ivAppGoogleLogin;

    /*
    *  https://developers.google.com/identity/sign-in/android/start-integrating
    *  GOOGLE LOGIN DEVELOPERS KEY
    */

    /*
    * TODO
    * EMULATOR GOOGLE LOGIN TOKEN NOT GET ONLY SUPPORT PAY STORE AVAILABLE
    * WHEN RELEASE KEY GENERATE THEN KEYSTORE BOTH FACEBOOK AND GOOGLE
    *
    * */

    /* TODO RELEASE APK KEY GENERATE PUT build.gradle
    signingConfigs {
        release {
            storeFile file("KEY STORE PATH HERE")
            storePassword ""
            keyAlias ""
            keyPassword ""
        }
    }

    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
     */

    @BindView(R.id.appIvLogo)
    AppCompatImageView appIvLogo;
    @BindView(R.id.rvLogin)
    RelativeLayout rvLogin;
    @BindView(R.id.appTvLogin)
    AppCompatTextView appTvLogin;
    @BindView(R.id.llFormLogin)
    LinearLayout llFormLogin;
    @BindView(R.id.llSocialLogin)
    LinearLayout llSocialLogin;
    @BindView(R.id.llSignUp)
    LinearLayout llSignUp;

    @BindView(R.id.appTvSignUp)
    AppCompatTextView appTvSignUp;
    @BindView(R.id.appTvForgotPassword)
    AppCompatTextView appTvForgotPassword;

    @BindView(R.id.appEdtEmail)
    AppCompatEditText appEdtEmail;
    @BindView(R.id.appEdtPassword)
    AppCompatEditText appEdtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        hideSoftKeyboard();
        ButterKnife.bind(this);
        gson = new Gson();

        /* TODO FACEBOOK HASHKEY
        AppInteractor appInteractor = new AppInteractor();
        appInteractor.FacebookHashKey(this, "com.jdkgroup.pocketquiz");
        */

        facebookLoginHelper = new FacebookLoginHelper(this);
        googleLoginHelper = new GoogleLoginHelper(this, LoginActivity.this, getString(R.string.google_client_id)); //TODO GOOGLE-SERVICE.JSON CLIENT_ID LAST HERE SET

        ivAppFacebookLogin = findViewById(R.id.ivAppFacebookLogin);
        ivAppGoogleLogin = findViewById(R.id.ivAppGoogleLogin);

        rvLogin.setOnClickListener(this);
        appTvForgotPassword.setOnClickListener(this);
    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @NonNull
    @Override
    public LoginView attachView() {
        return this;
    }

    private void resetAnim(View v) {
        v.animate().setStartDelay(0).setDuration(500).translationY(10).scaleX(1).scaleY(1).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookLoginHelper.onActivityResult(requestCode, resultCode, data);
        googleLoginHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFbSignInFail(String errorMessage) {
        AppUtils.showToast(getActivity(), errorMessage);
    }

    @Override
    public void onFbSignInSuccess(FacebookLoginModel facebookLoginModel) {
        //TODO FACEBOOK RESPONSE
    }

    @Override
    public void onFBSignOut() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivAppFacebookLogin:
                setLoginEditTextNull();

                if (hasInternet() == true) {
                    PreferenceUtils.getInstance(this).setLoginStatus(0);
                    facebookLoginHelper.performSignOut();
                    facebookLoginHelper.performSignIn(this);
                }
                break;

            case R.id.ivAppGoogleLogin:
                setLoginEditTextNull();

                if (hasInternet() == true) {
                    PreferenceUtils.getInstance(this).setLoginStatus(0);
                    googleLoginHelper.performSignOut();
                    googleLoginHelper.performSignIn(this);
                }
                break;

            case R.id.rvLogin:
                String email, password;
                email = appEdtEmail.getText().toString().toLowerCase();
                password = appEdtPassword.getText().toString();


                break;

            case R.id.appTvForgotPassword:

                break;
        }
    }

    @Override
    public void onGoogleAuthSignIn(GoogleLoginModel googleLoginModel) {
        //TODO GET GOOGLE RESPONSE
    }

    @Override
    public void onGoogleAuthSignInFailed(String errorMessage) {
        AppUtils.showToast(getActivity(), errorMessage);
    }

    @Override
    public void onGoogleAuthSignOut() {

    }

    @Override
    public void onBackPressed() {
        appExist();
    }


    @Override
    public void onFailure(String message) {

    }

    public void setLoginEditTextNull() {
        appEdtEmail.setText(null);
        appEdtPassword.setText(null);
    }

    private boolean Validation(String email, String password) {
        if (Validator.isEmpty(email)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_email));
            return false;
        }
        else if (Validator.isRegexValidator(email, Validator.patternEmail) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_valid_email));
            return false;
        }
        else if (Validator.isEmpty(password)) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_empty_password));
            return false;
        }  else if (Validator.isRegexValidator(password, Validator.patternPassword) == false) {
            AppUtils.showToast(getActivity(), getString(R.string.msg_valid_password));
            return false;
        }

        return true;
    }
}
