package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.CollectAuctionlistBean;
import com.tg.jiadeonline.date.CollectAuctionlistManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class CollectAuctionlist extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public CollectAuctionlist(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("cateId", paramString3);
    this.parmas.put("pnum", paramString4);
    this.parmas.put("pqty", paramString5);
    this.parmas.put("porder", paramString6);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/collectAuctionlist", "collectAuctionlist", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    CollectAuctionlistManager localCollectAuctionlistManager = new CollectAuctionlistManager(paramContext);
    localCollectAuctionlistManager.openDataBase();
    localCollectAuctionlistManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      localJSONArray = localJSONObject1.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localCollectAuctionlistManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str3 = localJSONObject2.getString("itemnum");
      String str4 = localJSONObject2.getString("title");
      String str5 = localJSONObject2.getString("picPath");
      String str6 = localJSONObject2.getString("timeStart");
      String str7 = localJSONObject2.getString("timeEnd");
      String str8 = localJSONObject2.getString("sysTime");
      String str9 = localJSONObject2.getString("carePers");
      String str10 = localJSONObject2.getString("minimumBid");
      String str11 = localJSONObject2.getString("currentBid");
      String str12 = "";
      if (localJSONObject2.has("bstaus"))
        str12 = localJSONObject2.getString("bstaus");
      if (localJSONObject2.has("bstatus"))
        str12 = localJSONObject2.getString("bstatus");
      CollectAuctionlistBean localCollectAuctionlistBean = new CollectAuctionlistBean();
      if (str3 == null)
        str3 = "";
      localCollectAuctionlistBean.setItemnum(str3);
      if (str4 == null)
        str4 = "";
      localCollectAuctionlistBean.setTitle(str4);
      if (str5 == null)
        str5 = "";
      localCollectAuctionlistBean.setPicPath(str5);
      if (str6 == null)
        str6 = "";
      localCollectAuctionlistBean.setTimeStart(str6);
      if (str7 == null)
        str7 = "";
      localCollectAuctionlistBean.setTimeEnd(str7);
      if (str8 == null)
        str8 = "";
      localCollectAuctionlistBean.setSysTime(str8);
      if (str9 == null)
        str9 = "";
      localCollectAuctionlistBean.setCarePers(str9);
      if (str10 == null)
        str10 = "";
      localCollectAuctionlistBean.setMinimumBid(str10);
      if (str11 == null)
        str11 = "";
      localCollectAuctionlistBean.setCurrentBid(str11);
      if (str12 == null)
        str12 = "";
      localCollectAuctionlistBean.setBstatus(str12);
      localCollectAuctionlistManager.insertData(localCollectAuctionlistBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.CollectAuctionlist
 * JD-Core Version:    0.6.2
 */