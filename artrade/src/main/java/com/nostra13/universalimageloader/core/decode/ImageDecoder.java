package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import java.io.IOException;

public abstract interface ImageDecoder
{
  public abstract Bitmap decode(ImageDecodingInfo paramImageDecodingInfo)
    throws IOException;
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.decode.ImageDecoder
 * JD-Core Version:    0.6.2
 */