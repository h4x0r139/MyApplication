package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class District
  implements Parcelable
{
  public static final Parcelable.Creator<District> CREATOR = new f();
  private String a;
  private String b;

  public District()
  {
  }

  public District(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getDistrictAdcode()
  {
    return this.b;
  }

  public String getDistrictName()
  {
    return this.a;
  }

  public void setDistrictAdcode(String paramString)
  {
    this.b = paramString;
  }

  public void setDistrictName(String paramString)
  {
    this.a = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.District
 * JD-Core Version:    0.6.2
 */