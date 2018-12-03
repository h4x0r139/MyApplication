package com.kfb.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Dknd extends BroadcastReceiver
{
  d a;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      this.a = d.a(paramContext, c.a);
      Object[] arrayOfObject = { paramContext, paramIntent };
      Class[] arrayOfClass = { Context.class, Intent.class };
      this.a.a(c.t, arrayOfObject, arrayOfClass);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.a.Dknd
 * JD-Core Version:    0.6.2
 */