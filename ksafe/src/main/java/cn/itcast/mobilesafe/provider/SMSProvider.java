package cn.itcast.mobilesafe.provider;
//本源码免费下载自 http://javaapk.com
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import cn.itcast.mobilesafe.domain.SmsInfo;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Xml;

public class SMSProvider {
    private Context context = null;

	public SMSProvider(Context context) {
		this.context = context;
	}
	/**
	 * 获得所有的信息
	 * @return
	 */
    public List<SmsInfo> getSmsInfo(){
    	List<SmsInfo> smsInfos = new ArrayList<SmsInfo>();
    	ContentResolver resolver = context.getContentResolver();
    	Uri uri = Uri.parse("content://sms");
    	Cursor cursor = resolver.query(uri, new String []{"_id","address","date","type","body"}, null, null, "date desc");
    	SmsInfo smsinfo = null;
    	while(cursor.moveToNext()){
    		String id = cursor.getString(0);
    		String address = cursor.getString(1);
    		String date = cursor.getString(2);
    		int type = cursor.getInt(3);
    		String body = cursor.getString(4);
			smsinfo = new SmsInfo(id, address, date, type, body);
			smsInfos.add(smsinfo);
			smsinfo = null;
    	}
    	return smsInfos;
    }
	/**苍井空
	 * 还原短信信息  ,
	 * @param path 短信备份文件对应的路径 
	 */
	public void restoreSms(String path,ProgressDialog pd) throws Exception{
		File file = new File (path);
		ContentValues values = null;
		FileInputStream fis = new FileInputStream(file);
		XmlPullParser parser =  Xml.newPullParser();
		parser.setInput(fis, "utf-8");
		int type = parser.getEventType();
		int currentcount=0;
		while ( type!=XmlPullParser.END_DOCUMENT){
			
			switch (type) {
			
			
			case XmlPullParser.START_TAG:
				if("count".equals(parser.getName())){
				   String count =	parser.nextText();
				   pd.setMax(Integer.parseInt(count));
				}
				
				if("sms".equals(parser.getName())){
					values = new ContentValues();
				}else if("address".equals(parser.getName())){
					values.put("address", parser.nextText());
				}else if("date".equals(parser.getName())){
					values.put("date", parser.nextText());
				}else if("type".equals(parser.getName())){
					values.put("type", parser.nextText());
				}else if("body".equals(parser.getName())){
					values.put("body", parser.nextText());
				}
				
				break;

			case XmlPullParser.END_TAG:
				if("sms".equals(parser.getName())){
					ContentResolver resolver = context.getContentResolver();
					resolver.insert(Uri.parse("content://sms/"), values);
					values = null;
					currentcount++;
					pd.setProgress(currentcount);
				}
				break;

			}
			type = parser.next();
		}
		
	}
	
}














