package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;

public final class GeocodeAddress
  implements Parcelable
{
  public static final Parcelable.Creator<GeocodeAddress> CREATOR = new a();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private LatLonPoint i;
  private String j;

  public GeocodeAddress()
  {
  }

  private GeocodeAddress(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
    this.g = paramParcel.readString();
    this.h = paramParcel.readString();
    this.i = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    this.j = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAdcode()
  {
    return this.h;
  }

  public String getBuilding()
  {
    return this.g;
  }

  public String getCity()
  {
    return this.c;
  }

  public String getDistrict()
  {
    return this.d;
  }

  public String getFormatAddress()
  {
    return this.a;
  }

  public LatLonPoint getLatLonPoint()
  {
    return this.i;
  }

  public String getLevel()
  {
    return this.j;
  }

  public String getNeighborhood()
  {
    return this.f;
  }

  public String getProvince()
  {
    return this.b;
  }

  public String getTownship()
  {
    return this.e;
  }

  protected void setAdcode(String paramString)
  {
    this.h = paramString;
  }

  protected void setBuilding(String paramString)
  {
    this.g = paramString;
  }

  protected void setCity(String paramString)
  {
    this.c = paramString;
  }

  protected void setDistrict(String paramString)
  {
    this.d = paramString;
  }

  protected void setFormatAddress(String paramString)
  {
    this.a = paramString;
  }

  protected void setLatLonPoint(LatLonPoint paramLatLonPoint)
  {
    this.i = paramLatLonPoint;
  }

  protected void setLevel(String paramString)
  {
    this.j = paramString;
  }

  protected void setNeighborhood(String paramString)
  {
    this.f = paramString;
  }

  protected void setProvince(String paramString)
  {
    this.b = paramString;
  }

  protected void setTownship(String paramString)
  {
    this.e = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeString(this.f);
    paramParcel.writeString(this.g);
    paramParcel.writeString(this.h);
    paramParcel.writeValue(this.i);
    paramParcel.writeString(this.j);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeAddress
 * JD-Core Version:    0.6.2
 */