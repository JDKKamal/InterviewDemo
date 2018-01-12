package com.jdkgroup.dynamicjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feeds {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }
    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }
    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}