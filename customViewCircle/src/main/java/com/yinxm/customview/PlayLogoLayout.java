package com.yinxm.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.R;

/**
 * Created by yinxm on 2016/4/24.
 */
public class PlayLogoLayout extends FrameLayout{

    //中心坐标
    public float centerX, centerY;
    private int mWidth,mHeight;


    private CircleImageView circleImageView;
    private CircleProgress circelProgress;

    public PlayLogoLayout(Context context) {
        this(context, null);
    }

    public PlayLogoLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayLogoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.play_logo_layout, this);
        circelProgress = (CircleProgress) view.findViewById(R.id.circelProgress);
//        circelProgress.setVisibility(GONE);
    }

    private void initData(){

    }

    //重写测量大小的onMeasure方法和绘制View的核心方法onDraw()
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i("my", "PlayLogoLayout.onMeasure");
        mWidth = getRealSize(widthMeasureSpec);
        mHeight = getRealSize(heightMeasureSpec);
        centerX = mWidth / 2;
        centerY = mHeight / 2;
        Log.d("my", "PlayLogoLayout onMeasure mWidth="+mWidth+", centerX="+centerX+", mHeight="+mHeight+", centerY="+centerY);
        setMeasuredDimension(mWidth, mHeight);

        int totalChild = getChildCount();
        Log.d("my", "totalChild="+totalChild);
/*        for (int i=0; i<totalChild; i++) {
            View childView = getChildAt(i);
            Log.d("my", "child i="+i+", id="+childView.getId());
            measureChild(childView, mWidth-50, mHeight-50);
        }*/
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    public int getRealSize(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        Log.d("my", "PlayLogoLayout mode=" + mode + ", size="+size);
        if (mode == MeasureSpec.EXACTLY) {//精确值模式,指定100dp
            Log.d("my", " MeasureSpec.EXACTLY 模式 size="+size);
            result = size;
        }else if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {//最大值模式，或者没有指定宽高——》默认值
            Log.d("my", "MeasureSpec.AT_MOST模式 size=200");
            result = 200;
        }
        return result;
    }


    public void updateProgress(int progress) {
        circelProgress.updateProgress(progress);
    }

}
