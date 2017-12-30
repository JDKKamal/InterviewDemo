package com.jdkgroup.constant;

public interface AppConstant {

    // extension
    public String EXTENSION_JSON = "json";
    public String EXTENSION_JPG = ".jpg";
    public String FOLDER_NAME = "interviewdemo_jdkgroup";

    public int INTENT_HISTORY_CLEAR = 0, INTENT_HISTORY = 1;
    public int IS_LOGIN_SIGNUP = 0, IS_LOGIN_FB = 3, IS_LOGIN_GOOGLE = 2;
    public int IS_NOTIFICATION_DELETE = 0, IS_NOTIFICATION_SENT = 1, IS_NOTIFICATION_READ = 3, IS_NOTIFICATION_UPDATE = 4;

    //BUNDlE
    public String BUNDLE = "bundle";
    public String BUNDLE_PARCELABLE = "parcelable";
    public String BUNDLE_PARCELABLE_TRANSACTION = "parcelable_transaction";

    //FONT
    public String FONT_AILERON_SEMIBOLD = "fonts/aileron_semi_bold.otf";
    public String FONT_AILERON_REGULAR = "fonts/aileron_regular.otf";

    //LOCAL READ FILE
    public String READ_FILE_JSON_COUNTRY = "json/countries";

    public int isStatusDefault = 0;

    public int PARCELABLE_FORGOT_PASSWORD = 1_1;
    public int PARCELABLE_SIGN_UP = 1_2;
    public int PARCELABLE_CHANGE_USERNAME = 1_3;
    public int PARCELABLE_CHANGE_EMAIL = 1_4;
    public int PARCELABLE_CHANGE_MOBILE = 1_5;
    public int PARCELABLE_CHANGE_PASSWORD = 1_6;

    //LAUNCH ACTIVITY OR FRAGMENT
    public int LAUNCH_PROFILE = 1;

    public int LAUNCH_CATEGORY_FRAGMENT = 7;
    public int LAUNCH_CATEGORY_LIST_FRAGMENT = 7_1;
    public int LAUNCH_QUESTION_FRAGMENT = 7_2;
    public int LAUNCH_COMPLETE_FRAGMENT = 7_3;
    public int LAUNCH_CHANGE_PASSWORD_FRAGMENT = 1_1;

    /* BASIC */
    public final String SP_DEVICE_TOKEN = "device_token";
    public final String SP_AUTH_TOKEN = "auth_token";
    public final String SP_USERNAME = "name";
    public final String SP_EMAIL = "email";
    public final String SP_MOBILE = "mobile";
    public final String SP_SOCIAL_ID = "social_id";
    public final String SP_DEVICE_ID = "device_id";
    public final String SP_USER_ID = "user_id";
    public final String SP_LOGIN_STATUS = "login_status";

    public final String SP_SETTING_SIGNUP = "setting_sign_up";
    public final String SP_SETTING_LOGIN = "setting_login";
    public final String SP_SETTING_USERNAME = "setting_username";
    public final String SP_SETTING_EMAIL = "setting_email";

    public final String SP_SETTING_MOBILE = "setting_mobile";
    public final String SP_SETTING_PROFILE_PICTURE = "setting_profile_picture";
    public final String SP_SETTING_PASSWORD = "setting_password";

    public final String SP_APP_LOAD = "app_load";

}

