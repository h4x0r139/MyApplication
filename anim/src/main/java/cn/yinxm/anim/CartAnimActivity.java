package cn.yinxm.anim;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加到购物车动画
 */
public class CartAnimActivity extends Activity {
    private RecyclerView mRv;
    List<Integer> mList;
    private ImageView mCart;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_anim);
        mRl = (RelativeLayout) findViewById(R.id.rl);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mCart = (ImageView) findViewById(R.id.cart);
        mList = new ArrayList<>();
        mList.add(R.mipmap.icon1);
        mList.add(R.mipmap.icon2);
        mList.add(R.mipmap.icon3);
        mList.add(R.mipmap.icon1);
        mList.add(R.mipmap.icon2);
        mList.add(R.mipmap.icon3);
        mList.add(R.mipmap.icon1);
        mList.add(R.mipmap.icon2);
        mList.add(R.mipmap.icon3);
        mList.add(R.mipmap.icon1);
        mList.add(R.mipmap.icon2);
        mList.add(R.mipmap.icon3);
        CommonAdapter<Integer> mAdapter = new CommonAdapter<Integer>(this, R.layout.item, mList) {
            @Override
            protected void convert(final ViewHolder holder, Integer integer, int position) {
                holder.setImageResource(R.id.iv, integer);
                final ImageView imageView = holder.getView(R.id.iv);
                TextView view = holder.getView(R.id.buy);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AddCartAnimation.AddToCart(imageView, mCart, CartAnimActivity.this, mRl, 1);
                    }
                });
            }
        };
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setAdapter(mAdapter);
    }


//    /**
//     * 运动的控件
//     *
//     * @return
//     */
//    public Bitmap getAddDrawBitMap() {
//        ImageView text = new ImageView(context);
//        // 运动的控件，样式可以自定义
//        text.setBackgroundResource(R.drawable.xiaohongdian_shangping);
//        return convertViewToBitmap(text);
//    }
//
//    /**
//     * 创建动画层
//     */
//    private ViewGroup createAnimLayout() {
//        ViewGroup rootView = (ViewGroup) ((Activity) context).getWindow().getDecorView();
//        LinearLayout animLayout = new LinearLayout(context);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.MATCH_PARENT);
//        animLayout.setLayoutParams(lp);
//        animLayout.setId(Integer.MAX_VALUE);
//        animLayout.setBackgroundResource(android.R.color.transparent);
//        rootView.addView(animLayout);
//        return animLayout;
//    }
//
//    private View addViewToAnimLayout(final ViewGroup vg, final View view, int[] location) {
//        int x = location[0];
//        int y = location[1];
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.leftMargin = x;
//        lp.topMargin = y;
//        view.setLayoutParams(lp);
//        return view;
//    }
//
//    /**
//     * // 开始执行动画
//     * @param v
//     * @param start_location
//     */
//    private void setAnim(final View v, int[] start_location) {
//        anim_mask_layout = null;
//        anim_mask_layout = createAnimLayout();
//        // 把动画小球添加到动画层
//        anim_mask_layout.addView(v);
//        final View view = addViewToAnimLayout(anim_mask_layout, v, start_location);
//        // 这是用来存储动画结束位置的X、Y坐标
//        int[] end_location = new int[2];
//        // rl_gouwuche是小球运动的终点 一般是购物车图标
//        rl_gouwuche.getLocationInWindow(end_location);
//
//        // 计算位移
//        int endX = 0 - start_location[0] + 40;// 动画位移的X坐标
//        int endY = end_location[1] - start_location[1];// 动画位移的y坐标
//        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
//        translateAnimationX.setInterpolator(new LinearInterpolator());
//        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
//        translateAnimationX.setFillAfter(true);
//
//        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
//        translateAnimationY.setInterpolator(new AccelerateInterpolator());
//        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
//        translateAnimationX.setFillAfter(true);
//
//        AnimationSet set = new AnimationSet(false);
//        set.setFillAfter(false);
//        set.addAnimation(translateAnimationY);
//        set.addAnimation(translateAnimationX);
//        set.setDuration(800);// 动画的执行时间
//        view.startAnimation(set);
//        // 动画监听事件
//        set.setAnimationListener(new Animation.AnimationListener() {
//            // 动画的开始
//            @Override
//            public void onAnimationStart(Animation animation) {
//                v.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                // TODO Auto-generated method stub
//            }
//
//            // 动画的结束
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                v.setVisibility(View.GONE);
//            }
//        });
//
//    }
//
//    /**
//     * 将定义的view装换成 bitmap格式
//     *
//     * @param view
//     * @return
//     */
//    public Bitmap convertViewToBitmap(View view) {
//        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//        view.buildDrawingCache();
//        Bitmap bitmap = view.getDrawingCache();
//        return bitmap;
//    }
}
