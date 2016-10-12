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
		Log.e("yinxm", "TouchEventFather | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		boolean flag = super.dispatchTouchEvent(ev);
		Log.e("yinxm", "TouchEventFather | dispatchTouchEvent --> flag=" + flag);
		return flag;
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("yinxm", "TouchEventFather | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		boolean flag =  super.onInterceptTouchEvent(ev);
		Log.i("yinxm", "TouchEventFather | onInterceptTouchEvent --> flag=" + flag);
//		return flag;
		return false;//父控件默认不拦截事件

	}

	public boolean onTouchEvent(MotionEvent ev) {
		Log.d("yinxm", "TouchEventFather | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		boolean flag = super.onTouchEvent(ev);
		Log.d("yinxm", "TouchEventFather | onTouchEvent --> flag=" + flag);
		return flag;
	}

}
