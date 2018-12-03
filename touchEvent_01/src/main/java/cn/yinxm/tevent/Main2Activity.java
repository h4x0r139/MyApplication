package cn.yinxm.tevent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import cn.yinxm.lib.utils.log.LogUtil;


public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "您点击了我", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.touchChild).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "这是child点击事件", Toast.LENGTH_SHORT).show();
            }
        });
    }

    float startX, startY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.e("[Main2Activity.dispatchTouchEvent] action=" + ev.getAction() + ", x=" + ev.getX() + ", y=" + ev.getY());
        boolean flag = false;
       /* switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                isSlideRigh = false;
                break;
            case MotionEvent.ACTION_UP:

                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                LogUtil.d("startX=" + startX + ", endX=" + endX + ", startY=" + startY + ", endY=" + endY);
                //判断滑动方向
                if (Math.abs(endX - startX) > Math.abs(endY - startY)) {
                    //x方向水平滑动
//                    LogUtil.d("x方向水平滑动");
                    //判断是否是右滑
                    if (endX - startX < -20) {
                        LogUtil.d("水平左滑");
//                        if (listener != null) {
//                            listener.onSlideRight();
//                        }
                        flag = true;
                    }

                } else {
                    //y方向上垂直滑动
                }
                break;
        }*/
        if (flag) {
            LogUtil.d("[Main2Activity.dispatchTouchEvent]  action=" + ev.getAction()+", true，交给onTouch处理");
            return flag;
        } else {
            LogUtil.d("[Main2Activity.dispatchTouchEvent]  action=" + ev.getAction()+", false，转给下级控件处理");
            return super.dispatchTouchEvent(ev);
        }
    }

    boolean isSlideRigh = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.d("[Main2Activity.onTouchEvent] action=" + event.getAction() + ", x=" + event.getX() + ", y=" + event.getY());
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_UP:
//                isSlideRigh = false;
//                break;
//        }
        boolean flag = super.onTouchEvent(event);
        LogUtil.d("[Main2Activity.onTouchEvent] flag="+flag);
        flag = true;
        LogUtil.d("[Main2Activity.onTouchEvent] flag="+flag);
        return flag;
    }
}
