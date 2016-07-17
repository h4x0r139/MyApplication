package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.HomePageBean;
import com.tg.jiadeonline.date.HomePageManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class HomePage extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public HomePage(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "homepage", "homepage", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    HomePageManager localHomePageManager = new HomePageManager(paramContext);
    localHomePageManager.openDataBase();
    localHomePageManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    String str3 = localJSONObject1.getString("sysTime");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      JSONArray localJSONArray = localJSONObject1.getJSONArray("Data");
      int i = 0;
      if (i >= localJSONArray.length())
      {
        localHomePageManager.closeDataBase();
        return "ok";
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str4 = localJSONObject2.getString("modType");
      if ("Info".equals(str4))
      {
        String str9 = localJSONObject2.getString("msgData");
        String str10 = localJSONObject2.getString("posId");
        HomePageBean localHomePageBean3 = new HomePageBean();
        localHomePageBean3.setSuccess(str1);
        localHomePageBean3.setSysTime(str3);
        localHomePageBean3.setErrorInfo(str2);
        localHomePageBean3.setSysTime(str3);
        localHomePageBean3.setBann(str9);
        localHomePageBean3.setBannnum(str4);
        localHomePageBean3.setCat1(str10);
        localHomePageManager.insertData(localHomePageBean3);
      }
      while (true)
      {
        i++;
        break;
        if ("Lots".equals(str4))
        {
          String str7 = localJSONObject2.getString("itemData");
          String str8 = localJSONObject2.getString("posId");
          HomePageBean localHomePageBean2 = new HomePageBean();
          localHomePageBean2.setSuccess(str1);
          localHomePageBean2.setSysTime(str3);
          localHomePageBean2.setErrorInfo(str2);
          localHomePageBean2.setSysTime(str3);
          localHomePageBean2.setBann(str7);
          localHomePageBean2.setBannnum(str4);
          localHomePageBean2.setCat1(str8);
          localHomePageManager.insertData(localHomePageBean2);
        }
        else
        {
          String str5 = localJSONObject2.getString("content");
          String str6 = localJSONObject2.getString("posId");
          HomePageBean localHomePageBean1 = new HomePageBean();
          localHomePageBean1.setSuccess(str1);
          localHomePageBean1.setSysTime(str3);
          localHomePageBean1.setErrorInfo(str2);
          localHomePageBean1.setSysTime(str3);
          localHomePageBean1.setBann(str5);
          localHomePageBean1.setCat1(str6);
          localHomePageBean1.setBannnum(str4);
          localHomePageManager.insertData(localHomePageBean1);
        }
      }
    }
    localHomePageManager.closeDataBase();
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.HomePage
 * JD-Core Version:    0.6.2
 */