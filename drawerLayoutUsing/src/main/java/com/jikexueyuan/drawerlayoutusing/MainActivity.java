package com.jikexueyuan.drawerlayoutusing;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnItemClickListener {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ArrayList<String> menuLists;
	private ArrayAdapter<String> adapter;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = (String) getTitle();

		Log.d("my", "mactionBar=" + getActionBar());


		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		menuLists = new ArrayList<String>();
		for (int i = 0; i < 5; i++)
			menuLists.add("极客学院0" + i);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, menuLists);
		mDrawerList.setAdapter(adapter);
		mDrawerList.setOnItemClickListener(this);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getActionBar().setTitle("请选择");
				invalidateOptionsMenu(); // Call onPrepareOptionsMenu()
			}

			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		//开启ActionBar上APP ICON的功能
		getActionBar().setDisplayHomeAsUpEnabled(true);//启用ActionBar左上角返回图标
		getActionBar().setHomeButtonEnabled(true);//是左上角homeButton可用

	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Log.d("my", "[MainActivity.onPrepareOptionsMenu]");
		boolean isDrawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!isDrawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.d("my", "[MainActivity.onCreateOptionsMenu]");
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.d("my", "[MainActivity.onOptionsItemSelected]");
		//将ActionBar上的图标与Drawer结合起来
		if (mDrawerToggle.onOptionsItemSelected(item)){
			Log.d("my", "[MainActivity.onOptionsItemSelected], mDrawerToggle=true");
			return true;
		}
		switch (item.getItemId()) {
		case R.id.action_websearch:
			Log.d("my", "[MainActivity.onOptionsItemSelected]，action_websearch");
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri uri = Uri.parse("http://www.jikexueyuan.com");
			intent.setData(uri);
			startActivity(intent);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		Log.d("my", "[MainActivity.onPostCreate]");
		super.onPostCreate(savedInstanceState);
		//需要将ActionDrawerToggle与DrawerLayout的状态同步
		//将ActionBarDrawerToggle中的drawer图标，设置为ActionBar中的Home-Button的Icon
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		Log.d("my", "[MainActivity.onConfigurationChanged]");
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Log.d("my", "[MainActivity.onItemClick], position="+position);
		// 动态插入一个Fragment到FrameLayout当中
		Fragment contentFragment = new ContentFragment();
		Bundle args = new Bundle();
		args.putString("text", menuLists.get(position));
		contentFragment.setArguments(args);

		FragmentManager fm = getFragmentManager();
		fm.beginTransaction().replace(R.id.content_frame, contentFragment)
				.commit();

		mDrawerLayout.closeDrawer(mDrawerList);
	}

}
