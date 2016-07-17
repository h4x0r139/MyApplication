package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class AddOrderBean
  implements Serializable
{
  public static final String netOrderIdc = "netOrderId";
  public static final String payMoneyc = "payMoney";
  private static final long serialVersionUID = 3739508270786187530L;
  private String netOrderId;
  private String payMoney;

  public String getNetOrderId()
  {
    return this.netOrderId;
  }

  public String getPayMoney()
  {
    return this.payMoney;
  }

  public void setNetOrderId(String paramString)
  {
    this.netOrderId = paramString;
  }

  public void setPayMoney(String paramString)
  {
    this.payMoney = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.AddOrderBean
 * JD-Core Version:    0.6.2
 */