package com.unionpay.mobile.android.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f
{
  public static String a(JSONArray paramJSONArray, int paramInt)
  {
    Object localObject = "";
    if ((paramJSONArray != null) && (paramInt >= 0) && (paramInt < paramJSONArray.length()));
    try
    {
      String str = paramJSONArray.getString(paramInt);
      localObject = str;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localObject;
  }

  public static String a(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    if (e(paramJSONObject, paramString));
    try
    {
      String str = paramJSONObject.getString(paramString);
      localObject = str;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      g.c("uppay", f.class.toString() + " get " + paramString + " failed!!");
    }
    return localObject;
  }

  public static Object b(JSONArray paramJSONArray, int paramInt)
  {
    Object localObject1 = null;
    if (paramJSONArray != null)
    {
      int i = paramJSONArray.length();
      localObject1 = null;
      if (paramInt < i)
      {
        localObject1 = null;
        if (paramInt < 0);
      }
    }
    try
    {
      Object localObject2 = paramJSONArray.get(paramInt);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }

  public static JSONObject b(JSONObject paramJSONObject, String paramString)
  {
    boolean bool = e(paramJSONObject, paramString);
    Object localObject = null;
    if (bool);
    try
    {
      JSONObject localJSONObject = paramJSONObject.getJSONObject(paramString);
      localObject = localJSONObject;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      g.c("uppay", f.class.toString() + " get " + paramString + " failed!!");
    }
    return null;
  }

  public static JSONArray c(JSONObject paramJSONObject, String paramString)
  {
    boolean bool = e(paramJSONObject, paramString);
    Object localObject = null;
    if (bool);
    try
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray(paramString);
      localObject = localJSONArray;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      g.c("uppay", f.class.toString() + " get " + paramString + " failed!!");
    }
    return null;
  }

  public static List<JSONArray> d(JSONObject paramJSONObject, String paramString)
  {
    ArrayList localArrayList = new ArrayList(1);
    JSONArray localJSONArray = c(paramJSONObject, paramString);
    for (int i = 0; (localJSONArray != null) && (i < localJSONArray.length()); i++)
      localArrayList.add((JSONArray)b(localJSONArray, i));
    return localArrayList;
  }

  private static boolean e(JSONObject paramJSONObject, String paramString)
  {
    boolean bool1 = false;
    if (paramJSONObject != null)
    {
      boolean bool2 = paramJSONObject.has(paramString);
      bool1 = false;
      if (bool2)
        bool1 = true;
    }
    return bool1;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.f
 * JD-Core Version:    0.6.2
 */