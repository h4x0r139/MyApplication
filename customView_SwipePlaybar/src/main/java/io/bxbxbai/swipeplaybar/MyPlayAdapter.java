package io.bxbxbai.swipeplaybar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.Queue;

import butterknife.ButterKnife;

/**
 * Created by mAC on 2016/5/5.
 */
public class MyPlayAdapter extends PagerAdapter {

    private static final int NUM_SONGS = 10;
    private static final int ANIMATOR_DURATION = 1000 * 10;

    private LayoutInflater mInflater;

    private Queue<View> mReusableViews;


    Context context;
    public MyPlayAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mReusableViews = new ArrayDeque<>(NUM_SONGS);
        this.context=context;
    }

    @Override
    public int getCount() {
        return NUM_SONGS;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
            mReusableViews.add((View) object);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mReusableViews.poll();
        if (v == null) {
            v = mInflater.inflate(R.layout.mymusic, container, false);
        }
        bindData(v, position);
        container.addView(v);
        setAnimator(v);
        return v;
    }

    private void bindData(View v, final int position) {
        ImageView img_play=(ImageView)ButterKnife.findById(v,R.id.img_play);
        RoundProgressBar player_seekbar=(RoundProgressBar)ButterKnife.findById(v,R.id.player_seekbar);
        ImageButton btn_previous=(ImageButton)ButterKnife.findById(v,R.id.btn_previous);
        ImageButton btn_next=(ImageButton)ButterKnife.findById(v,R.id.btn_next);

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"postion--"+position,Toast.LENGTH_SHORT).show();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"postion--"+position,Toast.LENGTH_SHORT).show();
            }
        });
//        TextView songName = ButterKnife.findById(v, R.id.tv_song_name);
//        songName.setText("Try - " + position);
//
//        ImageView artistImage = ButterKnife.findById(v, R.id.iv_artist_cover);
        if (position % 2 == 1) {
            player_seekbar.setImageResource(R.drawable.adele);
        } else {
            player_seekbar.setImageResource(R.drawable.bxbxbai);
        }
    }

    @Override
    public float getPageWidth(int position) {
        return 1.0f;
    }

    public static void setAnimator(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view.findViewById(R.id.player_seekbar), "rotation", 0f, 360f);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setDuration(ANIMATOR_DURATION);
        animator.setInterpolator(new LinearInterpolator());

        view.setTag(R.id.tag_animator, animator);
    }
}

