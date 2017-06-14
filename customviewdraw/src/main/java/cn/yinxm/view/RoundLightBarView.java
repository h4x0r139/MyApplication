package cn.yinxm.view;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import cn.yinxm.lib.screen.ScreenUtil;

/**
 * 功能：光点模糊渐变的自旋转圆环特效
 * Created by yinxm on 2017/6/14.
 * 来源： http://blog.csdn.net/qq_22770457/article/details/54882052
 */

public class RoundLightBarView extends ImageView {
    private int mTotalWidth, mTotalHeight;
    private int mCenterX, mCenterY;
    //底色画笔
    private Paint mCirclePaint;
    //进度条画笔
    private Paint mProgressPaint;
    //圆点画笔
    private Paint mbitmapPaint;
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作
    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度


    private int mCircleR;


    private Context mContext;
    //距离外围的边距
    private float interval ;

    private int startAngle = 1;

    //球
    private Bitmap mLititleBitmap;  // 圆点图片

    public RoundLightBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public RoundLightBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        interval  = ScreenUtil.px2dp(mContext, 100);
        //初始化画笔
        initPaint();
        //初始化bitmap
        initBitmap();
    }

    private void initBitmap() {
        mMatrix=new Matrix();
        pos = new float[2];
        tan = new float[2];
        mLititleBitmap= ((BitmapDrawable) getResources()
                .getDrawable(R.mipmap.leave_msg_head))
                .getBitmap();
    }

    private void initPaint() {
        //画黑底的深色圆画笔
        mCirclePaint = new Paint();
        //抗锯齿
        mCirclePaint.setAntiAlias(true);
        // 防抖动
        mCirclePaint.setDither(true);
        // 开启图像过滤，对位图进行滤波处理。
        mCirclePaint.setFilterBitmap(true);
        mCirclePaint.setColor(Color.BLACK);
        //空心圆
        mCirclePaint.setStyle(Paint.Style.STROKE);
        //圆半径
        mCircleR =ScreenUtil.px2dp(mContext, 20);
        mCirclePaint.setStrokeWidth(mCircleR);

        //画彩色圆弧的画笔
        mProgressPaint = new Paint();
        //抗锯齿
        mProgressPaint.setAntiAlias(true);
        // 防抖动
        mProgressPaint.setDither(true);
        // 开启图像过滤，对位图进行滤波处理。
        mProgressPaint.setFilterBitmap(true);
        mProgressPaint.setColor(Color.BLUE);
        //空心圆
        mProgressPaint.setStyle(Paint.Style.STROKE);
        //设置笔刷样式为原型
        mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        //设置圆弧粗
        mProgressPaint.setStrokeWidth(mCircleR);
        //将绘制的内容显示在第一次绘制内容之上
        mProgressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));


        //圆点画笔
        mbitmapPaint = new Paint();
        mbitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        mbitmapPaint.setStyle(Paint.Style.FILL);
        mbitmapPaint.setAntiAlias(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas去锯齿
        canvas.setDrawFilter(
                new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        //画底色圆
        canvas.drawCircle(mCenterX, mCenterY, mCenterX - mCircleR - interval, mCirclePaint);
        //画进度条
        int colorSweep[] = {Color.TRANSPARENT, Color.parseColor("#3bbaea"),Color.parseColor("#7ac9d3"),Color.parseColor("#7cc9d0")};

        //设置渐变色
        sweepGradient = new SweepGradient(mCenterX, mCenterY, colorSweep, null);
        //按照圆心旋转
        Matrix matrix = new Matrix();
        matrix.setRotate(startAngle, mCenterX, mCenterY);
        sweepGradient.setLocalMatrix(matrix);

        mProgressPaint.setShader(sweepGradient);

        canvas.drawArc(
                new RectF(0 + mCircleR + interval, 0 + mCircleR + interval,
                        mTotalWidth - mCircleR - interval, mTotalHeight - mCircleR - interval),
                2 + startAngle, 350, false, mProgressPaint);

        startAngle++;
        if (startAngle == 360) {
            startAngle = 1;
        }

        //绘制白色小星星
        Path orbit = new Path();
        //通过Path类画一个90度（180—270）的内切圆弧路径
        orbit.addArc(
                new RectF(0 + mCircleR + interval, 0 + mCircleR + interval,
                        mTotalWidth - mCircleR - interval, mTotalHeight - mCircleR - interval)
                , 2 + startAngle, 350);
        // 创建 PathMeasure
        PathMeasure measure = new PathMeasure(orbit, false);
        measure.getPosTan(measure.getLength() * 1, pos, tan);
        mMatrix.reset();
        mMatrix.postScale(2,2);
        //进度条头部 不使用图片资源
//        mMatrix.postTranslate(pos[0] - mLititleBitmap.getWidth()  , pos[1] - mLititleBitmap.getHeight()  );   // 将图片绘制中心调整到与当前点重合
//        canvas.drawBitmap(mLititleBitmap, mMatrix, mbitmapPaint);//绘制球
        mbitmapPaint.setColor(Color.WHITE);
        mbitmapPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.OUTER));
        //绘制实心小圆圈
        canvas.drawCircle(pos[0], pos[1], 100, mbitmapPaint);

        //启动绘制
        postInvalidateDelayed(10);
    }

    SweepGradient sweepGradient;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;


    }
}
