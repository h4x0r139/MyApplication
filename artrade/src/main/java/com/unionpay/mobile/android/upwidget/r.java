package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.os.Message;
import java.util.List;

final class r extends Handler
{
  r(UPRadiationView paramUPRadiationView)
  {
  }

  public final void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
    case 0:
    }
    do
    {
      return;
      UPRadiationView.a(this.a);
      this.a.invalidate();
    }
    while ((UPRadiationView.b(this.a) == null) || (UPRadiationView.b(this.a).size() <= 0));
    UPRadiationView.c(this.a).sendEmptyMessageDelayed(0, 50L);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.r
 * JD-Core Version:    0.6.2
 */