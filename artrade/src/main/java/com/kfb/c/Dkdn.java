package com.kfb.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Dkdn extends BroadcastReceiver
{
  d a;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      if ("".equals(c.a))
        c.a(paramContext);
      this.a = d.a(paramContext, c.a);
      Object[] arrayOfObject = { paramContext, paramIntent };
      Class[] arrayOfClass = { Context.class, Intent.class };
      this.a.a(c.n, arrayOfObject, arrayOfClass);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.c.Dkdn
 * JD-Core Version:    0.6.2
 */