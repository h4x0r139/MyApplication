package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine.a;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.upwidget.UPScrollView.a;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.ac;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.ae;
import com.unionpay.mobile.android.widgets.ag;
import com.unionpay.mobile.android.widgets.an;
import com.unionpay.mobile.android.widgets.ar;
import com.unionpay.mobile.android.widgets.at;
import com.unionpay.mobile.android.widgets.au;
import com.unionpay.mobile.android.widgets.ax.a;
import com.unionpay.mobile.android.widgets.t;
import com.unionpay.mobile.android.widgets.x;
import com.unionpay.mobile.android.widgets.y;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.mobile.android.widgets.z.a;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class b extends RelativeLayout
  implements UPPayEngine.a, a, UPScrollView.a, ax.a, z.a
{
  protected com.unionpay.mobile.android.model.b a = null;
  protected com.unionpay.mobile.android.widgets.m b = null;
  protected com.unionpay.mobile.android.resource.c c = null;
  protected Context d = null;
  protected UPPayEngine e = null;
  protected int f = 0;
  protected String g = null;
  protected String h = null;
  protected boolean i = true;
  protected RelativeLayout j = null;
  protected ViewGroup k = null;
  protected RelativeLayout l = null;
  protected ar m = null;
  protected UPScrollView n = null;
  protected com.unionpay.mobile.android.model.d o;
  private LinearLayout p;
  private LinearLayout q;
  private LinearLayout r;
  private int s;
  private int t;

  public b(Context paramContext)
  {
    this(paramContext, null);
  }

  public b(Context paramContext, com.unionpay.mobile.android.model.d paramd)
  {
    super(paramContext);
    this.d = paramContext;
    this.o = paramd;
    this.e = ((UPPayEngine)((BaseActivity)paramContext).a(UPPayEngine.class.toString()));
    this.a = ((com.unionpay.mobile.android.model.b)((BaseActivity)paramContext).a(null));
    this.b = ((com.unionpay.mobile.android.widgets.m)((BaseActivity)paramContext).a(com.unionpay.mobile.android.widgets.m.class.toString()));
    this.c = com.unionpay.mobile.android.resource.c.a(paramContext);
    setId(8888);
    setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    setBackgroundColor(-1);
    com.unionpay.mobile.android.utils.g.b("uppayEx", "UPViewBase:" + toString());
  }

  protected static boolean b(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0);
  }

  protected static ColorStateList p()
  {
    return com.unionpay.mobile.android.utils.d.a(com.unionpay.mobile.android.global.b.b, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.c, com.unionpay.mobile.android.global.b.d);
  }

  private final RelativeLayout r()
  {
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    if (this.j != null)
    {
      localLayoutParams1.addRule(3, this.j.getId());
      localLayoutParams1.addRule(12, -1);
    }
    FrameLayout localFrameLayout = new FrameLayout(this.d);
    addView(localFrameLayout, localLayoutParams1);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -1);
    this.n = new UPScrollView(this.d);
    this.n.setPadding(0, 0, 0, 0);
    localFrameLayout.addView(this.n, localLayoutParams2);
    FrameLayout.LayoutParams localLayoutParams3 = new FrameLayout.LayoutParams(-1, -2);
    int i1 = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
    this.r = new LinearLayout(this.d);
    this.r.setId(this.r.hashCode());
    this.r.setOrientation(1);
    this.r.setBackgroundColor(-267336);
    this.r.setPadding(i1, i1, i1, i1);
    String str = "";
    if (b(this.a.am))
      str = str + this.a.am;
    if (b(str))
    {
      TextView localTextView = new TextView(this.d);
      localTextView.setTextColor(-10066330);
      localTextView.setText(str);
      localTextView.setTextSize(com.unionpay.mobile.android.global.b.k);
      this.r.addView(localTextView);
    }
    while (true)
    {
      this.r.setVisibility(8);
      localFrameLayout.addView(this.r, localLayoutParams3);
      RelativeLayout localRelativeLayout = new RelativeLayout(this.d);
      localRelativeLayout.setBackgroundColor(-1052684);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
      this.n.addView(localRelativeLayout, localLayoutParams4);
      return localRelativeLayout;
      this.r.setVisibility(8);
    }
  }

  protected final RelativeLayout a()
  {
    RelativeLayout localRelativeLayout = new RelativeLayout(getContext());
    localRelativeLayout.setId(localRelativeLayout.hashCode());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(10, -1);
    addView(localRelativeLayout, localLayoutParams);
    return localRelativeLayout;
  }

  public final void a(int paramInt)
  {
    ((BaseActivity)this.d).a(paramInt);
  }

  protected final void a(int paramInt, com.unionpay.mobile.android.model.d paramd)
  {
    BaseActivity localBaseActivity = (BaseActivity)this.d;
    Object localObject = null;
    switch (paramInt)
    {
    case 3:
    case 4:
    case 7:
    case 9:
    default:
    case 2:
    case 6:
    case 8:
    case 5:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    case 16:
    }
    while (true)
    {
      if (localObject != null)
        localBaseActivity.a((b)localObject);
      return;
      localObject = new ao(this.d, paramd);
      continue;
      List localList = this.a.l;
      int i1 = 0;
      if (localList != null)
      {
        int i2 = this.a.l.size();
        i1 = 0;
        if (i2 > 0)
          i1 = ((com.unionpay.mobile.android.model.c)this.a.l.get(this.a.I)).c();
      }
      if ((i()) || (i1 == 0) || (this.a.aF == l.c.intValue()))
      {
        localObject = new as(this.d, paramd);
      }
      else
      {
        localObject = localBaseActivity.b(6);
        continue;
        localObject = new bc(this.d);
        continue;
        localObject = new g(this.d);
        continue;
        localObject = new ak(this.d);
        continue;
        localObject = new ai(this.d);
        continue;
        localObject = new af(this.d);
        continue;
        localObject = new o(this.d, paramd);
        continue;
        localObject = new bh(this.d);
        continue;
        localObject = localBaseActivity.b(paramInt);
      }
    }
  }

  public final void a(int paramInt, String paramString)
  {
    this.i = true;
    com.unionpay.mobile.android.utils.g.a("uppay", " " + toString());
    if (paramInt == 0)
    {
      com.unionpay.mobile.android.utils.g.a("uppay", "parserResponseMesage() +++");
      JSONObject localJSONObject1 = null;
      int i1;
      if ((paramString == null) || (paramString.length() == 0))
      {
        com.unionpay.mobile.android.utils.g.a("uppay", " ERROR_MSG_FORMAT");
        i1 = 2;
        if (i1 == 0)
          break label246;
        if (!a(this.h, localJSONObject1))
          b(i1);
      }
      while (true)
      {
        while (true)
        {
          com.unionpay.mobile.android.utils.g.a("uppay", "parserResponseMesage() ---");
          return;
          try
          {
            JSONObject localJSONObject2 = new JSONObject(paramString);
            this.g = com.unionpay.mobile.android.utils.f.a(localJSONObject2, "resp");
            this.h = com.unionpay.mobile.android.utils.f.a(localJSONObject2, "msg");
            localJSONObject1 = com.unionpay.mobile.android.utils.f.b(localJSONObject2, "params");
            boolean bool = this.g.equalsIgnoreCase("00");
            i1 = 0;
            if (bool)
              break;
            if (!this.g.equalsIgnoreCase("21"))
              break label230;
            i1 = 17;
            com.unionpay.mobile.android.utils.g.a("uppay", " ERROR_ORDER_TIMEOUT");
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
            com.unionpay.mobile.android.utils.g.a("uppay", " ERROR_MSG_FORMAT");
            i1 = 2;
          }
        }
        break;
        label230: i1 = 3;
        com.unionpay.mobile.android.utils.g.a("uppay", " ERROR_TRANSACTION");
        break;
        label246: a(localJSONObject1);
      }
    }
    b(paramInt);
  }

  public final void a(t paramt, String paramString)
  {
  }

  protected final void a(String paramString)
  {
    a(paramString, false);
  }

  protected final void a(String paramString, View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    this.b.a(paramOnClickListener1, paramOnClickListener2);
    this.b.a(com.unionpay.mobile.android.languages.c.by.Y, paramString, com.unionpay.mobile.android.languages.c.by.W, com.unionpay.mobile.android.languages.c.by.X, false);
  }

  protected final void a(String paramString1, String paramString2)
  {
    ((InputMethodManager)this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
    this.a.ab = paramString2;
    this.a.aa = paramString1;
    d(14);
  }

  protected final void a(String paramString, boolean paramBoolean)
  {
    d locald = new d(this, paramBoolean);
    com.unionpay.mobile.android.utils.g.a("uppay", " showErrDialog(msg, boolean)  ");
    this.b.a(locald, null);
    this.b.a(com.unionpay.mobile.android.languages.c.by.Y, paramString, com.unionpay.mobile.android.languages.c.by.W);
  }

  protected boolean a(String paramString, JSONObject paramJSONObject)
  {
    return false;
  }

  protected final y b(JSONObject paramJSONObject)
  {
    String str = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "type");
    int i1 = com.unionpay.mobile.android.global.a.I - 4 * com.unionpay.mobile.android.global.a.f;
    Object localObject;
    if ("pan".equalsIgnoreCase(str))
      localObject = new ae(this.d, i1, paramJSONObject);
    while (true)
    {
      if ((localObject != null) && ((localObject instanceof z)))
        ((z)localObject).a(this);
      return localObject;
      if ("mobile".equalsIgnoreCase(str))
      {
        localObject = new ag(this.d, i1, paramJSONObject);
      }
      else if ("sms".equalsIgnoreCase(str))
      {
        localObject = new com.unionpay.mobile.android.widgets.ao(this.d, i1, paramJSONObject);
      }
      else if ("cvn2".equalsIgnoreCase(str))
      {
        localObject = new com.unionpay.mobile.android.widgets.e(this.d, i1, paramJSONObject);
      }
      else if ("expire".equalsIgnoreCase(str))
      {
        localObject = new au(this.d, i1, paramJSONObject);
      }
      else if ("pwd".equalsIgnoreCase(str))
      {
        localObject = new UPWidget(this.d, this.e.b(), i1, paramJSONObject);
      }
      else if ("text".equalsIgnoreCase(str))
      {
        localObject = new com.unionpay.mobile.android.widgets.as(this.d, i1, paramJSONObject);
      }
      else if ("string".equalsIgnoreCase(str))
      {
        localObject = new ac(this.d, paramJSONObject);
      }
      else if ("cert_id".equalsIgnoreCase(str))
      {
        localObject = new com.unionpay.mobile.android.widgets.f(this.d, i1, paramJSONObject);
      }
      else if ("cert_type".equalsIgnoreCase(str))
      {
        localObject = new com.unionpay.mobile.android.widgets.g(this.d, paramJSONObject);
      }
      else if ("name".equalsIgnoreCase(str))
      {
        localObject = new ad(this.d, i1, paramJSONObject);
      }
      else if ("hidden".equalsIgnoreCase(str))
      {
        localObject = new x(this.d, paramJSONObject);
      }
      else if ("user_name".equalsIgnoreCase(str))
      {
        localObject = new at(this.d, i1, paramJSONObject);
      }
      else
      {
        boolean bool = "password".equalsIgnoreCase(str);
        localObject = null;
        if (bool)
          localObject = new an(this.d, i1, paramJSONObject);
      }
    }
  }

  protected void b()
  {
  }

  public void b(int paramInt)
  {
    if ((paramInt == 8) || (paramInt == 17) || (paramInt == 19))
    {
      this.a.D.f = "fail";
      com.unionpay.mobile.android.utils.g.a("uppay", "showErrDialog 1");
      a(c(paramInt), true);
      return;
    }
    com.unionpay.mobile.android.utils.g.a("uppay", "showErrDialog 2");
    a(c(paramInt), false);
  }

  protected void b(String paramString, JSONObject paramJSONObject)
  {
  }

  protected final String c(int paramInt)
  {
    switch (paramInt)
    {
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 15:
    default:
      return com.unionpay.mobile.android.languages.c.by.aA;
    case 2:
      return com.unionpay.mobile.android.languages.c.by.aB;
    case 7:
      return com.unionpay.mobile.android.languages.c.by.aG;
    case 5:
      return com.unionpay.mobile.android.languages.c.by.aH;
    case 6:
      return com.unionpay.mobile.android.languages.c.by.aI;
    case 4:
      return com.unionpay.mobile.android.languages.c.by.az;
    case 8:
      return com.unionpay.mobile.android.languages.c.by.aJ;
    case 9:
      return com.unionpay.mobile.android.languages.c.by.aK;
    case 21:
      return com.unionpay.mobile.android.languages.c.by.aL;
    case 16:
      return com.unionpay.mobile.android.languages.c.by.aM;
    case 19:
      return com.unionpay.mobile.android.languages.c.by.aN;
    case 20:
      return com.unionpay.mobile.android.languages.c.by.aO;
    case 18:
      return com.unionpay.mobile.android.languages.c.by.aP;
    case 3:
    case 17:
    }
    return this.h;
  }

  protected void c()
  {
  }

  protected final boolean c(JSONObject paramJSONObject)
  {
    boolean bool1 = com.unionpay.mobile.android.nocard.utils.f.c(this.a, paramJSONObject);
    boolean bool2 = false;
    if (bool1)
    {
      d(paramJSONObject);
      bool2 = true;
    }
    return bool2;
  }

  protected void d()
  {
    this.l = r();
  }

  protected final void d(int paramInt)
  {
    BaseActivity localBaseActivity = (BaseActivity)this.d;
    Object localObject;
    switch (paramInt)
    {
    case 3:
    case 4:
    case 7:
    case 9:
    default:
      localObject = null;
    case 2:
    case 6:
    case 8:
    case 5:
    case 10:
    case 11:
    case 12:
    case 13:
    case 14:
    case 17:
    case 15:
    case 16:
    }
    while (true)
    {
      if (localObject != null)
        localBaseActivity.a((b)localObject);
      return;
      localObject = new ao(this.d, null);
      continue;
      List localList = this.a.l;
      int i1 = 0;
      if (localList != null)
      {
        int i2 = this.a.l.size();
        i1 = 0;
        if (i2 > 0)
          i1 = ((com.unionpay.mobile.android.model.c)this.a.l.get(this.a.I)).c();
      }
      if ((i()) || (i1 == 0))
      {
        localObject = new as(this.d);
      }
      else
      {
        localObject = localBaseActivity.b(6);
        continue;
        localObject = new bc(this.d);
        continue;
        localObject = new g(this.d);
        continue;
        localObject = new ak(this.d);
        continue;
        localObject = new ai(this.d);
        continue;
        localObject = new af(this.d);
        continue;
        localObject = new o(this.d, null);
        continue;
        localObject = new bh(this.d);
        continue;
        localObject = localBaseActivity.b(paramInt);
        continue;
        localObject = localBaseActivity.b(paramInt);
      }
    }
  }

  protected final void d(JSONObject paramJSONObject)
  {
    e locale = new e(this, paramJSONObject);
    f localf = new f(this, paramJSONObject);
    this.b.a(locale, localf);
    this.b.a(this.a.aw, this.a.ax, this.a.ay, this.a.aA);
  }

  protected final void e()
  {
    this.j = a();
    b();
    RelativeLayout localRelativeLayout1 = r();
    LinearLayout localLinearLayout = new LinearLayout(this.d);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setId(localLinearLayout.hashCode());
    localLinearLayout.setBackgroundColor(-1114114);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams1.addRule(10, -1);
    localRelativeLayout1.addView(localLinearLayout, localLayoutParams1);
    this.k = localLinearLayout;
    this.k.setBackgroundColor(0);
    f();
    int i1 = this.k.getId();
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams2.bottomMargin = com.unionpay.mobile.android.global.a.b;
    localLayoutParams2.addRule(12, -1);
    localLayoutParams2.addRule(3, i1);
    RelativeLayout localRelativeLayout2 = new RelativeLayout(this.d);
    localRelativeLayout1.addView(localRelativeLayout2, localLayoutParams2);
    this.l = localRelativeLayout2;
    c();
  }

  public final void e(int paramInt)
  {
    if (paramInt >= this.t)
      if ((this.r.getVisibility() != 0) && (this.r != null) && (this.p.getVisibility() == 0))
        this.r.setVisibility(0);
    while ((paramInt > this.t + this.s) || (this.r.getVisibility() != 0) || (this.r == null))
      return;
    this.r.setVisibility(8);
  }

  protected void f()
  {
    this.q = new LinearLayout(this.d);
    this.q.setOrientation(1);
    this.q.setBackgroundColor(-267336);
    int i1 = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
    label185: label355: com.unionpay.mobile.android.views.order.m localm;
    if (b(this.a.am))
    {
      this.q.setPadding(i1, i1, i1, 0);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams1.topMargin = 0;
      this.k.addView(this.q, localLayoutParams1);
      String str1 = "";
      if (b(this.a.ao))
        str1 = str1 + this.a.ao;
      if (!b(str1))
        break label546;
      TextView localTextView1 = new TextView(this.d);
      localTextView1.setTextColor(-10066330);
      localTextView1.setText(str1);
      localTextView1.setTextSize(com.unionpay.mobile.android.global.b.k);
      this.q.addView(localTextView1);
      this.p = new LinearLayout(this.d);
      this.p.setOrientation(1);
      this.p.setBackgroundColor(-267336);
      this.p.setPadding(i1, i1, i1, i1);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams2.topMargin = 0;
      this.k.addView(this.p, localLayoutParams2);
      String str2 = "";
      if (b(this.a.am))
        str2 = str2 + this.a.am;
      if (!b(str2))
        break label558;
      TextView localTextView2 = new TextView(this.d);
      localTextView2.setTextColor(-10066330);
      localTextView2.setText(str2);
      localTextView2.setTextSize(com.unionpay.mobile.android.global.b.k);
      this.p.addView(localTextView2);
      this.p.getViewTreeObserver().addOnPreDrawListener(new c(this));
      localm = new com.unionpay.mobile.android.views.order.m(this.d);
      localm.a(this.c.a(1003), this.c.a(1001));
      if (!(this instanceof ao))
        break label570;
    }
    label546: label558: label570: for (boolean bool = false; ; bool = true)
    {
      localm.a(bool, this.a.e, this.a.f);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
      this.k.addView(localm, localLayoutParams3);
      Drawable localDrawable = this.c.a(1026);
      LinearLayout localLinearLayout = new LinearLayout(this.d);
      if (localDrawable != null)
        localLinearLayout.setBackgroundDrawable(localDrawable);
      LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.c.a(this.d, 2.0F));
      this.k.addView(localLinearLayout, localLayoutParams4);
      return;
      this.q.setPadding(i1, i1, i1, i1);
      break;
      this.q.setVisibility(8);
      break label185;
      this.p.setVisibility(8);
      break label355;
    }
  }

  protected final void g()
  {
  }

  public final int h()
  {
    return this.f;
  }

  protected final boolean i()
  {
    return (this.a.E) || (this.a.l == null) || (this.a.l.size() == 0);
  }

  protected final void j()
  {
    if ((this.b != null) && (this.b.a()))
      this.b.c();
  }

  public final void k()
  {
    com.unionpay.mobile.android.nocard.utils.d.a(this.d, this.a);
  }

  public void l()
  {
    if (this.i)
      n();
  }

  public final void m()
  {
    l();
  }

  public final void n()
  {
    ((BaseActivity)this.d).a();
  }

  protected final boolean o()
  {
    com.unionpay.mobile.android.widgets.m localm = this.b;
    boolean bool1 = false;
    if (localm != null)
    {
      boolean bool2 = this.b.a();
      bool1 = false;
      if (bool2)
        bool1 = true;
    }
    com.unionpay.mobile.android.utils.g.a("uppay", " dialog showing:" + bool1);
    return bool1;
  }

  protected void onAttachedToWindow()
  {
    com.unionpay.mobile.android.utils.g.b("uppayEx", toString() + " onAttachedToWindow()");
    super.onAttachedToWindow();
    this.e.a(this);
  }

  protected final boolean q()
  {
    return !this.a.c;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.b
 * JD-Core Version:    0.6.2
 */