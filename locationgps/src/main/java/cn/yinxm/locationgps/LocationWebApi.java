package cn.yinxm.locationgps;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import cn.yinxm.lib.utils.sign.util.MD5Util;

/**
 * Created by yinxm on 2017/4/1.
 * 功能:
 */

public class LocationWebApi {
    private static final String AMAP_KEY = "566a9839f6e80057e626e6ca9481eae7";

    public static void getAddress(long longitude, long latitude) {
//        key	请求服务权限标识	用户在高德地图官网申请Web服务API类型Key	必填	无
//        location	经纬度坐标	规则： 最多支持20个坐标点。多个点之间用"|"分割。经度在前，纬度在后，经纬度间以“，”分割，经纬度小数点后不得超过6位	必填	无
//        http://restapi.amap.com/v3/geocode/regeo?output=xml&location=116.310003,39.991957&key=<用户的key>&radius=1000&extensions=all

        TreeMap<String, String> reqParams = new TreeMap<>();
        reqParams.put("location",""+longitude+","+latitude);
        String sign = getSign(reqParams);
        reqParams.put("key",AMAP_KEY);

    }

    /**
     * 获取高德地图签名数据
     * @param params
     * @return
     */
    private static String getSign(TreeMap<String, String> params) {
        String sign = null;
//        请求服务为testservice，请求参数分别为a=23，b=12，d=48，f=8，c=67；私钥为bbbbb 则数字签名：sig=MD5(a=23&b=12&c=67&d=48&f=8bbbbb)
        if (params != null) {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            while (iterator != null && iterator.hasNext()) {
                Map.Entry<String, String> entry =  iterator.next();
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
            stringBuilder.append(AMAP_KEY);
            Log.d("yinxm", "plain="+stringBuilder.toString());
            try {
                sign = MD5Util.getMD5(stringBuilder.toString(), "UTF-8");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return sign;
    }
}
