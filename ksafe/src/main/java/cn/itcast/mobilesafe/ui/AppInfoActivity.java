package cn.itcast.mobilesafe.ui;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.adapter.AppManagerAdapter;
import cn.itcast.mobilesafe.domain.AppInfo;
import cn.itcast.mobilesafe.engine.AppInfoServie;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.ScaleAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class AppInfoActivity extends Activity implements OnClickListener {
     protected static final int GET_ALL_APP_FINISH = 80;
	private ListView lv_app_manager = null;
     private AppInfoServie app = null;
     private List<AppInfo> list = null;
     private List<AppInfo> userAppInfo = null;
     private AppManagerAdapter adapter = null;
     private LinearLayout ll_loading;
     private PopupWindow pop = null;
     private boolean flag=true;
     private LinearLayout ll_popup = null;
     private TextView tv_app_manager = null;
     private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case GET_ALL_APP_FINISH:
				//������ɺ�ѽ�������������
				ll_loading.setVisibility(View.INVISIBLE);
				adapter = new AppManagerAdapter(list,AppInfoActivity.this);
				lv_app_manager.setAdapter(adapter);
				//�Զ���listview�ұ߹����򻻳ɱ��ͼƬ
				try {
					Field f = AbsListView.class.getDeclaredField("mFastScroller");
					f.setAccessible(true);
					Object o=f.get(lv_app_manager);
					f=f.getType().getDeclaredField("mThumbDrawable");
					f.setAccessible(true);
					Drawable drawable=(Drawable) f.get(o);
					drawable=getResources().getDrawable(R.drawable.mmm);
					f.set(o,drawable);
					Toast.makeText(AppInfoActivity.this, f.getType().getName(), 1000).show();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}
    	 
     };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.app_manager);
		lv_app_manager = (ListView) findViewById(R.id.lv_app_manager);
		ll_loading = (LinearLayout) this
				.findViewById(R.id.ll_app_manager_loading);
		tv_app_manager = (TextView) findViewById(R.id.tv_app_manager);
        inintUI();
        /**
         * �����û�Ӧ�ó���
         */
        tv_app_manager.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				String appName = tv_app_manager.getText().toString();
				//��������г����л����û�����,������л������г���
				if("���г���".equals(appName)){
					flag=false;
					tv_app_manager.setText("�û�����");
					userAppInfo = getUserApps(list);
					adapter.setApater(userAppInfo);
					adapter.notifyDataSetChanged();
				}else{
					flag=true;
					tv_app_manager.setText("���г���");
					adapter.setApater(list);
					adapter.notifyDataSetChanged();
				}
			

				
			}
        	
        });
		lv_app_manager.setOnItemClickListener(new OnItemClickListener(){
        

			/**
             *  ��һ�������Ƿ��ص�listview���ڶ��������ǵ�ǰ��view����
             *  �����������ǵ�ǰ��λ�ã����ĸ��ǵ�ǰλ�õ�ID
             */
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				dismisspop();
				String titleView;
				
//				AppInfo info = (AppInfo) lv_app_manager.getItemAtPosition(position);	
//				String packname = info.getPackname();
				//��ȡ��ǰ��view�����ڴ����е�λ�÷��õ���������
				int [] location = new int[2];
				view.getLocationInWindow(location);
				int i = location[0]+60;
				int j = location[1];
				
				/*TextView text = new TextView(AppInfoActivity.this);
				text.setText(packname);
				text.setTextSize(20);
				text.setTextColor(Color.RED);*/
				
				View popView = View.inflate(AppInfoActivity.this, R.layout.pop_item, null);
				
				LinearLayout ll_shape = (LinearLayout) popView.findViewById(R.id.ll_shape);
				LinearLayout ll_uninstall = (LinearLayout) popView.findViewById(R.id.ll_uninstall);
				LinearLayout ll_start = (LinearLayout) popView.findViewById(R.id.ll_start);
				
				ll_shape.setOnClickListener(AppInfoActivity.this);
				ll_uninstall.setOnClickListener(AppInfoActivity.this);
				ll_start.setOnClickListener(AppInfoActivity.this);
				
				ll_shape.setTag(position);
				ll_uninstall.setTag(position);
				ll_start.setTag(position);
				
				
				ll_popup = (LinearLayout) popView.findViewById(R.id.ll_popup);
				pop = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				//һ��Ҫ��popupwindow���ñ�����ɫ
				Drawable background = getResources().getDrawable(R.drawable.local_popup_bg);
				pop.setBackgroundDrawable(background);
				pop.showAtLocation(popView, 53, i, j);
				ScaleAnimation scal = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
				scal.setDuration(1000);
				ll_popup.startAnimation(scal);
			}
			
		});
		/**
		 * listview�����ļ����¼�
		 */
		lv_app_manager.setOnScrollListener(new OnScrollListener(){

			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				dismisspop();
				
			}

			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				dismisspop();
			}
			
		});
	}
	/**
	 * ��ʼ��UI����
	 */
	private void inintUI() {
		//		/���ڻ�ȡ���е�Ӧ�ó���󣬰ѽ��������ÿɼ�
				ll_loading.setVisibility(View.VISIBLE);
				app = new AppInfoServie(this);
				new Thread(){
		
					@Override
					public void run() {
						//������е�Ӧ�ó���
						list = app.getAllApps();
						Message msg = new Message();
						msg.what = GET_ALL_APP_FINISH;
						handler.sendMessage(msg);
					}
					
				}.start();
	}
	/**
	 * ��pop�����ڿյ�ʱ��ɾ���Ի���
	 */
	public void dismisspop(){
		if(pop!=null){
			pop.dismiss();
			pop = null;
		}
	}
	public void onClick(View v) {
		// �жϵ�ǰ�б��״̬
		String titletext;
		AppInfo app;
		TextView tv;
		String packname;
		
			if(tv_app_manager.getText().toString().equals("���г���")){
				int positon = (Integer) v.getTag();
				 app = list.get(positon);
				 packname = app.getPackname();
			}else{
				int positon = (Integer) v.getTag();
				 app = userAppInfo.get(positon);
				 packname = app.getPackname();
			}

		switch (v.getId()) {
		case R.id.ll_start:
			try {
				//��ȡ���еİ���Ϣ
				PackageInfo info = getPackageManager().getPackageInfo(
						packname,
						PackageManager.GET_UNINSTALLED_PACKAGES
								| PackageManager.GET_ACTIVITIES);
				//��ȡ���е�activity
				ActivityInfo [] activityinfos = info.activities;
				//��Ϊ��Щ��ϵͳactivity���Դ򲻿������ж���
				if(activityinfos.length>0){
					//��һ��activity�߱�����������,�õ�һ��activity��
					ActivityInfo startActivity = activityinfos[0];
					Intent intent  = new Intent();
					intent.setClassName(app.getPackname(), startActivity.name);
					startActivity(intent);
				}else {
					Toast.makeText(this, "��ǰӦ�ó����޷�����", 0).show();
				}
			} catch (NameNotFoundException e) {
				Toast.makeText(this, "Ӧ�ó����޷�����", 0).show();
				e.printStackTrace();
			}
			break;
        //�������
		case R.id.ll_shape:
			Intent shareIntent = new Intent();
			shareIntent.setAction(Intent.ACTION_SEND);
			shareIntent.setType("text/plain");
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "����");
			shareIntent.putExtra(Intent.EXTRA_TEXT, "�Ƽ���ʹ��һ������"+packname);
			shareIntent = Intent.createChooser(shareIntent, "����");
			startActivity(shareIntent);
			break;
		case R.id.ll_uninstall:
            if(app.isSystemApp()){
            	Toast.makeText(this, "ϵͳӦ�ò��ܱ�ɾ��", 1).show();
            }else{
            	if(!packname.equals(getPackageName())){
            		String uristr = "package:"+packname;
                	Uri uri = Uri.parse(uristr);
                	Intent deleteIntent = new Intent();
                	deleteIntent.setAction(Intent.ACTION_DELETE);
                	deleteIntent.setData(uri);
                	startActivityForResult(deleteIntent, 0);
                	return;
            	}else{
            		Toast.makeText(this, "����ж���Լ�", 1).show();
            		return;
            	}
            	
            }
			break;
		}
		
	}
	/**
	 * �ж�ĳ��Ӧ�ó����� ����������Ӧ�ó���
	 * @param info 
	 * @return 
	 * ����Ϊ������û�Ӧ�ó���
	 * Ϊ�پ���ϵͳӦ�ó���
	 */
    public boolean filterApp(ApplicationInfo info) {
        if ((info.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
            return true;
        } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
            return true;
        }
        return false;
    }
	/**
	 * ��ȡ���е��û���װ��app
	 * 
	 * @param appinfos
	 * @return
	 */
	private List<AppInfo> getUserApps(List<AppInfo> appinfos) {
		List<AppInfo> userAppinfos = new ArrayList<AppInfo>();
		for (AppInfo appinfo : appinfos) {
			if (!appinfo.isSystemApp()) {
				userAppinfos.add(appinfo);
			}
		}
		return userAppinfos;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		inintUI();
	}
	
}

























