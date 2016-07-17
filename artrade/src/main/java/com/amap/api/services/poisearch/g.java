package com.amap.api.services.poisearch;

import com.amap.api.services.core.c;
import com.amap.api.services.core.h;
import java.io.InputStream;
import java.net.Proxy;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class g<T, V> extends h<T, V>
{
  public g(T paramT, Proxy paramProxy)
  {
    super(paramT, paramProxy);
  }

  protected PoiItemDetail a(JSONObject paramJSONObject)
    throws JSONException
  {
    PoiItemDetail localPoiItemDetail = new PoiItemDetail(a(paramJSONObject, "id"), b(paramJSONObject, "location"), a(paramJSONObject, "name"), a(paramJSONObject, "address"));
    localPoiItemDetail.setAdCode(a(paramJSONObject, "adcode"));
    localPoiItemDetail.setProvinceName(a(paramJSONObject, "pname"));
    localPoiItemDetail.setCityName(a(paramJSONObject, "cityname"));
    localPoiItemDetail.setAdName(a(paramJSONObject, "adname"));
    localPoiItemDetail.setCityCode(a(paramJSONObject, "citycode"));
    if (paramJSONObject.has("distance"))
    {
      String str = paramJSONObject.getString("distance");
      if (!e(str))
      {
        localPoiItemDetail.setDistance(Integer.parseInt(str));
        if (localPoiItemDetail.getDistance() == 0)
          localPoiItemDetail.setDistance(-1);
      }
    }
    localPoiItemDetail.setTel(a(paramJSONObject, "tel"));
    localPoiItemDetail.setTypeDes(a(paramJSONObject, "type"));
    localPoiItemDetail.setEnter(b(paramJSONObject, "entr_location"));
    localPoiItemDetail.setExit(b(paramJSONObject, "exit_location"));
    localPoiItemDetail.setWebsite(a(paramJSONObject, "website"));
    localPoiItemDetail.setPostcode(a(paramJSONObject, "citycode"));
    localPoiItemDetail.setEmail(a(paramJSONObject, "email"));
    if (c(a(paramJSONObject, "groupbuy_num")))
      localPoiItemDetail.setGroupbuyInfo(false);
    while (c(a(paramJSONObject, "discount_num")))
    {
      localPoiItemDetail.setDiscountInfo(false);
      return localPoiItemDetail;
      localPoiItemDetail.setGroupbuyInfo(true);
    }
    localPoiItemDetail.setDiscountInfo(true);
    return localPoiItemDetail;
  }

  protected String a(InputStream paramInputStream)
  {
    try
    {
      String str = new String(c.a(paramInputStream));
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  protected boolean e(String paramString)
  {
    return (paramString == null) || (paramString.equals("")) || (paramString.equals("[]"));
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.g
 * JD-Core Version:    0.6.2
 */