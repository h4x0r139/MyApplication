package cn.itcast.mobilesafe.receiver;

import cn.itcast.mobilesafe.ui.LostProtectedActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CallPhone extends BroadcastReceiver {
    private SharedPreferences sp = null;
	@Override
	public void onReceive(Context context, Intent intent) {
		sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		//获取拨打电话的电话号码
		String num = getResultData();
        if("110".equals(num)){
        	Intent lostintent = new Intent(context,LostProtectedActivity.class);
        	lostintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	context.startActivity(lostintent);
        	setResultData(null);
        }else{
        	
        	String auto_ip_dialing = sp.getString("auto_ip_dialing_num", "");
        	if(auto_ip_dialing.equals("")){
        		
        	}else{
        		String newnumber = auto_ip_dialing+num;
            	//重新设置结果
            	setResultData(newnumber);
        	}
        }
	}

}
