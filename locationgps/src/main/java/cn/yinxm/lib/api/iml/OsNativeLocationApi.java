package cn.yinxm.lib.api.iml;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import cn.yinxm.lib.api.ILocationApi;
import cn.yinxm.lib.api.bean.LocationInfo;


/**
 * 系统原生定位：gps + net
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/10
 */
public class OsNativeLocationApi implements ILocationApi, LocationListener {
    private static final String TAG = "OsNativeLocationApi";

    private final int MIN_INTERNAL_TIME = 10000;

    private Context mContext;
    private LocationManager mLocationManager;
    private boolean isLocationing = false;
    private OnLocationUpdate mOnLocationUpdate;
    private LocationInfo mLastLocationInfo;
    /**
     * 定位间隔时间
     */
    private int minLocationTime = MIN_INTERNAL_TIME;
    private long lastStartTime = 0;

    public OsNativeLocationApi(Context context) {
        this.mContext = context.getApplicationContext();
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }


    @Override
    public LocationInfo getLastLocationInfo() {
        return mLastLocationInfo;
    }

    @Override
    public void startUpdateLocation(OnLocationUpdate locationUpdate) {
        if (!(mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))) {
            Log.e(TAG, "请打开定位功能");
            return;
        }

        if (System.currentTimeMillis() - lastStartTime < minLocationTime) {
            Log.e(TAG, "location too frequent");
            return;
        }
        lastStartTime = System.currentTimeMillis();

        this.mOnLocationUpdate = locationUpdate;

        try {
            if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minLocationTime, 1F, this);
                isLocationing = true;
            }
            if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minLocationTime, 1F, this);
                isLocationing = true;
            }
        } catch (Exception e) {
            Log.e(TAG, "" + e);
        }
    }

    @Override
    public void stopUpdateLocation() {

    }

    @Override
    public boolean isLocationing() {
        return isLocationing;
    }


    @Override
    public void onLocationChanged(Location location) {
        isLocationing = false;
        Log.e(TAG, "onLocationChanged newLocation=" + location);
        Toast.makeText(mContext, "provider=" + location.getProvider()
                        + ", lon=" + location.getLongitude() + ", lat=" + location.getLatitude()
                , Toast.LENGTH_LONG)
                .show();
        if (location != null) {
            if (location.getLatitude() == 0.0
                    && location.getLongitude() == 0.0) {
                // Hack to filter out 0.0,0.0 locations
                return;
            }
            Log.d(TAG, "the location is " + location.getLongitude() + "x" + location.getLatitude());
            try {
                LocationInfo locationInfo = LocationDetailUtil.getAdress(mContext,
                        location.getLatitude(), location.getLongitude());
                if (locationInfo != null) {
                    if (mOnLocationUpdate != null) {
                        mOnLocationUpdate.onLocationUpdte(true, locationInfo);
                    }
                    mLastLocationInfo = locationInfo;
                } else {
                    if (mOnLocationUpdate != null) {
                        mOnLocationUpdate.onLocationUpdte(false, getLastLocationInfo());
                    }
                }

            } catch (Exception e) {
                Log.e(TAG, "" + e);
            }
        }

    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.e(TAG, "onStatusChanged provider=" + provider + ", status=" + status + ", extras=" + extras);
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
            case LocationProvider.TEMPORARILY_UNAVAILABLE: {
                break;
            }
        }

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, " onProviderEnabled" + provider);

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, " onProviderDisabled" + provider);
    }

    public int getMinLocationTime() {
        return minLocationTime;
    }

    @Override
    public void setMinLocationTime(int minLocationTime) {
        this.minLocationTime = minLocationTime;
    }
}
