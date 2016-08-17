package cn.yinxm.data;

import android.net.Uri;

/**
 * Created by yinxm on 2016/8/17.
 */
public class WifiDbConstant {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "wifiplayer.db";
    public static final String DEVICE_CONFIG_TABLE_NAME = "device_config";


    public static final String CREATE_TABLE_DEVICE_CONFIG = "CREATE TABLE "
            +DEVICE_CONFIG_TABLE_NAME+"( id INTEGER PRIMARY KEY NOT NULL, key_item TEXT, value_item TEXT)";


    public static final String WIFI_PLAYER_AUTHORITY = "cn.yinxm.data.provider.wifiplayer"; // 与AndroidManifest保持一致
    public static final int PUB_CONFIG_CODE = 1; //
    public static final String PUB_CONFIG_ACTION = "/pubconfig"; //
    public static final Uri PUB_CONFIG_URI=  Uri.parse("content://" + WIFI_PLAYER_AUTHORITY + PUB_CONFIG_ACTION);

}
