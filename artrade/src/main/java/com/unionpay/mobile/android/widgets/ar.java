package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.resource.c;

public final class ar extends LinearLayout
{
  private c a = null;
  private ImageView b = null;
  private ImageView c = null;

  public ar(Context paramContext)
  {
    super(paramContext);
    this.a = c.a(paramContext);
    setBackgroundColor(0);
    setOrientation(1);
    this.c = new ImageView(paramContext);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, a.C);
    localLayoutParams.gravity = 80;
    addView(this.c, localLayoutParams);
    Drawable localDrawable = this.a.a(1001);
    if (this.b != null)
      this.b.setBackgroundDrawable(localDrawable);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ar
 * JD-Core Version:    0.6.2
 */