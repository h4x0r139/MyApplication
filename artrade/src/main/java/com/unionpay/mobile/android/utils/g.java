package com.unionpay.mobile.android.utils;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public final class g
{
  private static boolean a = false;
  private static int b = 2147483647;

  private static int a(int paramInt, String paramString1, String paramString2)
  {
    int i = 0;
    if (paramString1 != null)
    {
      i = 0;
      if (paramString2 != null)
      {
        i = 0;
        switch (paramInt)
        {
        default:
        case 3:
        case 6:
        case 4:
        case 2:
        case 5:
        }
      }
    }
    while (true)
    {
      String str1;
      if (a)
        str1 = "[ ERROR ] " + paramString1 + ":" + paramString2;
      try
      {
        File localFile1 = Environment.getExternalStorageDirectory();
        File localFile2 = new File(localFile1.getAbsolutePath() + File.separator + "upmp_log.txt");
        if (!localFile2.exists())
          localFile2.createNewFile();
        String str2 = str1 + "\n";
        FileOutputStream localFileOutputStream = new FileOutputStream(localFile2, true);
        localFileOutputStream.write(str2.getBytes());
        localFileOutputStream.close();
        return i;
        i = Log.d(paramString1, paramString2);
        continue;
        i = Log.e(paramString1, paramString2);
        continue;
        i = Log.i(paramString1, paramString2);
        continue;
        i = Log.v(paramString1, paramString2);
        continue;
        i = Log.w(paramString1, paramString2);
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
    return i;
  }

  public static int a(String paramString1, String paramString2)
  {
    if (b <= 3)
      a(3, paramString1, paramString2);
    return 0;
  }

  public static int b(String paramString1, String paramString2)
  {
    if (b <= 4)
      a(4, paramString1, paramString2);
    return 0;
  }

  public static int c(String paramString1, String paramString2)
  {
    int i = b;
    int j = 0;
    if (i <= 6)
      j = a(6, paramString1, paramString2);
    return j;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.g
 * JD-Core Version:    0.6.2
 */