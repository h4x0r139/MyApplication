package com.h4x0r.ecarx.wifi.data;

/**
 * Created by yinxm on 2016/8/15.
 */

public class DeviceWifiMode {
    public String wifiName;
    public String wifiPwd;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceWifiMode{");
        sb.append("wifiName='").append(wifiName).append('\'');
        sb.append(", wifiPwd='").append(wifiPwd).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
