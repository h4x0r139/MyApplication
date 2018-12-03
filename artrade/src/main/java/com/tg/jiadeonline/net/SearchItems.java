package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.SearchItemsBean;
import com.tg.jiadeonline.date.SearchItemsManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchItems extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public SearchItems(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("srchStr", paramString3);
    this.parmas.put("pnum", paramString4);
    this.parmas.put("pqty", paramString5);
    this.parmas.put("porder", paramString6);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "search/searchItems", "searchItems", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    SearchItemsManager localSearchItemsManager = new SearchItemsManager(paramContext);
    localSearchItemsManager.openDataBase();
    localSearchItemsManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    String str3 = localJSONObject1.getString("total");
    JSONArray localJSONArray;
    int i;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      localJSONArray = localJSONObject1.getJSONArray("data");
      i = 0;
      if (i >= localJSONArray.length())
      {
        localSearchItemsManager.closeDataBase();
        str2 = "ok";
      }
    }
    else
    {
      return str2;
    }
    JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
    String str4 = localJSONObject2.getString("itemnum");
    String str5 = localJSONObject2.getString("title");
    String str6 = localJSONObject2.getString("picture");
    String str7 = localJSONObject2.getString("timeEnd");
    String str8 = localJSONObject2.getString("carePers");
    String str9 = localJSONObject2.getString("minimumBid");
    String str10 = localJSONObject2.getString("currentBid");
    SearchItemsBean localSearchItemsBean = new SearchItemsBean();
    if (str4 == null)
      str4 = "";
    localSearchItemsBean.setItemnum(str4);
    if (str5 == null)
      str5 = "";
    localSearchItemsBean.setTitle(str5);
    if (str6 == null)
      str6 = "";
    localSearchItemsBean.setPicture(str6);
    if (str7 == null)
      str7 = "";
    localSearchItemsBean.setTimeEnd(str7);
    if (str3 == null);
    for (String str11 = ""; ; str11 = str3)
    {
      localSearchItemsBean.setTotal(str11);
      if (str8 == null)
        str8 = "";
      localSearchItemsBean.setCarePers(str8);
      if (str9 == null)
        str9 = "";
      localSearchItemsBean.setMinimumBid(str9);
      if (str10 == null)
        str10 = "";
      localSearchItemsBean.setCurrentBid(str10);
      localSearchItemsManager.insertData(localSearchItemsBean);
      i++;
      break;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.SearchItems
 * JD-Core Version:    0.6.2
 */