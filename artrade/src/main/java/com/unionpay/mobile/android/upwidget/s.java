package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final class s
  implements ViewTreeObserver.OnGlobalLayoutListener
{
  s(UPScrollView paramUPScrollView)
  {
  }

  public final void onGlobalLayout()
  {
    UPScrollView.a(this.a).sendMessageDelayed(UPScrollView.a(this.a).obtainMessage(), 5L);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.s
 * JD-Core Version:    0.6.2
 */