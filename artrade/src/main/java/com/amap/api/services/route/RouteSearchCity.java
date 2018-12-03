package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class RouteSearchCity extends SearchCity
  implements Parcelable
{
  public static final Parcelable.Creator<RouteSearchCity> CREATOR = new s();
  List<District> a;

  public RouteSearchCity()
  {
  }

  public RouteSearchCity(Parcel paramParcel)
  {
    super(paramParcel);
    this.a = paramParcel.createTypedArrayList(District.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public List<District> getDistricts()
  {
    return this.a;
  }

  public void setDistricts(List<District> paramList)
  {
    this.a = paramList;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeTypedList(this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.RouteSearchCity
 * JD-Core Version:    0.6.2
 */