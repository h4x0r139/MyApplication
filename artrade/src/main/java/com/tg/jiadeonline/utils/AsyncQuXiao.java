package com.tg.jiadeonline.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.File;
import java.io.PrintStream;

public class AsyncQuXiao extends AsyncTask<String, Integer, String>
{
  public static Context ctx = null;
  public static Dialog dialog = null;
  private Activity activity;
  private boolean finished = true;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 2:
      }
      AsyncQuXiao.dialog.dismiss();
      Toast.makeText(AsyncQuXiao.ctx, 2131165203, 1).show();
      Log.v("test", "--->>>  end");
    }
  };
  private boolean paused = false;
  private ProgressBar pb;
  private String url = "";

  public AsyncQuXiao(Activity paramActivity)
  {
    this.activity = paramActivity;
  }

  private void setupApp(String paramString)
  {
    if (!new File(paramString).exists())
      return;
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
    localIntent.setFlags(268435456);
    ctx.startActivity(localIntent);
  }

  public void continued()
  {
    this.paused = false;
    Log.d("debug", "paused------------" + this.paused);
  }

  // ERROR //
  protected String doInBackground(String[] paramArrayOfString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aconst_null
    //   5: astore 4
    //   7: aload_1
    //   8: iconst_1
    //   9: aaload
    //   10: astore 5
    //   12: new 131	java/net/URL
    //   15: dup
    //   16: aload_1
    //   17: iconst_0
    //   18: aaload
    //   19: invokespecial 132	java/net/URL:<init>	(Ljava/lang/String;)V
    //   22: astore 6
    //   24: aload 6
    //   26: invokevirtual 136	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   29: checkcast 138	java/net/HttpURLConnection
    //   32: astore_2
    //   33: aload_2
    //   34: iconst_1
    //   35: invokevirtual 142	java/net/HttpURLConnection:setAllowUserInteraction	(Z)V
    //   38: aload_2
    //   39: invokevirtual 146	java/net/HttpURLConnection:getContentLength	()I
    //   42: istore 15
    //   44: ldc 105
    //   46: new 68	java/lang/StringBuilder
    //   49: dup
    //   50: ldc 148
    //   52: invokespecial 71	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   55: iload 15
    //   57: invokevirtual 151	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   60: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   66: pop
    //   67: aload_2
    //   68: invokevirtual 155	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   71: astore_3
    //   72: new 68	java/lang/StringBuilder
    //   75: dup
    //   76: aload 5
    //   78: invokestatic 161	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   81: invokespecial 71	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   84: ldc 163
    //   86: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_1
    //   90: iconst_2
    //   91: aaload
    //   92: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: ldc 165
    //   97: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: astore 17
    //   105: new 51	java/io/File
    //   108: dup
    //   109: aload 17
    //   111: invokespecial 53	java/io/File:<init>	(Ljava/lang/String;)V
    //   114: astore 18
    //   116: ldc 105
    //   118: aload 17
    //   120: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: new 167	java/io/RandomAccessFile
    //   127: dup
    //   128: aload 18
    //   130: ldc 169
    //   132: invokespecial 172	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   135: astore 20
    //   137: iconst_0
    //   138: i2l
    //   139: lstore 21
    //   141: aload 20
    //   143: lload 21
    //   145: invokevirtual 176	java/io/RandomAccessFile:seek	(J)V
    //   148: sipush 10240
    //   151: newarray byte
    //   153: astore 23
    //   155: iconst_0
    //   156: istore 24
    //   158: ldc 105
    //   160: new 68	java/lang/StringBuilder
    //   163: dup
    //   164: ldc 178
    //   166: invokespecial 71	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   169: aload 23
    //   171: arraylength
    //   172: invokevirtual 151	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   175: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 116	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   181: pop
    //   182: aload_0
    //   183: getfield 32	com/tg/jiadeonline/utils/AsyncQuXiao:finished	Z
    //   186: ifne +57 -> 243
    //   189: aload_3
    //   190: invokevirtual 183	java/io/InputStream:close	()V
    //   193: aload 20
    //   195: invokevirtual 184	java/io/RandomAccessFile:close	()V
    //   198: aload_2
    //   199: invokevirtual 187	java/net/HttpURLConnection:disconnect	()V
    //   202: aload_0
    //   203: iconst_0
    //   204: putfield 32	com/tg/jiadeonline/utils/AsyncQuXiao:finished	Z
    //   207: aload_3
    //   208: ifnull +357 -> 565
    //   211: aload_3
    //   212: invokevirtual 183	java/io/InputStream:close	()V
    //   215: aload 20
    //   217: ifnull +8 -> 225
    //   220: aload 20
    //   222: invokevirtual 184	java/io/RandomAccessFile:close	()V
    //   225: aload_2
    //   226: ifnull +339 -> 565
    //   229: aload_2
    //   230: invokevirtual 187	java/net/HttpURLConnection:disconnect	()V
    //   233: aload_1
    //   234: iconst_0
    //   235: aaload
    //   236: areturn
    //   237: ldc2_w 188
    //   240: invokestatic 194	java/lang/Thread:sleep	(J)V
    //   243: aload_0
    //   244: getfield 34	com/tg/jiadeonline/utils/AsyncQuXiao:paused	Z
    //   247: ifne -10 -> 237
    //   250: aload_3
    //   251: aload 23
    //   253: invokevirtual 198	java/io/InputStream:read	([B)I
    //   256: istore 27
    //   258: iload 27
    //   260: iconst_m1
    //   261: if_icmpeq -72 -> 189
    //   264: aload 20
    //   266: aload 23
    //   268: iconst_0
    //   269: iload 27
    //   271: invokevirtual 202	java/io/RandomAccessFile:write	([BII)V
    //   274: iload 24
    //   276: iload 27
    //   278: iadd
    //   279: istore 24
    //   281: iconst_1
    //   282: anewarray 204	java/lang/Integer
    //   285: astore 28
    //   287: aload 28
    //   289: iconst_0
    //   290: ldc 205
    //   292: iload 24
    //   294: i2f
    //   295: fmul
    //   296: iload 15
    //   298: i2f
    //   299: fdiv
    //   300: f2i
    //   301: invokestatic 208	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   304: aastore
    //   305: aload_0
    //   306: aload 28
    //   308: invokevirtual 212	com/tg/jiadeonline/utils/AsyncQuXiao:publishProgress	([Ljava/lang/Object;)V
    //   311: iload 24
    //   313: iload 15
    //   315: if_icmpne +82 -> 397
    //   318: aload_0
    //   319: iconst_2
    //   320: invokevirtual 216	com/tg/jiadeonline/utils/AsyncQuXiao:sentMassage	(I)V
    //   323: aload 17
    //   325: ldc 165
    //   327: invokevirtual 220	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   330: ifeq -141 -> 189
    //   333: aload_0
    //   334: aload 17
    //   336: invokespecial 222	com/tg/jiadeonline/utils/AsyncQuXiao:setupApp	(Ljava/lang/String;)V
    //   339: goto -150 -> 189
    //   342: astore 7
    //   344: aload 20
    //   346: astore 4
    //   348: aload 7
    //   350: invokevirtual 225	java/net/MalformedURLException:printStackTrace	()V
    //   353: aload_0
    //   354: iconst_0
    //   355: putfield 32	com/tg/jiadeonline/utils/AsyncQuXiao:finished	Z
    //   358: aload_3
    //   359: ifnull -126 -> 233
    //   362: aload_3
    //   363: invokevirtual 183	java/io/InputStream:close	()V
    //   366: aload 4
    //   368: ifnull +8 -> 376
    //   371: aload 4
    //   373: invokevirtual 184	java/io/RandomAccessFile:close	()V
    //   376: aload_2
    //   377: ifnull -144 -> 233
    //   380: aload_2
    //   381: invokevirtual 187	java/net/HttpURLConnection:disconnect	()V
    //   384: goto -151 -> 233
    //   387: astore 10
    //   389: aload 10
    //   391: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   394: goto -161 -> 233
    //   397: ldc2_w 227
    //   400: invokestatic 194	java/lang/Thread:sleep	(J)V
    //   403: goto -221 -> 182
    //   406: astore 11
    //   408: aload 20
    //   410: astore 4
    //   412: aload 11
    //   414: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   417: aload_0
    //   418: iconst_0
    //   419: putfield 32	com/tg/jiadeonline/utils/AsyncQuXiao:finished	Z
    //   422: aload_3
    //   423: ifnull -190 -> 233
    //   426: aload_3
    //   427: invokevirtual 183	java/io/InputStream:close	()V
    //   430: aload 4
    //   432: ifnull +8 -> 440
    //   435: aload 4
    //   437: invokevirtual 184	java/io/RandomAccessFile:close	()V
    //   440: aload_2
    //   441: ifnull -208 -> 233
    //   444: aload_2
    //   445: invokevirtual 187	java/net/HttpURLConnection:disconnect	()V
    //   448: goto -215 -> 233
    //   451: astore 12
    //   453: aload 12
    //   455: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   458: goto -225 -> 233
    //   461: astore 13
    //   463: aload 13
    //   465: invokevirtual 229	java/lang/InterruptedException:printStackTrace	()V
    //   468: aload_0
    //   469: iconst_0
    //   470: putfield 32	com/tg/jiadeonline/utils/AsyncQuXiao:finished	Z
    //   473: aload_3
    //   474: ifnull -241 -> 233
    //   477: aload_3
    //   478: invokevirtual 183	java/io/InputStream:close	()V
    //   481: aload 4
    //   483: ifnull +8 -> 491
    //   486: aload 4
    //   488: invokevirtual 184	java/io/RandomAccessFile:close	()V
    //   491: aload_2
    //   492: ifnull -259 -> 233
    //   495: aload_2
    //   496: invokevirtual 187	java/net/HttpURLConnection:disconnect	()V
    //   499: goto -266 -> 233
    //   502: astore 14
    //   504: aload 14
    //   506: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   509: goto -276 -> 233
    //   512: astore 8
    //   514: aload_0
    //   515: iconst_0
    //   516: putfield 32	com/tg/jiadeonline/utils/AsyncQuXiao:finished	Z
    //   519: aload_3
    //   520: ifnull +25 -> 545
    //   523: aload_3
    //   524: invokevirtual 183	java/io/InputStream:close	()V
    //   527: aload 4
    //   529: ifnull +8 -> 537
    //   532: aload 4
    //   534: invokevirtual 184	java/io/RandomAccessFile:close	()V
    //   537: aload_2
    //   538: ifnull +7 -> 545
    //   541: aload_2
    //   542: invokevirtual 187	java/net/HttpURLConnection:disconnect	()V
    //   545: aload 8
    //   547: athrow
    //   548: astore 9
    //   550: aload 9
    //   552: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   555: goto -10 -> 545
    //   558: astore 26
    //   560: aload 26
    //   562: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   565: goto -332 -> 233
    //   568: astore 8
    //   570: aconst_null
    //   571: astore 4
    //   573: goto -59 -> 514
    //   576: astore 8
    //   578: aload 20
    //   580: astore 4
    //   582: goto -68 -> 514
    //   585: astore 13
    //   587: aconst_null
    //   588: astore 4
    //   590: goto -127 -> 463
    //   593: astore 13
    //   595: aload 20
    //   597: astore 4
    //   599: goto -136 -> 463
    //   602: astore 11
    //   604: aconst_null
    //   605: astore_2
    //   606: aconst_null
    //   607: astore_3
    //   608: aconst_null
    //   609: astore 4
    //   611: goto -199 -> 412
    //   614: astore 11
    //   616: aconst_null
    //   617: astore 4
    //   619: goto -207 -> 412
    //   622: astore 7
    //   624: aconst_null
    //   625: astore_2
    //   626: aconst_null
    //   627: astore_3
    //   628: aconst_null
    //   629: astore 4
    //   631: goto -283 -> 348
    //   634: astore 7
    //   636: aconst_null
    //   637: astore 4
    //   639: goto -291 -> 348
    //
    // Exception table:
    //   from	to	target	type
    //   141	155	342	java/net/MalformedURLException
    //   158	182	342	java/net/MalformedURLException
    //   182	189	342	java/net/MalformedURLException
    //   189	202	342	java/net/MalformedURLException
    //   237	243	342	java/net/MalformedURLException
    //   243	258	342	java/net/MalformedURLException
    //   264	274	342	java/net/MalformedURLException
    //   281	311	342	java/net/MalformedURLException
    //   318	339	342	java/net/MalformedURLException
    //   397	403	342	java/net/MalformedURLException
    //   362	366	387	java/io/IOException
    //   371	376	387	java/io/IOException
    //   380	384	387	java/io/IOException
    //   141	155	406	java/io/IOException
    //   158	182	406	java/io/IOException
    //   182	189	406	java/io/IOException
    //   189	202	406	java/io/IOException
    //   237	243	406	java/io/IOException
    //   243	258	406	java/io/IOException
    //   264	274	406	java/io/IOException
    //   281	311	406	java/io/IOException
    //   318	339	406	java/io/IOException
    //   397	403	406	java/io/IOException
    //   426	430	451	java/io/IOException
    //   435	440	451	java/io/IOException
    //   444	448	451	java/io/IOException
    //   12	24	461	java/lang/InterruptedException
    //   477	481	502	java/io/IOException
    //   486	491	502	java/io/IOException
    //   495	499	502	java/io/IOException
    //   12	24	512	finally
    //   348	353	512	finally
    //   412	417	512	finally
    //   463	468	512	finally
    //   523	527	548	java/io/IOException
    //   532	537	548	java/io/IOException
    //   541	545	548	java/io/IOException
    //   211	215	558	java/io/IOException
    //   220	225	558	java/io/IOException
    //   229	233	558	java/io/IOException
    //   24	137	568	finally
    //   141	155	576	finally
    //   158	182	576	finally
    //   182	189	576	finally
    //   189	202	576	finally
    //   237	243	576	finally
    //   243	258	576	finally
    //   264	274	576	finally
    //   281	311	576	finally
    //   318	339	576	finally
    //   397	403	576	finally
    //   24	137	585	java/lang/InterruptedException
    //   141	155	593	java/lang/InterruptedException
    //   158	182	593	java/lang/InterruptedException
    //   182	189	593	java/lang/InterruptedException
    //   189	202	593	java/lang/InterruptedException
    //   237	243	593	java/lang/InterruptedException
    //   243	258	593	java/lang/InterruptedException
    //   264	274	593	java/lang/InterruptedException
    //   281	311	593	java/lang/InterruptedException
    //   318	339	593	java/lang/InterruptedException
    //   397	403	593	java/lang/InterruptedException
    //   12	24	602	java/io/IOException
    //   24	137	614	java/io/IOException
    //   12	24	622	java/net/MalformedURLException
    //   24	137	634	java/net/MalformedURLException
  }

  public ProgressBar getPb()
  {
    return this.pb;
  }

  public String getUrl()
  {
    return this.url;
  }

  public boolean isPaused()
  {
    return this.paused;
  }

  public void onCancelled()
  {
    Log.d("debug", "onCancelled");
    this.finished = false;
    super.onCancelled();
  }

  protected void onPostExecute(String paramString)
  {
    super.onPostExecute(paramString);
  }

  protected void onPreExecute()
  {
    super.onPreExecute();
  }

  protected void onProgressUpdate(Integer[] paramArrayOfInteger)
  {
    System.out.println("进度值：" + paramArrayOfInteger[0]);
    this.pb.setProgress(paramArrayOfInteger[0].intValue());
    super.onProgressUpdate(paramArrayOfInteger);
  }

  public void pause()
  {
    this.paused = true;
    Log.d("debug", "paused------------" + this.paused);
  }

  public void sentMassage(int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = paramInt;
    this.handler.sendMessage(localMessage);
  }

  public void setPb(ProgressBar paramProgressBar)
  {
    this.pb = paramProgressBar;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.AsyncQuXiao
 * JD-Core Version:    0.6.2
 */