package cn.yinxm.locationgps;

/**
 * Created by yinxm on 2017/4/1.
 * 功能: 自定义地理位置接口
 */

public interface ICustomLocation {
    /**
     * 获取经度
     * @return
     */
    double getLongitude();

    /**
     * 获取纬度
     * @return
     */
    double getLatitude();

    /**
     * 获取详细地址
     *
     * @return
     */
    String getAddress();

    /**
     * 1、获取国家名称
     * @return
     */
    String getCountry();

    /**
     * 2、获取省份
     * @return
     */
    String getProvince();

    /**
     * 3、获取城市
     * @return
     */
    String getCity();

    /**
     * 4、获取地区
     * @return
     */
    String getDistrict();

    /**
     * 5、获取街道
     * @return
     */
    String getStreet();
}

