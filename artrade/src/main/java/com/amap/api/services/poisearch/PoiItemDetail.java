package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoiItemDetail extends PoiItem
  implements Parcelable
{
  public static final Parcelable.Creator<PoiItemDetail> CREATOR = new h();
  Dining a;
  Hotel b;
  Cinema c;
  Scenic d;
  DeepType e;
  private List<Groupbuy> f = new ArrayList();
  private List<Discount> g = new ArrayList();

  private PoiItemDetail(Parcel paramParcel)
  {
    super(paramParcel);
    this.f = paramParcel.readArrayList(Groupbuy.class.getClassLoader());
    this.g = paramParcel.readArrayList(Discount.class.getClassLoader());
  }

  public PoiItemDetail(String paramString1, LatLonPoint paramLatLonPoint, String paramString2, String paramString3)
  {
    super(paramString1, paramLatLonPoint, paramString2, paramString3);
  }

  protected void addDiscount(Discount paramDiscount)
  {
    this.g.add(paramDiscount);
  }

  protected void addGroupbuy(Groupbuy paramGroupbuy)
  {
    this.f.add(paramGroupbuy);
  }

  public int describeContents()
  {
    return 0;
  }

  public Cinema getCinema()
  {
    return this.c;
  }

  public DeepType getDeepType()
  {
    return this.e;
  }

  public Dining getDining()
  {
    return this.a;
  }

  public List<Discount> getDiscounts()
  {
    return this.g;
  }

  public List<Groupbuy> getGroupbuys()
  {
    return this.f;
  }

  public Hotel getHotel()
  {
    return this.b;
  }

  public Scenic getScenic()
  {
    return this.d;
  }

  protected void initDiscounts(List<Discount> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      this.g.clear();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Discount localDiscount = (Discount)localIterator.next();
        this.g.add(localDiscount);
      }
    }
  }

  protected void initGroupbuys(List<Groupbuy> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0));
    while (true)
    {
      return;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        Groupbuy localGroupbuy = (Groupbuy)localIterator.next();
        this.f.add(localGroupbuy);
      }
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeList(this.f);
    paramParcel.writeList(this.g);
  }

  public static enum DeepType
  {
    static
    {
      DINING = new DeepType("DINING", 1);
      HOTEL = new DeepType("HOTEL", 2);
      CINEMA = new DeepType("CINEMA", 3);
      SCENIC = new DeepType("SCENIC", 4);
      DeepType[] arrayOfDeepType = new DeepType[5];
      arrayOfDeepType[0] = UNKNOWN;
      arrayOfDeepType[1] = DINING;
      arrayOfDeepType[2] = HOTEL;
      arrayOfDeepType[3] = CINEMA;
      arrayOfDeepType[4] = SCENIC;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.PoiItemDetail
 * JD-Core Version:    0.6.2
 */