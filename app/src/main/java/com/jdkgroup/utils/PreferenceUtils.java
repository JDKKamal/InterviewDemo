package com.jdkgroup.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jdkgroup.constant.AppConstant;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtils implements AppConstant{
    private final static String SP_NAME = "interview";
    private static PreferenceUtils preferenceUtils;
    private SharedPreferences sharedPreferences;
    private Context mContext;

    private PreferenceUtils(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(SP_NAME, MODE_PRIVATE);
    }

    public static PreferenceUtils getInstance(Context mContext) {
        return preferenceUtils = (preferenceUtils == null ? new PreferenceUtils(mContext) : preferenceUtils);
    }

    private static void removeInstance() {
        preferenceUtils = null;
    }

    public void clearAllPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        removeInstance();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(SP_IS_LOGIN, false);
    }

    public void setIsLogin(boolean islogin) {
        sharedPreferences.edit().putBoolean(SP_IS_LOGIN, islogin).apply();
    }

    public String getDeviceToken() {
        return sharedPreferences.getString(SP_DEVICE_TOKEN, "");
    }

    public void setDeviceToken(String deviceToken) {
        sharedPreferences.edit().putString(SP_DEVICE_TOKEN, deviceToken).apply();
    }

    public String getAuthToken() {
        return sharedPreferences.getString(SP_AUTH_TOKEN, "");
    }

    public void setAuthToken(String deviceToken) {
        sharedPreferences.edit().putString(SP_AUTH_TOKEN, deviceToken).apply();
    }

    public String getDeviceId() {
        return sharedPreferences.getString(SP_DEVICE_ID, "");
    }

    public void setDeviceId(String deviceId) {
        sharedPreferences.edit().putString(SP_DEVICE_ID, deviceId).apply();
    }

    public String getUserId() {
        return sharedPreferences.getString(SP_USER_ID, "");
    }

    public void setUserId(String userId) {
        sharedPreferences.edit().putString(SP_USER_ID, userId).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(SP_USERNAME, "");
    }

    public void setUserName(String userName) {
        sharedPreferences.edit().putString(SP_USERNAME, userName).apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(SP_EMAIL, "");
    }

    public void setEmail(String email) {
        sharedPreferences.edit().putString(SP_EMAIL, email).apply();
    }

    public int getLoginStatus() {
        return sharedPreferences.getInt(SP_LOGIN_STATUS, 0);
    }

    public void setLoginStatus(int loginStatus) {
        sharedPreferences.edit().putInt(SP_LOGIN_STATUS, loginStatus).apply();
    }

    public String getMobile() {
        return sharedPreferences.getString(SP_MOBILE, "");
    }

    public void setMobile(String mobile) {
        sharedPreferences.edit().putString(SP_MOBILE, mobile).apply();
    }

    public String getSocialId() {
        return sharedPreferences.getString(SP_SOCIAL_ID, "");
    }

    public void setSocialId(String socialId) {
        sharedPreferences.edit().putString(SP_SOCIAL_ID, socialId).apply();
    }

    public int getSettingSignUp() {
        return sharedPreferences.getInt(SP_SETTING_SIGNUP, 0);
    }

    public void setSettingSignUp(int signUp) {
        sharedPreferences.edit().putInt(SP_SETTING_SIGNUP, signUp).apply();
    }

    public int getSettingLogin() {
        return sharedPreferences.getInt(SP_SETTING_LOGIN, 0);
    }

    public void setSettingLogin(int login) {
        sharedPreferences.edit().putInt(SP_SETTING_LOGIN, login).apply();
    }

    public int getSettingUsername() {
        return sharedPreferences.getInt(SP_SETTING_USERNAME, 0);
    }

    public void setSettingUsername(int username) {
        sharedPreferences.edit().putInt(SP_SETTING_USERNAME, username).apply();
    }

    public int getSettingEmail() {
        return sharedPreferences.getInt(SP_SETTING_EMAIL, 0);
    }

    public void setSettingEmail(int email) {
        sharedPreferences.edit().putInt(SP_SETTING_EMAIL, email).apply();
    }

    public int getSettingMobile() {
        return sharedPreferences.getInt(SP_SETTING_MOBILE, 0);
    }

    public void setSettingMobile(int mobile) {
        sharedPreferences.edit().putInt(SP_SETTING_MOBILE, mobile).apply();
    }

    public int getSettingProfilePicture() {
        return sharedPreferences.getInt(SP_SETTING_PROFILE_PICTURE, 0);
    }

    public void setSettingProfilePicture(int profilePicture) {
        sharedPreferences.edit().putInt(SP_SETTING_PROFILE_PICTURE, profilePicture).apply();
    }

    public int getSettingPassword() {
        return sharedPreferences.getInt(SP_SETTING_PASSWORD, 0);
    }

    public void setSettingPassword(int password) {
        sharedPreferences.edit().putInt(SP_SETTING_PASSWORD, password).apply();
    }

    public int getAppLoad() {
        return sharedPreferences.getInt(SP_APP_LOAD, 0);
    }

    public void setAppLoad(int password) {
        sharedPreferences.edit().putInt(SP_APP_LOAD, password).apply();
    }

    public String getFCMToken() {
        return sharedPreferences.getString(SP_FCM_TOKEN, "");
    }

    public void setFCMToken(String fcmToken) {
        sharedPreferences.edit().putString(SP_FCM_TOKEN, fcmToken).apply();
    }
}
