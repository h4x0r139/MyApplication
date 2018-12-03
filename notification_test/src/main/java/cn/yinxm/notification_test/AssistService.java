package cn.yinxm.notification_test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import cn.yinxm.lib.utils.LogUtil;

public class AssistService extends Service {
    public AssistService() {
    }

    public class LocalBinder extends Binder {
        public AssistService getService() {
            return AssistService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.i("AssistService.onBind()");
        return new LocalBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("AssistService.onDestroy()");
    }
}
