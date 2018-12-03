package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d
  implements Parcelable.Creator<RegeocodeAddress>
{
  public RegeocodeAddress a(Parcel paramParcel)
  {
    return new RegeocodeAddress(paramParcel, null);
  }

  public RegeocodeAddress[] a(int paramInt)
  {
    return null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.d
 * JD-Core Version:    0.6.2
 */