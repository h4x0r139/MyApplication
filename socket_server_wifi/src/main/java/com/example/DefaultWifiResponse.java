package com.example;

import java.io.Serializable;

/**
 * Created by yinxm on 2016/8/2.
 */

public class DefaultWifiResponse implements Serializable {
    public String code;
    public String msg;
    public String action;
    public String requestCode;
    public String requestFrom;
    public String requestTo;
    public String data;

}
