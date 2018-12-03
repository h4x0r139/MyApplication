package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class f
  implements Parcelable.Creator<LatLonPoint>
{
  public LatLonPoint a(Parcel paramParcel)
  {
    return new LatLonPoint(paramParcel, null);
  }

  public LatLonPoint[] a(int paramInt)
  {
    return new LatLonPoint[paramInt];
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.f
 * JD-Core Version:    0.6.2
 */