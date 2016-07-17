package cn.itcast.mobilesafe.test;

import cn.itcast.mobilesafe.engine.AppInfoServie;
import android.test.AndroidTestCase;

public class TestGetAppInfo extends AndroidTestCase {
	public void getApps() throws Exception{
		AppInfoServie provider = new AppInfoServie(getContext());
		provider.getAllApps();
	}
}
