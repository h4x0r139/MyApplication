package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.PaymentOrderBean;
import com.tg.jiadeonline.date.PaymentOrderManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnPayedOrder extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public UnPayedOrder(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("duration", paramString3);
    this.parmas.put("pnum", paramString4);
    this.parmas.put("pqty", paramString5);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "personal/order/unpayedOrder", "unpayedOrder", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    PaymentOrderManager localPaymentOrderManager = new PaymentOrderManager(paramContext);
    localPaymentOrderManager.openDataBase();
    localPaymentOrderManager.deleteAllDatas();
    JSONObject localJSONObject1 = new JSONObject(paramString);
    JSONObject localJSONObject2 = (JSONObject)localJSONObject1.get("OutputData");
    String str1 = localJSONObject2.getString("Success");
    String str2 = localJSONObject2.getString("ErrorInfo");
    localJSONObject2.getString("totPgs");
    localJSONObject2.getString("totRecs");
    JSONArray localJSONArray;
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
      localJSONArray = localJSONObject2.getJSONArray("Data");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
      {
        localPaymentOrderManager.closeDataBase();
        str2 = "ok";
        return str2;
      }
      JSONObject localJSONObject3 = localJSONArray.getJSONObject(i);
      String str3 = localJSONObject3.getString("orderId");
      String str4 = localJSONObject3.getString("payType");
      String str5 = localJSONObject3.getString("buyerId");
      String str6 = localJSONObject3.getString("totalAamount");
      String str7 = localJSONObject3.getString("totalCommission");
      String str8 = localJSONObject3.getString("toatlCertifycateFee");
      String str9 = localJSONObject3.getString("transportFee");
      String str10 = localJSONObject3.getString("logisticsId");
      String str11 = localJSONObject3.getString("freightOrderId");
      String str12 = localJSONObject3.getString("freightCompName");
      String str13 = localJSONObject3.getString("isPaySucss");
      String str14 = localJSONObject3.getString("order_status");
      String str15 = localJSONObject3.getString("description");
      String str16 = localJSONObject3.getString("fsFee");
      String str17 = localJSONObject3.getString("fsId");
      String str18 = localJSONObject3.getString("ofsId");
      String str19 = localJSONObject3.getString("ofsFee");
      String str20 = localJSONObject3.getString("datePay");
      String str21 = localJSONObject3.getString("itemData");
      String str22 = localJSONObject3.getString("invoice");
      PaymentOrderBean localPaymentOrderBean = new PaymentOrderBean();
      if (str22 == null)
        str22 = "";
      localPaymentOrderBean.setInvoice(str22);
      if (str3 == null)
        str3 = "";
      localPaymentOrderBean.setOrderId(str3);
      if (str4 == null)
        str4 = "";
      localPaymentOrderBean.setPayType(str4);
      if (str5 == null)
        str5 = "";
      localPaymentOrderBean.setBuyerId(str5);
      if (str6 == null)
        str6 = "";
      localPaymentOrderBean.setTotalAamount(str6);
      if (str7 == null)
        str7 = "";
      localPaymentOrderBean.setTotalCommission(str7);
      if (str8 == null)
        str8 = "";
      localPaymentOrderBean.setToatlCertifycateFee(str8);
      if (str9 == null)
        str9 = "";
      localPaymentOrderBean.setTransportFee(str9);
      if (str10 == null)
        str10 = "";
      localPaymentOrderBean.setLogisticsId(str10);
      if (str11 == null)
        str11 = "";
      localPaymentOrderBean.setFreightOrderId(str11);
      if (str12 == null)
        str12 = "";
      localPaymentOrderBean.setFreightCompName(str12);
      if (str13 == null)
        str13 = "";
      localPaymentOrderBean.setIsPaySucss(str13);
      if (str14 == null)
        str14 = "";
      localPaymentOrderBean.setOrder_status(str14);
      if (str15 == null)
        str15 = "";
      localPaymentOrderBean.setDescription(str15);
      if (str16 == null)
        str16 = "";
      localPaymentOrderBean.setFsFee(str16);
      if (str17 == null)
        str17 = "";
      localPaymentOrderBean.setFsId(str17);
      if (str18 == null)
        str18 = "";
      localPaymentOrderBean.setOfsId(str18);
      if (str19 == null)
        str19 = "";
      localPaymentOrderBean.setOfsFee(str19);
      if (str20 == null)
        str20 = "";
      localPaymentOrderBean.setDatePay(str20);
      if (str21 == null)
        str21 = "";
      localPaymentOrderBean.setItemData(str21);
      localPaymentOrderManager.insertData(localPaymentOrderBean);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.UnPayedOrder
 * JD-Core Version:    0.6.2
 */