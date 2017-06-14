package cn.yinxm.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
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
 * Created by yinxm on 2017/6/14.
 */

public class ProgressbarSendView extends View{

    private float maxProgress;
    private float currentProgress;

    private boolean antiAlias = true;

    //
    Point centerPoint;//中心点
    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度

    //绘制头部
    private Paint paintHeadArc;//头部圆弧
    private Paint paintHeadRound;//头部球形
    private float headRoundRadius = getPx(5);//头部圆形半径
    private Bitmap mLititleBitmap;  // 圆点图片
    private Matrix mMatrix;          // 矩阵,用于对图片进行一些操作


    //绘制圆弧
    private Paint mArcPaint;
    private float mArcWidth;
    private float mStartAngle, mSweepAngle;
    private RectF mRectF;
    int lightAngle = 8;//圆弧偏移角度


    //渐变的颜色是360度，如果只显示270，那么则会缺失部分颜色
    private SweepGradient mSweepGradient;
    private int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};

    float roundWidth = getPx(3);//线条宽度


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
        LogUtil.d("onDraw");
        drawArc(canvas);
    }

    private void initPaint() {
        pos = new float[2];
        tan = new float[2];

        mMatrix = new Matrix();

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(antiAlias);
        // 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔粗细
        mArcPaint.setStrokeWidth(mArcWidth);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);


        setLayerType(LAYER_TYPE_SOFTWARE,null);
    }

    private void drawArc(Canvas canvas) {
        LogUtil.d("drawArc maxProgress="+maxProgress+", currentProgress="+currentProgress);

        if (currentProgress == 0 || maxProgress == 0) {
            return;
        }

        //画最外层的大圆环
        int centre = centerPoint.x; //获取圆心的x坐标
        float radius = (int) (centre - roundWidth/2) - headRoundRadius*2; //圆环的半径
        LogUtil.d("centre="+centre+", radius="+radius);
        if (radius <= 0) {
            radius = (int) (centre - roundWidth/2);
        }

        //初始化画笔
        Paint paint = new Paint();
        paint.setAntiAlias(true);//消除锯齿
        paint.setStrokeWidth(roundWidth);
//        paint.setColor(Color.parseColor("#7728f4"));
        paint.setStyle(Paint.Style.STROKE);//设置空心


        int[] mGradientColors = {  Color.parseColor("#6685ef"), Color.parseColor("#7728f4")};
//        int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.RED};
        mSweepGradient = new SweepGradient(centre, centre, mGradientColors, null);
        paint.setShader(mSweepGradient);


        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限

        // 绘制背景圆弧
        // 从进度圆弧结束的地方开始重新绘制，优化性能
        canvas.save();
//        float currentAngle = mSweepAngle * mPercent;
        canvas.rotate(-90, centerPoint.x, centerPoint.y);//默认从3点钟方向开始绘制，逆时针旋转90度，开始绘制


        //1、绘制进度
        float sweepAngle = 0.0f;
        if (maxProgress > 0) {
            sweepAngle = currentProgress * 360.0f / maxProgress ;
        }
        canvas.drawArc(oval, 0, sweepAngle, false, paint);

       //2、绘制头部
        paintHeadRound = new Paint();
        paintHeadRound.setAntiAlias(antiAlias);
        paintHeadRound.setStrokeCap(Paint.Cap.ROUND);
        paintHeadRound.setStrokeWidth(roundWidth);
        paintHeadRound.setStyle(Paint.Style.FILL);
//        paintHeadRound.setColor(Color.parseColor("#9553fe"));
        paintHeadArc = new Paint();
        paintHeadArc.setAntiAlias(true);
        paintHeadArc.setStrokeWidth(roundWidth);
        paintHeadArc.setStrokeCap(Paint.Cap.ROUND);
        paintHeadArc.setStyle(Paint.Style.STROKE);


        //计算圆弧路径
        Path pathHead = new Path();
        pathHead.addArc(oval, 0, sweepAngle);
        PathMeasure pathMeasure = new PathMeasure(pathHead, false);
        pathMeasure.getPosTan(pathMeasure.getLength(), pos, tan);
        LogUtil.e("1，length="+pathMeasure.getLength()+", pos="+pos[0]+","+pos[1]+", tan="+tan[0]+","+tan[1]);

        //绘制实心小圆圈
        paintHeadRound.setColor(Color.parseColor("#6685ef"));
        paintHeadRound.setMaskFilter(new BlurMaskFilter(getPx(10), BlurMaskFilter.Blur.SOLID));
//        //头部圆圈渐变
        int[] gradientHeadRoundColors = {Color.WHITE,Color.parseColor("#6685ef"), Color.parseColor("#9553fe")};
        float[] gradientHeadRoundColorsStops  = new float[]{0f,0.3f,1f};
        RadialGradient gradientHeadRound = new RadialGradient(pos[0], pos[1], getPx(5), gradientHeadRoundColors, gradientHeadRoundColorsStops,  Shader.TileMode.CLAMP);
        paintHeadRound.setShader(gradientHeadRound);
        canvas.drawCircle(pos[0], pos[1], headRoundRadius, paintHeadRound);


        //3、绘制头部弧形
//        paintHeadArc.setColor(Color.WHITE);
        paintHeadArc.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.SOLID));
        int[] headGradientColors = {Color.parseColor("#6685ef"), Color.parseColor("#7728f4"), Color.parseColor("#ffffff") };

