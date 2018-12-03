package com.amap.api.services.geocoder;

import com.amap.api.services.core.LatLonPoint;

public class RegeocodeQuery
{
  private LatLonPoint a;
  private float b;
  private String c = GeocodeSearch.AMAP;

  public RegeocodeQuery(LatLonPoint paramLatLonPoint, float paramFloat, String paramString)
  {
    this.a = paramLatLonPoint;
    this.b = paramFloat;
    setLatLonType(paramString);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject != null)
    {
      boolean bool2 = paramObject instanceof RegeocodeQuery;
      bool1 = false;
      if (bool2)
      {
        RegeocodeQuery localRegeocodeQuery = (RegeocodeQuery)paramObject;
        boolean bool3 = localRegeocodeQuery.getLatLonType().equals(this.c);
        bool1 = false;
        if (bool3)
        {
          boolean bool4 = localRegeocodeQuery.getPoint().equals(this.a);
          bool1 = false;
          if (bool4)
          {
            boolean bool5 = localRegeocodeQuery.getRadius() < this.b;
            bool1 = false;
            if (!bool5)
              bool1 = true;
          }
        }
      }
    }
    return bool1;
  }

  public String getLatLonType()
  {
    return this.c;
  }

  public LatLonPoint getPoint()
  {
    return this.a;
  }

  public float getRadius()
  {
    return this.b;
  }

  public void setLatLonType(String paramString)
  {
    if ((paramString != null) && ((paramString.equals(GeocodeSearch.AMAP)) || (paramString.equals(GeocodeSearch.GPS))))
      this.c = paramString;
  }

  public void setPoint(LatLonPoint paramLatLonPoint)
  {
    this.a = paramLatLonPoint;
  }

  public void setRadius(float paramFloat)
  {
    this.b = paramFloat;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeQuery
 * JD-Core Version:    0.6.2
 */