package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class Register extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public Register(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("nickname", paramString2);
    this.parmas.put("password", paramString3);
    this.parmas.put("email", paramString4);
    this.parmas.put("mobile", paramString5);
    this.parmas.put("vcode", paramString6);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "reg", "reg", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(paramContext);
    localRegisterOrLoginManager.openDataBase();
    localRegisterOrLoginManager.deleteAllDatas();
    JSONObject localJSONObject = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject.getString("Success");
    String str2 = localJSONObject.getString("ErrorInfo");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      String str3 = localJSONObject.getString("nickname");
      String str4 = localJSONObject.getString("userId");
      LoginBean localLoginBean = new LoginBean();
      if (str3 == null)
        str3 = "";
      localLoginBean.setNickname(str3);
      if (str4 == null)
        str4 = "";
      localLoginBean.setUserId(str4);
      localRegisterOrLoginManager.insertData(localLoginBean);
      localRegisterOrLoginManager.closeDataBase();
      str2 = "ok";
    }
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.Register
 * JD-Core Version:    0.6.2
 */