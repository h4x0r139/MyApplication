package cn.yinxm.lib.utils.log;

import android.util.Log;

import cn.yinxm.lib.utils.StringUtil;


/**
 * Created by yinxm on 2016/3/24.
 * 日志打印工具类
 */
public class LogUtil {
	public static boolean DEBUG = true;
	public static String TAG="yinxm";
	/**
	 * 调试
	 * @param msg 内容
	 */
	public static void d(String msg){
		d(TAG, msg);
	}

	/**
	 * 调试
	 * @param tag
	 * @param msg 内容
	 */
	public static void d(String tag, String msg){
		if (DEBUG) {
			if (StringUtil.isBlank(tag)) {
				tag = TAG;
			}
			Log.d(tag, msg);
			LogFileUtil.d(tag, msg);
		}
	}

	public static  void i(String msg) {
		i(null, msg);
	}

	public static  void i(String tag, String msg) {
		if (DEBUG) {
			if (StringUtil.isBlank(tag)) {
				tag = TAG;
			}
			Log.i(tag, msg);
			LogFileUtil.i(tag, msg);
		}
	}


	/**
	 * 错误
	 * @param exceptionStr 内容
	 */
	public static void e(String exceptionStr){
		if (DEBUG) {
			e(TAG, exceptionStr, null);
		}
	}

	/**
	 * 错误
	 * @param tag
	 * @param exceptionStr 内容
	 */
	public static void e(String tag, String exceptionStr){
		if (DEBUG) {
			e(tag, exceptionStr, null);
		}
	}


	/**
	 * 错误
	 * @param exception 内容
	 */
	public static void e(Exception exception){
		if (DEBUG) {
			e(TAG, exception);
		}
	}
	/**
	 * 错误
	 * @param exception 内容
	 */
	public static void e(String tag, Exception exception){
		if (DEBUG) {
			e(tag, null, exception);
		}
	}

	/**
	 * 错误
	 * @param exception 内容
	 */
	public static void e(String tag, String exceptionStr, Exception exception){
		if (StringUtil.isBlank(tag)) {
			tag = TAG;
		}
		String errorStr = getExceptionStr(exceptionStr, exception);
		if (DEBUG) {
			while (errorStr.length() > 4000) {
				Log.e(tag, errorStr.substring(0, 4000));
				errorStr = errorStr.substring(4000);
			}
			Log.e(tag, errorStr);
			LogFileUtil.e(tag, errorStr);
		}
		if (exception != null && errorStr != null) {//上传异常
			String uploadStr = "";
			if (errorStr.length() > 500) {
				uploadStr = errorStr.substring(0, 500) + "...";
			}
//			ExceptionUtil.uploadExeption(uploadStr);
        }

	}

	/**
	 * 打印日志
	 * @param e
	 * @return
     */
	public static String getExceptionStr(String exceptionStr, Exception e) {
		StringBuilder sb = new StringBuilder();
		if (StringUtil.isNotBlank(exceptionStr)) {
			sb.append(exceptionStr);
		}
		if (e != null) {
			StackTraceElement [] stack = e.getStackTrace();
			sb.append(e.toString()).append("\n");
			for (int i=0; i<stack.length; i++ ){
				sb.append("at ");
				sb.append(stack[i].toString());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
