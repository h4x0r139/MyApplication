package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class updateUserBaseInfo extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public updateUserBaseInfo(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("name", paramString3);
    this.parmas.put("password", paramString4);
    this.parmas.put("personalId", paramString5);
    this.parmas.put("address", paramString6);
    this.parmas.put("postal_code", paramString7);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "userinfo/updateUserBaseInfo", "updateUserBaseInfo", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    JSONObject localJSONObject = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject.getString("Success");
    String str2 = localJSONObject.getString("ErrorInfo");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      str2 = "ok";
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.updateUserBaseInfo
 * JD-Core Version:    0.6.2
 */