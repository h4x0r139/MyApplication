package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;

final class d
  implements View.OnClickListener
{
  d(c paramc)
  {
  }

  public final void onClick(View paramView)
  {
    Iterator localIterator = c.a(this.a).iterator();
    while (localIterator.hasNext())
      ((View.OnClickListener)localIterator.next()).onClick(paramView);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.d
 * JD-Core Version:    0.6.2
 */