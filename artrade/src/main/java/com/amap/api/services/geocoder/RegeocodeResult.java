package com.amap.api.services.geocoder;

public class RegeocodeResult
{
  private RegeocodeQuery a;
  private RegeocodeAddress b;

  public RegeocodeResult(RegeocodeQuery paramRegeocodeQuery, RegeocodeAddress paramRegeocodeAddress)
  {
    this.a = paramRegeocodeQuery;
    this.b = paramRegeocodeAddress;
  }

  public RegeocodeAddress getRegeocodeAddress()
  {
    return this.b;
  }

  public RegeocodeQuery getRegeocodeQuery()
  {
    return this.a;
  }

  public void setRegeocodeAddress(RegeocodeAddress paramRegeocodeAddress)
  {
    this.b = paramRegeocodeAddress;
  }

  public void setRegeocodeQuery(RegeocodeQuery paramRegeocodeQuery)
  {
    this.a = paramRegeocodeQuery;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.RegeocodeResult
 * JD-Core Version:    0.6.2
 */