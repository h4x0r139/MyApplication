package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.AddOrderBean;
import com.tg.jiadeonline.date.AddOrderManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddOrder extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public AddOrder(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, JSONArray paramJSONArray, String paramString7)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("logistics_id", paramString3);
    this.parmas.put("fsId", paramString4);
    this.parmas.put("ofsId", paramString5);
    this.parmas.put("ofsFee", paramString6);
    this.parmas.put("itemData", paramJSONArray);
    this.parmas.put("invoice", paramString7);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/order/addOrder", "addOrder", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    JSONObject localJSONObject = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject.getString("Success");
    String str2 = localJSONObject.getString("ErrorInfo");
    AddOrderManager localAddOrderManager = new AddOrderManager(paramContext);
    localAddOrderManager.openDataBase();
    localAddOrderManager.deleteAllDatas();
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      String str3 = localJSONObject.getString("netOrderId");
      String str4 = localJSONObject.getString("payMoney");
      AddOrderBean localAddOrderBean = new AddOrderBean();
      if (str3 == null)
        str3 = "";
      localAddOrderBean.setNetOrderId(str3);
      if (str4 == null)
        str4 = "";
      localAddOrderBean.setPayMoney(str4);
      localAddOrderManager.insertData(localAddOrderBean);
      localAddOrderManager.closeDataBase();
      return "ok";
    }
    localAddOrderManager.closeDataBase();
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.AddOrder
 * JD-Core Version:    0.6.2
 */