package com.unionpay.mobile.android.utils;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.unionpay.mobile.android.net.c;
import com.unionpay.mobile.android.net.d;
import java.lang.ref.WeakReference;

public final class i
  implements Handler.Callback, Runnable
{
  private d a = null;
  private Handler b = null;
  private WeakReference<a> c = null;

  public i(String paramString, a parama)
  {
    this.a = new d(0, paramString, null);
    this.b = new Handler(this);
    this.c = new WeakReference(parama);
  }

  public final void a()
  {
    new Thread(this).start();
  }

  public final boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    }
    do
      return true;
    while ((this.c == null) || (this.c.get() == null));
    if (paramMessage.obj != null);
    for (byte[] arrayOfByte = (byte[])paramMessage.obj; ; arrayOfByte = null)
    {
      ((a)this.c.get()).a(paramMessage.arg1, arrayOfByte);
      break;
    }
  }

  public final void run()
  {
    c localc = new c(this.a);
    int i = localc.a();
    Message localMessage = this.b.obtainMessage();
    localMessage.what = 0;
    localMessage.arg1 = i;
    localMessage.obj = localc.b();
    this.b.sendMessage(localMessage);
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt, byte[] paramArrayOfByte);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.i
 * JD-Core Version:    0.6.2
 */