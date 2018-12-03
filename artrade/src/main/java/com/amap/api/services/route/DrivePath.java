package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class DrivePath extends Path
  implements Parcelable
{
  public static final Parcelable.Creator<DrivePath> CREATOR = new h();
  private String a;
  private float b;
  private float c;
  private List<DriveStep> d;

  public DrivePath()
  {
  }

  public DrivePath(Parcel paramParcel)
  {
    super(paramParcel);
    this.a = paramParcel.readString();
    this.b = paramParcel.readFloat();
    this.c = paramParcel.readFloat();
    this.d = paramParcel.createTypedArrayList(DriveStep.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public List<DriveStep> getSteps()
  {
    return this.d;
  }

  public String getStrategy()
  {
    return this.a;
  }

  public float getTollDistance()
  {
    return this.c;
  }

  public float getTolls()
  {
    return this.b;
  }

  public void setSteps(List<DriveStep> paramList)
  {
    this.d = paramList;
  }

  public void setStrategy(String paramString)
  {
    this.a = paramString;
  }

  public void setTollDistance(float paramFloat)
  {
    this.c = paramFloat;
  }

  public void setTolls(float paramFloat)
  {
    this.b = paramFloat;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.a);
    paramParcel.writeFloat(this.b);
    paramParcel.writeFloat(this.c);
    paramParcel.writeTypedList(this.d);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.DrivePath
 * JD-Core Version:    0.6.2
 */