package cn.yinxm.lib;

import android.util.Log;


/**
 * Created by yinxm on 2016/3/24.
 * 日志打印工具类
 */
public class LogUtil {


	public static boolean DEBUG = true;
	public static String TAG="yika";
	
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
			Log.i(TAG+"i", msg);
		}
	}


	/**
	 * 错误
	 * @param exceptionStr 内容
	 */
	public static void e(String exceptionStr){
		if (DEBUG) {
			e(exceptionStr, null);
		}
	}

	
	/**
	 * 错误
	 * @param exception 内容
	 */
	public static void e(Exception exception){
		if (DEBUG) {
			e(null, exception);
		}
	}

	/**
	 * 错误
	 * @param exception 内容
	 */
	public static void e(String exceptionStr, Exception exception){
		String errorStr = getExceptionStr(exceptionStr, exception);
		if (exception != null && errorStr != null) {//上传异常
			String uploadStr = "";
			if (errorStr.length() > 500) {
				uploadStr = errorStr.substring(0, 500) + "...";
			}
        }
		if (DEBUG) {
			Log.e(TAG, errorStr);
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
