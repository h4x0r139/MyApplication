package com.unionpay.mobile.android.upviews;

import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

final class e extends TimerTask
{
  e(d.d paramd)
  {
  }

  public final void run()
  {
    if (!d.b(this.a.a))
      d.a(this.a.a).sendEmptyMessage(3);
    d.c(this.a.a).cancel();
    d.c(this.a.a).purge();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upviews.e
 * JD-Core Version:    0.6.2
 */