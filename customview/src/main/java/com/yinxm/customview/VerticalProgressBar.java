package com.yinxm.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/** 
 *  
 * @author http://blog.csdn.net/lovehong0306/article/details/7803702 
 * 
 */  
public class VerticalProgressBar extends ProgressBar
{  
  
    public VerticalProgressBar(Context context, AttributeSet attrs, int defStyle)
    {  
        super(context, attrs, defStyle);  
        // TODO Auto-generated constructor stub  
    }  
  
    public VerticalProgressBar(Context context, AttributeSet attrs)
    {  
        super(context, attrs);  
        // TODO Auto-generated constructor stub  
    }  
  
    public VerticalProgressBar(Context context)
    {  
        super(context);  
        // TODO Auto-generated constructor stub  
    }  
  
    @Override
    protected synchronized void onDraw(Canvas canvas)
    {  
        // TODO Auto-generated method stub  
        canvas.rotate(-90);//��ת90�ȣ���ˮƽProgressBar������  
        canvas.translate(-getHeight(), 0);//��������ת��õ���VerticalProgressBar�Ƶ���ȷ��λ��,ע�⾭��ת<span style="white-space:pre">                     </span>    ����ֵ����  
        super.onDraw(canvas);  
    }  
  
    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);  
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());//�������ֵ  
    }  
      
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(h, w, oldw, oldh);//�������ֵ  
    }  
}  
