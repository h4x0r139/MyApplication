package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.e;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.mobile.android.widgets.m;
import org.json.JSONArray;
import org.json.JSONObject;

public final class g extends b
  implements a.b
{
  private int p = 20;
  private int q = 100;
  private com.unionpay.mobile.android.upwidget.a r = null;
  private TextView s = null;
  private com.unionpay.mobile.android.upviews.a t = null;
  private boolean u = false;
  private boolean v;
  private View.OnClickListener w;
  private View.OnClickListener x;

  public g(Context paramContext)
  {
    super(paramContext);
    this.v = bool;
    this.w = new h(this);
    this.x = new i(this);
    this.f = 5;
    if (!this.a.F);
    while (true)
    {
      this.v = bool;
      setBackgroundColor(-1052684);
      e();
      return;
      bool = false;
    }
  }

  private void e(JSONObject paramJSONObject)
  {
    j();
    this.a.u = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "rules");
    this.a.v = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "title");
    this.a.x = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "followrules_button");
    this.a.ag = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "service_checkbox");
    this.a.ah = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "bind_card_checkbox");
    this.a.al = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "pan");
    if ((this.a.u == null) || (this.a.u.length() <= 0))
    {
      b(2);
      return;
    }
    if (this.t != null)
      this.t.d();
    d locald = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
    this.a.F = false;
    a(6, locald);
  }

  private void r()
  {
    this.e.e(this.t.a("pan"));
    this.q = 104;
  }

  private void s()
  {
    this.q = 103;
    this.e.a("query", this.a.ae, 3);
    this.p = (-1 + this.p);
  }

  public final void a(a.a parama)
  {
    if (parama.a())
    {
      this.i = false;
      this.q = 101;
      this.b.a(com.unionpay.mobile.android.languages.c.by.U);
      com.unionpay.mobile.android.utils.g.a("uppay", "sms elements:" + parama.b);
      this.e.b("sms", parama.b);
      return;
    }
    a(parama.b);
  }

  public final void a(JSONObject paramJSONObject)
  {
    this.v = false;
    switch (this.q)
    {
    default:
    case 101:
    case 102:
    case 103:
    case 104:
    case 105:
    }
    do
    {
      do
      {
        do
        {
          return;
          this.t.a(com.unionpay.mobile.android.global.b.p);
          this.b.c();
          this.q = 100;
          return;
          this.a.ae = e.a(paramJSONObject.toString());
          if ((this.a.ae == null) || (this.a.ae.length() <= 0))
          {
            b(2);
            return;
          }
          this.p = 20;
          s();
          return;
          String str1 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "status");
          String str2 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "fail_msg");
          if ((this.p > 0) && (str1.equalsIgnoreCase("01")))
          {
            s();
            return;
          }
          this.q = 100;
          if (str1.equalsIgnoreCase("00"))
          {
            this.u = true;
            r();
            return;
          }
          j();
          if (str1.equalsIgnoreCase("03"))
          {
            j localj = new j(this);
            k localk = new k(this);
            if (this.a.A)
            {
              this.b.a(localj, localk);
              this.b.a(com.unionpay.mobile.android.languages.c.by.ab, str2, com.unionpay.mobile.android.languages.c.by.ac, com.unionpay.mobile.android.languages.c.by.ad);
              return;
            }
            this.b.a(localj, null);
            this.b.a(com.unionpay.mobile.android.languages.c.by.ab, str2, com.unionpay.mobile.android.languages.c.by.ac);
            return;
          }
        }
        while (this.p > 0);
        a(this.a.af);
        return;
      }
      while (c(paramJSONObject));
      e(paramJSONObject);
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
    }
    while ((this.a.y == null) || (this.a.y.length() <= 0));
    d(5);
  }

  public final void a(boolean paramBoolean)
  {
    TextView localTextView;
    if (this.s != null)
    {
      localTextView = this.s;
      if (paramBoolean)
        break label24;
    }
    label24: for (boolean bool = true; ; bool = false)
    {
      localTextView.setEnabled(bool);
      return;
    }
  }

  protected final boolean a(String paramString, JSONObject paramJSONObject)
  {
    this.v = false;
    return false;
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    ax localax = new ax(getContext(), this.a.v, this);
    localLayoutParams.addRule(13, -1);
    this.j.addView(localax, localLayoutParams);
  }

  public final void b(String paramString1, String paramString2)
  {
  }

  protected final void b(String paramString, JSONObject paramJSONObject)
  {
    if ("init".equals(paramString))
    {
      a(2);
      return;
    }
    if ("".equals(paramString))
    {
      e(paramJSONObject);
      return;
    }
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    this.i = false;
    this.q = 105;
    this.e.b(paramString, "");
  }

  protected final void c()
  {
    this.n.a(this);
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    int i = com.unionpay.mobile.android.global.a.d;
    this.l.addView(localLinearLayout1, localLayoutParams1);
    this.t = new com.unionpay.mobile.android.upviews.a(this.d, this.a.y, this.e.b(), this, this.a.al);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
    localLinearLayout1.addView(this.t, localLayoutParams2);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams3.topMargin = i;
    localLayoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
    localLayoutParams3.addRule(3, localLinearLayout1.getId());
    LinearLayout localLinearLayout2 = new LinearLayout(this.d);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setId(localLinearLayout2.hashCode());
    this.r = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.x);
    LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-2, -2);
    localLinearLayout2.addView(this.r, localLayoutParams4);
    this.l.addView(localLinearLayout2, localLayoutParams3);
    this.s = new TextView(this.d);
    this.s.setText(com.unionpay.mobile.android.utils.f.a(this.a.x, "label"));
    this.s.setTextSize(com.unionpay.mobile.android.global.b.i);
    this.s.setTextColor(p());
    this.s.setGravity(17);
    int j = com.unionpay.mobile.android.global.a.n;
    Drawable localDrawable = this.c.a(2008);
    this.s.setBackgroundDrawable(localDrawable);
    this.s.setOnClickListener(this.w);
    TextView localTextView = this.s;
    if ((this.t == null) || (this.t.c()));
    for (boolean bool = true; ; bool = false)
    {
      localTextView.setEnabled(bool);
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-1, j);
      localLayoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
      int k = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
      localLayoutParams5.rightMargin = k;
      localLayoutParams5.leftMargin = k;
      localLayoutParams5.addRule(3, localLinearLayout2.getId());
      this.l.addView(this.s, localLayoutParams5);
      return;
    }
  }

  public final void c(String paramString)
  {
  }

  public final void l()
  {
    if (this.t.b())
      return;
    if (this.a.G)
    {
      a(13);
      this.a.G = false;
      return;
    }
    if ((this.a.F) && (this.v))
    {
      this.a.F = false;
      com.unionpay.mobile.android.nocard.utils.f.a(this.a, this.a.B);
      n();
      return;
    }
    this.a.F = false;
    this.a.B = null;
    a(2);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.g
 * JD-Core Version:    0.6.2
 */