package cn.yinxm.lib.permission;

import android.Manifest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by yinxm on 2016/9/27.
 * android 6.0 需要动态获取的Dangerous 权限声明
 */
public class PermissionConstant {
    public static final String[] APP_DANGEROUS_PERMISSIONS = {
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.SEND_SMS,   //m800读取短信
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    public static final String[] APP_DANGEROUS_PERMISSIONS_NAME = {
            "电话",
            "通讯录",
            "位置信息",
            "相机",
            "麦克风",
            "短信",
            "存储"
    };

    public static final Map<String,String> DANGER_PERMISSIONS_NAME_MAP = new HashMap<>();
    static {
        for (int i=0; i<APP_DANGEROUS_PERMISSIONS.length; i++) {
            DANGER_PERMISSIONS_NAME_MAP.put(APP_DANGEROUS_PERMISSIONS[i], APP_DANGEROUS_PERMISSIONS_NAME[i]);
        }
    }

}
