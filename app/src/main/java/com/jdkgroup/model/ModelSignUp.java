package com.jdkgroup.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lakhani on 12/4/2017.
 */

public class ModelSignUp extends RealmObject
{
    @PrimaryKey
    private String uuidsignup;
    private String socialid;
    private String username, email, password, mobile, profilepicture, deviceid;
    private int signupwhich, status;
    private Date datetime;

    public ModelSignUp() {
    }

    public ModelSignUp(String uuidsignup, String socialid, String username, String email, String password, String mobile, String profilepicture, String deviceid, int signupwhich, int status, Date datetime) {
        this.uuidsignup = uuidsignup;
        this.socialid = socialid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.profilepicture = profilepicture;
        this.deviceid = deviceid;
        this.signupwhich = signupwhich;
        this.status = status;
        this.datetime = datetime;
    }

    public ModelSignUp(String socialid, String email, String password, int signupwhich) {
        this.socialid = socialid;
        this.email = email;
        this.password = password;
        this.signupwhich = signupwhich;
    }

    public ModelSignUp(String email) {
        this.email = email;
    }

    public String getUuidsignup() {
        return uuidsignup;
    }

    public void setUuidsignup(String uuidsignup) {
        this.uuidsignup = uuidsignup;
    }

    public String getSocialid() {
        return socialid;
    }

    public void setSocialid(String socialid) {
        this.socialid = socialid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public int getSignupwhich() {
        return signupwhich;
    }

    public void setSignupwhich(int signupwhich) {
        this.signupwhich = signupwhich;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
