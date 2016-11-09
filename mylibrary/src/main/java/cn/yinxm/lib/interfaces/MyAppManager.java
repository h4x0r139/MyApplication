package cn.yinxm.lib.interfaces;

import android.content.Context;

import cn.yinxm.lib.interfaces.iml.MyAppConfigIml;

/**
 * Created by yinxm on 2016/8/11.
 */
public  class MyAppManager {
    private MyAppManager(){}
    private IMyAppConfig config;
    private Context applicationContext;

    public static MyAppManager getInstance() {
        return MyAppManagerFactory.instance;
    }
    private static class MyAppManagerFactory{
        private static MyAppManager instance = new MyAppManager();
    }

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public  IMyAppConfig getMyAppConfig() {
        if (config == null) {
            config = new MyAppConfigIml();
        }
        return config;
    }

    public  void setMyAppConfig(IMyAppConfig config) {
        this.config = config;
    }
}
