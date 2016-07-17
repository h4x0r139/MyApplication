package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class WalkPath extends Path
  implements Parcelable
{
  public static final Parcelable.Creator<WalkPath> CREATOR = new v();
  private List<WalkStep> a;

  public WalkPath()
  {
  }

  public WalkPath(Parcel paramParcel)
  {
    super(paramParcel);
    this.a = paramParcel.createTypedArrayList(WalkStep.CREATOR);
  }

  public int describeContents()
  {
    return 0;
  }

  public List<WalkStep> getSteps()
  {
    return this.a;
  }

  public void setSteps(List<WalkStep> paramList)
  {
    this.a = paramList;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeTypedList(this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.WalkPath
 * JD-Core Version:    0.6.2
 */