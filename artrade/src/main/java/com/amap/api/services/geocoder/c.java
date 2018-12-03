package com.amap.api.services.geocoder;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.b;
import com.amap.api.services.core.h;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class c extends h<GeocodeQuery, ArrayList<GeocodeAddress>>
{
  public int h = 0;

  public c(GeocodeQuery paramGeocodeQuery, Proxy paramProxy)
  {
    super(paramGeocodeQuery, paramProxy);
  }

  protected String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json").append("&address=").append(d(((GeocodeQuery)this.b).getLocationName()));
    String str1 = ((GeocodeQuery)this.b).getCity();
    if (!e(str1))
    {
      String str2 = d(str1);
      localStringBuffer.append("&city=").append(str2);
    }
    localStringBuffer.append("&key=" + b.a);
    return localStringBuffer.toString();
  }

  protected ArrayList<GeocodeAddress> a(InputStream paramInputStream)
    throws AMapException
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      str = new String(com.amap.api.services.core.c.a(paramInputStream));
      if ((str == null) || (str.equals("")))
      {
        localArrayList = null;
        return localArrayList;
      }
    }
    catch (Exception localException2)
    {
      while (true)
      {
        localException2.printStackTrace();
        String str = null;
        continue;
        com.amap.api.services.core.c.b(str);
        try
        {
          JSONObject localJSONObject1 = new JSONObject(str);
          if ((localJSONObject1.has("count")) && (localJSONObject1.getInt("count") > 0))
          {
            JSONArray localJSONArray = localJSONObject1.getJSONArray("geocodes");
            for (int i = 0; i < localJSONArray.length(); i++)
            {
              JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
              GeocodeAddress localGeocodeAddress = new GeocodeAddress();
              localGeocodeAddress.setFormatAddress(a(localJSONObject2, "formatted_address"));
              localGeocodeAddress.setProvince(a(localJSONObject2, "province"));
              localGeocodeAddress.setCity(a(localJSONObject2, "city"));
              localGeocodeAddress.setDistrict(a(localJSONObject2, "district"));
              localGeocodeAddress.setTownship(a(localJSONObject2, "township"));
              localGeocodeAddress.setNeighborhood(a(localJSONObject2.getJSONObject("neighborhood"), "name"));
              localGeocodeAddress.setBuilding(a(localJSONObject2.getJSONObject("building"), "name"));
              localGeocodeAddress.setAdcode(a(localJSONObject2, "adcode"));
              localGeocodeAddress.setLatLonPoint(b(localJSONObject2, "location"));
              localGeocodeAddress.setLevel(a(localJSONObject2, "level"));
              localArrayList.add(localGeocodeAddress);
            }
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return localArrayList;
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
      }
    }
    return localArrayList;
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/geocode/geo?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.c
 * JD-Core Version:    0.6.2
 */