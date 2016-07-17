package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class h
  implements Parcelable.Creator<PoiItemDetail>
{
  public PoiItemDetail a(Parcel paramParcel)
  {
    return new PoiItemDetail(paramParcel, null);
  }

  public PoiItemDetail[] a(int paramInt)
  {
    return new PoiItemDetail[paramInt];
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.h
 * JD-Core Version:    0.6.2
 */