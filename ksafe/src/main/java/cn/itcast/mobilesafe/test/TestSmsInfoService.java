package cn.itcast.mobilesafe.test;

import java.util.List;

import cn.itcast.mobilesafe.domain.SmsInfo;
import cn.itcast.mobilesafe.provider.SMSProvider;
import android.test.AndroidTestCase;

public class TestSmsInfoService extends AndroidTestCase {

	
	public void testGetSmsInfos() throws Exception{
		SMSProvider service  = new SMSProvider(getContext());
		List<SmsInfo>  smsinfos = service.getSmsInfo();
		System.out.println(smsinfos.size());
		assertEquals(6, smsinfos.size());
	}
}
