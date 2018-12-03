package cn.yinxm.lib.api;

import cn.yinxm.lib.api.bean.LocationInfo;

/**
 * Created by yinxm on 2017/4/1.
 * 功能: 自定义地理位置接口
 */

public interface ILocationApi {
    /**
     * 获取最近一次定位结果
     *
     * @return
     */
    LocationInfo getLastLocationInfo();

    /**
     * 开始重新定位
     *
     * @param locationUpdate
     */
    void startUpdateLocation(OnLocationUpdate locationUpdate);

    void stopUpdateLocation();

    /**
     * 是否正在定位中
     */
    boolean isLocationing();

    /**
     * 设置重新定位间隔
     *
     * @param minLocationTime
     */
    void setMinLocationTime(int minLocationTime);

    interface OnLocationUpdate {
        /**
         * 定位更新回调
         *
         * @param isSucess         是否重新定位成功
         * @param lastLocationInfo 最后一次有效定位
         */
        void onLocationUpdte(boolean isSucess, LocationInfo lastLocationInfo);
    }
}

