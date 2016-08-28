package com.h4x0r.ecarx.wifi.data;

/**
 * Created by yinxm on 2016/8/11.
 */

public class DeviceInfo {
    public String wifiName;//wifi名称
    public String wifiPwd;//wifi密码
    public String deviceIp;//设备ip地址
    public String imei;//imei号
    public String panelLight;//面板灯状态
    public String gesture;//手势开启状态
    public String wifiStatus;//wifi开启状态
    public String bindStatus;//设备和账号绑定状态
    public String bluetoothStatus;//蓝牙开启状态
    public String bluetoothConnectStatus;//蓝牙连接状态
    public String fm;//fm发射频率
    public String playMode;//播放模式 0:智能；1:收藏；2:随机
    public String whiteList;//白名单列表，具体格式待定

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceInfo{");
        sb.append("wifiName='").append(wifiName).append('\'');
        sb.append(", wifiPwd='").append(wifiPwd).append('\'');
        sb.append(", deviceIp='").append(deviceIp).append('\'');
        sb.append(", imei='").append(imei).append('\'');
        sb.append(", panelLight='").append(panelLight).append('\'');
        sb.append(", gesture='").append(gesture).append('\'');
        sb.append(", wifiStatus='").append(wifiStatus).append('\'');
        sb.append(", bindStatus='").append(bindStatus).append('\'');
        sb.append(", bluetoothStatus='").append(bluetoothStatus).append('\'');
        sb.append(", bluetoothConnectStatus='").append(bluetoothConnectStatus).append('\'');
        sb.append(", fm='").append(fm).append('\'');
        sb.append(", playMode='").append(playMode).append('\'');
        sb.append(", whiteList='").append(whiteList).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
