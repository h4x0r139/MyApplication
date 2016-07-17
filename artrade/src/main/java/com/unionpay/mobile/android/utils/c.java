package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class c
{
  public static int a(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.c
 * JD-Core Version:    0.6.2
 */