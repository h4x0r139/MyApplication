package com.unionpay.mobile.android.nocard.views;

import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.LinearLayout;

final class c
  implements ViewTreeObserver.OnPreDrawListener
{
  c(b paramb)
  {
  }

  public final boolean onPreDraw()
  {
    b.a(this.a).getViewTreeObserver().removeOnPreDrawListener(this);
    b.a(this.a, b.a(this.a).getMeasuredHeight());
    b.b(this.a, b.a(this.a).getTop());
    return true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.c
 * JD-Core Version:    0.6.2
 */