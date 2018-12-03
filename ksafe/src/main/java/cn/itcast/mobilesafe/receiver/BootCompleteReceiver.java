package cn.itcast.mobilesafe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

public class BootCompleteReceiver extends BroadcastReceiver {
    private SharedPreferences sp = null;
	@Override
	public void onReceive(Context context, Intent intent) {
		sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
        boolean isProtected = sp.getBoolean("isProtected", false);
        //�ж��Ƿ�������
        System.out.println("xxxx���ͱ�������");
        if(isProtected){
        	//�������ж�SIM���Ƿ����˸ı�
        	String sim = sp.getString("sim", "");
        	TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        	String currentsim = manager.getSimSerialNumber();
        	if(!currentsim.equals(sim)){
        		//sim�����Ų�ͬ���ͱ�������
        		System.out.println("���ͱ�������");
        		SmsManager sms = SmsManager.getDefault();
        		String safetyTel = sp.getString("tel", "");
        		sms.sendTextMessage(safetyTel, null, "sim ka fa sheng le gai bian", null, null);
        	}
        }
	}

}

































