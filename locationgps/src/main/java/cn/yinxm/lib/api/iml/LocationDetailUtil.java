package cn.yinxm.lib.api.iml;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import cn.yinxm.lib.api.bean.LocationInfo;


/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/10
 */
public class LocationDetailUtil {
    private static final String TAG = "LocationDetailUtil";

    /**
     * 经纬度——》详细地址
     *
     * @param context
     * @param latitude
     * @param longitude
     * @return
     */
    public static LocationInfo getAdress(Context context, double latitude, double longitude) {

        Geocoder geocoder = new Geocoder(context);
        boolean flag = Geocoder.isPresent();
        Log.e(TAG, "the flag is " + flag);

        LocationInfo locationInfo = new LocationInfo();
        locationInfo.setLatitude(latitude);
        locationInfo.setLongitude(longitude);
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                if (address == null) {
                    return locationInfo;
                }

                locationInfo.setCountry(address.getCountryName());
                // 省份
                locationInfo.setProvince(address.getAdminArea());
                locationInfo.setCity(address.getLocality());
                // 地区
                locationInfo.setDistrict(address.getSubLocality());
                // 街道
                locationInfo.setStreet(address.getFeatureName());

                locationInfo.setUpdateTime(System.currentTimeMillis());
            }
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }

        return locationInfo;
    }


    public static LocationInfo getLocation(Context context, String adress) {
        return null;
    }
}
