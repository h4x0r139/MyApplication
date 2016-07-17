package com.example.yinxm.s05_checkbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MainActivity extends AppCompatActivity {

    private CheckBox mondayBox;
    private CheckBox tusdayBox;
    private CheckBox thursdayBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mondayBox = (CheckBox)findViewById(R.id.mondayId);
        tusdayBox = (CheckBox)findViewById(R.id.tusedayId);
        thursdayBox = (CheckBox)findViewById(R.id.thursdayId);

        //添加监听事件
//        OnBoxClickListener boxClickListener = new OnBoxClickListener();
//        mondayBox.setOnClickListener(boxClickListener);
//        tusdayBox.setOnClickListener(boxClickListener);
//        thursdayBox.setOnClickListener(boxClickListener);
        OnBoxCheckedChangeListener boxCheckedChangeListener = new OnBoxCheckedChangeListener();
        mondayBox.setOnCheckedChangeListener(boxCheckedChangeListener);
        tusdayBox.setOnCheckedChangeListener(boxCheckedChangeListener);
        thursdayBox.setOnCheckedChangeListener(boxCheckedChangeListener);
    }

    class OnBoxClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            CheckBox box = (CheckBox) v;
            System.out.println("按钮"+box.getId()+", "+box.getText()+"被点击了, isChecked="+box.isChecked());

        }
    }

    class OnBoxCheckedChangeListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            System.out.println(buttonView.getId()+", "+ buttonView.getText()+" checked:"+isChecked);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
