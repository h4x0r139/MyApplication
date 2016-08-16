package com.yika.viewpager_play;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private List<View> viewList;
    private  int position =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);

        LayoutInflater inflater = LayoutInflater.from(this);
        viewList = new ArrayList<>();
        for (int i=0; i<6; i++) {
            View view = inflater.inflate(R.layout.layout_music, null);
            RoundImageViewByXfermode roundImageIcon = (RoundImageViewByXfermode) view.findViewById(R.id.img_audioIcon);
            switch (i) {
                case 0:
                    roundImageIcon.setImageResource(R.mipmap.number01);
                    break;
                case 1:
                    roundImageIcon.setImageResource(R.mipmap.number02);
                    break;
                case 2:
                    roundImageIcon.setImageResource(R.mipmap.number03);
                    break;
                case 3:
                    roundImageIcon.setImageResource(R.mipmap.number04);
                    break;
                case 4:
                    roundImageIcon.setImageResource(R.mipmap.number05);
                    break;
                case 5:
                    roundImageIcon.setImageResource(R.mipmap.number06);
                    break;

            }
            RoundProgress roundProgress = (RoundProgress) view.findViewById(R.id.player_seekbar);
            roundProgress.setMax(100);
            roundProgress.setProgress(50);
            viewList.add(view);
        }
        PlayViewPagerAdapter playAdapter = new PlayViewPagerAdapter(viewList);
        viewPager.setAdapter(playAdapter);
        PlaybarPagerTransformer transformer = new PlaybarPagerTransformer();
        viewPager.setPageTransformer(true, transformer);
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(position);

        ((Button) findViewById(R.id.previous)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yika", "[onClick previous]，mposition="+position+" previous="+(position-1));
                if ((position-1) >= 0) {
                    position = position -1;
//                viewPager.setCurrentItem(position, false);
                    viewPager.setCurrentItem(position);
                }
            }
        });
        ((Button) findViewById(R.id.next)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yika", "[onClick next]，mposition="+position+" next="+(position+1));
                if ((position + 1) < viewList.size()) {
                    position = position + 1;
//                viewPager.setCurrentItem(position, false);
                    viewPager.setCurrentItem(position);
                }
            }
        });

        findViewById(R.id.goto2Activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        findViewById(R.id.goto3Activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        });
     }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {//当页面被滑动时调用
//        System.out.println("[onPageScrolled]");
    }

    @Override
    public void onPageSelected(int position) {//新的页面被选中时调用
//        System.out.println("[onPageSelected]，position="+position);
        Log.d("yika", "[onPageSelected]，position="+position+", mposition="+this.position);
        this.position = position;

    }

    @Override
    public void onPageScrollStateChanged(int state) {//当滑动状态改变时调用
//        System.out.println("[onPageScrollStateChanged]");
//        Log.d("yika", "[onPageScrollStateChanged]，state="+state);

    }

    public void gotoRotateAnimationActivity(View view) {
        startActivity(new Intent(MainActivity.this, RotateAnimationActivity.class));

    }

    public void gotoMain3Activity(View view) {
        startActivity(new Intent(MainActivity.this, Main3Activity.class));
    }

    public void gotoMain2Activity(View view) {
        startActivity(new Intent(MainActivity.this, Main2Activity.class));

    }

    public void back(View view) {
        finish();
    }
}
