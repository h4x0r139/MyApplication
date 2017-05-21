package com.example.yinxm.s06_radio;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * 在RadioGroup中包含RadioButton以外的布局，回导致RadioGroup互斥选中效果失效
 */
public class CustomRadioButtonActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_radio_button);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Log.d("yinxm", "group="+group+", checkedId="+checkedId);
            }
        });

        RadioButton btn1 = (RadioButton) findViewById(R.id.btn1);
        RadioButton btn2 = (RadioButton) findViewById(R.id.btn2);
        RadioButton btn3 = (RadioButton) findViewById(R.id.btn3);

        btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("yinxm", "btn1 buttonView="+buttonView+", isChecked="+isChecked);

            }
        });

        btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("yinxm", "btn2 buttonView="+buttonView+", isChecked="+isChecked);

            }
        });

        btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("yinxm", "btn3 buttonView="+buttonView+", isChecked="+isChecked);

            }
        });

    }
}
