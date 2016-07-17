package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.EndOfDayListBean;
import com.tg.jiadeonline.date.EndOfDayListManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class EndOfDaylist extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public EndOfDaylist(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
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
    return new JiaDeNetRequest(BASE_URL + "listings/endOfDaylist", "endOfDaylist", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    EndOfDayListManager localEndOfDayListManager = new EndOfDayListManager(paramContext);
    localEndOfDayListManager.openDataBase();
    localEndOfDayListManager.deleteAllDatas();
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
        localEndOfDayListManager.closeDataBase();
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
      EndOfDayListBean localEndOfDayListBean = new EndOfDayListBean();
      if (str3 == null)
        str3 = "";
      localEndOfDayListBean.setItemnum(str3);
      if (str4 == null)
        str4 = "";
      localEndOfDayListBean.setTitle(str4);
      if (str5 == null)
        str5 = "";
      localEndOfDayListBean.setPicPath(str5);
      if (str6 == null)
        str6 = "";
      localEndOfDayListBean.setTimeStart(str6);
      if (str7 == null)
        str7 = "";
      localEndOfDayListBean.setTimeEnd(str7);
      if (str8 == null)
        str8 = "";
      localEndOfDayListBean.setSysTime(str8);
      if (str9 == null)
        str9 = "";
      localEndOfDayListBean.setCarePers(str9);
      if (str10 == null)
        str10 = "";
      localEndOfDayListBean.setMinimumBid(str10);
      if (str11 == null)
        str11 = "";
      localEndOfDayListBean.setCurrentBid(str11);
      if (str12 == null)
        str12 = "";
      localEndOfDayListBean.setBstatus(str12);
      localEndOfDayListManager.insertData(localEndOfDayListBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.EndOfDaylist
 * JD-Core Version:    0.6.2
 */