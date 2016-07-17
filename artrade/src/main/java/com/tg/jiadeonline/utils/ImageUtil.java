package com.tg.jiadeonline.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.cache.disc.impl.LimitedAgeDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration.Builder;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ImageUtil
{
  public static DisplayImageOptions getDisplayImageOptions()
  {
    return new DisplayImageOptions.Builder().showStubImage(2130837541).showImageForEmptyUri(2130837541).showImageOnFail(2130837541).cacheInMemory(false).cacheOnDisc(true).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).build();
  }

  public static DisplayImageOptions getDisplayImageOptions1()
  {
    return new DisplayImageOptions.Builder().showStubImage(2130837574).showImageForEmptyUri(2130837574).showImageOnFail(2130837574).cacheInMemory(false).cacheOnDisc(true).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2).build();
  }

  public static ImageLoaderConfiguration getImageLoaderConfiguration(Context paramContext)
  {
    LimitedAgeDiskCache localLimitedAgeDiskCache = new LimitedAgeDiskCache(StorageUtils.getCacheDirectory(paramContext), 604800L);
    return new ImageLoaderConfiguration.Builder(paramContext).threadPoolSize(5).diskCache(localLimitedAgeDiskCache).diskCacheSize(134217728).diskCacheFileCount(512).defaultDisplayImageOptions(DisplayImageOptions.createSimple()).writeDebugLogs().build();
  }

  public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener
  {
    static final List<String> displayedImages = Collections.synchronizedList(new LinkedList());

    public void onLoadingComplete(String paramString, View paramView, Bitmap paramBitmap)
    {
      ImageView localImageView;
      if (paramBitmap != null)
      {
        localImageView = (ImageView)paramView;
        if (!displayedImages.contains(paramString))
          break label49;
      }
      label49: for (int i = 0; ; i = 1)
      {
        if (i != 0)
        {
          FadeInBitmapDisplayer.animate(localImageView, 500);
          displayedImages.add(paramString);
        }
        return;
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.ImageUtil
 * JD-Core Version:    0.6.2
 */