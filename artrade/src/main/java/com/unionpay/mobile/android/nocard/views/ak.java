package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.utils.e;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.mobile.android.widgets.m;
import org.json.JSONObject;

public final class ak extends b
  implements a.b
{
  private int p = 100;
  private int q = 20;
  private com.unionpay.mobile.android.upwidget.a r = null;
  private TextView s = null;
  private com.unionpay.mobile.android.upviews.a t = null;
  private View.OnClickListener u = new al(this);
  private View.OnClickListener v = new am(this);

  public ak(Context paramContext)
  {
    super(paramContext);
    this.f = 10;
    setBackgroundColor(-1052684);
    this.j = a();
    b();
    super.d();
    c();
  }

  private void r()
  {
    this.p = 103;
    g.c("uppay", this.q);
    this.e.a("query", this.a.ae, 3);
    this.q = (-1 + this.q);
  }

  public final void a(a.a parama)
  {
    if (!parama.a())
    {
      a(parama.b);
      return;
    }
    this.i = false;
    this.p = 101;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    g.a("uppay", "sms elements:" + parama.b);
    this.e.b("sms", parama.b);
  }

  public final void a(JSONObject paramJSONObject)
  {
    switch (this.p)
    {
    default:
    case 101:
    case 102:
    case 103:
    }
    do
    {
      return;
      this.t.a(com.unionpay.mobile.android.global.b.p);
      this.b.c();
      this.p = 100;
      return;
      this.a.ae = e.a(paramJSONObject.toString());
      if ((this.a.ae == null) || (this.a.ae.length() <= 0))
      {
        b(2);
        return;
      }
      this.q = 20;
      r();
      return;
      String str1 = f.a(paramJSONObject, "status");
      String str2 = f.a(paramJSONObject, "fail_msg");
      this.a.N = f.a(paramJSONObject, "open_info");
      this.a.v = f.a(paramJSONObject, "title");
      this.a.w = f.a(paramJSONObject, "succ_info");
      if ((this.q > 0) && (str1.equalsIgnoreCase("01")))
      {
        r();
        return;
      }
      this.p = 100;
      j();
      if (str1.equalsIgnoreCase("00"))
      {
        d(11);
        return;
      }
      if (str1.equalsIgnoreCase("03"))
      {
        an localan = new an(this);
        this.b.a(localan, null);
        this.b.a(com.unionpay.mobile.android.languages.c.by.ab, str2, com.unionpay.mobile.android.languages.c.by.ac);
        return;
      }
    }
    while (this.q > 0);
    b(20);
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

  protected final void c()
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setBackgroundColor(-1);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    int i = com.unionpay.mobile.android.global.a.f;
    localLayoutParams1.rightMargin = i;
    localLayoutParams1.leftMargin = i;
    this.l.addView(localLinearLayout1, localLayoutParams1);
    this.t = new com.unionpay.mobile.android.upviews.a(this.d, this.a.O, this.e.b(), this, this.a.al);
    new LinearLayout.LayoutParams(-1, -1).topMargin = com.unionpay.mobile.android.global.a.f;
    localLinearLayout1.addView(this.t);
    LinearLayout localLinearLayout2 = new LinearLayout(this.d);
    localLinearLayout2.setOrientation(1);
    localLinearLayout2.setId(localLinearLayout2.hashCode());
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams2.topMargin = com.unionpay.mobile.android.global.a.d;
    localLayoutParams2.leftMargin = com.unionpay.mobile.android.global.a.d;
    localLayoutParams2.addRule(3, localLinearLayout1.getId());
    this.l.addView(localLinearLayout2, localLayoutParams2);
    this.r = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.v);
    localLinearLayout2.addView(this.r);
    this.s = new TextView(this.d);
    this.s.setText(f.a(this.a.x, "label"));
    this.s.setTextSize(com.unionpay.mobile.android.global.b.i);
    this.s.setTextColor(p());
    this.s.setGravity(17);
    int j = com.unionpay.mobile.android.global.a.n;
    Drawable localDrawable = this.c.a(2008);
    this.s.setBackgroundDrawable(localDrawable);
    this.s.setOnClickListener(this.u);
    TextView localTextView = this.s;
    if ((this.t == null) || (this.t.c()));
    for (boolean bool = true; ; bool = false)
    {
      localTextView.setEnabled(bool);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, j);
      localLayoutParams3.topMargin = com.unionpay.mobile.android.global.a.d;
      int k = com.unionpay.mobile.android.global.a.d;
      localLayoutParams3.rightMargin = k;
      localLayoutParams3.leftMargin = k;
      localLayoutParams3.addRule(3, localLinearLayout2.getId());
      this.l.addView(this.s, localLayoutParams3);
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
    n();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.ak
 * JD-Core Version:    0.6.2
 */