package com.xjp.customvolumeview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.R;

/**
 * Description:圆形音量控件
 * User: xjp
 * Date: 2015/5/29
 * Time: 14:08
 */

public class VolumeView extends View {

    private static final String TAG = "VolumeView";
    private static final boolean DEBUG = false;

    //圆形半径
    private int radius = 0;
    //音量边框底色
    private int primaryVolumeColor = 0;
    //音量边框颜色
    private int volumeColor = 0;
    //圆形音量背景颜色
    private int backgroundColor = 0;
    //音量边框宽度
    private int borderWidth = 0;
    //动画百分比
    private int fraction = 0;

    //以下都是默认值
    private int defaultRadius = 60;
    private int defaultBorderWidth = 8;
    private int defaultBackgroundColor = 0x60000000;
    private int defaultVolumeColor = Color.WHITE;
    private int defaultPrimaryVolumeColor = 0x80000000;

    private RectF rectF = null;

    private Paint paint = null;

    //最大音量次数
    private int maxVolume = 15;
    //音量每增加一次，对于的角度
    private float angle = 0;
    //动画的最大值
    private int maxAnimationValue = 10;
    //音量每增加一次的单位角度
    private float unitAngle = 0;
    //当前音量的次数
    private int volumeNum = 0;
    //是否是加音量
    private boolean isVolumeUp = true;

    public VolumeView(Context context) {
        this(context, null);
    }

    public VolumeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttrs(context, attrs);
        initPaint();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        angle = 360f / maxVolume;
        unitAngle = angle / maxAnimationValue;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    /**
     * 获取自定义View的属性值
     *
     * @param context
     * @param attrs
     */
    private void setAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.VolumeView);
        if (null != a) {
            radius = a.getDimensionPixelSize(R.styleable.VolumeView_radius, defaultRadius);
            backgroundColor = a.getColor(R.styleable.VolumeView_backgroundColor, defaultBackgroundColor);
            volumeColor = a.getColor(R.styleable.VolumeView_volumeColor, defaultVolumeColor);
            primaryVolumeColor = a.getColor(R.styleable.VolumeView_primaryVolumeColor, defaultPrimaryVolumeColor);
            borderWidth = a.getDimensionPixelSize(R.styleable.VolumeView_borderWidth, defaultBorderWidth);
            maxVolume = a.getInt(R.styleable.VolumeView_maxVolume, 15);
            a.recycle();
        }

    }

    /**
     * 设置圆形半径
     *
     * @param radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * 设置音量边框的宽度
     *
     * @param borderWidth
     */
    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    /**
     * 设置最大音量值
     *
     * @param maxVolume
     */
    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    /**
     * 设置音量边框底色
     *
     * @param color
     */
    public void setPrimaryVolumeColor(int color) {
        primaryVolumeColor = color;
    }

    /**
     * 设置音量边框颜色
     *
     * @param color
     */
    public void setVolumeColor(int color) {
        volumeColor = color;
    }

    /**
     * 设置圆形音量的背景颜色
     *
     * @param color
     */
    public void setBackgroundColor(int color) {
        backgroundColor = color;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**固定自定义圆形UI的大小，不管属性设置大小多少都不影响圆形UI大小，
         唯一影响圆形UI的大小只有圆的半径，言外之意：
         只能通过半径来控制圆形UI大小，所以属性里半径为必设值。*/
        setMeasuredDimension(radius * 2, radius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制背景
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(backgroundColor);
        radius = getWidth() / 2;
        canvas.drawCircle(radius, radius, radius, paint);

        //绘制音量线圈背景
        paint.setAntiAlias(true);
        paint.setColor(primaryVolumeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(radius, radius, radius - borderWidth, paint);

        //绘制音量线圈
        paint.setAntiAlias(true);
        paint.setColor(volumeColor);
        rectF = new RectF(borderWidth, borderWidth, getWidth() - borderWidth, getHeight() - borderWidth);
        if (isVolumeUp) {//音量增加时
            canvas.drawArc(rectF, -90, angle * (volumeNum > 0 ? volumeNum - 1 : 0) + unitAngle * fraction, false, paint);
        } else {//音量减小时
            canvas.drawArc(rectF, -90, angle * (volumeNum + 1) - unitAngle * fraction, false, paint);
        }
    }


    /**
     * 控制音量增加减少时的动画效果
     */
    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, maxAnimationValue);
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = (int) animation.getAnimatedValue();
                if (DEBUG) {
                    Log.e(TAG, "the fraction is " + fraction);
                }
                invalidate();
            }
        });
        valueAnimator.start();
    }

    /**
     * 加音量
     */
    public void volumeUp() {
        isVolumeUp = true;
        if (volumeNum < maxVolume) {
            volumeNum++;
            startAnim();
        }
    }

    /**
     * 减音量
     */
    public void volumeDown() {
        isVolumeUp = false;
        if (volumeNum > 0) {
            volumeNum--;
            startAnim();
        }
    }

}
