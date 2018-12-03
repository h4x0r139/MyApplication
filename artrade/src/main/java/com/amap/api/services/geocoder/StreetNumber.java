package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class StreetNumber
  implements Parcelable
{
  public static final Parcelable.Creator<StreetNumber> CREATOR = new g();
  private String a;
  private String b;
  private LatLonPoint c;
  private String d;
  private float e;

  public StreetNumber()
  {
  }

  private StreetNumber(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    this.d = paramParcel.readString();
    this.e = paramParcel.readFloat();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getDirection()
  {
    return this.d;
  }

  public float getDistance()
  {
    return this.e;
  }

  public LatLonPoint getLatLonPoint()
  {
    return this.c;
  }

  public String getNumber()
  {
    return this.b;
  }

  public String getStreet()
  {
    return this.a;
  }

  protected void setDirection(String paramString)
  {
    this.d = paramString;
  }

  protected void setDistance(float paramFloat)
  {
    this.e = paramFloat;
  }

  protected void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    this.c = paramLatLonPoint;
  }

  protected void setNumber(String paramString)
  {
    this.b = paramString;
  }

  protected void setStreet(String paramString)
  {
    this.a = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeValue(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeFloat(this.e);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.StreetNumber
 * JD-Core Version:    0.6.2
 */