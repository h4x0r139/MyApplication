package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LogOut extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public LogOut(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "logout", "logout", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    JSONObject localJSONObject = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject.getString("Success");
    String str2 = localJSONObject.getString("ErrorInfo");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(paramContext);
      localRegisterOrLoginManager.openDataBase();
      localRegisterOrLoginManager.deleteAllDatas();
      localRegisterOrLoginManager.closeDataBase();
      str2 = "ok";
    }
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.LogOut
 * JD-Core Version:    0.6.2
 */