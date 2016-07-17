package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Crossroad extends Road
  implements Parcelable
{
  public static final Parcelable.Creator<Crossroad> CREATOR = new a();
  private float a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;

  public Crossroad()
  {
  }

  private Crossroad(Parcel paramParcel)
  {
    this.a = paramParcel.readFloat();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getDirection()
  {
    return this.b;
  }

  public float getDistance()
  {
    return this.a;
  }

  public String getFirstRoadId()
  {
    return this.c;
  }

  public String getFirstRoadName()
  {
    return this.d;
  }

  public String getSecondRoadId()
  {
    return this.e;
  }

  public String getSecondRoadName()
  {
    return this.f;
  }

  public void setDirection(String paramString)
  {
    this.b = paramString;
  }

  public void setDistance(float paramFloat)
  {
    this.a = paramFloat;
  }

  public void setFirstRoadId(String paramString)
  {
    this.c = paramString;
  }

  public void setFirstRoadName(String paramString)
  {
    this.d = paramString;
  }

  public void setSecondRoadId(String paramString)
  {
    this.e = paramString;
  }

  public void setSecondRoadName(String paramString)
  {
    this.f = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeString(this.f);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.road.Crossroad
 * JD-Core Version:    0.6.2
 */