package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.d;
import java.lang.ref.WeakReference;

public final class m
{
  private static final float k = b.k;
  private static final float l = b.k;
  private static final float m = b.j;
  private Context a = null;
  private TextView b = null;
  private WeakReference<View.OnClickListener> c = null;
  private TextView d = null;
  private WeakReference<View.OnClickListener> e = null;
  private int f = 0;
  private com.unionpay.mobile.android.resource.c g = null;
  private Dialog h = null;
  private Drawable i = null;
  private WeakReference<DialogInterface.OnDismissListener> j = null;

  public m(Context paramContext)
  {
    this(paramContext, (byte)0);
  }

  private m(Context paramContext, byte paramByte)
  {
    this.a = paramContext;
    this.j = new WeakReference(null);
    this.g = com.unionpay.mobile.android.resource.c.a(paramContext);
    this.f = (a.I - 4 * a.b);
    int n = this.f / 2;
    this.i = this.g.a(1028, n, -1);
  }

  private RelativeLayout a(Context paramContext)
  {
    c();
    this.h = new n(this, paramContext);
    if ((this.j != null) && (this.j.get() != null))
      this.h.setOnDismissListener((DialogInterface.OnDismissListener)this.j.get());
    this.h.setCanceledOnTouchOutside(false);
    this.h.setOwnerActivity((Activity)paramContext);
    this.h.requestWindowFeature(1);
    this.h.getWindow().setBackgroundDrawable(this.g.a(4004));
    RelativeLayout localRelativeLayout1 = new RelativeLayout(this.a);
    this.h.getWindow().setBackgroundDrawable(this.g.a(4004));
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(this.f, -2);
    this.h.setContentView(localRelativeLayout1, localLayoutParams1);
    RelativeLayout localRelativeLayout2 = new RelativeLayout(this.a);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(13, -1);
    localRelativeLayout1.addView(localRelativeLayout2, localLayoutParams2);
    return localRelativeLayout1;
  }

  private boolean d()
  {
    return ((Activity)this.a).isFinishing();
  }

  public final void a(View.OnClickListener paramOnClickListener1, View.OnClickListener paramOnClickListener2)
  {
    this.c = new WeakReference(paramOnClickListener1);
    this.e = new WeakReference(paramOnClickListener2);
  }

  public final void a(String paramString)
  {
    int n = com.unionpay.mobile.android.utils.c.a(this.a, 12.0F);
    com.unionpay.mobile.android.utils.c.a(this.a, 20.0F);
    RelativeLayout localRelativeLayout = a(this.a);
    localRelativeLayout.setBackgroundColor(a.L);
    if (this.h != null)
    {
      WindowManager.LayoutParams localLayoutParams3 = this.h.getWindow().getAttributes();
      localLayoutParams3.alpha = 0.7F;
      this.h.getWindow().setAttributes(localLayoutParams3);
    }
    LinearLayout localLinearLayout1 = new LinearLayout(this.a);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setGravity(17);
    int i1 = a.j;
    int i2 = this.f - (i1 << 1);
    ImageView localImageView = new ImageView(this.a);
    localImageView.setImageDrawable(this.i);
    localLinearLayout1.addView(localImageView, new LinearLayout.LayoutParams(i2, -2));
    TextView localTextView = new TextView(this.a);
    localTextView.setTextSize(l);
    localTextView.setTextColor(-1);
    localTextView.setText(paramString);
    localTextView.setGravity(16);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams1.topMargin = n;
    localLinearLayout1.addView(localTextView, localLayoutParams1);
    LinearLayout localLinearLayout2 = new LinearLayout(this.a);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setGravity(17);
    int i3 = a.p;
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(i3, i3);
    localLayoutParams2.topMargin = n;
    localLinearLayout1.addView(new ProgressBar(this.a), localLayoutParams2);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(10, -1);
    localLayoutParams.addRule(14, -1);
    int i4 = com.unionpay.mobile.android.utils.c.a(this.a, 20.0F);
    localRelativeLayout.setPadding(i4, i4, i4, i4);
    localRelativeLayout.addView(localLinearLayout1, localLayoutParams);
    if ((this.h != null) && (!this.h.isShowing()) && (!d()))
      this.h.show();
  }

