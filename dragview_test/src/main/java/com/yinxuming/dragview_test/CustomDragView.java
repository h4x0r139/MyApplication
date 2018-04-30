package com.yinxuming.dragview_test;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cn.yinxm.lib.utils.log.LogUtil;

/**
 * Created by yinxuming on 2018/4/28.
 *
 * 通过重写onTouchEvent方法，调用layout方法进行布局，实现拖动效果
 * http://wuxiaolong.me/2015/11/20/DragView/
 *
 */

public class CustomDragView extends View{
    private int startX, startY;//触摸起始点位置

    public CustomDragView(Context context) {
        super(context);
    }

    public CustomDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDragView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //当前位置
        int touchX = (int)event.getX();
        int touchY = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下事件为起始位置
                startX = (int)event.getX();
                startY = (int)event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动事件，计算当前位置与起始点的位移量
                int offsetX = touchX - startX;
                int offsetY = touchY - startY;
                LogUtil.d("offsetX="+offsetX+"， offsetY="+offsetY);

                //调用layout重新布局，在当前left、top、right、bottom上面增加位移量
                layout(getLeft()+offsetX, getTop()+offsetY, getRight()+offsetX, getBottom()+offsetY);

                // 方法2，
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //方法3，get setLayoutParams，【无效】
//                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                params.leftMargin = getLeft() + offsetX;
//                params.topMargin = getTop() + offsetY;
//                setLayoutParams(params);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

//        return super.onTouchEvent(event);
        return true;//必须要消费事件
    }
}
