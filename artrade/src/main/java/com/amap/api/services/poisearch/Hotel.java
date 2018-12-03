package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public final class Hotel
  implements Parcelable
{
  public static final Parcelable.Creator<Hotel> CREATOR = new e();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private String h;
  private String i;
  private String j;
  private String k;
  private List<Photo> l;

  protected Hotel()
  {
  }

  public Hotel(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
    this.g = paramParcel.readString();
    this.h = paramParcel.readString();
    this.i = paramParcel.readString();
    this.j = paramParcel.readString();
    this.k = paramParcel.readString();
    this.l = paramParcel.createTypedArrayList(Photo.CREATOR);
  }

  void a(String paramString)
  {
    this.a = paramString;
  }

  void a(List<Photo> paramList)
  {
    this.l = paramList;
  }

  void b(String paramString)
  {
    this.b = paramString;
  }

  void c(String paramString)
  {
    this.c = paramString;
  }

  void d(String paramString)
  {
    this.d = paramString;
  }

  public int describeContents()
  {
    return 0;
  }

  void e(String paramString)
  {
    this.e = paramString;
  }

  void f(String paramString)
  {
    this.f = paramString;
  }

  void g(String paramString)
  {
    this.g = paramString;
  }

  public String getAddition()
  {
    return this.j;
  }

  public String getDeepsrc()
  {
    return this.k;
  }

  public String getEnvironmentRating()
  {
    return this.g;
  }

  public String getFaciRating()
  {
    return this.e;
  }

  public String getHealthRating()
  {
    return this.f;
  }

  public String getIntro()
  {
    return this.c;
  }

  public String getLowestPrice()
  {
    return this.d;
  }

  public List<Photo> getPhotos()
  {
    return this.l;
  }

  public String getRating()
  {
    return this.a;
  }

  public String getServiceRating()
  {
    return this.h;
  }

  public String getStar()
  {
    return this.b;
  }

  public String getTraffic()
  {
    return this.i;
  }

  void h(String paramString)
  {
    this.h = paramString;
  }

  void i(String paramString)
  {
    this.i = paramString;
  }

  void j(String paramString)
  {
    this.j = paramString;
  }

  void k(String paramString)
  {
    this.k = paramString;
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
    paramParcel.writeString(this.i);
    paramParcel.writeString(this.j);
    paramParcel.writeString(this.k);
    paramParcel.writeTypedList(this.l);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.Hotel
 * JD-Core Version:    0.6.2
 */