package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.SpecialFieldlistBean;
import com.tg.jiadeonline.date.SpecialFieldlistManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class SpecialFieldlist1 extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public SpecialFieldlist1(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
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
    return new JiaDeNetRequest(BASE_URL + "listings/list", "list", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    SpecialFieldlistManager localSpecialFieldlistManager = new SpecialFieldlistManager(paramContext);
    localSpecialFieldlistManager.openDataBase();
    localSpecialFieldlistManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    String str3 = localJSONObject1.getString("catPic");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      JSONArray localJSONArray = localJSONObject1.getJSONArray("Data");
      for (int i = 0; ; i++)
      {
        if (i >= localJSONArray.length())
        {
          localSpecialFieldlistManager.closeDataBase();
          return "ok";
        }
        JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
        String str4 = localJSONObject2.getString("itemnum");
        String str5 = localJSONObject2.getString("title");
        String str6 = "";
        if (localJSONObject2.has("picPath"))
          str6 = localJSONObject2.getString("picPath");
        String str7 = localJSONObject2.getString("timeStart");
        String str8 = "";
        if (localJSONObject2.has("timeEnd"))
          str8 = localJSONObject2.getString("timeEnd");
        String str9 = localJSONObject2.getString("sysTime");
        String str10 = localJSONObject2.getString("carePers");
        String str11 = localJSONObject2.getString("minimumBid");
        String str12 = "";
        if (localJSONObject2.has("currentBid"))
          str12 = localJSONObject2.getString("currentBid");
        String str13 = "";
        if (localJSONObject2.has("bstatus"))
          str13 = localJSONObject2.getString("bstatus");
        SpecialFieldlistBean localSpecialFieldlistBean = new SpecialFieldlistBean();
        if (str4 == null)
          str4 = "";
        localSpecialFieldlistBean.setItemnum(str4);
        if (str5 == null)
          str5 = "";
        localSpecialFieldlistBean.setTitle(str5);
        if (str6 == null)
          str6 = "";
        localSpecialFieldlistBean.setPicPath(str6);
        if (str7 == null)
          str7 = "";
        localSpecialFieldlistBean.setTimeStart(str7);
        if (str8 == null)
          str8 = "";
        localSpecialFieldlistBean.setTimeEnd(str8);
        if (str9 == null)
          str9 = "";
        localSpecialFieldlistBean.setSysTime(str9);
        if (str10 == null)
          str10 = "";
        localSpecialFieldlistBean.setCarePers(str10);
        if (str11 == null)
          str11 = "";
        localSpecialFieldlistBean.setMinimumBid(str11);
        if (str12 == null)
          str12 = "";
        localSpecialFieldlistBean.setCurrentBid(str12);
        if (str13 == null)
          str13 = "";
        localSpecialFieldlistBean.setBstatus(str13);
        localSpecialFieldlistBean.setCatPic(str3);
        localSpecialFieldlistManager.insertData(localSpecialFieldlistBean);
      }
    }
    localSpecialFieldlistManager.closeDataBase();
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.SpecialFieldlist1
 * JD-Core Version:    0.6.2
 */