package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.MunionpayBean;
import com.tg.jiadeonline.date.MunionpayManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class MtenpayIU extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public MtenpayIU(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("insureFee", paramString3);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "payment/tenpay/mtenpayIU", "mtenpayIU", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    MunionpayManager localMunionpayManager = new MunionpayManager(paramContext);
    localMunionpayManager.openDataBase();
    localMunionpayManager.deleteAllDatas();
    JSONObject localJSONObject = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject.getString("Success");
    String str2 = localJSONObject.getString("ErrorInfo");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      String str3 = localJSONObject.getString("billNo");
      String str4 = localJSONObject.getString("payAmount");
      String str5 = localJSONObject.getString("payTN");
      MunionpayBean localMunionpayBean = new MunionpayBean();
      if (str3 == null)
        str3 = "";
      localMunionpayBean.setBillNo(str3);
      if (str4 == null)
        str4 = "";
      localMunionpayBean.setPayAmount(str4);
      if (str5 == null)
        str5 = "";
      localMunionpayBean.setPayTN(str5);
      localMunionpayManager.insertData(localMunionpayBean);
      localMunionpayManager.closeDataBase();
      str2 = "ok";
    }
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.MtenpayIU
 * JD-Core Version:    0.6.2
 */