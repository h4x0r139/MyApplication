package cn.yinxm.locationgps;

public interface IEcarxLocation {
    int GPS_ACCURACY_BAD = 0;
    int GPS_ACCURACY_GOOD = 1;
    int GPS_ACCURACY_UNKNOWN = -1;

    /**
     * 纬度
     * @return
     */
    double getLatitude();

    /**
     * 经度
     * @return
     */
    double getLongitude();

    /**
     * 精确地址
     * @return
     */
    float getAccuracy();

    double getAltitude();

    float getSpeed();

    float getBearing();

    String getAddress();

    String getCountry();

    String getProvince();

    String getCity();

    String getDistrict();

    String getStreet();

    String getStreetNum();

    String getCityCode();

    String getAdCode();

    String getPoiName();

    String getAoiName();

    int getGpsAccuracyStatus();

    int getLocationType();

    String getLocationDetail();

    String getErrorInfo();

    int getErrorCode();
}