package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.views.order.o;
import com.unionpay.mobile.android.views.order.o.a;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.mobile.android.widgets.m;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ao extends b
  implements a.b
{
  private int p = 0;
  private int q = -1;
  private Button r = null;
  private com.unionpay.mobile.android.upviews.a s = null;
  private o t;
  private boolean u = false;
  private Handler v = null;

  public ao(Context paramContext, d paramd)
  {
    super(paramContext, paramd);
    this.f = 2;
    setBackgroundColor(-1052684);
    e();
    if ((!TextUtils.isEmpty(this.a.p)) || (this.a.au))
      this.v = new Handler(new ap(this));
  }

  private void e(JSONObject paramJSONObject)
  {
    int i = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, false);
    if (i != 0)
      b(i);
    while (true)
    {
      this.p = 0;
      return;
      d locald = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
      if ((this.a.u != null) && (this.a.u.length() > 0))
        a(6, locald);
      else if ((this.a.y != null) && (this.a.y.length() > 0))
        d(5);
    }
  }

  private void f(JSONObject paramJSONObject)
  {
    int i = com.unionpay.mobile.android.nocard.utils.f.b(this.a, paramJSONObject);
    if (i != 0)
    {
      b(i);
      return;
    }
    if (this.t != null)
    {
      String str = this.t.b();
      if (!TextUtils.isEmpty(str))
        PreferenceUtils.c(this.d, str);
    }
    a(13, com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject));
    this.p = 0;
  }

  public final void a(a.a parama)
  {
  }

  public final void a(JSONObject paramJSONObject)
  {
    this.b.c();
    switch (this.p)
    {
    default:
    case 2:
      do
        return;
      while (c(paramJSONObject));
      e(paramJSONObject);
      return;
    case 1:
      com.unionpay.mobile.android.model.b localb3 = this.a;
      localb3.S = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "login_rules");
      localb3.T = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "register_url");
      if ((localb3.S != null) && (localb3.S.length() > 0))
        break;
    case 3:
    case 4:
    case 5:
    }
    for (int k = 2; ; k = 0)
    {
      if (k != 0)
        b(2);
      while (true)
      {
        this.p = 0;
        return;
        d(12);
      }
      com.unionpay.mobile.android.model.b localb1 = this.a;
      int j = this.q;
      if ((localb1.l != null) && (j < localb1.l.size()))
        localb1.l.remove(j);
      if ((this.a.l != null) && (this.a.l.size() > 0))
        if (this.q == this.a.I)
        {
          this.a.I = 0;
          this.t.c(this.a.I);
        }
      while (true)
      {
        this.p = 0;
        return;
        if (this.q >= this.a.I)
          break;
        com.unionpay.mobile.android.model.b localb2 = this.a;
        localb2.I = (-1 + localb2.I);
        break;
        this.a.I = -1;
        c();
      }
      if (c(paramJSONObject))
        break;
      f(paramJSONObject);
      return;
      j();
      int i = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, false);
      if (i != 0)
      {
        b(i);
        return;
      }
      d locald = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
      if ((this.a.u != null) && (this.a.u.length() > 0))
      {
        a(6, locald);
        return;
      }
      if ((this.a.y == null) || (this.a.y.length() <= 0))
        break;
      d(5);
      return;
    }
  }

  public final void a(boolean paramBoolean)
  {
    Button localButton;
    if ((this.r != null) && (this.r != null))
    {
      localButton = this.r;
      if (paramBoolean)
        break label31;
    }
    label31: for (boolean bool = true; ; bool = false)
    {
      localButton.setEnabled(bool);
      return;
    }
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    String str = this.a.j;
    ax localax = new ax(this.d, str, this);
    if (!this.a.E)
      if ((this.a.aF == l.a.intValue()) || ((this.a.aF == l.c.intValue()) && (this.a.au) && ((this.a.l == null) || (this.a.l.size() <= 0))))
        localax = new ax(this.d, str, this.c.a(1030), com.unionpay.mobile.android.utils.c.a(this.d, 20.0F), this);
    while (true)
    {
      localLayoutParams.addRule(13, -1);
      this.j.addView(localax, localLayoutParams);
      return;
      localax.a(com.unionpay.mobile.android.languages.c.by.l);
    }
  }

  public final void b(String paramString1, String paramString2)
  {
  }

  protected final void b(String paramString, JSONObject paramJSONObject)
  {
    if ("init".equals(paramString))
      if (this.a.E)
        n();
    do
    {
      return;
      if (!"".equals(paramString))
        break;
      if (this.p == 2)
      {
        e(paramJSONObject);
        return;
      }
    }
    while (this.p != 4);
    f(paramJSONObject);
    return;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    this.i = false;
    this.p = 5;
    this.e.b(paramString, "");
  }

  protected final void c()
  {
    this.l.removeAllViews();
    this.n.a(this);
    Drawable localDrawable1 = this.c.a(2014);
    Drawable localDrawable2 = this.c.a(1002);
    if ((this.a.aF == l.b.intValue()) || (this.a.E))
    {
      List localList = com.unionpay.mobile.android.nocard.views.xlistview.a.a(this.d, this.a.l, true);
      if ((this.a.E) && ((this.a.aF == l.b.intValue()) || (l.a.intValue() == this.a.aF)))
        localList = null;
      String str1 = com.unionpay.mobile.android.utils.f.a(this.a.r, "label");
      this.t = o.a(this.d, this.a.o, localList, localDrawable1, localDrawable2, str1);
    }
    while (true)
    {
      this.t.a(new a());
      this.t.b(this.a.ap);
      this.t.c(this.a.T);
      if (!TextUtils.isEmpty(this.a.n));
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("href", this.a.n);
        localJSONObject.put("title", com.unionpay.mobile.android.languages.c.by.k);
        localJSONObject.put("label", com.unionpay.mobile.android.languages.c.by.j);
        this.t.a(localJSONObject);
        Drawable localDrawable3 = this.c.a(2008);
        this.t.b(localDrawable3);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.l.addView(this.t, localLayoutParams);
        return;
        if (this.a.aF == l.c.intValue())
        {
          this.t = o.a(this.d, localDrawable1, localDrawable2);
          continue;
        }
        this.t = o.a(this.d, localDrawable1);
        o localo = this.t;
        localo.a(localDrawable2);
        int i = l.a.intValue();
        boolean bool = com.unionpay.mobile.android.nocard.utils.f.a(this.a.o);
        if (!bool)
        {
          String str2 = com.unionpay.mobile.android.utils.f.a(this.a.s, "label");
          if ((this.a.s != null) && (str2.length() > 0) && (this.a.ar) && (!this.a.au))
          {
            i |= l.d.intValue();
            localo.a(str2);
          }
          g.a("uppay", i);
          String str3 = com.unionpay.mobile.android.utils.f.a(this.a.q, "label");
          if ((this.a.q != null) && (str3.length() > 0))
            i |= l.c.intValue();
          g.a("uppay", i);
          String str4 = com.unionpay.mobile.android.utils.f.a(this.a.r, "label");
          if ((this.a.r != null) && (str4.length() > 0) && (this.a.l != null) && (this.a.l.size() > 0))
            localo.a(com.unionpay.mobile.android.nocard.views.xlistview.a.a(this.d, this.a.l, true));
          if ((this.a.as) && (this.a.at) && (!this.a.au))
            i |= l.e.intValue();
        }
        int j = i & com.unionpay.mobile.android.nocard.utils.c.a(this.d, this.a);
        if (j == 0)
          j = l.b.intValue();
        localo.b(j);
        int k = i | l.b.intValue();
        localo.a(this.a.o);
        g.a("uppay", k);
        localo.b(com.unionpay.mobile.android.utils.f.a(this.a.r, "label"));
        localo.a(k);
        localo.a(bool);
        localo.c();
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
      }
    }
  }

  public final void c(String paramString)
  {
  }

  public final void l()
  {
    if (this.a.E)
    {
      super.l();
      this.a.E = false;
      return;
    }
    if ((this.a.aF != l.a.intValue()) && ((this.a.aF != l.c.intValue()) || (!this.a.au) || ((this.a.l != null) && (this.a.l.size() > 0))))
    {
      super.l();
      this.a.aF = l.a.intValue();
      return;
    }
    aq localaq = new aq(this);
    ar localar = new ar(this);
    this.b.a(localaq, localar);
    this.b.a(com.unionpay.mobile.android.languages.c.by.Y, com.unionpay.mobile.android.languages.c.by.av, com.unionpay.mobile.android.languages.c.by.W, com.unionpay.mobile.android.languages.c.by.X);
  }

  protected final void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.s != null)
      this.s.clearFocus();
  }

  protected final void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((!this.u) && (this.v != null))
    {
      this.u = true;
      this.v.sendEmptyMessage(0);
    }
  }

  protected final void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public final class a
    implements o.a
  {
    public a()
    {
    }

    public final int a()
    {
      ao.this.a.E = true;
      ao.this.d(2);
      return 0;
    }

    public final int a(int paramInt)
    {
      ao.a(ao.this, paramInt);
      ao.b(ao.this, 3);
      ao.this.i = false;
      ao.this.b.a(com.unionpay.mobile.android.languages.c.by.U);
      ao.this.e.g(((com.unionpay.mobile.android.model.c)ao.this.a.l.get(paramInt)).a());
      return 0;
    }

    public final void a(int paramInt1, boolean paramBoolean, int paramInt2, a.a parama)
    {
      if (ao.d(ao.this) != null)
      {
        ao.this.a.aG = ao.d(ao.this).a();
        g.c("functionEx", ao.this.a.aG);
      }
      if (paramBoolean)
      {
        ao.this.a.I = paramInt2;
        ao.this.i = false;
        ao.b(ao.this, 2);
        ao.this.b.a(com.unionpay.mobile.android.languages.c.by.U);
        String str4;
        if (((com.unionpay.mobile.android.model.c)ao.this.a.l.get(paramInt2)).c() == 0)
        {
          str4 = ((com.unionpay.mobile.android.model.c)ao.this.a.l.get(paramInt2)).a();
          ao.this.a.H = "1";
        }
        String str2;
        for (String str3 = bg.a(ao.this.a.H, str4, "1", "1"); ; str3 = bg.b(ao.this.a.H, "\"pan\":\"" + str2 + "\"", "2", "1"))
        {
          ao.this.e.b(bg.a(ao.this.a.g), str3);
          return;
          ao.this.a.H = "0";
          str2 = ((com.unionpay.mobile.android.model.c)ao.this.a.l.get(paramInt2)).b();
        }
      }
      if (!parama.a())
      {
        ao.this.a(parama.b);
        return;
      }
      if (paramInt1 == l.c.intValue())
      {
        ao.this.i = false;
        ao.b(ao.this, 4);
        ao.this.b.a(com.unionpay.mobile.android.languages.c.by.U);
        ao.this.e.h(parama.b);
        return;
      }
      ao.this.i = false;
      ao.b(ao.this, 2);
      ao.this.b.a(com.unionpay.mobile.android.languages.c.by.U);
      ao.this.a.H = "0";
      String str1 = bg.b(ao.this.a.H, parama.b, "1", "1");
      ao.this.e.b(bg.a(ao.this.a.g), str1);
    }

    public final void a(String paramString1, String paramString2)
    {
      ao.this.a(paramString1, paramString2);
    }

    public final void b(int paramInt)
    {
      if (paramInt == l.d.intValue())
        ao.b(ao.this);
      do
      {
        return;
        if (paramInt == l.b.intValue())
        {
          ao.this.a.aF = l.b.intValue();
          ao.this.d(2);
          return;
        }
        if (paramInt == l.c.intValue())
        {
          ao.a(ao.this);
          return;
        }
      }
      while (paramInt != l.e.intValue());
      ao.c(ao.this);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.ao
 * JD-Core Version:    0.6.2
 */