package com.yinxm.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yinxm on 2016/4/19.
 */
public class CircleProgress extends View {



    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    public  int mStrokeWidth = 50;
    public  int mHalfStrokeWidth = mStrokeWidth / 2;
//    private float mRadius = 300;
    private float mRadius;
    private RectF mRect;
    private int mProgress = 0;
    //目标值，想改多少就改多少
    private int mTargetProgress = 100;
    private int mMax = 100;

    public float centerX, centerY;
    private int mWidth;
    private int mHeight;


    public CircleProgress(Context context) {
        super(context);
        init();
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    //完成相关参数初始化
    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.WHITE);
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.STROKE);
        mBackPaint.setStrokeWidth(mStrokeWidth);

        mFrontPaint = new Paint();
        mFrontPaint.setColor(Color.GREEN);
        mFrontPaint.setAntiAlias(true);
        mFrontPaint.setStyle(Paint.Style.STROKE);
        mFrontPaint.setStrokeWidth(mStrokeWidth);


        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    public void initData(float centerX, float centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.mRadius = radius;
    }


    //重写测量大小的onMeasure方法和绘制View的核心方法onDraw()
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("my", "CircleProgress.onMeasure");
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        Log.d("my", "CircleProgress.onMeasure mWidth="+mWidth+", mHeight="+mHeight);
        setMeasuredDimension(mWidth, mHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mRadius = getWidth()/2 - mHalfStrokeWidth;//减去进度条宽度的一半，说明指明了宽度是左右增加半个宽度来执行的
        Log.d("my", "CircleProgress.onDraw mRadius="+mRadius);
        initRect();
        float angle = mProgress / (float) mMax * 360;
        canvas.drawCircle(mWidth / 2, mHeight / 2, mRadius, mBackPaint);
        canvas.drawArc(mRect, -90, angle, false, mFrontPaint);
        canvas.drawText(mProgress + "%", mWidth / 2 + mHalfStrokeWidth, mHeight / 2 + mHalfStrokeWidth, mTextPaint);
//        if (mProgress < mTargetProgress) {
//            mProgress += 1;
//            invalidate();
//        }

    }

    public int getRealSize(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            //自己计算
            result = (int) (mRadius * 2 + mStrokeWidth);
        } else {
            result = size;
        }

        return result;
    }

    private void initRect() {
        if (mRect == null) {
            mRect = new RectF();
            int viewSize = (int) (mRadius * 2);
            int left = (mWidth - viewSize) / 2;
            int top = (mHeight - viewSize) / 2;
            int right = left + viewSize;
            int bottom = top + viewSize;
            mRect.set(left, top, right, bottom);
        }
    }

    public void updateProgress(int progress) {
        Log.d("my", "updateProgress progress="+progress);
        this.mProgress = progress;
        this.invalidate();
    }

}
