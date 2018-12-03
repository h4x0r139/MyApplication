package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
import com.unionpay.mobile.android.upwidget.e;
import com.unionpay.mobile.android.utils.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a extends y
{
  private static List<String> u;
  private static List<String> v;
  private Spinner a = null;
  private int b = 1;
  private String n = com.unionpay.mobile.android.languages.c.by.be;
  private com.unionpay.mobile.android.upwidget.c o;
  private TextView p;
  private RelativeLayout q;
  private PopupWindow r;
  private e s;
  private List<Map<String, Object>> t;
  private final View.OnClickListener w = new b(this);
  private final AdapterView.OnItemClickListener x = new c(this);

  public a(Context paramContext, JSONObject paramJSONObject, JSONArray paramJSONArray)
  {
    super(paramContext, paramJSONObject);
    ArrayList localArrayList = new ArrayList(1);
    for (int i = 0; i < paramJSONArray.length(); i++)
      localArrayList.add((JSONArray)f.b(paramJSONArray, i));
    if (localArrayList.size() > 0)
    {
      u = new ArrayList(localArrayList.size());
      v = new ArrayList(localArrayList.size());
      for (int k = 0; k < localArrayList.size(); k++)
      {
        v.add(f.a((JSONArray)localArrayList.get(k), 0));
        u.add(f.a((JSONArray)localArrayList.get(k), 1));
      }
    }
    this.t = f();
    this.o = new com.unionpay.mobile.android.upwidget.c(paramContext, this.t, this.n, "", "", this.b, 0);
    this.s = new e(this.c, this.o);
    this.s.a(this.x);
    this.s.a(this.w);
    RelativeLayout localRelativeLayout = this.l;
    Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.c).a(2014);
    this.q = new RelativeLayout(this.c);
    this.q.setBackgroundDrawable(localDrawable);
    this.q.setOnClickListener(new d(this));
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.global.a.n);
    localLayoutParams1.addRule(15, -1);
    localRelativeLayout.addView(this.q, localLayoutParams1);
    ImageView localImageView = new ImageView(this.c);
    localImageView.setId(localImageView.hashCode());
    localImageView.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1002));
    int j = com.unionpay.mobile.android.utils.c.a(this.c, 15.0F);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(j, j);
    localLayoutParams2.addRule(11, -1);
    localLayoutParams2.addRule(15, -1);
    localLayoutParams2.rightMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    this.q.addView(localImageView, localLayoutParams2);
    TextView localTextView = new TextView(this.c);
    localTextView.setId(localTextView.hashCode());
    localTextView.setTextSize(com.unionpay.mobile.android.global.b.k);
    localTextView.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    localTextView.setSingleLine(true);
    localTextView.setEms(4);
    localTextView.setText(com.unionpay.mobile.android.languages.c.by.bd);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams3.addRule(15, -1);
    localLayoutParams3.addRule(9, -1);
    localLayoutParams3.leftMargin = com.unionpay.mobile.android.utils.c.a(this.c, 10.0F);
    this.q.addView(localTextView, localLayoutParams3);
    this.p = new TextView(this.c);
    this.p.setTextSize(com.unionpay.mobile.android.global.b.k);
    this.p.setEllipsize(TextUtils.TruncateAt.MIDDLE);
    this.p.setSingleLine(true);
    this.p.setTextColor(-10066330);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams4.addRule(15, -1);
    localLayoutParams4.addRule(1, localTextView.getId());
    localLayoutParams4.addRule(0, localImageView.getId());
    this.q.addView(this.p, localLayoutParams4);
    if (this.h)
    {
      this.p.setText(b(h()));
      localImageView.setVisibility(8);
      this.q.setClickable(false);
      return;
    }
    a(1);
  }

  private void a(int paramInt)
  {
    this.b = paramInt;
    int i = paramInt - this.o.c();
    this.o.a(this.b);
    if ((this.p != null) && (u != null))
      this.p.setText((CharSequence)u.get(i));
  }

  private static String b(String paramString)
  {
    Object localObject1 = "";
    int i = 0;
    if (i < v.size())
      if (!((String)v.get(i)).equals(paramString))
        break label59;
    label59: for (Object localObject2 = (String)u.get(i); ; localObject2 = localObject1)
    {
      i++;
      localObject1 = localObject2;
      break;
      return localObject1;
    }
  }

  private static List<Map<String, Object>> f()
  {
    List localList = u;
    Object localObject = null;
    if (localList != null)
    {
      int i = u.size();
      localObject = null;
      if (i > 0)
      {
        ArrayList localArrayList = new ArrayList(u.size());
        for (int j = 0; j < u.size(); j++)
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("text1", u.get(j));
          localHashMap.put("text2", "");
          localHashMap.put("editable", Boolean.FALSE);
          localArrayList.add(localHashMap);
        }
        localObject = localArrayList;
      }
    }
    return localObject;
  }

  public final String a()
  {
    String str = "";
    int i = this.b - this.o.c();
    if (this.h)
      str = h();
    while ((i < 0) || (i > u.size()))
      return str;
    return (String)v.get(i);
  }

  public final boolean b()
  {
    return true;
  }

  public final boolean c()
  {
    return true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.a
 * JD-Core Version:    0.6.2
 */