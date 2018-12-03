package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.mobile.android.widgets.m;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o extends b
  implements a.b
{
  private com.unionpay.mobile.android.upwidget.a A = null;
  private com.unionpay.mobile.android.upviews.a B = null;
  private b C;
  private String D;
  private View.OnClickListener E = new p(this);
  private View.OnClickListener F = new u(this);
  private boolean G = false;
  LinearLayout p = null;
  private int q = 0;
  private com.unionpay.mobile.android.upviews.a r = null;
  private View.OnClickListener s = null;
  private View.OnClickListener t = null;
  private View.OnClickListener u = null;
  private TextView v = null;
  private int w = 0;
  private int x = 0;
  private int y = 20;
  private int z = 5;

  public o(Context paramContext, com.unionpay.mobile.android.model.d paramd)
  {
    super(paramContext, paramd);
    this.f = 13;
    if ((!q()) && (!s()) && (!this.a.aP))
      this.G = true;
    setBackgroundColor(-1052684);
    e();
    if (this.a.av != null)
      d(null);
  }

  private void a(LinearLayout paramLinearLayout)
  {
    JSONArray localJSONArray = this.a.X;
    if (localJSONArray == null);
    while (true)
    {
      return;
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        com.unionpay.mobile.android.widgets.y localy = b((JSONObject)com.unionpay.mobile.android.utils.f.b(localJSONArray, i));
        if (localy != null)
          paramLinearLayout.addView(localy);
      }
    }
  }

  private void c(String paramString1, String paramString2)
  {
    this.q = 9;
    if (TextUtils.isEmpty(paramString2))
      this.e.b(paramString1, "");
    while (true)
    {
      this.z = (-1 + this.z);
      return;
      String str = "\"uuid\":\"" + paramString2 + "\"";
      this.e.a(paramString1, str, 10);
    }
  }

  private void e(JSONObject paramJSONObject)
  {
    int i = 1;
    int k = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, false);
    if (k != 0)
    {
      b(k);
      if (i == this.q)
        f(this.x);
    }
    com.unionpay.mobile.android.model.d locald;
    do
    {
      return;
      locald = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
      if (5 != this.q)
        break;
      if ((this.a.u != null) && (this.a.u.length() > 0))
      {
        a(6, locald);
        return;
      }
    }
    while ((this.a.y == null) || (this.a.y.length() <= 0));
    d(5);
    return;
    this.o = locald;
    f(this.w);
    this.B.a(r(), this.a.al, i, null, this.a.Y);
    this.B.a(this.E);
    this.B.b(this.F);
    this.B.a(this.b, this.a.aK);
    com.unionpay.mobile.android.upviews.a locala = this.B;
    com.unionpay.mobile.android.widgets.y localy = null;
    if (locala != null)
      localy = this.B.c("instalment");
    this.r.a(this.a.u, this.a.al, i, localy, this.a.Y);
    TextView localTextView = this.v;
    if ((this.r == null) || (this.r.c()));
    while (true)
    {
      localTextView.setEnabled(i);
      return;
      int j = 0;
    }
  }

  private void f(int paramInt)
  {
    this.w = paramInt;
    this.C.a(this.w);
  }

  private JSONArray r()
  {
    JSONArray localJSONArray = new JSONArray();
    if (this.o != null)
    {
      com.unionpay.mobile.android.model.e locale = (com.unionpay.mobile.android.model.e)this.o;
      localJSONArray.put(locale.a("promotion"));
      localJSONArray.put(locale.a("instalment"));
      this.a.aK = locale.a("promotion_instalment_msgbox");
    }
    return localJSONArray;
  }

  private final boolean s()
  {
    boolean bool1 = this.a.aP;
    boolean bool2 = false;
    if (!bool1)
    {
      List localList = this.a.W;
      bool2 = false;
      if (localList != null)
      {
        int i = this.a.W.size();
        bool2 = false;
        if (i > 0)
          bool2 = true;
      }
    }
    return bool2;
  }

  private void t()
  {
    this.q = 4;
    this.e.a("query", this.a.ae, 3);
    this.y = (-1 + this.y);
  }

  public final void a(a.a parama)
  {
    this.r.b();
    if (!parama.a())
    {
      a(parama.b);
      return;
    }
    this.i = false;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    this.e.b("sms", parama.b);
    this.q = 2;
  }

  public final void a(JSONObject paramJSONObject)
  {
    switch (this.q)
    {
    default:
    case 2:
    case 1:
    case 5:
    case 6:
    case 3:
    case 4:
    case 7:
    case 8:
    case 9:
    }
    JSONArray localJSONArray1;
    String str2;
    do
    {
      String str4;
      do
      {
        JSONArray localJSONArray2;
        do
        {
          do
          {
            do
            {
              do
              {
                return;
                j();
                this.r.a(com.unionpay.mobile.android.global.b.p);
                return;
                j();
              }
              while (c(paramJSONObject));
              if (this.q == 5)
                this.a.G = true;
              e(paramJSONObject);
              return;
              j();
              int j = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, true);
              if (j != 0)
                b(j);
              while (true)
              {
                this.q = 0;
                return;
                this.a.F = true;
                com.unionpay.mobile.android.model.d locald2 = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
                if ((this.a.u != null) && (this.a.u.length() > 0))
                  a(6, locald2);
                else if ((this.a.y != null) && (this.a.y.length() > 0))
                  d(5);
              }
              this.a.ae = com.unionpay.mobile.android.utils.e.a(paramJSONObject.toString());
              if (this.a.ae == null)
              {
                b(2);
                return;
              }
              this.y = 20;
              t();
              return;
              String str5 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "status");
              if ((this.y > 0) && (str5.equalsIgnoreCase("01")))
              {
                t();
                return;
              }
              j();
              if (str5.equalsIgnoreCase("00"))
              {
                this.q = 0;
                this.a.C = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "result");
                this.a.K = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "openupgrade_flag");
                this.a.L = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "temporary_pay_flag");
                this.a.M = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "temporary_pay_info");
                this.a.Q = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "front_url");
                this.a.R = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "front_request");
                this.a.v = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "title");
                this.a.w = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "succ_info");
                com.unionpay.mobile.android.nocard.utils.b.a(paramJSONObject, this.a);
                com.unionpay.mobile.android.nocard.utils.b.b(paramJSONObject, this.a);
                d(8);
                return;
              }
              if (str5.equalsIgnoreCase("03"))
              {
                String str6 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "fail_msg");
                a(str6);
                return;
              }
            }
            while (this.y > 0);
            b(19);
            return;
            j();
            int i = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, false);
            if (i != 0)
            {
              b(i);
              return;
            }
            com.unionpay.mobile.android.model.d locald1 = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
            if ((this.a.u != null) && (this.a.u.length() > 0))
            {
              a(6, locald1);
              return;
            }
          }
          while ((this.a.y == null) || (this.a.y.length() <= 0));
          d(5);
          return;
          j();
          localJSONArray2 = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "options");
        }
        while (this.B == null);
        this.B.a(localJSONArray2);
        return;
        String str1 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "status");
        if ((str1 == null) || (!"01".equals(str1)))
          break;
        String str3 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "uuid");
        if (this.z >= 0)
        {
          c(this.D, str3);
          return;
        }
        str4 = com.unionpay.mobile.android.languages.c.by.D;
      }
      while (this.B == null);
      this.B.a(null, str4);
      return;
      localJSONArray1 = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "options");
      str2 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "empty_info");
    }
    while (this.B == null);
    this.B.a(localJSONArray1, str2);
  }

  public final void a(boolean paramBoolean)
  {
    TextView localTextView = this.v;
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      localTextView.setEnabled(bool);
      return;
    }
  }

  protected final boolean a(String paramString, JSONObject paramJSONObject)
  {
    if (this.q == 1)
    {
      f(this.x);
      j();
      a(paramString);
      return true;
    }
    return false;
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    String str = com.unionpay.mobile.android.languages.c.by.o;
    ax localax = new ax(this.d, str, this);
    if ((this.a.au) && ((this.a.l == null) || (this.a.l.size() == 0)) && (!this.a.aP) && (!TextUtils.isEmpty(this.a.p)))
      localax = new ax(this.d, str, this.c.a(1030), com.unionpay.mobile.android.utils.c.a(this.d, 20.0F), this);
    localLayoutParams.addRule(13, -1);
    this.j.addView(localax, localLayoutParams);
  }

  public final void b(String paramString1, String paramString2)
  {
    a(paramString1, paramString2);
  }

  protected final void b(String paramString, JSONObject paramJSONObject)
  {
    if ("init".equals(paramString))
      a(2);
    do
    {
      return;
      if (!"".equals(paramString))
        break;
      if (this.q == 5)
        this.a.G = true;
    }
    while (paramJSONObject == null);
    e(paramJSONObject);
    return;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    this.i = false;
    this.q = 7;
    this.e.b(paramString, "");
  }

  protected final void c()
  {
    int i = 1;
    this.l.removeAllViews();
    this.n.a(this);
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setOrientation(i);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams1.topMargin = com.unionpay.mobile.android.global.a.f;
    localLayoutParams1.addRule(10, -1);
    this.l.addView(localLinearLayout1, localLayoutParams1);
    a(localLinearLayout1);
    JSONArray localJSONArray = r();
    if ((localJSONArray != null) && (localJSONArray.length() > 0) && (s()))
    {
      this.B = new com.unionpay.mobile.android.upviews.a(this.d, localJSONArray, this);
      this.B.a(this.E);
      this.B.b(this.F);
      this.B.a(this.b, this.a.aK);
      LinearLayout.LayoutParams localLayoutParams16 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams16.bottomMargin = com.unionpay.mobile.android.global.a.f;
      localLinearLayout1.addView(this.B, localLayoutParams16);
    }
    if (q())
      if (!s())
      {
        if (!TextUtils.isEmpty(this.a.Z))
        {
          LinearLayout.LayoutParams localLayoutParams15 = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams15.topMargin = com.unionpay.mobile.android.global.a.f;
          TextView localTextView5 = new TextView(this.d);
          localTextView5.setTextSize(com.unionpay.mobile.android.global.b.k);
          localTextView5.setText(this.a.Z);
          localLinearLayout1.addView(localTextView5, localLayoutParams15);
        }
        LinearLayout localLinearLayout3 = new LinearLayout(this.d);
        localLinearLayout3.setOrientation(i);
        localLinearLayout3.setId(localLinearLayout3.hashCode());
        LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams4.topMargin = com.unionpay.mobile.android.global.a.d;
        localLinearLayout1.addView(localLinearLayout3, localLayoutParams4);
        if ((this.a.U != null) && (s()))
        {
          this.A = new com.unionpay.mobile.android.upwidget.a(this.d, com.unionpay.mobile.android.nocard.views.xlistview.a.a(this.a.U, com.unionpay.mobile.android.languages.c.by.s), new q(this));
          localLinearLayout3.addView(this.A);
        }
        com.unionpay.mobile.android.upwidget.u localu = com.unionpay.mobile.android.upwidget.u.a(this.d, this.a.V, this.c.a(1017));
        if (localu != null)
        {
          localu.a(new r(this, localu.a()));
          LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-2, -2);
          localLayoutParams6.topMargin = com.unionpay.mobile.android.global.a.f;
          localLinearLayout3.addView(localu, localLayoutParams6);
        }
        this.v = new TextView(this.d);
        if (!s())
          break label1506;
        this.v.setText(com.unionpay.mobile.android.languages.c.by.p);
        this.v.setOnClickListener(this.s);
        TextView localTextView1 = this.v;
        if ((this.r != null) && (!this.r.c()))
          break label1501;
        label545: localTextView1.setEnabled(i);
      }
    while (true)
    {
      this.v.setTextSize(com.unionpay.mobile.android.global.b.i);
      this.v.setTextColor(p());
      this.v.setGravity(17);
      int j = com.unionpay.mobile.android.global.a.n;
      Drawable localDrawable = this.c.a(2008);
      this.v.setBackgroundDrawable(localDrawable);
      LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, j);
      localLayoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
      int k = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
      localLayoutParams5.rightMargin = k;
      localLayoutParams5.leftMargin = k;
      localLinearLayout1.addView(this.v, localLayoutParams5);
      return;
      LinearLayout localLinearLayout4 = new LinearLayout(this.d);
      localLinearLayout4.setBackgroundColor(-3419943);
      LinearLayout.LayoutParams localLayoutParams12 = new LinearLayout.LayoutParams(-1, i);
      localLayoutParams12.topMargin = com.unionpay.mobile.android.global.a.f;
      localLinearLayout1.addView(localLinearLayout4, localLayoutParams12);
      LinearLayout.LayoutParams localLayoutParams13 = new LinearLayout.LayoutParams(-1, -2);
      this.C = new b(this.d, new z(this), com.unionpay.mobile.android.nocard.views.xlistview.a.a(this.d, this.a.W, false), com.unionpay.mobile.android.languages.c.by.bh, this.a.aO);
      localLinearLayout1.addView(this.C, localLayoutParams13);
      com.unionpay.mobile.android.upviews.a locala2 = this.B;
      com.unionpay.mobile.android.widgets.y localy2 = null;
      if (locala2 != null)
        localy2 = this.B.c("instalment");
      this.r = new com.unionpay.mobile.android.upviews.a(this.d, this.a.u, this.e.b(), this, this.a.al, i, localy2, this.a.Y);
      LinearLayout.LayoutParams localLayoutParams14 = new LinearLayout.LayoutParams(-1, -2);
      localLinearLayout1.addView(this.r, localLayoutParams14);
      break;
      if (!s())
      {
        if (TextUtils.isEmpty(this.a.aO))
        {
          LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams7.topMargin = com.unionpay.mobile.android.global.a.f;
          localLayoutParams7.leftMargin = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
          TextView localTextView2 = new TextView(this.d);
          localTextView2.setTextSize(com.unionpay.mobile.android.global.b.k);
          localTextView2.setText(this.a.Z);
          localLinearLayout1.addView(localTextView2, localLayoutParams7);
          break;
        }
        RelativeLayout localRelativeLayout = new RelativeLayout(this.d);
        TextView localTextView3 = new TextView(this.d);
        localTextView3.setTextSize(com.unionpay.mobile.android.global.b.k);
        localTextView3.setTextColor(-13421773);
        localTextView3.setText(com.unionpay.mobile.android.languages.c.by.bv);
        RelativeLayout.LayoutParams localLayoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams8.addRule(9, -1);
        localLayoutParams8.addRule(15, -1);
        localLayoutParams8.leftMargin = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
        localRelativeLayout.addView(localTextView3, localLayoutParams8);
        TextView localTextView4 = new TextView(this.d);
        localTextView4.setText(Html.fromHtml(com.unionpay.mobile.android.languages.c.by.j));
        localTextView4.setTextSize(com.unionpay.mobile.android.global.b.k);
        localTextView4.setTextColor(com.unionpay.mobile.android.utils.d.a(-10705958, -5846275, -5846275, -6710887));
        localTextView4.setOnClickListener(new y(this));
        RelativeLayout.LayoutParams localLayoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams9.addRule(11, -1);
        localLayoutParams9.rightMargin = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
        localLayoutParams9.addRule(15, -1);
        localRelativeLayout.addView(localTextView4, localLayoutParams9);
        LinearLayout.LayoutParams localLayoutParams10 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams10.topMargin = com.unionpay.mobile.android.global.a.f;
        localLinearLayout1.addView(localRelativeLayout, localLayoutParams10);
        this.r = new com.unionpay.mobile.android.upviews.a(this.d, this.a.o, this);
        LinearLayout.LayoutParams localLayoutParams11 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams11.topMargin = com.unionpay.mobile.android.global.a.f;
        localLinearLayout1.addView(this.r, localLayoutParams11);
        break;
      }
      LinearLayout localLinearLayout2 = new LinearLayout(this.d);
      localLinearLayout2.setBackgroundColor(-3419943);
      localLinearLayout1.addView(localLinearLayout2, new LinearLayout.LayoutParams(-1, i));
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
      this.C = new b(this.d, new aa(this), com.unionpay.mobile.android.nocard.views.xlistview.a.a(this.d, this.a.W, false), com.unionpay.mobile.android.languages.c.by.bh, this.a.aO);
      localLinearLayout1.addView(this.C, localLayoutParams2);
      com.unionpay.mobile.android.upviews.a locala1 = this.B;
      com.unionpay.mobile.android.widgets.y localy1 = null;
      if (locala1 != null)
        localy1 = this.B.c("instalment");
      this.r = new com.unionpay.mobile.android.upviews.a(this.d, this.a.u, this.e.b(), this, this.a.al, i, localy1, this.a.Y);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
      localLinearLayout1.addView(this.r, localLayoutParams3);
      break;
      label1501: boolean bool = false;
      break label545;
      label1506: if (q())
      {
        this.v.setText(com.unionpay.mobile.android.languages.c.by.q);
        this.v.setOnClickListener(new ab(this));
        this.v.setEnabled(bool);
      }
      else
      {
        if ((TextUtils.isEmpty(this.a.aO)) && (!this.a.aP))
        {
          if ((this.a.l == null) || (this.a.l.size() == 0))
            this.v.setText(com.unionpay.mobile.android.languages.c.by.bp);
          while (true)
          {
            this.v.setOnClickListener(this.u);
            this.v.setEnabled(bool);
            break;
            this.v.setText(com.unionpay.mobile.android.languages.c.by.bq);
          }
        }
        this.v.setText(com.unionpay.mobile.android.languages.c.by.r);
        this.v.setOnClickListener(this.t);
        this.v.setEnabled(false);
      }
    }
  }

  public final void c(String paramString)
  {
    this.i = false;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    if (this.a.aP);
    for (String str = "\"card\":\"" + this.a.al + "\""; ; str = "\"card\":\"" + ((com.unionpay.mobile.android.model.c)this.a.W.get(this.w)).a() + "\"")
    {
      g.a("uppay", "cmd:" + paramString + ", ele:" + str);
      this.e.b(paramString, str);
      this.q = 6;
      return;
    }
  }

  public final void l()
  {
    if ((!TextUtils.isEmpty(this.a.p)) && (this.a.au) && ((this.a.l == null) || (this.a.l.size() == 0)))
    {
      s locals = new s(this);
      t localt = new t(this);
      this.b.a(locals, localt);
      this.b.a(com.unionpay.mobile.android.languages.c.by.Y, com.unionpay.mobile.android.languages.c.by.av, com.unionpay.mobile.android.languages.c.by.W, com.unionpay.mobile.android.languages.c.by.X);
    }
    do
    {
      return;
      if (this.a.aP)
        this.a.aP = false;
    }
    while ((this.r != null) && (this.r.b()));
    if ((this.a.p != null) && (this.a.p.length() > 0))
    {
      a(2);
      return;
    }
    n();
  }

  protected final void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt);
  }

  private final class b extends LinearLayout
  {
    private PopupWindow b;
    private com.unionpay.mobile.android.upwidget.c c;
    private com.unionpay.mobile.android.upwidget.e d;
    private String e;
    private TextView f;
    private int g = 1;
    private final View.OnClickListener h = new ac(this);
    private final AdapterView.OnItemClickListener i = new ad(this);
    private List<Map<String, Object>> j;
    private o.a k;
    private String l;

    public b(o.a paramList, List<Map<String, Object>> paramJSONArray, JSONArray paramString, String arg5)
    {
      super();
      setOrientation(1);
      this.k = paramJSONArray;
      this.j = paramString;
      Object localObject1;
      this.e = localObject1;
      Object localObject2;
      this.l = localObject2;
      this.c = new com.unionpay.mobile.android.upwidget.c(o.this.d, this.j, this.e, this.l, "", this.g, 0);
      this.d = new com.unionpay.mobile.android.upwidget.e(o.this.d, this.c);
      this.d.a(this.i);
      this.d.a(this.h);
      if ((paramString != null) && (paramString.size() > 0))
      {
        Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(o.this.d).a(2014);
        RelativeLayout localRelativeLayout = new RelativeLayout(o.this.d);
        localRelativeLayout.setBackgroundDrawable(localDrawable);
        localRelativeLayout.setOnClickListener(new ae(this));
        addView(localRelativeLayout, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
        ImageView localImageView = new ImageView(o.this.d);
        localImageView.setId(localImageView.hashCode());
        localImageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(o.this.d).a(1002));
        int m = com.unionpay.mobile.android.utils.c.a(o.this.d, 15.0F);
        RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(m, m);
        localLayoutParams1.addRule(11, -1);
        localLayoutParams1.addRule(15, -1);
        localLayoutParams1.rightMargin = com.unionpay.mobile.android.utils.c.a(o.this.d, 10.0F);
        localRelativeLayout.addView(localImageView, localLayoutParams1);
        this.f = new TextView(o.this.d);
        this.f.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.f.setTextColor(-10066330);
        RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        this.f.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.f.setSingleLine(true);
        localLayoutParams2.leftMargin = com.unionpay.mobile.android.utils.c.a(o.this.d, 10.0F);
        localLayoutParams2.rightMargin = localLayoutParams2.leftMargin;
        localLayoutParams2.addRule(15, -1);
        localLayoutParams2.addRule(9, -1);
        localLayoutParams2.addRule(0, localImageView.getId());
        localRelativeLayout.addView(this.f, localLayoutParams2);
        a(0);
      }
    }

    public final void a(int paramInt)
    {
      int m = paramInt + this.c.c();
      if (this.f != null)
        this.f.setText(this.c.b(m));
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.o
 * JD-Core Version:    0.6.2
 */