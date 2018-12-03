package com.yinxm.customview;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class MyRect extends View {

    public MyRect(Context context) {
        super(context);
    }

    public MyRect(Context context, AttributeSet attrs) {//资源解析器访问这个构造函数，将MyRect放入布局文件中，自动调用
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        int color = ta.getColor(R.styleable.MyView_rect_color,0xffff0000);
        setBackgroundColor(color);
        ta.recycle();
    }
}
