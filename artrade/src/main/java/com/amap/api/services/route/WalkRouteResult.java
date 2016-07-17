package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class WalkRouteResult extends RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<WalkRouteResult> CREATOR = new x();
  private List<WalkPath> a;
  private RouteSearch.WalkRouteQuery b;

  public WalkRouteResult()
  {
  }

  public WalkRouteResult(Parcel paramParcel)
  {
    this.a = paramParcel.createTypedArrayList(WalkPath.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public List<WalkPath> getPaths()
  {
    return this.a;
  }

  public RouteSearch.WalkRouteQuery getWalkQuery()
  {
    return this.b;
  }

  protected void setPaths(List<WalkPath> paramList)
  {
    this.a = paramList;
  }

  public void setWalkQuery(RouteSearch.WalkRouteQuery paramWalkRouteQuery)
  {
    this.b = paramWalkRouteQuery;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeTypedList(this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.WalkRouteResult
 * JD-Core Version:    0.6.2
 */