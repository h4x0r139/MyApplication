package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class g
  implements Parcelable.Creator<PoiItem>
{
  public PoiItem a(Parcel paramParcel)
  {
    return new PoiItem(paramParcel);
  }

  public PoiItem[] a(int paramInt)
  {
    return new PoiItem[paramInt];
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.g
 * JD-Core Version:    0.6.2
 */