package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;

final class ab
  implements TextWatcher
{
  ab(z paramz)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    this.a.a(paramEditable);
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (z.a(this.a) != null)
      z.a(this.a).a(this.a.b, paramCharSequence.toString());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ab
 * JD-Core Version:    0.6.2
 */