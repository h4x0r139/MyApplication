package com.amap.api.services.busline;

import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class BusLineResult
{
  private int a;
  private ArrayList<BusLineItem> b;
  private BusLineQuery c;
  private List<String> d;
  private List<SuggestionCity> e;

  private BusLineResult(c paramc, ArrayList<?> paramArrayList)
  {
    this.c = ((BusLineQuery)paramc.a());
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

  static BusLineResult a(c paramc, ArrayList<?> paramArrayList)
  {
    return new BusLineResult(paramc, paramArrayList);
  }

  public List<BusLineItem> getBusLines()
  {
    return this.b;
  }

  public int getPageCount()
  {
    return this.a;
  }

  public BusLineQuery getQuery()
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
 * Qualified Name:     com.amap.api.services.busline.BusLineResult
 * JD-Core Version:    0.6.2
 */