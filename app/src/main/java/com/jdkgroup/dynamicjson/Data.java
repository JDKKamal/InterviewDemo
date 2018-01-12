package com.jdkgroup.dynamicjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Data {
    @SerializedName("status_code")
    @Expose
    private String statusCode;
    @SerializedName("feeds")
    @Expose
    private Map<String, Feeds> feeds;
    /**
     *
     * @return
     * The statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }
    /**
     *
     * @param statusCode
     * The status_code
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    /**
     *
     * @return
     * The feeds
     */
    public Map<String, Feeds>  getFeeds() {
        return feeds;
    }
    /**
     *
     * @param feeds
     * The feeds
     */
    public void setFeeds(Map<String, Feeds>  feeds) {
        this.feeds = feeds;
    }
}