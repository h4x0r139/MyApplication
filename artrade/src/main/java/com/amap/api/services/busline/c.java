package com.amap.api.services.busline;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.core.b;
import com.amap.api.services.core.h;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c<T> extends h<T, ArrayList<?>>
{
  private int h = 0;
  private List<String> i = new ArrayList();
  private List<SuggestionCity> j = new ArrayList();

  public c(T paramT, Proxy paramProxy)
  {
    super(paramT, paramProxy);
  }

  private void g(JSONObject paramJSONObject)
    throws JSONException, NumberFormatException
  {
    if (!paramJSONObject.has("cities"));
    while (true)
    {
      return;
      JSONArray localJSONArray = paramJSONObject.getJSONArray("cities");
      for (int k = 0; k < localJSONArray.length(); k++)
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(k);
        SuggestionCity localSuggestionCity = new SuggestionCity(a(localJSONObject, "name"), a(localJSONObject, "citycode"), a(localJSONObject, "adcode"), f(a(localJSONObject, "num")));
        this.j.add(localSuggestionCity);
      }
    }
  }

  private void h(JSONObject paramJSONObject)
    throws JSONException
  {
    if (!paramJSONObject.has("keywords"));
    while (true)
    {
      return;
      JSONArray localJSONArray = paramJSONObject.getJSONArray("keywords");
      for (int k = 0; k < localJSONArray.length(); k++)
        this.i.add(localJSONArray.getString(k));
    }
  }

  public T a()
  {
    return this.b;
  }

  protected ArrayList<?> a(InputStream paramInputStream)
    throws AMapException
  {
    try
    {
      String str = new String(com.amap.api.services.core.c.a(paramInputStream));
      if (str != null)
      {
        if (str.equals(""))
          return null;
        com.amap.api.services.core.c.b(str);
        JSONObject localJSONObject1 = new JSONObject(str);
        if (localJSONObject1.has("suggestion"))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("suggestion");
          g(localJSONObject2);
          h(localJSONObject2);
        }
        this.h = localJSONObject1.getInt("count");
        if ((this.b instanceof BusLineQuery))
          return a(localJSONObject1);
        ArrayList localArrayList = f(localJSONObject1);
        return localArrayList;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  protected ArrayList<BusLineItem> a(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    JSONArray localJSONArray = paramJSONObject.getJSONArray("buslines");
    int k = 0;
    if (k < localJSONArray.length())
    {
      JSONObject localJSONObject = localJSONArray.getJSONObject(k);
      if (((BusLineQuery)this.b).getCategory() == BusLineQuery.SearchType.BY_LINE_ID)
        localArrayList.add(b(localJSONObject));
      while (true)
      {
        k++;
        break;
        localArrayList.add(c(localJSONObject));
      }
    }
    return localArrayList;
  }

  public int b()
  {
    return this.h;
  }

  protected BusLineItem b(JSONObject paramJSONObject)
    throws JSONException
  {
    BusLineItem localBusLineItem = c(paramJSONObject);
    localBusLineItem.setFirstBusTime(com.amap.api.services.core.c.d(a(paramJSONObject, "start_time")));
    localBusLineItem.setLastBusTime(com.amap.api.services.core.c.d(a(paramJSONObject, "end_time")));
    localBusLineItem.setBusCompany(a(paramJSONObject, "company"));
    localBusLineItem.setDistance(g(a(paramJSONObject, "distance")));
    localBusLineItem.setBasicPrice(g(a(paramJSONObject, "basic_price")));
    localBusLineItem.setTotalPrice(g(a(paramJSONObject, "total_price")));
    localBusLineItem.setBounds(c(paramJSONObject, "bounds"));
    JSONArray localJSONArray = paramJSONObject.getJSONArray("busstops");
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < localJSONArray.length(); k++)
      localArrayList.add(e(localJSONArray.getJSONObject(k)));
    localBusLineItem.setBusStations(localArrayList);
    return localBusLineItem;
  }

  protected BusLineItem c(JSONObject paramJSONObject)
    throws JSONException
  {
    BusLineItem localBusLineItem = new BusLineItem();
    localBusLineItem.setBusLineId(a(paramJSONObject, "id"));
    localBusLineItem.setBusLineType(a(paramJSONObject, "type"));
    localBusLineItem.setBusLineName(a(paramJSONObject, "name"));
    localBusLineItem.setDirectionsCoordinates(c(paramJSONObject, "polyline"));
    localBusLineItem.setCityCode(a(paramJSONObject, "citycode"));
    localBusLineItem.setOriginatingStation(a(paramJSONObject, "start_stop"));
    localBusLineItem.setTerminalStation(a(paramJSONObject, "end_stop"));
    return localBusLineItem;
  }

  protected byte[] c()
  {
    return g().getBytes();
  }

  protected BusStationItem d(JSONObject paramJSONObject)
    throws JSONException
  {
    BusStationItem localBusStationItem = e(paramJSONObject);
    localBusStationItem.setAdCode(a(paramJSONObject, "adcode"));
    localBusStationItem.setCityCode(a(paramJSONObject, "citycode"));
    JSONArray localJSONArray = paramJSONObject.getJSONArray("buslines");
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < localJSONArray.length(); k++)
      localArrayList.add(c(localJSONArray.getJSONObject(k)));
    localBusStationItem.setBusLineItems(localArrayList);
    return localBusStationItem;
  }

  protected String d()
  {
    String str;
    if ((this.b instanceof BusLineQuery))
      if (((BusLineQuery)this.b).getCategory() == BusLineQuery.SearchType.BY_LINE_ID)
        str = "lineid";
    while (true)
    {
      return "http://restapi.amap.com/v3/bus/" + str + "?";
      if (((BusLineQuery)this.b).getCategory() == BusLineQuery.SearchType.BY_LINE_NAME)
      {
        str = "linename";
        continue;
        str = "stopname";
      }
      else
      {
        str = "";
      }
    }
  }

  protected BusStationItem e(JSONObject paramJSONObject)
    throws JSONException
  {
    BusStationItem localBusStationItem = new BusStationItem();
    localBusStationItem.setBusStationId(a(paramJSONObject, "id"));
    localBusStationItem.setLatLonPoint(b(paramJSONObject, "location"));
    localBusStationItem.setBusStationName(a(paramJSONObject, "name"));
    return localBusStationItem;
  }

  public List<String> e()
  {
    return this.i;
  }

  protected ArrayList<BusStationItem> f(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    this.h = paramJSONObject.getInt("count");
    JSONArray localJSONArray = paramJSONObject.getJSONArray("busstops");
    for (int k = 0; k < localJSONArray.length(); k++)
      localArrayList.add(d(localJSONArray.getJSONObject(k)));
    return localArrayList;
  }

  public List<SuggestionCity> f()
  {
    return this.j;
  }

  protected String g()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("output=json");
    BusLineQuery localBusLineQuery;
    if ((this.b instanceof BusLineQuery))
    {
      localBusLineQuery = (BusLineQuery)this.b;
      if (localBusLineQuery.getCategory() == BusLineQuery.SearchType.BY_LINE_ID)
      {
        localStringBuilder.append("&id=").append(d(((BusLineQuery)this.b).getQueryString()));
        localStringBuilder.append("&extensions=all");
      }
    }
    while (true)
    {
      localStringBuilder.append("&key=" + b.a);
      return localStringBuilder.toString();
      String str3 = localBusLineQuery.getCity();
      if (!e(str3))
      {
        String str4 = d(str3);
        localStringBuilder.append("&city=").append(str4);
      }
      localStringBuilder.append("&keywords=" + d(localBusLineQuery.getQueryString()));
      localStringBuilder.append("&offset=" + localBusLineQuery.getPageSize());
      localStringBuilder.append("&page=" + (1 + localBusLineQuery.getPageNumber()));
      continue;
      BusStationQuery localBusStationQuery = (BusStationQuery)this.b;
      String str1 = localBusStationQuery.getCity();
      if (!e(str1))
      {
        String str2 = d(str1);
        localStringBuilder.append("&city=").append(str2);
      }
      localStringBuilder.append("&keywords=" + d(localBusStationQuery.getQueryString()));
      localStringBuilder.append("&offset=" + localBusStationQuery.getPageSize());
      localStringBuilder.append("&page=" + (1 + localBusStationQuery.getPageNumber()));
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.c
 * JD-Core Version:    0.6.2
 */