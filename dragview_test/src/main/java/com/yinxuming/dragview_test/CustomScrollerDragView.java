package com.yinxuming.dragview_test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import cn.yinxm.lib.utils.log.LogUtil;

/**
 * Created by yinxuming on 2018/4/28.
 *  拖拽回弹效果
 *
 */

public class CustomScrollerDragView extends View {

    private int x, y;
    private Scroller mScroller;

    public CustomScrollerDragView(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollerDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollerDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //当前点
        int touchX = (int) event.getRawX();
        int touchY = (int) event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //触摸起始点
                x = (int) event.getRawX();
                y = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetX = touchX - x;
                int offsetY = touchY - y;

                ((View)getParent()).scrollBy(-offsetX, -offsetY);
                //重新设置初始坐标
                x = touchX;
                y = touchY;
                break;
            case MotionEvent.ACTION_UP:
                //实现拖动回弹回去 效果
                View viewGroup =  (View)getParent();
                mScroller.startScroll(viewGroup.getScrollX(), viewGroup.getScrollY(),
                        -viewGroup.getScrollX(), -viewGroup.getScrollY());
                invalidate();

                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        LogUtil.d("computeScroll");
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            LogUtil.d("computeScrollOffset");
            ((View) getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY());
            invalidate();
        }
    }
}
