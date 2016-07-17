package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;

final class r
  implements AdapterView.OnItemClickListener
{
  r(p paramp)
  {
  }

  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    p.a(this.a, paramInt);
    if (p.a(this.a) != null)
      p.a(this.a).dismiss();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.r
 * JD-Core Version:    0.6.2
 */