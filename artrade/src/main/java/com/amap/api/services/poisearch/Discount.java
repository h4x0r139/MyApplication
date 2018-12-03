package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class Discount
  implements Parcelable
{
  public static final Parcelable.Creator<Discount> CREATOR = new c();
  private String a;
  private String b;
  private Date c;
  private Date d;
  private int e;
  private List<Photo> f = new ArrayList();
  private String g;
  private String h;

  protected Discount()
  {
  }

  public Discount(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = com.amap.api.services.core.c.e(paramParcel.readString());
    this.d = com.amap.api.services.core.c.e(paramParcel.readString());
    this.e = paramParcel.readInt();
    this.f = paramParcel.createTypedArrayList(Photo.CREATOR);
    this.g = paramParcel.readString();
    this.h = paramParcel.readString();
  }

  protected void addPhotos(Photo paramPhoto)
  {
    this.f.add(paramPhoto);
  }

  public int describeContents()
  {
    return 0;
  }

  public String getDetail()
  {
    return this.b;
  }

  public Date getEndTime()
  {
    return this.d;
  }

  public List<Photo> getPhotos()
  {
    return this.f;
  }

  public String getProvider()
  {
    return this.h;
  }

  public int getSoldCount()
  {
    return this.e;
  }

  public Date getStartTime()
  {
    return this.c;
  }

  public String getTitle()
  {
    return this.a;
  }

  public String getUrl()
  {
    return this.g;
  }

  protected void initPhotos(List<Photo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      this.f.clear();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Photo localPhoto = (Photo)localIterator.next();
        this.f.add(localPhoto);
      }
    }
  }

  protected void setDetail(String paramString)
  {
    this.b = paramString;
  }

  protected void setEndTime(Date paramDate)
  {
    this.d = paramDate;
  }

  protected void setProvider(String paramString)
  {
    this.h = paramString;
  }

  protected void setSoldCount(int paramInt)
  {
    this.e = paramInt;
  }

  protected void setStartTime(Date paramDate)
  {
    this.c = paramDate;
  }

  protected void setTitle(String paramString)
  {
    this.a = paramString;
  }

  protected void setUrl(String paramString)
  {
    this.g = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(com.amap.api.services.core.c.a(this.c));
    paramParcel.writeString(com.amap.api.services.core.c.a(this.d));
    paramParcel.writeInt(this.e);
    paramParcel.writeTypedList(this.f);
    paramParcel.writeString(this.g);
    paramParcel.writeString(this.h);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.Discount
 * JD-Core Version:    0.6.2
 */