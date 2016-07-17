package com.example.yinxm.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
    private EditText loginIdText, loginPwdText;
    private Button loginBtn;
    private TextView forgetPwdTextView;

    public static void setToolbarLogin(Activity activity){
        if(activity instanceof LoginActivity ) {
            activity.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_login);
        setToolbarLogin(this);
        //绑定控件对象
        loginIdText = (EditText) findViewById(R.id.loginIdText);
        loginPwdText = (EditText) findViewById(R.id.loginPwdText);
        loginBtn = (Button)  findViewById(R.id.loginBtn);
        forgetPwdTextView = (TextView) findViewById(R.id.forgetPwdTextView);



        //注册事件
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginId = loginIdText.getText().toString();
                String loginPwd = loginPwdText.getText().toString();
                System.out.println("id="+loginId+", pwd="+loginPwd);
                //合法校验
                // TODO: 2015/12/2  
                //http请求
                // TODO: 2015/12/2
            }
        });

        forgetPwdTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
