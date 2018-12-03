package com.carrey.asyncloaddemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 工具类，用于获得要加载的图片资源
 * @author carrey
 *
 */
public class ImageHelper {
	
	private static final String TAG = "ImageHelper";
	
	public static String getImageUrl(String webServerStr, int position) {
		return "http://" + webServerStr + "/" + (position % 50) + ".jpg";
	}
	
	/**
	 * 获得网络图片Bitmap
	 * @param imageUrl
	 * @return
	 */
	public static Bitmap loadBitmapFromNet(String imageUrlStr) {
		Bitmap bitmap = null;
		URL imageUrl = null;
		
		if (imageUrlStr == null || imageUrlStr.length() == 0) {
			return null;
		}
		
		try {
			imageUrl = new URL(imageUrlStr);
			URLConnection conn = imageUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			int length = conn.getContentLength();
			if (length != -1) {
				byte[] imgData = new byte[length];
				byte[] temp = new byte[512];
				int readLen = 0;
				int destPos = 0;
				while ((readLen = is.read(temp)) != -1) {
					System.arraycopy(temp, 0, imgData, destPos, readLen);
					destPos += readLen;
				}
				bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
			}
		} catch (IOException e) {
			Log.e(TAG, e.toString());
			return null;
		}
		
		return bitmap;
	}
}
