package com.h4x0r.ecarx.wifi.data;

import java.io.Serializable;

/**
 * Created by yinxm on 2016/8/1.
 */

public class WifiRequest implements Serializable {
    public int code;
    public String msg;
    public String action;
    public String requestCode;
    public String requestFrom;
    public String requestTo;
    public String data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WifiRequest{");
        sb.append("code=").append(code);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", action='").append(action).append('\'');
        sb.append(", requestCode='").append(requestCode).append('\'');
        sb.append(", requestFrom='").append(requestFrom).append('\'');
        sb.append(", requestTo='").append(requestTo).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
