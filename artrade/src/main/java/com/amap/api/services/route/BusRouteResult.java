package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class BusRouteResult extends RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<BusRouteResult> CREATOR = new c();
  private float a;
  private List<BusPath> b;
  private RouteSearch.BusRouteQuery c;

  public BusRouteResult()
  {
  }

  public BusRouteResult(Parcel paramParcel)
  {
    this.a = paramParcel.readFloat();
    this.b = paramParcel.createTypedArrayList(BusPath.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public RouteSearch.BusRouteQuery getBusQuery()
  {
    return this.c;
  }

  public List<BusPath> getPaths()
  {
    return this.b;
  }

  public float getTaxiCost()
  {
    return this.a;
  }

  public void setBusQuery(RouteSearch.BusRouteQuery paramBusRouteQuery)
  {
    this.c = paramBusRouteQuery;
  }

  protected void setPaths(List<BusPath> paramList)
  {
    this.b = paramList;
  }

  protected void setTaxiCost(float paramFloat)
  {
    this.a = paramFloat;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(this.a);
    paramParcel.writeTypedList(this.b);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.BusRouteResult
 * JD-Core Version:    0.6.2
 */