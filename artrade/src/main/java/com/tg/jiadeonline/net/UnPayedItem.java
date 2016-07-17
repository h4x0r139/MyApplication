package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.ItemsBean;
import com.tg.jiadeonline.date.ItemsManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnPayedItem extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public UnPayedItem(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("duration", paramString3);
    this.parmas.put("pnum", paramString4);
    this.parmas.put("pqty", paramString5);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/order/unpayedItem", "unpayedItem", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    ItemsManager localItemsManager = new ItemsManager(paramContext);
    localItemsManager.openDataBase();
    localItemsManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    localJSONObject1.getString("totPgs");
    localJSONObject1.getString("totRecs");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      localJSONArray = localJSONObject1.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localItemsManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str3 = localJSONObject2.getString("itemnum");
      String str4 = localJSONObject2.getString("title");
      String str5 = localJSONObject2.getString("picPath");
      String str6 = localJSONObject2.getString("priceBid");
      String str7 = localJSONObject2.getString("dateBid");
      String str8 = localJSONObject2.getString("commision");
      String str9 = localJSONObject2.getString("certFee");
      ItemsBean localItemsBean = new ItemsBean();
      if (str3 == null)
        str3 = "";
      localItemsBean.setItemnum(str3);
      if (str4 == null)
        str4 = "";
      localItemsBean.setTitle(str4);
      if (str5 == null)
        str5 = "";
      localItemsBean.setPicPath(str5);
      if (str6 == null)
        str6 = "";
      localItemsBean.setPriceBid(str6);
      if (str7 == null)
        str7 = "";
      localItemsBean.setDateBid(str7);
      if (str8 == null)
        str8 = "";
      localItemsBean.setCommision(str8);
      if (str9 == null)
        str9 = "";
      localItemsBean.setCertFee(str9);
      localItemsManager.insertData(localItemsBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.UnPayedItem
 * JD-Core Version:    0.6.2
 */