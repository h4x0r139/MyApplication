package com.tg.jiadeonline;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.Window;
import android.widget.Toast;
import com.tg.jiadeonline.activity.HomeActivity;
import com.tg.jiadeonline.date.LoginManager;
import com.tg.jiadeonline.date.PaymentOrderManager;

public class Welcome extends Activity
{
  private static final long time = 2000L;
  Handler handler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      Welcome.this.endToHome();
      return true;
    }
  });
  private long sleepTime;
  private long startTime;

  private void endToHome()
  {
    startActivity(new Intent(this, HomeActivity.class));
    finish();
  }

  private void toHomeFragment()
  {
    this.sleepTime = (System.currentTimeMillis() - this.startTime);
    if (this.sleepTime < 2000L)
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            Thread.sleep(2000L);
            Welcome.this.handler.sendEmptyMessage(0);
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            while (true)
              localInterruptedException.printStackTrace();
          }
        }
      }).start();
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(2000L);
          Welcome.this.handler.sendEmptyMessage(0);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            localInterruptedException.printStackTrace();
        }
      }
    }).start();
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903142);
    getWindow().setFlags(1024, 1024);
    SharedPreferences localSharedPreferences = getSharedPreferences("jiadeonline", 0);
    if (localSharedPreferences.getInt("counter", 1) == 1)
    {
      LoginManager localLoginManager = new LoginManager(this);
      localLoginManager.openDataBase();
      localLoginManager.createTable();
      localLoginManager.closeDataBase();
      PaymentOrderManager localPaymentOrderManager = new PaymentOrderManager(this);
      localPaymentOrderManager.openDataBase();
      localPaymentOrderManager.createTable();
      localPaymentOrderManager.closeDataBase();
      SharedPreferences.Editor localEditor = localSharedPreferences.edit();
      localEditor.putInt("counter", 2);
      localEditor.commit();
    }
    ((ActivityManager)getSystemService("activity")).getMemoryInfo(new ActivityManager.MemoryInfo());
    toHomeFragment();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.Welcome
 * JD-Core Version:    0.6.2
 */