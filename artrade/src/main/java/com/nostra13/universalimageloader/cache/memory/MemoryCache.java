package com.nostra13.universalimageloader.cache.memory;

import android.graphics.Bitmap;
import java.util.Collection;

public abstract interface MemoryCache
{
  public abstract void clear();

  public abstract Bitmap get(String paramString);

  public abstract Collection<String> keys();

  public abstract boolean put(String paramString, Bitmap paramBitmap);

  public abstract Bitmap remove(String paramString);
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.cache.memory.MemoryCache
 * JD-Core Version:    0.6.2
 */