package com.yinxm.handler;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by yinxm on 2018/3/8.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initActivityLifeMonitor();
    }

    private void initActivityLifeMonitor() {

        ActivityLifecycleCallbacks callback = new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.d("yinxm", "-------onActivityCreated "+activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d("yinxm", "-------onActivityStarted "+activity);

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d("yinxm", "-------onActivityResumed "+activity);

                if (activity instanceof HandlerActivity) {
                    ((HandlerActivity)activity).updateUIInThread();
                }

            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("yinxm", "-------onActivityPaused "+activity);

            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d("yinxm", "-------onActivityStopped "+activity);

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d("yinxm", "-------onActivitySaveInstanceState "+activity);

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d("yinxm", "-------onActivityDestroyed "+activity);

            }
        };

        registerActivityLifecycleCallbacks(callback);

    }
}
