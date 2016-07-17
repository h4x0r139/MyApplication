package com.kfb.c;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public final class a
{
  private static boolean a = false;

  public static Class a(Context paramContext, Class paramClass)
  {
    String str = paramContext.getApplicationInfo().packageName;
    try
    {
      ActivityInfo[] arrayOfActivityInfo = paramContext.getPackageManager().getPackageInfo(str, 1).activities;
      if (arrayOfActivityInfo != null)
      {
        int i = 0;
        while (true)
        {
          int j = arrayOfActivityInfo.length;
          if (i >= j)
            break;
          try
          {
            Class localClass = Class.forName(arrayOfActivityInfo[i].name);
            boolean bool = paramClass.isAssignableFrom(localClass);
            if (bool)
              return localClass;
          }
          catch (ClassNotFoundException localClassNotFoundException)
          {
            localClassNotFoundException.printStackTrace();
            i++;
          }
        }
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return null;
  }

  // ERROR //
  static void a(Context paramContext)
  {
    // Byte code:
    //   0: new 72	java/lang/String
    //   3: dup
    //   4: ldc 74
    //   6: invokestatic 79	com/kfb/c/b:a	(Ljava/lang/String;)[B
    //   9: invokespecial 82	java/lang/String:<init>	([B)V
    //   12: astore_1
    //   13: aload_0
    //   14: invokestatic 86	com/kfb/c/d:a	(Landroid/content/Context;)V
    //   17: aload_0
    //   18: aload_1
    //   19: iconst_0
    //   20: invokevirtual 90	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   23: astore_2
    //   24: aload_2
    //   25: invokevirtual 96	java/io/File:listFiles	()[Ljava/io/File;
    //   28: arraylength
    //   29: istore_3
    //   30: aload_2
    //   31: invokevirtual 100	java/io/File:exists	()Z
    //   34: ifeq +29 -> 63
    //   37: iload_3
    //   38: bipush 50
    //   40: if_icmpeq +9 -> 49
    //   43: iload_3
    //   44: bipush 51
    //   46: if_icmpne +17 -> 63
    //   49: return
    //   50: astore 33
    //   52: aload 33
    //   54: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   57: ldc 103
    //   59: astore_1
    //   60: goto -47 -> 13
    //   63: aload_2
    //   64: invokevirtual 107	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   67: astore 17
    //   69: aload_0
    //   70: invokevirtual 111	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   73: getstatic 114	com/kfb/c/d:b	Ljava/lang/String;
    //   76: invokevirtual 120	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   79: astore 18
    //   81: aload 18
    //   83: astore 9
    //   85: new 122	java/io/DataInputStream
    //   88: dup
    //   89: aload 9
    //   91: invokespecial 125	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   94: astore 5
    //   96: aload 5
    //   98: aload 5
    //   100: invokevirtual 129	java/io/DataInputStream:readInt	()I
    //   103: newarray byte
    //   105: invokevirtual 133	java/io/DataInputStream:read	([B)I
    //   108: pop
    //   109: aload 5
    //   111: invokevirtual 129	java/io/DataInputStream:readInt	()I
    //   114: pop
    //   115: new 135	java/util/zip/ZipInputStream
    //   118: dup
    //   119: aload 5
    //   121: invokespecial 136	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   124: astore 10
    //   126: aload 10
    //   128: invokevirtual 140	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   131: astore 21
    //   133: aload 21
    //   135: ifnull +262 -> 397
    //   138: aload 21
    //   140: invokevirtual 145	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   143: astore 22
    //   145: aload 21
    //   147: invokevirtual 148	java/util/zip/ZipEntry:isDirectory	()Z
    //   150: ifeq +112 -> 262
    //   153: aload 22
    //   155: iconst_0
    //   156: iconst_m1
    //   157: aload 22
    //   159: invokevirtual 151	java/lang/String:length	()I
    //   162: iadd
    //   163: invokevirtual 155	java/lang/String:substring	(II)Ljava/lang/String;
    //   166: astore 28
    //   168: new 92	java/io/File
    //   171: dup
    //   172: new 157	java/lang/StringBuilder
    //   175: dup
    //   176: invokespecial 158	java/lang/StringBuilder:<init>	()V
    //   179: aload 17
    //   181: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: getstatic 165	java/io/File:separator	Ljava/lang/String;
    //   187: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: aload 28
    //   192: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   198: invokespecial 171	java/io/File:<init>	(Ljava/lang/String;)V
    //   201: invokevirtual 174	java/io/File:mkdirs	()Z
    //   204: pop
    //   205: goto -79 -> 126
    //   208: astore 4
    //   210: aload 10
    //   212: astore 6
    //   214: aload 9
    //   216: astore 7
    //   218: aload 4
    //   220: invokevirtual 175	java/lang/Exception:printStackTrace	()V
    //   223: aload 7
    //   225: ifnull +8 -> 233
    //   228: aload 7
    //   230: invokevirtual 180	java/io/InputStream:close	()V
    //   233: aload 5
    //   235: ifnull +8 -> 243
    //   238: aload 5
    //   240: invokevirtual 181	java/io/DataInputStream:close	()V
    //   243: aload 6
    //   245: ifnull -196 -> 49
    //   248: aload 6
    //   250: invokevirtual 182	java/util/zip/ZipInputStream:close	()V
    //   253: return
    //   254: astore 14
    //   256: aload 14
    //   258: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   261: return
    //   262: new 92	java/io/File
    //   265: dup
    //   266: new 157	java/lang/StringBuilder
    //   269: dup
    //   270: invokespecial 158	java/lang/StringBuilder:<init>	()V
    //   273: aload 17
    //   275: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: getstatic 165	java/io/File:separator	Ljava/lang/String;
    //   281: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   284: aload 22
    //   286: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: invokespecial 171	java/io/File:<init>	(Ljava/lang/String;)V
    //   295: astore 23
    //   297: aload 23
    //   299: invokevirtual 185	java/io/File:createNewFile	()Z
    //   302: pop
    //   303: new 187	java/io/FileOutputStream
    //   306: dup
    //   307: aload 23
    //   309: invokespecial 190	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   312: astore 25
    //   314: sipush 1024
    //   317: newarray byte
    //   319: astore 26
    //   321: aload 10
    //   323: aload 26
    //   325: invokevirtual 191	java/util/zip/ZipInputStream:read	([B)I
    //   328: istore 27
    //   330: iload 27
    //   332: iconst_m1
    //   333: if_icmpeq +56 -> 389
    //   336: aload 25
    //   338: aload 26
    //   340: iconst_0
    //   341: iload 27
    //   343: invokevirtual 195	java/io/FileOutputStream:write	([BII)V
    //   346: aload 25
    //   348: invokevirtual 198	java/io/FileOutputStream:flush	()V
    //   351: goto -30 -> 321
    //   354: astore 8
    //   356: aload 9
    //   358: ifnull +8 -> 366
    //   361: aload 9
    //   363: invokevirtual 180	java/io/InputStream:close	()V
    //   366: aload 5
    //   368: ifnull +8 -> 376
    //   371: aload 5
    //   373: invokevirtual 181	java/io/DataInputStream:close	()V
    //   376: aload 10
    //   378: ifnull +8 -> 386
    //   381: aload 10
    //   383: invokevirtual 182	java/util/zip/ZipInputStream:close	()V
    //   386: aload 8
    //   388: athrow
    //   389: aload 25
    //   391: invokevirtual 199	java/io/FileOutputStream:close	()V
    //   394: goto -268 -> 126
    //   397: aload 9
    //   399: ifnull +8 -> 407
    //   402: aload 9
    //   404: invokevirtual 180	java/io/InputStream:close	()V
    //   407: aload 5
    //   409: invokevirtual 181	java/io/DataInputStream:close	()V
    //   412: aload 10
    //   414: invokevirtual 182	java/util/zip/ZipInputStream:close	()V
    //   417: return
    //   418: astore 31
    //   420: aload 31
    //   422: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   425: return
    //   426: astore 32
    //   428: aload 32
    //   430: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   433: goto -26 -> 407
    //   436: astore 30
    //   438: aload 30
    //   440: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   443: goto -31 -> 412
    //   446: astore 16
    //   448: aload 16
    //   450: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   453: goto -220 -> 233
    //   456: astore 15
    //   458: aload 15
    //   460: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   463: goto -220 -> 243
    //   466: astore 13
    //   468: aload 13
    //   470: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   473: goto -107 -> 366
    //   476: astore 12
    //   478: aload 12
    //   480: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   483: goto -107 -> 376
    //   486: astore 11
    //   488: aload 11
    //   490: invokevirtual 101	java/io/IOException:printStackTrace	()V
    //   493: goto -107 -> 386
    //   496: astore 8
    //   498: aconst_null
    //   499: astore 5
    //   501: aconst_null
    //   502: astore 10
    //   504: aconst_null
    //   505: astore 9
    //   507: goto -151 -> 356
    //   510: astore 8
    //   512: aconst_null
    //   513: astore 5
    //   515: aconst_null
    //   516: astore 10
    //   518: goto -162 -> 356
    //   521: astore 8
    //   523: aconst_null
    //   524: astore 10
    //   526: goto -170 -> 356
    //   529: astore 8
    //   531: aload 7
    //   533: astore 9
    //   535: aload 6
    //   537: astore 10
    //   539: goto -183 -> 356
    //   542: astore 4
    //   544: aconst_null
    //   545: astore 5
    //   547: aconst_null
    //   548: astore 6
    //   550: aconst_null
    //   551: astore 7
    //   553: goto -335 -> 218
    //   556: astore 4
    //   558: aload 9
    //   560: astore 7
    //   562: aconst_null
    //   563: astore 5
    //   565: aconst_null
    //   566: astore 6
    //   568: goto -350 -> 218
    //   571: astore 4
    //   573: aload 9
    //   575: astore 7
    //   577: aconst_null
    //   578: astore 6
    //   580: goto -362 -> 218
    //
    // Exception table:
    //   from	to	target	type
    //   0	13	50	java/io/IOException
    //   126	133	208	java/lang/Exception
    //   138	205	208	java/lang/Exception
    //   262	321	208	java/lang/Exception
    //   321	330	208	java/lang/Exception
    //   336	351	208	java/lang/Exception
    //   389	394	208	java/lang/Exception
    //   248	253	254	java/io/IOException
    //   126	133	354	finally
    //   138	205	354	finally
    //   262	321	354	finally
    //   321	330	354	finally
    //   336	351	354	finally
    //   389	394	354	finally
    //   412	417	418	java/io/IOException
    //   402	407	426	java/io/IOException
    //   407	412	436	java/io/IOException
    //   228	233	446	java/io/IOException
    //   238	243	456	java/io/IOException
    //   361	366	466	java/io/IOException
    //   371	376	476	java/io/IOException
    //   381	386	486	java/io/IOException
    //   63	81	496	finally
    //   85	96	510	finally
    //   96	126	521	finally
    //   218	223	529	finally
    //   63	81	542	java/lang/Exception
    //   85	96	556	java/lang/Exception
    //   96	126	571	java/lang/Exception
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.c.a
 * JD-Core Version:    0.6.2
 */