package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class SearchCity
  implements Parcelable
{
  public static final Parcelable.Creator<SearchCity> CREATOR = new u();
  private String a;
  private String b;
  private String c;

  public SearchCity()
  {
  }

  public SearchCity(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getSearchCityAdCode()
  {
    return this.c;
  }

  public String getSearchCityName()
  {
    return this.a;
  }

  public String getSearchCitycode()
  {
    return this.b;
  }

  public void setSearchCityName(String paramString)
  {
    this.a = paramString;
  }

  public void setSearchCitycode(String paramString)
  {
    this.b = paramString;
  }

  public void setSearchCityhAdCode(String paramString)
  {
    this.c = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.SearchCity
 * JD-Core Version:    0.6.2
 */