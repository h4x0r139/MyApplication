package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class DriveRouteResult extends RouteResult
  implements Parcelable
{
  public static final Parcelable.Creator<DriveRouteResult> CREATOR = new j();
  private List<DrivePath> a;
  private RouteSearch.DriveRouteQuery b;

  public DriveRouteResult()
  {
  }

  public DriveRouteResult(Parcel paramParcel)
  {
    this.a = paramParcel.createTypedArrayList(DrivePath.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public RouteSearch.DriveRouteQuery getDriveQuery()
  {
    return this.b;
  }

  public List<DrivePath> getPaths()
  {
    return this.a;
  }

  public void setDriveQuery(RouteSearch.DriveRouteQuery paramDriveRouteQuery)
  {
    this.b = paramDriveRouteQuery;
  }

  protected void setPaths(List<DrivePath> paramList)
  {
    this.a = paramList;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeTypedList(this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.DriveRouteResult
 * JD-Core Version:    0.6.2
 */