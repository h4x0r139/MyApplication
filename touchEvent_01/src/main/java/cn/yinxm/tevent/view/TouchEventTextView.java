package cn.yinxm.tevent.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import cn.yinxm.tevent.TouchEventUtil;

/**
 * 功能：
 * Created by yinxm on 2018/1/12.
 */

public class TouchEventTextView extends TextView {
    public TouchEventTextView(Context context) {
        super(context);
    }

    public TouchEventTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("yinxm", "View | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));

        return super.dispatchTouchEvent(ev);//传递给onTouchEvent

//		return false;//down事件不再传递，回传递给父类onTouchEvent，move和up事件不会再到这里面来，都到父类onTouchEvent
//		return true;//down事件在此消费，就此结束，move和up事件会来到这里
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("yinxm", "View | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onTouchEvent(ev);//不消费事件，回传给父类onTouchEvent
//		return false;//不消费事件，回传给父类onTouchEvent
//		return true;//消费事件， down被消费掉后、move和up事件经过dispatch后会直接到onTouchEvent里面消费
    }

}