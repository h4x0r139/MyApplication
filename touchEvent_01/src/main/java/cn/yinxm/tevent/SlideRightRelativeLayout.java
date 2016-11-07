package cn.yinxm.tevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import cn.yinxm.lib.utils.LogUtil;


/**
 * Created by yinxm on 2016/9/2.
 * 外部拦截法
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
    boolean isSlideRight = false;
    OnSlideRightListener slideRightListener;

    private boolean isInterceptSlideRightEvent = true;//是否拦截右滑事件


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.e("[SlideRightRelativeLayout.dispatchTouchEvent] action=" + event.getAction() + ", x=" + event.getRawX() + ", x1=" + event.getX() + ", y=" + event.getY());
        boolean flag = super.dispatchTouchEvent(event);
        LogUtil.d("[SlideRightRelativeLayout.dispatchTouchEvent] action=" + event.getAction() + ", flag=" + flag);
        if (!flag) {
            flag = true;//如果子控件都没有消费事件，由SlideRightRelativeLayout的onTouch来消费事件
        }
        LogUtil.d("[SlideRightRelativeLayout.dispatchTouchEvent] action=" + event.getAction() + ", flag=" + flag);
        return flag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("[SlideRightRelativeLayout.onInterceptTouchEvent] action=" + ev.getAction());
        boolean flag = false;
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
                    LogUtil.d("onInterceptTouchEvent x方向水平滑动");
                    //只会出现2此move，如果都没有消费，则不会走
                    if (isInterceptSlideRightEvent) {
                        LogUtil.d("水平右滑，2次拦截");//父控件确定要拦截右滑事件，转给onTouch方法消费
                        flag = true;
                    }
                    //判断是否是右滑
//                    if (endX-startX > 20) {
//                    if (endX-startX > SlideDirectionManager.SLIDE_PX) {
//                        LogUtil.d("水平右滑，判断是否拦截事件");
//                        if (isInterceptSlideRightEvent) {
//                            LogUtil.d("水平右滑，被父控件拦截");//父控件确定要拦截右滑事件，转给onTouch方法消费
//                            flag = true;
//                        }
//                    }

                } else {
                    //y方向上垂直滑动
                }
                break;

        }

        LogUtil.d("[SlideRightRelativeLayout.onInterceptTouchEvent] action=" + ev.getAction() + ", flag=" + flag);
        if (flag) {
            return true;
        } else  {
            flag = super.onInterceptTouchEvent(ev);
            LogUtil.d("[SlideRightRelativeLayout.onInterceptTouchEvent] action=" + ev.getAction() + ", flag=" + flag);
            return flag;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("[SlideRightRelativeLayout.onTouchEvent] action=" + event.getAction() + ", x=" + event.getRawX() + ", x1=" + event.getX() + ", y=" + event.getY());
        boolean flag = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSlideRight = false;
                startX = event.getX();
                startY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                LogUtil.d("startX=" + startX + ", endX=" + endX + ", startY=" + startY + ", endY=" + endY);
                //判断滑动方向
                if (Math.abs(endX - startX) > Math.abs(endY - startY)) {
                    //x方向水平滑动
                    LogUtil.d("onTouchEvent x方向水平滑动");
                    //判断是否是右滑
                    if (endX - startX > 20) {
                        LogUtil.d("onTouchEvent 水平右滑");
                        isSlideRight = true;
                        flag = true;//拦截右滑事件
                    }

                } else {
                    //y方向上垂直滑动
                }
                break;

            case MotionEvent.ACTION_UP:
                if (isSlideRight) {
                    LogUtil.d("处理右滑结束事件");
                    if (slideRightListener != null) {
                        slideRightListener.onSlideRight();
                    }
                    isSlideRight =false;
                }
                break;
        }
        LogUtil.d("[SlideRightRelativeLayout.onTouchEvent] action=" + event.getAction() + ", flag=" + flag);
        if (flag) {
            return flag;
        } else {
            flag = super.onTouchEvent(event);
            LogUtil.d("[SlideRightRelativeLayout.onTouchEvent] action=" + event.getAction() + ", flag=" + flag);
            return flag;
        }
    }

    public void setOnSlideRightListener(OnSlideRightListener onSlideRightListener) {
        this.slideRightListener = onSlideRightListener;
    }

    public boolean isInterceptSlideRightEvent() {
        return isInterceptSlideRightEvent;
    }

    public void setInterceptSlideRightEvent(boolean interceptSlideRightEvent) {
        isInterceptSlideRightEvent = interceptSlideRightEvent;
    }

    public interface OnSlideRightListener {
        public void onSlideRight();
    }
}


