package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.widgets.m;

final class h
  implements View.OnClickListener
{
  h(g paramg)
  {
  }

  public final void onClick(View paramView)
  {
    if (this.a.o())
      return;
    g.a(this.a).b();
    a.a locala = g.a(this.a).a();
    if (!locala.a())
    {
      this.a.a(locala.b);
      return;
    }
    if ((g.b(this.a) != null) && (!g.b(this.a).e()))
    {
      this.a.a(g.b(this.a).b());
      return;
    }
    this.a.b.a(c.by.U);
    if (!g.c(this.a))
    {
      String str = f.a(this.a.a.x, "action");
      this.a.e.b(str, locala.b);
      g.a(this.a, 102);
      return;
    }
    g.d(this.a);
    g.a(this.a, 104);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.h
 * JD-Core Version:    0.6.2
 */