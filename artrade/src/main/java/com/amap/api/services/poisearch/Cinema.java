package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public final class Cinema
  implements Parcelable
{
  public static final Parcelable.Creator<Cinema> CREATOR = new a();
  private boolean a;
  private String b;
  private String c;
  private String d;
  private String e;
  private String f;
  private String g;
  private List<Photo> h;

  protected Cinema()
  {
  }

  public Cinema(Parcel paramParcel)
  {
    boolean[] arrayOfBoolean = new boolean[1];
    paramParcel.readBooleanArray(arrayOfBoolean);
    this.a = arrayOfBoolean[0];
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readString();
    this.g = paramParcel.readString();
    this.h = paramParcel.createTypedArrayList(Photo.CREATOR);
  }

  void a(String paramString)
  {
    this.b = paramString;
  }

  void a(List<Photo> paramList)
  {
    this.h = paramList;
  }

  void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  void b(String paramString)
  {
    this.c = paramString;
  }

  void c(String paramString)
  {
    this.d = paramString;
  }

  void d(String paramString)
  {
    this.e = paramString;
  }

  public int describeContents()
  {
    return 0;
  }

  void e(String paramString)
  {
    this.f = paramString;
  }

  void f(String paramString)
  {
    this.g = paramString;
  }

  public String getDeepsrc()
  {
    return this.d;
  }

  public String getIntro()
  {
    return this.b;
  }

  public String getOpentime()
  {
    return this.g;
  }

  public String getOpentimeGDF()
  {
    return this.f;
  }

  public String getParking()
  {
    return this.e;
  }

  public List<Photo> getPhotos()
  {
    return this.h;
  }

  public String getRating()
  {
    return this.c;
  }

  public boolean isSeatOrdering()
  {
    return this.a;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    boolean[] arrayOfBoolean = new boolean[1];
    arrayOfBoolean[0] = this.a;
    paramParcel.writeBooleanArray(arrayOfBoolean);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.d);
    paramParcel.writeString(this.e);
    paramParcel.writeString(this.f);
    paramParcel.writeString(this.g);
    paramParcel.writeTypedList(this.h);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.Cinema
 * JD-Core Version:    0.6.2
 */