package cn.yinxm.lib.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import cn.yinxm.lib.utils.log.LogUtil;
import cn.yinxm.lib.utils.sign.util.MD5Util;

/**
 * Created by yinxm on 2017/4/20.
 * 功能:
 */

public class HardwareUID {

    /**
     * 获取设备唯一标示
     * @return
     */
    public static String getDeviceUID(Context context) {
        String  deviceId = getDeviceUIDNoNeedPermission(context);
        LogUtil.d("1, deviceId=" + deviceId);
        if (StringUtil.isBlank(deviceId)) {
            deviceId = DeviceUtil.getDeviceId(context);
        }
        LogUtil.d("2, deviceId=" + deviceId);
        if (StringUtil.isBlank(deviceId)) {
            deviceId = NetworkUtil.getMacAddress(context);
            LogUtil.d("mac deviceId=" + deviceId);//mac deviceId=00:90:4c:36:ad:2d
            if (StringUtil.isNotBlank(deviceId)) {
                deviceId = deviceId.replaceAll(":", "");
            }
        }
        LogUtil.d("3, deviceId=" + deviceId);

        return deviceId;
    }

    /**
     * 获取设备唯一标示，不需要任何权限
     * @return
     */
    public static String getDeviceUIDNoNeedPermission(Context context) {
        String mixedInfo = getDeviceMixedInfo();
        String rtn = getAndroidId(context);
        LogUtil.d("androidId="+rtn);
        rtn = mixedInfo + rtn;
        LogUtil.d("rtn1="+rtn);

        try {
            rtn = MD5Util.getMD5(rtn);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rtn;
    }




    private static String getDeviceMixedInfo() {
        return "xiaoka" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
    }
    private static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "android_id");
    }
}
