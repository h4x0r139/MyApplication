package cn.yinxm.tevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import cn.yinxm.lib.LogUtil;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Log.d("yinxm","test Main3");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.e("Main3Activity.dispatchTouchEvent action="+event.getAction()+", x="+event.getRawX()+", x1="+event.getX()+", y="+event.getY());
        boolean flag = super.dispatchTouchEvent(event);
        LogUtil.d("Main3Activity.dispatchTouchEvent action="+event.getAction()+", flag="+flag);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("Main3Activity.onTouchEvent action="+event.getAction()+", x="+event.getRawX()+", x1="+event.getX()+", y="+event.getY());
        boolean flag = super.onTouchEvent(event);
        LogUtil.d("Main3Activity.onTouchEvent action="+event.getAction()+", flag="+flag);
        return flag;
    }
    
}
