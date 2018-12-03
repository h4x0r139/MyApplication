package com.h4x0r.work_ec.wifi.data;

/**
 * Created by yinxm on 2016/8/15.
 */

public class DevicePanelMode {
    public String panelLight;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DevicePanelMode{");
        sb.append("panelLight='").append(panelLight).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
