package com.amap.api.services.poisearch;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import java.util.ArrayList;
import java.util.List;

public final class PoiResult
{
  private int a;
  private ArrayList<PoiItem> b;
  private k c;

  private PoiResult(k paramk, ArrayList<PoiItem> paramArrayList)
  {
    this.c = paramk;
    this.a = a(paramk.b());
    this.b = paramArrayList;
  }

  private int a(int paramInt)
  {
    int i = this.c.a();
    int j = (-1 + (paramInt + i)) / i;
    if (j > 30)
      return 30;
    return j;
  }

  static PoiResult a(k paramk, ArrayList<PoiItem> paramArrayList)
  {
    return new PoiResult(paramk, paramArrayList);
  }

  public PoiSearch.SearchBound getBound()
  {
    return this.c.f();
  }

  public int getPageCount()
  {
    return this.a;
  }

  public ArrayList<PoiItem> getPois()
  {
    return this.b;
  }

  public PoiSearch.Query getQuery()
  {
    return this.c.e();
  }

  public List<SuggestionCity> getSearchSuggestionCitys()
  {
    return this.c.k();
  }

  public List<String> getSearchSuggestionKeywords()
  {
    return this.c.g();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.PoiResult
 * JD-Core Version:    0.6.2
 */