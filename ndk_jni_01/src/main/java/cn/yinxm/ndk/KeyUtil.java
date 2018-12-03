package cn.yinxm.ndk;

import android.util.Log;

/**
 * Created by yinxm on 2016/12/29.
 * 功能:
 */

public class KeyUtil {

    public static native String getKey();

    /**
     *
     * @return
     */
    public static native String callJavaStaticMethod();

    /**
     * JNI调用实例方法
     * @return
     */
    public static native String callJavaObjectMethod();

    public static String getStaticMethod() {
        Log.d("yinxm","getStaticMethod");
        return "call KeyUtil.getStaticMethod";
    }
}
