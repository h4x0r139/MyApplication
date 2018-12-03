package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;

final class f
  implements View.OnClickListener
{
  f(b paramb)
  {
  }

  public final void onClick(View paramView)
  {
    if (this.a.e != null)
    {
      AbstractMethod.b localb = this.a.e;
      String str = b.a(b.c(this.a), "title");
      localb.a(str, b.a(b.c(this.a), "href"));
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.f
 * JD-Core Version:    0.6.2
 */