package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;

final class al
  implements AdapterView.OnItemClickListener
{
  al(ai paramai)
  {
  }

  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    int i = ((Integer)paramView.getTag()).intValue();
    ai.a(this.a, i, paramInt);
    if (ai.a(this.a) != null)
      ai.a(this.a).dismiss();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.al
 * JD-Core Version:    0.6.2
 */