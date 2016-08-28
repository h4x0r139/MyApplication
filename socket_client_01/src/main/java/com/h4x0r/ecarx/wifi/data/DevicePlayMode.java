package com.h4x0r.ecarx.wifi.data;

/**
 * Created by yinxm on 2016/8/11.
 */

public class DevicePlayMode {

    public String playMode;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DevicePlayMode{");
        sb.append("playMode='").append(playMode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
