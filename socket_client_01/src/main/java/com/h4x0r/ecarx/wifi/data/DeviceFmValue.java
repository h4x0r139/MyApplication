package com.h4x0r.ecarx.wifi.data;

/**
 * Created by yinxm on 2016/8/11.
 */

public class DeviceFmValue {
    public String fm;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceFmValue{");
        sb.append("fm='").append(fm).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
