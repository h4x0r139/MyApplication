package cn.itcast.mobilesafe.service;

import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.db.dao.BlackNumberDao;
import cn.itcast.mobilesafe.engine.NumberAddressService2;
import cn.itcast.mobilesafe.ui.CallInterceptRecode;
import cn.itcast.mobilesafe.uitl.TimeTransition;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.CallLog;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddressService extends Service {
	private TelephonyManager manager = null;
	private MyPhoneListener listener;
	//private TextView view = null;
	private BlackNumberDao dao = null;
	private SharedPreferences sp = null;
	private WindowManager windowManager = null;
	private View view = null;
	private long firstRingTime;
	private long endRingTime;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * ������ѯ�绰�����ַ����
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// ע��ϵͳ�绰�������ļ�����
		listener = new MyPhoneListener();
		manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		manager.listen(listener,
				PhoneStateListener.LISTEN_CALL_STATE);
		dao = new BlackNumberDao(this);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
	}

	private class MyPhoneListener extends PhoneStateListener {
		/**
		 * �绰״̬�����ı���õķ���
		 */
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			// �ڶ�������������ĵ绰����
			super.onCallStateChanged(state, incomingNumber);
			switch (state) {
			/**
			 * ���ھ�ֹ״̬��û�к���
			 */
			case TelephonyManager.CALL_STATE_IDLE:
				endRingTime = System.currentTimeMillis();
				long time = endRingTime - firstRingTime;
				if(time<5000){
					System.out.println("��һ���绰");
					showNotification(incomingNumber);
				}
				if(view!=null){
					windowManager.removeView(view);
					view = null;
					}
				//û�к��е�ʱ���ڴλ�ȡϵͳʱ�䣬�����ж��Ƿ���ɧ�ŵ绰1322����
				break;
				/**
				 * �����״̬
				 */
			case TelephonyManager.CALL_STATE_RINGING:
				//��ѯ����������ѯ��������ֱ�ӹҶϵ绰
				firstRingTime = System.currentTimeMillis();
				if(dao.find(incomingNumber)){
					System.out.println("��ѯ���˺�����"+incomingNumber);
					endCall();
					//ע��һ�����ݹ۲��� �۲�call_log��uri����Ϣ ,��һ������������һ��URI����ȥע�����ݹ۲���
					//�����������ǣ���ע�ᵽCallLog.Calls.CONTENT_URI���URI����ʱ�����ĸ��۲���ȥ�۲�
				    getContentResolver().registerContentObserver(CallLog.Calls.CONTENT_URI, true, new Myobserver(new Handler(),incomingNumber));	
				}
               String address = NumberAddressService2.getAddress(incomingNumber);
               showLoaction(address);
               //�������ʱ���ȡϵͳʱ��
				break;
				/**
				 * ��ͨ�绰��״̬
				 */
			case TelephonyManager.CALL_STATE_OFFHOOK:
				if(view!=null){
					windowManager.removeView(view);
					view = null;
					}
				break;
			}
		}
