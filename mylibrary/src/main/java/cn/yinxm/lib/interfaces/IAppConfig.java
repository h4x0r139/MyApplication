package cn.yinxm.lib.interfaces;

/**
 * Created by yinxm on 2016/8/11.
 */
public interface IAppConfig {
    public String getUserJID();
    public void setUserJID(String userJID);

    public boolean isLogEnabled();

    //deviceId需要获取ReadPhoneState权限
    public String getDeviceId();

    /**
     * 获取公共通信字段APPID
     * @return
     */
    public String getWorkEcAppId();

    /**
     * 获取公共通信字段_V
     * @return
     */
    public String getWorkEcVersion();

    public String getAppTypeName();

    /**
     * 是否不允许播放，默认返回false允许播放
     * @return
     */
    public boolean isNotAllowed2Play();

    /**
     * 获取车机型号
     * @return VehicleModel
     */
    public int getVehicleModel();


    class VehicleModel{
        /**
         * LX-1车机
         */
        public static final int LX_1 = 10;
    }

}
