package com.amap.api.services.busline;

import com.amap.api.services.core.c;

public class BusLineQuery
{
  private String a;
  private String b;
  private int c = 10;
  private int d = 0;
  private SearchType e;

  public BusLineQuery(String paramString1, SearchType paramSearchType, String paramString2)
  {
    this.a = paramString1;
    this.e = paramSearchType;
    this.b = paramString2;
    if (!a())
      throw new IllegalArgumentException("Empty query");
  }

  private boolean a()
  {
    return !c.a(this.a);
  }

  protected BusLineQuery clone()
  {
    BusLineQuery localBusLineQuery = new BusLineQuery(this.a, this.e, this.b);
    localBusLineQuery.setPageNumber(this.d);
    localBusLineQuery.setPageSize(this.c);
    return localBusLineQuery;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject != null)
    {
      boolean bool2 = paramObject instanceof BusLineQuery;
      bool1 = false;
      if (bool2)
      {
        BusLineQuery localBusLineQuery = (BusLineQuery)paramObject;
        boolean bool3 = localBusLineQuery.getQueryString().equals(this.a);
        bool1 = false;
        if (bool3)
        {
          boolean bool4 = localBusLineQuery.getCity().equals(this.b);
          bool1 = false;
          if (bool4)
          {
            int i = localBusLineQuery.getPageNumber();
            int j = this.d;
            bool1 = false;
            if (i == j)
            {
              int k = localBusLineQuery.getPageSize();
              int m = this.c;
              bool1 = false;
              if (k == m)
              {
                int n = localBusLineQuery.getCategory().compareTo(this.e);
                bool1 = false;
                if (n == 0)
                  bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }

  public SearchType getCategory()
  {
    return this.e;
  }

  public String getCity()
  {
    return this.b;
  }

  public int getPageNumber()
  {
    return this.d;
  }

  public int getPageSize()
  {
    return this.c;
  }

  public String getQueryString()
  {
    return this.a;
  }

  public void setCategory(SearchType paramSearchType)
  {
    this.e = paramSearchType;
  }

  public void setCity(String paramString)
  {
    this.b = paramString;
  }

  public void setPageNumber(int paramInt)
  {
    this.d = paramInt;
  }

  public void setPageSize(int paramInt)
  {
    this.c = paramInt;
  }

  public void setQueryString(String paramString)
  {
    this.a = paramString;
  }

  protected boolean weakEquals(BusLineQuery paramBusLineQuery)
  {
    boolean bool1 = paramBusLineQuery.getQueryString().equals(this.a);
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = paramBusLineQuery.getCity().equals(this.b);
      bool2 = false;
      if (bool3)
      {
        int i = paramBusLineQuery.getPageSize();
        int j = this.c;
        bool2 = false;
        if (i == j)
        {
          int k = paramBusLineQuery.getCategory().compareTo(this.e);
          bool2 = false;
          if (k == 0)
            bool2 = true;
        }
      }
    }
    return bool2;
  }

  public static enum SearchType
  {
    static
    {
      SearchType[] arrayOfSearchType = new SearchType[2];
      arrayOfSearchType[0] = BY_LINE_ID;
      arrayOfSearchType[1] = BY_LINE_NAME;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.BusLineQuery
 * JD-Core Version:    0.6.2
 */