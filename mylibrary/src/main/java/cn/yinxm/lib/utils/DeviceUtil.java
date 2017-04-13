package cn.yinxm.lib.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import static android.os.Build.MODEL;

public class DeviceUtil {


    /**
     * 返回版本名字
     * 对应build.gradle中的versionName
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 返回版本号
     * 对应build.gradle中的versionCode
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context) {
        String versionCode = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = String.valueOf(packInfo.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取设备的唯一标识，deviceId
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        //java.lang.SecurityException: getDeviceId: Neither user 10220 nor current process has android.permission.READ_PHONE_STATE.
        String deviceId = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            deviceId = tm.getDeviceId();
            if (deviceId == null) {
                deviceId = "";
            }
        } catch (Exception e) {
            LogUtil.e(e);
        }
        return deviceId;
    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return MODEL;
    }

    /**
     * 获取手机Android API等级（22、23 ...）
     *
     * @return
     */
    public static int getBuildLevel() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取手机Android 版本（4.4、5.0、5.1 ...）
     *
     * @return
     */
    public static String getBuildVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取当前App进程的id
     *
     * @return
     */
    public static int getAppProcessId() {
        return android.os.Process.myPid();
    }

    /**
     * 获取当前App进程的Name
     *
     * @param context
     * @param processId
     * @return
     */
    public static String getAppProcessName(Context context, int processId) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取所有运行App的进程集合
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == processId) {
                    CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));

                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                Log.e(DeviceUtil.class.getName(), e.getMessage(), e);
            }
        }
        return processName;
    }


    /**
     * 通过Uri找到File
     *
     * @param context
     * @param uri
     * @return
     */
    public static File uri2File(Activity context, Uri uri) {
        File file;
        String[] project = {MediaStore.Images.Media.DATA};
        Cursor actualImageCursor = context.getContentResolver().query(uri, project, null, null, null);
        if (actualImageCursor != null) {
            int actual_image_column_index = actualImageCursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            actualImageCursor.moveToFirst();
            String img_path = actualImageCursor
                    .getString(actual_image_column_index);
            file = new File(img_path);
        } else {
            file = new File(uri.getPath());
        }
        if (actualImageCursor != null) actualImageCursor.close();
        return file;
    }

    /**
     * 获取AndroidManifest.xml里 <meta-data>的值
     *
     * @param context
     * @param name
     * @return
     */
    public static String getMetaData(Context context, String name) {
        String value = null;
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 得到无线网关的IP地址
     *
     * @return
     */
    public static String getWirelseeIp() {

        try {
            // 获取本地设备的所有网络接口
            Enumeration<NetworkInterface> enumerationNi = NetworkInterface
                    .getNetworkInterfaces();
            while (enumerationNi.hasMoreElements()) {
                NetworkInterface networkInterface = enumerationNi.nextElement();
                String interfaceName = networkInterface.getDisplayName();

                // 如果是无线网卡
                if (interfaceName.equals("wlan0")) {
                    Enumeration<InetAddress> enumIpAddr = networkInterface
                            .getInetAddresses();

                    while (enumIpAddr.hasMoreElements()) {
                        // 返回枚举集合中的下一个IP地址信息
                        InetAddress inetAddress = enumIpAddr.nextElement();
                        // 不是回环地址，并且是ipv4的地址
                        if (!inetAddress.isLoopbackAddress()
                                && inetAddress instanceof Inet4Address) {
                            Log.i("tag", inetAddress.getHostAddress() + "   ");

                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getDebugInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BOARD=" + Build.BOARD).append("\n");//主板
        stringBuilder.append("BRAND=" + Build.BRAND).append("\n"); //android系统定制商
//        stringBuilder.append("SUPPORTED_ABIS="+Build.SUPPORTED_ABIS).append("\n"); //cpu指令集
        stringBuilder.append("DEVICE=" + Build.DEVICE).append("\n");//设备参数
        stringBuilder.append("DISPLAY=" + Build.DISPLAY).append("\n");//显示屏参数
        stringBuilder.append("FINGERPRINT=" + Build.FINGERPRINT).append("\n");// 唯一编号
        stringBuilder.append("SERIAL=" + Build.SERIAL).append("\n"); //硬件序列号
        stringBuilder.append("ID=" + Build.ID).append("\n"); //修订版本列表
        stringBuilder.append("MANUFACTURER=" + Build.MANUFACTURER).append("\n"); //硬件制造商
        stringBuilder.append("MODEL=" + MODEL).append("\n"); //版本
        stringBuilder.append("HARDWARE=" + Build.HARDWARE).append("\n"); //硬件名
        stringBuilder.append("PRODUCT=" + Build.PRODUCT).append("\n"); //手机产品名
        stringBuilder.append("TAGS=" + Build.TAGS).append("\n"); //BUILD的标签
        stringBuilder.append("TYPE=" + Build.TYPE).append("\n"); //builder类型
        stringBuilder.append("CODENAME=" + Build.VERSION.CODENAME).append("\n"); //当前开发代号
        stringBuilder.append("INCREMENTAL=" + Build.VERSION.INCREMENTAL).append("\n"); //源码控制版本号
        stringBuilder.append("RELEASE=" + Build.VERSION.RELEASE).append("\n"); //版本字符串
        stringBuilder.append("SDK_INT=" + Build.VERSION.SDK_INT).append("\n"); //版本号
        stringBuilder.append("HOST=" + Build.HOST).append("\n"); //Host值
        stringBuilder.append("USER=" + Build.USER).append("\n"); //USER 名
        stringBuilder.append("TIME=" + Build.TIME);//编译时间4
        return stringBuilder.toString();
    }
}