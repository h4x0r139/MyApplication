package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.widgets.m;

final class al
  implements View.OnClickListener
{
  al(ak paramak)
  {
  }

  public final void onClick(View paramView)
  {
    if (this.a.o())
      return;
    ak.a(this.a).b();
    a.a locala = ak.a(this.a).a();
    if (!locala.a())
    {
      this.a.a(locala.b);
      return;
    }
    if ((ak.b(this.a) != null) && (!ak.b(this.a).e()))
    {
      this.a.a(ak.b(this.a).b());
      return;
    }
    String str = locala.b;
    this.a.b.a(c.by.U);
    this.a.e.f(str);
    ak.c(this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.al
 * JD-Core Version:    0.6.2
 */