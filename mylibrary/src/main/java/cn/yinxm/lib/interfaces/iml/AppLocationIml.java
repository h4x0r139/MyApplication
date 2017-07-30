package cn.yinxm.lib.interfaces.iml;

import java.io.Serializable;

import cn.yinxm.lib.interfaces.IAppLocation;

/**
 * Created by yinxm on 2017/2/14.
 * 功能:
 */

public class AppLocationIml implements IAppLocation, Serializable {
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


    public AppLocationIml() {
    }

    public AppLocationIml(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public AppLocationIml(double longitude, double latitude, String address, String country, String province, String city, String district, String street) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomLocationIml{");
        sb.append("longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", address='").append(address).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", province='").append(province).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
