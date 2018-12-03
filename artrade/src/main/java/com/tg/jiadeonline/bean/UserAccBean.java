package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class UserAccBean
  implements Serializable
{
  public static final String acctTypec = "acctType";
  public static final String amountc = "amount";
  public static final String idc = "id";
  public static final String op_typec = "op_type";
  public static final String payTimec = "payTime";
  private static final long serialVersionUID = 7924980729344080917L;
  private String acctType;
  private String amount;
  private String id;
  private String op_type;
  private String payTime;

  public String getAcctType()
  {
    return this.acctType;
  }

  public String getAmount()
  {
    return this.amount;
  }

  public String getId()
  {
    return this.id;
  }

  public String getOp_type()
  {
    return this.op_type;
  }

  public String getPayTime()
  {
    return this.payTime;
  }

  public void setAcctType(String paramString)
  {
    this.acctType = paramString;
  }

  public void setAmount(String paramString)
  {
    this.amount = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setOp_type(String paramString)
  {
    this.op_type = paramString;
  }

  public void setPayTime(String paramString)
  {
    this.payTime = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.UserAccBean
 * JD-Core Version:    0.6.2
 */