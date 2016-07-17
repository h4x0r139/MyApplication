package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class CaredItemMod extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public CaredItemMod(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("itemnum", paramString3);
    this.parmas.put("type", paramString4);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/caredItemMod", "caredItemMod", false, this.parmas, this.mContext);
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
 * Qualified Name:     com.tg.jiadeonline.net.CaredItemMod
 * JD-Core Version:    0.6.2
 */