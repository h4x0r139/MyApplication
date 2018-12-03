package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.PassMod;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.List;

public class PasswordChangetActivity extends Activity
{
  private ImageView backImageView;
  Button button_save;
  Context context;
  private String imei = "";
  EditText password_new;
  EditText password_new_second;
  EditText passwordold;
  private TextView titlename;
  private String userid = "";

  private void showDialog(String paramString1, String paramString2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(17301659);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setPositiveButton("确认", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        PasswordChangetActivity.this.finish();
      }
    });
    localBuilder.create().show();
  }

  public void addButtonEvent()
  {
    this.button_save.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str1 = PasswordChangetActivity.this.passwordold.getText().toString().trim();
        String str2 = PasswordChangetActivity.this.password_new.getText().toString().trim();
        String str3 = PasswordChangetActivity.this.password_new_second.getText().toString().trim();
        if ("".equals(str1))
        {
          PasswordChangetActivity.this.showDialog("温馨提示 ", "请输入原密码。");
          return;
        }
        if ("".equals(str2))
        {
          PasswordChangetActivity.this.showDialog("温馨提示 ", "请输入新密码。");
          return;
        }
        if ("".equals(str3))
        {
          PasswordChangetActivity.this.showDialog("温馨提示 ", "请再次输入新密码。");
          return;
        }
        if (!str2.equals(str3))
        {
          PasswordChangetActivity.this.showDialog("温馨提示 ", "两次新密码不一样，请重新输入。");
          PasswordChangetActivity.this.password_new_second.setText("");
          return;
        }
        PassMod localPassMod = new PassMod(new JiaDeNetRequestTask.JiaDeNetCallback()
        {
          public void onCanceled()
          {
            WaitLoadDialog.getInstance().dismissDialog();
          }

          public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
          {
            WaitLoadDialog.getInstance().dismissDialog();
            Utils.showDialog(PasswordChangetActivity.this, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
          }

          public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
          {
            WaitLoadDialog.getInstance().dismissDialog();
            String str = (String)paramAnonymous2JiaDeNetResponse.result;
            if ((!"".equals(str)) && (!"ok".equals(str)))
            {
              Utils.showDialog(PasswordChangetActivity.this, "提示", str, 2130837514, null);
              return;
            }
            Utils.showDialog(PasswordChangetActivity.this, "提示", "修改成功", 2130837514, null);
          }
        }
        , PasswordChangetActivity.this, PasswordChangetActivity.this.imei, PasswordChangetActivity.this.userid, str1, str2);
        WaitLoadDialog.getInstance().showDialog(PasswordChangetActivity.this, null, true);
        localPassMod.execute(new JiaDeNetRequest[0]);
      }
    });
  }

  public void init()
  {
    this.passwordold = ((EditText)findViewById(2131427430));
    this.password_new = ((EditText)findViewById(2131427431));
    this.password_new_second = ((EditText)findViewById(2131427432));
    this.button_save = ((Button)findViewById(2131427433));
    this.context = this;
    addButtonEvent();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.imei = Utils.getPhoneId();
    setContentView(2130903062);
    this.titlename = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titlename.setText("修改密码");
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userid = ((LoginBean)localList.get(0)).getUserId();
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PasswordChangetActivity.this.finish();
      }
    });
    init();
  }

  protected void onStart()
  {
    super.onStart();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.PasswordChangetActivity
 * JD-Core Version:    0.6.2
 */