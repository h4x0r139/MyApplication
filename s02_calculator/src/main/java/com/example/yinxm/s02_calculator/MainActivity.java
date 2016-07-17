package com.example.yinxm.s02_calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;
    private Button calcuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);
        calcuBtn = (Button) findViewById(R.id.calcuBtn);
        calcuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1Str = num1.getText().toString();
                String num2Str = num2.getText().toString();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,ResultActivity.class);
                intent.putExtra("num1", num1Str);
                intent.putExtra("num2", num2Str);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,1,R.string.exit);
        menu.add(0, 2, 2, R.string.about);

        //super.onCreateOptionsMenu(menu);//

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

        if (id == 1) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
