package com.h4x0r;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.h4x0r.autocomplete.AutoCompleteTextActivity1;
import com.h4x0r.datetime.DatePickerActivity2;
import com.h4x0r.spinner.SpinnerActivity;

public class MainActivity extends AppCompatActivity {
    private Button spinnerBtn, datePicker01,autoCompleteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerBtn = (Button) findViewById(R.id.testSpinnerBtn);
        spinnerBtn.setOnClickListener(new SpinnerListener());

        datePicker01 = (Button) findViewById(R.id.datePickerTest);
        datePicker01.setOnClickListener(new DatePickerListener());

        autoCompleteText = (Button) findViewById(R.id.autoCompleteText);
        autoCompleteText.setOnClickListener(new AutoCompleteListener());

    }

    class SpinnerListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent1 =  new Intent();
            intent1.setClass(MainActivity.this, SpinnerActivity.class);
            startActivity(intent1);
        }
    }

    class DatePickerListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent1 =  new Intent();
            intent1.setClass(MainActivity.this, DatePickerActivity2.class);
            startActivity(intent1);
        }
    }

    class AutoCompleteListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent1 =  new Intent();
            intent1.setClass(MainActivity.this, AutoCompleteTextActivity1.class);
            startActivity(intent1);
        }
    }




}
