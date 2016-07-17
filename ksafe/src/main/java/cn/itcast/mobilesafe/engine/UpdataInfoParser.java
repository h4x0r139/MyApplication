package cn.itcast.mobilesafe.engine;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import cn.itcast.mobilesafe.domain.UpdataInfo;

public class UpdataInfoParser {
	/**
	 * 
	 * @param is
	 *            ½âÎöµÄxmlµÄinputstream
	 * @return updateinfo
	 */
	public static UpdataInfo getUpdataInfo(InputStream is) throws Exception {
		XmlPullParser parser = Xml.newPullParser();
		UpdataInfo info = new UpdataInfo();
		parser.setInput(is, "UTF-8");
		int type = parser.getEventType();
		while(type!=XmlPullParser.END_DOCUMENT){
			switch (type) {
			case XmlPullParser.START_TAG:
				if("version".equals(parser.getName())){
					String version = parser.nextText();
					info.setVersion(version);
				}else if("description".equals(parser.getName())){
					String description = parser.nextText();
					info.setDescription(description);
				}else if("apkurl".equals(parser.getName())){
					String apkurl = parser.nextText();
					info.setApkurl(apkurl);
				}
				break;

			}
			type = parser.next();
		}
		
		return info;
	}
}















