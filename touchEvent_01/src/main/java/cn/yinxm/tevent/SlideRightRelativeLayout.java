package cn.yinxm.tevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import cn.yinxm.lib.LogUtil;

/**
 * Created by yinxm on 2016/9/2.
 */
public class SlideRightRelativeLayout extends FrameLayout {
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
//    List<OnSlideRightListener> list = new ArrayList<>();
    OnSlideRightListener listener;

/*    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.e("[dispatchTouchEvent] action="+ev.getAction() + ", x="+ev.getX()+", y="+ev.getY());
        boolean flag = super.dispatchTouchEvent(ev);
        LogUtil.e("[dispatchTouchEvent] flag="+flag);
//        if (flag == false) {
//            LogUtil.d("子控件没有消费事件，由最外层拦截右滑事件");
//            flag = true;
//            super.dispatchTouchEvent(ev);
//        }
        return flag;
    }*/

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.d("[onInterceptTouchEvent] action="+ev.getAction() + ", x="+ev.getX()+", y="+ev.getY());
        boolean flag = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX=  ev.getX();
                startY= ev.getY();
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
//                float endX = ev.getX();
//                float endY = ev.getY();
//                LogUtil.d("startX="+startX+", endX="+endX+", startY="+startY+", endY="+endY);
//                //判断滑动方向
//                if (Math.abs(endX-startX) > Math.abs(endY-startY)) {
//                    //x方向水平滑动
//                    LogUtil.d("x方向水平滑动");
//                    //判断是否是右滑
//                    if (endX-startX > 20) {
//                        LogUtil.d("水平右滑");
//                        if (list.size() > 0) {
//                            for (OnSlideRightListener listener : list) {
//                                if (listener != null) {
//                                    listener.onSlideRight();
//                                }
//                            }
//                            flag = true;
//                        }
//                    }
//
//                } else {
//                    //y方向上垂直滑动
//                }
//                break;

        }

        LogUtil.d("[onInterceptTouchEvent] flag="+flag +", action="+ev.getAction());
        if (flag) {
            return flag;
        } else {
            flag = super.onInterceptTouchEvent(ev);
            LogUtil.d("[onInterceptTouchEvent] flag="+flag);
//            if (flag == false) {
//                LogUtil.d("子控件没有消费事件，由最外层拦截右滑事件");
//                flag = true;
//            }
            return flag;
        }
    }
    private boolean isSilding;

   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("[onTouchEvent]  action=" + event.getAction() + ", x=" + event.getX() + ", y=" + event.getY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                LogUtil.d("startX="+startX+", endX="+endX+", startY="+startY+", endY="+endY);
                //判断滑动方向
                if (Math.abs(endX-startX) > Math.abs(endY-startY)) {
                    //x方向水平滑动
                    LogUtil.d("x方向水平滑动");
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

        }

        return true;
    }*/

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


