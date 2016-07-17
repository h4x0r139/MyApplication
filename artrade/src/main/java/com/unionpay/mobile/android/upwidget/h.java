package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.widgets.ac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class h extends LinearLayout
{
  private View.OnClickListener A = new l(this);
  private View.OnClickListener B = new m(this);
  private View.OnClickListener C = new n(this);
  private Context a;
  private JSONArray b;
  private int c;
  private int d;
  private boolean e = true;
  private a[] f;
  private ArrayList<Object> g;
  private LinearLayout h;
  private HorizontalScrollView i;
  private com.unionpay.mobile.android.widgets.k j = null;
  private ac k = null;
  private TextView l = null;
  private TextView m = null;
  private int n = 0;
  private int o = 0;
  private int p = -1;
  private int q;
  private int r;
  private ArrayList<AdapterView.OnItemClickListener> s = new ArrayList();
  private ArrayList<View.OnClickListener> t = new ArrayList();
  private ArrayList<View.OnClickListener> u = new ArrayList();
  private ArrayList<View.OnClickListener> v = new ArrayList();
  private ArrayList<View.OnClickListener> w = new ArrayList();
  private AdapterView.OnItemClickListener x = new i(this);
  private View.OnClickListener y = new j(this);
  private View.OnClickListener z = new k(this);

  public h(Context paramContext, JSONArray paramJSONArray, int paramInt)
  {
    super(paramContext);
    this.a = paramContext;
    this.b = paramJSONArray;
    this.o = paramInt;
    DisplayMetrics localDisplayMetrics1 = new DisplayMetrics();
    ((Activity)this.a).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics1);
    this.q = localDisplayMetrics1.widthPixels;
    DisplayMetrics localDisplayMetrics2 = new DisplayMetrics();
    ((Activity)this.a).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
    this.r = localDisplayMetrics2.heightPixels;
    if (this.b != null)
    {
      FrameLayout localFrameLayout = new FrameLayout(this.a);
      RelativeLayout localRelativeLayout = new RelativeLayout(this.a);
      localFrameLayout.addView(localRelativeLayout, new FrameLayout.LayoutParams(-1, -1));
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, 2 * (this.r / 3));
      localLayoutParams1.addRule(12, -1);
      LinearLayout localLinearLayout1 = new LinearLayout(this.a);
      localLinearLayout1.setOrientation(1);
      localLinearLayout1.setBackgroundColor(-1);
      localLinearLayout1.setId(localLinearLayout1.hashCode());
      localRelativeLayout.addView(localLinearLayout1, localLayoutParams1);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
      LinearLayout localLinearLayout2 = new LinearLayout(this.a);
      localLayoutParams2.addRule(10, -1);
      localLayoutParams2.addRule(2, localLinearLayout1.getId());
      localRelativeLayout.addView(localLinearLayout2, localLayoutParams2);
      localLinearLayout2.setOnClickListener(this.y);
      this.h = new LinearLayout(this.a);
      this.h.setBackgroundColor(-1);
      this.h.setOrientation(0);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, a.n);
      localLinearLayout1.addView(this.h, localLayoutParams3);
      LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.c.a(this.a, 1.0F));
      LinearLayout localLinearLayout3 = new LinearLayout(this.a);
      localLinearLayout3.setBackgroundColor(-3355444);
      localLinearLayout1.addView(localLinearLayout3, localLayoutParams4);
      this.i = new HorizontalScrollView(this.a);
      this.i.setBackgroundColor(-1052684);
      LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-2, -1);
      localLinearLayout1.addView(this.i, localLayoutParams5);
      int i1 = com.unionpay.mobile.android.utils.c.a(this.a, 40.0F);
      ImageView localImageView = new ImageView(this.a);
      localImageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.a).a(1034));
      localImageView.setOnClickListener(this.y);
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(i1, i1);
      localLayoutParams.gravity = 85;
      localLayoutParams.rightMargin = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
      localLayoutParams.bottomMargin = (2 * (this.r / 3) - i1 / 2);
      localFrameLayout.addView(localImageView, localLayoutParams);
      addView(localFrameLayout);
      a();
    }
  }

  private View a(LinearLayout paramLinearLayout, JSONObject paramJSONObject)
  {
    List localList = b(f.c(paramJSONObject, "options"));
    c localc = new c(this.a, localList, "", "", "", this.p, 1);
    this.g.add(localc);
    ListView localListView = new ListView(this.a);
    localListView.setDivider(null);
    localListView.setAdapter(localc);
    localListView.setOnItemClickListener(this.x);
    paramLinearLayout.addView(localListView, new LinearLayout.LayoutParams(this.q, -1));
    return localListView;
  }

  private static String a(JSONArray paramJSONArray, int paramInt, String paramString)
  {
    Object localObject = f.b(paramJSONArray, paramInt);
    if (localObject != null)
      return f.a((JSONObject)localObject, paramString);
    return "";
  }

  private void a()
  {
    int i1 = this.b.length();
    this.f = new a[i1];
    for (int i2 = 0; i2 < i1; i2++)
    {
      this.f[i2] = new a(0);
      if (this.f[i2].a == null)
        this.f[i2].a = new TextView(this.a);
      if (this.f[i2].b == null)
        this.f[i2].b = new LinearLayout(this.a);
      if (this.f[i2].c == null)
        this.f[i2].c = new ListView(this.a);
      if (this.f[i2].d == null)
        this.f[i2].d = "";
    }
    this.g = new ArrayList(this.b.length());
    LinearLayout localLinearLayout1 = new LinearLayout(this.a);
    localLinearLayout1.setOrientation(0);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -1);
    this.i.addView(localLinearLayout1, localLayoutParams1);
    int i3 = 0;
    if (i3 < this.b.length())
    {
      JSONObject localJSONObject = (JSONObject)f.b(this.b, i3);
      String str1 = f.a(localJSONObject, "action");
      String str2 = f.a(localJSONObject, "label");
      RelativeLayout localRelativeLayout = new RelativeLayout(this.a);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -1);
      localLayoutParams2.leftMargin = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
      this.h.addView(localRelativeLayout, localLayoutParams2);
      int i4 = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
      TextView localTextView = new TextView(this.a);
      localTextView.setText(str2);
      localTextView.setTextSize(b.k);
      localTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
      localTextView.setSingleLine(true);
      localTextView.setTextColor(-10066330);
      localTextView.setPadding(i4, 0, i4, 0);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams3.addRule(15, -1);
      localRelativeLayout.addView(localTextView, localLayoutParams3);
      int i5 = com.unionpay.mobile.android.utils.c.a(this.a, 2.0F);
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(i4 + (i4 + (int)localTextView.getPaint().measureText(str2)), i5);
      localLayoutParams4.addRule(12, -1);
      LinearLayout localLinearLayout2 = new LinearLayout(this.a);
      localLinearLayout2.setBackgroundColor(-16730965);
      if (this.o != i3)
        localLinearLayout2.setVisibility(8);
      localRelativeLayout.addView(localLinearLayout2, localLayoutParams4);
      localRelativeLayout.setTag(Integer.valueOf(i3));
      localRelativeLayout.setOnClickListener(this.C);
      this.f[i3].a = localTextView;
      this.f[i3].b = localLinearLayout2;
      this.f[i3].d = str1;
      label583: String str3;
      View localView;
      if (this.o == i3)
      {
        this.p = 0;
        str3 = f.a(localJSONObject, "type");
        if (!"coupon".equals(str3))
          break label660;
        this.d = i3;
        localView = b(localLinearLayout1, localJSONObject);
      }
      while (true)
      {
        this.f[i3].c = localView;
        this.f[i3].c.setVisibility(8);
        i3++;
        break;
        this.p = -1;
        break label583;
        label660: if ("point".equals(str3))
        {
          this.c = i3;
          localView = c(localLinearLayout1, localJSONObject);
        }
        else if ("upoint".equals(str3))
        {
          localView = c(localLinearLayout1, localJSONObject);
        }
        else
        {
          localView = a(localLinearLayout1, localJSONObject);
        }
      }
    }
    a(this.o);
  }

  private void a(int paramInt)
  {
    this.f[this.o].b.setVisibility(8);
    this.f[this.o].a.setTextColor(-16777216);
    this.f[this.o].c.setVisibility(8);
    this.f[paramInt].b.setVisibility(0);
    this.f[paramInt].a.setTextColor(-16730965);
    this.f[paramInt].c.setVisibility(0);
    this.o = paramInt;
  }

  private void a(LinearLayout paramLinearLayout, boolean paramBoolean, String paramString, JSONObject paramJSONObject, c paramc)
  {
    paramLinearLayout.removeAllViews();
    ListView localListView = new ListView(this.a);
    localListView.setDivider(null);
    localListView.setAdapter(paramc);
    localListView.setOnItemClickListener(this.x);
    this.g.add(paramc);
    paramLinearLayout.addView(localListView, new LinearLayout.LayoutParams(this.q, -2));
    if (paramc != null)
      ((LinearLayout.LayoutParams)paramLinearLayout.getLayoutParams()).gravity = 48;
    if (paramBoolean)
    {
      int i4 = a.p;
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(i4, i4);
      localLayoutParams3.bottomMargin = com.unionpay.mobile.android.utils.c.a(this.a, 12.0F);
      localLayoutParams3.gravity = 17;
      paramLinearLayout.addView(new ProgressBar(this.a), localLayoutParams3);
    }
    TextView localTextView1 = new TextView(this.a);
    if (!TextUtils.isEmpty(paramString))
    {
      localTextView1.setText(paramString);
      localTextView1.setTextSize(b.k);
      localTextView1.setTextColor(-13421773);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams2.gravity = 17;
      paramLinearLayout.addView(localTextView1, localLayoutParams2);
    }
    if (paramJSONObject != null)
    {
      TextView localTextView2 = new TextView(this.a);
      localTextView2.setText(f.a(paramJSONObject, "label"));
      localTextView2.setTextSize(b.i);
      localTextView2.setTextColor(d.a(b.b, b.c, b.c, b.d));
      localTextView2.setGravity(17);
      localTextView2.setEnabled(true);
      int i1 = a.n;
      localTextView2.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.a).a(2008));
      float f1 = localTextView1.getPaint().measureText(paramString);
      localTextView2.setOnClickListener(this.z);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams((int)f1, i1);
      int i2 = a.f;
      localLayoutParams1.bottomMargin = i2;
      localLayoutParams1.topMargin = i2;
      int i3 = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
      localLayoutParams1.rightMargin = i3;
      localLayoutParams1.leftMargin = i3;
      paramLinearLayout.addView(localTextView2, localLayoutParams1);
    }
  }

  private View b(LinearLayout paramLinearLayout, JSONObject paramJSONObject)
  {
    RelativeLayout localRelativeLayout = new RelativeLayout(this.a);
    ListView localListView = new ListView(this.a);
    localListView.setDivider(null);
    localListView.setAdapter(null);
    this.g.add(localListView);
    JSONArray localJSONArray = f.c(paramJSONObject, "rules");
    Object localObject1 = null;
    Object localObject2 = null;
    int i9;
    Object localObject4;
    String str;
    Object localObject5;
    if (localJSONArray != null)
    {
      int i8 = localJSONArray.length();
      localObject1 = null;
      localObject2 = null;
      if (i8 > 0)
      {
        i9 = 0;
        if (i9 < localJSONArray.length())
        {
          Object localObject3 = f.b(localJSONArray, i9);
          if (localObject3 == null)
            break label859;
          localObject4 = (JSONObject)localObject3;
          str = f.a((JSONObject)localObject4, "type");
          if ("coupon_code".equals(str))
          {
            Object localObject6 = localObject1;
            localObject5 = localObject4;
            localObject4 = localObject6;
          }
        }
      }
    }
    while (true)
    {
      i9++;
      localObject2 = localObject5;
      localObject1 = localObject4;
      break;
      if ("string".equals(str))
      {
        localObject5 = localObject2;
        continue;
        RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(this.q, -2);
        localLayoutParams1.addRule(10, -1);
        localRelativeLayout.addView(localListView, localLayoutParams1);
        int i1 = a.I - 4 * a.f;
        this.j = new com.unionpay.mobile.android.widgets.k(this.a, i1, localObject2);
        this.j.setId(this.j.hashCode());
        RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(this.q, -2);
        localLayoutParams2.addRule(10, -1);
        int i2 = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
        localLayoutParams2.topMargin = i2;
        localLayoutParams2.rightMargin = i2;
        localLayoutParams2.leftMargin = i2;
        localRelativeLayout.addView(this.j, localLayoutParams2);
        this.k = new ac(this.a, i1, localObject1);
        RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(this.q, -2);
        localLayoutParams3.addRule(3, this.j.getId());
        int i3 = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
        localLayoutParams3.topMargin = i3;
        localLayoutParams3.rightMargin = i3;
        localLayoutParams3.leftMargin = i3;
        localRelativeLayout.addView(this.k, localLayoutParams3);
        this.l = new TextView(this.a);
        this.l.setTextSize(b.k);
        this.l.setTextColor(-10066330);
        this.l.setVisibility(8);
        RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(this.q, -2);
        localLayoutParams4.addRule(3, this.j.getId());
        int i4 = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
        localLayoutParams4.topMargin = i4;
        localLayoutParams4.rightMargin = i4;
        localLayoutParams4.leftMargin = i4;
        localRelativeLayout.addView(this.l, localLayoutParams4);
        JSONObject localJSONObject = f.b(paramJSONObject, "use_button");
        LinearLayout localLinearLayout1 = new LinearLayout(this.a);
        localLinearLayout1.setOrientation(1);
        localLinearLayout1.setBackgroundColor(-1);
        LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.c.a(this.a, 1.0F));
        LinearLayout localLinearLayout2 = new LinearLayout(this.a);
        localLinearLayout2.setBackgroundColor(-3355444);
        localLinearLayout1.addView(localLinearLayout2, localLayoutParams5);
        this.m = new TextView(this.a);
        this.m.setText(f.a(localJSONObject, "label"));
        this.m.setTextSize(b.i);
        this.m.setTextColor(d.a(b.b, b.c, b.c, b.d));
        this.m.setGravity(17);
        this.m.setEnabled(false);
        int i5 = a.n;
        Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.a).a(2008);
        this.m.setBackgroundDrawable(localDrawable);
        this.m.setTag(Integer.valueOf(this.d));
        this.m.setOnClickListener(this.B);
        LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, i5);
        int i6 = a.f;
        localLayoutParams6.bottomMargin = i6;
        localLayoutParams6.topMargin = i6;
        int i7 = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
        localLayoutParams6.rightMargin = i7;
        localLayoutParams6.leftMargin = i7;
        localLinearLayout1.addView(this.m, localLayoutParams6);
        RelativeLayout.LayoutParams localLayoutParams7 = new RelativeLayout.LayoutParams(this.q, -2);
        localLayoutParams7.addRule(12, -1);
        localRelativeLayout.addView(localLinearLayout1, localLayoutParams7);
        paramLinearLayout.addView(localRelativeLayout, new LinearLayout.LayoutParams(this.q, -2));
        return localRelativeLayout;
      }
      else
      {
        label859: localObject4 = localObject1;
        localObject5 = localObject2;
      }
    }
  }

  private static List<Map<String, Object>> b(JSONArray paramJSONArray)
  {
    Object localObject = null;
    if (paramJSONArray != null)
    {
      int i1 = paramJSONArray.length();
      localObject = null;
      if (i1 > 0)
      {
        ArrayList localArrayList = new ArrayList(paramJSONArray.length());
        for (int i2 = 0; i2 < paramJSONArray.length(); i2++)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("text1", a(paramJSONArray, i2, "label"));
          localHashMap.put("text2", "");
          localHashMap.put("editable", Boolean.FALSE);
          String str = a(paramJSONArray, i2, "available");
          boolean bool = Boolean.TRUE.booleanValue();
          if ((!TextUtils.isEmpty(str)) && ("1".equals(str)))
            bool = Boolean.FALSE.booleanValue();
          localHashMap.put("available", Boolean.valueOf(bool));
          localArrayList.add(localHashMap);
        }
        localObject = localArrayList;
      }
    }
    return localObject;
  }

  private View c(LinearLayout paramLinearLayout, JSONObject paramJSONObject)
  {
    LinearLayout localLinearLayout = new LinearLayout(this.a);
    localLinearLayout.setOrientation(1);
    String str1 = f.a(paramJSONObject, "tip");
    String str2 = f.a(paramJSONObject, "empty_info");
    JSONObject localJSONObject = f.b(paramJSONObject, "button");
    if (localJSONObject != null)
    {
      a(localLinearLayout, false, str1, localJSONObject, null);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-2, -2);
      localLayoutParams3.gravity = 17;
      localLinearLayout.setGravity(17);
      paramLinearLayout.addView(localLinearLayout, localLayoutParams3);
      return localLinearLayout;
    }
    if ("upoint".equals(f.a(paramJSONObject, "type")))
    {
      if ((str2 != null) && (!TextUtils.isEmpty(str2)))
      {
        a(localLinearLayout, false, str2, null, null);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams2.gravity = 17;
        localLinearLayout.setGravity(17);
        paramLinearLayout.addView(localLinearLayout, localLayoutParams2);
        return localLinearLayout;
      }
      return a(paramLinearLayout, paramJSONObject);
    }
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams1.gravity = 17;
    localLinearLayout.setGravity(17);
    paramLinearLayout.addView(localLinearLayout, localLayoutParams1);
    return localLinearLayout;
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    this.t.add(paramOnClickListener);
  }

  public final void a(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.s.add(paramOnItemClickListener);
  }

  public final void a(JSONArray paramJSONArray)
  {
    Object localObject = f.b(paramJSONArray, 0);
    if (localObject != null)
    {
      JSONObject localJSONObject = (JSONObject)localObject;
      this.l.setText(f.a(localJSONObject, "label"));
      this.l.setVisibility(0);
      this.k.setVisibility(8);
    }
    this.m.setEnabled(true);
  }

  public final void a(JSONArray paramJSONArray, String paramString)
  {
    c localc2;
    if ((paramJSONArray != null) && (paramJSONArray.length() > 0))
    {
      List localList = b(paramJSONArray);
      localc2 = new c(this.a, localList, "", "", "", -1, 1);
      this.g.add(this.c, localc2);
    }
    for (c localc1 = localc2; ; localc1 = null)
    {
      a((LinearLayout)this.f[this.c].c, false, paramString, null, localc1);
      return;
    }
  }

  public final void b(View.OnClickListener paramOnClickListener)
  {
    this.u.add(paramOnClickListener);
  }

  public final void c(View.OnClickListener paramOnClickListener)
  {
    this.v.add(paramOnClickListener);
  }

  public final void d(View.OnClickListener paramOnClickListener)
  {
    this.w.add(paramOnClickListener);
  }

  public final void e(View.OnClickListener paramOnClickListener)
  {
    if (this.j != null)
    {
      this.j.a(paramOnClickListener);
      this.j.b(this.A);
    }
  }

  private final class a
  {
    TextView a;
    LinearLayout b;
    View c;
    String d;

    private a()
    {
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.h
 * JD-Core Version:    0.6.2
 */