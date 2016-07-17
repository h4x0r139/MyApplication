package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.ForegroundColorSpan;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.upwidget.h;
import com.unionpay.mobile.android.upwidget.o;
import com.unionpay.mobile.android.upwidget.o.a;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class ai extends y
{
  private final View.OnClickListener a = new aj(this);
  private final View.OnClickListener b = new ak(this);
  private final AdapterView.OnItemClickListener n = new al(this);
  private JSONArray o = f.c(this.m, "items");
  private PopupWindow p;
  private h q;
  private int r = 0;
  private int s = 0;
  private JSONArray t = null;
  private JSONArray u = null;
  private TextView v;
  private o w;
  private TextView x;
  private String y;
  private RelativeLayout z;

  public ai(Context paramContext, JSONObject paramJSONObject)
  {
    super(paramContext, paramJSONObject);
    this.y = f.a(paramJSONObject, "label");
    if (a(this.y))
      this.y = com.unionpay.mobile.android.languages.c.by.bg;
    if (!TextUtils.isEmpty(f.a(paramJSONObject, "default_item_idx")))
      this.s = Integer.parseInt(f.a(paramJSONObject, "default_item_idx"));
    this.q = new h(paramContext, this.o, this.s);
    this.q.a(this.n);
    this.q.a(this.a);
    this.q.d(this.b);
    RelativeLayout localRelativeLayout = this.l;
    Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.c).a(2014);
    LinearLayout localLinearLayout1 = new LinearLayout(this.c);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    localLinearLayout1.setBackgroundColor(-3419943);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, 1);
    f.a(this.m, "type");
    localRelativeLayout.addView(localLinearLayout1, localLayoutParams1);
    this.z = new RelativeLayout(this.c);
    this.z.setId(this.z.hashCode());
    this.z.setBackgroundDrawable(localDrawable);
    this.z.setOnClickListener(new am(this));
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
    localLayoutParams2.addRule(15, -1);
    localLayoutParams2.addRule(3, localLinearLayout1.getId());
    localRelativeLayout.addView(this.z, localLayoutParams2);
    ImageView localImageView = new ImageView(this.c);
    localImageView.setId(localImageView.hashCode());
    localImageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1002));
    int i = com.unionpay.mobile.android.utils.c.a(this.c, 15.0F);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(i, i);
    localLayoutParams3.addRule(11, -1);
    localLayoutParams3.addRule(15, -1);
    localLayoutParams3.rightMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    this.z.addView(localImageView, localLayoutParams3);
    this.v = new TextView(this.c);
    this.v.setTextSize(b.k);
    this.v.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    this.v.setSingleLine(true);
    this.v.setTextColor(-10066330);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams4.addRule(15, -1);
    localLayoutParams4.addRule(9, -1);
    localLayoutParams4.addRule(0, localImageView.getId());
    localLayoutParams4.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    localLayoutParams4.rightMargin = localLayoutParams4.leftMargin;
    this.z.addView(this.v, localLayoutParams4);
    if (!"instalment".equals("promotion"))
    {
      LinearLayout localLinearLayout2 = new LinearLayout(this.c);
      localLinearLayout2.setBackgroundColor(-3419943);
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-1, 1);
      localLayoutParams5.bottomMargin = com.unionpay.mobile.android.global.a.f;
      localLayoutParams5.addRule(3, this.z.getId());
      localRelativeLayout.addView(localLinearLayout2, localLayoutParams5);
    }
    a(f());
    a(this.s, 0);
  }

  private String a(int paramInt1, int paramInt2, String paramString)
  {
    Object localObject1 = f.b(this.o, paramInt1);
    if (localObject1 != null)
    {
      JSONObject localJSONObject = (JSONObject)localObject1;
      String str = f.a(localJSONObject, "type");
      JSONArray localJSONArray;
      if ("coupon".equals(str))
        localJSONArray = this.t;
      while (true)
      {
        Object localObject2 = f.b(localJSONArray, paramInt2);
        if (localObject2 == null)
          break;
        return f.a((JSONObject)localObject2, paramString);
        if ("point".equals(str))
          localJSONArray = this.u;
        else
          localJSONArray = f.c(localJSONObject, "options");
      }
    }
    return "";
  }

  private static JSONObject a(String paramString1, String paramString2, String paramString3)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("type", paramString1);
      localJSONObject.put("label", paramString2);
      localJSONObject.put("checked", paramString3);
      localJSONObject.put("ckb_style", "small");
      localJSONObject.put("required", "0");
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }

  private void a(int paramInt1, int paramInt2)
  {
    this.s = paramInt1;
    this.r = paramInt2;
    if (this.v != null)
      this.v.setText(a(paramInt1, paramInt2, "label"));
    String str1 = a(paramInt1, paramInt2, "rel_label");
    String str2 = a(paramInt1, paramInt2, "rel_value");
    String str3 = a(paramInt1, paramInt2, "rel_value_style");
    if ((a(str1)) && (a(str2)))
    {
      this.x.setVisibility(8);
      return;
    }
    if (com.unionpay.mobile.android.data.a.a(str3))
    {
      int i1 = Color.parseColor(str3);
      str3 = Integer.toString(i1, 16);
    }
    String str4 = "#ff" + str3;
    TextView localTextView = this.x;
    int i = Color.parseColor(str4);
    SpannableString localSpannableString = new SpannableString(str1 + str2);
    ForegroundColorSpan localForegroundColorSpan = new ForegroundColorSpan(i);
    int j = str1.length();
    int k;
    boolean bool;
    label256: int m;
    if (TextUtils.isEmpty(str2))
    {
      k = 0;
      localSpannableString.setSpan(localForegroundColorSpan, j, k + j, 33);
      localTextView.setText(localSpannableString);
      if (this.w == null)
        break label284;
      bool = this.w.b();
      m = 0;
      if (!bool)
        break label290;
    }
    while (true)
    {
      this.x.setVisibility(m);
      return;
      k = str2.length();
      break;
      label284: bool = true;
      break label256;
      label290: m = 8;
    }
  }

  public final String a()
  {
    String str = a(this.s, this.r, "value");
    if (str != null)
      str = str.replace("\"", "\\\"");
    if ((this.w != null) && (!this.w.b()))
      str = null;
    g.c("uppay", m() + " : " + str);
    return str;
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    this.q.b(this.a);
    this.q.b(paramOnClickListener);
  }

  public final void a(o.a parama)
  {
    if (this.w != null)
      this.w.a(parama);
  }

  public final void a(JSONArray paramJSONArray)
  {
    this.t = paramJSONArray;
    this.q.a(paramJSONArray);
  }

  public final void a(JSONArray paramJSONArray, String paramString)
  {
    this.u = paramJSONArray;
    this.q.a(paramJSONArray, paramString);
  }

  public final void a(boolean paramBoolean)
  {
    if (this.w != null)
      this.w.a(paramBoolean);
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      this.l.setVisibility(i);
      if (this.x != null)
      {
        if (!TextUtils.isEmpty(this.x.getText().toString()))
          break;
        this.x.setVisibility(8);
      }
      return;
    }
    this.x.setVisibility(i);
  }

  protected final boolean a(LinearLayout paramLinearLayout, String paramString)
  {
    if (a(paramString))
      return true;
    LinearLayout localLinearLayout = new LinearLayout(this.c);
    localLinearLayout.setBackgroundColor(-1);
    localLinearLayout.setOrientation(1);
    paramLinearLayout.addView(localLinearLayout, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
    JSONObject localJSONObject = a(f.a(this.m, "type"), paramString, f.a(this.m, "checked"));
    this.w = new o(this.c, localJSONObject);
    this.w.a();
    this.w.a(b.k);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
    localLayoutParams.gravity = 16;
    int i = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    localLayoutParams.rightMargin = i;
    localLayoutParams.leftMargin = i;
    localLinearLayout.addView(this.w, localLayoutParams);
    return true;
  }

  public final void b(View.OnClickListener paramOnClickListener)
  {
    this.q.e(paramOnClickListener);
  }

  public final boolean b()
  {
    return true;
  }

  protected final boolean b_()
  {
    this.x = new TextView(this.c);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    int i = com.unionpay.mobile.android.utils.c.a(this.c, 5.0F);
    localLayoutParams.bottomMargin = i;
    localLayoutParams.topMargin = i;
    this.x.setTextSize(b.k);
    addView(this.x, localLayoutParams);
    this.x.setVisibility(8);
    return true;
  }

  public final void c(View.OnClickListener paramOnClickListener)
  {
    this.q.c(paramOnClickListener);
  }

  public final boolean c()
  {
    return true;
  }

  public final boolean e()
  {
    String str = a(this.s, this.r, "available");
    return (TextUtils.isEmpty(str)) || (!"1".equals(str));
  }

  public final boolean f()
  {
    if (this.w != null)
      return this.w.b();
    return true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ai
 * JD-Core Version:    0.6.2
 */