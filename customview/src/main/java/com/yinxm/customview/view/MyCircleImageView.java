package com.yinxm.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.yinxm.customview.R;
import com.yinxm.customview.util.ScreenUtil;

/**
 * 功能：自定义圆形图片
 * Created by yinxm on 2018/1/17.
 * 核心步骤
 * 方式1：
 * 1、canvas.saveLayer(srcRect, mPaint, Canvas.ALL_SAVE_FLAG); 图像保存到新图层
 * 2、canvas.drawCircle 画底层
 * 3、PorterDuff.Mode.SRC_IN即circle和bitmap相交部分中的bitmap部分
 * 4、canvas.drawBitmap 画上层,需考虑图片缩放
 * 5、canvas.restoreToCount(saveCount);
 *
 * 方式2：
 * 1、canvasDraw = new Canvas(outputBitmap) //创建一个空画布，绘制圆形Bitmap
 * 2、canvasDraw.drawCircle
 * 3、paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//两层相交图像，显示上层，即显示最后画的那一层
 * 4、canvasDraw.drawBitmap,需考虑图片缩放，得到圆形的outputBitmap
 * 5、canvas.draw(outputBitmap )
 */

public class MyCircleImageView extends ImageView {

    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;
    private static final int DEFAULT_CIRCLE_BACKGROUND_COLOR = Color.TRANSPARENT;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;

    public MyCircleImageView(Context context) {
        this(context, null);
    }

    public MyCircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCircle02(canvas);
//        drawCircle01(canvas);

    }


    void  drawCircle02(Canvas canvas) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        int  minW = viewWidth > viewHeight ? viewHeight : viewWidth;//最小宽度，圆形直径
        Log.d("yinxm", "w="+viewWidth+", h="+viewHeight+", minW="+minW);

        Drawable drawable = getDrawable();
        Log.d("yinxm", "drawable="+drawable);
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            return;
        }
        //获取到原图
        Bitmap srcBitmap = ((BitmapDrawable) drawable).getBitmap();
        if (srcBitmap == null) {
            return;
        }

        //裁剪缩放图片
        Bitmap circleBitmap = getCroppedBitmap(srcBitmap, minW);
        canvas.drawBitmap(circleBitmap, 0, 0, null);

    }

    /**
     *
     * @param bitmap 原图
     * @param diameter 圆形直径
     * @return 返回一个圆形缩放裁剪后的Bitmap对象
     */
    public Bitmap getCroppedBitmap (Bitmap bitmap, int diameter) {
        if (bitmap == null) {
            return null;
        }
        Log.d("yinxm", "src w="+bitmap.getWidth()+", h="+bitmap.getHeight()+", count="+bitmap.getByteCount());
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, diameter, diameter, false);
        Log.d("yinxm", "scaled w="+scaledBitmap.getWidth()+", h="+scaledBitmap.getHeight()+", count="+scaledBitmap.getByteCount());

        Bitmap output = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        canvas.drawCircle(diameter/2, diameter/2, diameter/2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledBitmap, 0, 0, paint);
        return output;
    }




    /**
     * 通过saveLayer 消耗资源较多
     * @param canvas
     */
    void drawCircle01(Canvas canvas) {
        //根据屏幕区域，控件宽高，控制绘制区域
        int screenWidth = ScreenUtil.getScreenW(getContext());
        int screenHeight = ScreenUtil.getScreenH(getContext());
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        Log.d("yinxm", "w="+viewWidth+", h="+viewHeight);

        int centerX = viewWidth/2;
        int centerY = viewHeight/2;
        int quarterWidth = Math.min(viewWidth, viewHeight) / 2;
        RectF srcRect = new RectF(centerX-quarterWidth, centerY-quarterWidth, centerX+quarterWidth, centerY+quarterWidth);
        RectF dstRect = new RectF(centerX-quarterWidth, centerY-quarterWidth, centerX+quarterWidth, centerY+quarterWidth);

//        Bitmap destBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //创建一个Bitmap中包含一个圆形
        Bitmap destBitmap = Bitmap.createBitmap(viewWidth, viewHeight, Bitmap.Config.ARGB_4444);
        Canvas destCanvas = new Canvas(destBitmap);
        Paint destPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG);
        destCanvas.drawOval(dstRect, destPaint);
        //要显示的图片
        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.img01);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);
        //背景色设为白色，方便比较效果
        canvas.drawColor(Color.WHITE);
        //将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
        int saveCount = canvas.saveLayer(srcRect, paint, Canvas.ALL_SAVE_FLAG);
        //绘制目标图
        canvas.drawBitmap(destBitmap, null, dstRect, paint);
        //设置混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //绘制源图
        canvas.drawBitmap(srcBitmap, null, srcRect, paint);
        //清除混合模式
        paint.setXfermode(null);
        //还原画布
        canvas.restoreToCount(saveCount);
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//        int width = w <= h ? w : h;
//        int centerX = w/2;
//        int centerY = h/2;
//        int quarterWidth = width /4;
//        srcRect = new RectF(centerX-quarterWidth, centerY-quarterWidth, centerX+quarterWidth, centerY+quarterWidth);
//        dstRect = new RectF(centerX-quarterWidth, centerY-quarterWidth, centerX+quarterWidth, centerY+quarterWidth);
    }

    //定义一个绘制圆形Bitmap的方法，作为dst图
    private Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFF26AAD1);
        c.drawOval(new RectF(0, 0, w * 3 / 4, h * 3 / 4), p);
        return bm;
    }

    //定义一个绘制矩形的Bitmap的方法，作为src图
    private Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFCE43);
        c.drawRect(w / 3, h / 3, w * 19 / 20, h * 19 / 20, p);
        return bm;
    }


    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

