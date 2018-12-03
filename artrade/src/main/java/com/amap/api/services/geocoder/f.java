package com.amap.api.services.geocoder;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.b;
import com.amap.api.services.core.c;
import com.amap.api.services.core.h;
import com.amap.api.services.road.Crossroad;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f extends h<RegeocodeQuery, RegeocodeAddress>
{
  public f(RegeocodeQuery paramRegeocodeQuery, Proxy paramProxy)
  {
    super(paramRegeocodeQuery, paramProxy);
  }

  private void a(JSONArray paramJSONArray, RegeocodeAddress paramRegeocodeAddress)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
    {
      Crossroad localCrossroad = new Crossroad();
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      localCrossroad.setId(a(localJSONObject, "id"));
      localCrossroad.setDirection(a(localJSONObject, "direction"));
      localCrossroad.setDistance(g(a(localJSONObject, "distance")));
      localCrossroad.setCenterPoint(b(localJSONObject, "location"));
      localCrossroad.setFirstRoadId(a(localJSONObject, "first_id"));
      localCrossroad.setFirstRoadName(a(localJSONObject, "first_name"));
      localCrossroad.setSecondRoadId(a(localJSONObject, "second_id"));
      localCrossroad.setSecondRoadName(a(localJSONObject, "second_name"));
      localArrayList.add(localCrossroad);
    }
    paramRegeocodeAddress.setCrossroads(localArrayList);
  }

  private void a(JSONObject paramJSONObject, RegeocodeAddress paramRegeocodeAddress)
    throws JSONException
  {
    paramRegeocodeAddress.setProvince(a(paramJSONObject, "province"));
    paramRegeocodeAddress.setCity(a(paramJSONObject, "city"));
    paramRegeocodeAddress.setDistrict(a(paramJSONObject, "district"));
    paramRegeocodeAddress.setTownship(a(paramJSONObject, "township"));
    paramRegeocodeAddress.setNeighborhood(a(paramJSONObject.getJSONObject("neighborhood"), "name"));
    paramRegeocodeAddress.setBuilding(a(paramJSONObject.getJSONObject("building"), "name"));
    StreetNumber localStreetNumber = new StreetNumber();
    JSONObject localJSONObject = paramJSONObject.getJSONObject("streetNumber");
    localStreetNumber.setStreet(a(localJSONObject, "street"));
    localStreetNumber.setNumber(a(localJSONObject, "number"));
    localStreetNumber.setLatLonPoint(b(localJSONObject, "location"));
    localStreetNumber.setDirection(a(localJSONObject, "direction"));
    localStreetNumber.setDistance(g(a(localJSONObject, "distance")));
    paramRegeocodeAddress.setStreetNumber(localStreetNumber);
  }

  private void b(JSONArray paramJSONArray, RegeocodeAddress paramRegeocodeAddress)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
    {
      RegeocodeRoad localRegeocodeRoad = new RegeocodeRoad();
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      localRegeocodeRoad.setId(a(localJSONObject, "id"));
      localRegeocodeRoad.setName(a(localJSONObject, "name"));
      localRegeocodeRoad.setLatLngPoint(b(localJSONObject, "location"));
      localRegeocodeRoad.setDirection(a(localJSONObject, "direction"));
      localRegeocodeRoad.setDistance(g(a(localJSONObject, "distance")));
      localArrayList.add(localRegeocodeRoad);
    }
    paramRegeocodeAddress.setRoads(localArrayList);
  }

  private void c(JSONArray paramJSONArray, RegeocodeAddress paramRegeocodeAddress)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      PoiItem localPoiItem = new PoiItem(a(localJSONObject, "id"), b(localJSONObject, "location"), a(localJSONObject, "name"), "");
      localPoiItem.setDirection(a(localJSONObject, "direction"));
      localPoiItem.setDistance(f(a(localJSONObject, "distance")));
      localPoiItem.setTel(a(localJSONObject, "tel"));
      localPoiItem.setTypeDes(a(localJSONObject, "type"));
      localArrayList.add(localPoiItem);
    }
    paramRegeocodeAddress.setPois(localArrayList);
  }

  protected RegeocodeAddress a(InputStream paramInputStream)
    throws AMapException
  {
    RegeocodeAddress localRegeocodeAddress = new RegeocodeAddress();
    try
    {
      str = new String(c.a(paramInputStream));
      if ((str == null) || (str.equals("")))
        return null;
    }
    catch (Exception localException)
    {
      String str;
      while (true)
      {
        localException.printStackTrace();
        str = null;
      }
      c.b(str);
      try
      {
        JSONObject localJSONObject = new JSONObject(str).getJSONObject("regeocode");
        localRegeocodeAddress.setFormatAddress(a(localJSONObject, "formatted_address"));
        a(localJSONObject.getJSONObject("addressComponent"), localRegeocodeAddress);
        c(localJSONObject.getJSONArray("pois"), localRegeocodeAddress);
        b(localJSONObject.getJSONArray("roads"), localRegeocodeAddress);
        a(localJSONObject.getJSONArray("roadinters"), localRegeocodeAddress);
        return localRegeocodeAddress;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localRegeocodeAddress;
  }

  protected String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json").append("&extensions=all").append("&location=").append(((RegeocodeQuery)this.b).getPoint().getLongitude()).append(",").append(((RegeocodeQuery)this.b).getPoint().getLatitude());
    localStringBuffer.append("&radius=").append(((RegeocodeQuery)this.b).getRadius());
    localStringBuffer.append("&coordsys=").append(((RegeocodeQuery)this.b).getLatLonType());
    localStringBuffer.append("&key=" + b.a);
    return localStringBuffer.toString();
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/geocode/regeo?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.f
 * JD-Core Version:    0.6.2
 */