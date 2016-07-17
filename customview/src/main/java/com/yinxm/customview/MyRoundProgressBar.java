package com.yinxm.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;


/** 
 * 仿iphone带进度的进度条，线程安全的View，可直接在线程中更新进度 
 * @author xiaanming 
 * 
 */  
public class MyRoundProgressBar extends ImageView {
	
	
	// 控件默认长、宽
	private int defaultWidth = 0;
	private int defaultHeight = 0;
    /** 
     * 画笔对象的引用 
     */  
    private Paint paint;
      
    /** 
     * 圆环的颜色 
     */  
    private int roundColor;  
      
    /** 
     * 圆环进度的颜色 
     */  
    private int roundProgressColor;  
      
    /** 
     * 中间进度百分比的字符串的颜色 
     */  
    private int textColor;  
      
    /** 
     * 中间进度百分比的字符串的字体 
     */  
    private float textSize;  
      
    /** 
     * 圆环的宽度 
     */  
    private float roundWidth;  
      
    /** 
     * 最大进度 
     */  
    private int max;  
      
    /** 
     * 当前进度 
     */  
    private int progress;  
    /** 
     * 是否显示中间的进度 
     */  
    private boolean textIsDisplayable;  
      
    /** 
     * 进度的风格，实心或者空心 
     */  
    private int style;  
      
    public static final int STROKE = 0;  
    public static final int FILL = 1;  
      
    public MyRoundProgressBar(Context context) {
        this(context, null);  
    }  
  
