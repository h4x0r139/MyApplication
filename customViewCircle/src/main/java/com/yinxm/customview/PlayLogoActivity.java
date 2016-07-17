package com.yinxm.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.R;

public class PlayLogoActivity extends AppCompatActivity {

    PlayLogoLayout playLogoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_logo);
        playLogoLayout = (PlayLogoLayout) findViewById(R.id.playLogoLayout);
    }

    int progress = 50;

    public void addProgressClick(View view) {
        playLogoLayout.updateProgress(progress++);
    }
}
