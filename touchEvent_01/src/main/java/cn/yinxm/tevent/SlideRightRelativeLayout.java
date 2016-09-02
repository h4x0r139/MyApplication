package cn.yinxm.tevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.LogUtil;

/**
 * Created by yinxm on 2016/9/2.
 */
public class SlideRightRelativeLayout extends RelativeLayout {
    public SlideRightRelativeLayout(Context context) {
        super(context);
    }

    public SlideRightRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideRightRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    float startX, startY;
    List<OnSlideRightListener> list = new ArrayList<>();

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("[onInterceptTouchEvent] action="+ev.getAction());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX=  ev.getX();
                startY= ev.getY();
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                LogUtil.d("startX="+startX+", endX="+endX+", startY="+startY+", endY="+endY);
                //判断滑动方向
                if (Math.abs(endX-startX) > Math.abs(endY-startY)) {
                    //x方向水平滑动
                    LogUtil.d("x方向水平滑动");
                    //判断是否是右滑
                    if (endX-startX > 20) {
                        LogUtil.d("水平右滑");
                        if (list.size() > 0) {
                            for (OnSlideRightListener listener : list) {
                                if (listener != null) {
                                    listener.onSlideRight();
                                }
                            }
                            return true;
                        }
                    }

                } else {
                    //y方向上垂直滑动
                }
                break;

        }

        return super.onInterceptTouchEvent(ev);
    }


    public void setOnSlideRightListener(OnSlideRightListener listener) {
        if (listener != null) {
            list.add(listener);
        }
    }


    public interface OnSlideRightListener {
        public void onSlideRight();
    }
}


