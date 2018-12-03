package com.example.yinxm.s12_progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar firstBar, secondBar;
    private Button myButton;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstBar = (ProgressBar) findViewById(R.id.firstBar);
        secondBar = (ProgressBar) findViewById(R.id.secondBar);
        myButton = (Button) findViewById(R.id.myButton);

        OnButtonClickListener buttonClickListener = new OnButtonClickListener();
        myButton.setOnClickListener(buttonClickListener);
    }

    class OnButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (i == 0) {
                firstBar.setVisibility(ProgressBar.VISIBLE);
                secondBar.setVisibility(ProgressBar.VISIBLE);
                i += 10;
            } else if (i < firstBar.getMax()) {
                i += 10;
                firstBar.setProgress(i);
                firstBar.setSecondaryProgress(i+10);
            } else {
                firstBar.setVisibility(ProgressBar.GONE);
                secondBar.setVisibility(ProgressBar.GONE);
                i = 0;
            }
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
