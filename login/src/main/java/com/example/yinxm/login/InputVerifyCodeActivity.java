package com.example.yinxm.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputVerifyCodeActivity extends AppCompatActivity {

    private EditText inputVerifyCodeText;
    private Button regetVerifyCodeBtn, nextStepBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_verify_code);

        inputVerifyCodeText = (EditText) findViewById(R.id.inputVerifyCodeText);
        regetVerifyCodeBtn = (Button) findViewById(R.id.regetVerifyCodeBtn);
        nextStepBtn = (Button) findViewById(R.id.nextStepBtn);

        inputVerifyCodeText.setText("1234");

        final Intent intent = getIntent();
        final String phone = intent.getStringExtra("phone");
        System.out.println("【InputVerifyCodeActivity.onCreate】 phone="+phone);

        regetVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2015/12/2 重新发送phone验证码
                System.out.print("重新发送验证码phone=" + phone);
            }
        });

        nextStepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2015/12/2 校验验证码是否正确
                String inputVerifyCode = inputVerifyCodeText.getText().toString().trim();
                //远程获取已发送的验证码
                String remoteVeriryCode = "1234";//test
                if (remoteVeriryCode != null && remoteVeriryCode.equals(inputVerifyCode)) {
                    System.out.print("验证码校验通过，跳转到设置新密码页面");
                    Intent intent1 = new Intent();
                    intent1.putExtra("inputVerifyCode", inputVerifyCode);
                    intent1.setClass(InputVerifyCodeActivity.this, NewPwdActivity.class);
                    startActivity(intent1);
                } else {
                    System.out.print("验证码校验不一致");
                    Toast.makeText(getApplicationContext(),"验证码输入错误",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_verify_code, menu);
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
