package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class a extends LinearLayout
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
  private Button j = null;
  private boolean k = false;
  private Context l = null;
  private float m = 0.0F;
  private View.OnClickListener n = new b(this);
  private String o;
  private TextView p;
  private a q;

  public a(Context paramContext, JSONObject paramJSONObject, View.OnClickListener paramOnClickListener)
  {
    this(paramContext, paramJSONObject, paramOnClickListener, (byte)0);
  }

  private a(Context paramContext, JSONObject paramJSONObject, View.OnClickListener paramOnClickListener, byte paramByte)
  {
    super(paramContext);
    this.l = paramContext;
    this.m = 16.0F;
    this.a = f.a(paramJSONObject, "name");
    this.b = f.a(paramJSONObject, "value");
    this.c = f.a(paramJSONObject, "label");
    this.d = f.a(paramJSONObject, "href_label");
    this.e = f.a(paramJSONObject, "href_url");
    this.f = f.a(paramJSONObject, "href_title");
    this.g = f.a(paramJSONObject, "checked");
    this.h = f.a(paramJSONObject, "required");
    this.i = f.a(paramJSONObject, "error_info");
    this.o = f.a(paramJSONObject, "ckb_style");
    this.j = new Button(this.l);
    if ((a(this.g)) && (this.g.equalsIgnoreCase("0")));
    for (this.k = true; ; this.k = false)
    {
      this.j.setOnClickListener(this.n);
      g();
      f();
      int i1 = com.unionpay.mobile.android.utils.c.a(this.l, 20.0F);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(i1, i1);
      localLayoutParams1.gravity = 16;
      addView(this.j, localLayoutParams1);
      if ((this.q == null) || (a(this.c)))
      {
        this.p = new TextView(this.l);
        this.p.setText(this.c);
        this.p.setTextSize(this.m);
        this.p.setTextColor(-16777216);
        this.p.setOnClickListener(this.n);
        LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams3.gravity = 16;
        localLayoutParams3.leftMargin = com.unionpay.mobile.android.global.a.d;
        addView(this.p, localLayoutParams3);
      }
      if ((a(this.d)) && (a(this.e)))
      {
        TextView localTextView = new TextView(this.l);
        localTextView.setText(Html.fromHtml(this.d));
        localTextView.setTextColor(d.a(-10705958, -5846275, -5846275, -6710887));
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this.d;
        String.format("<u>%s</u>", arrayOfObject);
        localTextView.setTextSize(this.m);
        localTextView.setOnClickListener(paramOnClickListener);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams2.gravity = 16;
        addView(localTextView, localLayoutParams2);
      }
      return;
    }
  }

  private static boolean a(String paramString)
  {
    return (paramString != null) && (paramString.length() > 0);
  }

  private boolean f()
  {
    boolean bool1 = "small".equalsIgnoreCase(this.o);
    boolean bool2 = false;
    if (bool1)
      bool2 = true;
    return bool2;
  }

  private void g()
  {
    if (this.j == null)
      return;
    int i1;
    if (this.k)
    {
      i1 = 1008;
      if (!f())
        break label66;
    }
    label66: for (int i2 = com.unionpay.mobile.android.utils.c.a(this.l, 15.0F); ; i2 = com.unionpay.mobile.android.global.a.w)
    {
      Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.l).a(i1, i2, i2);
      this.j.setBackgroundDrawable(localDrawable);
      return;
      i1 = 1007;
      break;
    }
  }

  public final String a()
  {
    if (this.k);
    for (String str = this.b; ; str = "")
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.a;
      arrayOfObject[1] = str;
      return String.format("\"%s\":\"%s\"", arrayOfObject);
    }
  }

  public final String b()
  {
    return this.i;
  }

  public final String c()
  {
    return this.e;
  }

  public final String d()
  {
    return this.f;
  }

  public final boolean e()
  {
    boolean bool = true;
    if ((a(this.h)) && (this.h.equalsIgnoreCase("0")))
      bool = this.k;
    return bool;
  }

  public static abstract interface a
  {
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.a
 * JD-Core Version:    0.6.2
 */