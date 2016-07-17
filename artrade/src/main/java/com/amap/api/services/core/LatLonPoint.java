package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class LatLonPoint
  implements Parcelable
{
  public static final Parcelable.Creator<LatLonPoint> CREATOR = new f();
  private double a;
  private double b;

  public LatLonPoint(double paramDouble1, double paramDouble2)
  {
    this.a = paramDouble1;
    this.b = paramDouble2;
  }

  private LatLonPoint(Parcel paramParcel)
  {
    this.a = paramParcel.readDouble();
    this.b = paramParcel.readDouble();
  }

  public LatLonPoint Copy()
  {
    return new LatLonPoint(this.a, this.b);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    LatLonPoint localLatLonPoint;
    do
    {
      do
        return false;
      while (paramObject.getClass() != getClass());
      localLatLonPoint = (LatLonPoint)paramObject;
    }
    while ((this.a != localLatLonPoint.a) || (this.b != localLatLonPoint.b));
    return true;
  }

  public double getLatitude()
  {
    return this.a;
  }

  public double getLongitude()
  {
    return this.b;
  }

  public void setLatitude(double paramDouble)
  {
    this.a = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.b = paramDouble;
  }

  public String toString()
  {
    return "" + this.a + "," + this.b;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeDouble(this.a);
    paramParcel.writeDouble(this.b);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.LatLonPoint
 * JD-Core Version:    0.6.2
 */