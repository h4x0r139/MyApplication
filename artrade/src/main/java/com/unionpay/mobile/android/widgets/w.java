package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;

final class w
  implements View.OnFocusChangeListener
{
  w(t paramt)
  {
  }

  public final void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
      if ((t.b(this.a)) && (t.a(this.a) != null))
        t.a(this.a).setVisibility(0);
    while (true)
    {
      if (t.d(this.a) != null)
        t.d(this.a).a(paramBoolean);
      if (t.e(this.a) != null)
        t.e(this.a).a(paramBoolean);
      t.g();
      this.a.invalidate();
      return;
      if ((t.b(this.a)) && (t.a(this.a) != null))
        t.a(this.a).setVisibility(8);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.w
 * JD-Core Version:    0.6.2
 */