package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<RouteResult> CREATOR = new q();
  private LatLonPoint a;
  private LatLonPoint b;

  public RouteResult()
  {
  }

  public RouteResult(Parcel paramParcel)
  {
    this.a = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
    this.b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
  }

  public int describeContents()
  {
    return 0;
  }

  public LatLonPoint getStartPos()
  {
    return this.a;
  }

  public LatLonPoint getTargetPos()
  {
    return this.b;
  }

  protected void setStartPos(LatLonPoint paramLatLonPoint)
  {
    this.a = paramLatLonPoint;
  }

  protected void setTargetPos(LatLonPoint paramLatLonPoint)
  {
    this.b = paramLatLonPoint;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.a, paramInt);
    paramParcel.writeParcelable(this.b, paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.RouteResult
 * JD-Core Version:    0.6.2
 */