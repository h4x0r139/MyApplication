package com.h4x0r.work_ec.wifi.data;

/**
 * Created by yinxm on 2016/8/15.
 */

public class DeviceGestureMode {
    public String gestureStatus;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceGestureMode{");
        sb.append("gestureStatus='").append(gestureStatus).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
