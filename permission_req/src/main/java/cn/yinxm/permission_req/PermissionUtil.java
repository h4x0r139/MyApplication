package cn.yinxm.permission_req;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import java.lang.reflect.Method;

import cn.yinxm.lib.utils.LogUtil;

/**
 * Created by yinxm on 2016/12/29.
 * 功能:
 */

public class PermissionUtil {
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static void requestPermission(Context context, String permission, PermissionGrantResult permissionGrantResult ) {
        boolean permissionGranted = false;
        if (Build.VERSION.SDK_INT >= 23) {

            try {
                Class clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int code = (int) method.invoke(context, permission);
                LogUtil.d("code="+code);
                if (code == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                }else {
                    // TODO: 2016/12/29 动态申请权限
//                    ActivityCompat.requestPermissions(this, missedPermissions, 0);//Visit https://developer.android.com/training/permissions/requesting.html for more information.
                    clazz = Class.forName("android.support.v4.app.ActivityCompat");
//                    method = clazz.getMethod("requestPermissions", );//第一个参数为Activity

                }
            } catch (Exception e) {
                LogUtil.e("异常",e);
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                permissionGranted = true;
            }
        }
    }
}
