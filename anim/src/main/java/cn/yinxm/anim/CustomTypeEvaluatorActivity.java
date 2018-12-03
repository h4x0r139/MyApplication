package cn.yinxm.anim;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;

import cn.yinxm.lib.utils.log.LogUtil;

public class CustomTypeEvaluatorActivity extends Activity {

    CircleView circleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_type_evaluator);
        circleView = (CircleView) findViewById(R.id.circleView);

        customTypeEvaluator();
    }


    /**
     * 自定义估值器动画，斜对角平移
     */
    private void customTypeEvaluator() {
        Point pointStart = new Point(0, 0);
        Point pointEnd = new Point(500, 500);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointTypeEvaluator(), pointStart, pointEnd);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                LogUtil.d("customTypeEvaluator.onAnimationUpdate x=" + point.x + ", y=" + point.y);
                //布局会跟着更新，移动控件
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
//                params.setMargins(point.x, point.y, 0, 0);
//                view.requestLayout();

                //不行
//                Paint paint = new Paint();
//                paint.setStyle(Paint.Style.FILL);
//                paint.setColor(Color.RED);
//                Canvas canvas = new Canvas();
//                canvas.drawCircle(point.x, point.y, 100, paint);
                circleView.setPoint(point);
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.start();
    }
}
