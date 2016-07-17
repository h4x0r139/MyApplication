package com.tg.jiadeonline.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BitmapUtil
{
  public static final String DIR = "/sdcard/hypers";
  private static int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;
  private static int MB = 1048576;

  public static boolean Exist(String paramString)
  {
    return new File("/sdcard/hypers" + paramString).exists();
  }

  // ERROR //
  public static Bitmap GetBitmap(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_0
    //   7: invokestatic 53	java/net/URLEncoder:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   10: astore_2
    //   11: new 27	java/lang/StringBuilder
    //   14: dup
    //   15: ldc 55
    //   17: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   20: aload_2
    //   21: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   27: invokestatic 57	com/tg/jiadeonline/utils/BitmapUtil:Exist	(Ljava/lang/String;)Z
    //   30: ifeq +27 -> 57
    //   33: new 27	java/lang/StringBuilder
    //   36: dup
    //   37: ldc 55
    //   39: invokespecial 30	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   42: aload_2
    //   43: invokevirtual 34	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual 38	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   49: invokestatic 63	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   52: astore 6
    //   54: aload 6
    //   56: areturn
    //   57: new 65	java/net/URL
    //   60: dup
    //   61: aload_0
    //   62: invokespecial 66	java/net/URL:<init>	(Ljava/lang/String;)V
    //   65: astore_3
    //   66: aload_3
    //   67: invokevirtual 70	java/net/URL:openStream	()Ljava/io/InputStream;
    //   70: astore 5
    //   72: aload 5
    //   74: invokestatic 74	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   77: astore 6
    //   79: aload 6
    //   81: ifnull +10 -> 91
    //   84: aload 6
    //   86: aload_2
    //   87: iload_1
    //   88: invokestatic 78	com/tg/jiadeonline/utils/BitmapUtil:saveBmpToSd	(Landroid/graphics/Bitmap;Ljava/lang/String;I)V
    //   91: aload 5
    //   93: invokevirtual 83	java/io/InputStream:close	()V
    //   96: goto -42 -> 54
    //   99: astore 4
    //   101: aload 4
    //   103: invokevirtual 86	java/lang/Exception:printStackTrace	()V
    //   106: aconst_null
    //   107: areturn
    //   108: astore 4
    //   110: goto -9 -> 101
    //
    // Exception table:
    //   from	to	target	type
    //   57	66	99	java/lang/Exception
    //   66	79	108	java/lang/Exception
    //   84	91	108	java/lang/Exception
    //   91	96	108	java/lang/Exception
  }

  public static Bitmap ReadBitmapById(Context paramContext, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    return BitmapFactory.decodeStream(paramContext.getResources().openRawResource(paramInt), null, localOptions);
  }

  public static Bitmap ReadBitmapById(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    localOptions.inInputShareable = true;
    localOptions.inPurgeable = true;
    return getBitmap(BitmapFactory.decodeStream(paramContext.getResources().openRawResource(paramInt1), null, localOptions), paramInt2, paramInt3);
  }

  private static int freeSpaceOnSd()
  {
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    return (int)(localStatFs.getAvailableBlocks() * localStatFs.getBlockSize() / MB);
  }

  public static Bitmap getBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Log.e("jj", "图片宽度" + i + ",screenWidth=" + paramInt1);
    Matrix localMatrix = new Matrix();
    float f = paramInt1 / i;
    (paramInt2 / j);
    localMatrix.postScale(f, f);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }

  public static void saveBmpToSd(Bitmap paramBitmap, String paramString, int paramInt)
  {
    if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd());
    while (!"mounted".equals(Environment.getExternalStorageState()))
      return;
    File localFile1 = new File("/sdcard/hypers");
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File("/sdcard/hypers/" + paramString);
    try
    {
      localFile2.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      paramBitmap.compress(Bitmap.CompressFormat.PNG, paramInt, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.BitmapUtil
 * JD-Core Version:    0.6.2
 */