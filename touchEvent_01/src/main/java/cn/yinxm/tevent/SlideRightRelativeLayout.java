package cn.yinxm.tevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import cn.yinxm.lib.utils.LogUtil;

/**
 * Created by yinxm on 2016/9/2.
 */
public class SlideRightRelativeLayout extends FrameLayout {
    private int mTouchSlop;
    public SlideRightRelativeLayout(Context context) {
        this(context, null);
    }

    public SlideRightRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public SlideRightRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }


    float startX, startY;
    //    List<OnSlideRightListener> list = new ArrayList<>();
    OnSlideRightListener listener;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        LogUtil.e("[dispatchTouchEvent] action=" + ev.getAction() + ", x=" + ev.getRawX() + ", y=" + ev.getRawY());
//        boolean flag = super.dispatchTouchEvent(ev);
//        LogUtil.e("[dispatchTouchEvent] flag=" + flag);
//        return flag;
//    }

/*
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("[onInterceptTouchEvent] action=" + ev.getAction() + ", x=" + ev.getRawX() + ", y=" + ev.getRawY());
        boolean flag = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getRawX();
                startY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getRawX();
                float endY = ev.getRawY();
                LogUtil.d("startX=" + startX + ", endX=" + endX + ", startY=" + startY + ", endY=" + endY);
                //判断滑动方向
                if (Math.abs(endX - startX) > Math.abs(endY - startY)) {
                    //x方向水平滑动
                    LogUtil.d("x方向水平滑动");
                    //判断是否是右滑
                    if (endX - startX > 20) {
                        LogUtil.d("水平右滑");

                        if (listener != null) {
                            listener.onSlideRight();
                        }
                        flag = true;
                    }

                } else {
                    //y方向上垂直滑动
                }
                break;

        }

        LogUtil.d("[onInterceptTouchEvent] flag=" + flag + ", action=" + ev.getAction());
//        if (flag) {
//            return flag;
//        } else {
            flag = super.onInterceptTouchEvent(ev);
            LogUtil.d("[onInterceptTouchEvent] flag=" + flag);
            return flag;
//        }
    }

    private boolean isSilding;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("[onTouchEvent]  action=" + event.getAction() + ", x=" + event.getRawX() + ", y=" + event.getRawY());

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                float endX = event.getRawX();
                float endY = event.getRawY();
                LogUtil.d("startX="+startX+", endX="+endX+", startY="+startY+", endY="+endY);
                //判断滑动方向
                if (Math.abs(endX-startX) > Math.abs(endY-startY)) {
                    //x方向水平滑动
                    LogUtil.d("onTouchEvent x方向水平滑动");
                    //判断是否是右滑
                    if (endX-startX > 20) {
                        isSilding = true;
                        LogUtil.d("水平右滑");
                        if (listener != null) {
                            listener.onSlideRight();
                        }
//                            flag = true;
                    }

                } else {
                    //y方向上垂直滑动
                }
                break;
            case MotionEvent.ACTION_UP:

                break;

        }

        return true;
    }
*/

    /**
     * 事件拦截操作
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.e("[SwipeBackLayout.onInterceptTouchEvent] action="+ev.getAction() + ", x="+ev.getRawX()+", y="+ev.getRawY());
        boolean flag = false;
//        //处理ViewPager冲突问题
//        ViewPager mViewPager = getTouchViewPager(mViewPagers, ev);
//        Log.i(TAG, "mViewPager = " + mViewPager);
//
//        if(mViewPager != null && mViewPager.getCurrentItem() != 0){
//            flag = super.onInterceptTouchEvent(ev);
//            LogUtil.d("[SwipeBackLayout.onInterceptTouchEvent] action="+ev.getAction() + "flag="+flag);
//            return flag;
//        }


        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) ev.getRawX();
                startY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) ev.getRawX();
                // 满足此条件屏蔽SildingFinishLayout里面子类的touch事件
                if (moveX - startX > mTouchSlop
                        && Math.abs((int) ev.getRawY() - startY) < mTouchSlop) {
                    flag = true;
                    LogUtil.i("[SwipeBackLayout.onInterceptTouchEvent] action="+ev.getAction() + " move ");
                    return flag;
                }
                break;
        }
        flag = super.onInterceptTouchEvent(ev);

        LogUtil.e("[SwipeBackLayout.onInterceptTouchEvent] action="+ev.getAction() + ", flag="+flag);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.e("[SwipeBackLayout.onTouchEvent] action="+event.getAction() + ", x="+event.getRawX()+", y="+event.getRawY());
    /*    switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getRawX();
                int deltaX = tempX - moveX;
                tempX = moveX;
                if (moveX - downX > mTouchSlop
                        && Math.abs((int) event.getRawY() - downY) < mTouchSlop) {
                    isSilding = true;
                    LogUtil.d("[SwipeBackLayout.onTouchEvent] isSilding");
                }

                if (moveX - downX >= 0 && isSilding) {
                    mContentView.scrollBy(deltaX, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
                if (mContentView.getScrollX() <= -viewWidth / 2) {
                    isFinish = true;
                    scrollRight();
                    LogUtil.e("[SwipeBackLayout.onTouchEvent] isFinish");
                } else {
                    scrollOrigin();
                    isFinish = false;
                    LogUtil.d("[SwipeBackLayout.onTouchEvent] not isFinish");
                }
                break;
        }*/

        return true;
    }


    public void setOnSlideRightListener(OnSlideRightListener listener) {
        if (listener != null) {
//            list.add(listener);
            this.listener = listener;
        }
    }


    public interface OnSlideRightListener {
        public void onSlideRight();
    }
}


