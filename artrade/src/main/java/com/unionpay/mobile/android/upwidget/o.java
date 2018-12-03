package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class o extends RelativeLayout
{
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";
  private String f = "";
  private String g = "";
  private String h = "";
  private String i = "";
  private String j = "";
  private RelativeLayout k;
  private Button l = null;
  private boolean m = false;
  private Context n = null;
  private float o = 0.0F;
  private View.OnClickListener p = new p(this);
  private View.OnClickListener q = new q(this);
  private String r;
  private TextView s;
  private a t;

  public o(Context paramContext, JSONObject paramJSONObject)
  {
    this(paramContext, paramJSONObject, (byte)0);
  }

  private o(Context paramContext, JSONObject paramJSONObject, byte paramByte)
  {
    super(paramContext);
    this.n = paramContext;
    this.o = 16.0F;
    this.a = f.a(paramJSONObject, "name");
    this.b = f.a(paramJSONObject, "type");
    this.c = f.a(paramJSONObject, "value");
    this.d = f.a(paramJSONObject, "label");
    this.e = f.a(paramJSONObject, "href_label");
    this.f = f.a(paramJSONObject, "href_url");
    this.g = f.a(paramJSONObject, "href_title");
    this.h = f.a(paramJSONObject, "checked");
    this.i = f.a(paramJSONObject, "required");
    this.j = f.a(paramJSONObject, "error_info");
    this.r = f.a(paramJSONObject, "ckb_style");
    this.k = new RelativeLayout(this.n);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, a.n);
    addView(this.k, localLayoutParams1);
    this.l = new Button(this.n);
    this.l.setId(this.l.hashCode());
    if ((a(this.h)) && (this.h.equalsIgnoreCase("0")));
    for (this.m = true; ; this.m = false)
    {
      this.l.setOnClickListener(this.p);
      c();
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(com.unionpay.mobile.android.utils.c.a(this.n, 60.0F), com.unionpay.mobile.android.utils.c.a(this.n, 34.0F));
      localLayoutParams2.addRule(11, -1);
      localLayoutParams2.addRule(15, -1);
      this.k.addView(this.l, localLayoutParams2);
      if (this.t != null)
        this.t.a(this.b, this.m);
      if ((a(this.e)) && (a(this.f)))
      {
        TextView localTextView = new TextView(this.n);
        localTextView.setText(Html.fromHtml(this.e));
        localTextView.setTextSize(this.o);
        localTextView.setOnClickListener(this.q);
        localTextView.setTextColor(d.a(-10705958, -5846275, -5846275, -6710887));
        RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams4.addRule(0, this.l.getId());
        localLayoutParams4.addRule(15, -1);
        localLayoutParams4.rightMargin = com.unionpay.mobile.android.utils.c.a(this.n, 10.0F);
        this.k.addView(localTextView, localLayoutParams4);
      }
      if (a(this.d))
      {
        this.s = new TextView(this.n);
        this.s.setText(this.d);
        this.s.setTextSize(this.o);
        this.s.setTextColor(-16777216);
        RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams3.addRule(9, -1);
        localLayoutParams3.addRule(15, -1);
        this.k.addView(this.s, localLayoutParams3);
      }
      return;
    }
  }

  private static boolean a(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0);
  }

  private void c()
  {
    if (this.l == null)
      return;
    if (this.m);
    for (int i1 = 1010; ; i1 = 1009)
    {
      Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.n).a(i1, com.unionpay.mobile.android.utils.c.a(this.n, 60.0F), com.unionpay.mobile.android.utils.c.a(this.n, 34.0F));
      this.l.setBackgroundDrawable(localDrawable);
      return;
    }
  }

  public final void a()
  {
    if (this.s != null)
      this.s.setTextColor(-13421773);
  }

  public final void a(float paramFloat)
  {
    if (this.s != null)
      this.s.setTextSize(paramFloat);
  }

  public final void a(a parama)
  {
    this.t = parama;
  }

  public final void a(boolean paramBoolean)
  {
    this.m = paramBoolean;
    c();
  }

  public final boolean b()
  {
    boolean bool = true;
    if ((a(this.i)) && (this.i.equalsIgnoreCase("0")))
      bool = this.m;
    return bool;
  }

  public static abstract interface a
  {
    public abstract void a(String paramString1, String paramString2);

    public abstract void a(String paramString, boolean paramBoolean);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.o
 * JD-Core Version:    0.6.2
 */