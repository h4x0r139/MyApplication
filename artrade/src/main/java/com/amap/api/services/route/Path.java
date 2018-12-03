package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Path
  implements Parcelable
{
  public static final Parcelable.Creator<Path> CREATOR = new m();
  private float a;
  private long b;

  public Path()
  {
  }

  public Path(Parcel paramParcel)
  {
    this.a = paramParcel.readFloat();
    this.b = paramParcel.readLong();
  }

  public int describeContents()
  {
    return 0;
  }

  public float getDistance()
  {
    return this.a;
  }

  public long getDuration()
  {
    return this.b;
  }

  public void setDistance(float paramFloat)
  {
    this.a = paramFloat;
  }

  public void setDuration(long paramLong)
  {
    this.b = paramLong;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(this.a);
    paramParcel.writeLong(this.b);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.Path
 * JD-Core Version:    0.6.2
 */