package com.unionpay.uppay;

import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.plugin.BaseActivity;

public final class PayActivity extends BaseActivity
{
  private UPPayEngine c;

  static
  {
    System.loadLibrary("entryex");
  }

  public final b b(int paramInt)
  {
    l locall = null;
    if (paramInt == 1)
      locall = new l(this);
    return locall;
  }

  public final UPPayEngine c()
  {
    this.c = new UPPayEngine(this);
    return this.c;
  }

  protected final void onDestroy()
  {
    super.onDestroy();
    this.c.c();
    this.c = null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.uppay.PayActivity
 * JD-Core Version:    0.6.2
 */