package com.UCMobile.PayPlugin;

import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

final class a
  implements SurfaceHolder.Callback
{
  a(PluginSurfaceView paramPluginSurfaceView)
  {
  }

  public final void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    synchronized (PluginSurfaceView.a(this.a))
    {
      if (PluginSurfaceView.b(this.a))
        PluginSurfaceView.a(this.a, PluginSurfaceView.c(this.a), paramInt1, paramInt2, paramInt3);
      return;
    }
  }

  public final void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
    synchronized (PluginSurfaceView.a(this.a))
    {
      if (PluginSurfaceView.b(this.a))
        PluginSurfaceView.a(this.a, PluginSurfaceView.c(this.a));
      return;
    }
  }

  public final void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    synchronized (PluginSurfaceView.a(this.a))
    {
      if (PluginSurfaceView.b(this.a))
        PluginSurfaceView.b(this.a, PluginSurfaceView.c(this.a));
      return;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.UCMobile.PayPlugin.a
 * JD-Core Version:    0.6.2
 */