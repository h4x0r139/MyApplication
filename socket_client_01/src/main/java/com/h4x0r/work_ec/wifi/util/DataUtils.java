package com.h4x0r.work_ec.wifi.util;

/**
 * Created by yinxm on 2016/8/1.
 */

public class DataUtils {

    public static String stringToXml(String mStr) {
        StringBuilder sb = new StringBuilder();
        sb.append("<WORK_EC>");
        sb.append(mStr);
        sb.append("</WORK_EC>");
        return sb.toString();
    }


    public static String xmlToString(String mStr) {
        return mStr.substring("<WORK_EC>".length(), mStr.indexOf("</WORK_EC>"));
    }

}
