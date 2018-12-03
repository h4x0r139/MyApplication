package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.e;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ar;
import com.unionpay.mobile.android.widgets.ax;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bc extends b
{
  private TextView p = null;
  private View.OnClickListener q = new bd(this);
  private a r;

  public bc(Context paramContext)
  {
    super(paramContext);
    e();
    this.a.al = null;
    if (this.a.aL)
      this.e.b("bingopromotion", "");
  }

  private void r()
  {
    String str = this.a.aG;
    g.c("functionEx", str);
    PreferenceUtils.b(this.d, str);
    this.a.D.f = "success";
    k();
  }

  public final void a(JSONObject paramJSONObject)
  {
    JSONObject localJSONObject = f.b(paramJSONObject, "luck_draw");
    if (localJSONObject != null)
    {
      this.r.setVisibility(0);
      this.r.a(localJSONObject);
    }
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    ax localax = new ax(getContext(), this.a.v, this);
    localax.a();
    localLayoutParams.addRule(13, -1);
    this.j.addView(localax, localLayoutParams);
  }

  public final void b(int paramInt)
  {
  }

  protected final void c()
  {
    g();
    this.l.invalidate();
    this.n.setBackgroundColor(-1052684);
    RelativeLayout localRelativeLayout1 = new RelativeLayout(this.d);
    localRelativeLayout1.setBackgroundColor(-1052684);
    localRelativeLayout1.setId(localRelativeLayout1.hashCode());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams1.addRule(10, -1);
    localLayoutParams1.topMargin = a.d;
    this.l.addView(localRelativeLayout1, localLayoutParams1);
    RelativeLayout localRelativeLayout2 = new RelativeLayout(this.d);
    localRelativeLayout2.setId(localRelativeLayout2.hashCode());
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams2.topMargin = a.d;
    localRelativeLayout1.addView(localRelativeLayout2, localLayoutParams2);
    int i = a.n;
    Drawable localDrawable = this.c.a(2008);
    this.p = new TextView(this.d);
    this.p.setId(this.p.hashCode());
    this.p.setText(com.unionpay.mobile.android.languages.c.by.E);
    this.p.setTextSize(com.unionpay.mobile.android.global.b.i);
    this.p.setTextColor(p());
    this.p.setGravity(17);
    this.p.setOnClickListener(this.q);
    this.p.setBackgroundDrawable(localDrawable);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, i);
    int j = com.unionpay.mobile.android.utils.c.a(this.d, 12.0F);
    localLayoutParams3.rightMargin = j;
    localLayoutParams3.leftMargin = j;
    localLayoutParams3.addRule(9, -1);
    localLayoutParams3.addRule(15, -1);
    localRelativeLayout2.addView(this.p, localLayoutParams3);
    this.r = new a(this.d);
    this.r.setVisibility(8);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams4.addRule(3, localRelativeLayout2.getId());
    localLayoutParams4.topMargin = com.unionpay.mobile.android.utils.c.a(this.d, 20.0F);
    localRelativeLayout1.addView(this.r, localLayoutParams4);
  }

  protected final void f()
  {
    if (this.a.C != null)
    {
      LinearLayout localLinearLayout1 = new LinearLayout(this.d);
      localLinearLayout1.setOrientation(1);
      int i = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
      if (!TextUtils.isEmpty(this.a.w))
      {
        TextView localTextView1 = new TextView(this.d);
        localTextView1.setText(this.a.w);
        localTextView1.setTextSize(24.0F);
        localTextView1.setTextColor(-15365480);
        localTextView1.setGravity(1);
        localTextView1.setPadding(0, a.d, 0, 0);
        localTextView1.getPaint().setFakeBoldText(true);
        localLinearLayout1.addView(localTextView1, new LinearLayout.LayoutParams(-1, -2));
        LinearLayout localLinearLayout2 = new LinearLayout(this.d);
        localLinearLayout2.setOrientation(0);
        localLinearLayout2.setBackgroundColor(-6958338);
        LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, 1);
        int j = a.d;
        localLayoutParams1.bottomMargin = j;
        localLayoutParams1.topMargin = j;
        localLinearLayout1.addView(localLinearLayout2, localLayoutParams1);
        this.k.addView(localLinearLayout1);
      }
      JSONArray localJSONArray = this.a.C;
      if (localJSONArray != null)
        if (localJSONArray.length() < 2)
          break label408;
      label408: for (int k = 2; ; k = localJSONArray.length())
      {
        LinearLayout localLinearLayout3 = bg.a(this.d, localJSONArray, 0, k);
        localLinearLayout3.setBackgroundColor(0);
        LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams3.rightMargin = i;
        localLayoutParams3.leftMargin = i;
        localLinearLayout1.addView(localLinearLayout3, localLayoutParams3);
        int m = localJSONArray.length();
        LinearLayout localLinearLayout4 = bg.a(this.d, localJSONArray, k, m);
        localLinearLayout4.setBackgroundColor(0);
        LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams4.rightMargin = i;
        localLayoutParams4.leftMargin = i;
        this.k.addView(localLinearLayout4, localLayoutParams4);
        ar localar = new ar(this.d);
        localar.setId(localar.hashCode());
        this.k.setOnClickListener(new be(this));
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams2.bottomMargin = 0;
        this.k.addView(localar, localLayoutParams2);
        return;
      }
    }
    int n = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
    LinearLayout localLinearLayout5 = new LinearLayout(this.d);
    localLinearLayout5.setPadding(n, n, n, n);
    localLinearLayout5.setOrientation(1);
    localLinearLayout5.setBackgroundColor(-1);
    this.k.addView(localLinearLayout5);
    LinearLayout localLinearLayout6 = new LinearLayout(this.d);
    localLinearLayout6.setOrientation(0);
    localLinearLayout5.addView(localLinearLayout6);
    int i1 = com.unionpay.mobile.android.utils.c.a(this.d, 25.0F);
    ImageView localImageView = new ImageView(this.d);
    localImageView.setBackgroundDrawable(this.c.a(1035));
    localLinearLayout6.addView(localImageView, new LinearLayout.LayoutParams(i1, i1));
    LinearLayout localLinearLayout7 = new LinearLayout(this.d);
    localLinearLayout7.setOrientation(1);
    LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams5.leftMargin = n;
    localLinearLayout6.addView(localLinearLayout7, localLayoutParams5);
    String str1 = this.a.aH;
    boolean bool = e.b(str1);
    SpannableString localSpannableString = null;
    int i3;
    if (!bool)
    {
      i3 = str1.indexOf('<');
      if (-1 == i3)
        break label1194;
    }
    label1194: for (String str2 = str1.substring(0, i3); ; str2 = null)
    {
      int i4 = 1 + str1.indexOf('>');
      if (-1 != i4);
      for (String str3 = str1.substring(i4); ; str3 = null)
      {
        int i5 = i3 + 1;
        int i6 = i4 - 1;
        String str5;
        String str4;
        if ((-1 != i3) && (i5 < i6) && (i4 != -1))
        {
          String str6 = str1.substring(i5, i6);
          if (!e.b(str6))
          {
            String[] arrayOfString = str6.split("#");
            if ((arrayOfString != null) && (arrayOfString.length == 2))
            {
              str5 = arrayOfString[0];
              str4 = arrayOfString[1];
            }
          }
        }
        while (true)
        {
          localSpannableString = e.a(str2, str5, str4, str3);
          if (localSpannableString != null)
          {
            TextView localTextView2 = new TextView(this.d);
            localTextView2.setTextSize(20.0F);
            localTextView2.setText(localSpannableString);
            localLinearLayout7.addView(localTextView2);
          }
          if (b(this.a.aI))
          {
            TextView localTextView3 = new TextView(this.d);
            localTextView3.setTextSize(com.unionpay.mobile.android.global.b.l);
            localTextView3.setText(this.a.aI);
            localTextView3.setTextColor(-10066330);
            localLinearLayout7.addView(localTextView3);
          }
          if (b(this.a.aJ))
          {
            TextView localTextView4 = new TextView(this.d);
            localTextView4.setTextSize(com.unionpay.mobile.android.global.b.l);
            localTextView4.setTextColor(-10066330);
            localTextView4.setText(this.a.aJ);
            localLinearLayout7.addView(localTextView4);
          }
          int i2 = com.unionpay.mobile.android.utils.c.a(this.d, 5.0F);
          LinearLayout localLinearLayout8 = new LinearLayout(this.d);
          localLinearLayout8.setOrientation(1);
          localLinearLayout8.setBackgroundColor(-1052684);
          localLinearLayout8.setPadding(i2, i2, i2, i2);
          LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-1, -2);
          localLayoutParams6.topMargin = com.unionpay.mobile.android.utils.c.a(this.d, 10.0F);
          localLinearLayout5.addView(localLinearLayout8, localLayoutParams6);
          localLinearLayout8.setVisibility(8);
          if (("0".equals(this.a.aM)) && (b(this.a.aN)))
          {
            TextView localTextView5 = new TextView(this.d);
            localTextView5.setTextSize(com.unionpay.mobile.android.global.b.l);
            localTextView5.setText(this.a.aN);
            localLinearLayout8.addView(localTextView5);
            localLinearLayout8.setVisibility(0);
          }
          Drawable localDrawable = this.c.a(1026);
          LinearLayout localLinearLayout9 = new LinearLayout(this.d);
          if (localDrawable != null)
            localLinearLayout9.setBackgroundDrawable(localDrawable);
          LinearLayout.LayoutParams localLayoutParams7 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.utils.c.a(this.d, 2.0F));
          this.k.addView(localLinearLayout9, localLayoutParams7);
          return;
          str4 = null;
          str5 = null;
        }
      }
    }
  }

  public final void l()
  {
    r();
  }

  public final class a extends LinearLayout
  {
    private Context b;

    public a(Context arg2)
    {
      super();
      this.b = localContext;
      setOrientation(1);
    }

    private void a(JSONArray paramJSONArray)
    {
      if ((paramJSONArray == null) || (paramJSONArray.length() <= 0))
        return;
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
      LinearLayout localLinearLayout1 = new LinearLayout(bc.this.d);
      localLinearLayout1.setOrientation(1);
      localLinearLayout1.setBackgroundColor(-1);
      addView(localLinearLayout1, localLayoutParams1);
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.global.b.n);
      int i = 0;
      while (i < paramJSONArray.length())
        try
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          String str1 = f.a(localJSONObject, "label");
          String str2 = f.a(localJSONObject, "url");
          LinearLayout localLinearLayout2 = new LinearLayout(bc.this.d);
          localLinearLayout2.setBackgroundColor(-3419943);
          LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, 1);
          if (i != 0)
            localLayoutParams3.leftMargin = com.unionpay.mobile.android.utils.c.a(bc.this.d, 12.0F);
          localLinearLayout1.addView(localLinearLayout2, localLayoutParams3);
          Context localContext = this.b;
          RelativeLayout localRelativeLayout = new RelativeLayout(this.b);
          localRelativeLayout.setBackgroundDrawable(bc.this.c.a(2014));
          localRelativeLayout.setOnClickListener(new bf(this, str2));
          TextView localTextView = new TextView(localContext);
          localTextView.setText(str1);
          localTextView.setTextSize(com.unionpay.mobile.android.global.b.k);
          localTextView.setTextColor(-13421773);
          RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
          localLayoutParams4.addRule(15, -1);
          localLayoutParams4.addRule(9, -1);
          localLayoutParams4.leftMargin = com.unionpay.mobile.android.utils.c.a(localContext, 12.0F);
          localRelativeLayout.addView(localTextView, localLayoutParams4);
          int j = com.unionpay.mobile.android.utils.c.a(localContext, 20.0F);
          Drawable localDrawable = bc.this.c.a(1002);
          ImageView localImageView = new ImageView(localContext);
          localImageView.setBackgroundDrawable(localDrawable);
          RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(j, j);
          localLayoutParams5.addRule(15, -1);
          localLayoutParams5.addRule(11, -1);
          localLayoutParams5.rightMargin = com.unionpay.mobile.android.utils.c.a(localContext, 12.0F);
          localRelativeLayout.addView(localImageView, localLayoutParams5);
          localLinearLayout1.addView(localRelativeLayout, localLayoutParams2);
          if (i == -1 + paramJSONArray.length())
          {
            LinearLayout localLinearLayout3 = new LinearLayout(bc.this.d);
            localLinearLayout3.setBackgroundColor(-3419943);
            localLinearLayout1.addView(localLinearLayout3, new LinearLayout.LayoutParams(-1, 1));
          }
          i++;
        }
        catch (JSONException localJSONException)
        {
          while (true)
            localJSONException.printStackTrace();
        }
    }

    public final void a(JSONObject paramJSONObject)
    {
      removeAllViews();
      String str = f.a(paramJSONObject, "label");
      if (bc.b(str))
      {
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams.leftMargin = com.unionpay.mobile.android.utils.c.a(bc.this.d, 12.0F);
        localLayoutParams.bottomMargin = a.f;
        TextView localTextView = new TextView(this.b);
        localTextView.setText(str);
        localTextView.setTextSize(com.unionpay.mobile.android.global.b.k);
        localTextView.setTextColor(-25009);
        addView(localTextView, localLayoutParams);
      }
      a(f.c(paramJSONObject, "options"));
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.bc
 * JD-Core Version:    0.6.2
 */