package com.unionpay.mobile.android.utils;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class h
{
  public static boolean a(byte[] paramArrayOfByte)
  {
    File localFile1 = new File(Environment.getExternalStorageDirectory(), "UPPay");
    localFile1.mkdir();
    File localFile2 = new File(localFile1, "UPPayPluginEx.apk");
    try
    {
      localFile2.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      localFileOutputStream.write(paramArrayOfByte);
      localFileOutputStream.close();
      return true;
    }
    catch (IOException localIOException)
    {
      g.a("uppay", "write2file error!!!!");
      localIOException.printStackTrace();
    }
    return false;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.h
 * JD-Core Version:    0.6.2
 */