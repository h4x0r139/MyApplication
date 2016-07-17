package cn.itcast.mobilesafe.engine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;

public class DownLoadFileTask {
	
	/**
	 * 
	 * @param path �������ļ�·��
	 * @param filepath �����ļ�·�� 
	 * @return �����ļ�����
	 * @throws Exception
	 */
	public static File getFile(String path,String filepath,ProgressDialog pd) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		if(conn.getResponseCode() == 200){
			int total =  conn.getContentLength();
			pd.setMax(total);
			InputStream is = conn.getInputStream();
			File file = new File(filepath);
			FileOutputStream fos = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			int process = 0;
			while((len = is.read(buffer))!=-1){
				fos.write(buffer, 0, len);
				process +=len;
				pd.setProgress(process);
				Thread.sleep(50);
			}
			fos.flush();
			fos.close();
			is.close();
			
			return file;
		}
		return null;
	}
}
