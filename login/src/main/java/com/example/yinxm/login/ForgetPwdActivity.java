package com.example.yinxm.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPwdActivity extends Activity {
    private EditText phoneText;
    private Button getSmsVerifyCodeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);

        phoneText = (EditText) findViewById(R.id.phoneText);
        getSmsVerifyCodeBtn = (Button) findViewById(R.id.getSmsVerifyCodeBtn);

        getSmsVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取手机号
                String phoneNum = phoneText.getText().toString().trim();
                System.out.println("phoneNum="+phoneNum);
                //http请求
                // TODO: 2015/12/2

                //跳转到下一页
                Intent intent = new Intent();
                intent.putExtra("phone", phoneNum);
                intent.setClass(ForgetPwdActivity.this, InputVerifyCodeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forget_pwd, menu);
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
