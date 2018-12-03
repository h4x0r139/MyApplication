package com.amap.api.services.help;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import java.util.List;

public final class Inputtips
{
  Handler a = new a(this);
  private Context b;
  private InputtipsListener c;

  public Inputtips(Context paramContext, InputtipsListener paramInputtipsListener)
  {
    com.amap.api.services.core.b.a(paramContext);
    this.b = paramContext.getApplicationContext();
    this.c = paramInputtipsListener;
  }

  public void requestInputtips(final String paramString1, final String paramString2)
    throws AMapException
  {
    if ((paramString1 == null) || (paramString1.equals("")))
      throw new AMapException("无效的参数 - IllegalArgumentException");
    new Thread()
    {
      public void run()
      {
        b localb = new b(new c(paramString1, paramString2), com.amap.api.services.core.c.a(Inputtips.a(Inputtips.this)));
        Message localMessage = new Message();
        try
        {
          localMessage.obj = ((List)localb.i());
          localMessage.what = 0;
          return;
        }
        catch (AMapException localAMapException)
        {
          localMessage.what = localAMapException.getErrorCode();
          return;
        }
        finally
        {
          Inputtips.this.a.sendMessage(localMessage);
        }
      }
    }
    .start();
  }

  public static abstract interface InputtipsListener
  {
    public abstract void onGetInputtips(List<Tip> paramList, int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.help.Inputtips
 * JD-Core Version:    0.6.2
 */