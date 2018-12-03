package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.utils.g;

final class h
  implements View.OnClickListener
{
  h(CViewMethods paramCViewMethods, int paramInt)
  {
  }

  public final void onClick(View paramView)
  {
    g.c("uppay", " touched " + this.a);
    if (CViewMethods.a(this.b) != null)
      CViewMethods.a(this.b).b(this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.h
 * JD-Core Version:    0.6.2
 */