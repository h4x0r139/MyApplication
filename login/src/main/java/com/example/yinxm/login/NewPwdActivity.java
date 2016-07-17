package com.example.yinxm.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewPwdActivity extends AppCompatActivity {
    private Button finishBtn;
    private EditText pwdText, repwdText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pwd);

        pwdText = (EditText) findViewById(R.id.pwdText);
        repwdText = (EditText) findViewById(R.id.repwdText);
        finishBtn = (Button) findViewById(R.id.finishBtn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = pwdText.getText().toString();
                String repwd = repwdText.getText().toString();
                System.out.println("pwd=" + pwd);
                //清空数据
                pwdText.setText("");
                repwdText.setText("");

                if (pwd==null || pwd.equals("")) {
                    Toast.makeText(getApplicationContext(),"密码不能为空", Toast.LENGTH_SHORT).show();
                } else if(pwd.equals(repwd)) {
                    System.out.println("两次密码一致，开始请求密码重置服务");
                    // TODO: 2015/12/2 密码重置
                    Toast.makeText(getApplicationContext(),"密码设置成功", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("两次输入密码不一致，请重新输入");
                    Toast.makeText(getApplicationContext(), "两次输入密码不一致，请重新输入", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_pwd, menu);
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
