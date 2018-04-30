package cn.yinxm.tevent.conflict.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by yinxuming on 2018/4/28.
 */

public class MyMeasureListView extends ListView {
    public MyMeasureListView(Context context) {
        super(context);
    }

    public MyMeasureListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMeasureListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST));
//        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.EXACTLY));
    }
}
