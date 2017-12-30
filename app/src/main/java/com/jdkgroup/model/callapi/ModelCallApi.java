package com.jdkgroup.model.callapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jdkgroup.model.Response;

public class ModelCallApi extends Response{

    @SerializedName("data")
    @Expose
    private ModelData data;


    public ModelData getData() {
        return data;
    }

    public void setData(ModelData data) {
        this.data = data;
    }

}