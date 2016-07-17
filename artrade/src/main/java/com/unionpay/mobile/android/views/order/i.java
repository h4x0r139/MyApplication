package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public final class i extends AbstractMethod
{
  private JSONObject g;
  private JSONObject h;
  private com.unionpay.mobile.android.upviews.a i;
  private TextView j;
  private TextView k;
  private RelativeLayout l;

  public i(Context paramContext)
  {
    super(paramContext);
  }

  private static JSONArray e(String paramString)
  {
    JSONArray localJSONArray = new JSONArray();
    try
    {
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("label", "");
      localJSONObject1.put("name", "user_name");
      if (!a(paramString))
        localJSONObject1.put("value", paramString);
      localJSONObject1.put("regexp", "[.@_A-Za-z0-9]{1,64}");
      localJSONObject1.put("type", "user_name");
      localJSONObject1.put("tip", "");
      localJSONObject1.put("placeholder", com.unionpay.mobile.android.languages.c.by.br);
      localJSONArray.put(localJSONObject1);
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("label", "");
      localJSONObject2.put("name", "password");
      localJSONObject2.put("type", "password");
      localJSONObject2.put("placeholder", com.unionpay.mobile.android.languages.c.by.bs);
      localJSONArray.put(localJSONObject2);
      return localJSONArray;
    }
    catch (Exception localException)
    {
    }
    return localJSONArray;
  }

  public final i a(JSONObject paramJSONObject)
  {
    this.g = paramJSONObject;
    if (this.j != null)
    {
      String str = a(this.g, "label");
      if (!TextUtils.isEmpty(str))
      {
        this.j.setText(Html.fromHtml(str));
        if (this.l != null)
          this.l.setVisibility(0);
      }
    }
    return this;
  }

  public final void a(RelativeLayout paramRelativeLayout)
  {
    TextView localTextView = new TextView(this.b);
    localTextView.setText(this.c);
    localTextView.setTextSize(b.k);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(9, -1);
    localLayoutParams.addRule(15, -1);
    localLayoutParams.leftMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    paramRelativeLayout.addView(localTextView, localLayoutParams);
  }

  public final int b()
  {
    return l.c.intValue();
  }

  public final i b(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public final i b(JSONObject paramJSONObject)
  {
    this.h = paramJSONObject;
    if (this.k != null)
    {
      String str = a(this.h, "label");
      if (!TextUtils.isEmpty(str))
      {
        this.k.setText(Html.fromHtml(str));
        if (this.l != null)
          this.l.setVisibility(0);
      }
    }
    return this;
  }

  public final void b(RelativeLayout paramRelativeLayout)
  {
    this.i = new com.unionpay.mobile.android.upviews.a(this.b, e(PreferenceUtils.c(this.b)), this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
    paramRelativeLayout.addView(this.i, localLayoutParams);
  }

  public final a.a c()
  {
    if (this.i != null)
      return this.i.a();
    return null;
  }

  public final void c(RelativeLayout paramRelativeLayout)
  {
    String str1 = a(this.g, "label");
    this.j = new TextView(this.b);
    a(this.j);
    if (!TextUtils.isEmpty(str1))
      this.j.setText(Html.fromHtml(str1));
    this.j.setOnClickListener(new j(this));
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.addRule(9, -1);
    localLayoutParams1.addRule(15, -1);
    localLayoutParams1.leftMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    paramRelativeLayout.addView(this.j, localLayoutParams1);
    String str2 = a(this.h, "label");
    this.k = new TextView(this.b);
    a(this.k);
    if (!TextUtils.isEmpty(str2))
      this.k.setText(Html.fromHtml(str2));
    this.k.setOnClickListener(new k(this));
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(11, -1);
    localLayoutParams2.addRule(15, -1);
    localLayoutParams2.rightMargin = com.unionpay.mobile.android.utils.c.a(this.b, 10.0F);
    paramRelativeLayout.addView(this.k, localLayoutParams2);
    if ((TextUtils.isEmpty(str2)) && (TextUtils.isEmpty(str1)))
      paramRelativeLayout.setVisibility(8);
    this.l = paramRelativeLayout;
  }

  public final i d(String paramString)
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
    return (this.i == null) || (this.i.c());
  }

  public final String h()
  {
    if (this.i != null)
      return this.i.b("user_name");
    return "";
  }

  protected final void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    TextUtils.isEmpty(h());
  }

  protected final void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.i != null)
      this.i.d();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.i
 * JD-Core Version:    0.6.2
 */