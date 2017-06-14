package cn.yinxm.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cn.yinxm.lib.view.progress.SpringProgressView;
import cn.yinxm.view.leavemsg.MsgPlayProgressView;

public class ProgressbarSendActivity extends AppCompatActivity {

    Button btn;
    SpringProgressView springProgressView;
    ProgressbarSendView progressView;

    MsgPlayProgressView msg_progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar_send);

        btn = (Button) findViewById(R.id.btn);
        progressView = (ProgressbarSendView) findViewById(R.id.progressbar);
        springProgressView = (SpringProgressView) findViewById(R.id.springProgressView);

        msg_progress = (MsgPlayProgressView) findViewById(R.id.msg_progress);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.updateProgress(2);

                springProgressView.setCurrentCount(springProgressView.getCurrentCount()+1);

                msg_progress.setCurrentProgress(msg_progress.getCurrentProgress()+1);

            }
        });

        progressView.setMaxProgress(100);
        progressView.setCurrentProgress(10);

        springProgressView.setMaxCount(100);
        springProgressView.setCurrentCount(50);

        msg_progress.setMaxProgress(100);
        msg_progress.setCurrentProgress(50);

    }
}
