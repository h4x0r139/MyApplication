package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.widgets.m;

final class at
  implements View.OnClickListener
{
  at(as paramas)
  {
  }

  public final void onClick(View paramView)
  {
    if (this.a.o())
      return;
    String str1 = "";
    if (as.a(this.a) != null)
    {
      as.a(this.a).b();
      a.a locala2 = as.a(this.a).a();
      if (!locala2.a())
      {
        this.a.a(locala2.b);
        return;
      }
      str1 = locala2.b;
    }
    as.b(this.a).b();
    a.a locala1 = as.b(this.a).a();
    if (!locala1.a())
    {
      this.a.a(locala1.b);
      return;
    }
    if ((as.c(this.a) != null) && (!as.c(this.a).e()))
    {
      this.a.a(as.c(this.a).b());
      return;
    }
    if ((as.d(this.a) != null) && (!as.d(this.a).e()))
    {
      this.a.a(as.d(this.a).b());
      return;
    }
    String str2 = locala1.b;
    if (as.b(str1));
    for (String str3 = str2 + "," + str1; ; str3 = str2)
    {
      this.a.b.a(c.by.U);
      if ((this.a.a.z != null) && (this.a.a.z.length() > 0))
      {
        as.a(this.a, as.e(this.a), str3);
        return;
      }
      as.a(this.a, str3);
      return;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.at
 * JD-Core Version:    0.6.2
 */