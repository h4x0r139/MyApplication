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
import com.unionpay.mobile.android.upwidget.e;
import com.unionpay.mobile.android.upwidget.o;
import com.unionpay.mobile.android.upwidget.o.a;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class p extends y
{
  private final View.OnClickListener a = new q(this);
  private final AdapterView.OnItemClickListener b = new r(this);
  private JSONArray n = f.c(this.m, "options");
  private List<Map<String, Object>> o;
  private PopupWindow p;
  private com.unionpay.mobile.android.upwidget.c q;
  private e r;
  private int s = 1;
  private TextView t;
  private o u;
  private TextView v;
  private String w;
  private RelativeLayout x;

  public p(Context paramContext, JSONObject paramJSONObject)
  {
    super(paramContext, paramJSONObject);
    this.w = f.a(paramJSONObject, "label");
    if (a(this.w))
      this.w = com.unionpay.mobile.android.languages.c.by.bg;
    JSONArray localJSONArray = this.n;
    Object localObject = null;
    if (localJSONArray != null)
    {
      int j = localJSONArray.length();
      localObject = null;
      if (j > 0)
      {
        ArrayList localArrayList = new ArrayList(localJSONArray.length());
        for (int k = 0; k < localJSONArray.length(); k++)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("text1", a(k, "label"));
          localHashMap.put("text2", "");
          localHashMap.put("editable", Boolean.FALSE);
          localArrayList.add(localHashMap);
        }
        localObject = localArrayList;
      }
    }
    this.o = localObject;
    this.q = new com.unionpay.mobile.android.upwidget.c(paramContext, this.o, this.w, "", "", this.s, 0);
    this.r = new e(this.c, this.q);
    this.r.a(this.b);
    this.r.a(this.a);
    RelativeLayout localRelativeLayout = this.l;
    Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.c).a(2014);
    LinearLayout localLinearLayout1 = new LinearLayout(this.c);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    localLinearLayout1.setBackgroundColor(-3419943);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, 1);
    String str = f.a(this.m, "type");
    if ("instalment".equals(str))
      localLayoutParams1.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    localRelativeLayout.addView(localLinearLayout1, localLayoutParams1);
    this.x = new RelativeLayout(this.c);
    this.x.setId(this.x.hashCode());
    this.x.setBackgroundDrawable(localDrawable);
    this.x.setOnClickListener(new s(this));
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
    localLayoutParams2.addRule(15, -1);
    localLayoutParams2.addRule(3, localLinearLayout1.getId());
    localRelativeLayout.addView(this.x, localLayoutParams2);
    ImageView localImageView = new ImageView(this.c);
    localImageView.setId(localImageView.hashCode());
    localImageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1002));
    int i = com.unionpay.mobile.android.utils.c.a(this.c, 15.0F);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(i, i);
    localLayoutParams3.addRule(11, -1);
    localLayoutParams3.addRule(15, -1);
    localLayoutParams3.rightMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    this.x.addView(localImageView, localLayoutParams3);
    this.t = new TextView(this.c);
    this.t.setTextSize(b.k);
    this.t.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    this.t.setSingleLine(true);
    this.t.setTextColor(-10066330);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams4.addRule(15, -1);
    localLayoutParams4.addRule(9, -1);
    localLayoutParams4.addRule(0, localImageView.getId());
    localLayoutParams4.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    localLayoutParams4.rightMargin = localLayoutParams4.leftMargin;
    this.x.addView(this.t, localLayoutParams4);
    if (!"instalment".equals(str))
    {
      LinearLayout localLinearLayout2 = new LinearLayout(this.c);
      localLinearLayout2.setBackgroundColor(-3419943);
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-1, 1);
      localLayoutParams5.bottomMargin = com.unionpay.mobile.android.global.a.f;
      localLayoutParams5.addRule(3, this.x.getId());
      localRelativeLayout.addView(localLinearLayout2, localLayoutParams5);
    }
    a(f());
    a(1);
  }

  private String a(int paramInt, String paramString)
  {
    Object localObject = f.b(this.n, paramInt);
    if (localObject != null)
      return f.a((JSONObject)localObject, paramString);
    return "";
  }

  private JSONObject a(String paramString1, String paramString2, String paramString3)
  {
    JSONObject localJSONObject1 = new JSONObject();
    try
    {
      localJSONObject1.put("type", paramString1);
      localJSONObject1.put("label", paramString2);
      localJSONObject1.put("checked", paramString3);
      localJSONObject1.put("ckb_style", "small");
      localJSONObject1.put("required", "0");
      if ("instalment".equals(paramString1))
      {
        JSONObject localJSONObject2 = f.b(this.m, "url");
        if (localJSONObject2 != null)
        {
          localJSONObject1.put("href_label", f.a(localJSONObject2, "label"));
          localJSONObject1.put("href_url", f.a(localJSONObject2, "href"));
        }
      }
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject1;
  }

  private void a(int paramInt)
  {
    this.s = paramInt;
    int i = paramInt - this.q.c();
    this.q.a(this.s);
    if (this.t != null)
      this.t.setText(a(i, "label"));
    String str1 = a(i, "rel_label");
    String str2 = a(i, "rel_value");
    String str3 = a(i, "rel_value_style");
    if ((a(str1)) && (a(str2)))
      return;
    if (com.unionpay.mobile.android.data.a.a(str3))
    {
      int i2 = Color.parseColor(str3);
      str3 = Integer.toString(i2, 16);
    }
    String str4 = "#ff" + str3;
    TextView localTextView = this.v;
    int j = Color.parseColor(str4);
    SpannableString localSpannableString = new SpannableString(str1 + str2);
    ForegroundColorSpan localForegroundColorSpan = new ForegroundColorSpan(j);
    int k = str1.length();
    int m;
    boolean bool;
    label259: int i1;
    if (TextUtils.isEmpty(str2))
    {
      m = 0;
      localSpannableString.setSpan(localForegroundColorSpan, k, m + k, 33);
      localTextView.setText(localSpannableString);
      if (this.u == null)
        break label287;
      bool = this.u.b();
      i1 = 0;
      if (!bool)
        break label293;
    }
    while (true)
    {
      this.v.setVisibility(i1);
      return;
      m = str2.length();
      break;
      label287: bool = true;
      break label259;
      label293: i1 = 8;
    }
  }

  public final String a()
  {
    String str = a(this.s - this.q.c(), "value");
    if ((this.u != null) && (!this.u.b()))
      str = null;
    g.c("uppay", m() + " : " + str);
    return str;
  }

  public final void a(o.a parama)
  {
    this.u.a(parama);
  }

  public final void a(boolean paramBoolean)
  {
    this.u.a(paramBoolean);
    if (paramBoolean);
    for (int i = 0; ; i = 8)
    {
      this.l.setVisibility(i);
      if (this.v != null)
      {
        if (!TextUtils.isEmpty(this.v.getText().toString()))
          break;
        this.v.setVisibility(8);
      }
      return;
    }
    this.v.setVisibility(i);
  }

  protected final boolean a(LinearLayout paramLinearLayout, String paramString)
  {
    if (a(paramString))
      return true;
    LinearLayout localLinearLayout1 = new LinearLayout(this.c);
    localLinearLayout1.setBackgroundColor(-1);
    localLinearLayout1.setOrientation(1);
    paramLinearLayout.addView(localLinearLayout1, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n));
    String str = f.a(this.m, "type");
    if ("instalment".equals(str))
    {
      LinearLayout localLinearLayout2 = new LinearLayout(this.c);
      localLinearLayout2.setId(localLinearLayout2.hashCode());
      localLinearLayout2.setBackgroundColor(-3419943);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, 1);
      localLayoutParams1.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
      localLinearLayout1.addView(localLinearLayout2, localLayoutParams1);
    }
    JSONObject localJSONObject = a(str, paramString, f.a(this.m, "checked"));
    this.u = new o(this.c, localJSONObject);
    this.u.a();
    this.u.a(b.k);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
    localLayoutParams2.gravity = 16;
    int i = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    localLayoutParams2.rightMargin = i;
    localLayoutParams2.leftMargin = i;
    localLinearLayout1.addView(this.u, localLayoutParams2);
    return true;
  }

  public final boolean b()
  {
    return true;
  }

  protected final boolean b_()
  {
    this.v = new TextView(this.c);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    int i = com.unionpay.mobile.android.utils.c.a(this.c, 5.0F);
    localLayoutParams.bottomMargin = i;
    localLayoutParams.topMargin = i;
    this.v.setTextSize(b.k);
    addView(this.v, localLayoutParams);
    this.v.setVisibility(8);
    return true;
  }

  public final boolean c()
  {
    return true;
  }

  public final boolean e()
  {
    String str = a(this.s - this.q.c(), "available");
    return (TextUtils.isEmpty(str)) || (!"1".equals(str));
  }

  public final boolean f()
  {
    if (this.u != null)
      return this.u.b();
    return true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.p
 * JD-Core Version:    0.6.2
 */