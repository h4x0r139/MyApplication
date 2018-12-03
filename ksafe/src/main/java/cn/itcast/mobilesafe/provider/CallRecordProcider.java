package cn.itcast.mobilesafe.provider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.itcast.mobilesafe.domain.CallRecord;
import cn.itcast.mobilesafe.domain.ContactInfo;
import cn.itcast.mobilesafe.engine.NumberAddressService2;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;

public class CallRecordProcider {
	private static final String TAG = "CallRecordProcider";
	private Context context = null;
	private String number = null;
	public CallRecordProcider(Context context) {
		
		this.context = context;
	}

	/**
	 * 查询所有的呼叫记录
	 * 
	 */
	public List<CallRecord> queryCallLog() {
		ContentResolver  resolver = context.getContentResolver();
		List<CallRecord> list = new ArrayList<CallRecord>();
		//呼叫记录的URI CallLog.Calls.CONTENT_URI
		//Uri uri = Uri.parse("CallLog.Calls.CONTENT_URI");
		
		/* 
	     * 根据电话号码取得联系人姓名 
	     */  
	     
	        String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,  
	                                ContactsContract.CommonDataKinds.Phone.NUMBER};  
	  
	       
		Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null); 
		while(cursor.moveToNext()){//查询到了呼叫记录
			CallRecord call = new CallRecord();
			//获取到电话号码
			number = cursor.getString(cursor.getColumnIndex("Calls.NUMBER"));
			call.setNumber(number);
			//获取到毫秒数 转换成我们能看懂的时间
			String date = cursor.getString(cursor.getColumnIndex("Calls.DATE"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date d = new Date(Long.parseLong(date));
			String time = sdf.format(d);
			call.setDate(time);
			System.out.println(time);
			 /**
			  * 根据电话号码查询出地址
			  */
			String address = NumberAddressService2.getAddress(number);
			call.setAddress(address);
			System.out.println(address);
			//根据电话号码查询人的姓名
			Cursor nameCursor = context.getContentResolver().query(  
		                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  
		                projection,    // Which columns to return.   
		                ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + number + "'", // WHERE clause.   
		                null,          // WHERE clause value substitution   
		                null); 
			 if( nameCursor.moveToNext() ) {  
	        	 Log.d(TAG, "getPeople cursor.getCount() = " + nameCursor.getCount());  
		 	        for( int i = 0; i < nameCursor.getCount(); i++ )  
		 	        {  
		 	        	nameCursor.moveToPosition(i);  
		 	              
		 	            // 取得联系人名字   
		 	            int nameFieldColumnIndex = nameCursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);     
		 	            String name = nameCursor.getString(nameFieldColumnIndex);  
		 	            System.out.println(name);
		 	           call.setName(name);
		 	        //    return name;
		 	        }  
	        	
	        }else{
	        	System.out.println(number);
	        	 call.setName(number);
	        }  
			list.add(call);
		
		}
		//return list;
		
	       
	       
	        return list;
	}
}










