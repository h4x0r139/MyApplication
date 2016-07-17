package cn.itcast.mobilesafe.test;

import java.util.List;

import cn.itcast.mobilesafe.domain.ContactInfo;
import cn.itcast.mobilesafe.engine.ContactInfoService;
import android.test.AndroidTestCase;

public class TestGetContactInfo extends AndroidTestCase {

	public void testGetContacts() throws Exception{
		ContactInfoService service = new ContactInfoService(getContext());
		List<ContactInfo> infos =  service.getContactInfo();
		for(ContactInfo info : infos ){
			System.out.println(info.getName());
			System.out.println(info.getPhone());
			
		}
	}
}
