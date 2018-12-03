package com.example.yinxm.s11_timepicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    private TimePicker firstTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstTimePicker = (TimePicker) findViewById(R.id.firstTimePicker);
        TimeListener timeListener = new TimeListener();
        firstTimePicker.setOnTimeChangedListener(timeListener);

        //设置、获取当前时间
        firstTimePicker.getHour();
        firstTimePicker.getMinute();
        System.out.println();

    }

    class TimeListener implements TimePicker.OnTimeChangedListener {
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            System.out.println("id="+view.getId()+", hourofDay="+hourOfDay+", minute="+minute);
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
