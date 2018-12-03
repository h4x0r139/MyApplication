package com.unionpay.mobile.android.widgets;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;

final class n extends Dialog
{
  n(m paramm, Context paramContext)
  {
    super(paramContext);
  }

  public final boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      return true;
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.n
 * JD-Core Version:    0.6.2
 */