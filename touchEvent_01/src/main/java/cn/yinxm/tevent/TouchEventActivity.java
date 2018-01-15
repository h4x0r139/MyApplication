package cn.yinxm.tevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class TouchEventActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.w("yinxm", "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
//		return false;//事件在此消费，就此结束
//		return true;//事件在此消费，就此结束
	}

	public boolean onTouchEvent(MotionEvent event) {
		Log.w("yinxm", "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
		return super.onTouchEvent(event);
//		return false;//只用于标识事件是否消费，对事件流向基本无影响？
//		return true;//只用于标识事件是否消费，对事件流向基本无影响？
	}

}