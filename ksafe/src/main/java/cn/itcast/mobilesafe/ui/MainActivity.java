package cn.itcast.mobilesafe.ui;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;


import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.R.anim;
import cn.itcast.mobilesafe.adapter.GridViewAdapter;
import cn.itcast.mobilesafe.adapter.MainAdapterItem;
import cn.itcast.mobilesafe.domain.MainItem;
import cn.itcast.mobilesafe.engine.Rotate3dAnimation;
import cn.itcast.mobilesafe.uitl.MD5Encoder;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {
	protected static final int STOP = 100;
	private ImageView iv_main = null;
    private GridView content = null;
    private GridViewAdapter adapter = null;
    private SharedPreferences sp = null;
    private ListView lv_main_item = null;
    private LinearLayout ll = null;
    private SlidingDrawer sd = null;
    private Button bt_main = null;
	private ViewGroup mContainer;
	private ImageView imageView1;
	private LinearLayout imageView2;
	private RelativeLayout rv = null;
	private SQLiteDatabase db;
	private ImageView iv_cache = null;
	private ScrollView sv;
	private PackageManager pm = null;
	private Animation am=null;
	LinearInterpolator lin;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 100:
				 if(msg.what==STOP){
//					 imageView2.removeAllViews();
//				     am. setRepeatCount ( -1 ); 
//				     am.setRepeatCount(Animation.INFINITE); 
//				     am.setInterpolator(lin); 
					 am.setRepeatCount(0);
					 bt_main.setClickable(true);
					 }
				break;

			default:
				break;
			}
			String str = (String) msg.obj;
			TextView tv = new TextView(getApplicationContext());
			tv.setTextSize(20);
			tv.setText(str);
			imageView2.setOrientation(LinearLayout.VERTICAL);
			imageView2.addView(tv);
			sv.scrollBy(0, 30);
		}
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		ll = (LinearLayout) findViewById(R.id.ll);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		content = (GridView) findViewById(R.id.content);
		iv_main = (ImageView) findViewById(R.id.iv_main);
		iv_cache = (ImageView) findViewById(R.id.iv_cache);
		//iv_main.setVisibility(View.INVISIBLE);
		lv_main_item = (ListView) findViewById(R.id.lv_main_item);
		sd = (SlidingDrawer) findViewById(R.id.sd);
		bt_main = (Button) findViewById(R.id.bt_main);
		rv = (RelativeLayout) findViewById(R.id.rv);
		mContainer = (ViewGroup)findViewById(R.id.container);
	  //  imageView1 = (ImageView)findViewById(R.id.imageview1);
	    imageView2 = (LinearLayout)findViewById(R.id.imageview2);
	    pm = this.getPackageManager();
	    sv = (ScrollView) findViewById(R.id.sv);
