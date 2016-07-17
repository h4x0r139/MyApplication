package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

final class am
  implements View.OnClickListener
{
  am(ak paramak)
  {
  }

  public final void onClick(View paramView)
  {
    ak.a(this.a).b();
    ((InputMethodManager)this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
    this.a.a(ak.b(this.a).d(), ak.b(this.a).c());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.am
 * JD-Core Version:    0.6.2
 */