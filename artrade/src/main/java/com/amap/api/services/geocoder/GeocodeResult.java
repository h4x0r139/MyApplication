package com.amap.api.services.geocoder;

import java.util.List;

public class GeocodeResult
{
  private GeocodeQuery a;
  private List<GeocodeAddress> b;

  public GeocodeResult(GeocodeQuery paramGeocodeQuery, List<GeocodeAddress> paramList)
  {
    this.a = paramGeocodeQuery;
    this.b = paramList;
  }

  public List<GeocodeAddress> getGeocodeAddressList()
  {
    return this.b;
  }

  public GeocodeQuery getGeocodeQuery()
  {
    return this.a;
  }

  public void setGeocodeAddressList(List<GeocodeAddress> paramList)
  {
    this.b = paramList;
  }

  public void setGeocodeQuery(GeocodeQuery paramGeocodeQuery)
  {
    this.a = paramGeocodeQuery;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeResult
 * JD-Core Version:    0.6.2
 */