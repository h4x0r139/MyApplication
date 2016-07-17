package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;
import com.nostra13.universalimageloader.utils.L;

final class ProcessAndDisplayImageTask
  implements Runnable
{
  private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
  private final Bitmap bitmap;
  private final ImageLoaderEngine engine;
  private final Handler handler;
  private final ImageLoadingInfo imageLoadingInfo;

  public ProcessAndDisplayImageTask(ImageLoaderEngine paramImageLoaderEngine, Bitmap paramBitmap, ImageLoadingInfo paramImageLoadingInfo, Handler paramHandler)
  {
    this.engine = paramImageLoaderEngine;
    this.bitmap = paramBitmap;
    this.imageLoadingInfo = paramImageLoadingInfo;
    this.handler = paramHandler;
  }

  public void run()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.imageLoadingInfo.memoryCacheKey;
    L.d("PostProcess image before displaying [%s]", arrayOfObject);
    LoadAndDisplayImageTask.runTask(new DisplayBitmapTask(this.imageLoadingInfo.options.getPostProcessor().process(this.bitmap), this.imageLoadingInfo, this.engine, LoadedFrom.MEMORY_CACHE), this.imageLoadingInfo.options.isSyncLoading(), this.handler, this.engine);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.nostra13.universalimageloader.core.ProcessAndDisplayImageTask
 * JD-Core Version:    0.6.2
 */