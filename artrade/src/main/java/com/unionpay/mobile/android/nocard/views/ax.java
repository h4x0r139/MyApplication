package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

final class ax
  implements View.OnClickListener
{
  ax(as paramas)
  {
  }

  public final void onClick(View paramView)
  {
    as.b(this.a).b();
    ((InputMethodManager)this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
    this.a.a(as.c(this.a).d(), as.c(this.a).c());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.ax
 * JD-Core Version:    0.6.2
 */