package cn.yinxm.lib.interfaces;

/**
 * Created by yinxm on 2016/8/11.
 */
public interface IMyAppConfig {
    public String getUserJID();
    public void setUserJID(String userJID);

    public boolean isLogEnabled();
    public void setLogEnabled(boolean logEnabled);

    //deviceId需要获取ReadPhoneState权限
    public void setDeviceId(String deviceId);
    public String getDeviceId();

}
