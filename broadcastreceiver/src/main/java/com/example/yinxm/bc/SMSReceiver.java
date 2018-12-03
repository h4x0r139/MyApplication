package com.example.yinxm.bc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("receive message");

		//接受Intent对象当中的数据
		Bundle bundle = intent.getExtras();
		//在Bundle对象当中有一个属性名为pdus，这个属性的值是一个Object数组
		Object[] myOBJpdus = (Object[]) bundle.get("pdus");
		//创建一个SmsMessage类型的数组
		SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
		System.out.println(messages.length);
		for (int i = 0; i<myOBJpdus.length; i++)
		{
			//使用Object数组当中的对象创建SmsMessage对象
			messages[i] = SmsMessage.createFromPdu((byte[]) myOBJpdus[i]);
			//调用SmsMessage对象的getDisppalyMessageBody()方法，就可以得到消息的内容
			System.out.println(messages[i].getDisplayMessageBody());
		}
		try {
			Thread.sleep(30 * 1000);
			System.out.println("-------------------------------");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}