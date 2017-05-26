package cn.yinxm.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.yinxm.lib.view.progress.SpringProgressView;

public class ProgressbarSendActivity extends AppCompatActivity {

    Button btn;
    SpringProgressView springProgressView;
    ProgressbarSendView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar_send);

        btn = (Button) findViewById(R.id.btn);
        progressView = (ProgressbarSendView) findViewById(R.id.progressbar);
        springProgressView = (SpringProgressView) findViewById(R.id.springProgressView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.updateProgress(1);
            }
        });

        springProgressView.setMaxCount(100);
        springProgressView.setCurrentCount(50);

    }
}
