package com.example.yinxm.s14_handler;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class Main1Activity extends AppCompatActivity {

    private ProgressBar bar1;
    private Button startButton1;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        bar1 = (ProgressBar) findViewById(R.id.bar1);
        startButton1 = (Button) findViewById(R.id.startButton1);

        startButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(r);
                t.start();
//                handler.postDelayed(r,2000);//不会新启一个线程，会直接调用run方法
            }
        });

//        setContentView(R.layout.main);

        System.out.println("activity--->" + Thread.currentThread().getId());
        System.out.println("activityname--->" + Thread.currentThread().getName());
    }

    Runnable r = new Runnable(){
        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("handler--->" + Thread.currentThread().getId());
            System.out.println("handlername--->" + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