//        LinearGradient gradient = new LinearGradient(pos[0]-50 ,pos[1]-50, pos[0], pos[1], headGradientColors[0], headGradientColors[1], Shader.TileMode.CLAMP);
        Path pathHeadArc = new Path();
        pathHeadArc.addArc(oval, 0, sweepAngle-lightAngle);//圆弧角度
        PathMeasure pathMeasureHeadArc = new PathMeasure(pathHeadArc, false);
        float[] posHeadArc = new float[2];
        float[] tanHeadArc = new float[2];
        pathMeasureHeadArc.getPosTan(pathMeasureHeadArc.getLength(), posHeadArc, tanHeadArc);
        LogUtil.e("2，length="+pathMeasureHeadArc.getLength()+", pos="+posHeadArc[0]+","+posHeadArc[1]+", tan="+tanHeadArc[0]+","+tanHeadArc[1]);

        float gradientX = posHeadArc[0];
        float gradientY = posHeadArc[1];
        int currentColor = 0;
        if (sweepAngle > 180) {
            currentColor = headGradientColors[1];
        } else {
            currentColor = headGradientColors[0];
        }
        if (pathMeasure.getLength()-pathMeasureHeadArc.getLength() > 0) {
            RadialGradient gradient = new RadialGradient(gradientX,gradientY, pathMeasure.getLength()-pathMeasureHeadArc.getLength(),
                    currentColor, headGradientColors[2], Shader.TileMode.CLAMP);
            paintHeadArc.setShader(gradient);
        }
        if (sweepAngle-lightAngle > 0) {
            canvas.drawArc(oval, sweepAngle-lightAngle, lightAngle, false, paintHeadArc);//头部发光弧度
        }

        canvas.restore();
    }


    public void setMaxProgress(float maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setCurrentProgress(float currentProgress) {
        if (currentProgress <= maxProgress) {
            this.currentProgress = currentProgress;
        } else {
            this.currentProgress = maxProgress;
        }
        invalidate();
    }

    public float getMaxProgress() {
        return maxProgress;
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    //读取dimens里面定义好的px
    public float getPx(int px) {
        int resId = getResources().getIdentifier("dip_" + px, "dimen", getContext().getPackageName());
        float dimens = getResources().getDimension(resId);
        LogUtil.d(px+"="+dimens);
        return dimens;
    }
}
