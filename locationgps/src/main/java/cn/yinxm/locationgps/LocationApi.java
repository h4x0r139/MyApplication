package cn.yinxm.locationgps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by yinxm on 2017/4/1.
 * 功能:
 */

public class LocationApi {
    private Context mContext;
    private MyLocationListener listener;
    private final static String TAG = "yinxm";
    private android.location.LocationManager locationManager;
    private MyLocationListener listeners[] = {
            new MyLocationListener(),
            new MyLocationListener()
    };

    public LocationApi(Context context, OnLocationChangeListener onLocationChangeListener) {
        this.mContext = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        listener = new MyLocationListener(onLocationChangeListener);
    }

    public void startRequestLocationUpdates() {
        //Caused by: java.lang.SecurityException: "network" location provider requires ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION permission.
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, listener);
    }

    public void stopRequestLocationUpdates() {
        locationManager.removeUpdates(listeners[0]);
        locationManager.removeUpdates(listeners[1]);
    }

    public Location getCurrentLocation() {

        // go in best to worst order
        for (int i = 0; i < listeners.length; i++) {
            Location l = listeners[i].current();
            if (l != null) return l;
        }
        Log.d(TAG, "No location received yet.");
        return null;
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
            Log.d(TAG, "newLocation=" + newLocation);
            if (newLocation != null) {
                if (newLocation.getLatitude() == 0.0
                        && newLocation.getLongitude() == 0.0) {
                    // Hack to filter out 0.0,0.0 locations
                    return;
                }
                if (!mValid) {
                    Log.d(TAG, "Got first location.");
                }
                mLastLocation = newLocation;
                Log.d(TAG, "the newLocation is " + newLocation.getLongitude() + "x" + newLocation.getLatitude());
                try {
                    getAddress(mContext, mLastLocation);
                   ICustomLocation  customLocation = getCustomLocation(mContext, mLastLocation.getLongitude(), mLastLocation.getLatitude());
                    Log.d(TAG,"customLocation="+customLocation+", onLocationChangeListener="+onLocationChangeListener);
                    if (onLocationChangeListener != null) {
                        onLocationChangeListener.onLocationChanged(customLocation);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mValid = true;
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d(TAG, "provider=" + provider + ", status=" + status + ", extras=" + extras);

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
            Log.d(TAG, " support current " + provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d(TAG, "no support current " + provider);
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
            Log.e(TAG, "the flag is " + flag);
            if (flag) {
//根据经纬度获取地理位置信息
                //32.628041,110.778442，湖北省十堰市茅箭区朝阳南路4号朝阳南路
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

                if (addresses.size() > 0) {
                    Address address = addresses.get(0);
                    Log.d(TAG, "getMaxAddressLineIndex=" + address.getMaxAddressLineIndex() + "\n" + address);
                    String country = address.getCountryName();
                    String province = address.getAdminArea();
                    String city = address.getLocality();
                    String district = address.getSubLocality();
                    String street = address.getThoroughfare();
                    String featureName = address.getFeatureName();//周边地址
                    String addressDetail = province + city + district + street + featureName;
                    customLocation = new CustomLocationIml(longitude, latitude, addressDetail, country, province, city, district, street);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customLocation;

    }

    private String getAddress(Context context, Location location) throws IOException {
        Geocoder geocoder = new Geocoder(context);
        boolean falg = geocoder.isPresent();
        Log.e(TAG, "the falg is " + falg);
        StringBuilder stringBuilder = new StringBuilder();
        try {

//根据经纬度获取地理位置信息
            //32.628041,110.778442
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            List<Address> addresses = geocoder.getFromLocation(32.628041, 110.778442, 1);
//            "address":"湖北省十堰市茅箭区朝阳南路4号朝阳南路",
//            "nation":"中国",
//            "province":"湖北省",
//            "city":"十堰市",
//            "district":"茅箭区",
//            "street":"朝阳南路",
//            "street_number":"朝阳南路4号"

//根据地址获取地理位置信息
//            List<Address> addresses = geocoder.getFromLocationName("广东省珠海市香洲区沿河路321号", 1);

            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                    stringBuilder.append(address.getAddressLine(i)).append("\n");
                }
                stringBuilder.append("CountryName=").append(address.getCountryName()).append("\n");//国家
                stringBuilder.append("FeatureName=").append(address.getFeatureName()).append("\n");//周边地址
                stringBuilder.append("Locality=").append(address.getLocality()).append("\n");//市
                stringBuilder.append("PostalCode=").append(address.getPostalCode()).append("\n");
                stringBuilder.append("CountryCode=").append(address.getCountryCode()).append("\n");//国家编码
                stringBuilder.append("AdminArea=").append(address.getAdminArea()).append("\n");//省份
                stringBuilder.append("SubAdminArea=").append(address.getSubAdminArea()).append("\n");
                stringBuilder.append("Thoroughfare=").append(address.getThoroughfare()).append("\n");//道路
                stringBuilder.append("SubLocality=").append(address.getSubLocality()).append("\n");//香洲区

                stringBuilder.append("Latitude=").append(address.getLatitude()).append("\n");//经度
                stringBuilder.append("Longitude=").append(address.getLongitude());//维度
                System.out.println(stringBuilder.toString());
//                getAddress=CountryName=中国
//                FeatureName=竹山巷16号湖北医药学院
//                Locality=十堰市
//                PostalCode=null
//                CountryCode=CN
//                AdminArea=湖北省
//                SubAdminArea=null
//                Thoroughfare=竹山巷
//                SubLocality=茅箭区
//                Latitude=32.627764
//                Longitude=110.778264
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Toast.makeText(context, "报错", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        Log.d(TAG, "getAddress=" + stringBuilder.toString());
        return stringBuilder.toString();

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
