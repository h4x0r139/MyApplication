package cn.yinxm.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import cn.yinxm.lib.utils.LogUtil;

/**
 * 功能：60s发送按钮特效
 * Created by yinxm on 2017/5/25.
 */

public class ProgressbarSendView extends View{

    private int maxProgress;
    private int currentProgress;

    private boolean antiAlias = true;

    //
    Point centerPoint;//中心点

    //绘制头部
    private Paint paintHead;

    //绘制圆弧
    private Paint mArcPaint;
    private float mArcWidth;
    private float mStartAngle, mSweepAngle;
    private RectF mRectF;

    //绘制背景圆弧
    private Paint mBgArcPaint;
    private int mBgArcColor;
    private float mBgArcWidth;

    //渐变的颜色是360度，如果只显示270，那么则会缺失部分颜色
    private SweepGradient mSweepGradient;
    private int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};


    public ProgressbarSendView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtil.d("onMeasure widthMeasureSpec="+widthMeasureSpec+", heightMeasureSpec="+heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        LogUtil.d("width="+width+", height="+height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogUtil.d("onMeasure w="+w+", h="+h);

        centerPoint = new Point(w/2, w/2);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawArc(canvas);
/*
        //画笔
        Paint paint = new Paint();
        paint.setColor(R.color.colorAccent);//给画笔设置颜色值
        paint.setStrokeWidth(10);//设置画笔宽度
        canvas.drawLine(20,20, 150,150, paint);
        //图形
        Path path = new Path();
*/



    }

    private void initPaint() {
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(antiAlias);
        // 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔粗细
        mArcPaint.setStrokeWidth(mArcWidth);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mBgArcPaint = new Paint();
        mBgArcPaint.setAntiAlias(antiAlias);
        mBgArcPaint.setColor(mBgArcColor);
        mBgArcPaint.setStyle(Paint.Style.STROKE);
        mBgArcPaint.setStrokeWidth(mBgArcWidth);
        mBgArcPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void drawArc(Canvas canvas) {
        LogUtil.d("drawArc maxProgress="+maxProgress+", currentProgress="+currentProgress);

        float roundWidth = getPx(3);

        /**
         * 画最外层的大圆环
         */
//        int centre = getWidth()/2; //获取圆心的x坐标
        int centre = centerPoint.x; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth/2) - 20; //圆环的半径

        LogUtil.d("centre="+centre+", radius="+radius);

        //初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);//消除锯齿
        paint.setStrokeWidth(roundWidth);
//        paint.setColor(Color.parseColor("#7728f4"));
        paint.setStyle(Paint.Style.STROKE);//设置空心
//        paint.setStyle(Paint.Style.FILL);//设置实心


        paintHead = new Paint();
        paintHead.setAntiAlias(antiAlias);
        paintHead.setStrokeWidth(roundWidth);
        paintHead.setStyle(Paint.Style.STROKE);
//        paintHead.setColor(Color.parseColor("#9553fe"));
        paintHead.setColor(Color.WHITE);
//        paintHead.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));


        //中心点需要计算为头部的坐标
//        RadialGradient radialGradient =  new RadialGradient(300, 30, 20, Color.parseColor("#6685ef"), Color.parseColor("#9553fe"), Shader.TileMode.CLAMP);
        RadialGradient radialGradient =  new RadialGradient(580, 300, 50, Color.RED, Color.GREEN, Shader.TileMode.CLAMP);

//        paintHead.setShader(radialGradient);



        // 设置渐变
//        int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};//依次是画笔开始-结束颜色
//        #ffffff   #7728f4   #6685ef
//        int[] mGradientColors = { Color.parseColor("#7728f4"), Color.parseColor("#6685ef"), Color.parseColor("#ffffff")};
        int[] mGradientColors = {  Color.parseColor("#6685ef"), Color.parseColor("#7728f4")};
//        int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};
        mSweepGradient = new SweepGradient(centre, centre, mGradientColors, null);
        paint.setShader(mSweepGradient);

        int[] headGradientColors = {Color.parseColor("#7728f4"), Color.parseColor("#ffffff") };
        int gradientX = 0;
        int gradientY = 0;
//        RadialGradient gradient = new RadialGradient(gradientX,gradientY, 100, );
//        LinearGradient gradient = new LinearGradient()
//        paintHead.setShader(mHeadSweepGradient);

//        RectF oval = new RectF(0, 0, 100, 100);

        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限

        // 绘制背景圆弧
        // 从进度圆弧结束的地方开始重新绘制，优化性能
        canvas.save();
//        float currentAngle = mSweepAngle * mPercent;
        canvas.rotate(-90, centerPoint.x, centerPoint.y);//默认从3点钟方向开始绘制，逆时针旋转90度，开始绘制



        float sweepAngle = 0.0f;
        if (maxProgress > 0) {
            sweepAngle = currentProgress * 360.0f / maxProgress ;
        }
        canvas.drawArc(oval, 0, sweepAngle, false, paint);


        canvas.drawArc(oval, sweepAngle-30, 30, false, paintHead);//头部发光弧度

//        Path path = new Path();
//        path.addArc(oval, 0, 360);
//        canvas.drawPath(path, paint);

        // 第一个参数 oval 为 RectF 类型，即圆弧显示区域
        // startAngle 和 sweepAngle  均为 float 类型，分别表示圆弧起始角度和圆弧度数
        // 3点钟方向为0度，顺时针递增
        // 如果 startAngle < 0 或者 > 360,则相当于 startAngle % 360
        // useCenter:如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形
//        canvas.drawArc(mRectF, 2, 0, false, mArcPaint);

//        canvas.drawArc(oval, 180 , 360, false, paintHead);

        canvas.restore();
    }

    /**
     * 更新圆弧画笔
     */
/*    private void updateArcPaint() {
        // 设置渐变
        int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};
        mSweepGradient = new SweepGradient(mCenterPoint.x, mCenterPoint.y, mGradientColors, null);
        mArcPaint.setShader(mSweepGradient);
    }*/


    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        if (currentProgress <= maxProgress) {
            this.currentProgress = currentProgress;
        }
        invalidate();
    }



    public void updateProgress(int progressAdd) {
        currentProgress += progressAdd;
        invalidate();
    }

    //读取dimens里面定义好的px
    public float getPx(int px) {
        int resId = getResources().getIdentifier("dip_" + px, "dimen", getContext().getPackageName());
        float dimens = getResources().getDimension(resId);
        LogUtil.d(px+"="+dimens);
        return dimens;
    }
}
