package com.UCMobile.PayPlugin;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.unionpay.mobile.android.utils.g;

public class PayInterface
{
  private static boolean a = false;

  public static int show(Context paramContext, String paramString)
  {
    int i = 1;
    g.a("uppay", "UC.PayInterface.show() +++ ");
    if (!a)
    {
      IntentFilter localIntentFilter = new IntentFilter("com.unionpay.uppay.resultURL");
      paramContext.registerReceiver(new PayResultReceiver(), localIntentFilter);
      a = i;
    }
    if (paramString == null)
    {
      g.a("uppay", "data == null!!!!");
      return -1;
    }
    String[] arrayOfString = paramString.split(",");
    Bundle localBundle;
    if ((arrayOfString == null) || (arrayOfString.length == i))
    {
      localBundle = new Bundle();
      localBundle.putInt("reqOriginalId", 4);
      if (i == 0)
        break label240;
      localBundle.putString("paydata", paramString);
    }
    while (true)
    {
      String str = paramContext.getPackageName();
      if ((str != null) && (str.startsWith("com.UCMobile")))
      {
        Intent localIntent1 = new Intent();
        localIntent1.setFlags(33554432);
        localIntent1.putExtras(localBundle);
        localIntent1.putExtra("PackageName", str);
        localIntent1.setClassName("com.unionpay.uppay", "com.unionpay.uppay.PayActivity");
        paramContext.startActivity(localIntent1);
        Intent localIntent2 = new Intent("com.UCMobile.PluginApp.ActivityState");
        localIntent2.putExtra("ActivityState", "active");
        localIntent2.putExtra("PackageName", str);
        localIntent2.addCategory("android.intent.category.DEFAULT");
        paramContext.sendBroadcast(localIntent2);
      }
      g.a("uppay", "UC.PayInterface.show() +++ ");
      return 0;
      i = 0;
      break;
      label240: localBundle.putString("oldVersionPlugin", "true");
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.UCMobile.PayPlugin.PayInterface
 * JD-Core Version:    0.6.2
 */