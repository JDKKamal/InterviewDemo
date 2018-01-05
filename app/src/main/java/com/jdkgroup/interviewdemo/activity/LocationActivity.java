package com.jdkgroup.interviewdemo.activity;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.customviews.fusedbulblib.GetCurrentLocation;
import com.jdkgroup.customviews.fusedbulblib.interfaces.GpsOnListener;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.utils.AppUtils;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class LocationActivity extends BaseActivity implements GpsOnListener {
    GetCurrentLocation getCurrentLocation;
    @BindView(R.id.appTvLongitude)
    AppCompatTextView appTvLongitude;
    @BindView(R.id.appTvLatitude)
    AppCompatTextView appTvLatitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        bindViews();

        getCurrentLocation = new GetCurrentLocation(this);

        getCurrentLocation.getContinuousLocation(true);
        getCurrentLocation.getCurrentLocation();
    }

    @Override
    public void gpsStatus(boolean status) {
        if (status == true) {
            getCurrentLocation.getContinuousLocation(true);
            getCurrentLocation.getCurrentLocation();
        }
    }

    @Override
    public void gpsPermissionDenied(int deviceGpsPermission) {

    }

    @Override
    public void gpsLocationFetched(Location location) {
        AppUtils.showToast(this, "Current location refresh... ");

        appTvLongitude.setText(location.getLongitude() + "");
        appTvLatitude.setText(location.getLatitude() + "");

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addressList = geocoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(), 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    sb.append(address.getAddressLine(i)).append("\n");
                }
                sb.append(address.getLocality()).append("\n");
                sb.append(address.getPostalCode()).append("\n");
                sb.append(address.getCountryName());

                appTvLatitude.setText(getToJsonClass(address));
            }
        } catch (Exception ex) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getCurrentLocation.stopLocationUpdate();
    }
}
