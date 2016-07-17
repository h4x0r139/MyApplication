package com.amap.api.services.core;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class b
{
  public static String a = "";
  private static b b = null;
  private static Context c = null;
  private static TelephonyManager d;
  private static ConnectivityManager e;
  private static String f;
  private static String g;
  private static String h;
  private static String i;

  public static b a(Context paramContext)
  {
    if (b == null)
    {
      b = new b();
      c = paramContext;
      d = (TelephonyManager)c.getSystemService("phone");
      e = (ConnectivityManager)c.getSystemService("connectivity");
      f = c.getApplicationContext().getPackageName();
      g = h();
      h = f();
      i = g();
      a = b(c);
    }
    return b;
  }

  public static String b(Context paramContext)
  {
    if ((a == null) || (a.equals("")));
    try
    {
      a = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getString("com.amap.api.v2.apikey");
      return a;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static String f()
  {
    try
    {
      PackageManager localPackageManager = c.getPackageManager();
      String str = (String)localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(c.getPackageName(), 0));
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }

  public static String g()
  {
    PackageManager localPackageManager = c.getPackageManager();
    try
    {
      String str = localPackageManager.getPackageInfo(c.getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return "";
  }

  private static String h()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)c.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    int j = localDisplayMetrics.widthPixels;
    int k = localDisplayMetrics.heightPixels;
    if (k > j);
    for (String str = j + "*" + k; ; str = k + "*" + j)
    {
      g = str;
      return g;
    }
  }

  // ERROR //
  public String a()
  {
    // Byte code:
    //   0: invokestatic 170	com/amap/api/services/core/d:a	()Ljava/lang/String;
    //   3: astore_1
    //   4: getstatic 24	com/amap/api/services/core/b:c	Landroid/content/Context;
    //   7: invokestatic 173	com/amap/api/services/core/d:a	(Landroid/content/Context;)Ljava/security/PublicKey;
    //   10: astore 5
    //   12: aload_1
    //   13: invokevirtual 177	java/lang/String:getBytes	()[B
    //   16: aload 5
    //   18: invokestatic 180	com/amap/api/services/core/d:a	([BLjava/security/Key;)[B
    //   21: astore 6
    //   23: aload_1
    //   24: invokevirtual 177	java/lang/String:getBytes	()[B
    //   27: aload_0
    //   28: invokevirtual 182	com/amap/api/services/core/b:b	()Ljava/lang/String;
    //   31: invokevirtual 177	java/lang/String:getBytes	()[B
    //   34: invokestatic 185	com/amap/api/services/core/d:a	([B[B)[B
    //   37: astore 7
    //   39: aload 6
    //   41: arraylength
    //   42: aload 7
    //   44: arraylength
    //   45: iadd
    //   46: newarray byte
    //   48: astore_3
    //   49: aload 6
    //   51: iconst_0
    //   52: aload_3
    //   53: iconst_0
    //   54: aload 6
    //   56: arraylength
    //   57: invokestatic 191	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   60: aload 7
    //   62: iconst_0
    //   63: aload_3
    //   64: aload 6
    //   66: arraylength
    //   67: aload 7
    //   69: arraylength
    //   70: invokestatic 191	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   73: aload_3
    //   74: invokestatic 194	com/amap/api/services/core/d:a	([B)[B
    //   77: invokestatic 197	com/amap/api/services/core/d:b	([B)Ljava/lang/String;
    //   80: areturn
    //   81: astore_2
    //   82: aconst_null
    //   83: astore_3
    //   84: aload_2
    //   85: astore 4
    //   87: aload 4
    //   89: invokevirtual 111	java/lang/Exception:printStackTrace	()V
    //   92: goto -19 -> 73
    //   95: astore 4
    //   97: goto -10 -> 87
    //
    // Exception table:
    //   from	to	target	type
    //   4	49	81	java/lang/Exception
    //   49	73	95	java/lang/Exception
  }

  public String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ia=1&");
    if ((a != null) && (a.length() > 0))
    {
      localStringBuilder.append("key=");
      localStringBuilder.append(a);
      localStringBuilder.append("&");
    }
    localStringBuilder.append("ct=android");
    String str1 = d.getDeviceId();
    String str2 = d.getSubscriberId();
    localStringBuilder.append("&ime=" + str1);
    localStringBuilder.append("&sim=" + str2);
    localStringBuilder.append("&pkg=" + f);
    localStringBuilder.append("&mod=");
    localStringBuilder.append(d());
    localStringBuilder.append("&sv=");
    localStringBuilder.append(c());
    localStringBuilder.append("&nt=");
    localStringBuilder.append(e());
    String str3 = d.getNetworkOperatorName();
    localStringBuilder.append("&np=");
    localStringBuilder.append(str3);
    localStringBuilder.append("&ctm=" + System.currentTimeMillis());
    localStringBuilder.append("&re=" + g);
    localStringBuilder.append("&av=V2.2.0");
    localStringBuilder.append("&apn=" + h);
    localStringBuilder.append("&apv=" + i);
    localStringBuilder.append("&pro=sea");
    return localStringBuilder.toString();
  }

  public String c()
  {
    return Build.VERSION.RELEASE;
  }

  public String d()
  {
    return Build.MODEL;
  }

  public String e()
  {
    if (c.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0)
      return "";
    if (e == null)
      return "";
    NetworkInfo localNetworkInfo = e.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return "";
    return localNetworkInfo.getTypeName();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.b
 * JD-Core Version:    0.6.2
 */