package cn.yinxm.lib.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by yinxm on 2016/3/24.
 */
public class NetworkUtil {


    /**
     * 是否有网
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
//        android.permission.ACCESS_NETWORK_STATE.
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled();
    }

    public static boolean isBlueToothConnected(Context context) {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            return mBluetoothAdapter.isEnabled();
        }
        return false;
    }

    public static String getWifiIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int ipAddress = 0;
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            ipAddress = wifiInfo.getIpAddress();
        }
        return intToIp(ipAddress);
    }

    /**
     * 获取wifi网关ip
     *
     * @param context
     * @return
     */
    public static String getWifiGatewayIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int ipAddress = 0;
        if (wifiManager.isWifiEnabled()) {
            DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
            ipAddress = dhcpInfo.gateway;
        }
        return intToIp(ipAddress);
    }

    //// TODO: 2016/3/29 待验证


    public static String getWifiMac(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String macAddress = null;
        if (wifiManager.isWifiEnabled()) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            macAddress = wifiInfo.getMacAddress();
        }
        return macAddress;
    }

    /**
     * gprs 是否连接
     *
     * @param context
     * @return
     */
    public static boolean isGprsConnected(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        Network[] networks = conMan.getAllNetworks();
        NetworkInfo networkInfo = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo.State state = networkInfo.getState();
        return NetworkInfo.State.CONNECTED == state;
    }

    /**
     * gprs ipv4
     *
     * @param context
     * @return
     */
    public static String getGprsIP(Context context) {
//        <uses-permission android:name="android.permission.INTERNET"></uses-permission>
        return getGprsIPs(context)[0];
    }

    /**
     * 无论是wifi还是gprs都可用
     * todo 待整合
     * [0] ipv4地址
     * [1] ipv6地址
     *
     * @param context
     * @return
     */
    public static String[] getGprsIPs(Context context) {
//        <uses-permission android:name="android.permission.INTERNET"></uses-permission>
        String[] ips = new String[2];
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    if (!inetAddress.isLoopbackAddress()) {
//                        ips[2] = byte2hex(intf.getHardwareAddress());//获取mac地址
                        if (inetAddress instanceof Inet4Address) {
                            ips[0] = inetAddress.getHostAddress();
                        } else if (inetAddress instanceof Inet6Address) {
                            //inetAddress.isLinkLocalAddress()
                            ips[1] = inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ips;
    }
//    public static String getGprsMac(Context context) {
//
//    }


    public static String intToIp(int i) {
        return (i & 0xFF) + "." +
                ((i >> 8) & 0xFF) + "." +
                ((i >> 16) & 0xFF) + "." +
                (i >> 24 & 0xFF);
    }

    public static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer(b.length);
        String stmp = "";
        int len = b.length;
        for (int n = 0; n < len; n++) {
            stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                hs = hs.append("0").append(stmp);
            } else {
                hs = hs.append(stmp);
            }
        }
        return String.valueOf(hs);
    }


    /**
     * 获取MAC地址
     *
     * @param mContext
     * @return
     */
    public static String getMacAddress(Context mContext) {
//        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
//        <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"></uses-permission>
//        <uses-permission android:name="android.permission.WAKE_LOCK"></uses-permission>
        String macStr = "";
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getMacAddress() != null) {
            macStr = wifiInfo.getMacAddress();// MAC地址
        }
        return macStr;
    }

    /***
     * 获取网关IP地址
     * 无论是WIFI上网或者GPRS上网，均可使用
     *
     * @return
     */
    public static String getHostIp() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> ipAddr = intf.getInetAddresses();
                while (ipAddr.hasMoreElements()) {
                    InetAddress inetAddress = ipAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
        } catch (Exception e) {
        }
        return null;
    }


    class MyNetworkInfo {
        boolean isConnected;
        public String ipv4;
        public String ipv6;
        public String mac;
        public int netType;//TelephonyManager.NETWORK_TYPE_XXX
    }

}
