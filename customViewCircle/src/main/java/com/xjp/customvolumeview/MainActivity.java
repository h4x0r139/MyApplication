package com.xjp.customvolumeview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.R;
import com.yinxm.customview.PlayLogoActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAdd;
    private Button buttonDelete;
    private VolumeViewLayout volumeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(this);
        volumeView = (VolumeViewLayout) findViewById(R.id.volumeView);
        volumeView.setIcon(R.drawable.icon);
        volumeView.setTitle("音乐音量");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAdd:
                volumeView.volumeUp();
                break;
            case R.id.buttonDelete:
                volumeView.volumeDown();
                break;
            case R.id.btn_play_logo:
                startActivity(new Intent(MainActivity.this, PlayLogoActivity.class));
                break;
        }
    }
}
