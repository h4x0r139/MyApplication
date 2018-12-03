package com.yika.viewpager_play;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import java.util.List;

/**
 * Created by yinxm on 2016/5/16.
 */
public class PlayViewPagerAdapter  extends PagerAdapter{
    List<View> viewList;
//    private Queue<View> reusableViews;
    Activity activity;
    PlayViewPagerAdapter(List<View> viewList) {
//        viewList = new ArrayList<>(10);
//        reusableViews = new ArrayDeque<>(10);
        this.viewList = viewList;
    }
    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("yika", "destroyItem position="+position+", size="+viewList.size());
        container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("yika", "instantiateItem position="+position+", size="+viewList.size());
        View view = null;
//        if (viewList.size() <= position || viewList.get(position) == null ) {
//            view = View.inflate(activity,R.layout.layout_music, container);
////            RoundProgress roundProgress = (RoundProgress) view.findViewById(R.id.progressbar);
//            ImageView roundProgress = (ImageView) view.findViewById(R.id.progressbar);
//            switch (position) {
//                case 0:
//                    roundProgress.setImageResource(R.drawable.p40);
//                    break;
//                case 1:
//                    roundProgress.setImageResource(R.drawable.p43);
//                    break;
//                case 2:
//                    roundProgress.setImageResource(R.drawable.p83);
//                    break;
//                case 3:
//                    roundProgress.setImageResource(R.drawable.p85);
//                    break;
//                case 4:
//                    roundProgress.setImageResource(R.drawable.p89);
//            }
//        } else  {
            view = viewList.get(position);
//        }
        container.addView(view);
        setAnimator(view);
        return view;
    }

    public static void setAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view.findViewById(R.id.img_audioIcon), "rotation", 0f, 360f);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());

        view.setTag(R.id.tag_animator, animator);
    }

/*    public void startAnimator() {
        ObjectAnimator animator =  ObjectAnimator.ofFloat(view.findViewById(R.id.img_audioIcon), "rotation", 0f, 360f);
        if (animator != null) {
            animator.start();
        }
    }

    public void pauseAnimator() {
        ObjectAnimator animator = (ObjectAnimator) page.getTag(R.id.tag_animator);
        if (animator != null) {
//            animator.pause();
            animator.end();
        }
    }*/
}
