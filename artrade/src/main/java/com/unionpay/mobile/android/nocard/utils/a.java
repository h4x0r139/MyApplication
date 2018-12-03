package com.unionpay.mobile.android.nocard.utils;

import android.content.Intent;
import android.os.Bundle;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.plugin.c;
import com.unionpay.mobile.android.utils.g;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class a
{
  private static String a(Bundle paramBundle)
  {
    String str = null;
    if (paramBundle != null)
    {
      if (!paramBundle.containsKey("paydata"))
        break label24;
      str = paramBundle.getString("paydata");
    }
    label24: boolean bool;
    do
    {
      return str;
      bool = paramBundle.containsKey("tn");
      str = null;
    }
    while (!bool);
    return paramBundle.getString("tn");
  }

  // ERROR //
  private static String a(String paramString)
  {
    // Byte code:
    //   0: ldc 26
    //   2: ldc 28
    //   4: invokestatic 33	com/unionpay/mobile/android/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   7: pop
    //   8: aload_0
    //   9: ifnull +134 -> 143
    //   12: aload_0
    //   13: invokestatic 38	java/net/URLDecoder:decode	(Ljava/lang/String;)Ljava/lang/String;
    //   16: astore_2
    //   17: ldc 26
    //   19: new 40	java/lang/StringBuilder
    //   22: dup
    //   23: ldc 42
    //   25: invokespecial 46	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: aload_2
    //   29: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokestatic 57	com/unionpay/mobile/android/utils/g:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   38: pop
    //   39: aload_2
    //   40: ifnull +84 -> 124
    //   43: aload_2
    //   44: invokestatic 62	com/unionpay/mobile/android/utils/a:a	(Ljava/lang/String;)[B
    //   47: astore 11
    //   49: aload 11
    //   51: astore 4
    //   53: aconst_null
    //   54: astore 5
    //   56: aload 4
    //   58: ifnull +20 -> 78
    //   61: new 64	java/lang/String
    //   64: dup
    //   65: aload 4
    //   67: ldc 66
    //   69: invokespecial 69	java/lang/String:<init>	([BLjava/lang/String;)V
    //   72: astore 8
    //   74: aload 8
    //   76: astore 5
    //   78: ldc 26
    //   80: new 40	java/lang/StringBuilder
    //   83: dup
    //   84: ldc 71
    //   86: invokespecial 46	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   89: aload 5
    //   91: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc 73
    //   96: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: invokestatic 33	com/unionpay/mobile/android/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   105: pop
    //   106: ldc 26
    //   108: ldc 75
    //   110: invokestatic 33	com/unionpay/mobile/android/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   113: pop
    //   114: aload 5
    //   116: areturn
    //   117: astore 10
    //   119: aload 10
    //   121: invokevirtual 79	java/io/IOException:printStackTrace	()V
    //   124: aconst_null
    //   125: astore 4
    //   127: goto -74 -> 53
    //   130: astore 9
    //   132: aload 9
    //   134: invokevirtual 80	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   137: aconst_null
    //   138: astore 5
    //   140: goto -62 -> 78
    //   143: aconst_null
    //   144: astore_2
    //   145: goto -128 -> 17
    //
    // Exception table:
    //   from	to	target	type
    //   43	49	117	java/io/IOException
    //   61	74	130	java/io/UnsupportedEncodingException
  }

  public static boolean a(Intent paramIntent, b paramb)
  {
    if (paramIntent == null)
      return false;
    g.c("uppay", " ===>" + paramIntent.toString());
    String str1 = paramIntent.getDataString();
    Bundle localBundle = paramIntent.getExtras();
    label139: int n;
    label189: label227: boolean bool1;
    if ((localBundle != null) && (localBundle.containsKey("reqOriginalId")))
    {
      g.c("uppay", " bundle===>" + localBundle.toString());
      paramb.D.a = localBundle.getInt("reqOriginalId");
      g.b("uppay", "reqID:" + paramb.D.a);
      paramb.D.e = localBundle.getBoolean("invokedbyplugin");
      if (localBundle == null)
        break label422;
      paramb.aC = localBundle.getBoolean("dlgstyle", false);
      String str9 = localBundle.getString("url_index");
      if (str9 != null)
      {
        if (Pattern.compile("[0-9]*").matcher(str9).matches())
          break label416;
        n = 0;
        if (n != 0)
          paramb.aE = Integer.parseInt(str9);
      }
      com.unionpay.mobile.android.global.a.L = localBundle.getInt("navbargb", -10705958);
      com.unionpay.mobile.android.global.a.M = localBundle.getInt("divlinecolor", -13268007);
      if (paramb.aC)
      {
        com.unionpay.mobile.android.global.b.b = -1;
        com.unionpay.mobile.android.global.b.c = -2513882;
        com.unionpay.mobile.android.global.b.d = -6745;
      }
      int i = paramb.D.a;
      bool1 = false;
      switch (i)
      {
      default:
        label320: if ((paramb.D.a == 2) || (paramb.D.a == 3))
          paramb.D.d = true;
        if (!paramb.c)
          break;
      case 0:
      case 2:
      case 1:
      case 3:
      case 4:
      case 1000:
      }
    }
    for (String str2 = "1.8"; ; str2 = "1.4")
    {
      paramb.a = str2;
      return bool1;
      if ((str1 == null) || (str1.length() <= 0))
        break;
      paramb.D.a = 1000;
      g.b("uppay", "nativeBrowser data:" + str1);
      break label139;
      label416: n = 1;
      break label189;
      label422: paramb.aC = false;
      com.unionpay.mobile.android.global.a.L = -10705958;
      com.unionpay.mobile.android.global.a.M = -13268007;
      break label227;
      paramb.D.c = localBundle.getString("ex_mode");
      g.a("uppay", "mServerIdentifier = " + paramb.D.c);
      paramb.b = a(localBundle);
      paramb.d = localBundle.getString("appID");
      if (paramb.d == null)
        paramb.d = "";
      String str7 = localBundle.getString("frontNotifyByPlugin");
      if ((str7 == null) || (str7.length() == 0));
      for (boolean bool2 = true; ; bool2 = false)
      {
        paramb.P = bool2;
        String str8 = paramb.b;
        bool1 = false;
        if (str8 != null)
        {
          int m = paramb.b.trim().length();
          bool1 = false;
          if (m > 0)
          {
            g.a("uppay", "tn from bundle:" + paramb.b);
            boolean bool3 = "2".equalsIgnoreCase(paramb.b.substring(-1 + paramb.b.length()));
            boolean bool4 = false;
            if (!bool3)
              bool4 = true;
            paramb.c = bool4;
            g.a("uppay", "dw.isNewTypeTn=" + paramb.c);
            bool1 = true;
          }
        }
        paramb.m = localBundle.getString("ResultURL");
        g.a("uppay", "result URL:" + paramb.m);
        break;
      }
      String str6 = localBundle.getString("uppayuri");
      paramb.D.b = localBundle.getString("resultIntentAction");
      g.a("uppay", " result Action=" + paramb.D.b);
      bool1 = a(str6, paramb);
      break label320;
      paramb.D.c = localBundle.getString("ex_mode");
      paramb.D.g = localBundle.getString("tencentUID");
      paramb.D.h = localBundle.getString("tencentWID");
      paramb.b = a(localBundle);
      String str4 = paramb.b;
      bool1 = false;
      if (str4 == null)
        break label320;
      int j = paramb.b.trim().length();
      bool1 = false;
      if (j <= 0)
        break label320;
      String str5 = paramb.D.h;
      bool1 = false;
      if (str5 == null)
        break label320;
      int k = paramb.D.h.trim().length();
      bool1 = false;
      if (k <= 0)
        break label320;
      bool1 = true;
      break label320;
      String str3 = localBundle.getString("paydata");
      g.b("PluginEx", " paydata=" + str3);
      bool1 = b(a(str3), paramb);
      break label320;
      bool1 = a(str1, paramb);
      break label320;
    }
  }

  private static boolean a(String paramString, b paramb)
  {
    String str1 = null;
    String[] arrayOfString1;
    if (paramString != null)
    {
      arrayOfString1 = paramString.split("\\?");
      if (arrayOfString1.length < 2)
        g.c("uppay", "uppay protocol params error!");
    }
    else
    {
      return false;
    }
    String str2 = arrayOfString1[1];
    g.a("uppay", "parseUPPayURIParams() +++ ");
    g.a("uppay", str2);
    String[] arrayOfString2 = str2.split("&");
    int i = arrayOfString2.length;
    int j = 0;
    Object localObject = null;
    if (j < i)
    {
      String[] arrayOfString3 = arrayOfString2[j].split("=");
      if (arrayOfString3.length >= 2)
      {
        if (!arrayOfString3[0].equalsIgnoreCase("style"))
          break label126;
        localObject = arrayOfString3[1];
      }
      while (true)
      {
        j++;
        break;
        label126: if (arrayOfString3[0].equalsIgnoreCase("paydata"))
          str1 = arrayOfString3[1];
      }
    }
    if ((localObject != null) && (localObject.equalsIgnoreCase("token")) && (str1 != null))
      g.a("uppay", "paydata=" + str1);
    for (boolean bool = b(a(str1), paramb); ; bool = false)
    {
      g.a("uppay", "parseUPPayURIParams() ---");
      return bool;
    }
  }

  private static boolean b(String paramString, b paramb)
  {
    if ((paramString == null) || (paramString.length() == 0));
    label97: 
    do
    {
      return false;
      paramb.D.c = "00";
      String[] arrayOfString1 = paramString.split(",");
      int i = arrayOfString1.length;
      int j = 0;
      if (j < i)
      {
        String[] arrayOfString2 = arrayOfString1[j].trim().split("=");
        if (arrayOfString2.length >= 2)
        {
          if (!arrayOfString2[0].trim().equalsIgnoreCase("tn"))
            break label97;
          paramb.b = arrayOfString2[1].trim();
        }
        while (true)
        {
          j++;
          break;
          if (arrayOfString2[0].trim().equalsIgnoreCase("usetestmode"))
          {
            if (arrayOfString2[1].trim().equalsIgnoreCase("true"))
              paramb.D.c = "01";
            else if (arrayOfString2[1].trim().equalsIgnoreCase("test"))
              paramb.D.c = "02";
            else if (arrayOfString2[1].trim().equalsIgnoreCase("inner"))
              paramb.D.c = "98";
          }
          else if (arrayOfString2[0].trim().equalsIgnoreCase("ResultURL"))
            try
            {
              paramb.m = URLDecoder.decode(arrayOfString2[1].trim(), "UTF-8");
            }
            catch (UnsupportedEncodingException localUnsupportedEncodingException)
            {
              localUnsupportedEncodingException.printStackTrace();
            }
        }
      }
    }
    while ((paramb.b == null) || (paramb.b.length() <= 0) || (paramb.m == null) || (paramb.m.length() <= 0));
    boolean bool1 = "2".equalsIgnoreCase(paramb.b.substring(-1 + paramb.b.length()));
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    paramb.c = bool2;
    g.a("uppay", "dw.isNewTypeTn=" + paramb.c);
    return true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.utils.a
 * JD-Core Version:    0.6.2
 */