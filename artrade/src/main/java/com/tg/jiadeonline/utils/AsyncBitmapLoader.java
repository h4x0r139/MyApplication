package com.tg.jiadeonline.utils;

import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncBitmapLoader
{
  private static int sampleSize = 3;
  private ExecutorService executorService = Executors.newFixedThreadPool(3);
  private HashMap<String, SoftReference<BitmapDrawable>> imageCache = new HashMap();

  // ERROR //
  public static BitmapDrawable loadImageFromUrl(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 41	java/net/URL
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 44	java/net/URL:<init>	(Ljava/lang/String;)V
    //   10: invokevirtual 48	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   13: checkcast 50	java/net/HttpURLConnection
    //   16: astore 4
    //   18: new 52	android/graphics/BitmapFactory$Options
    //   21: dup
    //   22: invokespecial 53	android/graphics/BitmapFactory$Options:<init>	()V
    //   25: astore 5
    //   27: aload 5
    //   29: getstatic 15	com/tg/jiadeonline/utils/AsyncBitmapLoader:sampleSize	I
    //   32: putfield 56	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   35: new 58	android/graphics/drawable/BitmapDrawable
    //   38: dup
    //   39: aload 4
    //   41: invokevirtual 62	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   44: aconst_null
    //   45: aload 5
    //   47: invokestatic 68	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   50: invokespecial 71	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   53: astore 6
    //   55: aload 4
    //   57: invokevirtual 74	java/net/HttpURLConnection:disconnect	()V
    //   60: aload 6
    //   62: areturn
    //   63: astore_3
    //   64: getstatic 80	java/lang/System:out	Ljava/io/PrintStream;
    //   67: aload_3
    //   68: invokevirtual 84	java/net/MalformedURLException:getMessage	()Ljava/lang/String;
    //   71: invokevirtual 89	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   74: aload_3
    //   75: invokevirtual 92	java/net/MalformedURLException:printStackTrace	()V
    //   78: aload_1
    //   79: areturn
    //   80: astore_2
    //   81: getstatic 80	java/lang/System:out	Ljava/io/PrintStream;
    //   84: aload_2
    //   85: invokevirtual 93	java/io/IOException:getMessage	()Ljava/lang/String;
    //   88: invokevirtual 89	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   91: aload_2
    //   92: invokevirtual 94	java/io/IOException:printStackTrace	()V
    //   95: aload_1
    //   96: areturn
    //   97: astore_2
    //   98: aload 6
    //   100: astore_1
    //   101: goto -20 -> 81
    //   104: astore_3
    //   105: aload 6
    //   107: astore_1
    //   108: goto -44 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   2	55	63	java/net/MalformedURLException
    //   2	55	80	java/io/IOException
    //   55	60	97	java/io/IOException
    //   55	60	104	java/net/MalformedURLException
  }

  public BitmapDrawable loadBitmap(final String paramString, final ImageView paramImageView, final ImageCallBack paramImageCallBack)
  {
    if (this.imageCache.containsKey(paramString))
    {
      BitmapDrawable localBitmapDrawable = (BitmapDrawable)((SoftReference)this.imageCache.get(paramString)).get();
      if (localBitmapDrawable != null)
        return localBitmapDrawable;
    }
    final Handler local1 = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        paramImageCallBack.imageLoad(paramImageView, (BitmapDrawable)paramAnonymousMessage.obj);
      }
    };
    this.executorService.submit(new Thread()
    {
      public void run()
      {
        BitmapDrawable localBitmapDrawable = AsyncBitmapLoader.loadImageFromUrl(paramString);
        AsyncBitmapLoader.this.imageCache.put(paramString, new SoftReference(localBitmapDrawable));
        Message localMessage = local1.obtainMessage(0, localBitmapDrawable);
        local1.sendMessage(localMessage);
      }
    });
    return null;
  }

  public void setSampleSize(int paramInt)
  {
    sampleSize = paramInt;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.AsyncBitmapLoader
 * JD-Core Version:    0.6.2
 */