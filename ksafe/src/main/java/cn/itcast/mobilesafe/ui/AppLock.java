package cn.itcast.mobilesafe.ui;

import java.util.ArrayList;
import java.util.List;


import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.db.dao.AppLockDao;
import cn.itcast.mobilesafe.db.dao.AppUnLockDao;
import cn.itcast.mobilesafe.domain.AppInfo;
import cn.itcast.mobilesafe.engine.AppInfoServie;
import cn.itcast.mobilesafe.service.WatchDogService;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AppLock extends Activity {
    protected static final int FINISH = 22;
    protected static final int UNLOCKFINISH = 32;
    protected static final int APPLOCK = 34;
    protected static final int UNAPPLOCK = 35;
	private ListView lv_app_lock = null;
    private AppInfoServie infos = null;
    private List<AppInfo> appInfoList = null;
    //������ס�ļ���
    private List<String> lockInfoList = null;
    private List<AppInfo> unlockInfoList = null;
    private TextView tv_app_lock = null;
    private AppInfo appInfo = null;
    private AppLockDao dao = null;
    private AppLockAdapter adapter = null;
  //�Ƿ������˿��ؼ�����
  	private boolean isSwitchListenerOn = false;
  //��ǰ����״̬��trueΪ������falseΪ�ر�
  	private boolean isSwitchOn = false;
    private ImageView imageView1 = null;
    //��ָ����ʱ��ˮƽ����X����ǰ��ˮƽ����X
  	private float previousX, currentX;
  	//û�б�����dao
  	private AppUnLockDao unlockdao = null;
  //��ʼ��������е�Ӧ�ô�һ�����
  	private boolean flag = false;
  	private int startx; // ��¼������һ����ָ������Ļ��λ��
	private int starty;
	//��ѯ������������Ϣ
	List<AppInfo> rm = null;
	private PackageManager pm = null;
  	private boolean isSlipping = false;
    private LinearLayout ll_app_manager_loading = null;
    private List<String> lockList = null;
    private Handler handler = new Handler(){
        
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case FINISH:
			//	ll_app_manager_loading.setVisibility(View.INVISIBLE);
			
				lockInfoList = dao.getAllApps();
				List<AppInfo> rr = new ArrayList<AppInfo>();
                //�����������ʼ����ʱ�����Ȼ�ȡ���е�Ӧ�ó���Ȼ���ѯ����ס��Ӧ�ó���Ȼ���ȫ��Ӧ�ó���ȶ�ɾ�������
				for(int i=0;i<appInfoList.size();i++){
					String appName = appInfoList.get(i).getPackname();
		 			for (String info : lockInfoList) {
						if (info.equals(appName)) {
							rr.add(appInfoList.get(i));
						}
					}
					 
				}
				for (AppInfo  is :rr) {
					appInfoList.remove(is);
				}
				lv_app_lock.setAdapter(adapter);
				move();
				break;
			case UNLOCKFINISH:
				lockInfoList = dao.getAllApps();
				List<AppInfo> del = new ArrayList<AppInfo>();
                //�����������ʼ����ʱ�����Ȼ�ȡ���е�Ӧ�ó���Ȼ���ѯ����ס��Ӧ�ó���Ȼ���ȫ��Ӧ�ó���ȶ�ɾ�������
				for(int i=0;i<unlockInfoList.size();i++){
					String appName = unlockInfoList.get(i).getPackname();
		 			for (String info : lockInfoList) {
						if (info.equals(appName)) {
							del.add(unlockInfoList.get(i));
						}
					}
					 
				}
				for (AppInfo  is :del) {
					appInfoList.remove(is);
				}
				lv_app_lock.setAdapter(adapter);
				break;
			case APPLOCK:
				adapter.setList(rm);
 				adapter.notifyDataSetChanged();
				break;
			case UNAPPLOCK:
				adapter.setList(unlockInfoList);
				adapter.notifyDataSetChanged();
				break;	
			}
		
		}
    	
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_lock);
		Intent intent = new Intent(this,WatchDogService.class);
		startService(intent);
		lv_app_lock = (ListView) findViewById(R.id.lv_app_lock);
		adapter = new AppLockAdapter();
		infos = new AppInfoServie(this);
		lockInfoList = new ArrayList<String>();
		lockList = new ArrayList<String>();
		ll_app_manager_loading = (LinearLayout) findViewById(R.id.ll_app_manager_loading);
		unlockdao = new AppUnLockDao(this);
		pm = getPackageManager();
		dao = new AppLockDao(this);
		initUI();

		//���ó�����������listview��Ŀ��ʱ����ס�������
		lv_app_lock.setOnItemClickListener(new OnItemClickListener(){
            //parent��ʾlistview ��view��ʾ��ǰ�����item
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TranslateAnimation ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
						Animation.RELATIVE_TO_SELF, 1.0f,
						Animation.RELATIVE_TO_SELF, 1.0f,
						Animation.RELATIVE_TO_SELF, 2.0f);
				ta.setDuration(1000);
				view.startAnimation(ta);
				appInfo = (AppInfo) lv_app_lock.getItemAtPosition(position);
				// String packname = appInfo.getPackname();
				 String packname = appInfo.getPackname();
				 if(!dao.find(packname)){
					 ContentValues values = new ContentValues();
					 values.put("packname", packname);
					 getContentResolver().insert(Uri.parse("content://cn.itcast.applockprovider/insert"), values);
//					 dao.add(packname);
					 if(appInfoList.size()>0){
						 appInfoList.remove(appInfo);	 
					 }
					 

					 adapter.notifyDataSetChanged();
				 }
				
			}
			
		});
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		imageView1.setAlpha(22);
		tv_app_lock = (TextView) findViewById(R.id.tv_app_lock);
		
	}

	private void move() {
		//�����������û��������¼�
		imageView1.setOnTouchListener(new OnTouchListener(){

			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getRawX(); 
			//		int y = (int) event.getRawY();
					//��ȡ��ָ�ƶ��ľ���
					int dx = x-startx;
			//		int dy = y-starty;
					int l= imageView1.getLeft();
					int t= imageView1.getTop();
					int r = imageView1.getRight();
					int b = imageView1.getBottom();
					int left = l+dx;
					int top =  t;
					int right = r+dx;
					int below = b;
					//�ж��������Ҳ����ƶ�����Ļ
					if(l+dx<=0){
						left=0;
						right=140;
					}
					
					if(r+dx>=tv_app_lock.getWidth()+30){
						right=tv_app_lock.getWidth()+30;
						left=imageView1.getWidth();
					} 
					imageView1.layout(left, top, right, below);
					
					startx = (int) event.getRawX(); // ��ȡ���ƶ����λ��
					starty = (int) event.getRawY();
                    break;
               case MotionEvent.ACTION_DOWN:

       			
            	   startx = (int) event.getRawX();
             //      starty = (int) event.getRawY();
					break;
               case MotionEvent.ACTION_UP:
     			
       			if(event.getRawX() >= (tv_app_lock.getWidth() / 2)) {
       			    Toast.makeText(AppLock.this, "�Ѽ���", 0).show();
       			    //�������������ѯ�����еļ����ĳ�����Ϣ
       			    
       			 lockInfoList = dao.getAllApps();
 				
                 //�����������ʼ����ʱ�����Ȼ�ȡ���е�Ӧ�ó���Ȼ���ѯ����ס��Ӧ�ó���Ȼ���ȫ��Ӧ�ó���ȶ�ɾ�������
       			 rm = new ArrayList<AppInfo>();
 				new Thread(){
					@Override
					public void run() {
						 try {
							 //���ݳ���İ�����ȡ�����ͼƬ��Ӧ�ó��������
								for(String appinfos : lockInfoList){
									 AppInfo app = new AppInfo();
									ApplicationInfo appinfo = pm.getPackageInfo(appinfos, 0).applicationInfo;
									Drawable appicon = appinfo.loadIcon(pm);
									app.setIcon(appicon);
									String appname = appinfo.loadLabel(pm).toString();
									app.setAppname(appname);
									rm.add(app);
								 }

				 				Message msg = Message.obtain();
				 				msg.what = APPLOCK;
				 				handler.sendMessage(msg);
							} catch (Exception e) {
								e.printStackTrace();
							}
					}
 				}.start();
       			} else {
       				Toast.makeText(AppLock.this, "δ����", 0).show();
       		//		unlockInfoList = new ArrayList<AppInfo>();
       			 new Thread(){
					@Override
     				public void run() {
						lockInfoList = dao.getAllApps();
						List<AppInfo> rr = new ArrayList<AppInfo>();
						AppInfoServie appinfoservice = new AppInfoServie(AppLock.this);
						unlockInfoList = appinfoservice.getAllApps();
						if(lockInfoList.size()>0){
							for(int i=0;i<unlockInfoList.size();i++){
								String appName = unlockInfoList.get(i).getPackname();
					 			for (String info : lockInfoList) {
									if (info.equals(appName)) {
										rr.add(unlockInfoList.get(i));
									}
								}
							}
							for (AppInfo  is :rr) {
								unlockInfoList.remove(is);
							}
							Message msg = Message.obtain();
			 				msg.what = UNAPPLOCK;
			 				handler.sendMessage(msg);
//							adapter.setList(unlockInfoList);
//							adapter.notifyDataSetChanged();
						}else{
							initUI();	
						}
     				}
     				 
     			 }.start();
       			}
       			

					break;
				}
				return true;
			}
			
		});
	}

	private void initUI() {
	 
			 new Thread(){

				@Override
				public void run() {
					//���ص�ʱ����ʾ�������ɼ�
			//		ll_app_manager_loading.setVisibility(View.VISIBLE);
					appInfoList = infos.getAllApps();
					Message msg = new Message();
					msg.what = FINISH;
					handler.sendMessage(msg);
				}
				 
			 }.start();
 		
				
			 
		 
		
	}
	private class AppLockAdapter extends BaseAdapter {
      
		public void setList(List<AppInfo> lockList){
			appInfoList = lockList;
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return appInfoList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return appInfoList.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if(view==null){
            	view = View.inflate(AppLock.this, R.layout.lock_app_item, null); 
            }else{
            	view = convertView;
            }
            ImageView iv_app_icon = (ImageView) view.findViewById(R.id.iv_app_icon);
            TextView tv_app_name = (TextView) view.findViewById(R.id.tv_app_name);
       //     TextView tv_app_packname = (TextView) view.findViewById(R.id.tv_app_packname);
            
            ImageView iv_app_lock_status = (ImageView) view.findViewById(R.id.iv_app_lock_status);
            //�Ƿ���ס����ͼƬ
//            if(dao.find(appInfoList.get(position).getPackname())){
//            	iv_app_lock_status.setImageResource(R.drawable.app_lock_ic_locked);
//			}else{
//				iv_app_lock_status.setImageResource(R.drawable.app_lock_ic_unlock);
//			}
            
            
            
            iv_app_icon.setImageDrawable(appInfoList.get(position).getIcon());
            tv_app_name.setText(appInfoList.get(position).getAppname());
         //   tv_app_packname.setText(appInfoList.get(position).getPackname());
            iv_app_lock_status.setImageResource(R.drawable.app_lock_ic_unlock);
			return view;
		}

	}	
}
