package cn.itcast.mobilesafe.engine;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

public class CallContactRecode {
	private static final String TAG = "CallContactRecode";
	private Context context = null;

	public CallContactRecode(Context context) {
		this.context = context;
	}
	/**
	 * ���ݵ绰�����ѯ����ϵ��
	 */
	public String getName(String number){

		 /* 
	     * ���ݵ绰����ȡ����ϵ������ 
	     */  
	     
	        String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,  
	                                ContactsContract.CommonDataKinds.Phone.NUMBER};  
	  
	        Cursor cursor = context.getContentResolver().query(  
	                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,  
	                projection,    // Which columns to return.   
	                ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + number + "'", // WHERE clause.   
	                null,          // WHERE clause value substitution   
	                null);   
	        if( cursor.moveToNext() ) {  
	        	 Log.d(TAG, "getPeople cursor.getCount() = " + cursor.getCount());  
		 	        for( int i = 0; i < cursor.getCount(); i++ )  
		 	        {  
		 	            cursor.moveToPosition(i);  
		 	              
		 	            // ȡ����ϵ������   
		 	            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);     
		 	            String name = cursor.getString(nameFieldColumnIndex);  
		 	            System.out.println(name);
		 	            return name;
		 	        }  
	        	
	        }else{
	        	System.out.println(number);
	           
	        }  
	       
	        return number;
	}
	
}
