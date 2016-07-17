package com.unionpay.mobile.android.net;

public class HttpNative
{
  private static HttpNative a = null;

  public static HttpNative a()
  {
    if (a == null)
      a = new HttpNative();
    return a;
  }

  public native String getIssuer(int paramInt);

  public native String getSubject(int paramInt);
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.net.HttpNative
 * JD-Core Version:    0.6.2
 */