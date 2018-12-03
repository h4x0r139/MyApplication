package cn.itcast.mobilesafe.receiver;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.db.dao.BlackNumberDao;
import cn.itcast.mobilesafe.engine.GPSInfoProvider;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
/**第三个是重置手机出厂模式，远程删掉所有的数据
 * 注册短信的广播接受者，当用户发送#*weizhi，"#*suoping"，#*shanchu"，"#*baojing"这个短信内容
 * 防盗的功能
 * @author Administrator
 *
 */
public class SMSReceiver extends BroadcastReceiver {
	private SharedPreferences sp = null;
	private BlackNumberDao dao = null;
	@Override
	public void onReceive(Context context, Intent intent) {
		dao = new BlackNumberDao(context);
//		if(dao.find(number)){
//			
//		}
		// 获取短信内容
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		for (Object pdu : pdus) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
			String content = sms.getMessageBody();
			System.out.println("发送的内容"+content);
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
			String telNumber = sp.getString("tel", "");
			//获取短信的电话号码
			String sender = sms.getOriginatingAddress();
			System.out.println("短信的电话号码"+sender);
			//查询黑名单数据库里面有电话号码，就终止广播
			if(dao.find(sender)){
				abortBroadcast();
				//市面上很多软件终止短信后: 把短信内容存放到自己的数据库里面
			}
			if ("#*weizhi".equals(content)) {
				abortBroadcast();

				GPSInfoProvider provider = GPSInfoProvider.getInstance(context);
				String location = provider.getLocation();
				SmsManager smsmanager = SmsManager.getDefault();
				if (!"".equals(location)) {
					smsmanager.sendTextMessage(telNumber, null, location, null,
							null);
				}

			}else if("#*suoping".equals(content)){
				DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
				manager.resetPassword("123", 0);
				manager.lockNow();
				abortBroadcast();
			}else if("#*shanchu".equals(content)){
				DevicePolicyManager manager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
				manager.wipeData(0);
				abortBroadcast();
			}else if("#*baojing".equals(content)){
				for(int i=0;i<500;i++){
				MediaPlayer player = MediaPlayer.create(context, R.raw.alarm);
				player.setVolume(1.0f, 1.0f);
				
					player.start();
				}
					
					abortBroadcast();
			}//建立短信内容的匹配库 (关键字: 发票,卖房,哥,学生....办证...)
			else if(content.contains("fapiao")){
				System.out.println("垃圾短信");
				abortBroadcast();
			}
		}
	}

}
