package cn.yinxm.memoryrecycle;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    public static int name = 12;
    public static List<String> list = new ArrayList<>();
    public static Activity activityStatic;
    public  Activity activity;

    public static OnClick onClick;

    TextView textView2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView2 = (TextView) findViewById(R.id.textView2);
        list.add("1");
        activity = this;
        activityStatic = this;//将activity对象的引用赋值给static变量，会引起内存泄漏

        onClick = new OnClick();

        textView2.setOnClickListener(onClick);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textView2.setText("this.list="+list+", Main3Activity list="+Main3Activity.list);
    }

    public void back(View view) {
        finish();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("yinxm", "onDestroy");
        activityStatic = null;
    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.textView:
                    Toast.makeText(Main2Activity.this, "textView", Toast.LENGTH_SHORT);
                    break;
                case R.id.textView2:
                    Toast.makeText(Main2Activity.this, "textView2", Toast.LENGTH_SHORT);
                    break;
            }
        }
    }
}
