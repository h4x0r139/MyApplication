package cn.yinxm.tevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import cn.yinxm.tevent.TouchEventUtil;

public class TouchEventChilds extends LinearLayout {

	public TouchEventChilds(Context context) {
		super(context);
	}

	public TouchEventChilds(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("yinxm", "TouchEventChilds | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));

// 		boolean flag = super.dispatchTouchEvent(ev);
//		Log.d("yinxm", "TouchEventChilds | dispatchTouchEvent --> flag=" + flag);
//		flag = true;
//		Log.d("yinxm", "TouchEventChilds | dispatchTouchEvent --> flag=" + flag);
//		return flag;
//		return flag;//默认消费了事件
		return super.dispatchTouchEvent(ev);//默认事件传递
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("yinxm", "TouchEventChilds | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		boolean flag =  super.onInterceptTouchEvent(ev);
		Log.i("yinxm", "TouchEventChilds | onInterceptTouchEvent --> flag=" + flag);
		return flag;
	}

	public boolean onTouchEvent(MotionEvent ev) {
		Log.d("yinxm", "TouchEventChilds | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
		boolean flag =  super.onTouchEvent(ev);
		Log.d("yinxm", "TouchEventChilds | onTouchEvent --> flag=" + flag);
		return flag;
	}

}
