package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

abstract class az extends LinearLayout
{
  private String a = null;
  private String b = null;
  protected Context c = null;
  protected int d = -16777216;
  protected int e = -7829368;
  protected String f = null;
  protected String g = null;
  protected boolean h = false;
  protected String i = null;
  protected LinearLayout j = null;
  protected TextView k = null;
  protected RelativeLayout l = null;
  protected JSONObject m;
  private String n = null;
  private String o = null;
  private TextView p = null;
  private ImageView q = null;
  private boolean r = false;

  public az(Context paramContext, JSONObject paramJSONObject)
  {
    super(paramContext);
    this.m = paramJSONObject;
    this.c = paramContext;
    this.g = f.a(paramJSONObject, "label");
    this.o = f.a(paramJSONObject, "placeholder");
    this.n = f.a(paramJSONObject, "tip");
    this.a = f.a(paramJSONObject, "name");
    this.f = f.a(paramJSONObject, "value");
    this.b = f.a(paramJSONObject, "type");
    this.i = f.a(paramJSONObject, "regexp");
    String str = f.a(paramJSONObject, "readonly");
    if ((str != null) && (str.equalsIgnoreCase("true")))
      this.h = true;
    boolean bool;
    if (f.a(paramJSONObject, "margin").length() > 0)
    {
      bool = true;
      this.r = bool;
      setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
      setBackgroundColor(0);
      setOrientation(1);
      setPadding(2, 2, 2, 2);
      if (this.b.equalsIgnoreCase("string"))
        break label631;
      if (!a(this, this.g))
      {
        this.p = new TextView(this.c);
        this.p.setTextSize(20.0F);
        this.p.setText("");
        this.p.setTextColor(this.d);
        LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams3.leftMargin = a.f;
        addView(this.p, localLayoutParams3);
        if ((this.g != null) && (this.g.length() != 0))
          break label588;
        this.p.setVisibility(8);
      }
    }
    while (true)
    {
      a();
      if (!b_())
      {
        this.j = new LinearLayout(this.c);
        this.j.setBackgroundColor(-267336);
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        addView(this.j, localLayoutParams1);
        this.k = new TextView(this.c);
        this.k.setTextSize(15.0F);
        this.k.setTextColor(this.e);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        int i1 = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
        localLayoutParams2.rightMargin = i1;
        localLayoutParams2.leftMargin = i1;
        int i2 = com.unionpay.mobile.android.utils.c.a(this.c, 5.0F);
        localLayoutParams2.bottomMargin = i2;
        localLayoutParams2.topMargin = i2;
        this.j.addView(this.k, localLayoutParams2);
        if ((this.n != null) && (this.n.length() > 0))
          break label611;
        this.j.setVisibility(8);
        this.q.setVisibility(8);
      }
      return;
      bool = false;
      break;
      label588: this.p.setText(this.g);
      this.p.setVisibility(8);
    }
    label611: this.q.setVisibility(0);
    this.k.setText(this.n);
    return;
    label631: a();
  }

  private void a()
  {
    FrameLayout localFrameLayout = new FrameLayout(this.c);
    addView(localFrameLayout, new LinearLayout.LayoutParams(-1, -2));
    this.l = new RelativeLayout(this.c);
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -2);
    localFrameLayout.addView(this.l, localLayoutParams1);
    this.q = new ImageView(this.c);
    this.q.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1038));
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(com.unionpay.mobile.android.utils.c.a(this.c, 10.0F), com.unionpay.mobile.android.utils.c.a(this.c, 5.0F));
    localLayoutParams2.gravity = 80;
    localLayoutParams2.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 20.0F);
    this.q.setVisibility(8);
    localFrameLayout.addView(this.q, localLayoutParams2);
  }

  protected final void a(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    if ((this.p != null) && (paramCharSequence != null) && (paramCharSequence.length() > 0))
      this.p.setText(paramCharSequence, paramBufferType);
  }

  protected boolean a(LinearLayout paramLinearLayout, String paramString)
  {
    return false;
  }

  public boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  protected boolean b_()
  {
    return false;
  }

  protected final void c(String paramString)
  {
    if ((this.k != null) && (paramString != null) && (paramString.length() > 0))
      this.k.setText(paramString);
  }

  public boolean e()
  {
    return true;
  }

  public String h()
  {
    return this.f;
  }

  public final String m()
  {
    return this.a;
  }

  public final String n()
  {
    return this.b;
  }

  public final String o()
  {
    return this.g;
  }

  public final String p()
  {
    return this.n;
  }

  public final String q()
  {
    return this.o;
  }

  protected final void r()
  {
    if (this.p != null)
      this.p.setVisibility(0);
  }

  protected final void s()
  {
    if (this.k != null)
    {
      this.k.setVisibility(0);
      this.q.setVisibility(0);
    }
  }

  protected final void t()
  {
    if (this.p != null)
      this.p.setTextSize(16.0F);
  }

  public static abstract interface a
  {
    public abstract String a();

    public abstract boolean b();

    public abstract boolean c();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.az
 * JD-Core Version:    0.6.2
 */