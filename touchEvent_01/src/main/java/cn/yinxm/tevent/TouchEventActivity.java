package cn.yinxm.tevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 解决滑动冲突的方法
 * 方法一：外部拦截法
 *	重写父控件onInterceptTouchEvent方法返回true
 *
 * 方法二：内部拦截法
 * 1、重写子控件dispatchTouchEvent方法，调用
 * getParent().requestDisallowInterceptTouchEvent(true);
 * 告诉父控件不要拦截事件（ViewPager嵌套时，默认事件就被外层的给拦截了）方法返回true，
 * 2、并且重写子控件onInterceptTouchEvent返回true
 * 3、重写父控件的onInterceptTouchEvent方法，ActionDown时，不要拦截
 */
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