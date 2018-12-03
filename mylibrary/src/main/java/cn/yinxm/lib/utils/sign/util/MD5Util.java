package cn.yinxm.lib.utils.sign.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
    public static String getMD5(String val) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return getMD5(val, "UTF-8");
    }

    public static String getMD5(String val, String charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(val.getBytes(charset));
        byte[] m = md5.digest();//加密
        return getBytesToHexString(m);
    }

    public static String getBytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
