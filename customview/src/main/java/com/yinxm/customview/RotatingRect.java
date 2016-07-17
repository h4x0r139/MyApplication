package com.yinxm.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.os.Handler;


/**
 * Created by yinxm on 2016/1/19.
 */
public class RotatingRect extends View {



    public RotatingRect(Context context) {
        super(context);
        initProperties();

        Log.d("","");
    }

    public RotatingRect(Context context, AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }

    public RotatingRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProperties();
    }

    private void initProperties() {
        mpint = new Paint();
        mpint.setColor(Color.CYAN);
        drawHandler.postDelayed(drawThread, 1000);
    }

    //比较耗费资源
//    @Override
//    public void draw(Canvas canvas) {
//        System.out.println("[RotatingRect.draw]");
//        super.draw(canvas);
//        canvas.save();//
//        canvas.translate(200, 200);//移动位置
//        canvas.rotate(degrees, 50, 50);//绕中心点旋转
//        canvas.drawRect(0,0,100,100,mpint);
//        degrees++;//每执行一帧旋转角度加1
//        invalidate();//加入这句话能让draw方法不断被调用
//    }


    @Override
    public void draw(Canvas canvas) {
        System.out.println("[RotatingRect.draw]");
        super.draw(canvas);
        canvas.save();//
        canvas.translate(200, 200);//移动位置
        canvas.rotate(degrees, 50, 50);//绕中心点旋转
        canvas.drawRect(0, 0, 100, 100, mpint);
    }

    Handler drawHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {//向Handler中发送消息就会执行
            System.out.println("[drawHandler.handleMessage]degrees="+degrees);
            //执行业务代码
//            draw(mcanvas);
            invalidate();//重复调用draw方法绘制
            //循环绑定任务
            drawHandler.postDelayed(drawThread,50);
        }
    };

    Runnable drawThread = new Runnable() {

        @Override
        public void run() {
            System.out.println("[drawThread.run]degrees="+degrees);
            Message message = drawHandler.obtainMessage();

            degrees++;//每执行一帧旋转角度加1
            if (degrees > 360) {
                drawHandler.removeCallbacks(drawThread);
                degrees = 0;
            } else {
                drawHandler.sendMessage(message);
            }
        }
    };


    private Canvas mcanvas = null;
    private Paint mpint;
    private float degrees = 0;//旋转角度
}
