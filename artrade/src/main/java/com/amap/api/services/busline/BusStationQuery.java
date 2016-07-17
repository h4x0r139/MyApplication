package com.amap.api.services.busline;

import com.amap.api.services.core.c;

public class BusStationQuery
{
  private String a;
  private String b;
  private int c = 10;
  private int d = 0;

  public BusStationQuery(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
    if (!a())
      throw new IllegalArgumentException("Empty query");
  }

  private boolean a()
  {
    return !c.a(this.a);
  }

  protected BusStationQuery clone()
  {
    BusStationQuery localBusStationQuery = new BusStationQuery(this.a, this.b);
    localBusStationQuery.setPageNumber(this.d);
    localBusStationQuery.setPageSize(this.c);
    return localBusStationQuery;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject != null)
    {
      boolean bool2 = paramObject instanceof BusStationQuery;
      bool1 = false;
      if (bool2)
      {
        BusStationQuery localBusStationQuery = (BusStationQuery)paramObject;
        boolean bool3 = localBusStationQuery.getCity().equals(this.b);
        bool1 = false;
        if (bool3)
        {
          boolean bool4 = localBusStationQuery.getQueryString().equals(this.a);
          bool1 = false;
          if (bool4)
          {
            int i = localBusStationQuery.getPageNumber();
            int j = this.d;
            bool1 = false;
            if (i == j)
            {
              int k = localBusStationQuery.getPageSize();
              int m = this.c;
              bool1 = false;
              if (k == m)
                bool1 = true;
            }
          }
        }
      }
    }
    return bool1;
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
    int i = 20;
    if (paramInt > i);
    while (true)
    {
      if (i <= 0)
        i = 10;
      this.c = i;
      return;
      i = paramInt;
    }
  }

  public void setQueryString(String paramString)
  {
    this.a = paramString;
  }

  protected boolean weakEquals(BusStationQuery paramBusStationQuery)
  {
    boolean bool1 = paramBusStationQuery.getCity().equals(this.b);
    boolean bool2 = false;
    if (bool1)
    {
      boolean bool3 = paramBusStationQuery.getQueryString().equals(this.a);
      bool2 = false;
      if (bool3)
      {
        int i = paramBusStationQuery.getPageSize();
        int j = this.c;
        bool2 = false;
        if (i == j)
          bool2 = true;
      }
    }
    return bool2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.BusStationQuery
 * JD-Core Version:    0.6.2
 */