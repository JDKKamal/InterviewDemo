package com.jdkgroup.dynamicjson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class Run
{
    public static void main(String[] args)
    {

        String jsonData = "{\n" +
                "    \"data\": {\n" +
                "        \"status_code\": \"101\",\n" +
                "        \"feeds\": {\n" +
                "            \"39\": {\n" +
                "                \"name\": \"Ammy\",\n" +
                "                \"address\": \"Dehradun\"\n" +
                "            },\n" +
                "            \"40\": {\n" +
                "                \"name\": \"Aman\",\n" +
                "                \"address\": \"Dehradun\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        System.out.println("Tag" + jsonData);

        DemoJson myObj  = new Gson().fromJson(jsonData,
                new TypeToken<DemoJson>(){}.getType());


        for (Map.Entry<String, Feeds> entry : myObj.getData().getFeeds().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().getName();
            System.out.println("Tag " + value);
        }
    }
}
