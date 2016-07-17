package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PoiItem
  implements Parcelable
{
  public static final Parcelable.Creator<PoiItem> CREATOR = new g();
  private String a;
  private String b;
  private String c;
  private String d;
  private String e = "";
  private int f = -1;
  private LatLonPoint g;
  private LatLonPoint h;
  private String i;
  private String j;
  private String k;
  private boolean l;
  private boolean m;
  protected final LatLonPoint mPoint;
  protected final String mSnippet;
  protected final String mTitle;
  private String n;
  private String o;
  private String p;
  private String q;

  protected PoiItem(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.c = paramParcel.readString();
    this.b = paramParcel.readString();
    this.e = paramParcel.readString();
    this.f = paramParcel.readInt();
    this.mPoint = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    this.mTitle = paramParcel.readString();
    this.mSnippet = paramParcel.readString();
    this.d = paramParcel.readString();
    this.g = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    this.h = ((LatLonPoint)paramParcel.readValue(LatLonPoint.class.getClassLoader()));
    this.i = paramParcel.readString();
    this.j = paramParcel.readString();
    this.k = paramParcel.readString();
    boolean[] arrayOfBoolean = new boolean[2];
    paramParcel.readBooleanArray(arrayOfBoolean);
    this.l = arrayOfBoolean[0];
    this.m = arrayOfBoolean[1];
    this.n = paramParcel.readString();
    this.o = paramParcel.readString();
    this.p = paramParcel.readString();
    this.q = paramParcel.readString();
  }

  public PoiItem(String paramString1, LatLonPoint paramLatLonPoint, String paramString2, String paramString3)
  {
    this.a = paramString1;
    this.mPoint = paramLatLonPoint;
    this.mTitle = paramString2;
    this.mSnippet = paramString3;
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    PoiItem localPoiItem;
    do
    {
      do
        return false;
      while (paramObject.getClass() != getClass());
      localPoiItem = (PoiItem)paramObject;
    }
    while (this.a != localPoiItem.a);
    return true;
  }

  public String getAdCode()
  {
    return this.c;
  }

  public String getAdName()
  {
    return this.q;
  }

  public String getCityCode()
  {
    return this.d;
  }

  public String getCityName()
  {
    return this.p;
  }

  public String getDirection()
  {
    return this.n;
  }

  public int getDistance()
  {
    return this.f;
  }

  public String getEmail()
  {
    return this.k;
  }

  public LatLonPoint getEnter()
  {
    return this.g;
  }

  public LatLonPoint getExit()
  {
    return this.h;
  }

  public LatLonPoint getLatLonPoint()
  {
    return this.mPoint;
  }

  public String getPoiId()
  {
    return this.a;
  }

  public String getPostcode()
  {
    return this.j;
  }

  public String getProvinceName()
  {
    return this.o;
  }

  public String getSnippet()
  {
    return this.mSnippet;
  }

  public String getTel()
  {
    return this.b;
  }

  public String getTitle()
  {
    return this.mTitle;
  }

  public String getTypeDes()
  {
    return this.e;
  }

  public String getWebsite()
  {
    return this.i;
  }

  public int hashCode()
  {
    return this.a.hashCode();
  }

  public boolean isDiscountInfo()
  {
    return this.m;
  }

  public boolean isGroupbuyInfo()
  {
    return this.l;
  }

  public void setAdCode(String paramString)
  {
    this.c = paramString;
  }

  public void setAdName(String paramString)
  {
    this.q = paramString;
  }

  public void setCityCode(String paramString)
  {
    this.d = paramString;
  }

  public void setCityName(String paramString)
  {
    this.p = paramString;
  }

  public void setDirection(String paramString)
  {
    this.n = paramString;
  }

  public void setDiscountInfo(boolean paramBoolean)
  {
    this.m = paramBoolean;
  }

  public void setDistance(int paramInt)
  {
    this.f = paramInt;
  }

  public void setEmail(String paramString)
  {
    this.k = paramString;
  }

  public void setEnter(LatLonPoint paramLatLonPoint)
  {
    this.g = paramLatLonPoint;
  }

  public void setExit(LatLonPoint paramLatLonPoint)
  {
    this.h = paramLatLonPoint;
  }

  public void setGroupbuyInfo(boolean paramBoolean)
  {
    this.l = paramBoolean;
  }

  public void setPostcode(String paramString)
  {
    this.j = paramString;
  }

  public void setProvinceName(String paramString)
  {
    this.o = paramString;
  }

  public void setTel(String paramString)
  {
    this.b = paramString;
  }

  public void setTypeDes(String paramString)
  {
    this.e = paramString;
  }

  public void setWebsite(String paramString)
  {
    this.i = paramString;
  }

  public String toString()
  {
    return this.mTitle;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.c);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.e);
    paramParcel.writeInt(this.f);
    paramParcel.writeValue(this.mPoint);
    paramParcel.writeString(this.mTitle);
    paramParcel.writeString(this.mSnippet);
    paramParcel.writeString(this.d);
    paramParcel.writeValue(this.g);
    paramParcel.writeValue(this.h);
    paramParcel.writeString(this.i);
    paramParcel.writeString(this.j);
    paramParcel.writeString(this.k);
    boolean[] arrayOfBoolean = new boolean[2];
    arrayOfBoolean[0] = this.l;
    arrayOfBoolean[1] = this.m;
    paramParcel.writeBooleanArray(arrayOfBoolean);
    paramParcel.writeString(this.n);
    paramParcel.writeString(this.o);
    paramParcel.writeString(this.p);
    paramParcel.writeString(this.q);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.PoiItem
 * JD-Core Version:    0.6.2
 */