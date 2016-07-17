package com.amap.api.services.road;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public class Road
  implements Parcelable
{
  public static final Parcelable.Creator<Road> CREATOR = new b();
  private String a;
  private String b;
  private String c;
  private float d;
  private String e;
  private LatLonPoint f;

  protected Road()
  {
  }

  private Road(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readFloat();
    this.e = paramParcel.readString();
    this.f = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
  }

  public Road(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public LatLonPoint getCenterPoint()
  {
    return this.f;
  }

  public String getCityCode()
  {
    return this.c;
  }

  public String getId()
  {
    return this.a;
  }

  public String getName()
  {
    return this.b;
  }

  public float getRoadWidth()
  {
    return this.d;
  }

  public String getType()
  {
    return this.e;
  }

  public void setCenterPoint(LatLonPoint paramLatLonPoint)
  {
    this.f = paramLatLonPoint;
  }

  protected void setCityCode(String paramString)
  {
    this.c = paramString;
  }

  public void setId(String paramString)
  {
    this.a = paramString;
  }

  protected void setName(String paramString)
  {
    this.b = paramString;
  }

  protected void setRoadWidth(float paramFloat)
  {
    this.d = paramFloat;
  }

  protected void setType(String paramString)
  {
    this.e = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeFloat(this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeValue(this.f);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.road.Road
 * JD-Core Version:    0.6.2
 */