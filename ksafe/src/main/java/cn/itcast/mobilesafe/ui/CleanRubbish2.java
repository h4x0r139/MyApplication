package cn.itcast.mobilesafe.ui;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.itcast.mobilesafe.R;
import cn.itcast.mobilesafe.domain.CacheInfo;
import cn.itcast.mobilesafe.uitl.TextFormater;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 * �����������
 * @author Administrator
 *
 */
public class CleanRubbish2 extends Activity {
//	private TextView tv_count_size = null;
//	private TextView tv_name = null;
//	private ImageView iv_clean_icon = null;
	private ListView lv_clean_manager = null;
	private PackageManager pm = null;
	private List<PackageInfo> packageinfos = null;
	private HashMap<String, CacheInfo> maps = null;
	private MyAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clean_cache_listview);

		maps = new HashMap<String, CacheInfo>();
		getInsatllAppManager();
		lv_clean_manager = (ListView) findViewById(R.id.lv_clean_manager);
		adapter = new MyAdapter();
		lv_clean_manager.setAdapter(adapter);
		//����Ӧ�ó�����Ϣ��ɾ����������
		lv_clean_manager.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// �����ĸ�Ӧ�ó��򣬱����Ӧ�ó���İ�������ȥ
				Intent intent  = new Intent();
				intent.setAction("android.intent.action.VIEW");
				intent.addCategory("android.intent.category.DEFAULT");
				intent.addCategory("android.intent.category.VOICE_LAUNCH");
				CacheInfo info = (CacheInfo) lv_clean_manager.getItemAtPosition(position);
				intent.putExtra("pkg", info.getPackname());
				startActivity(intent);
			}
			
		});
	}
	
    /**
     * ��ȡ���а�װ�õ�Ӧ�ó���
     */
	public void getInsatllAppManager(){
		pm = getPackageManager();
		// 1.��ȡ���еİ�װ�õ�Ӧ�ó���
		
		packageinfos = pm.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		for(PackageInfo info : packageinfos){
			//���Ӧ�ó��������
			String name = info.applicationInfo.loadLabel(pm).toString();
			Drawable icon = info.applicationInfo.loadIcon(pm);
			String packname = info.packageName;
			CacheInfo cacheinfos = new CacheInfo();
			cacheinfos.setDrawable(icon);
			cacheinfos.setPackname(packname);
			cacheinfos.setName(name);
			maps.put(packname, cacheinfos);
			getAppSize(packname);
		}
	}
	/**
	 * ���ݰ�����ȡӦ�ó���������Ϣ ע��: ���������һ���첽�ķ��� ��������Ҫ��һ��ʱ����ܻ�ȡ��.
	 * 
	 * @param packname
	 */
	private void getAppSize(final String packname) {
		try {
			Method method = PackageManager.class.getMethod(
					"getPackageSizeInfo", new Class[] { String.class,IPackageStatsObserver.class });

			method.invoke(pm, new Object[] { packname,new IPackageStatsObserver.Stub() {

						public void onGetStatsCompleted(PackageStats pStats,
								boolean succeeded) throws RemoteException {
							// ע�����������һ���첽�Ĳ���
							long cachesize = pStats.cacheSize;
							long codesize = pStats.codeSize;
							long datasize = pStats.dataSize;

							CacheInfo info = maps.get(packname);
							info.setCache_size(TextFormater.getDataSize(cachesize));
							info.setData_size(TextFormater.getDataSize(datasize));
							info.setCode_size(TextFormater.getDataSize(codesize));
							maps.put(packname, info);
							
							
							
						}
					} });

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class MyAdapter extends BaseAdapter{
        private Set<Entry<String,CacheInfo>> sets;
        private List<CacheInfo> cacheinfos;
        //��mapת��list
        public MyAdapter() {
        	sets = maps.entrySet();
        	cacheinfos = new ArrayList<CacheInfo>();
			for(Entry<String,CacheInfo> entry :sets){
				cacheinfos.add(entry.getValue());
			}
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return cacheinfos.size();
		}

		

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return cacheinfos.get(position);
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if(convertView==null){
				view = View.inflate(CleanRubbish2.this, R.layout.clean_cache, null);
			}else{
				view = convertView;
			}
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			TextView tv_count_size = (TextView) view.findViewById(R.id.tv_count_size);
			ImageView iv_clean_icon = (ImageView) view.findViewById(R.id.iv_clean_icon);
			
			iv_clean_icon.setImageDrawable(cacheinfos.get(position).getDrawable());
			tv_name.setText(cacheinfos.get(position).getName());
			tv_count_size.setText(cacheinfos.get(position).getCode_size());
			
			return view;
		}
		
	}
}


















