package cn.yinxm.anim;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * 功能：自定义Point估值器
 * Created by yinxm on 2017/8/4.
 */

public class PointTypeEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point pointStart = (Point) startValue;
        Point pointEnd = (Point) endValue;
        //对角线平移动画，从左上到右下
        float x = pointStart.x + fraction*(pointEnd.x - pointStart.x);//初始值+动画进度*动画改变值
        float y = pointStart.y + fraction*(pointEnd.y - pointStart.y);
        Point pointNew = new Point((int)x, (int)y);
        return pointNew;
    }
}
