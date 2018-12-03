package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.road.Crossroad;
import com.amap.api.services.road.Road;
import java.util.List;

public final class RegeocodeAddress
  implements Parcelable
{
  public static final Parcelable.Creator<RegeocodeAddress> CREATOR = new d();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private StreetNumber h;
  private List<RegeocodeRoad> i;
  private List<Crossroad> j;
  private List<PoiItem> k;

  public RegeocodeAddress()
  {
  }

  private RegeocodeAddress(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
    this.g = paramParcel.readString();
    this.h = ((StreetNumber)paramParcel.readValue(StreetNumber.class.getClassLoader()));
    this.i = paramParcel.readArrayList(Road.class.getClassLoader());
    this.j = paramParcel.readArrayList(Crossroad.class.getClassLoader());
    this.k = paramParcel.readArrayList(PoiItem.class.getClassLoader());
  }

  public int describeContents()
  {
    return 0;
  }

  public String getBuilding()
  {
    return this.g;
  }

  public String getCity()
  {
    return this.c;
  }

  public List<Crossroad> getCrossroads()
  {
    return this.j;
  }

  public String getDistrict()
  {
    return this.d;
  }

  public String getFormatAddress()
  {
    return this.a;
  }

  public String getNeighborhood()
  {
    return this.f;
  }

  public List<PoiItem> getPois()
  {
    return this.k;
  }

  public String getProvince()
  {
    return this.b;
  }

  public List<RegeocodeRoad> getRoads()
  {
    return this.i;
  }

  public StreetNumber getStreetNumber()
  {
    return this.h;
  }

  public String getTownship()
  {
    return this.e;
  }

  protected void setBuilding(String paramString)
  {
    this.g = paramString;
  }

  protected void setCity(String paramString)
  {
    this.c = paramString;
  }

  protected void setCrossroads(List<Crossroad> paramList)
  {
    this.j = paramList;
  }

  protected void setDistrict(String paramString)
  {
    this.d = paramString;
  }

  protected void setFormatAddress(String paramString)
  {
    this.a = paramString;
  }

  protected void setNeighborhood(String paramString)
  {
    this.f = paramString;
  }

  protected void setPois(List<PoiItem> paramList)
  {
    this.k = paramList;
  }

  protected void setProvince(String paramString)
  {
    this.b = paramString;
  }

  protected void setRoads(List<RegeocodeRoad> paramList)
  {
    this.i = paramList;
  }

  protected void setStreetNumber(StreetNumber paramStreetNumber)
  {
    this.h = paramStreetNumber;
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
    paramParcel.writeValue(this.h);
    paramParcel.writeList(this.i);
    paramParcel.writeList(this.j);
    paramParcel.writeList(this.k);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeAddress
 * JD-Core Version:    0.6.2
 */