package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class MunionpayBean
  implements Serializable
{
  public static final String billNoc = "billNo";
  public static final String orderIdc = "orderId";
  public static final String payAmountc = "payAmount";
  public static final String payTNc = "payTN";
  private static final long serialVersionUID = -8082730773673523243L;
  private String billNo;
  private String orderId;
  private String payAmount;
  private String payTN;

  public String getBillNo()
  {
    return this.billNo;
  }

  public String getOrderId()
  {
    return this.orderId;
  }

  public String getPayAmount()
  {
    return this.payAmount;
  }

  public String getPayTN()
  {
    return this.payTN;
  }

  public void setBillNo(String paramString)
  {
    this.billNo = paramString;
  }

  public void setOrderId(String paramString)
  {
    this.orderId = paramString;
  }

  public void setPayAmount(String paramString)
  {
    this.payAmount = paramString;
  }

  public void setPayTN(String paramString)
  {
    this.payTN = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.MunionpayBean
 * JD-Core Version:    0.6.2
 */