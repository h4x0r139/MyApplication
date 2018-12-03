package com.unionpay.mobile.android.model;

public final class a
  implements c
{
  private int a = 2;
  private int b = 0;
  private String c = null;
  private String d = null;
  private String e = null;
  private String f;

  public a()
  {
    this.c = null;
    this.d = null;
    this.e = null;
  }

  private a(String paramString1, String paramString2, String paramString3)
  {
    this(paramString1, paramString2, paramString3, (byte)0);
  }

  private a(String paramString1, String paramString2, String paramString3, byte paramByte)
  {
    this.b = 0;
    this.a = 2;
    this.c = paramString1;
    this.d = paramString2;
    this.e = paramString3;
  }

  public a(String paramString1, String paramString2, String paramString3, char paramChar)
  {
    this(paramString1, paramString2, paramString3);
  }

  public final String a()
  {
    return this.c;
  }

  public final void a(String paramString)
  {
    this.f = paramString;
  }

  public final String b()
  {
    return this.e;
  }

  public final int c()
  {
    return this.b;
  }

  public final String d()
  {
    if (this.b == 0)
      return this.d;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.f;
    return String.format("<font color=\"#FF0000\">%s</font>", arrayOfObject);
  }

  public final String toString()
  {
    return this.c + "," + this.d + "," + this.e;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.model.a
 * JD-Core Version:    0.6.2
 */