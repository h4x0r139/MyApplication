package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.Iterator;

final class i
  implements AdapterView.OnItemClickListener
{
  i(h paramh)
  {
  }

  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    paramView.setTag(Integer.valueOf(h.a(this.a)));
    if (h.b(this.a) != h.c(this.a))
    {
      Object localObject2 = h.d(this.a).get(h.b(this.a));
      if ((localObject2 instanceof c))
        ((c)localObject2).a(-1);
    }
    Object localObject1 = h.d(this.a).get(h.a(this.a));
    if ((localObject1 instanceof c))
      ((c)localObject1).a(paramInt);
    h.a(this.a, h.a(this.a));
    h.b(this.a, paramInt);
    Iterator localIterator = h.e(this.a).iterator();
    while (localIterator.hasNext())
      ((AdapterView.OnItemClickListener)localIterator.next()).onItemClick(paramAdapterView, paramView, paramInt, paramLong);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.i
 * JD-Core Version:    0.6.2
 */