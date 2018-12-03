package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;

public class BusStep
  implements Parcelable
{
  public static final Parcelable.Creator<BusStep> CREATOR = new e();
  private RouteBusWalkItem a;
  private List<RouteBusLineItem> b;
  private Doorway c;
  private Doorway d;

  public BusStep()
  {
  }

  public BusStep(Parcel paramParcel)
  {
    this.a = ((RouteBusWalkItem)paramParcel.readParcelable(RouteBusWalkItem.class.getClassLoader()));
    this.b = paramParcel.createTypedArrayList(RouteBusLineItem.CREATOR);
    this.c = ((Doorway)paramParcel.readParcelable(Doorway.class.getClassLoader()));
    this.d = ((Doorway)paramParcel.readParcelable(Doorway.class.getClassLoader()));
  }

  public int describeContents()
  {
    return 0;
  }

  public RouteBusLineItem getBusLine()
  {
    if ((this.b == null) || (this.b.size() == 0))
      return null;
    return (RouteBusLineItem)this.b.get(0);
  }

  public Doorway getEntrance()
  {
    return this.c;
  }

  public Doorway getExit()
  {
    return this.d;
  }

  public RouteBusWalkItem getWalk()
  {
    return this.a;
  }

  public void setBusLine(RouteBusLineItem paramRouteBusLineItem)
  {
    if (this.b == null)
      return;
    if (this.b.size() == 0)
      this.b.add(paramRouteBusLineItem);
    this.b.set(0, paramRouteBusLineItem);
  }

  protected void setBusLines(List<RouteBusLineItem> paramList)
  {
    this.b = paramList;
  }

  public void setEntrance(Doorway paramDoorway)
  {
    this.c = paramDoorway;
  }

  public void setExit(Doorway paramDoorway)
  {
    this.d = paramDoorway;
  }

  public void setWalk(RouteBusWalkItem paramRouteBusWalkItem)
  {
    this.a = paramRouteBusWalkItem;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.a, paramInt);
    paramParcel.writeTypedList(this.b);
    paramParcel.writeParcelable(this.c, paramInt);
    paramParcel.writeParcelable(this.d, paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.BusStep
 * JD-Core Version:    0.6.2
 */