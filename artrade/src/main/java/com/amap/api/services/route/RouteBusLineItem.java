package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.List;

public class RouteBusLineItem extends BusLineItem
  implements Parcelable
{
  public static final Parcelable.Creator<RouteBusLineItem> CREATOR = new n();
  private BusStationItem a;
  private BusStationItem b;
  private List<LatLonPoint> c;
  private int d;
  private List<BusStationItem> e;
  private float f;

  public RouteBusLineItem()
  {
  }

  public RouteBusLineItem(Parcel paramParcel)
  {
    super(paramParcel);
    this.a = ((BusStationItem)paramParcel.readParcelable(BusStationItem.class.getClassLoader()));
    this.b = ((BusStationItem)paramParcel.readParcelable(BusStationItem.class.getClassLoader()));
    this.c = paramParcel.createTypedArrayList(LatLonPoint.CREATOR);
    this.d = paramParcel.readInt();
    this.e = paramParcel.createTypedArrayList(BusStationItem.CREATOR);
    this.f = paramParcel.readFloat();
  }

  public int describeContents()
  {
    return 0;
  }

  public BusStationItem getArrivalBusStation()
  {
    return this.b;
  }

  public BusStationItem getDepartureBusStation()
  {
    return this.a;
  }

  public float getDuration()
  {
    return this.f;
  }

  public int getPassStationNum()
  {
    return this.d;
  }

  public List<BusStationItem> getPassStations()
  {
    return this.e;
  }

  public List<LatLonPoint> getPolyline()
  {
    return this.c;
  }

  public void setArrivalBusStation(BusStationItem paramBusStationItem)
  {
    this.b = paramBusStationItem;
  }

  public void setDepartureBusStation(BusStationItem paramBusStationItem)
  {
    this.a = paramBusStationItem;
  }

  public void setDuration(float paramFloat)
  {
    this.f = paramFloat;
  }

  public void setPassStationNum(int paramInt)
  {
    this.d = paramInt;
  }

  public void setPassStations(List<BusStationItem> paramList)
  {
    this.e = paramList;
  }

  public void setPolyline(List<LatLonPoint> paramList)
  {
    this.c = paramList;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.a, paramInt);
    paramParcel.writeParcelable(this.b, paramInt);
    paramParcel.writeTypedList(this.c);
    paramParcel.writeInt(this.d);
    paramParcel.writeTypedList(this.e);
    paramParcel.writeFloat(this.f);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.RouteBusLineItem
 * JD-Core Version:    0.6.2
 */