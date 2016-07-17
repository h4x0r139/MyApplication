package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;

final class j
  implements View.OnClickListener
{
  j(i parami)
  {
  }

  public final void onClick(View paramView)
  {
    if (this.a.e != null)
    {
      AbstractMethod.b localb = this.a.e;
      String str = i.a(i.a(this.a), "label");
      localb.a(str, i.a(i.a(this.a), "href"));
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.j
 * JD-Core Version:    0.6.2
 */