package com.amap.api.services.geocoder;

public class GeocodeQuery
{
  private String a;
  private String b;

  public GeocodeQuery(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject != null)
    {
      boolean bool2 = paramObject instanceof GeocodeQuery;
      bool1 = false;
      if (bool2)
      {
        GeocodeQuery localGeocodeQuery = (GeocodeQuery)paramObject;
        boolean bool3 = localGeocodeQuery.getCity().equals(this.b);
        bool1 = false;
        if (bool3)
        {
          boolean bool4 = localGeocodeQuery.getLocationName().equals(this.a);
          bool1 = false;
          if (bool4)
            bool1 = true;
        }
      }
    }
    return bool1;
  }

  public String getCity()
  {
    return this.b;
  }

  public String getLocationName()
  {
    return this.a;
  }

  public void setCity(String paramString)
  {
    this.b = paramString;
  }

  public void setLocationName(String paramString)
  {
    this.a = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeQuery
 * JD-Core Version:    0.6.2
 */