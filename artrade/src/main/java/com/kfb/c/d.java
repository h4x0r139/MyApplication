package com.kfb.c;

import android.content.Context;
import dalvik.system.DexClassLoader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class d
{
  public static String a = c.h;
  public static String b = c.j;
  private static d g;
  private static String h = "";
  private DexClassLoader c;
  private Class d;
  private Object e;
  private String f;

  public static d a(Context paramContext, String paramString)
  {
    if (g == null)
    {
      d locald = new d();
      g = locald;
      locald.b(paramContext);
      g.a(paramString);
    }
    while (true)
    {
      return g;
      if (!g.f.equals(paramString))
      {
        g.c.clearAssertionStatus();
        g.b(paramContext);
        g.a(paramString);
      }
    }
  }

  // ERROR //
  public static void a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 67	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   9: aload_0
    //   10: getstatic 71	com/kfb/c/c:i	Ljava/lang/String;
    //   13: iconst_0
    //   14: invokevirtual 77	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   17: invokevirtual 83	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   20: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: ldc 89
    //   25: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   31: putstatic 33	com/kfb/c/d:h	Ljava/lang/String;
    //   34: new 79	java/io/File
    //   37: dup
    //   38: new 67	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 68	java/lang/StringBuilder:<init>	()V
    //   45: getstatic 33	com/kfb/c/d:h	Ljava/lang/String;
    //   48: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: getstatic 25	com/kfb/c/d:a	Ljava/lang/String;
    //   54: invokevirtual 87	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 92	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokespecial 94	java/io/File:<init>	(Ljava/lang/String;)V
    //   63: astore 14
    //   65: aload_0
    //   66: invokevirtual 98	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   69: getstatic 30	com/kfb/c/d:b	Ljava/lang/String;
    //   72: invokevirtual 104	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   75: astore 15
    //   77: aload 15
    //   79: astore 4
    //   81: new 106	java/io/DataInputStream
    //   84: dup
    //   85: aload 4
    //   87: invokespecial 109	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   90: astore_3
    //   91: aload_3
    //   92: invokevirtual 113	java/io/DataInputStream:readInt	()I
    //   95: istore 16
    //   97: aload 14
    //   99: invokevirtual 117	java/io/File:exists	()Z
    //   102: ifeq +52 -> 154
    //   105: aload 14
    //   107: invokevirtual 121	java/io/File:length	()J
    //   110: lstore 24
    //   112: lload 24
    //   114: iload 16
    //   116: i2l
    //   117: lcmp
    //   118: ifne +36 -> 154
    //   121: aload_3
    //   122: invokevirtual 124	java/io/DataInputStream:close	()V
    //   125: aload 4
    //   127: ifnull +8 -> 135
    //   130: aload 4
    //   132: invokevirtual 127	java/io/InputStream:close	()V
    //   135: return
    //   136: astore 26
    //   138: aload 26
    //   140: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   143: goto -18 -> 125
    //   146: astore 27
    //   148: aload 27
    //   150: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   153: return
    //   154: iload 16
    //   156: newarray byte
    //   158: astore 17
    //   160: aload 14
    //   162: invokevirtual 133	java/io/File:createNewFile	()Z
    //   165: pop
    //   166: new 135	java/io/BufferedOutputStream
    //   169: dup
    //   170: new 137	java/io/FileOutputStream
    //   173: dup
    //   174: aload 14
    //   176: invokespecial 140	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   179: invokespecial 143	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   182: astore 19
    //   184: aload_3
    //   185: aload 17
    //   187: invokevirtual 147	java/io/DataInputStream:read	([B)I
    //   190: pop
    //   191: aload 19
    //   193: aload 17
    //   195: invokevirtual 151	java/io/BufferedOutputStream:write	([B)V
    //   198: aload_3
    //   199: invokevirtual 124	java/io/DataInputStream:close	()V
    //   202: aload 4
    //   204: ifnull +8 -> 212
    //   207: aload 4
    //   209: invokevirtual 127	java/io/InputStream:close	()V
    //   212: aload 19
    //   214: invokevirtual 152	java/io/BufferedOutputStream:close	()V
    //   217: return
    //   218: astore 22
    //   220: aload 22
    //   222: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   225: return
    //   226: astore 21
    //   228: aload 21
    //   230: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   233: goto -31 -> 202
    //   236: astore 23
    //   238: aload 23
    //   240: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   243: goto -31 -> 212
    //   246: astore 8
    //   248: aconst_null
    //   249: astore 9
    //   251: aconst_null
    //   252: astore 10
    //   254: aload 8
    //   256: invokevirtual 153	java/lang/Exception:printStackTrace	()V
    //   259: aload 9
    //   261: ifnull +8 -> 269
    //   264: aload 9
    //   266: invokevirtual 124	java/io/DataInputStream:close	()V
    //   269: aload 10
    //   271: ifnull +8 -> 279
    //   274: aload 10
    //   276: invokevirtual 127	java/io/InputStream:close	()V
    //   279: aload_1
    //   280: ifnull -145 -> 135
    //   283: aload_1
    //   284: invokevirtual 152	java/io/BufferedOutputStream:close	()V
    //   287: return
    //   288: astore 11
    //   290: aload 11
    //   292: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   295: return
    //   296: astore 13
    //   298: aload 13
    //   300: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   303: goto -34 -> 269
    //   306: astore 12
    //   308: aload 12
    //   310: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   313: goto -34 -> 279
    //   316: astore_2
    //   317: aconst_null
    //   318: astore_3
    //   319: aconst_null
    //   320: astore 4
    //   322: aload_3
    //   323: ifnull +7 -> 330
    //   326: aload_3
    //   327: invokevirtual 124	java/io/DataInputStream:close	()V
    //   330: aload 4
    //   332: ifnull +8 -> 340
    //   335: aload 4
    //   337: invokevirtual 127	java/io/InputStream:close	()V
    //   340: aload_1
    //   341: ifnull +7 -> 348
    //   344: aload_1
    //   345: invokevirtual 152	java/io/BufferedOutputStream:close	()V
    //   348: aload_2
    //   349: athrow
    //   350: astore 7
    //   352: aload 7
    //   354: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   357: goto -27 -> 330
    //   360: astore 6
    //   362: aload 6
    //   364: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   367: goto -27 -> 340
    //   370: astore 5
    //   372: aload 5
    //   374: invokevirtual 130	java/io/IOException:printStackTrace	()V
    //   377: goto -29 -> 348
    //   380: astore_2
    //   381: aconst_null
    //   382: astore_1
    //   383: aconst_null
    //   384: astore_3
    //   385: goto -63 -> 322
    //   388: astore_2
    //   389: aconst_null
    //   390: astore_1
    //   391: goto -69 -> 322
    //   394: astore_2
    //   395: aload 19
    //   397: astore_1
    //   398: goto -76 -> 322
    //   401: astore_2
    //   402: aload 10
    //   404: astore 4
    //   406: aload 9
    //   408: astore_3
    //   409: goto -87 -> 322
    //   412: astore 8
    //   414: aload 4
    //   416: astore 10
    //   418: aconst_null
    //   419: astore_1
    //   420: aconst_null
    //   421: astore 9
    //   423: goto -169 -> 254
    //   426: astore 8
    //   428: aload_3
    //   429: astore 9
    //   431: aload 4
    //   433: astore 10
    //   435: aconst_null
    //   436: astore_1
    //   437: goto -183 -> 254
    //   440: astore 8
    //   442: aload 19
    //   444: astore_1
    //   445: aload_3
    //   446: astore 9
    //   448: aload 4
    //   450: astore 10
    //   452: goto -198 -> 254
    //
    // Exception table:
    //   from	to	target	type
    //   121	125	136	java/io/IOException
    //   130	135	146	java/io/IOException
    //   212	217	218	java/io/IOException
    //   198	202	226	java/io/IOException
    //   207	212	236	java/io/IOException
    //   2	77	246	java/lang/Exception
    //   283	287	288	java/io/IOException
    //   264	269	296	java/io/IOException
    //   274	279	306	java/io/IOException
    //   2	77	316	finally
    //   326	330	350	java/io/IOException
    //   335	340	360	java/io/IOException
    //   344	348	370	java/io/IOException
    //   81	91	380	finally
    //   91	112	388	finally
    //   154	184	388	finally
    //   184	198	394	finally
    //   254	259	401	finally
    //   81	91	412	java/lang/Exception
    //   91	112	426	java/lang/Exception
    //   154	184	426	java/lang/Exception
    //   184	198	440	java/lang/Exception
  }

  private void a(String paramString)
  {
    this.f = paramString;
    try
    {
      this.d = this.c.loadClass(paramString);
      this.e = this.d.getConstructor(new Class[0]).newInstance(new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void b(Context paramContext)
  {
    if ("".equals(h))
      a(paramContext);
    this.c = new DexClassLoader(h + a, h, null, paramContext.getClassLoader());
  }

  public final Object a(String paramString, Object paramObject, Class paramClass)
  {
    return a(paramString, new Object[] { paramObject }, new Class[] { paramClass });
  }

  public final Object a(String paramString, Object[] paramArrayOfObject, Class[] paramArrayOfClass)
  {
    try
    {
      Method localMethod = this.d.getDeclaredMethod(paramString, paramArrayOfClass);
      localMethod.setAccessible(true);
      Object localObject = localMethod.invoke(this.e, paramArrayOfObject);
      return localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.c.d
 * JD-Core Version:    0.6.2
 */