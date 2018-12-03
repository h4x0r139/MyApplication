package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.AuctionPriceBean;
import com.tg.jiadeonline.bean.AuctiondetailsBean;
import com.tg.jiadeonline.date.AuctiondetailsManager;
import com.tg.jiadeonline.date.AuctionpriceManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Auctiondetails extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public Auctiondetails(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("itemnum", paramString3);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "item", "item", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    AuctiondetailsManager localAuctiondetailsManager = new AuctiondetailsManager(paramContext);
    localAuctiondetailsManager.openDataBase();
    localAuctiondetailsManager.deleteAllDatas();
    JSONObject localJSONObject1 = new JSONObject(paramString);
    JSONObject localJSONObject2 = (JSONObject)localJSONObject1.get("OutputData");
    String str1 = localJSONObject2.getString("Success");
    String str2 = localJSONObject2.getString("ErrorInfo");
    JSONArray localJSONArray;
    AuctionpriceManager localAuctionpriceManager;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      String str3 = "";
      if (localJSONObject2.has("title"))
        str3 = localJSONObject2.getString("title");
      String str4 = "";
      if (localJSONObject2.has("picPath"))
        str4 = localJSONObject2.getString("picPath");
      String str5 = "";
      if (localJSONObject2.has("timeStart"))
        str5 = localJSONObject2.getString("timeStart");
      String str6 = "";
      if (localJSONObject2.has("timeEnd"))
        str6 = localJSONObject2.getString("timeEnd");
      String str7 = "";
      if (localJSONObject2.has("sysTime"))
        str7 = localJSONObject2.getString("sysTime");
      String str8 = "";
      if (localJSONObject2.has("status"))
        str8 = localJSONObject2.getString("status");
      String str9 = "";
      if (localJSONObject2.has("sPicPath"))
        str9 = localJSONObject2.getString("sPicPath");
      boolean bool = localJSONObject2.has("isDelay");
      String str10 = null;
      if (bool)
        str10 = localJSONObject2.getString("isDelay");
      String str11 = "";
      if (localJSONObject2.has("minimumPrice"))
        str11 = localJSONObject2.getString("minimumPrice");
      String str12 = "";
      if (localJSONObject2.has("curBid"))
        str12 = localJSONObject2.getString("curBid");
      if (localJSONObject2.has("curbid"))
        str12 = localJSONObject2.getString("curbid");
      String str13 = "";
      if (localJSONObject2.has("bidTimes"))
        str13 = localJSONObject2.getString("bidTimes");
      String str14 = "";
      if (localJSONObject2.has("incrementPrice"))
        str14 = localJSONObject2.getString("incrementPrice");
      String str15 = "";
      if (localJSONObject2.has("description"))
        str15 = localJSONObject2.getString("description");
      String str16 = "";
      if (localJSONObject2.has("isCared"))
        str16 = localJSONObject2.getString("isCared");
      String str17 = "";
      if (localJSONObject2.has("aType"))
        str17 = localJSONObject2.getString("aType");
      String str18 = "";
      if (localJSONObject2.has("reservePrice"))
        str18 = localJSONObject2.getString("reservePrice");
      AuctiondetailsBean localAuctiondetailsBean = new AuctiondetailsBean();
      if (str18 == null)
        str18 = "";
      localAuctiondetailsBean.setReservePrice(str18);
      if (str3 == null)
        str3 = "";
      localAuctiondetailsBean.setTitle(str3);
      if (str4 == null)
        str4 = "";
      localAuctiondetailsBean.setPicPath(str4);
      if (str5 == null)
        str5 = "";
      localAuctiondetailsBean.setTimeStart(str5);
      if (str6 == null)
        str6 = "";
      localAuctiondetailsBean.setTimeEnd(str6);
      if (str7 == null)
        str7 = "";
      localAuctiondetailsBean.setSysTime(str7);
      if (str8 == null)
        str8 = "";
      localAuctiondetailsBean.setStatus(str8);
      if (str9 == null)
        str9 = "";
      localAuctiondetailsBean.setsPicPath(str9);
      if (str10 == null)
        str10 = "";
      localAuctiondetailsBean.setIsDelay(str10);
      if (str11 == null)
        str11 = "";
      localAuctiondetailsBean.setMinimumPrice(str11);
      if (str12 == null)
        str12 = "";
      localAuctiondetailsBean.setCurBid(str12);
      if (str13 == null)
        str13 = "";
      localAuctiondetailsBean.setBidTimes(str13);
      if (str14 == null)
        str14 = "";
      localAuctiondetailsBean.setIncrementPrice(str14);
      if (str15 == null)
        str15 = "";
      localAuctiondetailsBean.setDescription(str15);
      if (str16 == null)
        str16 = "";
      localAuctiondetailsBean.setIsCared(str16);
      localAuctiondetailsBean.setaType(str17);
      localAuctiondetailsManager.insertData(localAuctiondetailsBean);
      localAuctiondetailsManager.closeDataBase();
      if ((localJSONObject2.has("biddingData")) && (!localJSONObject2.getString("biddingData").equals("")))
      {
        localJSONArray = localJSONObject2.getJSONArray("biddingData");
        localAuctionpriceManager = new AuctionpriceManager(paramContext);
        localAuctionpriceManager.openDataBase();
        localAuctionpriceManager.deleteAllDatas();
      }
    }
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localAuctionpriceManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject3 = localJSONArray.getJSONObject(i);
      String str19 = localJSONObject3.getString("biddingId");
      String str20 = localJSONObject3.getString("biddingPrice");
      String str21 = localJSONObject3.getString("biddingTime");
      String str22 = localJSONObject3.getString("nickname");
      AuctionPriceBean localAuctionPriceBean = new AuctionPriceBean();
      localAuctionPriceBean.setBiddingId(str19);
      localAuctionPriceBean.setBiddingPrice(str20);
      localAuctionPriceBean.setBiddingTime(str21);
      localAuctionPriceBean.setNickname(str22);
      localAuctionpriceManager.insertData(localAuctionPriceBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.Auctiondetails
 * JD-Core Version:    0.6.2
 */