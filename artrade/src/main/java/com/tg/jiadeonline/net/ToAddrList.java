package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.date.ToAddrListManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ToAddrList extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public ToAddrList(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("pnum", paramString3);
    this.parmas.put("pqty", paramString4);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/toAddrList", "toAddrList", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    ToAddrListManager localToAddrListManager = new ToAddrListManager(paramContext);
    localToAddrListManager.openDataBase();
    localToAddrListManager.deleteAllDatas();
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      localJSONArray = localJSONObject1.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localToAddrListManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str3 = localJSONObject2.getString("toAddressId");
      String str4 = localJSONObject2.getString("toCountry");
      String str5 = localJSONObject2.getString("toProvince");
      String str6 = localJSONObject2.getString("toCity");
      String str7 = localJSONObject2.getString("toAddress");
      String str8 = localJSONObject2.getString("toPerson");
      String str9 = localJSONObject2.getString("isDefault");
      String str10 = "";
      if (localJSONObject2.has("emial"))
        str10 = localJSONObject2.getString("email");
      String str11 = localJSONObject2.getString("zipCode");
      String str12 = localJSONObject2.getString("telephone");
      AddressBean localAddressBean = new AddressBean();
      if (str3 == null)
        str3 = "";
      localAddressBean.setAutoid(str3);
      if (str4 == null)
        str4 = "";
      localAddressBean.setCounty(str4);
      if (str5 == null)
        str5 = "";
      localAddressBean.setProv(str5);
      if (str6 == null)
        str6 = "";
      localAddressBean.setCity(str6);
      if (str7 == null)
        str7 = "";
      localAddressBean.setAddress(str7);
      if (str8 == null)
        str8 = "";
      localAddressBean.setName(str8);
      if (str11 == null)
        str11 = "";
      localAddressBean.setZipcode(str11);
      if (str9 == null)
        str9 = "";
      localAddressBean.setIsDefault(str9);
      localAddressBean.setEmail(str10);
      if (str12 == null)
        str12 = "";
      localAddressBean.setTel(str12);
      localToAddrListManager.insertData(localAddressBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.ToAddrList
 * JD-Core Version:    0.6.2
 */