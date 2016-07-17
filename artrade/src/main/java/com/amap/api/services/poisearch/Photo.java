package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public final class Photo
  implements Parcelable
{
  public static final Parcelable.Creator<Photo> CREATOR = new f();
  private String a;
  private String b;

  protected Photo()
  {
  }

  public Photo(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
  }

  protected Photo(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  public int describeContents()
  {
    return 0;
  }

  public String getTitle()
  {
    return this.a;
  }

  public String getUrl()
  {
    return this.b;
  }

  protected void setTitle(String paramString)
  {
    this.a = paramString;
  }

  protected void setUrl(String paramString)
  {
    this.b = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.Photo
 * JD-Core Version:    0.6.2
 */