package com.h4x0r.datetime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

import com.h4x0r.R;

public class DatePickerActivity extends AppCompatActivity {
    private Button showDatePickerButton = null;
    //该常量用于标识DatePickerDialog
    private static final int DATE_PICKER_ID = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);
        showDatePickerButton = (Button) findViewById(R.id.showDatePickerButton);
        showDatePickerButton.setOnClickListener(new ButtonListener());
    }

    private class ButtonListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            //此方法用于显示DatePickerDialog
            showDialog(DATE_PICKER_ID);
        }

    }
    //监听器，用户监听用户点下DatePikerDialog的set按钮时，所设置的年月日
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            System.out.println(year + "-" + monthOfYear + "-" + dayOfMonth);
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, onDateSetListener, 2010, 11, 25);
        }
        return null;
    }
}
