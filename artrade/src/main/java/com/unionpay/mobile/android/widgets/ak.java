package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

final class ak
  implements View.OnClickListener
{
  ak(ai paramai)
  {
  }

  public final void onClick(View paramView)
  {
    int i = ((Integer)paramView.getTag()).intValue();
    ai.a(this.a, i, 0);
    if (ai.a(this.a) != null)
      ai.a(this.a).dismiss();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ak
 * JD-Core Version:    0.6.2
 */