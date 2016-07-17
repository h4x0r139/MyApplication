package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.Login;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;

public class LoginActivity extends Activity
{
  private ImageView backImageView;
  private Context context;
  private Button denglu_button2;
  private EditText denglu_denglu;
  private EditText denglu_mima;
  private String imei = "";
  private Button login;
  private String pwd = "";
  private TextView titleName;
  private String userName = "";

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903058);
    this.imei = Utils.getPhoneId();
    this.context = this;
    Exit.getInstance().addActivity(this);
    this.titleName = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.login = ((Button)findViewById(2131427411));
    this.denglu_button2 = ((Button)findViewById(2131427412));
    this.titleName.setText("登录");
    this.denglu_denglu = ((EditText)findViewById(2131427408));
    this.denglu_mima = ((EditText)findViewById(2131427410));
    this.denglu_mima.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        EditText localEditText = (EditText)paramAnonymousView;
        if (!paramAnonymousBoolean)
        {
          localEditText.setHint(localEditText.getTag().toString());
          return;
        }
        localEditText.setTag(localEditText.getHint().toString());
        localEditText.setHint("");
      }
    });
    this.denglu_denglu.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        EditText localEditText = (EditText)paramAnonymousView;
        if (!paramAnonymousBoolean)
        {
          localEditText.setHint(localEditText.getTag().toString());
          return;
        }
        localEditText.setTag(localEditText.getHint().toString());
        localEditText.setHint("");
      }
    });
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LoginActivity.this.finish();
      }
    });
    this.login.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LoginActivity.this.userName = LoginActivity.this.denglu_denglu.getText().toString().trim();
        LoginActivity.this.pwd = LoginActivity.this.denglu_mima.getText().toString().trim();
        if (("".equals(LoginActivity.this.userName)) || (LoginActivity.this.userName == null))
        {
          Utils.showDialog(LoginActivity.this.context, "提示", "请输入用户名！", 2130837567, null);
          return;
        }
        if (("".equals(LoginActivity.this.pwd)) || (LoginActivity.this.pwd == null))
        {
          Utils.showDialog(LoginActivity.this.context, "提示", "请输入密码！", 2130837567, null);
          return;
        }
        Login localLogin = new Login(new JiaDeNetRequestTask.JiaDeNetCallback()
        {
          public void onCanceled()
          {
            WaitLoadDialog.getInstance().dismissDialog();
          }

          public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
          {
            WaitLoadDialog.getInstance().dismissDialog();
            Utils.showDialog(LoginActivity.this, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
          }

          public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
          {
            WaitLoadDialog.getInstance().dismissDialog();
            String str = paramAnonymous2JiaDeNetResponse.result.toString();
            if ((!"".equals(str)) && (!"ok".equals(str)))
            {
              Utils.showDialog(LoginActivity.this, "提示", str, 2130837514, null);
              return;
            }
            LoginActivity.this.finish();
          }
        }
        , LoginActivity.this, LoginActivity.this.imei, LoginActivity.this.userName, LoginActivity.this.pwd);
        WaitLoadDialog.getInstance().showDialog(LoginActivity.this, null, true);
        localLogin.execute(new JiaDeNetRequest[0]);
      }
    });
    this.denglu_button2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(localIntent);
        LoginActivity.this.finish();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.LoginActivity
 * JD-Core Version:    0.6.2
 */