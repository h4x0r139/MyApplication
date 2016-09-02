package cn.yinxm.tevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventFather extends LinearLayout {

	public TouchEventFather(Context context) {
		super(context);
	}

	public TouchEventFather(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("sunzn", "TouchEventFather | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.dispatchTouchEvent(ev);
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("sunzn", "TouchEventFather | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//		return super.onInterceptTouchEvent(ev);
		return false;
	}

	public boolean onTouchEvent(MotionEvent ev) {
		Log.d("sunzn", "TouchEventFather | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.onTouchEvent(ev);
	}

}
