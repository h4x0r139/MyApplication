package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Doorway
  implements Parcelable
{
  public static final Parcelable.Creator<Doorway> CREATOR = new g();
  private String a;
  private LatLonPoint b;

  public Doorway()
  {
  }

  public Doorway(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = ((LatLonPoint)paramParcel.readParcelable(LatLonPoint.class.getClassLoader()));
  }

  public int describeContents()
  {
    return 0;
  }

  public LatLonPoint getLatLonPoint()
  {
    return this.b;
  }

  public String getName()
  {
    return this.a;
  }

  public void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    this.b = paramLatLonPoint;
  }

  public void setName(String paramString)
  {
    this.a = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeParcelable(this.b, paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.Doorway
 * JD-Core Version:    0.6.2
 */