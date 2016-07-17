package cn.itcast.mobilesafe.test;
//本源码免费下载自 http://javaapk.com
import java.util.List;

import cn.itcast.mobilesafe.domain.CallRecord;
import cn.itcast.mobilesafe.domain.ContactInfo;
import cn.itcast.mobilesafe.engine.ContactInfoService;
import cn.itcast.mobilesafe.provider.CallRecordProcider;
import android.test.AndroidTestCase;

public class testCallRecord extends AndroidTestCase {
     public void getcall(){
    	 CallRecordProcider service = new CallRecordProcider(getContext());
 		List<CallRecord> infos =  service.queryCallLog();
 		for(CallRecord info : infos ){
 			System.out.println(info.getNumber());
 			System.out.println(info.getDate());
 			
 		}
     }
}


