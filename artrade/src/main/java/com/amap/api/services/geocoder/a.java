package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class a
  implements Parcelable.Creator<GeocodeAddress>
{
  public GeocodeAddress a(Parcel paramParcel)
  {
    return new GeocodeAddress(paramParcel, null);
  }

  public GeocodeAddress[] a(int paramInt)
  {
    return null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.a
 * JD-Core Version:    0.6.2
 */