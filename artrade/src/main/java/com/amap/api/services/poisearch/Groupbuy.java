package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.c;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public final class Groupbuy
  implements Parcelable
{
  public static final Parcelable.Creator<Groupbuy> CREATOR = new d();
  private String a;
  private String b;
  private String c;
  private Date d;
  private Date e;
  private int f;
  private int g;
  private float h;
  private float i;
  private float j;
  private String k;
  private String l;
  private List<Photo> m = new ArrayList();
  private String n;
  private String o;

  protected Groupbuy()
  {
  }

  public Groupbuy(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = c.e(paramParcel.readString());
    this.e = c.e(paramParcel.readString());
    this.f = paramParcel.readInt();
    this.g = paramParcel.readInt();
    this.h = paramParcel.readFloat();
    this.i = paramParcel.readFloat();
    this.j = paramParcel.readFloat();
    this.k = paramParcel.readString();
    this.l = paramParcel.readString();
    this.m = paramParcel.createTypedArrayList(Photo.CREATOR);
    this.n = paramParcel.readString();
    this.o = paramParcel.readString();
  }

  protected void addPhotos(Photo paramPhoto)
  {
    this.m.add(paramPhoto);
  }

  public int describeContents()
  {
    return 0;
  }

  public int getCount()
  {
    return this.f;
  }

  public String getDetail()
  {
    return this.c;
  }

  public float getDiscount()
  {
    return this.j;
  }

  public Date getEndTime()
  {
    return this.e;
  }

  public float getGroupbuyPrice()
  {
    return this.i;
  }

  public float getOriginalPrice()
  {
    return this.h;
  }

  public List<Photo> getPhotos()
  {
    return this.m;
  }

  public String getProvider()
  {
    return this.o;
  }

  public int getSoldCount()
  {
    return this.g;
  }

  public Date getStartTime()
  {
    return this.d;
  }

  public String getTicketAddress()
  {
    return this.k;
  }

  public String getTicketTel()
  {
    return this.l;
  }

  public String getTypeCode()
  {
    return this.a;
  }

  public String getTypeDes()
  {
    return this.b;
  }

  public String getUrl()
  {
    return this.n;
  }

  protected void initPhotos(List<Photo> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      this.m.clear();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Photo localPhoto = (Photo)localIterator.next();
        this.m.add(localPhoto);
      }
    }
  }

  protected void setCount(int paramInt)
  {
    this.f = paramInt;
  }

  protected void setDetail(String paramString)
  {
    this.c = paramString;
  }

  protected void setDiscount(float paramFloat)
  {
    this.j = paramFloat;
  }

  protected void setEndTime(Date paramDate)
  {
    this.e = paramDate;
  }

  protected void setGroupbuyPrice(float paramFloat)
  {
    this.i = paramFloat;
  }

  protected void setOriginalPrice(float paramFloat)
  {
    this.h = paramFloat;
  }

  protected void setProvider(String paramString)
  {
    this.o = paramString;
  }

  protected void setSoldCount(int paramInt)
  {
    this.g = paramInt;
  }

  protected void setStartTime(Date paramDate)
  {
    this.d = paramDate;
  }

  protected void setTicketAddress(String paramString)
  {
    this.k = paramString;
  }

  protected void setTicketTel(String paramString)
  {
    this.l = paramString;
  }

  protected void setTypeCode(String paramString)
  {
    this.a = paramString;
  }

  protected void setTypeDes(String paramString)
  {
    this.b = paramString;
  }

  protected void setUrl(String paramString)
  {
    this.n = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeString(c.a(this.d));
    paramParcel.writeString(c.a(this.e));
    paramParcel.writeInt(this.f);
    paramParcel.writeInt(this.g);
    paramParcel.writeFloat(this.h);
    paramParcel.writeFloat(this.i);
    paramParcel.writeFloat(this.j);
    paramParcel.writeString(this.k);
    paramParcel.writeString(this.l);
    paramParcel.writeTypedList(this.m);
    paramParcel.writeString(this.n);
    paramParcel.writeString(this.o);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.Groupbuy
 * JD-Core Version:    0.6.2
 */