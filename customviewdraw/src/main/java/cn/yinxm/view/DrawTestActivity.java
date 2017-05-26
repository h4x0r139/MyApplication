package cn.yinxm.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DrawTestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_test);

//        initView();
    }

    private void initView() {

        DrawViewTest drawTest = new DrawViewTest(getApplicationContext());
        drawTest.setMinimumWidth(600);
        drawTest.setMinimumHeight(1024);
//        drawTest.measure(View.MeasureSpec.AT_MOST, View.MeasureSpec.AT_MOST);
//        drawTest.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        drawTest.setMinimumHeight();
//        drawTest.setMinimumWidth(300);
        //通知view组件重绘
        drawTest.invalidate();
//        ViewGroup content = (ViewGroup) findViewById(R.id.content);
//        content.addView(drawTest);

    }
}
