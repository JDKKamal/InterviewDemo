package com.jdkgroup.appdemo;

public enum EnumParam {
    latitude, longitude;
}

/*

        EnumMap<EnumParam, String> enumMap = new EnumMap(EnumParam.class);
        enumMap.put(EnumParam.latitude, "23.033212");
        enumMap.put(EnumParam.longitude, "72.560101");

        System.out.println("Tag" + getToJsonClass(enumMap));
*/