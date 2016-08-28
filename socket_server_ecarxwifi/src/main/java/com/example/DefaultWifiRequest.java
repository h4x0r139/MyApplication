package com.example;

import java.io.Serializable;

/**
 * Created by yinxm on 2016/8/2.
 */

public class DefaultWifiRequest implements Serializable {
    public String action;
    public String requestCode;
    public String requestFrom = "4";
    public String requestTo =  "1";
    public String data;
}
