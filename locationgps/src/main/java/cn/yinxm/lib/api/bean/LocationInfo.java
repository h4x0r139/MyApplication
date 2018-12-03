package cn.yinxm.lib.api.bean;

import java.io.Serializable;

/**
 * <p>
 *
 * @author yinxuming
 * @date 2018/9/10
 */
public class LocationInfo implements Serializable {
    private static final long serialVersionUID = -2393507073462637658L;

    /**
     * 获取经度
     * @return
     */
    private double longitude;

    /**
     * 获取纬度
     * @return
     */
    private double latitude;

    /**
     * 获取详细地址
     *
     * @return
     */
    private String address;


    /**
     * 1、获取国家名称
     * @return
     */
    private String country;

    /**
     * 2、获取省份
     * @return
     */
    private String province;

    /**
     * 3、获取城市
     * @return
     */
    private String city;

    /**
     * 4、获取地区
     * @return
     */
    private String district;

    /**
     * 5、获取街道
     * @return
     */
    private String street;

    /**
     * 更新时间
     */
    private long updateTime;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "\"LocationInfo\": {"
                + "\"longitude\": \"" + longitude
                + ", \"latitude\": \"" + latitude
                + ", \"address\": \"" + address + '\"'
                + ", \"country\": \"" + country + '\"'
                + ", \"province\": \"" + province + '\"'
                + ", \"city\": \"" + city + '\"'
                + ", \"district\": \"" + district + '\"'
                + ", \"street\": \"" + street + '\"'
                + ", \"updateTime\": \"" + updateTime
                + '}';
    }
}