/**
 * �Ҷϵ绰
 */
		private void endCall() {
			try {
				Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
				IBinder binder = (IBinder)method.invoke(null, new Object[]{TELEPHONY_SERVICE});
				ITelephony telephony = ITelephony.Stub.asInterface(binder);
				telephony.endCall();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	/**
	 * notification״̬��
	 */
	public void showNotification(String incomingNumber){
		//1����һ��notification�Ķ���
	NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//2����Ϣ���͹���״̬��������ʾ����Ϣ,��һ��Ҫ��ʾ��notification���󴴽�����
	int icon = R.drawable.main_icon_36;
	String tickerText = "������һ���绰����";
	long when = System.currentTimeMillis();
	Notification notification = new Notification(icon, tickerText, when);
		//3����������ʾ����Ϣ,����notification��һЩ����
	Context context = getApplicationContext();
	String contentTitle = "��һ������";
	String address = NumberAddressService2.getAddress(incomingNumber);
	String contentText = incomingNumber+"("+address+")";
	//���notification�Զ����
	notification.flags = Notification.FLAG_AUTO_CANCEL;
	Intent notificationIntent = new Intent(this,CallInterceptRecode.class);
	//�����ص��ĵ绰���룬��ַ��ʱ�䣬���͵��绰���ؼ�¼��activity
	notificationIntent.putExtra("incomingNumber", incomingNumber);
	//��ȡϵͳ����ʱ��
//	long systemTime = System.currentTimeMillis();
	notificationIntent.putExtra("systemTime", TimeTransition.getTime(when+""));
	//��ѯ���绰����Ĺ�����
	notificationIntent.putExtra("address", address);
	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
	//�յ�notification��������
	notification.sound = Uri.parse(Environment.getExternalStorageDirectory()+"/"+"alarm.wav");
	notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	// 4. ͨ��manger��notification ����
	manager.notify(0, notification);
	}
/**
 * ����ֹͣ��ʱ����õķ���
 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		manager.listen(listener, PhoneStateListener.LISTEN_NONE);
		listener = null;
	}
	/**
	 * ��View������ص���������
	 */
	public void showLoaction(String address) {
		LayoutParams params = new LayoutParams();
        params.height = LayoutParams.WRAP_CONTENT;
        params.width = LayoutParams.WRAP_CONTENT;
        params.flags = LayoutParams.FLAG_NOT_FOCUSABLE
                | LayoutParams.FLAG_NOT_TOUCHABLE
                | LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = LayoutParams.TYPE_TOAST;
        params.setTitle("Toast");
        //Ĭ�Ϲ��ص��������м䣬���Ȼ�ȡλ�ã��ڰѱ����x��Y�����ý���
        params.gravity = Gravity.LEFT | Gravity.TOP;

        params.x =     sp.getInt("lastx", 0);
        params.y =     sp.getInt("lasty", 0);
//         view = new TextView(AddressService.this);
//        view.setText(address);
        view = View.inflate(this, R.layout.show_location, null);
        view.findViewById(R.id.iv_show_location);
        TextView tv_show_location = (TextView) view.findViewById(R.id.tv_show_location);
        tv_show_location.setText(address);
        ImageView iv_show_location = (ImageView) view.findViewById(R.id.iv_show_location);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_location);
        int colour = sp.getInt("colour", 0);
        if(colour==1){
        	ll.setBackgroundResource(R.drawable.call_locate_gray);
        }else if(colour==2){
        	ll.setBackgroundResource(R.drawable.call_locate_orange);
        }else if(colour==3){
        	ll.setBackgroundResource(R.drawable.call_locate_blue);
        }else if(colour==4){
        	ll.setBackgroundResource(R.drawable.call_locate_white);
        }else if(colour==5){
        	ll.setBackgroundResource(R.drawable.call_locate_green);
        }
        
        windowManager =  (WindowManager) this.getSystemService(WINDOW_SERVICE);
       windowManager.addView(view, params);
	}
	private class Myobserver extends ContentObserver{
		private String incomingnumber;
		public Myobserver(Handler handler,String incomingnumber) {
			super(handler);
			this.incomingnumber = incomingnumber;
		}
		/**
		 * ����������ݷ����ı��ִ�еķ���
		 */
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			deleteCallLog(incomingnumber);
			
			//��ɾ���˺��м�¼�� ��ע�����ݹ۲���
			getContentResolver().unregisterContentObserver(this);
		}
		/**
		 * ���ݵ绰����ɾ�����м�¼
		 * @param incomingNumber Ҫɾ�����м�¼�ĺ���
		 */
		private void deleteCallLog(String incomingnumber2) {
			ContentResolver resolver = getContentResolver();
			Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, null, "number=?", new String[]{incomingnumber2}, null);
		    if(cursor.moveToNext()){
		    	String id = cursor.getString(cursor.getColumnIndex("_id"));
		    	resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?",new String[]{id});
		    }
		}
	}
}
















