package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class c extends BaseAdapter
{
  private Drawable a;
  private Drawable b;
  private Drawable c;
  private Context d;
  private String e = null;
  private String f = null;
  private String g = null;
  private boolean h = false;
  private int i = 1;
  private int j = 0;
  private List<Map<String, Object>> k;
  private ArrayList<View.OnClickListener> l = new ArrayList();
  private View.OnClickListener m = new d(this);

  public c(Context paramContext, List<Map<String, Object>> paramList, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2)
  {
    this.d = paramContext;
    this.k = paramList;
    this.e = paramString1;
    this.f = paramString2;
    this.g = paramString3;
    this.i = paramInt1;
    this.j = paramInt2;
    this.a = com.unionpay.mobile.android.resource.c.a(this.d).a(1015);
    this.b = com.unionpay.mobile.android.resource.c.a(this.d).a(1016);
    this.c = com.unionpay.mobile.android.resource.c.a(this.d).a(1002);
  }

  private boolean d()
  {
    return (this.e != null) && (!TextUtils.isEmpty(this.e));
  }

  private boolean d(int paramInt)
  {
    int n = paramInt - c();
    if (n == this.k.size())
      return true;
    Object localObject = ((Map)this.k.get(n)).get("available");
    if ((localObject != null) && (Boolean.FALSE == (Boolean)localObject));
    for (boolean bool = false; ; bool = true)
      return bool;
  }

  private boolean e()
  {
    return (this.f != null) && (!TextUtils.isEmpty(this.f));
  }

  public final void a()
  {
    if (!this.h);
    for (boolean bool = true; ; bool = false)
    {
      this.h = bool;
      return;
    }
  }

  public final void a(int paramInt)
  {
    this.i = paramInt;
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    this.l.add(paramOnClickListener);
  }

  public final void a(String paramString)
  {
    this.e = paramString;
  }

  public final Spanned b(int paramInt)
  {
    int n = paramInt - c();
    if (n == this.k.size())
      return null;
    Map localMap = (Map)this.k.get(n);
    String str1 = (String)localMap.get("text1");
    String str2 = (String)localMap.get("text2");
    return Html.fromHtml(str1 + " " + str2);
  }

  public final void b(String paramString)
  {
    this.g = paramString;
  }

  public final boolean b()
  {
    return this.h;
  }

  public final int c()
  {
    if (d())
      return 1;
    return 0;
  }

  public final boolean c(int paramInt)
  {
    int n = paramInt - c();
    if (n == this.k.size())
      return true;
    Object localObject = ((Map)this.k.get(n)).get("editable");
    if ((localObject != null) && (Boolean.FALSE == (Boolean)localObject));
    for (boolean bool = false; ; bool = true)
      return bool;
  }

  public final int getCount()
  {
    if (this.k == null)
      return 0;
    int n = this.k.size() + c();
    boolean bool = e();
    int i1 = 0;
    if (bool)
      i1 = 1;
    return i1 + n;
  }

  public final Object getItem(int paramInt)
  {
    Object localObject = null;
    if (paramInt != 0)
    {
      List localList = this.k;
      localObject = null;
      if (localList != null)
      {
        int n = this.k.size();
        localObject = null;
        if (paramInt < n)
          localObject = this.k.get(paramInt - c());
      }
    }
    return localObject;
  }

  public final long getItemId(int paramInt)
  {
    return paramInt;
  }

  public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setOrientation(1);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.d);
    int n = b.g;
    localRelativeLayout.setPadding(n, n, n, n);
    localLinearLayout1.addView(localRelativeLayout);
    LinearLayout localLinearLayout2 = new LinearLayout(this.d);
    localLinearLayout2.setBackgroundColor(-3419943);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, 1);
    int i1;
    if (paramInt - c() == this.k.size())
    {
      i1 = 1;
      if ((!d()) || (paramInt != 0))
        break label344;
      TextView localTextView3 = new TextView(this.d);
      localTextView3.setText(this.e);
      localTextView3.setTextSize(b.k);
      localTextView3.setTextColor(-13421773);
      RelativeLayout.LayoutParams localLayoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams7.addRule(9, -1);
      localLayoutParams7.addRule(15, -1);
      localLayoutParams7.leftMargin = b.f;
      localRelativeLayout.addView(localTextView3, localLayoutParams7);
      if ((this.g == null) || (TextUtils.isEmpty(this.g)))
        break label338;
    }
    label338: for (int i6 = 1; ; i6 = 0)
    {
      if (i6 != 0)
      {
        TextView localTextView4 = new TextView(this.d);
        localTextView4.setText(this.g);
        localTextView4.setTextSize(b.k);
        localTextView4.setTextColor(-13421773);
        localTextView4.setOnClickListener(this.m);
        RelativeLayout.LayoutParams localLayoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams8.addRule(11, -1);
        localLayoutParams8.addRule(15, -1);
        localLayoutParams8.rightMargin = b.f;
        localRelativeLayout.addView(localTextView4, localLayoutParams8);
      }
      localLinearLayout1.addView(localLinearLayout2, localLayoutParams);
      return localLinearLayout1;
      i1 = 0;
      break;
    }
    label344: if ((e()) && (i1 != 0))
    {
      TextView localTextView2 = new TextView(this.d);
      localTextView2.setText(this.f);
      localTextView2.setTextSize(b.k);
      localTextView2.setTextColor(-10066330);
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams5.addRule(15, -1);
      localLayoutParams5.addRule(9, -1);
      localRelativeLayout.addView(localTextView2, localLayoutParams5);
      ImageView localImageView2 = new ImageView(this.d);
      localImageView2.setBackgroundDrawable(this.c);
      int i5 = com.unionpay.mobile.android.utils.c.a(this.d, 20.0F);
      RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(i5, i5);
      localLayoutParams6.addRule(15, -1);
      localLayoutParams6.addRule(11, -1);
      localRelativeLayout.addView(localImageView2, localLayoutParams6);
      return localLinearLayout1;
    }
    ImageView localImageView1 = new ImageView(this.d);
    localImageView1.setVisibility(4);
    localImageView1.setId(localImageView1.hashCode());
    TextView localTextView1 = new TextView(this.d);
    localTextView1.setSingleLine(true);
    localTextView1.setEllipsize(TextUtils.TruncateAt.END);
    localTextView1.setText(b(paramInt));
    localTextView1.setTextSize(b.k);
    localTextView1.setTextColor(-10066330);
    int i2 = com.unionpay.mobile.android.utils.c.a(this.d, 20.0F);
    Drawable localDrawable2;
    if (this.j == 0)
      if (this.h)
      {
        localDrawable2 = this.b;
        if ((this.h) || (this.i != paramInt) || (localDrawable2 == null))
          break label770;
        localImageView1.setVisibility(0);
        localImageView1.setBackgroundDrawable(localDrawable2);
        label650: RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(i2, i2);
        localLayoutParams3.addRule(15, -1);
        localLayoutParams3.addRule(9, -1);
        localRelativeLayout.addView(localImageView1, localLayoutParams3);
        RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        localLayoutParams4.addRule(15, -1);
        localLayoutParams4.addRule(1, localImageView1.hashCode());
        localLayoutParams4.leftMargin = b.g;
        localRelativeLayout.addView(localTextView1, localLayoutParams4);
      }
    label737: int i3;
    label770: label827: int i4;
    while (true)
      if ((!e()) || (i1 == 0))
      {
        localLinearLayout1.addView(localLinearLayout2, localLayoutParams);
        return localLinearLayout1;
        localDrawable2 = this.a;
        break;
        if ((!c(paramInt)) || (!this.h) || (localDrawable2 == null))
          break label650;
        localImageView1.setVisibility(0);
        localImageView1.setBackgroundDrawable(localDrawable2);
        break label650;
        if (this.j == 1)
        {
          if (this.i != paramInt)
            break label994;
          i3 = 1008;
          i4 = com.unionpay.mobile.android.utils.c.a(this.d, 20.0F);
          Drawable localDrawable1 = com.unionpay.mobile.android.resource.c.a(this.d).a(i3, i2, i2);
          if (d(paramInt))
            localImageView1.setVisibility(0);
          localImageView1.setBackgroundDrawable(localDrawable1);
          if (!this.h)
            break label1002;
        }
      }
    label994: label1002: 
    while (true)
    {
      RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(i4, i4);
      localLayoutParams1.addRule(15, -1);
      localLayoutParams1.addRule(11, -1);
      localRelativeLayout.addView(localImageView1, localLayoutParams1);
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
      localLayoutParams2.addRule(15, -1);
      localLayoutParams2.addRule(9, -1);
      localLayoutParams2.addRule(0, localImageView1.hashCode());
      localLayoutParams2.rightMargin = b.g;
      localRelativeLayout.addView(localTextView1, localLayoutParams2);
      localLayoutParams.leftMargin = n;
      break label737;
      break;
      i3 = 1007;
      break label827;
    }
  }

  public final boolean isEnabled(int paramInt)
  {
    if (((d()) && (paramInt == 0)) || (!d(paramInt)))
      return false;
    return super.isEnabled(paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.c
 * JD-Core Version:    0.6.2
 */