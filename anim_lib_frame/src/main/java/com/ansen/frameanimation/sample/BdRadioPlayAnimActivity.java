package com.ansen.frameanimation.sample;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.ansen.frameanimation.R;

/**
 *      Caused by: java.lang.OutOfMemoryError: Failed to allocate a 9000012 byte allocation with 2616576 free bytes and 2MB until OOM
 */
public class BdRadioPlayAnimActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bd_radio_play_anim);

        ImageView imageView = findViewById(R.id.f_play_fragment_visualizer);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();
    }
}
