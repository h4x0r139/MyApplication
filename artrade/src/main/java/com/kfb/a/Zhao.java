package com.kfb.a;

import android.content.Context;
import android.content.IntentFilter;

public class Zhao
{
  static d a;
  private static Zhao b;
  private static boolean d = false;
  private Dknd c = null;

  private static void a(Context paramContext)
  {
    Zhao localZhao;
    Context localContext;
    if (b != null)
    {
      localZhao = b;
      localContext = paramContext.getApplicationContext();
      if ((!d) || (localZhao.c == null));
    }
    try
    {
      d = false;
      localContext.unregisterReceiver(localZhao.c);
    }
    catch (Exception localException2)
    {
      try
      {
        while (true)
        {
          IntentFilter localIntentFilter = new IntentFilter();
          localIntentFilter.addAction(c.y);
          localIntentFilter.addAction(c.z);
          localIntentFilter.addDataScheme(c.A);
          if (localZhao.c == null)
            localZhao.c = new Dknd();
          localContext.registerReceiver(localZhao.c, localIntentFilter);
          d = true;
          return;
          localException2 = localException2;
          localException2.printStackTrace();
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
  }

  private static void a(Context paramContext, String paramString, Object paramObject1, Object paramObject2, Class paramClass1, Class paramClass2)
  {
    a = d.a(paramContext, c.c);
    Object[] arrayOfObject = { paramObject1, paramObject2 };
    Class[] arrayOfClass = { paramClass1, paramClass2 };
    a.a(paramString, arrayOfObject, arrayOfClass);
  }

  public static Zhao getInstance(Context paramContext)
  {
    if (b == null)
      b = new Zhao();
    c.a(paramContext);
    a.a(paramContext);
    a(paramContext);
    d locald = d.a(paramContext, c.c);
    a = locald;
    locald.a(c.q, paramContext, Context.class);
    return b;
  }

  public static Zhao getInstance(Context paramContext, String paramString)
  {
    if (b == null)
      b = new Zhao();
    c.a(paramContext);
    a(paramContext);
    a.a(paramContext);
    a = d.a(paramContext, c.c);
    a(paramContext, c.q, paramContext, paramString, Context.class, String.class);
    return b;
  }

  public static Zhao getInstance(Context paramContext, String paramString1, String paramString2)
  {
    if (b == null)
      b = new Zhao();
    c.a(paramContext);
    a(paramContext);
    a.a(paramContext);
    a = d.a(paramContext, c.c);
    Object[] arrayOfObject = { paramContext, paramString1, paramString2 };
    Class[] arrayOfClass = { Context.class, String.class, String.class };
    a.a(c.q, arrayOfObject, arrayOfClass);
    return b;
  }

  public void config(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    a(paramContext, c.e, paramContext, Integer.valueOf(paramInt1), Context.class, Integer.TYPE);
    a(paramContext, c.f, paramContext, Integer.valueOf(paramInt2), Context.class, Integer.TYPE);
    a(paramContext, c.g, paramContext, Boolean.valueOf(paramBoolean1), Context.class, Boolean.TYPE);
    a(paramContext, c.h, paramContext, Boolean.valueOf(paramBoolean2), Context.class, Boolean.TYPE);
    a(paramContext, c.j, paramContext, Boolean.valueOf(paramBoolean3), Context.class, Boolean.TYPE);
  }

  public void load(Context paramContext)
  {
    d locald = d.a(paramContext, c.c);
    a = locald;
    locald.a(c.l, paramContext, Context.class);
  }

  public void setLa(Context paramContext)
  {
    String str = Qim.class.getName();
    a(paramContext, c.d, paramContext, str, Context.class, String.class);
  }

  public void show(Context paramContext)
  {
    d locald = d.a(paramContext, c.c);
    a = locald;
    locald.a(c.k, paramContext, Context.class);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.a.Zhao
 * JD-Core Version:    0.6.2
 */