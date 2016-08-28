package com.h4x0r.ecarx.wifi.client;


import com.h4x0r.ecarx.wifi.config.WifiConfig;
import com.h4x0r.ecarx.wifi.data.DefaultWifiRequest;
import com.h4x0r.ecarx.wifi.data.DeviceFmValue;
import com.h4x0r.ecarx.wifi.data.DeviceGestureMode;
import com.h4x0r.ecarx.wifi.data.DevicePanelMode;
import com.h4x0r.ecarx.wifi.data.DevicePlayMode;
import com.h4x0r.ecarx.wifi.data.DeviceWifiMode;
import com.h4x0r.ecarx.wifi.util.DataUtils;
import com.h4x0r.ecarx.wifi.util.GsonUtils;
import com.h4x0r.ecarx.wifi.util.UUIDutils;

/**
 * Created by yinxm on 2016/8/11.
 */

public class WifiModel {

    /**
     * 获取设备信息
     */
    public static void getDeviceInfo(IResponseCallBack mIResponseCallBack) {
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.GET_DEVICE_CONGIF;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }

    /**
     * 重启路由器
     */
    public static void restartWifi(IResponseCallBack mIResponseCallBack) {
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.RESTART_WIFI;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }

    /**
     * 设置fm频率
     */
    public static void setWifiFm(IResponseCallBack mIResponseCallBack, String fm) {
        DeviceFmValue deviceFmValue = new DeviceFmValue();
        deviceFmValue.fm = fm;
        String data = new GsonUtils<>().toJson(deviceFmValue);
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.SET_FM;
        mDefaultWifiRequest.data = data;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }

    /**
     * 设置收听模式
     */
    public static void setPlayMode (IResponseCallBack mIResponseCallBack , String playMode){
        DevicePlayMode devicePlayMode = new DevicePlayMode();
        devicePlayMode.playMode = playMode;
        String data = new GsonUtils<>().toJson(devicePlayMode);
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.SET_PLAY_MODE;
        mDefaultWifiRequest.data = data;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }

    /**
     * 设置播放提示音
     */
    public static void playAudioTone (IResponseCallBack mIResponseCallBack){
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.PLAY_AUDIO_TONE;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }
    /**
     * 设置wifi
     * wifiName wifi名称
     * wifiPwd wifi密码
     */
    public static void setWifi (IResponseCallBack mIResponseCallBack,String wifiName,String wifiPwd){
        DeviceWifiMode wifiMode=new DeviceWifiMode();
        wifiMode.wifiName=wifiName;
        wifiMode.wifiPwd=wifiPwd;
        String data = new GsonUtils<>().toJson(wifiMode);
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.SET_WIFI;
        mDefaultWifiRequest.data = data;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }
    /**
     * 恢复出厂设置
     */
    public static void resetFactory (IResponseCallBack mIResponseCallBack){
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.RESET_ACTORY;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }
    /**
     * 设置面板灯状态
     * panelLight 0关闭 1开启
     */
    public static void setPanelLight (IResponseCallBack mIResponseCallBack,String panelLight){
        DevicePanelMode panelMode=new DevicePanelMode();
        panelMode.panelLight=panelLight;
        String data = new GsonUtils<>().toJson(panelMode);
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.Set_PANEL_LIGHT;
        mDefaultWifiRequest.data=data;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }
    /**
     * 设置手势
     * gestureStatus  0关闭 1开启
     */
    public static void  setGesture (IResponseCallBack mIResponseCallBack,String gestureStatus){
        DeviceGestureMode gestureMode=new DeviceGestureMode();
        gestureMode.gestureStatus=gestureStatus;
        String data = new GsonUtils<>().toJson(gestureMode);
        DefaultWifiRequest mDefaultWifiRequest = new DefaultWifiRequest();
        mDefaultWifiRequest.requestCode = UUIDutils.createUUID();
        mDefaultWifiRequest.action = WifiConfig.RA.SET_GESTURE;
        mDefaultWifiRequest.data=data;
        String requestStr = new GsonUtils<DefaultWifiRequest>().toJson(mDefaultWifiRequest);
        WifiClient.sendMessageToServer(DataUtils.stringToXml(requestStr), mDefaultWifiRequest.requestCode, mIResponseCallBack);
    }

}
