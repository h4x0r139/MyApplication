package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Tip
  implements Parcelable
{
  public static final Parcelable.Creator<Tip> CREATOR = new d();
  private String a;
  private String b;
  private String c;

  public Tip()
  {
  }

  private Tip(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.c = paramParcel.readString();
    this.b = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAdcode()
  {
    return this.c;
  }

  public String getDistrict()
  {
    return this.b;
  }

  public String getName()
  {
    return this.a;
  }

  protected void setAdcode(String paramString)
  {
    this.c = paramString;
  }

  protected void setDistrict(String paramString)
  {
    this.b = paramString;
  }

  protected void setName(String paramString)
  {
    this.a = paramString;
  }

  public String toString()
  {
    return "name:" + this.a + " district:" + this.b + " adcode:" + this.c;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.b);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.help.Tip
 * JD-Core Version:    0.6.2
 */