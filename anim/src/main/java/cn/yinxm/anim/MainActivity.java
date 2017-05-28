package cn.yinxm.anim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img, anim1, anim2, anim3, anim4, anim5, anim6, anim7, anim8;

    private int amp_direct;//水平垂直，动画偏移幅度
    private int amp_diagonal;//斜角偏移幅度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.tv_i_want_leave_msg);

        anim1 = (ImageView) findViewById(R.id.anim1);
        anim2 = (ImageView) findViewById(R.id.anim2);
        anim3 = (ImageView) findViewById(R.id.anim3);
        anim4 = (ImageView) findViewById(R.id.anim4);
        anim5 = (ImageView) findViewById(R.id.anim5);
        anim6 = (ImageView) findViewById(R.id.anim6);
        anim7 = (ImageView) findViewById(R.id.anim7);
        anim8 = (ImageView) findViewById(R.id.anim8);

        amp_direct = (int) getResources().getDimension(R.dimen.dip_anim);
        amp_diagonal = amp_direct*2/3;
        Log.d("yinxm", "amp_direct="+amp_direct+", amp_diagonal="+amp_diagonal);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                new AsyncTask<Void,Void,Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        return null;
//                    }
//
//                }.execute();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startAnim(1, anim1);
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startAnim(2, anim2);
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startAnim(3, anim3);
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        startAnim(4, anim4);
                    }
                }).start();

//                startAnim(5, anim5);
//                startAnim(6, anim6);
//                startAnim(7, anim7);
//                startAnim(8, anim8);
            }
        });

    }

    public void startAnim(int num, final View view) {

        float fromXDelta = 0, toXDelta = 0, fromYDelta = 0, toYDelta = 0;

        //水平垂直方向，先上下，后左右
        //斜角方向，先右上-左下，后左上-右下
//        switch (num) {
//            case 1:
//                //left
//                toXDelta = -amp_direct;
//                break;
//            case 2:
//                //right
//                toXDelta = amp_direct;
//                break;
//            case 3:
//                //top
//                fromYDelta = -amp_direct;
//                break;
//            case 4:
//                //bottom
//                fromYDelta = amp_direct;
//                break;
//            case 5:
//                //左上
//                toXDelta = -amp_diagonal;
//                toYDelta = -amp_diagonal;
//                break;
//            case 6:
//                //右上
//                fromXDelta = amp_diagonal;
//                fromYDelta = -amp_diagonal;
//                break;
//            case 7:
//                //左下
//                fromXDelta = -amp_diagonal;
//                fromYDelta = amp_diagonal;
//                break;
//            case 8:
//                //右下
//                toXDelta = amp_diagonal;
//                toYDelta = amp_diagonal;
//                break;
//        }

        switch (num) {
            case 1:
                //右上-左下
                fromXDelta = amp_diagonal;
                fromYDelta = -amp_diagonal;
                toXDelta = -amp_diagonal;
                toYDelta = amp_diagonal;
                break;
            case 2:
                //左上-右下
                fromXDelta = amp_diagonal;
                fromYDelta = amp_diagonal;
                toXDelta = -amp_diagonal;
                toYDelta = -amp_diagonal;
                break;
            case 3:
                //水平
                fromXDelta = amp_direct;
                toXDelta = -amp_direct;
                break;
            case 4:
                //垂直
                fromYDelta = amp_direct;
                toYDelta = -amp_direct;
                break;

        }

        
        //视图 位移动画
        final TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(3000);
        translateAnimation.setFillAfter(true);//填充位置,看起来停留动画结束位置
        translateAnimation.setRepeatMode(Animation.REVERSE);//相反方式重复动画
//        translateAnimation.setRepeatMode(Animation.RESTART);//重复动画 - 重新开始
        translateAnimation.setRepeatCount(Integer.MAX_VALUE);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.startAnimation(translateAnimation);
            }
        });


    }


}
