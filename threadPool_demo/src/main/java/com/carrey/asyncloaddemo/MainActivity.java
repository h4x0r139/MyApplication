package com.carrey.asyncloaddemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.carrey.customview.customview.CustomView;
/**
 * 异步加载的两种方式:AsyncTask与ThreadPool
 * @author carrey
 *
 */
public class MainActivity extends Activity implements ThreadPoolTaskBitmap.CallBack {

	/** 服务器地址 */
	public static String webServerStr;
	
	private static final String TAG = "MainActivity";

	private LayoutInflater inflater;
	
	private Button btnAsync;
	
	private Button btnPool;
	
	private GridView gridView;
	private GridAdapter adapter;
	
	/** 加载方式 */
	private int loadWay;
	private static final int LOAD_ASYNC = 1;
	private static final int LOAD_POOL = 2;
	
	private ThreadPoolManager poolManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		loadWay = LOAD_ASYNC;
		webServerStr = getResources().getString(R.string.web_server);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		btnAsync = (Button) findViewById(R.id.btn_async);
		btnAsync.setOnClickListener(new AsyncButtonClick());
		btnPool = (Button) findViewById(R.id.btn_pool);
		btnPool.setOnClickListener(new PoolButtonClick());
		gridView = (GridView) findViewById(R.id.gridview);
		
		adapter = new GridAdapter();
		gridView.setAdapter(adapter);
		
		poolManager = new ThreadPoolManager(ThreadPoolManager.TYPE_FIFO, 5);
	}

	private class AsyncButtonClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			loadWay = LOAD_ASYNC;
			adapter.notifyDataSetChanged();
		}
		
	}
	
	private class PoolButtonClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			loadWay = LOAD_POOL;
			adapter.notifyDataSetChanged();
		}
		
	}
	
	private class GridAdapter extends BaseAdapter {

		private Bitmap mBackgroundBitmap;
		
		public GridAdapter() {
			mBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.item_bg);
		}
		
		@Override
		public int getCount() {
			return 999;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = inflater.inflate(R.layout.item, null);
				holder.customView = (CustomView) convertView.findViewById(R.id.customview);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.customView.position = position;
			holder.customView.setImageBitmap(null);
			holder.customView.setBackgroundBitmap(mBackgroundBitmap);
			
			if (loadWay == LOAD_ASYNC) {
				holder.customView.setTitleText("AsyncTask");
				holder.customView.setSubTitleText("position: " + position);
				new AsyncLoadTask(holder.customView).execute(position);
			} else if (loadWay == LOAD_POOL) {
				holder.customView.setTitleText("ThreadPool");
				holder.customView.setSubTitleText("position: " + position);
				poolManager.start();
				String imageUrl = ImageHelper.getImageUrl(webServerStr, position);
				poolManager.addAsyncTask(new ThreadPoolTaskBitmap(imageUrl, MainActivity.this, position, holder.customView));
			}
			
			return convertView;
		}
		
	}
	
	static class ViewHolder {
		CustomView customView;
	}
	
	@Override
	protected void onDestroy() {
		poolManager.stop();
		super.onDestroy();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onReady(String url, Bitmap bitmap, int position, CustomView view) {
		Log.i(TAG, "thread pool done task: " + url);
		if (view.position == position) {
			view.setImageBitmap(bitmap);
		}
	}

}
