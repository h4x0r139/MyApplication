package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;

import com.tg.jiadeonline.bean.UserFsBean;
import com.tg.jiadeonline.date.ForSaleInfoManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForSaleInfo extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public ForSaleInfo(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/order/forSaleInfo", "forSaleInfo", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    ForSaleInfoManager localForSaleInfoManager = new ForSaleInfoManager(paramContext);
    localForSaleInfoManager.openDataBase();
    localForSaleInfoManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    String str3 = localJSONObject1.getString("isHaveFS");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))) && (!str3.equals("N")))
      localJSONArray = localJSONObject1.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localForSaleInfoManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str4 = localJSONObject2.getString("forSaleId");
      String str5 = localJSONObject2.getString("userFSId");
      String str6 = localJSONObject2.getString("userFSFee");
      UserFsBean localUserFsBean = new UserFsBean();
      if (str4 == null)
        str4 = "";
      localUserFsBean.setForSaleId(str4);
      if (str6 == null)
        str6 = "";
      localUserFsBean.setUserFSFee(str6);
      if (str5 == null)
        str5 = "";
      localUserFsBean.setUserFSId(str5);
      if ("Y".equals(str3))
        localForSaleInfoManager.insertData(localUserFsBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.ForSaleInfo
 * JD-Core Version:    0.6.2
 */