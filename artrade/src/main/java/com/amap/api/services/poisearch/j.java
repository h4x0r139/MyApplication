package com.amap.api.services.poisearch;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.b;
import com.amap.api.services.core.c;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class j extends g<String, PoiItemDetail>
{
  public j(String paramString, Proxy paramProxy)
  {
    super(paramString, paramProxy);
  }

  private String a()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("id=").append((String)this.b);
    localStringBuilder.append("&output=json");
    localStringBuilder.append("&extensions=all");
    localStringBuilder.append("&key=" + b.a);
    return localStringBuilder.toString();
  }

  private void a(Discount paramDiscount, JSONObject paramJSONObject)
  {
    paramDiscount.initPhotos(d(paramJSONObject));
  }

  private void a(Groupbuy paramGroupbuy, JSONObject paramJSONObject)
    throws JSONException
  {
    paramGroupbuy.initPhotos(d(paramJSONObject));
  }

  private void a(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject == null);
    do
    {
      return;
      if (paramPoiItemDetail.isGroupbuyInfo())
        b(paramPoiItemDetail, paramJSONObject);
    }
    while (!paramPoiItemDetail.isDiscountInfo());
    c(paramPoiItemDetail, paramJSONObject);
  }

  private void a(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
    throws JSONException
  {
    if (paramJSONObject1 == null);
    String str;
    do
    {
      return;
      str = a(paramJSONObject1, "type");
      if (str.equalsIgnoreCase("hotel"))
        d(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
      if (str.equalsIgnoreCase("dining"))
        e(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
      if (str.equalsIgnoreCase("cinema"))
        c(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
    }
    while (!str.equalsIgnoreCase("scenic"));
    b(paramPoiItemDetail, paramJSONObject1, paramJSONObject2);
  }

  private PoiItemDetail b(JSONObject paramJSONObject)
    throws JSONException
  {
    PoiItemDetail localPoiItemDetail = null;
    if (paramJSONObject == null);
    JSONObject localJSONObject;
    do
    {
      JSONArray localJSONArray;
      int i;
      do
      {
        boolean bool;
        do
        {
          return localPoiItemDetail;
          bool = paramJSONObject.has("pois");
          localPoiItemDetail = null;
        }
        while (!bool);
        localJSONArray = paramJSONObject.optJSONArray("pois");
        i = localJSONArray.length();
        localPoiItemDetail = null;
      }
      while (i <= 0);
      localJSONObject = localJSONArray.getJSONObject(0);
      localPoiItemDetail = a(localJSONObject);
      if (localJSONObject.has("rich_content"))
        a(localPoiItemDetail, localJSONObject.optJSONObject("rich_content"));
    }
    while (!localJSONObject.has("deep_info"));
    a(localPoiItemDetail, localJSONObject.optJSONObject("deep_info"), localJSONObject);
    return localPoiItemDetail;
  }

  private void b(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject)
    throws JSONException
  {
    if (paramJSONObject == null);
    while (true)
    {
      return;
      if (paramJSONObject.has("groupbuys"))
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("groupbuys");
        for (int i = 0; i < localJSONArray.length(); i++)
        {
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          Groupbuy localGroupbuy = new Groupbuy();
          localGroupbuy.setTypeCode(a(localJSONObject, "typecode"));
          localGroupbuy.setTypeDes(a(localJSONObject, "type"));
          localGroupbuy.setDetail(a(localJSONObject, "detail"));
          localGroupbuy.setStartTime(c.c(a(localJSONObject, "start_time")));
          localGroupbuy.setEndTime(c.c(a(localJSONObject, "end_time")));
          localGroupbuy.setCount(f(a(localJSONObject, "num")));
          localGroupbuy.setSoldCount(f(a(localJSONObject, "sold_num")));
          localGroupbuy.setOriginalPrice(g(a(localJSONObject, "original_price")));
          localGroupbuy.setGroupbuyPrice(g(a(localJSONObject, "groupbuy_price")));
          localGroupbuy.setDiscount(g(a(localJSONObject, "discount")));
          localGroupbuy.setTicketAddress(a(localJSONObject, "ticket_address"));
          localGroupbuy.setTicketTel(a(localJSONObject, "ticket_tel"));
          localGroupbuy.setUrl(a(localJSONObject, "url"));
          localGroupbuy.setProvider(a(localJSONObject, "provider"));
          a(localGroupbuy, localJSONObject);
          paramPoiItemDetail.addGroupbuy(localGroupbuy);
        }
      }
    }
  }

  private void b(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
    throws JSONException
  {
    Scenic localScenic = new Scenic();
    localScenic.a(a(paramJSONObject1, "intro"));
    localScenic.b(a(paramJSONObject1, "rating"));
    localScenic.c(a(paramJSONObject1, "deepsrc"));
    localScenic.d(a(paramJSONObject1, "level"));
    localScenic.e(a(paramJSONObject1, "price"));
    localScenic.f(a(paramJSONObject1, "season"));
    localScenic.g(a(paramJSONObject1, "recommend"));
    localScenic.h(a(paramJSONObject1, "theme"));
    localScenic.i(a(paramJSONObject1, "ordering_wap_url"));
    localScenic.j(a(paramJSONObject1, "ordering_web_url"));
    localScenic.k(a(paramJSONObject1, "opentime_GDF"));
    localScenic.l(a(paramJSONObject1, "opentime"));
    localScenic.a(d(paramJSONObject1));
    paramPoiItemDetail.e = PoiItemDetail.DeepType.SCENIC;
    paramPoiItemDetail.d = localScenic;
  }

  private void c(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject)
    throws JSONException
  {
    if (!paramJSONObject.has("discounts"));
    while (true)
    {
      return;
      JSONArray localJSONArray = paramJSONObject.getJSONArray("discounts");
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        Discount localDiscount = new Discount();
        localDiscount.setTitle(a(localJSONObject, "title"));
        localDiscount.setDetail(a(localJSONObject, "detail"));
        localDiscount.setStartTime(c.c(a(localJSONObject, "start_time")));
        localDiscount.setEndTime(c.c(a(localJSONObject, "end_time")));
        localDiscount.setSoldCount(f(a(localJSONObject, "sold_num")));
        localDiscount.setUrl(a(localJSONObject, "url"));
        localDiscount.setProvider(a(localJSONObject, "provider"));
        a(localDiscount, localJSONObject);
        paramPoiItemDetail.addDiscount(localDiscount);
      }
    }
  }

  private void c(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
    throws JSONException
  {
    Cinema localCinema = new Cinema();
    localCinema.a(a(paramJSONObject1, "intro"));
    localCinema.b(a(paramJSONObject1, "rating"));
    localCinema.c(a(paramJSONObject1, "deepsrc"));
    localCinema.d(a(paramJSONObject1, "parking"));
    localCinema.e(a(paramJSONObject1, "opentime_GDF"));
    localCinema.f(a(paramJSONObject1, "opentime"));
    localCinema.a(d(paramJSONObject1));
    if (c(paramJSONObject2))
      localCinema.a(d(paramJSONObject2, "seat_ordering"));
    paramPoiItemDetail.e = PoiItemDetail.DeepType.CINEMA;
    paramPoiItemDetail.c = localCinema;
  }

  private boolean c(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null);
    while (!paramJSONObject.has("biz_ext"))
      return false;
    return true;
  }

  private List<Photo> d(JSONObject paramJSONObject)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null);
    while (true)
    {
      return localArrayList;
      if (paramJSONObject.has("photos"))
        try
        {
          JSONArray localJSONArray = paramJSONObject.optJSONArray("photos");
          for (int i = 0; i < localJSONArray.length(); i++)
          {
            JSONObject localJSONObject = localJSONArray.optJSONObject(i);
            Photo localPhoto = new Photo();
            localPhoto.setTitle(a(localJSONObject, "title"));
            localPhoto.setUrl(a(localJSONObject, "url"));
            localArrayList.add(localPhoto);
          }
        }
        catch (Exception localException)
        {
        }
    }
    return localArrayList;
  }

  private void d(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
    throws JSONException
  {
    Hotel localHotel = new Hotel();
    localHotel.b(a(paramJSONObject1, "star"));
    localHotel.c(a(paramJSONObject1, "intro"));
    localHotel.a(a(paramJSONObject1, "rating"));
    localHotel.d(a(paramJSONObject1, "lowest_price"));
    localHotel.k(a(paramJSONObject1, "deepsrc"));
    localHotel.e(a(paramJSONObject1, "faci_rating"));
    localHotel.f(a(paramJSONObject1, "health_rating"));
    localHotel.g(a(paramJSONObject1, "environment_rating"));
    localHotel.h(a(paramJSONObject1, "service_rating"));
    localHotel.i(a(paramJSONObject1, "traffic"));
    localHotel.j(a(paramJSONObject1, "addition"));
    localHotel.a(d(paramJSONObject1));
    paramPoiItemDetail.e = PoiItemDetail.DeepType.HOTEL;
    paramPoiItemDetail.b = localHotel;
  }

  private boolean d(JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    return j(a(paramJSONObject.optJSONObject("biz_ext"), paramString));
  }

  private void e(PoiItemDetail paramPoiItemDetail, JSONObject paramJSONObject1, JSONObject paramJSONObject2)
    throws JSONException
  {
    Dining localDining = new Dining();
    localDining.a(a(paramJSONObject1, "cuisines"));
    localDining.b(a(paramJSONObject1, "tag"));
    localDining.c(a(paramJSONObject1, "intro"));
    localDining.d(a(paramJSONObject1, "rating"));
    localDining.e(a(paramJSONObject1, "cp_rating"));
    localDining.f(a(paramJSONObject1, "deepsrc"));
    localDining.g(a(paramJSONObject1, "taste_rating"));
    localDining.h(a(paramJSONObject1, "environment_rating"));
    localDining.i(a(paramJSONObject1, "service_rating"));
    localDining.j(a(paramJSONObject1, "cost"));
    localDining.k(a(paramJSONObject1, "recommend"));
    localDining.l(a(paramJSONObject1, "atmosphere"));
    localDining.m(a(paramJSONObject1, "ordering_wap_url"));
    localDining.n(a(paramJSONObject1, "ordering_web_url"));
    localDining.o(a(paramJSONObject1, "ordering_app_url"));
    localDining.p(a(paramJSONObject1, "opentime_GDF"));
    localDining.q(a(paramJSONObject1, "opentime"));
    localDining.r(a(paramJSONObject1, "addition"));
    localDining.a(d(paramJSONObject1));
    if (c(paramJSONObject2))
      localDining.a(d(paramJSONObject2, "meal_ordering"));
    paramPoiItemDetail.e = PoiItemDetail.DeepType.DINING;
    paramPoiItemDetail.a = localDining;
  }

  private boolean j(String paramString)
  {
    try
    {
      int i;
      if (!paramString.equals(""))
      {
        i = Integer.parseInt(paramString);
        if (i != 0)
          break label23;
      }
      label23: 
      while (i != 1)
        return false;
      return true;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localNumberFormatException.printStackTrace();
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public PoiItemDetail c(InputStream paramInputStream)
    throws AMapException
  {
    String str = a(paramInputStream);
    if ((str == null) || (str.equals("")))
      return null;
    c.b(str);
    try
    {
      PoiItemDetail localPoiItemDetail = b(new JSONObject(str));
      return localPoiItemDetail;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
      return null;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/place/detail?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.j
 * JD-Core Version:    0.6.2
 */