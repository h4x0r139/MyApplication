package com.h4x0r.ecarx.wifi.config;

/**
 * 配置
 * Created by yinxm on 2016/8/1.
 */

public class WifiConfig {
    /**
     * 服务器的ip端口
     */
    public static final String IP = "192.168.2.100";
    public static final int PROT = 18567;

    /**
     * 页面跳转的模拟等待时间
     */
    public static final long WAIT_TIME = 1000 * 1;

    //request & response from to
    public static class RM {
        public static final String CIGAR_HARD_WARE = "0";
        public static final String CIGAR_MUSIC_APP = "1";
        public static final String ECARX_SERVICE = "2";
        public static final String IOS_APP = "3";
        public static final String ANDROID_APP = "4";
    }

    //request action
    public static class RA {
        public static final String TEST = "TEST";
        public static final String GET_DEVICE_CONGIF = "getDeviceConfig";
        public static final String RESTART_WIFI = "restartWifi";
        public static final String SET_FM = "setFm";
        public static final String SET_PLAY_MODE = "setPlayMode";
        public static final String PLAY_AUDIO_TONE = "playAudioTone";
        public static final String SET_WIFI="setWifi";
        public static final String RESET_ACTORY="tresetFactory";
        public static final String Set_PANEL_LIGHT="setPanelLight";
        public static final String SET_GESTURE="setGesture";
    }

    //request code
    public static class RC {
        public static final String SUCCESS = "0";
        public static final String FAILURE = "1";
    }

    //request &  response keyname
    public static class RK {
        public static final String ACTION = "action";
        public static final String REQUEST_CODE = "requestCode";
        public static final String REQUEST_FROM = "requestFrom";
        public static final String REQUEST_TO = "requestTo";
    }
}
