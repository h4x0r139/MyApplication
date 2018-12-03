package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class RegeocodeRoad
  implements Parcelable
{
  public static final Parcelable.Creator<RegeocodeRoad> CREATOR = new e();
  private String a;
  private String b;
  private float c;
  private String d;
  private LatLonPoint e;

  public RegeocodeRoad()
  {
  }

  private RegeocodeRoad(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readFloat();
    this.d = paramParcel.readString();
    this.e = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
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
    return this.c;
  }

  public String getId()
  {
    return this.a;
  }

  public LatLonPoint getLatLngPoint()
  {
    return this.e;
  }

  public String getName()
  {
    return this.b;
  }

  protected void setDirection(String paramString)
  {
    this.d = paramString;
  }

  protected void setDistance(float paramFloat)
  {
    this.c = paramFloat;
  }

  protected void setId(String paramString)
  {
    this.a = paramString;
  }

  protected void setLatLngPoint(LatLonPoint paramLatLonPoint)
  {
    this.e = paramLatLonPoint;
  }

  protected void setName(String paramString)
  {
    this.b = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeFloat(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeValue(this.e);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeRoad
 * JD-Core Version:    0.6.2
 */