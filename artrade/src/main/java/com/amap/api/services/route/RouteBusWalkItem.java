package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteBusWalkItem extends WalkPath
  implements Parcelable
{
  public static final Parcelable.Creator<RouteBusWalkItem> CREATOR = new o();
  private LatLonPoint a;
  private LatLonPoint b;

  public RouteBusWalkItem()
  {
  }

  public RouteBusWalkItem(Parcel paramParcel)
  {
    super(paramParcel);
    this.a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    this.b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
  }

  public int describeContents()
  {
    return 0;
  }

  public LatLonPoint getDestination()
  {
    return this.b;
  }

  public LatLonPoint getOrigin()
  {
    return this.a;
  }

  public void setDestination(LatLonPoint paramLatLonPoint)
  {
    this.b = paramLatLonPoint;
  }

  public void setOrigin(LatLonPoint paramLatLonPoint)
  {
    this.a = paramLatLonPoint;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.a, paramInt);
    paramParcel.writeParcelable(this.b, paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.RouteBusWalkItem
 * JD-Core Version:    0.6.2
 */