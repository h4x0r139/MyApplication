package com.yinxm.screenadapt;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "yinxm";
    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        Log.d(TAG, "100 getRawSize="+getRawSize(TypedValue.COMPLEX_UNIT_DIP, 100)+", dip_100="+getIntFromDimens(R.dimen.dip_100));//100 getRawSize=300.0, dip_100=300
        Log.d(TAG,"xml sp_18="+tv1.getTextSize()+", sp_18="+getIntFromDimens(R.dimen.sp_18));
//        tv2.setTextSize( getIntFromDimens(R.dimen.sp_18));//字体会变大很多，setTextSize接收的参数默认是dp
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, getIntFromDimens(R.dimen.sp_18));//正确方式

    }

    // 方法一
    public float getRawSize(int unit, float value) {
        Resources res = this.getResources();
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics());
    }

    // 方法2，需先在values中dimens的进行设置
    public int getIntFromDimens(int index) {
        int result = this.getResources().getDimensionPixelSize(index);
        return result;
    }
}
