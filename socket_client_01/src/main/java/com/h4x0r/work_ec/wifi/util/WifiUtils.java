package com.h4x0r.work_ec.wifi.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yinxm on 2016/8/1.
 */

public class WifiUtils {

    public static String inputStreamToString(InputStream in) {
        synchronized (in) {
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[100];
            try {
                for (int n; (n = in.read(b)) != -1; ) {
                    out.append(new String(b, 0, n));
                }
            } catch (IOException e) {
            }
            return out.toString();
        }
    }
}
