package com.jdkgroup.customviews.fusedbulblib.interfaces;

import android.location.Location;

public interface GpsOnListener {
     void gpsStatus(boolean status);
     void gpsPermissionDenied(int deviceGpsStatus);
     void gpsLocationFetched(Location location);
}
