package cn.yinxm.memoryrecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);



        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

//        textView.setOnClickListener(new Main2Activity.OnClick());
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView.setText("Main2Activity name="+Main2Activity.name+", list="+Main2Activity.list
                +", Main2Activity="+Main2Activity.activityStatic+", onclick="+Main2Activity.onClick);
    }
}
