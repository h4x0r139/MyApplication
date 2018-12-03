package com.unionpay.mobile.android.nocard.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.plugin.c;
import com.unionpay.mobile.android.utils.g;

public final class d
{
  public static void a(Context paramContext, b paramb)
  {
    g.b("uppay", "exit() +++");
    g.a("uppay", "reqId=" + paramb.D.a);
    BaseActivity localBaseActivity = (BaseActivity)paramContext;
    if (paramb.D.f.length() > 0)
    {
      g.a("uppay", "result=" + paramb.D.f);
      switch (paramb.D.a)
      {
      default:
      case 1:
      case 4:
      case 1000:
      case 0:
      case 2:
      case 3:
      }
    }
    while (true)
    {
      if ((paramb.Q != null) && (paramb.Q.length() > 0) && (paramb.R != null) && (paramb.R.length() > 0))
      {
        boolean bool = paramb.P;
        String str1 = paramb.Q;
        String str2 = paramb.R;
        if (bool)
          new Thread(new e(str1, str2)).start();
      }
      localBaseActivity.b();
      localBaseActivity.finish();
      g.b("uppay", "exit() +++");
      return;
      g.b("uppay", " notifyBrowserResult() +++ ");
      String str3 = paramb.D.f;
      String str4;
      label276: Intent localIntent3;
      label320: String str5;
      if (str3.equalsIgnoreCase("fail"))
      {
        str4 = "1";
        int i = paramb.D.a;
        localIntent3 = null;
        switch (i)
        {
        case 2:
        case 3:
        default:
          str5 = paramb.m + str4;
          g.a("uppay", "result URL= " + str5);
          if (1000 == paramb.D.a)
          {
            Intent localIntent4 = new Intent("android.intent.action.VIEW", Uri.parse(str5));
            localIntent4.addCategory("android.intent.category.BROWSABLE");
            localBaseActivity.startActivity(localIntent4);
          }
          break;
        case 4:
        case 1:
        }
      }
      while (true)
      {
        g.b("uppay", " notifyBrowserResult() --- ");
        break;
        if (str3.equalsIgnoreCase("cancel"))
        {
          str4 = "-1";
          break label276;
        }
        str4 = "0";
        break label276;
        Intent localIntent5 = new Intent("com.UCMobile.PluginApp.ActivityState");
        localIntent5.putExtra("ActivityState", "inactive");
        localIntent5.addCategory("android.intent.category.DEFAULT");
        localBaseActivity.sendBroadcast(localIntent5);
        localIntent3 = new Intent("com.unionpay.uppay.resultURL");
        g.b("uppay", " uc browser ");
        break label320;
        localIntent3 = new Intent(paramb.D.b);
        g.b("uppay", " other browser ");
        g.a("uppay", " result Action=" + paramb.D.b);
        break label320;
        localIntent3.putExtra("ResultURL", str5);
        g.a("browser", localIntent3.toURI());
        localBaseActivity.sendBroadcast(localIntent3);
      }
      g.b("uppay", "notifyAppResult() +++");
      Intent localIntent2 = new Intent();
      localIntent2.putExtra("pay_result", paramb.D.f);
      if ((paramb.Q != null) && (paramb.Q.length() > 0) && (paramb.R != null) && (paramb.R.length() > 0) && (!paramb.P))
      {
        localIntent2.putExtra("notify_url", paramb.Q);
        localIntent2.putExtra("notify_msg", paramb.R);
      }
      localBaseActivity.setResult(-1, localIntent2);
      g.b("uppay", "notifyAppResult() ---");
      continue;
      g.b("uppay", "notifyTencentJarResult() +++");
      Intent localIntent1 = new Intent();
      localIntent1.putExtra("pay_result", paramb.D.f);
      localIntent1.putExtra("tencentWID", paramb.D.h);
      localIntent1.putExtra("tencentUID", paramb.D.g);
      localIntent1.putExtra("bankInfo", paramb.D.j);
      localIntent1.putExtra("cardType", paramb.D.k);
      localIntent1.putExtra("cardNo", paramb.D.i);
      localBaseActivity.setResult(-1, localIntent1);
      g.b("uppay", "notifyTencentJarResult() ---");
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.utils.d
 * JD-Core Version:    0.6.2
 */