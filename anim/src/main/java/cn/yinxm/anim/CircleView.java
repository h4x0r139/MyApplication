package cn.yinxm.anim;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 功能：根据动画圆心位置，画一个圆
 * Created by yinxm on 2017/8/4.
 */

public class CircleView extends View {

    private Point mPoint;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mPoint != null) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.RED);
            canvas.drawCircle(mPoint.x, mPoint.y, 30, paint);
        }
    }


    public void setPoint(Point point) {
        mPoint = point;
        invalidate();
    }
}
