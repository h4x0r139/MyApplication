package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
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
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.mobile.android.widgets.y;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public final class as extends b
  implements a.b
{
  private boolean A = false;
  private String B;
  private View.OnClickListener C = new at(this);
  private View.OnClickListener D = new au(this);
  private View.OnClickListener E = new av(this);
  private View.OnClickListener F = new aw(this);
  private View.OnClickListener G = new ax(this);
  private String p = "00";
  private int q = 0;
  private int r = 0;
  private int s = 20;
  private int t = 5;
  private com.unionpay.mobile.android.upwidget.a u = null;
  private com.unionpay.mobile.android.upwidget.a v = null;
  private TextView w = null;
  private com.unionpay.mobile.android.upviews.a x = null;
  private com.unionpay.mobile.android.upviews.a y = null;
  private boolean z = false;

  public as(Context paramContext)
  {
    this(paramContext, null);
  }

  public as(Context paramContext, d paramd)
  {
    super(paramContext, paramd);
    this.f = 6;
    boolean bool1 = this.a.F;
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    this.A = bool2;
    setBackgroundColor(-1052684);
    e();
  }

  private void c(String paramString1, String paramString2)
  {
    this.r = 9;
    if (TextUtils.isEmpty(paramString2))
      this.e.b(paramString1, "");
    while (true)
    {
      this.t = (-1 + this.t);
      return;
      String str = "\"uuid\":\"" + paramString2 + "\"";
      this.e.a(paramString1, str, 10);
    }
  }

  private void d(String paramString)
  {
    a(paramString, new ay(this), new az(this));
  }

  private void e(String paramString)
  {
    this.i = false;
    this.r = 3;
    String str1 = bg.a(this.a.x);
    String str2;
    if (this.u != null)
    {
      str2 = this.u.a();
      if ((str2 == null) || (str2.length() <= 0))
        break label92;
    }
    label92: for (String str3 = String.format("\"pay_type\":\"%s\",\"pay_mode\":\"%s\",%s,%s", new Object[] { "1", "1", str2, paramString }); ; str3 = String.format("\"pay_type\":\"%s\",\"pay_mode\":\"%s\",%s", new Object[] { "1", "1", paramString }))
    {
      this.e.b(str1, str3);
      return;
      str2 = null;
      break;
    }
  }

  private static boolean e(JSONObject paramJSONObject)
  {
    return "0".equalsIgnoreCase(com.unionpay.mobile.android.utils.f.a(paramJSONObject, "reopen_flag"));
  }

  private void f(int paramInt)
  {
    this.r = 4;
    this.q = paramInt;
    this.e.a("query", this.a.ae, 3);
    this.s = (-1 + this.s);
  }

  private String r()
  {
    String str1 = "";
    if (this.y != null)
    {
      a.a locala2 = this.y.a();
      if (locala2.a())
        str1 = str1 + locala2.b;
    }
    if (this.x != null)
    {
      a.a locala1 = this.x.a();
      if (locala1.a())
      {
        String str2 = locala1.b;
        if (!TextUtils.isEmpty(str2))
        {
          if (!TextUtils.isEmpty(str1))
            str1 = str1 + ",";
          str1 = str1 + str2;
        }
      }
    }
    return str1;
  }

  public final void a(a.a parama)
  {
    if (!parama.a())
    {
      a(parama.b);
      return;
    }
    g.a("uppay", "sms elements:" + parama.b);
    this.i = false;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    this.e.b("sms", parama.b);
    this.r = 1;
  }

  public final void a(JSONObject paramJSONObject)
  {
    this.A = false;
    switch (this.r)
    {
    case 5:
    default:
    case 1:
    case 6:
    case 4:
    case 2:
    case 3:
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
              return;
              j();
              this.r = 0;
              this.y.a(com.unionpay.mobile.android.global.b.p);
              return;
              j();
              int j = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, true);
              if (j != 0)
                b(j);
              while (true)
              {
                this.r = 0;
                return;
                this.a.F = true;
                d locald2 = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
                if ((this.a.u != null) && (this.a.u.length() > 0))
                  a(6, locald2);
                else if ((this.a.y != null) && (this.a.y.length() > 0))
                  d(5);
              }
              this.p = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "status");
              if (e(paramJSONObject))
              {
                d(com.unionpay.mobile.android.utils.f.a(paramJSONObject, "fail_msg"));
                return;
              }
              if ((this.s > 0) && (this.p.equalsIgnoreCase("01")))
              {
                f(this.q);
                return;
              }
              this.r = 0;
              if (this.p.equalsIgnoreCase("00"))
              {
                switch (this.q)
                {
                default:
                  j();
                  this.r = 0;
                  this.a.C = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "result");
                  this.a.K = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "openupgrade_flag");
                  this.a.L = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "temporary_pay_flag");
                  this.a.M = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "temporary_pay_info");
                  this.a.Q = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "front_url");
                  this.a.R = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "front_request");
                  this.a.v = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "title");
                  this.a.w = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "succ_info");
                  com.unionpay.mobile.android.nocard.utils.b.b(paramJSONObject, this.a);
                  com.unionpay.mobile.android.nocard.utils.b.a(paramJSONObject, this.a);
                  if (this.y != null)
                    this.y.d();
                  d(8);
                  return;
                case 2:
                }
                this.z = true;
                e(r());
                return;
              }
              if (this.p.equalsIgnoreCase("03"))
              {
                String str6 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "fail_msg");
                if (this.q == 3)
                {
                  a(str6);
                  return;
                }
                ba localba = new ba(this);
                bb localbb = new bb(this);
                if (this.a.A)
                {
                  this.b.a(localba, localbb);
                  this.b.a(com.unionpay.mobile.android.languages.c.by.ab, str6, com.unionpay.mobile.android.languages.c.by.ac, com.unionpay.mobile.android.languages.c.by.ad);
                  return;
                }
                this.b.a(localba, null);
                this.b.a(com.unionpay.mobile.android.languages.c.by.ab, str6, com.unionpay.mobile.android.languages.c.by.ac);
                return;
              }
            }
            while (this.s > 0);
            String str5 = c(19);
            if ((this.a.af != null) && (!TextUtils.isEmpty(this.a.af)))
              str5 = this.a.af;
            if (this.q == 3)
            {
              a(str5, true);
              return;
            }
            a(str5);
            return;
            this.a.ae = com.unionpay.mobile.android.utils.e.a(paramJSONObject.toString());
            if ((this.a.ae == null) || (this.a.ae.length() <= 0))
            {
              b(2);
              return;
            }
            this.s = 20;
            f(this.r);
            return;
            j();
            int i = com.unionpay.mobile.android.nocard.utils.f.a(this.a, paramJSONObject, false);
            if (i != 0)
            {
              b(i);
              return;
            }
            d locald1 = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
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
        while (this.x == null);
        this.x.a(localJSONArray2);
        return;
        String str1 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "status");
        if ((str1 == null) || (!"01".equals(str1)))
          break;
        String str3 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "uuid");
        if (this.t >= 0)
        {
          c(this.B, str3);
          return;
        }
        str4 = com.unionpay.mobile.android.languages.c.by.D;
      }
      while (this.x == null);
      this.x.a(null, str4);
      return;
      localJSONArray1 = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "options");
      str2 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "empty_info");
    }
    while (this.x == null);
    this.x.a(localJSONArray1, str2);
  }

  public final void a(boolean paramBoolean)
  {
    TextView localTextView;
    if (this.w != null)
    {
      localTextView = this.w;
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
    this.A = false;
    if ((this.r == 1) && (e(paramJSONObject)))
    {
      d(paramString);
      return true;
    }
    return false;
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    com.unionpay.mobile.android.widgets.ax localax = new com.unionpay.mobile.android.widgets.ax(getContext(), this.a.v, this);
    localLayoutParams.addRule(13, -1);
    this.j.addView(localax, localLayoutParams);
  }

  public final void b(String paramString1, String paramString2)
  {
    a(paramString1, paramString2);
  }

  protected final void c()
  {
    this.n.a(this);
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setBackgroundColor(0);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    this.l.addView(localLinearLayout1, localLayoutParams1);
    JSONArray localJSONArray = new JSONArray();
    if (this.o != null)
    {
      com.unionpay.mobile.android.model.e locale = (com.unionpay.mobile.android.model.e)this.o;
      localJSONArray.put(locale.a("promotion"));
      localJSONArray.put(locale.a("instalment"));
      this.a.aK = locale.a("promotion_instalment_msgbox");
    }
    if (localJSONArray.length() > 0)
    {
      this.x = new com.unionpay.mobile.android.upviews.a(this.d, localJSONArray, this);
      this.x.a(this.b, this.a.aK);
      this.x.a(this.D);
      this.x.b(this.E);
      this.x.c(this.F);
      LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams6.topMargin = com.unionpay.mobile.android.global.a.f;
      localLinearLayout1.addView(this.x, localLayoutParams6);
    }
    com.unionpay.mobile.android.upviews.a locala = this.x;
    y localy = null;
    if (locala != null)
      localy = this.x.c("instalment");
    this.y = new com.unionpay.mobile.android.upviews.a(this.d, this.a.u, this.e.b(), this, this.a.al, false, localy, this.a.Y);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
    localLinearLayout1.addView(this.y, localLayoutParams2);
    LinearLayout localLinearLayout2 = new LinearLayout(this.d);
    localLinearLayout2.setOrientation(1);
    localLinearLayout2.setId(localLinearLayout2.hashCode());
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams3.addRule(3, localLinearLayout1.getId());
    localLayoutParams3.leftMargin = com.unionpay.mobile.android.global.a.f;
    localLayoutParams3.topMargin = localLayoutParams3.leftMargin;
    this.l.addView(localLinearLayout2, localLayoutParams3);
    if ((i()) || (this.a.ag != null) || (this.a.ah != null))
    {
      if (this.a.ag != null)
      {
        this.v = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ag, this.G);
        localLinearLayout2.addView(this.v);
      }
      if (this.a.ah != null)
      {
        this.u = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.ah, null);
        LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
        localLinearLayout2.addView(this.u, localLayoutParams5);
      }
    }
    this.w = new TextView(this.d);
    this.w.setText(com.unionpay.mobile.android.utils.f.a(this.a.x, "label"));
    this.w.setTextSize(com.unionpay.mobile.android.global.b.i);
    this.w.setTextColor(p());
    this.w.setGravity(17);
    TextView localTextView = this.w;
    boolean bool1;
    if (this.y != null)
    {
      boolean bool2 = this.y.c();
      bool1 = false;
      if (!bool2);
    }
    else
    {
      bool1 = true;
    }
    localTextView.setEnabled(bool1);
    int i = com.unionpay.mobile.android.global.a.n;
    Drawable localDrawable = this.c.a(2008);
    this.w.setBackgroundDrawable(localDrawable);
    this.w.setOnClickListener(this.C);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, i);
    localLayoutParams4.addRule(3, localLinearLayout2.getId());
    localLayoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
    int j = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
    localLayoutParams4.rightMargin = j;
    localLayoutParams4.leftMargin = j;
    this.l.addView(this.w, localLayoutParams4);
  }

  public final void c(String paramString)
  {
    this.i = false;
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    if (i());
    for (String str = "\"card\":\"" + this.a.al + "\""; ; str = "\"card\":\"" + ((com.unionpay.mobile.android.model.c)this.a.l.get(this.a.I)).a() + "\"")
    {
      g.a("uppay", "cmd:" + paramString + ", ele:" + str);
      this.e.b(paramString, str);
      this.r = 6;
      return;
    }
  }

  public final void l()
  {
    if (this.y.b())
      return;
    if (this.a.G)
    {
      a(13);
      this.a.G = false;
      return;
    }
    if ((this.a.F) && (this.A))
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

  protected final void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.y.b();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.as
 * JD-Core Version:    0.6.2
 */