package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils
{
  public static String a(Context paramContext)
  {
    return d(paramContext, paramContext.getSharedPreferences("UnionPayPluginEx.pref", 3).getString("uid", ""));
  }

  public static void a(Context paramContext, String paramString)
  {
    String str = e(paramContext, paramString);
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("UnionPayPluginEx.pref", 3).edit();
    localEditor.putString("uid", str);
    localEditor.commit();
  }

  public static String b(Context paramContext)
  {
    return d(paramContext, paramContext.getSharedPreferences("UnionPayPluginEx.pref", 3).getString("last_pay_method", ""));
  }

  public static void b(Context paramContext, String paramString)
  {
    String str = e(paramContext, paramString);
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("UnionPayPluginEx.pref", 3).edit();
    localEditor.putString("last_pay_method", str);
    localEditor.commit();
  }

  public static String c(Context paramContext)
  {
    return d(paramContext, paramContext.getSharedPreferences("UnionPayPluginEx.pref", 3).getString("last_user", ""));
  }

  public static void c(Context paramContext, String paramString)
  {
    String str = e(paramContext, paramString);
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("UnionPayPluginEx.pref", 3).edit();
    localEditor.putString("last_user", str);
    localEditor.commit();
  }

  private static String d(Context paramContext, String paramString)
  {
    String str1 = b.b(paramContext);
    String str2 = decPrefData(paramString, (str1 + "23456789abcdef12123456786789abcd").substring(0, 32));
    if (str2 == null)
      return "";
    if (!str2.endsWith(str1))
      return "";
    return str2.substring(0, str2.length() - str1.length());
  }

  public static native String decPrefData(String paramString1, String paramString2);

  private static String e(Context paramContext, String paramString)
  {
    String str1 = b.b(paramContext);
    String str2 = (str1 + "23456789abcdef12123456786789abcd").substring(0, 32);
    String str3 = encPrefData(paramString + str1, str2);
    if (str3 == null)
      str3 = "";
    return str3;
  }

  public static native String encPrefData(String paramString1, String paramString2);
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.PreferenceUtils
 * JD-Core Version:    0.6.2
 */