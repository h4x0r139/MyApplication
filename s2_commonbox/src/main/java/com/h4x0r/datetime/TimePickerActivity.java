package com.h4x0r.datetime;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.h4x0r.R;

public class TimePickerActivity extends AppCompatActivity {
    private Button showTimePickerButton = null;
    //该常量用于标识DatePickerDialog
    private static final int TIME_PICKER_ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        showTimePickerButton = (Button) findViewById(R.id.showtTimePickerButton);
        showTimePickerButton.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //此方法用于显示DatePickerDialog
            showDialog(TIME_PICKER_ID);
        }
    }
    @Override
    public Dialog onCreateDialog(int id) {
       switch (id) {
           case TIME_PICKER_ID :
           return new TimePickerDialog(this,onTimeSetListener,13,24,true);
       }
        return  null;
    }
    //time设置监听器
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            System.out.println(hourOfDay+":"+minute);
        }
    };

}
