package com.amap.api.services.busline;

import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class BusStationResult
{
  private int a;
  private ArrayList<BusStationItem> b;
  private BusStationQuery c;
  private List<String> d;
  private List<SuggestionCity> e;

  private BusStationResult(c paramc, ArrayList<?> paramArrayList)
  {
    this.c = ((BusStationQuery)paramc.a());
    this.a = a(paramc.b());
    this.e = paramc.f();
    this.d = paramc.e();
    this.b = paramArrayList;
  }

  private int a(int paramInt)
  {
    int i = this.c.getPageSize();
    int j = (-1 + (paramInt + i)) / i;
    if (j > 30)
      return 30;
    return j;
  }

  static BusStationResult a(c paramc, ArrayList<?> paramArrayList)
  {
    return new BusStationResult(paramc, paramArrayList);
  }

  public List<BusStationItem> getBusStations()
  {
    return this.b;
  }

  public int getPageCount()
  {
    return this.a;
  }

  public BusStationQuery getQuery()
  {
    return this.c;
  }

  public List<SuggestionCity> getSearchSuggestionCities()
  {
    return this.e;
  }

  public List<String> getSearchSuggestionKeywords()
  {
    return this.d;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.BusStationResult
 * JD-Core Version:    0.6.2
 */