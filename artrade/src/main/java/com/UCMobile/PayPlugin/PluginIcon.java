package com.UCMobile.PayPlugin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import com.unionpay.mobile.android.resource.a;

public class PluginIcon
{
  Bitmap a = null;
  int[] b = null;
  Context c = null;
  private int d;

  static
  {
    System.loadLibrary("ucpayplugin");
  }

  public PluginIcon(Context paramContext, int paramInt)
  {
    this.c = paramContext;
    this.d = paramInt;
    try
    {
      this.a = new BitmapDrawable(a.class.getClassLoader().getResourceAsStream("res/drawable/mobilepayplugin.bin")).getBitmap().copy(Bitmap.Config.ARGB_8888, false);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public int getIconHeight()
  {
    if (this.a != null)
      return this.a.getHeight();
    return 0;
  }

  public int[] getIconPixels()
  {
    if (this.b != null)
      return this.b;
    int i = this.a.getWidth();
    int j = this.a.getHeight();
    int k = j * this.a.getRowBytes();
    Bitmap localBitmap = this.a;
    int m = 0;
    if (localBitmap != null)
    {
      this.b = new int[k];
      this.a.getPixels(this.b, 0, i, 0, 0, i, j);
    }
    while (m < k)
    {
      int n = 0xFF & this.b[m] >> 16;
      int i1 = 0xFF0000 & this.b[m] << 16;
      this.b[m] = (n | (i1 | 0xFF00FF00 & this.b[m]));
      m++;
    }
    return this.b;
  }

  public int getIconRowBytes()
  {
    if (this.a != null)
      return this.a.getRowBytes();
    return 0;
  }

  public int getIconWidth()
  {
    if (this.a != null)
      return this.a.getWidth();
    return 0;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.UCMobile.PayPlugin.PluginIcon
 * JD-Core Version:    0.6.2
 */