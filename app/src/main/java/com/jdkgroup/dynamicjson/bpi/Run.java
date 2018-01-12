package com.jdkgroup.dynamicjson.bpi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.util.Objects;

public class Run {
    public static void main(String[] args)
    {
        String data = "{\"bpi\":{\"2017-12-05\":11696.0583,\"2017-12-06\":13708.9913,\"2017-12-07\":16858.02,\"2017-12-08\":16057.145,\"2017-12-09\":14913.4038,\"2017-12-10\":15036.9563,\"2017-12-11\":16699.6775,\"2017-12-12\":17178.1025,\"2017-12-13\":16407.2025,\"2017-12-14\":16531.0838,\"2017-12-15\":17601.9438,\"2017-12-16\":19343.04,\"2017-12-17\":19086.6438,\"2017-12-18\":18960.5225,\"2017-12-19\":17608.35,\"2017-12-20\":16454.7225,\"2017-12-21\":15561.05,\"2017-12-22\":13857.145,\"2017-12-23\":14548.71,\"2017-12-24\":13975.4363,\"2017-12-25\":13917.0275,\"2017-12-26\":15745.2575,\"2017-12-27\":15378.285,\"2017-12-28\":14428.76,\"2017-12-29\":14427.87,\"2017-12-30\":12629.8138,\"2017-12-31\":13860.1363,\"2018-01-01\":13412.44,\"2018-01-02\":14740.7563,\"2018-01-03\":15134.6513,\"2018-01-04\":15155.2263},\"disclaimer\":\"This data was produced from the CoinDesk Bitcoin Price Index. BPI value data returned as USD.\",\"time\":{\"updated\":\"Jan 5, 2018 11:28:34 UTC\",\"updatedISO\":\"2018-01-05T11:28:34+00:00\"}}";

        ModelMain myObj  = new Gson().fromJson(data, new TypeToken<ModelMain>(){}.getType());
        System.out.println("Tag" + new Gson().toJson(myObj.getBpi()));

        for (Map.Entry<String, String> entry : myObj.getBpi().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("Tag " + key + " - " + value);
        }
    }
}