    public MyRoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);  
    }  
      
    public MyRoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);  
          
        paint = new Paint();
  
          
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBar);
          
        //获取自定义属性和默认值  
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypedArray.getColor(R.styleable.RoundProgressBar_textColor, Color.GREEN);
        textSize = mTypedArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);  
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);  
        max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);  
        textIsDisplayable = mTypedArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);  
        style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);  
          
        mTypedArray.recycle();  
    }  
      
  
    @Override
    protected void onDraw(Canvas canvas) {
          
          
        
        Drawable drawable = getDrawable();
		if (drawable == null) {
			return;
		}

		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		this.measure(0, 0);
		if (drawable.getClass() == NinePatchDrawable.class)
			return;
		Bitmap b = ((BitmapDrawable) drawable).getBitmap();
		Bitmap bitmap = b.copy(Config.ARGB_8888, true);
		if (defaultWidth == 0) {
			defaultWidth = getWidth();

		}
		if (defaultHeight == 0) {
			defaultHeight = getHeight();
		}
        
        
        /** 
         * 画最外层的大圆环 
         */  
        int centre = getWidth()/2; //获取圆心的x坐标  
        int radius = (int) (centre - roundWidth/2); //圆环的半径  
        paint.setColor(roundColor); //设置圆环的颜色  
        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度  
        paint.setAntiAlias(true);  //消除锯齿   
        canvas.drawCircle(centre, centre, radius, paint); //画出圆环  
          
        Log.e("log", centre + "");
          
        /** 
         * 画进度百分比 
         */  
        paint.setStrokeWidth(0);   
        paint.setColor(textColor);  
        paint.setTextSize(textSize);  
        paint.setTypeface(Typeface.DEFAULT_BOLD); //设置字体
        int percent = (int)(((float)progress / (float)max) * 100);  //中间的进度百分比，先转换成float在进行除法运算，不然都为0  
        float textWidth = paint.measureText(percent + "%");   //测量字体宽度，我们需要根据字体的宽度设置在圆环中间  
          
        if(textIsDisplayable && percent != 0 && style == STROKE){  
            canvas.drawText(percent + "%", centre - textWidth / 2, centre + textSize/2, paint); //画出进度百分比  
        }  
          
          
        /** 
         * 画圆弧 ，画圆环的进度 
         */  
          
        //设置进度是实心还是空心  
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度  
        paint.setColor(roundProgressColor);  //设置进度的颜色  
        paint.setStrokeCap(Cap.ROUND);
        RectF oval = new RectF(centre - radius, centre - radius, centre
                + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限  
          
        switch (style) {  
        case STROKE:{  
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawArc(oval, 270, 360 * progress / max, false, paint);  //根据进度画圆弧  
            break;  
        }  
        case FILL:{  
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            if(progress !=0)  
                canvas.drawArc(oval, 270, 360 * progress / max, true, paint);  //根据进度画圆弧  
            break;  
        }  
        }  
        
        Bitmap roundBitmap = getCroppedRoundBitmap(bitmap, radius);
		canvas.drawBitmap(roundBitmap, defaultWidth / 2 - radius, defaultHeight
				/ 2 - radius, null);
          
    }  
      
      
    public synchronized int getMax() {  
        return max;  
    }  
  
    /** 
     * 设置进度的最大值 
     * @param max 
     */  
    public synchronized void setMax(int max) {  
        if(max < 0){  
            throw new IllegalArgumentException("max not less than 0");
        }  
        this.max = max;  
    }  
  
    /** 
     * 获取进度.需要同步 
     * @return 
     */  
    public synchronized int getProgress() {  
        return progress;  
    }  
  
    /** 
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步 
     * 刷新界面调用postInvalidate()能在非UI线程刷新 
     * @param progress 
     */  
    public synchronized void setProgress(int progress) {  
        if(progress < 0){  
            throw new IllegalArgumentException("progress not less than 0");
        }  
        if(progress > max){  
            progress = max;  
        }  
        if(progress <= max){  
            this.progress = progress;  
            postInvalidate();  
        }  
          
    }  
      
      
    public int getCricleColor() {  
        return roundColor;  
    }  
  
    public void setCricleColor(int cricleColor) {  
        this.roundColor = cricleColor;  
    }  
  
    public int getCricleProgressColor() {  
        return roundProgressColor;  
    }  
  
    public void setCricleProgressColor(int cricleProgressColor) {  
        this.roundProgressColor = cricleProgressColor;  
    }  
  
    public int getTextColor() {  
        return textColor;  
    }  
  
    public void setTextColor(int textColor) {  
        this.textColor = textColor;  
    }  
  
    public float getTextSize() {  
        return textSize;  
    }  
  
    public void setTextSize(float textSize) {  
        this.textSize = textSize;  
    }  
  
    public float getRoundWidth() {  
        return roundWidth;  
    }  
  
    public void setRoundWidth(float roundWidth) {  
        this.roundWidth = roundWidth;  
    }  
  
    /**
	 * 获取裁剪后的圆形图片
	 * 
	 * @param radius
	 *            半径
	 */
	public Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
		Bitmap scaledSrcBmp;
		int diameter = radius * 2;

		// 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		int squareWidth = 0, squareHeight = 0;
		int x = 0, y = 0;
		Bitmap squareBitmap;
		if (bmpHeight > bmpWidth) {// 高大于宽
			squareWidth = squareHeight = bmpWidth;
			x = 0;
			y = (bmpHeight - bmpWidth) / 2;
			// 截取正方形图片
			squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
					squareHeight);
		} else if (bmpHeight < bmpWidth) {// 宽大于高
			squareWidth = squareHeight = bmpHeight;
			x = (bmpWidth - bmpHeight) / 2;
			y = 0;
			squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth,
					squareHeight);
		} else {
			squareBitmap = bmp;
		}

		if (squareBitmap.getWidth() != diameter
				|| squareBitmap.getHeight() != diameter) {
			scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter,
					diameter, true);

		} else {
			scaledSrcBmp = squareBitmap;
		}
		Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
				scaledSrcBmp.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		Paint paint = new Paint();
		Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(),
				scaledSrcBmp.getHeight());

		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setDither(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
				scaledSrcBmp.getHeight() / 2, scaledSrcBmp.getWidth() / 2,
				paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
		// bitmap回收(recycle导致在布局文件XML看不到效果)
		// bmp.recycle();
		// squareBitmap.recycle();
		// scaledSrcBmp.recycle();
		bmp = null;
		squareBitmap = null;
		scaledSrcBmp = null;
		return output;
	}
  
}  