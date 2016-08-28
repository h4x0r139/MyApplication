package com.h4x0r.ecarx.wifi.util;

/**
 * Created by yinxm on 2016/8/1.
 */

public class DataUtils {

    public static String stringToXml(String mStr) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ECARX>");
        sb.append(mStr);
        sb.append("</ECARX>");
        return sb.toString();
    }


    public static String xmlToString(String mStr) {
        return mStr.substring("<ECARX>".length(), mStr.indexOf("</ECARX>"));
    }

}
