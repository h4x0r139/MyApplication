package cn.yinxm.lib.interfaces.iml;

import java.io.Serializable;

import cn.yinxm.lib.interfaces.IAppConfig;

/**
 * Created by yinxm on 2016/8/11.
 */
public class AppConfigIml implements IAppConfig, Serializable {
    String mUserJID;
    boolean isLogEnabled = false;
    String deviceId = "";//设备唯一标识
    String appId;//server下发appid
    String version;//app版本号
    String appTypeName;
    boolean isNotAllowed2Play;
    int vehicleModel;//车机型号标识

    @Override
    public String getUserJID() {
        return mUserJID;
    }

    @Override
    public void setUserJID(String userJID) {
        mUserJID = userJID;
    }

    @Override
    public boolean isLogEnabled() {
        return isLogEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        isLogEnabled = logEnabled;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return this.deviceId;
    }

    @Override
    public String getWorkEcAppId() {
        return this.appId;
    }

    @Override
    public String getWorkEcVersion() {
        return this.version;
    }

    @Override
    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName(String appTypeName) {
        if (appTypeName != null) {
            this.appTypeName = appTypeName;
        }
    }

    @Override
    public boolean isNotAllowed2Play() {
        return isNotAllowed2Play;
    }

    public void setNotAllowed2Play(boolean notAllowed2Play) {
        isNotAllowed2Play = notAllowed2Play;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public int getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(int vehicleModel) {
        this.vehicleModel = vehicleModel;
    }
}