  public final void a(String paramString1, String paramString2, String paramString3)
  {
    RelativeLayout localRelativeLayout = a(this.a);
    int n = a.b;
    LinearLayout localLinearLayout1 = new LinearLayout(this.a);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setGravity(1);
    localRelativeLayout.addView(localLinearLayout1, new LinearLayout.LayoutParams(-1, -2));
    if ((paramString1 != null) && (paramString1.length() != 0))
    {
      TextView localTextView2 = new TextView(this.a);
      localTextView2.getPaint().setFakeBoldText(true);
      localTextView2.setTextSize(k);
      localTextView2.setTextColor(-13421773);
      localTextView2.setText(paramString1);
      localTextView2.setGravity(17);
      localTextView2.setPadding(n, n << 1, n, n);
      localLinearLayout1.addView(localTextView2, new LinearLayout.LayoutParams(-1, -2));
    }
    TextView localTextView1 = new TextView(this.a);
    localTextView1.setTextSize(l);
    localTextView1.setTextColor(-13421773);
    localTextView1.setText(paramString2);
    localTextView1.setPadding(n, n, n, 0);
    localTextView1.setGravity(17);
    localLinearLayout1.addView(localTextView1, new LinearLayout.LayoutParams(-1, -2));
    FrameLayout localFrameLayout = new FrameLayout(this.a);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams1.topMargin = (a.b << 1);
    localLinearLayout1.addView(localFrameLayout, localLayoutParams1);
    LinearLayout localLinearLayout2 = new LinearLayout(this.a);
    localLinearLayout2.setOrientation(1);
    localFrameLayout.addView(localLinearLayout2, new LinearLayout.LayoutParams(-1, -2));
    LinearLayout localLinearLayout3 = new LinearLayout(this.a);
    localLinearLayout3.setOrientation(0);
    localLinearLayout3.setBackgroundColor(-7829368);
    new LinearLayout.LayoutParams(-1, -2);
    localLinearLayout2.addView(localLinearLayout3);
    LinearLayout localLinearLayout4 = new LinearLayout(this.a);
    localLinearLayout2.addView(localLinearLayout4, new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout4.setOrientation(0);
    localLinearLayout4.setGravity(17);
    this.b = new TextView(this.a);
    this.b.setPadding(5, 5, 5, 5);
    this.b.getPaint().setFakeBoldText(true);
    this.b.setText(paramString3);
    this.b.setTextSize(m);
    this.b.setTextColor(d.a(-15364869, -5846275));
    this.b.setGravity(17);
    int i1 = a.o;
    if ((this.c != null) && (this.c.get() != null))
      this.b.setOnClickListener((View.OnClickListener)this.c.get());
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, i1);
    localLinearLayout4.addView(this.b, localLayoutParams2);
    localFrameLayout.addView(new o(this.a), new FrameLayout.LayoutParams(-1, a.H));
    if ((this.h != null) && (!this.h.isShowing()) && (!d()))
      this.h.show();
  }

  public final void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    a(paramString1, paramString2, paramString3, paramString4, true);
  }

  public final void a(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    RelativeLayout localRelativeLayout = a(this.a);
    int n = a.b;
    LinearLayout localLinearLayout1 = new LinearLayout(this.a);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setGravity(1);
    localRelativeLayout.addView(localLinearLayout1, new LinearLayout.LayoutParams(-1, -2));
    if ((paramString1 != null) && (paramString1.length() != 0))
    {
      TextView localTextView2 = new TextView(this.a);
      localTextView2.getPaint().setFakeBoldText(true);
      localTextView2.setTextSize(k);
      localTextView2.setTextColor(-13421773);
      localTextView2.setText(paramString1);
      localTextView2.setGravity(17);
      localTextView2.setPadding(n, n << 1, n, n);
      localLinearLayout1.addView(localTextView2, new LinearLayout.LayoutParams(-1, -2));
    }
    TextView localTextView1 = new TextView(this.a);
    localTextView1.setTextSize(l);
    localTextView1.setTextColor(-13421773);
    localTextView1.setText(paramString2);
    localTextView1.setPadding(n, n, n, 0);
    localTextView1.setGravity(17);
    localLinearLayout1.addView(localTextView1, new LinearLayout.LayoutParams(-1, -2));
    int i1 = com.unionpay.mobile.android.utils.c.a(this.a, 1.0F);
    LinearLayout localLinearLayout2 = new LinearLayout(this.a);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setBackgroundColor(-7829368);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, i1);
    localLayoutParams1.topMargin = (a.b << 1);
    localLinearLayout1.addView(localLinearLayout2, localLayoutParams1);
    LinearLayout localLinearLayout3 = new LinearLayout(this.a);
    localLinearLayout3.setBackgroundColor(-1);
    localLinearLayout1.addView(localLinearLayout3, new LinearLayout.LayoutParams(-1, -2));
    localLinearLayout3.setOrientation(0);
    localLinearLayout3.setGravity(17);
    int i2 = this.f - a.H >> 1;
    this.b = new TextView(this.a);
    if (!paramBoolean)
      this.b.getPaint().setFakeBoldText(true);
    this.b.setText(paramString3);
    this.b.setTextSize(m);
    this.b.setTextColor(d.a(-15364869, -5846275));
    this.b.setGravity(17);
    int i3 = a.o;
    if ((this.c != null) && (this.c.get() != null))
      this.b.setOnClickListener((View.OnClickListener)this.c.get());
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(i2, i3);
    localLayoutParams2.leftMargin = 5;
    localLayoutParams2.bottomMargin = 5;
    localLinearLayout3.addView(this.b, localLayoutParams2);
    LinearLayout localLinearLayout4 = new LinearLayout(this.a);
    localLinearLayout4.setOrientation(1);
    localLinearLayout4.setBackgroundColor(-7829368);
    localLinearLayout3.addView(localLinearLayout4, new LinearLayout.LayoutParams(i1, -1));
    this.d = new TextView(this.a);
    if (paramBoolean)
      this.d.getPaint().setFakeBoldText(true);
    this.d.setText(paramString4);
    this.d.setTextSize(m);
    this.d.setTextColor(d.a(-15364869, -5846275));
    this.d.setGravity(17);
    if ((this.e != null) && (this.e.get() != null))
      this.d.setOnClickListener((View.OnClickListener)this.e.get());
    LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(i2, i3);
    localLayoutParams3.leftMargin = 5;
    localLayoutParams3.bottomMargin = 5;
    localLinearLayout3.addView(this.d, localLayoutParams3);
    if ((this.h != null) && (!this.h.isShowing()) && (!d()))
      this.h.show();
  }

  public final boolean a()
  {
    return (this.h != null) && (this.h.isShowing());
  }

  public final void b()
  {
    if (this.h != null)
    {
      this.h.hide();
      this.h.show();
    }
  }

  public final void c()
  {
    if ((this.h != null) && (this.h.isShowing()))
    {
      this.h.dismiss();
      this.h = null;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.m
 * JD-Core Version:    0.6.2
 */