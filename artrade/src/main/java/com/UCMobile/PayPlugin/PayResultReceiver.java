package com.UCMobile.PayPlugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PayResultReceiver extends BroadcastReceiver
{
  private static native void native_UCPayResultNotify(String paramString);

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    native_UCPayResultNotify(paramIntent.getStringExtra("ResultURL"));
    String str = paramContext.getPackageName();
    if (str.startsWith("com.UCMobile"))
    {
      Intent localIntent = new Intent("com.UCMobile.PluginApp.ActivityState");
      localIntent.putExtra("ActivityState", "inactive");
      localIntent.putExtra("PackageName", str);
      paramContext.sendBroadcast(localIntent);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.UCMobile.PayPlugin.PayResultReceiver
 * JD-Core Version:    0.6.2
 */