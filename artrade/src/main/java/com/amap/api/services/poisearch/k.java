package com.amap.api.services.poisearch;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.core.b;
import com.amap.api.services.core.c;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class k extends g<l, ArrayList<PoiItem>>
{
  private int h = 1;
  private int i = 20;
  private int j = 0;
  private List<String> k = new ArrayList();
  private List<SuggestionCity> l = new ArrayList();

  public k(l paraml, Proxy paramProxy)
  {
    super(paraml, paramProxy);
  }

  private ArrayList<PoiItem> b(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject.has("pois"))
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray("pois");
      this.j = paramJSONObject.getInt("count");
      for (int m = 0; m < localJSONArray.length(); m++)
        localArrayList.add(a(localJSONArray.getJSONObject(m)));
    }
    return localArrayList;
    return localArrayList;
  }

  private void c(JSONObject paramJSONObject)
    throws JSONException, NumberFormatException
  {
    if (!paramJSONObject.has("cities"));
    while (true)
    {
      return;
      JSONArray localJSONArray = paramJSONObject.getJSONArray("cities");
      for (int m = 0; m < localJSONArray.length(); m++)
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(m);
        SuggestionCity localSuggestionCity = new SuggestionCity(a(localJSONObject, "name"), a(localJSONObject, "citycode"), a(localJSONObject, "adcode"), f(a(localJSONObject, "num")));
        this.l.add(localSuggestionCity);
      }
    }
  }

  private void d(JSONObject paramJSONObject)
    throws JSONException
  {
    if (!paramJSONObject.has("keywords"));
    while (true)
    {
      return;
      JSONArray localJSONArray = paramJSONObject.getJSONArray("keywords");
      for (int m = 0; m < localJSONArray.length(); m++)
        this.k.add(localJSONArray.getString(m));
    }
  }

  private String l()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("output=json");
    if (((l)this.b).b != null)
    {
      if (!((l)this.b).b.getShape().equals("Bound"))
        break label403;
      double d5 = c.a(((l)this.b).b.getCenter().getLongitude());
      double d6 = c.a(((l)this.b).b.getCenter().getLatitude());
      localStringBuilder.append("&location=").append(d5 + "," + d6);
      localStringBuilder.append("&radius=").append(((l)this.b).b.getRange());
      localStringBuilder.append("&sortrule=").append(m());
    }
    while (true)
    {
      String str1 = ((l)this.b).a.getCity();
      if (!e(str1))
      {
        String str4 = d(str1);
        localStringBuilder.append("&city=").append(str4);
      }
      if (!c.a(n()))
        localStringBuilder.append(n());
      String str2 = d(((l)this.b).a.getQueryString());
      localStringBuilder.append("&keywords=" + str2);
      localStringBuilder.append("&offset=" + this.i);
      localStringBuilder.append("&page=" + this.h);
      String str3 = d(((l)this.b).a.getCategory());
      localStringBuilder.append("&types=" + str3);
      localStringBuilder.append("&extensions=all");
      localStringBuilder.append("&key=" + b.a);
      return localStringBuilder.toString();
      label403: if (((l)this.b).b.getShape().equals("Rectangle"))
      {
        LatLonPoint localLatLonPoint1 = ((l)this.b).b.getLowerLeft();
        LatLonPoint localLatLonPoint2 = ((l)this.b).b.getUpperRight();
        double d1 = c.a(localLatLonPoint1.getLatitude());
        double d2 = c.a(localLatLonPoint1.getLongitude());
        double d3 = c.a(localLatLonPoint2.getLatitude());
        double d4 = c.a(localLatLonPoint2.getLongitude());
        localStringBuilder.append("&polygon=" + d2 + "," + d1 + ";" + d4 + "," + d3);
      }
      else if (((l)this.b).b.getShape().equals("Polygon"))
      {
        List localList = ((l)this.b).b.getPolyGonList();
        if ((localList != null) && (localList.size() > 0))
          localStringBuilder.append("&polygon=" + c.a(localList));
      }
    }
  }

  private String m()
  {
    if (((l)this.b).b.isDistanceSort())
      return "distance";
    return "weight";
  }

  private String n()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((((l)this.b).a.hasGroupBuyLimit()) && (((l)this.b).a.hasDiscountLimit()))
    {
      localStringBuffer.append("&filter=groupbuy:1|discount:1");
      return localStringBuffer.toString();
    }
    if (((l)this.b).a.hasGroupBuyLimit())
    {
      localStringBuffer.append("&filter=");
      localStringBuffer.append("groupbuy:1");
    }
    if (((l)this.b).a.hasDiscountLimit())
    {
      localStringBuffer.append("&filter=");
      localStringBuffer.append("discount:1");
    }
    return localStringBuffer.toString();
  }

  public int a()
  {
    return this.i;
  }

  public void a(int paramInt)
  {
    this.h = (paramInt + 1);
  }

  public int b()
  {
    return this.j;
  }

  public void b(int paramInt)
  {
    int m = 30;
    if (paramInt > m);
    for (int n = m; ; n = paramInt)
    {
      if (n <= 0);
      while (true)
      {
        this.i = m;
        return;
        m = n;
      }
    }
  }

  public ArrayList<PoiItem> c(InputStream paramInputStream)
    throws AMapException
  {
    String str = a(paramInputStream);
    Object localObject = null;
    if (str != null)
    {
      boolean bool = str.equals("");
      localObject = null;
      if (!bool)
        break label30;
    }
    while (true)
    {
      return localObject;
      label30: c.b(str);
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        localObject = b(localJSONObject1);
        if (localJSONObject1.has("suggestion"))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("suggestion");
          c(localJSONObject2);
          d(localJSONObject2);
          return localObject;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return localObject;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return localObject;
  }

  protected byte[] c()
  {
    return l().getBytes();
  }

  protected String d()
  {
    if (((l)this.b).b == null)
      return "http://restapi.amap.com/v3/place" + "/text?";
    if (((l)this.b).b.getShape().equals("Bound"))
      return "http://restapi.amap.com/v3/place" + "/around?";
    if ((((l)this.b).b.getShape().equals("Rectangle")) || (((l)this.b).b.getShape().equals("Polygon")))
      return "http://restapi.amap.com/v3/place" + "/polygon?";
    return "http://restapi.amap.com/v3/place";
  }

  public PoiSearch.Query e()
  {
    return ((l)this.b).a;
  }

  public PoiSearch.SearchBound f()
  {
    return ((l)this.b).b;
  }

  public List<String> g()
  {
    return this.k;
  }

  public List<SuggestionCity> k()
  {
    return this.l;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.k
 * JD-Core Version:    0.6.2
 */