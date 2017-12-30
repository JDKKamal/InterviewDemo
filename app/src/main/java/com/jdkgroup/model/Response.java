package com.jdkgroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Lakhani on 12/5/2017.
 */

public class Response
{
    @SerializedName("status")
    @Expose
    private Integer status;
    private String message;
    private Date date;

    public Response() {
    }

    public Response(int status, String message, Date date) {
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public Response(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
