package cn.yinxm.anim;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import cn.yinxm.lib.utils.log.LogUtil;

public class PropertyAnimActivity extends Activity {

    View img;
    CircleView circleView;
    Button btn_objAnim, btn_valueAnim;
    Button btn_objAnimSet, btn_xmlAnimSet;
    //ValueAnimator高级用法
    Button btn_customTypeEvalue;

    ObjectAnimator mAnimator;
    Button btn_animStop;
    boolean isPaused = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);
        circleView = (CircleView) findViewById(R.id.circleView);
        img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("img onClick");
            }
        });

        btn_animStop = (Button) findViewById(R.id.btn_animStop);
        btn_animStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnimator != null) {
                    if (isPaused) {
                        mAnimator.start();
                        isPaused = false;
                    }else {
                        mAnimator.cancel();
                        isPaused = true;
                    }
                }
            }
        });
        btn_objAnim = (Button) findViewById(R.id.btn_objAnim);
        btn_objAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectAnimator(img);
            }
        });
        btn_valueAnim = (Button) findViewById(R.id.btn_valueAnim);
        btn_valueAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator(img);
            }
        });

        //组合动画
        btn_objAnimSet = (Button) findViewById(R.id.btn_objAnimSet);
        btn_objAnimSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objAnimSet(img);
            }
        });

        //xml 组合动画
        btn_xmlAnimSet = (Button) findViewById(R.id.btn_xmlAnimSet);
        btn_xmlAnimSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xmlAnimSet(img);
            }
        });

        //自定义估值器动画
        btn_customTypeEvalue = (Button) findViewById(R.id.btn_customTypeEvalue);
        btn_customTypeEvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PropertyAnimActivity.this, CustomTypeEvaluatorActivity.class));
            }
        });
    }

    /**
     * 属性动画实现方式1：通过ObjectAnimator
     *
     * @param view
     */
    private void objectAnimator(View view) {//重复执行该方法，也会从刚开始的位置执行
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, 500);//初始值，最终值
        objectAnimator.setDuration(10000);
        //可选动画运行时的状态监听
        objectAnimator.addListener(mAnimatorListener);//监听动画状态改变onAnimationStart、onAnimationEnd、onAnimationCancel、onAnimationRepeat
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LogUtil.d("onAnimationUpdate getAnimatedValue=" + animation.getAnimatedValue());

            }
        });
        //可选其他参数
        objectAnimator.setRepeatCount(3);
//        objectAnimator.setRepeatMode(ValueAnimator.RESTART);//默认，重新执行
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);//反向执行
        mAnimator = objectAnimator;
        //开始动画
        objectAnimator.start();
    }

    /**
     * 属性动画实现方式2：ValueAnimator 在回调里面改变控件属性值
     *
     * @param view
     */
    private void valueAnimator(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 500);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
                float value = (float) animation.getAnimatedValue();
                LogUtil.d("onAnimationUpdate value=" + value);
//                params.width = (int) value;//改变了布局的宽度，并不是平移动画
                params.leftMargin = (int) value;
                view.requestLayout();//改变了控件宽度重新布局
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        valueAnimator.start();
    }

    /**
     * 组合动画
     *
     * @param view
     */
    private void objAnimSet(View view) {
        ObjectAnimator animatorTranX = ObjectAnimator.ofFloat(view, "translationX", 0f, 500f);
        ObjectAnimator animatorTranY = ObjectAnimator.ofFloat(view, "translationY", 0, 300);
        ObjectAnimator animatorRotation = ObjectAnimator.ofFloat(view, "rotation", 0f, 360f, 0f);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        //先animatorTranX 完了再平移旋转
        animatorSet.play(animatorTranX).with(animatorTranY);
        animatorSet.play(animatorAlpha).with(animatorRotation).after(animatorTranY);
        animatorSet.setDuration(3000);
        animatorSet.start();
    }

    /**
     * xml 组合动画
     *
     * @param view
     */
    private void xmlAnimSet(View view) {
        Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(), R.anim.anim_set_custom);
        animator.setTarget(view);
        animator.start();
    }



    Animator.AnimatorListener mAnimatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            LogUtil.d("onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            LogUtil.d("onAnimationEnd");

        }

        @Override
        public void onAnimationCancel(Animator animation) {
            LogUtil.d("onAnimationCancel");

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            LogUtil.d("onAnimationRepeat");

        }
    };
}
