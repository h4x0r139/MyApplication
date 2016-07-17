package com.yinxm.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by yinxm on 2016/4/20.
 * http://www.jianshu.com/p/4f55200cea14
 */
public class CircleImageView extends ImageView {

    private int radius;//半径

    //基本的三个构造函数
    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("my", "CircleImageView.onMeasure");
        int width = getRealSize(widthMeasureSpec);
        int height = getRealSize(heightMeasureSpec);
        int min = Math.min(width, height);
        setMeasuredDimension(min, min);
        radius = min / 2;
        Log.d("my", "CircleImageView.onMeasure width="+width+", height="+height+", radius="+radius);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    public int getRealSize(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        Log.d("my", " mode=" + mode + ", size="+size);
        if (mode == MeasureSpec.EXACTLY) {//精确值模式,指定100dp,match_parent
            Log.d("my", " MeasureSpec.EXACTLY 模式 size="+size);
            result = size;
        }else if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {//最大值模式，或者没有指定宽高——》默认值
            Log.d("my", "MeasureSpec.AT_MOST模式 size=200");
            result = 200;
        }
        return result;
    }

    //自定义View实现过程中很重要的onDraw绘制图形的方法
    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();

        //空值判断，必要步骤，避免由于没有设置src导致的异常错误
        if (drawable == null) {
            return;
        }

        //必要步骤，避免由于初始化之前导致的异常错误
        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        if (!(drawable instanceof BitmapDrawable)) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();

        if (null == b) {
            return;
        }

        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_4444, true);

        int w = getWidth();
        Log.d("my", "CircleImageView.onDraw  width="+w);
//        w = w - CircleProgress.mHalfStrokeWidth;

        Bitmap roundBitmap = getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);

    }


    /**
     * 初始Bitmap对象的缩放裁剪过程
     *
     * @param bmp    初始Bitmap对象
     * @param radius 圆形图片直径大小
     * @return 返回一个圆形的缩放裁剪过后的Bitmap对象
     */
    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        //比较初始Bitmap宽高和给定的圆形直径，判断是否需要缩放裁剪Bitmap对象
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(),
                Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2,
                sbmp.getHeight() / 2, sbmp.getWidth() / 2, paint);
        //核心部分，设置两张图片的相交模式，在这里就是上面绘制的Circle和下面绘制的Bitmap
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);
        return output;
    }


}
