package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.date.ToAddrListManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ToAddrDetail extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public ToAddrDetail(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("toAddressId", paramString3);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/toAddrDetail", "toAddrDetail", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    ToAddrListManager localToAddrListManager = new ToAddrListManager(paramContext);
    localToAddrListManager.openDataBase();
    localToAddrListManager.deleteAllDatas();
    JSONObject localJSONObject = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject.getString("Success");
    String str2 = localJSONObject.getString("ErrorInfo");
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      String str3 = localJSONObject.getString("toAddressId");
      String str4 = localJSONObject.getString("toCountry");
      String str5 = localJSONObject.getString("toProvince");
      String str6 = localJSONObject.getString("toCity");
      String str7 = localJSONObject.getString("toAddress");
      String str8 = localJSONObject.getString("toPerson");
      String str9 = localJSONObject.getString("telephone");
      String str10 = localJSONObject.getString("zipCode");
      String str11 = localJSONObject.getString("email");
      String str12 = localJSONObject.getString("identityNo");
      String str13 = localJSONObject.getString("isDefault");
      String str14 = localJSONObject.getString("isLastUse");
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
      if (str10 == null)
        str10 = "";
      localAddressBean.setZipcode(str10);
      if (str11 == null)
        str11 = "";
      localAddressBean.setEmail(str11);
      if (str9 == null)
        str9 = "";
      localAddressBean.setTel(str9);
      if (str12 == null)
        str12 = "";
      localAddressBean.setIdentityCard(str12);
      if (str13 == null)
        str13 = "";
      localAddressBean.setIsDefault(str13);
      if (str14 == null)
        str14 = "";
      localAddressBean.setIsuse(str14);
      localToAddrListManager.insertData(localAddressBean);
      str2 = "ok";
    }
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.ToAddrDetail
 * JD-Core Version:    0.6.2
 */