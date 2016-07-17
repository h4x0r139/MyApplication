package com.kfb.c;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;

public class Kf extends Activity
{
  private static boolean c = false;
  d a;
  private Dkdn b = null;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    Context localContext = getApplicationContext();
    if ((c) && (this.b != null));
    try
    {
      c = false;
      localContext.unregisterReceiver(this.b);
    }
    catch (Exception localException2)
    {
      try
      {
        while (true)
        {
          IntentFilter localIntentFilter = new IntentFilter();
          localIntentFilter.addAction(c.q);
          localIntentFilter.addAction(c.r);
          localIntentFilter.addDataScheme(c.s);
          if (this.b == null)
            this.b = new Dkdn();
          localContext.registerReceiver(this.b, localIntentFilter);
          c = true;
          this.a = d.a(this, c.b);
          this.a.a(c.l, this, Activity.class);
          this.a.a(c.m, paramBundle, Bundle.class);
          return;
          localException2 = localException2;
          localException2.printStackTrace();
        }
      }
      catch (Exception localException1)
      {
        while (true)
          localException1.printStackTrace();
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.c.Kf
 * JD-Core Version:    0.6.2
 */