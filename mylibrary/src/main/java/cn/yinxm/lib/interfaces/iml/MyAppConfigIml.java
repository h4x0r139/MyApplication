package cn.yinxm.lib.interfaces.iml;

import cn.yinxm.lib.interfaces.IMyAppConfig;

/**
 * Created by yinxm on 2016/8/11.
 */
public class MyAppConfigIml implements IMyAppConfig {
    String mUserJID;
    boolean isLogEnabled = false;
    String deviceId = "";

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

    @Override
    public void setLogEnabled(boolean logEnabled) {
        isLogEnabled = logEnabled;
    }

    @Override
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return this.deviceId;
    }


}
