package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o extends LinearLayout
{
  private Context a;
  private int b = l.a.intValue();
  private int c = l.a.intValue();
  private JSONObject d;
  private JSONObject e;
  private JSONObject f;
  private Drawable g;
  private JSONArray h;
  private List<Map<String, Object>> i;
  private String j;
  private String k;
  private AbstractMethod l;
  private CViewMethods m;
  private Drawable n;
  private boolean o;

  private o(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext;
    setOrientation(1);
  }

  public static o a(Context paramContext, Drawable paramDrawable)
  {
    o localo = new o(paramContext);
    localo.g = paramDrawable;
    return localo;
  }

  public static o a(Context paramContext, Drawable paramDrawable1, Drawable paramDrawable2)
  {
    o localo = new o(paramContext);
    localo.n = paramDrawable2;
    localo.b = l.c.intValue();
    localo.c = l.c.intValue();
    localo.g = paramDrawable1;
    localo.c();
    return localo;
  }

  public static o a(Context paramContext, JSONArray paramJSONArray, List<Map<String, Object>> paramList, Drawable paramDrawable1, Drawable paramDrawable2, String paramString)
  {
    o localo = new o(paramContext);
    localo.n = paramDrawable2;
    localo.b = l.b.intValue();
    localo.c = l.b.intValue();
    localo.g = paramDrawable1;
    localo.h = paramJSONArray;
    localo.i = paramList;
    localo.k = paramString;
    localo.c();
    return localo;
  }

  public final int a()
  {
    return this.b;
  }

  public final o a(int paramInt)
  {
    this.c = paramInt;
    return this;
  }

  public final o a(Drawable paramDrawable)
  {
    this.n = paramDrawable;
    return this;
  }

  public final o a(a parama)
  {
    if (this.l != null)
    {
      this.l.a(parama);
      this.l.a(parama);
      if ((this.l instanceof b))
        ((b)this.l).a(parama);
    }
    if (this.m != null)
      this.m.a(parama);
    return this;
  }

  public final o a(String paramString)
  {
    this.j = paramString;
    return this;
  }

  public final o a(List<Map<String, Object>> paramList)
  {
    this.i = paramList;
    return this;
  }

  public final o a(JSONArray paramJSONArray)
  {
    this.h = paramJSONArray;
    return this;
  }

  public final o a(JSONObject paramJSONObject)
  {
    this.d = paramJSONObject;
    if ((this.l != null) && ((this.l instanceof b)))
      ((b)this.l).a(this.d);
    return this;
  }

  public final o a(boolean paramBoolean)
  {
    this.o = paramBoolean;
    return this;
  }

  public final o b(int paramInt)
  {
    this.b = paramInt;
    return this;
  }

  public final o b(Drawable paramDrawable)
  {
    if (this.l != null)
      this.l.a(paramDrawable);
    return this;
  }

  public final o b(String paramString)
  {
    this.k = paramString;
    return this;
  }

  public final o b(JSONObject paramJSONObject)
  {
    this.e = paramJSONObject;
    if ((this.l != null) && ((this.l instanceof i)))
      ((i)this.l).a(this.e);
    return this;
  }

  public final String b()
  {
    AbstractMethod localAbstractMethod = this.l;
    String str = null;
    if (localAbstractMethod != null)
    {
      boolean bool = this.l instanceof i;
      str = null;
      if (bool)
        str = ((i)this.l).h();
    }
    return str;
  }

  public final o c(JSONObject paramJSONObject)
  {
    this.f = paramJSONObject;
    if ((this.l != null) && ((this.l instanceof i)))
      ((i)this.l).b(this.f);
    return this;
  }

  public final void c()
  {
    this.l = null;
    if (this.b == l.b.intValue())
    {
      this.c &= (0xFFFFFFFF ^ l.b.intValue());
      b localb = new b(this.a, this.i, this.k);
      localb.b(com.unionpay.mobile.android.languages.c.by.bt);
      localb.d(com.unionpay.mobile.android.languages.c.by.bu);
      localb.a(this.d);
      localb.a(this.h);
      localb.b(this.o);
      localb.b(com.unionpay.mobile.android.resource.c.a(this.a).a(2014));
      com.unionpay.mobile.android.resource.c localc = com.unionpay.mobile.android.resource.c.a(this.a);
      localb.a(localc.a(1015), localc.a(1016), localc.a(1002));
      this.l = localb;
    }
    while (true)
    {
      if (this.l != null)
      {
        this.l.a();
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams2.topMargin = com.unionpay.mobile.android.global.b.a;
        addView(this.l, localLayoutParams2);
      }
      this.m = new CViewMethods(this.a);
      this.m.a(this.g);
      this.m.a(this.c);
      HashMap localHashMap1 = new HashMap();
      localHashMap1.put(l.b, com.unionpay.mobile.android.languages.c.by.bt);
      localHashMap1.put(l.d, this.j);
      localHashMap1.put(l.e, com.unionpay.mobile.android.languages.c.by.bo);
      localHashMap1.put(l.c, com.unionpay.mobile.android.languages.c.by.bv);
      this.m.a(localHashMap1);
      HashMap localHashMap2 = new HashMap();
      localHashMap2.put(l.b, this.n);
      localHashMap2.put(l.d, this.n);
      localHashMap2.put(l.e, this.n);
      localHashMap2.put(l.c, this.n);
      this.m.b(localHashMap2);
      this.m.a(com.unionpay.mobile.android.languages.c.by.bx).a();
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
      addView(this.m, localLayoutParams1);
      return;
      if (this.b == l.c.intValue())
      {
        this.c &= (0xFFFFFFFF ^ l.c.intValue());
        i locali = new i(this.a);
        locali.b(com.unionpay.mobile.android.languages.c.by.bv);
        locali.d(com.unionpay.mobile.android.languages.c.by.bw);
        locali.a(this.e);
        locali.b(this.f);
        this.l = locali;
      }
    }
  }

  public final void c(int paramInt)
  {
    if ((this.l != null) && ((this.l instanceof b)))
      ((b)this.l).a(paramInt);
  }

  public static abstract interface a extends AbstractMethod.a, AbstractMethod.b, CViewMethods.a, b.b
  {
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.o
 * JD-Core Version:    0.6.2
 */