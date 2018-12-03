package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.myBiddingBean;
import com.tg.jiadeonline.date.GetmyBiddingManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetmyBidding extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public GetmyBidding(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("type", paramString3);
    this.parmas.put("pnum", paramString4);
    this.parmas.put("pqty", paramString5);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/myBidding", "myBidding", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    GetmyBiddingManager localGetmyBiddingManager = new GetmyBiddingManager(paramContext);
    localGetmyBiddingManager.openDataBase();
    localGetmyBiddingManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    String str3 = localJSONObject1.getString("totRecs");
    String str4 = localJSONObject1.getString("totPgs");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      localJSONArray = localJSONObject1.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localGetmyBiddingManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str5 = localJSONObject2.getString("carePers");
      String str6 = localJSONObject2.getString("currentBid");
      String str7 = localJSONObject2.getString("itemnum");
      String str8 = localJSONObject2.getString("picPath");
      String str9 = localJSONObject2.getString("timeEnd");
      String str10 = localJSONObject2.getString("title");
      myBiddingBean localmyBiddingBean = new myBiddingBean();
      localmyBiddingBean.setCarePers(str5);
      localmyBiddingBean.setCurrentBid(str6);
      localmyBiddingBean.setItemnum(str7);
      localmyBiddingBean.setPicPath(str8);
      localmyBiddingBean.setTimeEnd(str9);
      localmyBiddingBean.setTitle(str10);
      localmyBiddingBean.setTotPgs(str4);
      localmyBiddingBean.setTotRecs(str3);
      localGetmyBiddingManager.insertData(localmyBiddingBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.GetmyBidding
 * JD-Core Version:    0.6.2
 */