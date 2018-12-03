package com.unionpay.mobile.android.global;

import android.content.Context;
import com.unionpay.mobile.android.utils.c;

public final class b
{
  public static int a = 12;
  public static int b = -1;
  public static int c = -1;
  public static int d = -1;
  public static int e = 4;
  public static int f = 8;
  public static int g = 12;
  public static int h = 16;
  public static float i = 18.0F;
  public static float j = 17.0F;
  public static float k = 16.0F;
  public static float l = 14.0F;
  public static float m = 12.0F;
  public static int n = 54;
  public static int o = 20;
  public static int p = 60;
  private static boolean q = false;

  public static final void a(Context paramContext)
  {
    if (!q)
    {
      a = c.a(paramContext, a);
      n = c.a(paramContext, n);
      o = c.a(paramContext, o);
      e = c.a(paramContext, e);
      f = c.a(paramContext, f);
      g = c.a(paramContext, g);
      h = c.a(paramContext, h);
      q = true;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.global.b
 * JD-Core Version:    0.6.2
 */