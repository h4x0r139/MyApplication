package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.upviews.a.a;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b extends AbstractMethod
{
  private TextView A;
  private boolean B = false;
  private JSONObject g;
  private JSONArray h;
  private boolean i;
  private com.unionpay.mobile.android.upviews.a j;
  private List<Map<String, Object>> k;
  private Drawable l;
  private PopupWindow m;
  private com.unionpay.mobile.android.upwidget.e n;
  private com.unionpay.mobile.android.upwidget.c o;
  private String p;
  private final View.OnClickListener q = new c(this);
  private final View.OnClickListener r = new d(this);
  private final AdapterView.OnItemClickListener s = new e(this);
  private a t;
  private int u = -1;
  private int v = 1;
  private b w;
  private Drawable x;
  private Drawable y;
  private Drawable z;

  public b(Context paramContext, List<Map<String, Object>> paramList, String paramString)
  {
    super(paramContext);
    this.k = paramList;
    this.p = paramString;
    this.o = new com.unionpay.mobile.android.upwidget.c(this.b, this.k, com.unionpay.mobile.android.languages.c.by.bh, this.p, com.unionpay.mobile.android.languages.c.by.bi, this.v, 0);
    this.o.a(this.q);
    this.n = new com.unionpay.mobile.android.upwidget.e(this.b, this.o);
    this.n.a(this.s);
    this.n.a(this.r);
  }

  private final void b(int paramInt)
  {
    int i1 = paramInt - this.o.c();
    if (paramInt == 0)
      return;
    if ((this.k != null) && (paramInt == this.k.size() + this.o.c()))
    {
      com.unionpay.mobile.android.utils.g.a("direct", " new ");
      if (this.w != null)
        this.w.a();
      this.m.dismiss();
      return;
    }
    if ((this.o.b()) && (this.o.c(paramInt)))
    {
      com.unionpay.mobile.android.utils.g.a("direct", " delete " + paramInt);
      i();
      if (this.w != null)
      {
        this.u = i1;
        this.w.a(i1);
      }
    }
    while (true)
    {
      this.m.dismiss();
      return;
      this.v = paramInt;
      this.o.a(this.v);
      com.unionpay.mobile.android.utils.g.a("direct", " pay with " + paramInt);
      if (this.t != null)
        this.t.b.setText(this.o.b(this.v));
      if (this.w == null);
    }
  }

  private boolean h()
  {
    return (this.i) || (this.k == null) || (this.k.size() == 0);
  }

  private void i()
  {
    String str1;
    if (this.o != null)
    {
      this.o.a();
      if (!this.o.b())
        break label72;
      str1 = com.unionpay.mobile.android.languages.c.by.bj;
      if (!this.o.b())
        break label82;
    }
    label72: label82: for (String str2 = com.unionpay.mobile.android.languages.c.by.bk; ; str2 = com.unionpay.mobile.android.languages.c.by.bi)
    {
      this.o.a(str1);
      this.o.b(str2);
      this.o.notifyDataSetChanged();
      return;
      str1 = com.unionpay.mobile.android.languages.c.by.bh;
      break;
    }
  }

  public final b a(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3)
  {
    this.x = paramDrawable1;
    this.y = paramDrawable2;
    this.z = paramDrawable3;
    return this;
  }

  public final b a(b paramb)
  {
    this.w = paramb;
    return this;
  }

  public final b a(JSONArray paramJSONArray)
  {
    this.h = paramJSONArray;
    return this;
  }

  public final b a(JSONObject paramJSONObject)
  {
    this.g = paramJSONObject;
    if (this.A != null)
      this.A.setText(Html.fromHtml(a(this.g, "label")));
    return this;
  }

  public final void a(int paramInt)
  {
    if (this.k != null);
    for (int i1 = this.k.size(); ; i1 = 0)
    {
      if ((i1 > 0) && (this.u >= 0) && (this.u < i1))
      {
        this.k.remove(this.u);
        this.u = -1;
        this.o.notifyDataSetChanged();
      }
      b(paramInt + this.o.c());
      return;
    }
  }

  public final void a(RelativeLayout paramRelativeLayout)
  {
    TextView localTextView = new TextView(this.b);
    localTextView.setTextSize(com.unionpay.mobile.android.global.b.k);
    localTextView.setTextColor(-13421773);
    localTextView.setText(this.c);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.addRule(9, -1);
    localLayoutParams1.addRule(15, -1);
    localLayoutParams1.leftMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    paramRelativeLayout.addView(localTextView, localLayoutParams1);
    if (!h())
      return;
    String str = a(this.g, "label");
    this.A = new TextView(this.b);
    this.A.setOnClickListener(new f(this));
    if (!a(str))
      this.A.setText(Html.fromHtml(str));
    a(this.A);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(11, -1);
    localLayoutParams2.rightMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    localLayoutParams2.addRule(15, -1);
    paramRelativeLayout.addView(this.A, localLayoutParams2);
  }

  public final int b()
  {
    return l.b.intValue();
  }

  public final b b(Drawable paramDrawable)
  {
    this.l = paramDrawable;
    return this;
  }

  public final b b(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public final b b(boolean paramBoolean)
  {
    this.B = paramBoolean;
    return this;
  }

  public final void b(RelativeLayout paramRelativeLayout)
  {
    if ((h()) || (this.B))
    {
      if (this.B)
        g();
      this.j = new com.unionpay.mobile.android.upviews.a(this.b, this.h, this);
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
      localLayoutParams1.topMargin = com.unionpay.mobile.android.global.a.f;
      paramRelativeLayout.addView(this.j, localLayoutParams1);
      return;
    }
    LinearLayout localLinearLayout1 = new LinearLayout(this.b);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    localLinearLayout1.setBackgroundColor(-3419943);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, 1);
    localLayoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
    paramRelativeLayout.addView(localLinearLayout1, localLayoutParams2);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.b);
    localRelativeLayout.setId(localRelativeLayout.hashCode());
    localRelativeLayout.setBackgroundDrawable(this.l);
    localRelativeLayout.setOnClickListener(new g(this));
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
    localLayoutParams3.addRule(3, localLinearLayout1.getId());
    paramRelativeLayout.addView(localRelativeLayout, localLayoutParams3);
    ImageView localImageView = new ImageView(this.b);
    localImageView.setId(localImageView.hashCode());
    localImageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.b).a(1002));
    int i1 = com.unionpay.mobile.android.utils.c.a(this.b, 15.0F);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(i1, i1);
    localLayoutParams4.addRule(11, -1);
    localLayoutParams4.addRule(15, -1);
    localLayoutParams4.rightMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    localRelativeLayout.addView(localImageView, localLayoutParams4);
    TextView localTextView = new TextView(this.b);
    localTextView.setText(this.o.b(this.v));
    localTextView.setTextSize(com.unionpay.mobile.android.global.b.k);
    localTextView.setTextColor(-10066330);
    localTextView.setSingleLine(true);
    localTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams5.addRule(9, -1);
    localLayoutParams5.addRule(15, -1);
    localLayoutParams5.addRule(0, localImageView.getId());
    localLayoutParams5.leftMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    localRelativeLayout.addView(localTextView, localLayoutParams5);
    LinearLayout localLinearLayout2 = new LinearLayout(this.b);
    localLinearLayout2.setBackgroundColor(-3419943);
    RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(-1, 1);
    localLayoutParams6.bottomMargin = com.unionpay.mobile.android.global.a.f;
    localLayoutParams6.addRule(3, localRelativeLayout.getId());
    paramRelativeLayout.addView(localLinearLayout2, localLayoutParams6);
    this.t = new a((byte)0);
    this.t.a = localRelativeLayout;
    this.t.b = localTextView;
  }

  public final a.a c()
  {
    if (this.j != null)
      return this.j.a();
    return null;
  }

  public final void c(RelativeLayout paramRelativeLayout)
  {
    paramRelativeLayout.setVisibility(8);
  }

  public final int d()
  {
    return this.v - this.o.c();
  }

  public final b d(String paramString)
  {
    this.d = paramString;
    return this;
  }

  public final String e()
  {
    return this.d;
  }

  public final boolean f()
  {
    return (this.j == null) || (this.j.c());
  }

  private final class a
  {
    View a;
    TextView b;

    private a()
    {
    }
  }

  public static abstract interface b
  {
    public abstract int a();

    public abstract int a(int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.b
 * JD-Core Version:    0.6.2
 */