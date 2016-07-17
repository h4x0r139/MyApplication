package com.amap.api.services.core;

import android.content.Context;
import android.os.Build.VERSION;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  public static double a(double paramDouble)
  {
    return Double.parseDouble(new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US)).format(paramDouble));
  }

  public static double a(int paramInt)
  {
    return paramInt / 111700.0D;
  }

  public static String a(LatLonPoint paramLatLonPoint)
  {
    if (paramLatLonPoint == null)
      return "";
    double d1 = a(paramLatLonPoint.getLongitude());
    double d2 = a(paramLatLonPoint.getLatitude());
    return d1 + "," + d2;
  }

  public static String a(Date paramDate)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    if (paramDate != null)
      return localSimpleDateFormat.format(paramDate);
    return "";
  }

  public static String a(List<LatLonPoint> paramList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramList.size(); i++)
    {
      LatLonPoint localLatLonPoint = (LatLonPoint)paramList.get(i);
      double d1 = a(localLatLonPoint.getLongitude());
      double d2 = a(localLatLonPoint.getLatitude());
      localStringBuffer.append(d1).append(",").append(d2);
      localStringBuffer.append(";");
    }
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    return localStringBuffer.toString();
  }

  public static Proxy a(Context paramContext)
  {
    Proxy localProxy;
    try
    {
      localURI = new URI("http://restapi.amap.com");
      if (Build.VERSION.SDK_INT >= 11)
      {
        localProxy = a(paramContext, localURI);
        if ((localProxy == null) || (localProxy.type() == Proxy.Type.DIRECT))
          break label58;
        return null;
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      while (true)
      {
        localURISyntaxException.printStackTrace();
        URI localURI = null;
        continue;
        localProxy = b(paramContext);
      }
    }
    label58: return localProxy;
  }

  private static Proxy a(Context paramContext, URI paramURI)
  {
    ProxySelector localProxySelector = ProxySelector.getDefault();
    try
    {
      List localList2 = localProxySelector.select(paramURI);
      localList1 = localList2;
      Proxy localProxy = null;
      if (localList1 != null)
      {
        int i = localList1.size();
        localProxy = null;
        if (i > 0)
          localProxy = (Proxy)localList1.get(0);
      }
      return localProxy;
    }
    catch (Exception localException)
    {
      while (true)
        List localList1 = null;
    }
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public static byte[] a(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[2048];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte, 0, 2048);
      if (i == -1)
        break;
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    return localByteArrayOutputStream.toByteArray();
  }

  public static String b(InputStream paramInputStream)
  {
    try
    {
      String str = new String(a(paramInputStream));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  // ERROR //
  private static Proxy b(Context paramContext)
  {
    // Byte code:
    //   0: bipush 80
    //   2: istore_1
    //   3: aload_0
    //   4: ldc 194
    //   6: invokevirtual 200	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   9: checkcast 202	android/net/ConnectivityManager
    //   12: invokevirtual 206	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnull +377 -> 394
    //   20: aload_3
    //   21: invokevirtual 211	android/net/NetworkInfo:getType	()I
    //   24: iconst_1
    //   25: if_icmpne +40 -> 65
    //   28: aload_0
    //   29: invokestatic 217	android/net/Proxy:getHost	(Landroid/content/Context;)Ljava/lang/String;
    //   32: astore 7
    //   34: aload_0
    //   35: invokestatic 221	android/net/Proxy:getPort	(Landroid/content/Context;)I
    //   38: istore_1
    //   39: aload 7
    //   41: ifnull +353 -> 394
    //   44: new 130	java/net/Proxy
    //   47: dup
    //   48: getstatic 224	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   51: aload 7
    //   53: iload_1
    //   54: invokestatic 230	java/net/InetSocketAddress:createUnresolved	(Ljava/lang/String;I)Ljava/net/InetSocketAddress;
    //   57: invokespecial 233	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   60: astore 9
    //   62: aload 9
    //   64: areturn
    //   65: ldc 235
    //   67: invokestatic 241	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   70: astore 13
    //   72: aload_0
    //   73: invokevirtual 245	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   76: aload 13
    //   78: aconst_null
    //   79: aconst_null
    //   80: aconst_null
    //   81: aconst_null
    //   82: invokevirtual 251	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   85: astore 14
    //   87: aload 14
    //   89: ifnull +319 -> 408
    //   92: aload 14
    //   94: invokeinterface 257 1 0
    //   99: ifeq +309 -> 408
    //   102: aload 14
    //   104: ldc_w 259
    //   107: invokeinterface 263 2 0
    //   112: istore 16
    //   114: iload 16
    //   116: iconst_m1
    //   117: if_icmpeq +285 -> 402
    //   120: aload 14
    //   122: iload 16
    //   124: invokeinterface 267 2 0
    //   129: astore 7
    //   131: aload 7
    //   133: ifnull +16 -> 149
    //   136: aload 7
    //   138: ldc 44
    //   140: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   143: ifeq +6 -> 149
    //   146: aconst_null
    //   147: astore 7
    //   149: aload 14
    //   151: ldc_w 273
    //   154: invokeinterface 263 2 0
    //   159: istore 17
    //   161: iload 17
    //   163: iconst_m1
    //   164: if_icmpeq +232 -> 396
    //   167: aload 14
    //   169: iload 17
    //   171: invokeinterface 267 2 0
    //   176: astore 18
    //   178: aload 18
    //   180: ifnull +216 -> 396
    //   183: aload 18
    //   185: ldc 44
    //   187: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifne +206 -> 396
    //   193: aload 18
    //   195: invokestatic 278	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   198: istore 19
    //   200: iload 19
    //   202: istore 5
    //   204: aload 14
    //   206: ifnull +10 -> 216
    //   209: aload 14
    //   211: invokeinterface 281 1 0
    //   216: iload 5
    //   218: istore_1
    //   219: goto -180 -> 39
    //   222: astore 4
    //   224: iload_1
    //   225: istore 5
    //   227: aload_3
    //   228: invokevirtual 284	android/net/NetworkInfo:getExtraInfo	()Ljava/lang/String;
    //   231: astore 6
    //   233: aload 6
    //   235: ifnull +135 -> 370
    //   238: aload 6
    //   240: ldc 44
    //   242: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   245: ifne +125 -> 370
    //   248: aload 6
    //   250: getstatic 22	java/util/Locale:US	Ljava/util/Locale;
    //   253: invokevirtual 288	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   256: astore 10
    //   258: aload 10
    //   260: ldc_w 290
    //   263: invokevirtual 293	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   266: ifne +25 -> 291
    //   269: aload 10
    //   271: ldc_w 295
    //   274: invokevirtual 293	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   277: ifne +14 -> 291
    //   280: aload 10
    //   282: ldc_w 297
    //   285: invokevirtual 293	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   288: ifeq +37 -> 325
    //   291: invokestatic 300	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   294: astore 11
    //   296: aload 11
    //   298: ifnull +126 -> 424
    //   301: aload 11
    //   303: ldc 44
    //   305: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   308: ifne +116 -> 424
    //   311: aload 11
    //   313: ldc_w 302
    //   316: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   319: ifne +105 -> 424
    //   322: goto +95 -> 417
    //   325: aload 10
    //   327: ldc_w 304
    //   330: invokevirtual 293	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   333: ifeq +114 -> 447
    //   336: invokestatic 300	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   339: astore 12
    //   341: aload 12
    //   343: ifnull +96 -> 439
    //   346: aload 12
    //   348: ldc 44
    //   350: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   353: ifne +86 -> 439
    //   356: aload 12
    //   358: ldc_w 302
    //   361: invokevirtual 271	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   364: ifne +75 -> 439
    //   367: goto +65 -> 432
    //   370: invokestatic 300	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   373: astore 7
    //   375: invokestatic 307	android/net/Proxy:getDefaultPort	()I
    //   378: istore 8
    //   380: iload 8
    //   382: istore_1
    //   383: goto -344 -> 39
    //   386: astore_2
    //   387: aconst_null
    //   388: areturn
    //   389: astore 15
    //   391: goto -164 -> 227
    //   394: aconst_null
    //   395: areturn
    //   396: iload_1
    //   397: istore 5
    //   399: goto -195 -> 204
    //   402: aconst_null
    //   403: astore 7
    //   405: goto -256 -> 149
    //   408: iload_1
    //   409: istore 5
    //   411: aconst_null
    //   412: astore 7
    //   414: goto -210 -> 204
    //   417: aload 11
    //   419: astore 7
    //   421: goto -382 -> 39
    //   424: ldc_w 309
    //   427: astore 11
    //   429: goto -12 -> 417
    //   432: aload 12
    //   434: astore 7
    //   436: goto -397 -> 39
    //   439: ldc_w 311
    //   442: astore 12
    //   444: goto -12 -> 432
    //   447: iload 5
    //   449: istore_1
    //   450: aconst_null
    //   451: astore 7
    //   453: goto -414 -> 39
    //
    // Exception table:
    //   from	to	target	type
    //   65	87	222	java/lang/Exception
    //   92	114	222	java/lang/Exception
    //   120	131	222	java/lang/Exception
    //   136	146	222	java/lang/Exception
    //   149	161	222	java/lang/Exception
    //   167	178	222	java/lang/Exception
    //   183	200	222	java/lang/Exception
    //   3	16	386	java/lang/Exception
    //   20	39	386	java/lang/Exception
    //   44	62	386	java/lang/Exception
    //   227	233	386	java/lang/Exception
    //   238	291	386	java/lang/Exception
    //   291	296	386	java/lang/Exception
    //   301	322	386	java/lang/Exception
    //   325	341	386	java/lang/Exception
    //   346	367	386	java/lang/Exception
    //   370	380	386	java/lang/Exception
    //   209	216	389	java/lang/Exception
  }

  public static void b(String paramString)
    throws AMapException
  {
    String str2;
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      if (!localJSONObject.has("status"))
        return;
      if (!localJSONObject.has("info"))
        return;
      String str1 = localJSONObject.getString("status");
      str2 = localJSONObject.getString("info");
      if ((str1.equals("1")) || (!str1.equals("0")))
        return;
      if ((str2.equals("INVALID_USER_KEY")) || (str2.equals("INSUFFICIENT_PRIVILEGES")))
        throw new AMapException("key鉴权失败");
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return;
    }
    if ((str2.equals("SERVICE_NOT_EXIST")) || (str2.equals("SERVICE_RESPONSE_ERROR")) || (str2.equals("OVER_QUOTA")) || (str2.equals("UNKNOWN_ERROR")))
      throw new AMapException("未知的错误");
    if (str2.equals("INVALID_PARAMS"))
      throw new AMapException("无效的参数 - IllegalArgumentException");
  }

  public static Date c(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return null;
  }

  public static Date d(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals("")))
      return null;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HHmm");
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return null;
  }

  public static Date e(String paramString)
  {
    if ((paramString == null) || (paramString.trim().equals("")))
      return null;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("HH:mm");
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.c
 * JD-Core Version:    0.6.2
 */