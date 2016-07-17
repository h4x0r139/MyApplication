package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.bean.ChannelListBean;
import com.tg.jiadeonline.date.ChannelList1Manager;
import com.tg.jiadeonline.date.ChannelListManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChannelList extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public ChannelList(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("cateId", paramString3);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "listings/channelList", "channelList", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    ChannelListManager localChannelListManager = new ChannelListManager(paramContext);
    ChannelList1Manager localChannelList1Manager = new ChannelList1Manager(paramContext);
    localChannelListManager.openDataBase();
    localChannelListManager.deleteAllDatas();
    localChannelList1Manager.openDataBase();
    localChannelList1Manager.deleteAllDatas();
    JSONObject localJSONObject1 = new JSONObject(paramString);
    JSONObject localJSONObject2 = (JSONObject)localJSONObject1.get("OutputData");
    String str1 = localJSONObject2.getString("Success");
    String str2 = localJSONObject2.getString("ErrorInfo");
    localJSONObject2.getString("sysTime");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      JSONArray localJSONArray2;
      int j;
      JSONArray localJSONArray1;
      if ((localJSONObject2.has("fixedData")) && (!localJSONObject2.getString("fixedData").equals("")))
      {
        localJSONArray2 = localJSONObject2.getJSONArray("fixedData");
        j = 0;
        if (j < localJSONArray2.length());
      }
      else if ((localJSONObject2.has("Data")) && (!localJSONObject2.getString("Data").equals("")))
      {
        localJSONArray1 = localJSONObject2.getJSONArray("Data");
      }
      for (int i = 0; ; i++)
      {
        if (i >= localJSONArray1.length())
        {
          localChannelListManager.closeDataBase();
          localChannelList1Manager.closeDataBase();
          return "ok";
          JSONObject localJSONObject4 = localJSONArray2.getJSONObject(j);
          String str11 = "";
          if (localJSONObject4.has("catName"))
            str11 = localJSONObject4.getString("catName");
          String str12 = "";
          if (localJSONObject4.has("catId"))
            str12 = localJSONObject4.getString("catId");
          String str13 = "";
          if (localJSONObject4.has("orderNum"))
            str13 = localJSONObject4.getString("orderNum");
          String str14 = "";
          if (localJSONObject4.has("catPicpath"))
            str14 = localJSONObject4.getString("catPicpath");
          ChannelListBean localChannelListBean = new ChannelListBean();
          localChannelListBean.setCatId(str12);
          localChannelListBean.setCatName(str11);
          localChannelListBean.setCatPicpath(str14);
          localChannelListBean.setOrderNum(str13);
          localChannelListManager.insertData(localChannelListBean);
          j++;
          break;
        }
        JSONObject localJSONObject3 = localJSONArray1.getJSONObject(i);
        String str3 = "";
        if (localJSONObject3.has("catTit"))
          str3 = localJSONObject3.getString("catTit");
        String str4 = "";
        if (localJSONObject3.has("catId"))
          str4 = localJSONObject3.getString("catId");
        String str5 = "";
        if (localJSONObject3.has("orderNum"))
          str5 = localJSONObject3.getString("orderNum");
        String str6 = "";
        if (localJSONObject3.has("catPicpath"))
          str6 = localJSONObject3.getString("catPicpath");
        String str7 = "";
        if (localJSONObject3.has("timeEnd"))
          str7 = localJSONObject3.getString("timeEnd");
        String str8 = "";
        if (localJSONObject3.has("timeStart"))
          str8 = localJSONObject3.getString("timeStart");
        String str9 = "";
        if (localJSONObject3.has("catSubTit"))
          str9 = localJSONObject3.getString("catSubTit");
        String str10 = "";
        if (localJSONObject3.has("ispar"))
          str10 = localJSONObject3.getString("ispar");
        ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(str4);
        localChannelList1Bean.setCatTit(str3);
        localChannelList1Bean.setCatPicpath(str6);
        localChannelList1Bean.setOrderNum(str5);
        localChannelList1Bean.setCatSubTit(str9);
        localChannelList1Bean.setTimeEnd(str7);
        localChannelList1Bean.setTimeStart(str8);
        localChannelList1Bean.setIspar(str10);
        localChannelList1Manager.insertData(localChannelList1Bean);
      }
    }
    localChannelListManager.closeDataBase();
    localChannelList1Manager.closeDataBase();
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.ChannelList
 * JD-Core Version:    0.6.2
 */