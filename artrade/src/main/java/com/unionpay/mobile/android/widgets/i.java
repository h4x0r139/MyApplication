package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;

final class i
  implements AdapterView.OnItemClickListener
{
  i(g paramg)
  {
  }

  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    g.a(this.a, paramInt);
    if (g.a(this.a) != null)
      g.a(this.a).dismiss();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.i
 * JD-Core Version:    0.6.2
 */