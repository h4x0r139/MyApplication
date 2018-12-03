package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public final class Dining
  implements Parcelable
{
  public static final Parcelable.Creator<Dining> CREATOR = new b();
  private boolean a;
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
  private String l;
  private String m;
  private String n;
  private String o;
  private String p;
  private String q;
  private String r;
  private String s;
  private List<Photo> t;

  protected Dining()
  {
  }

  public Dining(Parcel paramParcel)
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
    this.h = paramParcel.readString();
    this.i = paramParcel.readString();
    this.j = paramParcel.readString();
    this.k = paramParcel.readString();
    this.l = paramParcel.readString();
    this.m = paramParcel.readString();
    this.n = paramParcel.readString();
    this.o = paramParcel.readString();
    this.p = paramParcel.readString();
    this.q = paramParcel.readString();
    this.r = paramParcel.readString();
    this.s = paramParcel.readString();
    this.t = paramParcel.createTypedArrayList(Photo.CREATOR);
  }

  void a(String paramString)
  {
    this.b = paramString;
  }

  void a(List<Photo> paramList)
  {
    this.t = paramList;
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

  void g(String paramString)
  {
    this.h = paramString;
  }

  public String getAddition()
  {
    return this.s;
  }

  public String getAtmosphere()
  {
    return this.m;
  }

  public String getCost()
  {
    return this.k;
  }

  public String getCpRating()
  {
    return this.f;
  }

  public String getCuisines()
  {
    return this.b;
  }

  public String getDeepsrc()
  {
    return this.g;
  }

  public String getEnvironmentRating()
  {
    return this.i;
  }

  public String getIntro()
  {
    return this.d;
  }

  public String getOpentime()
  {
    return this.r;
  }

  public String getOpentimeGDF()
  {
    return this.q;
  }

  public String getOrderinAppUrl()
  {
    return this.p;
  }

  public String getOrderingWapUrl()
  {
    return this.n;
  }

  public String getOrderingWebUrl()
  {
    return this.o;
  }

  public List<Photo> getPhotos()
  {
    return this.t;
  }

  public String getRating()
  {
    return this.e;
  }

  public String getRecommend()
  {
    return this.l;
  }

  public String getServiceRating()
  {
    return this.j;
  }

  public String getTag()
  {
    return this.c;
  }

  public String getTasteRating()
  {
    return this.h;
  }

  void h(String paramString)
  {
    this.i = paramString;
  }

  void i(String paramString)
  {
    this.j = paramString;
  }

  public boolean isMealOrdering()
  {
    return this.a;
  }

  void j(String paramString)
  {
    this.k = paramString;
  }

  void k(String paramString)
  {
    this.l = paramString;
  }

  void l(String paramString)
  {
    this.m = paramString;
  }

  void m(String paramString)
  {
    this.n = paramString;
  }

  void n(String paramString)
  {
    this.o = paramString;
  }

  void o(String paramString)
  {
    this.p = paramString;
  }

  void p(String paramString)
  {
    this.q = paramString;
  }

  void q(String paramString)
  {
    this.r = paramString;
  }

  void r(String paramString)
  {
    this.s = paramString;
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
    paramParcel.writeString(this.h);
    paramParcel.writeString(this.i);
    paramParcel.writeString(this.j);
    paramParcel.writeString(this.k);
    paramParcel.writeString(this.l);
    paramParcel.writeString(this.m);
    paramParcel.writeString(this.n);
    paramParcel.writeString(this.o);
    paramParcel.writeString(this.p);
    paramParcel.writeString(this.q);
    paramParcel.writeString(this.r);
    paramParcel.writeString(this.s);
    paramParcel.writeTypedList(this.t);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.Dining
 * JD-Core Version:    0.6.2
 */