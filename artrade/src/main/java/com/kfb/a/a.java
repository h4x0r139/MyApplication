package com.kfb.a;

public final class a
{
  private static boolean a = false;

  // ERROR //
  static void a(android.content.Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 22	com/kfb/a/d:a	(Landroid/content/Context;)V
    //   4: aload_0
    //   5: getstatic 28	com/kfb/a/c:C	Ljava/lang/String;
    //   8: iconst_0
    //   9: invokevirtual 34	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 40	java/io/File:listFiles	()[Ljava/io/File;
    //   17: arraylength
    //   18: istore_2
    //   19: aload_1
    //   20: invokevirtual 44	java/io/File:exists	()Z
    //   23: ifeq +16 -> 39
    //   26: iload_2
    //   27: bipush 16
    //   29: if_icmpeq +9 -> 38
    //   32: iload_2
    //   33: bipush 17
    //   35: if_icmpne +4 -> 39
    //   38: return
    //   39: aload_1
    //   40: invokevirtual 48	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   43: astore 16
    //   45: aload_0
    //   46: invokevirtual 52	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   49: getstatic 55	com/kfb/a/d:b	Ljava/lang/String;
    //   52: invokevirtual 61	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   55: astore 17
    //   57: aload 17
    //   59: astore 8
    //   61: new 63	java/io/DataInputStream
    //   64: dup
    //   65: aload 8
    //   67: invokespecial 66	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   70: astore 4
    //   72: aload 4
    //   74: aload 4
    //   76: invokevirtual 70	java/io/DataInputStream:readInt	()I
    //   79: newarray byte
    //   81: invokevirtual 74	java/io/DataInputStream:read	([B)I
    //   84: pop
    //   85: aload 4
    //   87: invokevirtual 70	java/io/DataInputStream:readInt	()I
    //   90: pop
    //   91: new 76	java/util/zip/ZipInputStream
    //   94: dup
    //   95: aload 4
    //   97: invokespecial 77	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   100: astore 9
    //   102: aload 9
    //   104: invokevirtual 81	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   107: astore 20
    //   109: aload 20
    //   111: ifnull +260 -> 371
    //   114: aload 20
    //   116: invokevirtual 86	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   119: astore 21
    //   121: aload 20
    //   123: invokevirtual 89	java/util/zip/ZipEntry:isDirectory	()Z
    //   126: ifeq +110 -> 236
    //   129: aload 21
    //   131: iconst_0
    //   132: iconst_m1
    //   133: aload 21
    //   135: invokevirtual 94	java/lang/String:length	()I
    //   138: iadd
    //   139: invokevirtual 98	java/lang/String:substring	(II)Ljava/lang/String;
    //   142: astore 27
    //   144: new 36	java/io/File
    //   147: dup
    //   148: new 100	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   155: aload 16
    //   157: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: getstatic 108	java/io/File:separator	Ljava/lang/String;
    //   163: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: aload 27
    //   168: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   177: invokevirtual 117	java/io/File:mkdirs	()Z
    //   180: pop
    //   181: goto -79 -> 102
    //   184: astore_3
    //   185: aload 9
    //   187: astore 5
    //   189: aload 8
    //   191: astore 6
    //   193: aload_3
    //   194: invokevirtual 120	java/lang/Exception:printStackTrace	()V
    //   197: aload 6
    //   199: ifnull +8 -> 207
    //   202: aload 6
    //   204: invokevirtual 125	java/io/InputStream:close	()V
    //   207: aload 4
    //   209: ifnull +8 -> 217
    //   212: aload 4
    //   214: invokevirtual 126	java/io/DataInputStream:close	()V
    //   217: aload 5
    //   219: ifnull -181 -> 38
    //   222: aload 5
    //   224: invokevirtual 127	java/util/zip/ZipInputStream:close	()V
    //   227: return
    //   228: astore 13
    //   230: aload 13
    //   232: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   235: return
    //   236: new 36	java/io/File
    //   239: dup
    //   240: new 100	java/lang/StringBuilder
    //   243: dup
    //   244: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   247: aload 16
    //   249: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: getstatic 108	java/io/File:separator	Ljava/lang/String;
    //   255: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: aload 21
    //   260: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: invokevirtual 111	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   266: invokespecial 114	java/io/File:<init>	(Ljava/lang/String;)V
    //   269: astore 22
    //   271: aload 22
    //   273: invokevirtual 131	java/io/File:createNewFile	()Z
    //   276: pop
    //   277: new 133	java/io/FileOutputStream
    //   280: dup
    //   281: aload 22
    //   283: invokespecial 136	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   286: astore 24
    //   288: sipush 1024
    //   291: newarray byte
    //   293: astore 25
    //   295: aload 9
    //   297: aload 25
    //   299: invokevirtual 137	java/util/zip/ZipInputStream:read	([B)I
    //   302: istore 26
    //   304: iload 26
    //   306: iconst_m1
    //   307: if_icmpeq +56 -> 363
    //   310: aload 24
    //   312: aload 25
    //   314: iconst_0
    //   315: iload 26
    //   317: invokevirtual 141	java/io/FileOutputStream:write	([BII)V
    //   320: aload 24
    //   322: invokevirtual 144	java/io/FileOutputStream:flush	()V
    //   325: goto -30 -> 295
    //   328: astore 7
    //   330: aload 8
    //   332: ifnull +8 -> 340
    //   335: aload 8
    //   337: invokevirtual 125	java/io/InputStream:close	()V
    //   340: aload 4
    //   342: ifnull +8 -> 350
    //   345: aload 4
    //   347: invokevirtual 126	java/io/DataInputStream:close	()V
    //   350: aload 9
    //   352: ifnull +8 -> 360
    //   355: aload 9
    //   357: invokevirtual 127	java/util/zip/ZipInputStream:close	()V
    //   360: aload 7
    //   362: athrow
    //   363: aload 24
    //   365: invokevirtual 145	java/io/FileOutputStream:close	()V
    //   368: goto -266 -> 102
    //   371: aload 8
    //   373: ifnull +8 -> 381
    //   376: aload 8
    //   378: invokevirtual 125	java/io/InputStream:close	()V
    //   381: aload 4
    //   383: invokevirtual 126	java/io/DataInputStream:close	()V
    //   386: aload 9
    //   388: invokevirtual 127	java/util/zip/ZipInputStream:close	()V
    //   391: return
    //   392: astore 30
    //   394: aload 30
    //   396: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   399: return
    //   400: astore 31
    //   402: aload 31
    //   404: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   407: goto -26 -> 381
    //   410: astore 29
    //   412: aload 29
    //   414: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   417: goto -31 -> 386
    //   420: astore 15
    //   422: aload 15
    //   424: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   427: goto -220 -> 207
    //   430: astore 14
    //   432: aload 14
    //   434: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   437: goto -220 -> 217
    //   440: astore 12
    //   442: aload 12
    //   444: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   447: goto -107 -> 340
    //   450: astore 11
    //   452: aload 11
    //   454: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   457: goto -107 -> 350
    //   460: astore 10
    //   462: aload 10
    //   464: invokevirtual 128	java/io/IOException:printStackTrace	()V
    //   467: goto -107 -> 360
    //   470: astore 7
    //   472: aconst_null
    //   473: astore 4
    //   475: aconst_null
    //   476: astore 9
    //   478: aconst_null
    //   479: astore 8
    //   481: goto -151 -> 330
    //   484: astore 7
    //   486: aconst_null
    //   487: astore 4
    //   489: aconst_null
    //   490: astore 9
    //   492: goto -162 -> 330
    //   495: astore 7
    //   497: aconst_null
    //   498: astore 9
    //   500: goto -170 -> 330
    //   503: astore 7
    //   505: aload 6
    //   507: astore 8
    //   509: aload 5
    //   511: astore 9
    //   513: goto -183 -> 330
    //   516: astore_3
    //   517: aconst_null
    //   518: astore 4
    //   520: aconst_null
    //   521: astore 5
    //   523: aconst_null
    //   524: astore 6
    //   526: goto -333 -> 193
    //   529: astore_3
    //   530: aload 8
    //   532: astore 6
    //   534: aconst_null
    //   535: astore 4
    //   537: aconst_null
    //   538: astore 5
    //   540: goto -347 -> 193
    //   543: astore_3
    //   544: aload 8
    //   546: astore 6
    //   548: aconst_null
    //   549: astore 5
    //   551: goto -358 -> 193
    //
    // Exception table:
    //   from	to	target	type
    //   102	109	184	java/lang/Exception
    //   114	181	184	java/lang/Exception
    //   236	295	184	java/lang/Exception
    //   295	304	184	java/lang/Exception
    //   310	325	184	java/lang/Exception
    //   363	368	184	java/lang/Exception
    //   222	227	228	java/io/IOException
    //   102	109	328	finally
    //   114	181	328	finally
    //   236	295	328	finally
    //   295	304	328	finally
    //   310	325	328	finally
    //   363	368	328	finally
    //   386	391	392	java/io/IOException
    //   376	381	400	java/io/IOException
    //   381	386	410	java/io/IOException
    //   202	207	420	java/io/IOException
    //   212	217	430	java/io/IOException
    //   335	340	440	java/io/IOException
    //   345	350	450	java/io/IOException
    //   355	360	460	java/io/IOException
    //   39	57	470	finally
    //   61	72	484	finally
    //   72	102	495	finally
    //   193	197	503	finally
    //   39	57	516	java/lang/Exception
    //   61	72	529	java/lang/Exception
    //   72	102	543	java/lang/Exception
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.a.a
 * JD-Core Version:    0.6.2
 */