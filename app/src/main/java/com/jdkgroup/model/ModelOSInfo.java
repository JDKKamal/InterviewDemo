package com.jdkgroup.model;

/**
 * Created by kamlesh on 9/2/2017.
 */

public class ModelOSInfo
{
    private String deviceuniqueid, devicetype, devicename, osversion, appversion, countryiso, networkoperatorname;

    public ModelOSInfo(String deviceuniqueid, String devicetype, String devicename, String osversion, String appversion, String countryiso, String networkoperatorname) {
        this.deviceuniqueid = deviceuniqueid;
        this.devicetype = devicetype;
        this.devicename = devicename;
        this.osversion = osversion;
        this.appversion = appversion;
        this.countryiso = countryiso;
        this.networkoperatorname = networkoperatorname;
    }

    public String getDeviceuniqueid() {
        return deviceuniqueid;
    }

    public void setDeviceuniqueid(String deviceuniqueid) {
        this.deviceuniqueid = deviceuniqueid;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getOsversion() {
        return osversion;
    }

    public void setOsversion(String osversion) {
        this.osversion = osversion;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getCountryiso() {
        return countryiso;
    }

    public void setCountryiso(String countryiso) {
        this.countryiso = countryiso;
    }

    public String getNetworkoperatorname() {
        return networkoperatorname;
    }

    public void setNetworkoperatorname(String networkoperatorname) {
        this.networkoperatorname = networkoperatorname;
    }
}
