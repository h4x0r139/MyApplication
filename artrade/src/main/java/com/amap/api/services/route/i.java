package com.amap.api.services.route;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.c;
import java.util.List;

public class i extends p
{
  private List<LatLonPoint> c;
  private List<List<LatLonPoint>> d;
  private String e;

  public i(RouteSearch.FromAndTo paramFromAndTo, int paramInt, List<LatLonPoint> paramList, List<List<LatLonPoint>> paramList1, String paramString)
  {
    super(paramFromAndTo, paramInt);
    this.c = paramList;
    this.d = paramList1;
    this.e = paramString;
  }

  public String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((this.c == null) || (this.c.size() == 0))
      return null;
    for (int i = 0; i < this.c.size(); i++)
    {
      LatLonPoint localLatLonPoint = (LatLonPoint)this.c.get(i);
      localStringBuffer.append(localLatLonPoint.getLongitude());
      localStringBuffer.append(",");
      localStringBuffer.append(localLatLonPoint.getLatitude());
      if (i < -1 + this.c.size())
        localStringBuffer.append(";");
    }
    return localStringBuffer.toString();
  }

  public boolean b()
  {
    return !c.a(a());
  }

  public String c()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((this.d == null) || (this.d.size() == 0))
      return null;
    for (int i = 0; i < this.d.size(); i++)
    {
      List localList = (List)this.d.get(i);
      for (int j = 0; j < localList.size(); j++)
      {
        LatLonPoint localLatLonPoint = (LatLonPoint)localList.get(j);
        localStringBuffer.append(localLatLonPoint.getLongitude());
        localStringBuffer.append(",");
        localStringBuffer.append(localLatLonPoint.getLatitude());
        if (j < -1 + localList.size())
          localStringBuffer.append(";");
      }
      if (i < -1 + this.d.size())
        localStringBuffer.append("|");
    }
    return localStringBuffer.toString();
  }

  public boolean d()
  {
    return !c.a(c());
  }

  public String e()
  {
    return this.e;
  }

  public boolean f()
  {
    return !c.a(e());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.i
 * JD-Core Version:    0.6.2
 */