package com.tg.jiadeonline.utils;

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

public class Async extends AsyncTask<String, Integer, String>
{
  public static Context ctx = null;
  public static Dialog dialog = null;
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
      Async.dialog.dismiss();
      Toast.makeText(Async.ctx, 2131165203, 1).show();
      Log.v("test", "--->>>  end");
    }
  };
  private boolean paused = false;
  private ProgressBar pb;
  private String url = "";

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
    //   12: new 126	java/net/URL
    //   15: dup
    //   16: aload_1
    //   17: iconst_0
    //   18: aaload
    //   19: invokespecial 127	java/net/URL:<init>	(Ljava/lang/String;)V
    //   22: astore 6
    //   24: aload 6
    //   26: invokevirtual 131	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   29: checkcast 133	java/net/HttpURLConnection
    //   32: astore_2
    //   33: aload_2
    //   34: iconst_1
    //   35: invokevirtual 137	java/net/HttpURLConnection:setAllowUserInteraction	(Z)V
    //   38: aload_2
    //   39: invokevirtual 141	java/net/HttpURLConnection:getContentLength	()I
    //   42: istore 15
    //   44: ldc 100
    //   46: new 63	java/lang/StringBuilder
    //   49: dup
    //   50: ldc 143
    //   52: invokespecial 66	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   55: iload 15
    //   57: invokevirtual 146	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   60: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 111	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   66: pop
    //   67: aload_2
    //   68: invokevirtual 150	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   71: astore_3
    //   72: new 63	java/lang/StringBuilder
    //   75: dup
    //   76: aload 5
    //   78: invokestatic 156	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   81: invokespecial 66	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   84: ldc 158
    //   86: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_1
    //   90: iconst_2
    //   91: aaload
    //   92: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: ldc 160
    //   97: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: astore 17
    //   105: new 46	java/io/File
    //   108: dup
    //   109: aload 17
    //   111: invokespecial 48	java/io/File:<init>	(Ljava/lang/String;)V
    //   114: astore 18
    //   116: ldc 100
    //   118: aload 17
    //   120: invokestatic 111	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   123: pop
    //   124: new 162	java/io/RandomAccessFile
    //   127: dup
    //   128: aload 18
    //   130: ldc 164
    //   132: invokespecial 167	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   135: astore 20
    //   137: iconst_0
    //   138: i2l
    //   139: lstore 21
    //   141: aload 20
    //   143: lload 21
    //   145: invokevirtual 171	java/io/RandomAccessFile:seek	(J)V
    //   148: sipush 10240
    //   151: newarray byte
    //   153: astore 23
    //   155: iconst_0
    //   156: istore 24
    //   158: ldc 100
    //   160: new 63	java/lang/StringBuilder
    //   163: dup
    //   164: ldc 173
    //   166: invokespecial 66	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   169: aload 23
    //   171: arraylength
    //   172: invokevirtual 146	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   175: invokevirtual 74	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   178: invokestatic 111	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   181: pop
    //   182: aload_0
    //   183: getfield 29	com/tg/jiadeonline/utils/Async:finished	Z
    //   186: ifne +57 -> 243
    //   189: aload_3
    //   190: invokevirtual 178	java/io/InputStream:close	()V
    //   193: aload 20
    //   195: invokevirtual 179	java/io/RandomAccessFile:close	()V
    //   198: aload_2
    //   199: invokevirtual 182	java/net/HttpURLConnection:disconnect	()V
    //   202: aload_0
    //   203: iconst_0
    //   204: putfield 29	com/tg/jiadeonline/utils/Async:finished	Z
    //   207: aload_3
    //   208: ifnull +361 -> 569
    //   211: aload_3
    //   212: invokevirtual 178	java/io/InputStream:close	()V
    //   215: aload 20
    //   217: ifnull +8 -> 225
    //   220: aload 20
    //   222: invokevirtual 179	java/io/RandomAccessFile:close	()V
    //   225: aload_2
    //   226: ifnull +343 -> 569
    //   229: aload_2
    //   230: invokevirtual 182	java/net/HttpURLConnection:disconnect	()V
    //   233: aload_1
    //   234: iconst_0
    //   235: aaload
    //   236: areturn
    //   237: ldc2_w 183
    //   240: invokestatic 189	java/lang/Thread:sleep	(J)V
    //   243: aload_0
    //   244: getfield 31	com/tg/jiadeonline/utils/Async:paused	Z
    //   247: ifne -10 -> 237
    //   250: aload_3
    //   251: aload 23
    //   253: invokevirtual 193	java/io/InputStream:read	([B)I
    //   256: istore 27
    //   258: iload 27
    //   260: iconst_m1
    //   261: if_icmpeq -72 -> 189
    //   264: aload 20
    //   266: aload 23
    //   268: iconst_0
    //   269: iload 27
    //   271: invokevirtual 197	java/io/RandomAccessFile:write	([BII)V
    //   274: iload 24
    //   276: iload 27
    //   278: iadd
    //   279: istore 24
    //   281: iconst_1
    //   282: anewarray 199	java/lang/Integer
    //   285: astore 28
    //   287: aload 28
    //   289: iconst_0
    //   290: ldc 200
    //   292: iload 24
    //   294: i2f
    //   295: fmul
    //   296: iload 15
    //   298: i2f
    //   299: fdiv
    //   300: f2i
    //   301: invokestatic 203	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   304: aastore
    //   305: aload_0
    //   306: aload 28
    //   308: invokevirtual 207	com/tg/jiadeonline/utils/Async:publishProgress	([Ljava/lang/Object;)V
    //   311: iload 24
    //   313: iload 15
    //   315: if_icmpne +86 -> 401
    //   318: aload_0
    //   319: iconst_2
    //   320: invokevirtual 211	com/tg/jiadeonline/utils/Async:sentMassage	(I)V
    //   323: aload 17
    //   325: ldc 160
    //   327: invokevirtual 215	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   330: ifeq -141 -> 189
    //   333: aload_0
    //   334: aload 17
    //   336: invokespecial 217	com/tg/jiadeonline/utils/Async:setupApp	(Ljava/lang/String;)V
    //   339: iconst_0
    //   340: putstatic 222	com/tg/jiadeonline/utils/CommonRef:checkupdate	Z
    //   343: goto -154 -> 189
    //   346: astore 7
    //   348: aload 20
    //   350: astore 4
    //   352: aload 7
    //   354: invokevirtual 225	java/net/MalformedURLException:printStackTrace	()V
    //   357: aload_0
    //   358: iconst_0
    //   359: putfield 29	com/tg/jiadeonline/utils/Async:finished	Z
    //   362: aload_3
    //   363: ifnull -130 -> 233
    //   366: aload_3
    //   367: invokevirtual 178	java/io/InputStream:close	()V
    //   370: aload 4
    //   372: ifnull +8 -> 380
    //   375: aload 4
    //   377: invokevirtual 179	java/io/RandomAccessFile:close	()V
    //   380: aload_2
    //   381: ifnull -148 -> 233
    //   384: aload_2
    //   385: invokevirtual 182	java/net/HttpURLConnection:disconnect	()V
    //   388: goto -155 -> 233
    //   391: astore 10
    //   393: aload 10
    //   395: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   398: goto -165 -> 233
    //   401: ldc2_w 227
    //   404: invokestatic 189	java/lang/Thread:sleep	(J)V
    //   407: goto -225 -> 182
    //   410: astore 11
    //   412: aload 20
    //   414: astore 4
    //   416: aload 11
    //   418: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   421: aload_0
    //   422: iconst_0
    //   423: putfield 29	com/tg/jiadeonline/utils/Async:finished	Z
    //   426: aload_3
    //   427: ifnull -194 -> 233
    //   430: aload_3
    //   431: invokevirtual 178	java/io/InputStream:close	()V
    //   434: aload 4
    //   436: ifnull +8 -> 444
    //   439: aload 4
    //   441: invokevirtual 179	java/io/RandomAccessFile:close	()V
    //   444: aload_2
    //   445: ifnull -212 -> 233
    //   448: aload_2
    //   449: invokevirtual 182	java/net/HttpURLConnection:disconnect	()V
    //   452: goto -219 -> 233
    //   455: astore 12
    //   457: aload 12
    //   459: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   462: goto -229 -> 233
    //   465: astore 13
    //   467: aload 13
    //   469: invokevirtual 229	java/lang/InterruptedException:printStackTrace	()V
    //   472: aload_0
    //   473: iconst_0
    //   474: putfield 29	com/tg/jiadeonline/utils/Async:finished	Z
    //   477: aload_3
    //   478: ifnull -245 -> 233
    //   481: aload_3
    //   482: invokevirtual 178	java/io/InputStream:close	()V
    //   485: aload 4
    //   487: ifnull +8 -> 495
    //   490: aload 4
    //   492: invokevirtual 179	java/io/RandomAccessFile:close	()V
    //   495: aload_2
    //   496: ifnull -263 -> 233
    //   499: aload_2
    //   500: invokevirtual 182	java/net/HttpURLConnection:disconnect	()V
    //   503: goto -270 -> 233
    //   506: astore 14
    //   508: aload 14
    //   510: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   513: goto -280 -> 233
    //   516: astore 8
    //   518: aload_0
    //   519: iconst_0
    //   520: putfield 29	com/tg/jiadeonline/utils/Async:finished	Z
    //   523: aload_3
    //   524: ifnull +25 -> 549
    //   527: aload_3
    //   528: invokevirtual 178	java/io/InputStream:close	()V
    //   531: aload 4
    //   533: ifnull +8 -> 541
    //   536: aload 4
    //   538: invokevirtual 179	java/io/RandomAccessFile:close	()V
    //   541: aload_2
    //   542: ifnull +7 -> 549
    //   545: aload_2
    //   546: invokevirtual 182	java/net/HttpURLConnection:disconnect	()V
    //   549: aload 8
    //   551: athrow
    //   552: astore 9
    //   554: aload 9
    //   556: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   559: goto -10 -> 549
    //   562: astore 26
    //   564: aload 26
    //   566: invokevirtual 226	java/io/IOException:printStackTrace	()V
    //   569: goto -336 -> 233
    //   572: astore 8
    //   574: aconst_null
    //   575: astore 4
    //   577: goto -59 -> 518
    //   580: astore 8
    //   582: aload 20
    //   584: astore 4
    //   586: goto -68 -> 518
    //   589: astore 13
    //   591: aconst_null
    //   592: astore 4
    //   594: goto -127 -> 467
    //   597: astore 13
    //   599: aload 20
    //   601: astore 4
    //   603: goto -136 -> 467
    //   606: astore 11
    //   608: aconst_null
    //   609: astore_2
    //   610: aconst_null
    //   611: astore_3
    //   612: aconst_null
    //   613: astore 4
    //   615: goto -199 -> 416
    //   618: astore 11
    //   620: aconst_null
    //   621: astore 4
    //   623: goto -207 -> 416
    //   626: astore 7
    //   628: aconst_null
    //   629: astore_2
    //   630: aconst_null
    //   631: astore_3
    //   632: aconst_null
    //   633: astore 4
    //   635: goto -283 -> 352
    //   638: astore 7
    //   640: aconst_null
    //   641: astore 4
    //   643: goto -291 -> 352
    //
    // Exception table:
    //   from	to	target	type
    //   141	155	346	java/net/MalformedURLException
    //   158	182	346	java/net/MalformedURLException
    //   182	189	346	java/net/MalformedURLException
    //   189	202	346	java/net/MalformedURLException
    //   237	243	346	java/net/MalformedURLException
    //   243	258	346	java/net/MalformedURLException
    //   264	274	346	java/net/MalformedURLException
    //   281	311	346	java/net/MalformedURLException
    //   318	343	346	java/net/MalformedURLException
    //   401	407	346	java/net/MalformedURLException
    //   366	370	391	java/io/IOException
    //   375	380	391	java/io/IOException
    //   384	388	391	java/io/IOException
    //   141	155	410	java/io/IOException
    //   158	182	410	java/io/IOException
    //   182	189	410	java/io/IOException
    //   189	202	410	java/io/IOException
    //   237	243	410	java/io/IOException
    //   243	258	410	java/io/IOException
    //   264	274	410	java/io/IOException
    //   281	311	410	java/io/IOException
    //   318	343	410	java/io/IOException
    //   401	407	410	java/io/IOException
    //   430	434	455	java/io/IOException
    //   439	444	455	java/io/IOException
    //   448	452	455	java/io/IOException
    //   12	24	465	java/lang/InterruptedException
    //   481	485	506	java/io/IOException
    //   490	495	506	java/io/IOException
    //   499	503	506	java/io/IOException
    //   12	24	516	finally
    //   352	357	516	finally
    //   416	421	516	finally
    //   467	472	516	finally
    //   527	531	552	java/io/IOException
    //   536	541	552	java/io/IOException
    //   545	549	552	java/io/IOException
    //   211	215	562	java/io/IOException
    //   220	225	562	java/io/IOException
    //   229	233	562	java/io/IOException
    //   24	137	572	finally
    //   141	155	580	finally
    //   158	182	580	finally
    //   182	189	580	finally
    //   189	202	580	finally
    //   237	243	580	finally
    //   243	258	580	finally
    //   264	274	580	finally
    //   281	311	580	finally
    //   318	343	580	finally
    //   401	407	580	finally
    //   24	137	589	java/lang/InterruptedException
    //   141	155	597	java/lang/InterruptedException
    //   158	182	597	java/lang/InterruptedException
    //   182	189	597	java/lang/InterruptedException
    //   189	202	597	java/lang/InterruptedException
    //   237	243	597	java/lang/InterruptedException
    //   243	258	597	java/lang/InterruptedException
    //   264	274	597	java/lang/InterruptedException
    //   281	311	597	java/lang/InterruptedException
    //   318	343	597	java/lang/InterruptedException
    //   401	407	597	java/lang/InterruptedException
    //   12	24	606	java/io/IOException
    //   24	137	618	java/io/IOException
    //   12	24	626	java/net/MalformedURLException
    //   24	137	638	java/net/MalformedURLException
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
 * Qualified Name:     com.tg.jiadeonline.utils.Async
 * JD-Core Version:    0.6.2
 */