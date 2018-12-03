package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Iterator;

final class m
  implements View.OnClickListener
{
  m(h paramh)
  {
  }

  public final void onClick(View paramView)
  {
    if (h.b(this.a) != h.c(this.a))
    {
      Object localObject = h.d(this.a).get(h.b(this.a));
      if ((localObject instanceof c))
        ((c)localObject).a(-1);
    }
    h.a(this.a, h.a(this.a));
    h.b(this.a, h.c(this.a));
    Iterator localIterator = h.k(this.a).iterator();
    while (localIterator.hasNext())
      ((View.OnClickListener)localIterator.next()).onClick(paramView);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.m
 * JD-Core Version:    0.6.2
 */