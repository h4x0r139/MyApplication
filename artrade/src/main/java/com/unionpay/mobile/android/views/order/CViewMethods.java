package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.c;
import java.util.HashMap;

public class CViewMethods extends LinearLayout
{
  private static final Integer a = Integer.valueOf(-1);
  private static final Integer b = Integer.valueOf(-2);
  private static final int c = b.a;
  private Context d;
  private int e;
  private TextView f;
  private HashMap<Integer, String> g;
  private HashMap<Integer, Drawable> h;
  private String i;
  private a j;
  private Drawable k;

  public CViewMethods(Context paramContext)
  {
    this(paramContext, null);
  }

  public CViewMethods(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CViewMethods(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext);
    this.d = paramContext;
    this.e = l.a.intValue();
    setOrientation(1);
  }

  private void a(LinearLayout paramLinearLayout, int paramInt)
  {
    float f1 = b.k;
    RelativeLayout localRelativeLayout = new RelativeLayout(this.d);
    localRelativeLayout.setClickable(true);
    if (this.k != null)
      localRelativeLayout.setBackgroundDrawable(this.k.getConstantState().newDrawable());
    localRelativeLayout.setOnClickListener(new h(this, paramInt));
    paramLinearLayout.addView(localRelativeLayout, new LinearLayout.LayoutParams(a.intValue(), b.n));
    ImageView localImageView = new ImageView(this.d);
    localImageView.setId(localImageView.hashCode());
    if (this.h != null)
    {
      Drawable localDrawable = (Drawable)this.h.get(Integer.valueOf(paramInt));
      if (localDrawable != null)
        localImageView.setBackgroundDrawable(localDrawable);
    }
    int m = c.a(this.d, 15.0F);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(m, m);
    localLayoutParams1.addRule(15, -1);
    localLayoutParams1.addRule(11, -1);
    localLayoutParams1.rightMargin = c;
    localRelativeLayout.addView(localImageView, localLayoutParams1);
    TextView localTextView = new TextView(this.d);
    localTextView.setClickable(false);
    localTextView.setTextSize(f1);
    localTextView.setTextColor(-13421773);
    if (this.g != null)
    {
      String str = (String)this.g.get(Integer.valueOf(paramInt));
      if (str != null)
        localTextView.setText(str);
    }
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(a.intValue(), b.intValue());
    localLayoutParams2.addRule(15, -1);
    localLayoutParams2.addRule(9, -1);
    localLayoutParams2.addRule(0, localImageView.getId());
    localLayoutParams2.leftMargin = c;
    localRelativeLayout.addView(localTextView, localLayoutParams2);
  }

  public final CViewMethods a(int paramInt)
  {
    if (paramInt > 0)
      this.e = paramInt;
    return this;
  }

  public final CViewMethods a(Drawable paramDrawable)
  {
    this.k = paramDrawable;
    return this;
  }

  public final CViewMethods a(a parama)
  {
    this.j = parama;
    return this;
  }

  public final CViewMethods a(String paramString)
  {
    this.i = paramString;
    return this;
  }

  public final CViewMethods a(HashMap<Integer, String> paramHashMap)
  {
    this.g = paramHashMap;
    return this;
  }

  public final void a()
  {
    removeAllViews();
    if (this.e == l.a.intValue())
    {
      setVisibility(8);
      return;
    }
    this.f = new TextView(this.d);
    this.f.setTextColor(-13421773);
    this.f.setTextSize(b.k);
    if (this.i != null)
    {
      String str = this.i;
      if (this.f != null)
        this.f.setText(str);
    }
    this.f.setGravity(19);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(a.intValue(), b.intValue());
    localLayoutParams1.gravity = 19;
    localLayoutParams1.topMargin = c;
    localLayoutParams1.leftMargin = c.a(this.d, 10.0F);
    addView(this.f, localLayoutParams1);
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setBackgroundColor(-3419943);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, 1);
    localLayoutParams2.topMargin = c;
    addView(localLinearLayout1, localLayoutParams2);
    LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
    LinearLayout localLinearLayout2 = new LinearLayout(this.d);
    localLinearLayout2.setOrientation(1);
    localLinearLayout2.setBackgroundColor(-1);
    addView(localLinearLayout2, localLayoutParams3);
    if (l.b.intValue() == (l.b.intValue() & this.e))
      a(localLinearLayout2, l.b.intValue());
    if (l.c.intValue() == (l.c.intValue() & this.e))
      a(localLinearLayout2, l.c.intValue());
    if (l.d.intValue() == (l.d.intValue() & this.e))
    {
      LinearLayout localLinearLayout3 = new LinearLayout(this.d);
      localLinearLayout3.setBackgroundColor(-3419943);
      LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, 1);
      localLayoutParams4.leftMargin = c.a(this.d, 10.0F);
      localLinearLayout2.addView(localLinearLayout3, localLayoutParams4);
      a(localLinearLayout2, l.d.intValue());
    }
    if (l.e.intValue() == (l.e.intValue() & this.e))
    {
      LinearLayout localLinearLayout4 = new LinearLayout(this.d);
      localLinearLayout4.setBackgroundColor(-3419943);
      LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-1, 1);
      localLayoutParams5.leftMargin = c.a(this.d, 10.0F);
      localLinearLayout2.addView(localLinearLayout4, localLayoutParams5);
      a(this, l.e.intValue());
    }
    LinearLayout localLinearLayout5 = new LinearLayout(this.d);
    localLinearLayout5.setBackgroundColor(-3419943);
    addView(localLinearLayout5, new LinearLayout.LayoutParams(-1, 1));
  }

  public final CViewMethods b(HashMap<Integer, Drawable> paramHashMap)
  {
    this.h = paramHashMap;
    return this;
  }

  public static abstract interface a
  {
    public abstract void b(int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.CViewMethods
 * JD-Core Version:    0.6.2
 */