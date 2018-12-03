package com.kfb.c;

import android.app.Activity;
import android.content.Context;

public class Kfb
{
  static d a;
  private static Kfb b;

  private static void a(Context paramContext)
  {
    Class localClass1 = a.a(paramContext, Kf.class);
    String str1 = Kf.class.getName();
    if ((localClass1 != null) && (localClass1.getName() != null))
      str1 = localClass1.getName();
    a(paramContext, c.f, paramContext, str1, Context.class, String.class);
    Class localClass2 = a.a(paramContext, Za.class);
    String str2 = Za.class.getName();
    if ((localClass2 != null) && (localClass2.getName() != null))
      str2 = localClass2.getName();
    a(paramContext, c.u, paramContext, str2, Context.class, String.class);
  }

  private static void a(Context paramContext, String paramString, Object paramObject1, Object paramObject2, Class paramClass1, Class paramClass2)
  {
    a = d.a(paramContext, c.c);
    Object[] arrayOfObject = { paramObject1, paramObject2 };
    Class[] arrayOfClass = { paramClass1, paramClass2 };
    a.a(paramString, arrayOfObject, arrayOfClass);
  }

  public static Kfb getInstance(Context paramContext)
  {
    if (b == null)
      b = new Kfb();
    c.a(paramContext);
    a.a(paramContext);
    a(paramContext);
    d locald = d.a(paramContext, c.c);
    a = locald;
    locald.a(c.k, paramContext, Context.class);
    return b;
  }

  public static Kfb getInstance(Context paramContext, String paramString)
  {
    if (b == null)
      b = new Kfb();
    c.a(paramContext);
    a.a(paramContext);
    a(paramContext);
    a = d.a(paramContext, c.c);
    a(paramContext, c.k, paramContext, paramString, Context.class, String.class);
    return b;
  }

  public static Kfb getInstance(Context paramContext, String paramString1, String paramString2)
  {
    if (b == null)
      b = new Kfb();
    c.a(paramContext);
    a.a(paramContext);
    a(paramContext);
    a = d.a(paramContext, c.c);
    Object[] arrayOfObject = { paramContext, paramString1, paramString2 };
    Class[] arrayOfClass = { Context.class, String.class, String.class };
    a.a(c.k, arrayOfObject, arrayOfClass);
    return b;
  }

  public void init(Context paramContext)
  {
    a(paramContext, c.d, paramContext, Integer.valueOf(2), Context.class, Integer.TYPE);
  }

  public void setTextContent(Context paramContext, String paramString)
  {
    a = d.a(paramContext, c.c);
    Object[] arrayOfObject = { paramContext, paramString, "" };
    Class[] arrayOfClass = { Context.class, String.class, String.class };
    a.a(c.y, arrayOfObject, arrayOfClass);
  }

  public void setThemeStyle(Context paramContext, int paramInt)
  {
    a(paramContext, c.e, Integer.valueOf(paramInt), paramContext, Integer.TYPE, Context.class);
  }

  public void setZnum(Context paramContext, int paramInt)
  {
    a(paramContext, c.w, paramContext, Integer.valueOf(paramInt), Context.class, Integer.TYPE);
  }

  public void showExit(Activity paramActivity)
  {
    d locald = d.a(paramActivity, c.c);
    a = locald;
    locald.a(c.x, paramActivity, Context.class);
  }

  public void showlist(Context paramContext)
  {
    d locald = d.a(paramContext, c.c);
    a = locald;
    locald.a(c.d, paramContext, Context.class);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.c.Kfb
 * JD-Core Version:    0.6.2
 */