package com.tg.jiadeonline.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class ImageThumbnail
{
  public static Bitmap PicZoom(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramInt1 / i, paramInt2 / j);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }

  public static int reckonThumbnail(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i;
    if (((paramInt2 > paramInt4) && (paramInt1 > paramInt3)) || ((paramInt2 <= paramInt4) && (paramInt1 > paramInt3)))
    {
      i = (int)(paramInt1 / paramInt3);
      if (i <= 1)
        i = 1;
    }
    do
    {
      return i;
      if ((paramInt2 <= paramInt4) || (paramInt1 > paramInt3))
        break;
      i = (int)(paramInt2 / paramInt4);
    }
    while (i > 1);
    return 1;
    return 1;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.ImageThumbnail
 * JD-Core Version:    0.6.2
 */