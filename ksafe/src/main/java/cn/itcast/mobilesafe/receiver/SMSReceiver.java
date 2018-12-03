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
/**�������������ֻ�����ģʽ��Զ��ɾ�����е�����
 * ע����ŵĹ㲥�����ߣ����û�����#*weizhi��"#*suoping"��#*shanchu"��"#*baojing"�����������
 * �����Ĺ���
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
		// ��ȡ��������
		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		for (Object pdu : pdus) {
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
			String content = sms.getMessageBody();
			System.out.println("���͵�����"+content);
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
			String telNumber = sp.getString("tel", "");
			//��ȡ���ŵĵ绰����
			String sender = sms.getOriginatingAddress();
			System.out.println("���ŵĵ绰����"+sender);
			//��ѯ���������ݿ������е绰���룬����ֹ�㲥
			if(dao.find(sender)){
				abortBroadcast();
				//�����Ϻܶ������ֹ���ź�: �Ѷ������ݴ�ŵ��Լ������ݿ�����
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
			}//�����������ݵ�ƥ��� (�ؼ���: ��Ʊ,����,��,ѧ��....��֤...)
			else if(content.contains("fapiao")){
				System.out.println("��������");
				abortBroadcast();
			}
		}
	}

}
