package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.utils.g;

final class aw
  implements View.OnClickListener
{
  aw(UPWidget paramUPWidget)
  {
  }

  public final void onClick(View paramView)
  {
    int i = paramView.getId();
    int j = UPWidget.b(this.a);
    if (i == 10)
    {
      g.c("kb", " [ FIN ]");
      UPWidget.c(this.a);
      return;
    }
    if (i == 20)
    {
      g.c("kb", " [ DEL ]");
      if (j > 0)
      {
        UPWidget.a(this.a, UPWidget.d(this.a));
        UPWidget.e(this.a);
        String str2 = this.a.b.b().toString().substring(0, j - 1);
        this.a.b.c(str2);
        this.a.b.b(str2.length());
      }
      UPWidget.b(this.a);
      return;
    }
    if (UPWidget.b(this.a) == 6)
    {
      g.c("kb", " [ FIN ]");
      return;
    }
    UPWidget.a(this.a, UPWidget.d(this.a), Integer.toString(i));
    if (j == 0);
    for (String str1 = "*"; ; str1 = this.a.b.b() + "*")
    {
      this.a.b.c(str1);
      this.a.b.b(str1.length());
      UPWidget.f(this.a);
      return;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.aw
 * JD-Core Version:    0.6.2
 */