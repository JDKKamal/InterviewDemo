package com.jdkgroup.dynamicjson.bpi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ModelMain
{
    @SerializedName("bpi")
    @Expose
    private Map<String, String> bpi;

    @SerializedName("disclaimer")
    @Expose
    String disclaimer;

    @SerializedName("time")
    @Expose
    ModelTime time;

    public Map<String, String> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, String> bpi) {
        this.bpi = bpi;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public ModelTime getTime() {
        return time;
    }

    public void setTime(ModelTime time) {
        this.time = time;
    }
}
