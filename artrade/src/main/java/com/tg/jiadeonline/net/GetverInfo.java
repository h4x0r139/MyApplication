package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetverInfo extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public GetverInfo(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "version/newVerInfo", "version/newVerInfo", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      JSONArray localJSONArray = localJSONObject1.getJSONArray("Data");
      if ((localJSONArray != null) && (localJSONArray.length() > 0));
      for (int i = 0; ; i++)
      {
        if (i >= localJSONArray.length())
          return "";
        JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
        if ("A".equals(localJSONObject2.getString("app_type")))
          return localJSONObject2.toString();
      }
    }
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.GetverInfo
 * JD-Core Version:    0.6.2
 */