//    /**
//     * 根据原图添加圆角
//     *
//     * @param source
//     * @return
//     */
//    private Bitmap createRoundConerImage(Bitmap source)
//    {
//        final Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Config.ARGB_8888);
//        Canvas canvas = new Canvas(target);
//        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(source, 0, 0, paint);
//        return target;
//    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


//        Bitmap srcBitmap = getBitmapFromDrawable(getDrawable());
//        Drawable drawable = getDrawable();
//        Log.d("yinxm", "drawable=" + drawable + ", w=" + drawable.getIntrinsicWidth() + ", h=" + drawable.getIntrinsicHeight());
//        Bitmap srcBitmap = ((BitmapDrawable) drawable).getBitmap();//得到的是原图大小
//        Log.d("yinxm", "srcBitmap=" + srcBitmap + ", getByteCount=" + srcBitmap.getByteCount() + ", w=" + srcBitmap.getWidth() + ", h=" + srcBitmap.getHeight());//getByteCount=6096000, w=1500, h=1016
//        drawable.draw(canvas);//如果不进行缩放，这里画出来的图片，只会显示左上角部分

//    int w = 300;
//    int h = 300;
//    int min = Math.min(w, h);
//        Log.d("yinxm", "w=" + w + ", h=" + h + ", min=" + min);//getByteCount=6096000, w=1500, h=1016
//                Paint paint = new Paint();
////        paint.setStyle(Paint.Style.FILL);//默认0，fill
////        paint.setAntiAlias(true);
//                paint.setFilterBitmap(false);
//                paint.setStyle(Paint.Style.FILL);
//                paint.setColor(Color.WHITE);
//
//                Bitmap desBitmap = null;//下层图
//                desBitmap = makeDst(w,h);//得到bitmap，上面是个圆
//
////        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
//                canvas.drawBitmap(desBitmap, 0, 0, paint);
//                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//两层相交图像，显示上层，即显示最后画的那一层
//                paint.setColor(Color.RED);
////        //从图片资源文件得到Bitmap并缩放图片
////        bitmap = Bitmap.createScaledBitmap(srcBitmap, w-100, h-100, false);
////        Rect rect = new Rect(0, 0, w, h);
////        canvas.drawBitmap(bitmap, 0, 0, null);
////        canvas.drawBitmap(bitmap, rect, rect);
////        canvas.drawCircle(min/2+100, min/2, min/2-10, paint);
//
//                Bitmap srcBitmap = null;
//                srcBitmap = makeSrc(w, h);//得到bitmap，上面是个圆
//
////        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
//                canvas.drawBitmap(srcBitmap, 0, 0, paint);
