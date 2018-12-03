package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.UserAccBean;
import com.tg.jiadeonline.date.UserAccManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserAcct extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public UserAcct(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
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
    return new JiaDeNetRequest(BASE_URL + "personal/userAcct", "userAcct", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    UserAccManager localUserAccManager = new UserAccManager(paramContext);
    localUserAccManager.openDataBase();
    localUserAccManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    localJSONObject1.getString("curAmount");
    localJSONObject1.getString("intiBala");
    localJSONObject1.getString("totRecs");
    localJSONObject1.getString("totPgs");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      localJSONArray = localJSONObject1.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localUserAccManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str3 = localJSONObject2.getString("id");
      String str4 = localJSONObject2.getString("amount");
      String str5 = localJSONObject2.getString("payTime");
      String str6 = localJSONObject2.getString("op_type");
      String str7 = localJSONObject2.getString("acctType");
      UserAccBean localUserAccBean = new UserAccBean();
      if (str3 == null)
        str3 = "";
      localUserAccBean.setId(str3);
      if (str4 == null)
        str4 = "";
      localUserAccBean.setAmount(str4);
      if (str5 == null)
        str5 = "";
      localUserAccBean.setPayTime(str5);
      if (str6 == null)
        str6 = "";
      localUserAccBean.setOp_type(str6);
      if (str7 == null)
        str7 = "";
      localUserAccBean.setAcctType(str7);
      localUserAccManager.insertData(localUserAccBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.UserAcct
 * JD-Core Version:    0.6.2
 */