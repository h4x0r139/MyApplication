package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.widgets.m;

final class v
  implements View.OnClickListener
{
  v(o paramo)
  {
  }

  public final void onClick(View paramView)
  {
    if (this.a.o())
      return;
    o.b(this.a).b();
    a.a locala1 = o.b(this.a).a();
    if (!locala1.a())
    {
      this.a.a(locala1.b);
      return;
    }
    if ((o.c(this.a) != null) && (!o.c(this.a).e()))
    {
      this.a.a(o.c(this.a).b());
      return;
    }
    String str1 = "";
    if (o.d(this.a) != null)
    {
      a.a locala2 = o.d(this.a).a();
      if (!locala2.a())
      {
        this.a.a(locala2.b);
        return;
      }
      str1 = locala2.b;
    }
    String str2 = locala1.b;
    if (o.b(str1));
    for (String str3 = str2 + "," + str1; ; str3 = str2)
    {
      this.a.b.a(c.by.U);
      o.b(this.a, str3);
      return;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.v
 * JD-Core Version:    0.6.2
 */