package com.unionpay.mobile.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.unionpay.mobile.android.languages.c;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public final class b
{
  public static String a()
  {
    try
    {
      Class localClass = Class.forName("android.os.SystemProperties");
      Object localObject = localClass.newInstance();
      String str = (String)localClass.getMethod("get", new Class[] { String.class, String.class }).invoke(localObject, new Object[] { "gsm.version.baseband", "no message" });
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static String a(Context paramContext)
  {
    Activity localActivity = (Activity)paramContext;
    PackageManager localPackageManager = localActivity.getPackageManager();
    try
    {
      return c.by.a;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
        localNameNotFoundException.printStackTrace();
    }
  }

  public static String b()
  {
    try
    {
      Process localProcess2 = Runtime.getRuntime().exec("cat /proc/version");
      localProcess1 = localProcess2;
      localBufferedReader = new BufferedReader(new InputStreamReader(localProcess1.getInputStream()), 8192);
      localObject = "";
    }
    catch (IOException localIOException1)
    {
      try
      {
        while (true)
        {
          BufferedReader localBufferedReader;
          String str3 = localBufferedReader.readLine();
          if (str3 == null)
            break;
          String str4 = (String)localObject + str3;
          localObject = str4;
        }
        localIOException1 = localIOException1;
        localIOException1.printStackTrace();
        Process localProcess1 = null;
      }
      catch (IOException localIOException2)
      {
        Object localObject;
        localIOException2.printStackTrace();
        if (localObject != "")
          try
          {
            String str1 = ((String)localObject).substring(((String)localObject).indexOf("version ") + "version ".length());
            String str2 = str1.substring(0, str1.indexOf(" "));
            return str2;
          }
          catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
          {
            localIndexOutOfBoundsException.printStackTrace();
          }
      }
    }
    return "";
  }

  public static final String b(Context paramContext)
  {
    WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
    if ((localWifiInfo != null) && (localWifiInfo.getMacAddress() != null))
      return localWifiInfo.getMacAddress().replaceAll(":", "");
    return "";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.b
 * JD-Core Version:    0.6.2
 */