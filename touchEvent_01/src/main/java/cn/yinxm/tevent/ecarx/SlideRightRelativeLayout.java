package cn.yinxm.tevent.ecarx;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import cn.yinxm.lib.utils.LogUtil;


/**
 * Created by yinxm on 2016/9/2.
 * 右滑返回上一页自定义View
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

    private boolean isInterceptSlideRightEvent = true;//是否拦截右滑事件


    //是否启用右滑返回上一页
    OnSlideRightListener slideRightListener;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.e("[SlideRightRelativeLayout.dispatchTouchEvent] action=" + event.getAction() + ", x=" + event.getRawX() + ", x1=" + event.getX() + ", y=" + event.getY()
                +", isInterceptSlideRightEvent="+isInterceptSlideRightEvent+", slideRightListener="+slideRightListener);
        boolean flag = super.dispatchTouchEvent(event);
        LogUtil.d("[SlideRightRelativeLayout.dispatchTouchEvent] action=" + event.getAction() + ", flag=" + flag);
//        if (!flag && MotionEvent.ACTION_DOWN == event.getAction()) {//只能拦截down，不能拦截move
//            flag = true;//如果子控件都没有消费事件，由SlideRightRelativeLayout的onTouch来消费事件
//        }
        LogUtil.d("[SlideRightRelativeLayout.dispatchTouchEvent] action=" + event.getAction() + ", flag=" + flag);
        return flag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        LogUtil.d("[SlideRightRelativeLayout.onInterceptTouchEvent] action="+ev.getAction()+",startX="+startX+", x="+ev.getRawX()+", y="+ ev.getY()
//        +", isInterceptSlideRightEvent="+isInterceptSlideRightEvent);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX=  ev.getRawX();
                startY= ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getRawX();
                float endY = ev.getRawY();
                //判断滑动方向
                if (Math.abs(endX-startX) > Math.abs(endY-startY)) {
                    //x方向水平滑动
                    LogUtil.d("x方向水平滑动");
                    //判断是否是右滑
                    if (endX-startX > 0) {
                        LogUtil.d("右滑");
                        if (isInterceptSlideRightEvent) {
                            LogUtil.d("拦截右滑事件");
                            isSlideRight = true;
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("[SlideRightRelativeLayout.onTouchEvent] action=" + event.getAction()+", startX="+startX + ", x=" + event.getRawX() + ", x1=" + event.getRawX() + ", y=" + event.getRawY()
                +", isSlideRight="+isSlideRight+", isInterceptSlideRightEvent="+isInterceptSlideRightEvent);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isSlideRight = false;
                startX = event.getRawX();
                startY = event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                if (!isSlideRight) {
                    float endX = event.getRawX();
                    float endY = event.getRawY();
                    //判断滑动方向
                    if (Math.abs(endX - startX) > Math.abs(endY - startY)) {
                        if (endX - startX > SlideDirectionManager.SLIDE_PX) {
                            LogUtil.d("onTouchEvent 水平右滑");
                            if (isInterceptSlideRightEvent) {
                                isSlideRight = true;
                                return true;
                            }
                        }
                    } else {
                        //y方向上垂直滑动
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (isSlideRight && isInterceptSlideRightEvent) {
                    LogUtil.d("处理右滑结束事件 slideRightListener="+slideRightListener);
                    if (slideRightListener != null) {
                        slideRightListener.onSlideRight();
                    }
                    isSlideRight =false;
                }
                break;
        }

        boolean flag = false;
        if (isInterceptSlideRightEvent && MotionEvent.ACTION_DOWN == event.getAction()) {
            LogUtil.d("[SlideRightRelativeLayout.onTouchEvent] ACTION_DOWN");
            flag = true;
        } else {
            flag = super.onTouchEvent(event);
        }
        return flag;
    }



    public void setOnSlideRightListener(OnSlideRightListener slideRightListener) {
        if (slideRightListener != null) {
            this.slideRightListener = slideRightListener;
        }
    }

    private void removeSlideRightListener() {
        this.slideRightListener = null;
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


