package com.tg.jiadeonline.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import java.util.List;

public class CheckNet
{
  public static boolean is3rd(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getType() == 0);
  }

  public static boolean isGpsEnabled(Context paramContext)
  {
    List localList = ((LocationManager)paramContext.getSystemService("location")).getProviders(true);
    return (localList != null) && (localList.size() > 0);
  }

  public static boolean isNetworkAvailable(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    NetworkInfo[] arrayOfNetworkInfo;
    if (localConnectivityManager != null)
    {
      arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      if (arrayOfNetworkInfo == null);
    }
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfNetworkInfo.length)
        return false;
      if (arrayOfNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED)
        return true;
    }
  }

  public static boolean isWifi(Context paramContext)
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getType() == 1);
  }

  public static boolean isWifiEnabled(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    return ((localConnectivityManager.getActiveNetworkInfo() != null) && (localConnectivityManager.getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED)) || (localTelephonyManager.getNetworkType() == 3);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.CheckNet
 * JD-Core Version:    0.6.2
 */