package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;

public final class ax extends RelativeLayout
{
  private Context a = null;
  private TextView b = null;
  private ImageView c = null;
  private TextView d = null;
  private Drawable e = null;
  private ImageView f;
  private LinearLayout g;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private a k;

  public ax(Context paramContext, String paramString, Drawable paramDrawable, int paramInt, a parama)
  {
    super(paramContext);
    this.a = paramContext;
    this.k = parama;
    this.j = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
    this.e = paramDrawable;
    this.h = paramInt;
    b(paramString);
  }

  public ax(Context paramContext, String paramString, a parama)
  {
    this(paramContext, paramString, parama, (byte)0);
  }

  private ax(Context paramContext, String paramString, a parama, byte paramByte)
  {
    super(paramContext);
    this.a = paramContext;
    this.k = parama;
    this.j = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
    b(paramString);
  }

  private void b(String paramString)
  {
    this.i = a.k;
    setLayoutParams(new RelativeLayout.LayoutParams(-1, this.i));
    setBackgroundColor(a.L);
    com.unionpay.mobile.android.resource.c localc = com.unionpay.mobile.android.resource.c.a(this.a);
    this.g = new LinearLayout(this.a);
    this.g.setOnClickListener(new ay(this));
    this.g.setPadding(this.j, this.j, this.j, this.j);
    this.g.setGravity(16);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams1.addRule(9, -1);
    localLayoutParams1.addRule(15, -1);
    addView(this.g, localLayoutParams1);
    int m = com.unionpay.mobile.android.utils.c.a(this.a, 20.0F);
    int n = com.unionpay.mobile.android.utils.c.a(this.a, 11.0F);
    if (this.h != 0)
      n = this.h;
    ImageView localImageView = new ImageView(this.a);
    if (this.e != null)
      localImageView.setBackgroundDrawable(this.e);
    while (true)
    {
      RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(n, m);
      localLayoutParams2.addRule(15, -1);
      this.g.addView(localImageView, localLayoutParams2);
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(a.l, this.i);
      localLayoutParams3.addRule(13, -1);
      this.d = new TextView(this.a);
      this.d.setTextSize(20.0F);
      this.d.setTextColor(-1);
      this.d.setText(paramString);
      this.d.setGravity(17);
      this.d.setSingleLine(true);
      this.d.setEllipsize(TextUtils.TruncateAt.END);
      addView(this.d, localLayoutParams3);
      if (!TextUtils.isEmpty(null))
      {
        int i1 = a.e;
        RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(-2, this.i);
        localLayoutParams6.addRule(11, -1);
        localLayoutParams6.addRule(15, -1);
        localLayoutParams6.rightMargin = i1;
        this.b = new TextView(this.a);
        this.b.setTextSize(16.0F);
        this.b.setTextColor(-1);
        this.b.setText(null);
        this.b.setGravity(16);
        this.b.setId(this.b.hashCode());
        addView(this.b, localLayoutParams6);
        int i2 = a.m;
        RelativeLayout.LayoutParams localLayoutParams7 = new RelativeLayout.LayoutParams(a.H, i2);
        localLayoutParams7.addRule(0, this.b.getId());
        localLayoutParams7.addRule(15, -1);
        localLayoutParams7.rightMargin = i1;
        addView(new o(this.a, a.M, 1), localLayoutParams7);
      }
      RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(a.E, a.D);
      localLayoutParams4.addRule(11, -1);
      localLayoutParams4.addRule(15, -1);
      localLayoutParams4.rightMargin = this.j;
      Drawable localDrawable = localc.a(1031);
      this.f = new ImageView(this.a);
      this.f.setBackgroundDrawable(localDrawable);
      addView(this.f, localLayoutParams4);
      RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(a.D, a.D);
      localLayoutParams5.addRule(11, -1);
      localLayoutParams5.addRule(15, -1);
      localLayoutParams5.rightMargin = this.j;
      this.c = new ImageView(this.a);
      addView(this.c, localLayoutParams5);
      return;
      localImageView.setBackgroundDrawable(localc.a(1029));
    }
  }

  public final void a()
  {
    if (this.g != null)
      this.g.setVisibility(8);
  }

  public final void a(String paramString)
  {
    if (this.d != null)
      this.d.setText(paramString);
  }

  public static abstract interface a
  {
    public abstract void m();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ax
 * JD-Core Version:    0.6.2
 */