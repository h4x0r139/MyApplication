package com.UCMobile.PayPlugin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PluginSurfaceView extends SurfaceView
{
  SurfaceHolder a = null;
  Paint b = null;
  Bitmap c = null;
  int[] d = null;
  private final int e;
  private boolean f = true;
  private Object g = new Object();

  static
  {
    System.loadLibrary("ucpayplugin");
  }

  public PluginSurfaceView(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramContext);
    this.a.setFormat(1);
    this.b = new Paint();
    this.e = paramInt1;
    this.c = new BitmapDrawable(com.unionpay.mobile.android.resource.a.class.getClassLoader().getResourceAsStream("res/drawable/mobilepayplugin.bin")).getBitmap().copy(Bitmap.Config.ARGB_8888, false);
    getHolder().setFormat(-3);
    getHolder().setFormat(1);
    getHolder().addCallback(new a(this));
    getHolder().setSizeFromLayout();
    setWillNotDraw(false);
  }

  private native void nativeSurfaceChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  private native void nativeSurfaceCreated(int paramInt);

  private native void nativeSurfaceDestroyed(int paramInt);

  public int getIconHeight()
  {
    if (this.c != null)
      return this.c.getHeight();
    return 0;
  }

  public int[] getIconPixels()
  {
    if (this.d != null)
      return this.d;
    int i = this.c.getWidth();
    int j = this.c.getHeight();
    int k = j * this.c.getRowBytes();
    Bitmap localBitmap = this.c;
    int m = 0;
    if (localBitmap != null)
    {
      this.d = new int[k];
      this.c.getPixels(this.d, 0, i, 0, 0, i, j);
    }
    while (m < k)
    {
      int n = 0xFF & this.d[m] >> 16;
      int i1 = 0xFF0000 & this.d[m] << 16;
      this.d[m] = (n | (i1 | 0xFF00FF00 & this.d[m]));
      m++;
    }
    return this.d;
  }

  public int getIconRowBytes()
  {
    if (this.c != null)
      return this.c.getRowBytes();
    return 0;
  }

  public int getIconWidth()
  {
    if (this.c != null)
      return this.c.getWidth();
    return 0;
  }

  public void invalidateNPP()
  {
    synchronized (this.g)
    {
      this.f = false;
      return;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.UCMobile.PayPlugin.PluginSurfaceView
 * JD-Core Version:    0.6.2
 */