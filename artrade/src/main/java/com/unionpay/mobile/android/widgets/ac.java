package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.SpannableString;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class ac extends y
{
  private int a = 0;
  private String b;
  private TextView n;
  private TextView o;

  public ac(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    this(paramContext, paramInt, paramJSONObject, (byte)0);
  }

  private ac(Context paramContext, int paramInt, JSONObject paramJSONObject, byte paramByte)
  {
    super(paramContext, paramJSONObject);
    this.a = paramInt;
    if (paramJSONObject != null)
      this.b = f.a(paramJSONObject, "style");
    RelativeLayout localRelativeLayout = this.l;
    LinearLayout localLinearLayout = new LinearLayout(this.c);
    localRelativeLayout.addView(localLinearLayout, new RelativeLayout.LayoutParams(-1, -2));
    localLinearLayout.setOrientation(0);
    this.n = new TextView(this.c);
    this.n.setTextSize(b.k);
    this.n.setText(o());
    this.n.setGravity(3);
    this.n.setTextColor(-13421773);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(0, -2, 0.3F);
    localLayoutParams1.gravity = 3;
    localLinearLayout.addView(this.n, localLayoutParams1);
    this.o = new TextView(this.c);
    this.o.setGravity(16);
    this.o.setTextSize(b.k);
    SpannableString localSpannableString = com.unionpay.mobile.android.data.a.a(h(), this.b);
    this.o.setText(localSpannableString);
    this.o.setPadding(0, 0, com.unionpay.mobile.android.global.a.g, 0);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(0, -2, 0.7F);
    localLayoutParams2.gravity = 21;
    localLinearLayout.addView(this.o, localLayoutParams2);
  }

  public ac(Context paramContext, JSONObject paramJSONObject)
  {
    super(paramContext, paramJSONObject);
    String str1 = o();
    if ((str1 != null) && (str1.length() > 0))
    {
      this.n = new TextView(this.c);
      this.n.setTextSize(b.k);
      this.n.setText(o());
      this.n.setTextColor(-7829368);
      addView(this.n);
    }
    String str2 = h();
    if ((str2 != null) && (str2.length() > 0))
    {
      this.o = new TextView(this.c);
      this.o.setTextSize(b.k);
      this.o.setTextColor(-7829368);
      this.o.setText(h());
      addView(this.o);
    }
  }

  public final String a()
  {
    return null;
  }

  public final void a(float paramFloat)
  {
    this.o.setTextSize(paramFloat);
  }

  public final boolean b()
  {
    return true;
  }

  public final boolean c()
  {
    return true;
  }

  public final void f()
  {
    this.o.setTextColor(-6710887);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ac
 * JD-Core Version:    0.6.2
 */