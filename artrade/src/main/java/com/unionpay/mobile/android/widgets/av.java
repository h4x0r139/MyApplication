package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.unionpay.mobile.android.utils.g;

final class av
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  av(UPWidget paramUPWidget)
  {
  }

  public final void onGlobalLayout()
  {
    g.a("uppay", "onGlobalLayout() +++");
    int i = UPWidget.a(this.a).getRootView().getHeight() - UPWidget.a(this.a).getHeight();
    if ((i <= UPWidget.l()) && (i <= UPWidget.l()))
      this.a.k();
    g.a("uppay", "onGlobalLayout() ---");
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.av
 * JD-Core Version:    0.6.2
 */