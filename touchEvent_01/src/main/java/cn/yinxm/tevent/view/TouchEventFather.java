package cn.yinxm.tevent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import cn.yinxm.tevent.TouchEventUtil;

public class TouchEventFather extends LinearLayout {

	public TouchEventFather(Context context) {
		super(context);
	}

	public TouchEventFather(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		Log.e("yinxm", "TouchEventFather | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//		boolean flag = super.dispatchTouchEvent(ev);
//		Log.e("yinxm", "TouchEventFather | dispatchTouchEvent --> flag=" + flag);
		return super.dispatchTouchEvent(ev);//传递给onInterceptTouchEvent

//		return false;//down事件不再向下传递，回传递给父类onTouchEvent，move和up事件不会再到这里面来，都到父类onTouchEvent
//		return true;//down事件在此消费，就此结束，move和up事件会来到这里
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		Log.i("yinxm", "TouchEventFather | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//		boolean flag =  super.onInterceptTouchEvent(ev);
//		Log.i("yinxm", "TouchEventFather | onInterceptTouchEvent --> flag=" + flag);
//		return flag;
		return super.onInterceptTouchEvent(ev);
//		return false;//不拦截事件——》子控件dispatchTouchEvent
//		return true;//拦截事件——》onTouchEvent， down会到onTouchEvent，如果事件没有消费，会回到父类的onTouchEvent，move和up事件会回到down最终消费的那一级
	}

	public boolean onTouchEvent(MotionEvent ev) {
		Log.d("yinxm", "TouchEventFather | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
//		boolean flag = super.onTouchEvent(ev);
//		Log.d("yinxm", "TouchEventFather | onTouchEvent --> flag=" + flag);
//		return flag;
		return super.onTouchEvent(ev);//不消费事件，回传给父类onTouchEvent
//		return false;//不消费事件，回传给父类onTouchEvent
//		return true;//消费事件， down被消费掉后、move和up事件经过dispatch后会直接到onTouchEvent里面消费
	}

}
