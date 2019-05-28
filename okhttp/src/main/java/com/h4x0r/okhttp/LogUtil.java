package com.h4x0r.okhttp;

import android.util.Log;


/**
 * Created by yinxm on 2016/3/24.
 * 日志打印工具类
 */
public class LogUtil {


	public static boolean DEBUG = true;
	public static String TAG=TAG;
	
	/**
	 * 调试
	 * @param msg 内容
	 */
	public static void d(String msg){
		if (DEBUG) {
			Log.d(TAG, msg);
		}	
	}

	public static  void i(String msg) {
		if (DEBUG) {
			Log.i(TAG, msg);
		}
	}
	
	/**
	 * 错误
	 * @param msg 内容
	 */
	public static void e(String msg){
		if (DEBUG) {
			Log.e(TAG,msg);
		}
	}
}
