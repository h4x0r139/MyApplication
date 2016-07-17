package cn.itcast.mobilesafe.engine;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.UpdataInfo;
import android.content.Context;

public class UpdataInfoService {
     private Context context;

	public UpdataInfoService(Context context) {
		
		this.context = context;
	}
	public UpdataInfo getUpdataInfo(int urlid) throws Exception{
		String path = context.getResources().getString(urlid);
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		InputStream is = conn.getInputStream();
		UpdataInfoParser parser = new UpdataInfoParser();
		
		return parser.getUpdataInfo(is);
	}
}
