package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.d;

public final class v extends LinearLayout
{
  private Context a = null;
  private TextView b = null;

  private v(Context paramContext, Drawable paramDrawable)
  {
    super(paramContext);
    this.a = paramContext;
    setOrientation(0);
    Context localContext = this.a;
    if (paramDrawable != null)
    {
      ImageView localImageView = new ImageView(localContext);
      localImageView.setBackgroundDrawable(paramDrawable);
      int i = b.o;
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(i, i);
      localLayoutParams1.gravity = 16;
      addView(localImageView, localLayoutParams1);
    }
    this.b = new TextView(localContext);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams2.gravity = 16;
    if (paramDrawable != null)
      localLayoutParams2.leftMargin = a.d;
    addView(this.b, localLayoutParams2);
  }

  public static v a(Context paramContext, Drawable paramDrawable)
  {
    v localv = new v(paramContext, paramDrawable);
    if (localv.b != null)
      localv.b.setTextSize(16.0F);
    localv.a(d.a(-16758391, -65536));
    return localv;
  }

  public final void a(ColorStateList paramColorStateList)
  {
    if (this.b != null)
      this.b.setTextColor(paramColorStateList);
  }

  public final void a(CharSequence paramCharSequence)
  {
    if (this.b != null)
      this.b.setText(paramCharSequence);
  }

  public final void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    if (this.b != null)
      this.b.setOnClickListener(paramOnClickListener);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.v
 * JD-Core Version:    0.6.2
 */