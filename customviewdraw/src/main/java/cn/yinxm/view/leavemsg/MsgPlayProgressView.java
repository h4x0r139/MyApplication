package cn.yinxm.view.leavemsg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.yinxm.lib.screen.ScreenUtil;
import cn.yinxm.lib.utils.LogUtil;

/**
 * 功能：留言播放进度效果
 * Created by yinxm on 2017/5/29.
 */

public class MsgPlayProgressView extends View {

    //进度条最大值
    private float maxProgress;
    //进度条当前值
    private float currentProgress;

    //画笔
    Paint paint;
    private int mWidth, mHeight;

    public MsgPlayProgressView(Context context) {
        super(context);
    }

    public MsgPlayProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MsgPlayProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO: 2017/5/29 学习测量
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = ScreenUtil.dp2px(getContext(), 15);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

//        drawBackground1(canvas);
//        drawBackground2(canvas);

//        paint.setColor(Color.rgb(71, 76, 80));
        //渐变效果
        int[] gradientColors = {Color.parseColor("#23252e"), Color.parseColor("#7900ff"), Color.parseColor("#6585ee")};//#4d3198，,Color.parseColor("#7900ff")
        LinearGradient linearGradientShader = new LinearGradient(0, 0, mWidth, 0, gradientColors, null, Shader.TileMode.CLAMP);
        paint.setShader(linearGradientShader);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //计算当前进度
        float progressWidth = mWidth * currentProgress / maxProgress;
        //计算圆角
        int round = mHeight/2;
        LogUtil.d("currentProgress="+currentProgress+", mHeight="+mHeight+", progressWidth="+progressWidth+", round="+round);


        //先绘制左侧半圆区域
        if (progressWidth < mHeight) {//左侧半圆区域占用 mHeight/2 宽度
            RectF rectFLeft = new RectF(0, 0, mHeight, mHeight);
//            RectF rectFLeft = new RectF(progressWidth -mHeight, 0, progressWidth, mHeight);

            //计算弧线角度
            float centerAngle = 180;

            float angle = progressWidth/round*90;//圆弧上下夹角

            canvas.drawArc(rectFLeft, centerAngle-angle, angle*2, false, paint);
//            LogUtil.d("angle="+angle);
//            RectF rectFLeft = new RectF(progressWidth -mHeight, 0,mHeight, mHeight);
//            canvas.drawCircle(progressWidth-mHeight, 0,mHeight, paint);

            //左侧变成全圆，平移过来


        }else {
            RectF rectF = new RectF(0, 0, progressWidth, mHeight);//绘制进度条区域
//        canvas.drawRect(rectF, paint);
        canvas.drawRoundRect(rectF, mHeight, mHeight, paint);
        }



        LogUtil.d("round="+round);
    }

    //通过描点，划线实现
    private void drawBackground1(Canvas canvas) {
        //绘制带箭头的提示框
        paint.reset();
        paint.setAntiAlias(true);//抗锯齿
        paint.setColor(Color.parseColor("#6585ee"));//画笔颜色
        paint.setStrokeWidth(10);//画笔宽度
        paint.setStyle(Paint.Style.STROKE);//仅仅描边


        Path path = new Path();
        path.moveTo(getPx(100), 0);
//        path.lineTo(getPx(90), 0);
        //右侧圆弧外接矩形宽90-109，高0-45
        RectF rectfRight = new RectF(0,0,getPx(45), getPx(45));
        path.addArc(rectfRight,0, 45);//默认起始位置，3点钟方向
//        path.arcTo(rectfRight, 0, 45, false);//会将落笔点与3点钟方向用直线连起来

        canvas.drawPath(path, paint);

    }

    //通过圆角矩形和三角形相交实现，只能实现填充效果，边框效果无法实现
    private void drawBackground2(Canvas canvas) {
        //绘制带箭头的提示框
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#6585ee"));//边框颜色
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
//        paint.setPathEffect(new CornerPathEffect(50));

//        paint.setXfermode(new PorterDuffXfermode(Mo));


        Path path1 = new Path();
//        path1.setFillType(Path.FillType.WINDING);
//        path1.setFillType(Path.FillType.EVEN_ODD);
//        path1.setFillType(Path.FillType.INVERSE_EVEN_ODD);
//        path1.setFillType(Path.FillType.INVERSE_WINDING);

//        path1.transform();

        RectF rectfRight = new RectF(0,0,getPx(300), getPx(100));
        path1.addRoundRect(rectfRight, getPx(20), getPx(20), Path.Direction.CW);

        //画左下角 三角
//        Path path2 = new Path();
        path1.moveTo(getPx(10), getPx(80));
        path1.lineTo(getPx(2), getPx(120));
        path1.lineTo(getPx(20), getPx(90));
        path1.lineTo(getPx(10), getPx(80));
//        path1.op(path2, Path.Op.UNION);//api 19

        canvas.drawPath(path1, paint);


        //构造两个Region
        Region region = new Region(new Rect());
        Region region2= new Region(new Rect());

        //取两个区域的交集
        region.op(region2, Region.Op.INTERSECT);
    }


    public void setMaxProgress(float maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    //读取dimens里面定义好的px
    public float getPx(int px) {
        int resId = getResources().getIdentifier("dip_" + px, "dimen", getContext().getPackageName());
        float dimens = getResources().getDimension(resId);
        LogUtil.d(px+"="+dimens);
        return dimens;
    }
}