//	    bt_main.setOnClickListener(this);
//	    imageView2.setOnClickListener(this);
	   
	        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
		sd.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			
			public void onDrawerOpened() {
				ll.setVisibility(View.GONE);
				lv_main_item.setVisibility(View.GONE);
				rv.setVisibility(View.GONE);
				mContainer.setVisibility(View.GONE);
			}
		});
		sd.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			
			public void onDrawerClosed() {
				ll.setVisibility(View.VISIBLE);
				lv_main_item.setVisibility(View.VISIBLE);
				rv.setVisibility(View.VISIBLE);
				mContainer.setVisibility(View.VISIBLE);
			}
		});
		
		MainAdapterItem myadapter = new MainAdapterItem(getItem(),this);
		lv_main_item.setAdapter(myadapter);
		/**
		 * ���listView��Ŀ���õ��¼�
		 */
		lv_main_item.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int itemid = (int) lv_main_item.getItemIdAtPosition(position);
				//0���ߵ�˧��
				if(itemid==0){
					Intent intent = new Intent(MainActivity.this,PictureActivity.class);
					startActivity(intent);
				}
				//1��ʱ�����ɱ��
				if(itemid==1){
					Intent intent = new Intent(MainActivity.this,Blacknumber.class);
					startActivity(intent);
				}
			}
			
		});
		adapter = new GridViewAdapter(this);
		content.setAdapter(adapter);
		content.setOnItemClickListener(this);
		
		
		content.setOnItemLongClickListener(new OnItemLongClickListener(){

			public boolean onItemLongClick(AdapterView<?> parent, final View view,
					int position, long id) {
				if(position==0){
					Builder builder = new Builder(MainActivity.this);
					builder.setTitle("����");
					builder.setMessage("������Ҫ���ĵ�����");
					final EditText ed = new EditText(MainActivity.this);
					ed.setHint("�������������");
					builder.setView(ed);
					builder.setPositiveButton("ȷ��", new OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							String newName = ed.getText().toString().trim();
							if("".equals(newName)){
								Toast.makeText(MainActivity.this, "���벻��Ϊ��", 1).show();
								return;
							}
							Editor editor = sp.edit();
							editor.putString("lost_name", newName);
							editor.commit();
							TextView name = (TextView) view.findViewById(R.id.iv_main_item);
							name.setText(newName);
						}
					});
					builder.setNegativeButton("ȡ��", new OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							
							
						}
					});
					builder.create().show();
				}
				return false;
			}
			
		});
	}
	private List<MainItem> getItem(){
		List<MainItem> listItem = new ArrayList<MainItem>();
		MainItem mainItem1 = new MainItem("���߼��","�ڸ�����˧�գ��򵥽���",getResources().getDrawable(R.drawable.antivrus_icon_pressed));
		listItem.add(mainItem1);
		MainItem mainItem2 = new MainItem("ɧ������","ȫ�������������ź�ɧ�ŵ绰",getResources().getDrawable(R.drawable.block_icon_pressed));
		listItem.add(mainItem2);
		MainItem mainItem3 = new MainItem("�������","�����������.����������ʧ",getResources().getDrawable(R.drawable.traffic_icon_pressed));
		listItem.add(mainItem3);
		MainItem mainItem4 = new MainItem("�����Ż�","ȫ���Ż�ϵͳ���.�������",getResources().getDrawable(R.drawable.progress_icon_pressed));
		listItem.add(mainItem4);
		return listItem;
	}
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0://��ʾ�ֻ���ʿ
			System.out.println("��ʾ�ֻ���ʿ");
			Intent intent = new Intent(this,LostProtectedActivity.class);
			startActivity(intent);
			break;
		case 1://��ʾ�ֻ���ʿ
			System.out.println("��ʾ�ֻ���ʿ");
			Intent AppTaskProgressActivityintent = new Intent(this,AppTaskProgressActivity.class);
			startActivity(AppTaskProgressActivityintent);
			
			break;
		case 2://��ʾ�ֻ���ʿ
			System.out.println("��ʾ�ֻ���ʿ");
			Intent CleanRubbish2intent = new Intent(this,CleanRubbish.class);
			startActivity(CleanRubbish2intent);
			break;
		case 4://��ʾ�ֻ���ʿ
			System.out.println("��ȫ����");
			Intent SafetyBackupsintent = new Intent(this,SafetyBackups.class);
			startActivity(SafetyBackupsintent);
			break;
		case 3://��ʾ�ֻ���ʿ
			System.out.println("�������");
			Intent AppInfoActivityintent = new Intent(this,AppInfoActivity.class);
			startActivity(AppInfoActivityintent);
			break;
		case 5://��ʾ�ֻ���ʿ
			System.out.println("������");
			Intent AppLockintent = new Intent(this,AppLock.class);
			startActivity(AppLockintent);
			break;
		case 6://��ʾ�ֻ���ʿ
			System.out.println("������");
			Intent T4_DemoActivityintent = new Intent(this,BatteryState.class);
			startActivity(T4_DemoActivityintent);
			break;
		case 7://��ʾͨ�Ź���
			System.out.println("��ʾͨ�Ź���");
			Intent CommunicationActivityintent = new Intent(this,CommunicationActivity.class);
			startActivity(CommunicationActivityintent);
			break;
		default:
			break;
		}
		
	}
	/**
	 * һ������
	 */
	public void kill_cache(View v){
		
		
		iv_main.setImageDrawable(getResources().getDrawable(R.drawable.main_circle_bg_scan));
		//����Ϊ���� 
		  lin = new LinearInterpolator(); 
		 am = new RotateAnimation ( 0, +360, 
		 Animation.RELATIVE_TO_SELF, 0f, 
		 Animation.RELATIVE_TO_SELF, 0f ); 

		     // ������ʼ��������ִ��ʱ��(1000 = 1 ��) 
//		     am. setDuration ( 1000 ); 

		     // �����ظ�����(-1 ��ʾһֱ�ظ�) 
		     am. setRepeatCount ( -1 ); 
		     am.setRepeatCount(Animation.INFINITE); 
		     am.setInterpolator(lin); 
		     // ͼƬ���ö��� 
//		     progressImage. setAnimation (am); 
//		     am. startNow ();
		     findViewById(R.id.iv_cache).startAnimation(am);
		     String name = bt_main.getText().toString();
				if("һ������".equals(name)){
					applyRotation(0,90,R.id.bt_main);
					 am. setDuration ( 1000 ); 
					bt_main.setText("����");
					showKill();
					bt_main.setClickable(false);
					return;
				}else{
					applyRotation(0,90,R.id.imageview2);
					bt_main.setText("һ������");
					 am.setRepeatCount(0);
					iv_cache.setVisibility(View.GONE);
					iv_main.setImageResource(R.drawable.main_status_baohu);
					return;
				}
	}
	//��ɱ����
	private void showKill() {
		db = SQLiteDatabase.openDatabase("/sdcard/antivirus.db", null, SQLiteDatabase.OPEN_READONLY);
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				List<PackageInfo> infos = getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES
						| PackageManager.GET_SIGNATURES);
				int virustotal = 0;
				for(PackageInfo info :infos){
					try {
						sleep(500);
						Message msg = Message.obtain();
						
						//��ȡ���������ݰ������Ӧ�ó��������
						String packname = info.packageName;
						ApplicationInfo appinfo = pm.getPackageInfo(packname, 0).applicationInfo;
						String appname = appinfo.loadLabel(pm).toString();
						System.out.println(appname);
						msg.obj = "����ɨ��:"+appname;
						handler.sendMessage(msg);
						//���ǩ��
						Signature [] signs = info.signatures;
						String str = signs[0].toCharsString();
						String md5 = MD5Encoder.encode(str);
						System.out.println(md5);
						Cursor cursor = db.rawQuery("select desc from datable where md5=?",new String[] { md5 });
						if(cursor.moveToNext()){
							String desc = cursor.getString(0);
							msg = Message.obtain();
							msg.obj = info.packageName+":"+desc;
							handler.sendMessage(msg);
							virustotal++;
						}
						cursor.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				
				}
				Message msg = Message.obtain();
				msg.what = STOP;
				msg.obj = "ɨ����� ,������" + virustotal + "������";
				handler.sendMessage(msg);
			}
			
		}.start();
	}
	/**
	 * ��תlistview�ķ���
	 * @param start
	 * @param end
	 * @param viewId
	 */
	private void applyRotation(float start, float end, final int viewId){
		final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f;
        Rotate3dAnimation rotation =
            new Rotate3dAnimation(start, end, centerX, centerY, 200.0f, true);
        rotation.setDuration(500);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new AnimationListener() {
			public void onAnimationEnd(Animation arg0) {

				mContainer.post(new Runnable() {
					public void run() {
						if(viewId == R.id.bt_main){
							lv_main_item.setVisibility(View.GONE);
							imageView2.setVisibility(View.VISIBLE);
						}else if (viewId == R.id.imageview2) {
							imageView2.setVisibility(View.GONE);
							lv_main_item.setVisibility(View.VISIBLE);
						}
						Rotate3dAnimation rotatiomAnimation = new Rotate3dAnimation(-90, 0, centerX, centerY, 200.0f, false);
						rotatiomAnimation.setDuration(500);
						rotatiomAnimation.setInterpolator(new DecelerateInterpolator());
						
						mContainer.startAnimation(rotatiomAnimation);
					}
				});
			
			}
			public void onAnimationRepeat(Animation arg0) {
			}

			public void onAnimationStart(Animation arg0) {
			}
        });
        mContainer.startAnimation(rotation);
	}

}
	




















