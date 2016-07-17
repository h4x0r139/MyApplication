package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;

final class k
  implements View.OnClickListener
{
  k(h paramh)
  {
  }

  public final void onClick(View paramView)
  {
    Iterator localIterator = h.g(this.a).iterator();
    while (localIterator.hasNext())
      ((View.OnClickListener)localIterator.next()).onClick(paramView);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.k
 * JD-Core Version:    0.6.2
 */