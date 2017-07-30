package cn.yinxm.lib.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import java.util.List;

import cn.yinxm.lib.map.iml.DefaultEcarxLocationIml;
import cn.yinxm.lib.utils.log.LogUtil;


/**
 * Created by yinxm on 2017/4/1.
 * 功能: 系统定位
 */

public class LocationApi {
    private Context mContext;
    private MyLocationListener listener;
    private LocationManager locationManager;


    public LocationApi(Context context, OnLocationChangeListener onLocationChangeListener) {
        this.mContext = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener(onLocationChangeListener);
    }

    @SuppressWarnings("all")
    public void startRequestLocationUpdates() {
        //Caused by: java.lang.SecurityException: "network" location provider requires ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION permission.
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, listener);
    }

    @SuppressWarnings("all")
    public void stopRequestLocationUpdates() {
        locationManager.removeUpdates(listener);
        locationManager.removeUpdates(listener);
    }

    private  class MyLocationListener implements LocationListener {
        OnLocationChangeListener onLocationChangeListener;
        Location mLastLocation;
        boolean mValid = false;

        public MyLocationListener() {}

        public MyLocationListener(OnLocationChangeListener onLocationChangeListener) {
            if (onLocationChangeListener != null) {
                this.onLocationChangeListener = onLocationChangeListener;
            }
        }


        @Override
        public void onLocationChanged(Location newLocation) {
            LogUtil.d("newLocation=" + newLocation);
            if (newLocation != null) {
                if (newLocation.getLatitude() == 0.0
                        && newLocation.getLongitude() == 0.0) {
                    // Hack to filter out 0.0,0.0 locations
                    return;
                }
                if (!mValid) {
                    LogUtil.d("Got first location.");
                }
                mLastLocation = newLocation;
                LogUtil.d("the newLocation is " + newLocation.getLongitude() + "x" + newLocation.getLatitude());
                try {
                    ICustomLocation customLocation = getCustomLocation(mContext, mLastLocation.getLongitude(), mLastLocation.getLatitude());
                    LogUtil.d("customLocation="+customLocation+", onLocationChangeListener="+onLocationChangeListener);
                    if (onLocationChangeListener != null) {
                        onLocationChangeListener.onLocationChanged(customLocation);
                    }
                } catch (Exception e) {
                    LogUtil.e(e);
                }
                mValid = true;
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            LogUtil.d("provider=" + provider + ", status=" + status + ", extras=" + extras);

            switch (status) {
                case LocationProvider.OUT_OF_SERVICE:
                case LocationProvider.TEMPORARILY_UNAVAILABLE: {
                    mValid = false;
                    break;
                }
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            LogUtil.d(" support current " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            LogUtil.d("no support current " + provider);
            mValid = false;
        }

        public Location current() {
            return mValid ? mLastLocation : null;
        }
    }

    public ICustomLocation getCustomLocation(Context context, double longitude, double latitude) {
        ICustomLocation customLocation = null;
        try {
            Geocoder geocoder = new Geocoder(context);
            boolean flag = geocoder.isPresent();
            LogUtil.d("the flag is " + flag);
            if (flag) {
//根据经纬度获取地理位置信息
                //32.628041,110.778442，湖北省十堰市茅箭区朝阳南路4号朝阳南路
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    LogUtil.d("getMaxAddressLineIndex=" + address.getMaxAddressLineIndex() + "\n" + address);
                    String country = address.getCountryName();
                    String province = address.getAdminArea();
                    String city = address.getLocality();
                    String district = address.getSubLocality();
                    String street = address.getThoroughfare();
                    String featureName = address.getFeatureName();//周边地址
                    String addressDetail = province + city + district + street + featureName;
                    customLocation = new DefaultEcarxLocationIml(longitude, latitude, addressDetail, country, province, city, district, street);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customLocation;

    }

    public interface OnLocationChangeListener {

        /**
         * Called when the location has changed.
         *
         * <p> There are no restrictions on the use of the supplied Location object.
         *
         * @param location The new location, as a Location object.
         */
        void onLocationChanged(ICustomLocation location);
    }

}
