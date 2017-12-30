package com.jdkgroup.customviews.socialintegration.facebookintegration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookLoginHelper {

    private FacebookLoginListener mListener;
    private CallbackManager mCallBackManager;

    public FacebookLoginHelper(@NonNull FacebookLoginListener facebookLoginListener) {
        mListener = facebookLoginListener;
        mCallBackManager = CallbackManager.Factory.create();
        FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {
                                    String name, firsName, lastName, gender, email, profilePicture, cellNo;
                                    name = object.getString("name");
                                    firsName = object.getString("first_name");
                                    lastName = object.getString("last_name");
                                    gender = object.getString("gender");
                                    profilePicture = "https://graph.facebook.com/" + loginResult.getAccessToken().getUserId() + "/picture?type=large";
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                    } else {
                                        email = null;
                                    }
                                    if (object.has("phone")) {
                                        cellNo = object.getString("phone");
                                    } else {
                                        cellNo = null;
                                    }

                                    FacebookLoginModel facebookLoginModel = new FacebookLoginModel(loginResult.getAccessToken().getToken(), loginResult.getAccessToken().getUserId(), name, firsName, lastName, gender, email, profilePicture, cellNo);
                                    mListener.onFbSignInSuccess(facebookLoginModel);
                                } catch (Exception ex) {
                                    mListener.onFbSignInFail(ex.toString());
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,first_name,last_name,gender,email,picture.type(large)");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                //mListener.onFbSignInFail("User cancelled operation");
            }

            @Override
            public void onError(FacebookException e) {
                mListener.onFbSignInFail(e.getMessage());
            }
        };
        LoginManager.getInstance().registerCallback(mCallBackManager, mCallBack);
    }

    @NonNull
    @CheckResult

    public CallbackManager getCallbackManager() {
        return mCallBackManager;
    }

    public void performSignIn(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "user_friends", "email"));
    }

    public void performSignIn(Fragment fragment) {
        LoginManager.getInstance().logInWithReadPermissions(fragment, Arrays.asList("public_profile", "user_friends", "email"));
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void performSignOut() {
        LoginManager.getInstance().logOut();
        mListener.onFBSignOut();
    }
}
