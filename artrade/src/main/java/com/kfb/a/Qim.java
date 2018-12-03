package com.kfb.a;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public class Qim extends Activity
{
  d a;

  public void finish()
  {
    super.finish();
    this.a = d.a(this, c.b);
    this.a.a(c.v);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.a = d.a(this, c.b);
    this.a.a(c.x, paramConfiguration, Configuration.class);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    this.a = d.a(this, c.b);
    this.a.a(c.r, this, Activity.class);
    this.a.a(c.s, paramBundle, Bundle.class);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = paramKeyEvent;
    Class[] arrayOfClass = new Class[2];
    arrayOfClass[0] = Integer.TYPE;
    arrayOfClass[1] = KeyEvent.class;
    this.a = d.a(this, c.b);
    this.a.a(c.w, arrayOfObject, arrayOfClass);
    return true;
  }

  protected void onRestart()
  {
    super.onRestart();
    this.a = d.a(this, c.b);
    this.a.a(c.u);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.a.Qim
 * JD-Core Version:    0.6.2
 */