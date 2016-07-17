package cn.sunzn.tevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchEventChilds extends LinearLayout {

	public TouchEventChilds(Context context) {
		super(context);
	}

	public TouchEventChilds(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("sunzn", "TouchEventChilds | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//		return super.dispatchTouchEvent(ev);
		return true;
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("sunzn", "TouchEventChilds | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.onInterceptTouchEvent(ev);
	}

	public boolean onTouchEvent(MotionEvent ev) {
		Log.d("sunzn", "TouchEventChilds | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		return super.onTouchEvent(ev);
	}

}
