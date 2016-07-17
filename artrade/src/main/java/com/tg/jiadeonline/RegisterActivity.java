package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.net.GetMobVerifyForReg;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.Date;

public class RegisterActivity extends Activity
{
  private static Activity activity;
  private static Context context;
  private static String getYanzhengma = "";
  static Handler guanggaohandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      if (i == 0)
      {
        RegisterActivity.zhuce_huanyizhang.setText("获取验证码");
        RegisterActivity.zhuce_huanyizhang.setOnClickListener(new RegisterActivity.MyOnClickListener());
      }
      while (true)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
        RegisterActivity.zhuce_huanyizhang.setText("等待(" + i + ")重新获取");
        RegisterActivity.zhuce_huanyizhang.setOnClickListener(null);
      }
    }
  };
  private static String imei = "";
  private static MyThread mythread;
  private static Button zhuce_huanyizhang;
  private static EditText zhuce_phone;
  private static EditText zhuce_yanzhengma;
  private ImageView backImageView;
  private PopupWindow popright = null;
  private TextView titleName;
  private Button zhuce_button2;
  private Button zhuce_button3;

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    activity = this;
    context = this;
    imei = Utils.getPhoneId();
    requestWindowFeature(1);
    setContentView(2130903068);
    zhuce_phone = (EditText)findViewById(2131427504);
    zhuce_yanzhengma = (EditText)findViewById(2131427507);
    this.zhuce_button3 = ((Button)findViewById(2131427510));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.zhuce_button2 = ((Button)findViewById(2131427509));
    this.zhuce_button3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        RegisterActivity.this.startActivity(localIntent);
        RegisterActivity.this.finish();
      }
    });
    zhuce_phone.setOnFocusChangeListener(new View.OnFocusChangeListener()
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
    zhuce_yanzhengma.setOnFocusChangeListener(new View.OnFocusChangeListener()
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
    zhuce_huanyizhang = (Button)findViewById(2131427508);
    this.titleName = ((TextView)findViewById(2131427561));
    this.titleName.setText("注册");
    long l = new Date().getTime() - CommonRef.yanzhengmadate;
    if ((l > 9000L) || (CommonRef.yanzhengmadate == 0L))
    {
      zhuce_huanyizhang.setText("获取手机验证码");
      zhuce_huanyizhang.setOnClickListener(new MyOnClickListener());
    }
    while (true)
    {
      this.backImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          RegisterActivity.this.finish();
        }
      });
      this.zhuce_button2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str1 = RegisterActivity.zhuce_phone.getText().toString().trim();
          String str2 = RegisterActivity.zhuce_yanzhengma.getText().toString().trim();
          if (str1.equals(""))
          {
            Utils.showDialog(RegisterActivity.context, "提示", "请输入手机号", 2130837567, null);
            return;
          }
          if (RegisterActivity.getYanzhengma.equals(""))
          {
            Utils.showDialog(RegisterActivity.context, "提示", "请先获取验证码", 2130837567, null);
            return;
          }
          if (str2.equals(""))
          {
            Utils.showDialog(RegisterActivity.context, "提示", "请输入验证码", 2130837567, null);
            return;
          }
          if (!str2.equals(RegisterActivity.getYanzhengma))
          {
            Utils.showDialog(RegisterActivity.context, "提示", "请输入正确验证码", 2130837567, null);
            return;
          }
          Intent localIntent = new Intent(RegisterActivity.this, Register1Activity.class);
          localIntent.putExtra("phone", str1);
          localIntent.putExtra("yanzhengma", str2);
          RegisterActivity.this.startActivity(localIntent);
        }
      });
      return;
      mythread = new MyThread((int)l / 1000);
    }
  }

  public final void popwindows(String paramString)
  {
    View localView = LayoutInflater.from(context).inflate(2130903070, null);
    this.popright = new PopupWindow(localView, -1, -1, false);
    ((TextView)localView.findViewById(2131427522));
    this.popright.setAnimationStyle(2131230730);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    ((ImageView)localView.findViewById(2131427396)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (RegisterActivity.this.popright.isShowing())
          RegisterActivity.this.popright.dismiss();
      }
    });
  }

  static class MyOnClickListener
    implements View.OnClickListener
  {
    public void onClick(View paramView)
    {
      long l = new Date().getTime() - CommonRef.yanzhengmadate;
      if ((CommonRef.yanzhengmadate != 0L) && (l < 90000L))
      {
        RegisterActivity.mythread = new RegisterActivity.MyThread((int)l / 1000);
        return;
      }
      String str = RegisterActivity.zhuce_phone.getText().toString().trim();
      if (("".equals(str)) || (str == null))
      {
        Utils.showDialog(RegisterActivity.context, "提示", "请输入手机号", 2130837567, null);
        return;
      }
      GetMobVerifyForReg localGetMobVerifyForReg = new GetMobVerifyForReg(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          Utils.showDialog(RegisterActivity.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          CommonRef.yanzhengmadate = new Date().getTime();
          RegisterActivity.mythread = new RegisterActivity.MyThread(90);
          RegisterActivity.mythread.start();
          RegisterActivity.getYanzhengma = paramAnonymousJiaDeNetResponse.result.toString();
        }
      }
      , RegisterActivity.context, RegisterActivity.imei, "", str);
      WaitLoadDialog.getInstance().showDialog(RegisterActivity.activity, null, true);
      localGetMobVerifyForReg.execute(new JiaDeNetRequest[0]);
    }
  }

  static class MyThread extends Thread
  {
    private boolean b = true;
    private int shengyutime;

    public MyThread(int paramInt)
    {
      this.shengyutime = paramInt;
    }

    public void run()
    {
      while (true)
      {
        if ((this.shengyutime < 0) || (this.shengyutime > 90) || (!this.b))
        {
          this.b = false;
          return;
        }
        try
        {
          Thread.sleep(1000L);
          RegisterActivity.guanggaohandler.sendEmptyMessage(this.shengyutime);
          this.shengyutime = (-1 + this.shengyutime);
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            localInterruptedException.printStackTrace();
        }
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.RegisterActivity
 * JD-Core Version:    0.6.2
 */