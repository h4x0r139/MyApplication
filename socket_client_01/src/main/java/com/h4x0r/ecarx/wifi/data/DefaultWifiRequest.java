package com.h4x0r.ecarx.wifi.data;

import com.h4x0r.ecarx.wifi.config.WifiConfig;

import java.io.Serializable;


/**
 * Created by yinxm on 2016/8/2.
 */

public class DefaultWifiRequest implements Serializable {
    public String action;
    public String requestCode;
    public String requestFrom = WifiConfig.RM.ANDROID_APP;
    public String requestTo =  WifiConfig.RM.CIGAR_HARD_WARE;
    public String data;
}
