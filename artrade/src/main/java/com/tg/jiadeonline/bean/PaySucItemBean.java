package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class PaySucItemBean
  implements Serializable
{
  public static final String carrierc = "carrier";
  public static final String cerFeec = "cerFee";
  public static final String commisionc = "commision";
  public static final String isNeedCerc = "isNeedCer";
  public static final String itemnumc = "itemnum";
  public static final String picPathc = "picPath";
  public static final String priceBidc = "priceBid";
  private static final long serialVersionUID = 3986422868614108107L;
  public static final String titlec = "title";
  public static final String transportIDc = "transportID";
  private String carrier;
  private String certFee;
  private String commision;
  private String isNeedCer;
  private String itemnum;
  private String picPath;
  private String priceBid;
  private String title;
  private String transportID;

  public String getCarrier()
  {
    return this.carrier;
  }

  public String getCertFee()
  {
    return this.certFee;
  }

  public String getCommision()
  {
    return this.commision;
  }

  public String getIsNeedCer()
  {
    return this.isNeedCer;
  }

  public String getItemnum()
  {
    return this.itemnum;
  }

  public String getPicPath()
  {
    return this.picPath;
  }

  public String getPriceBid()
  {
    return this.priceBid;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getTransportID()
  {
    return this.transportID;
  }

  public void setCarrier(String paramString)
  {
    this.carrier = paramString;
  }

  public void setCertFee(String paramString)
  {
    this.certFee = paramString;
  }

  public void setCommision(String paramString)
  {
    this.commision = paramString;
  }

  public void setIsNeedCer(String paramString)
  {
    this.isNeedCer = paramString;
  }

  public void setItemnum(String paramString)
  {
    this.itemnum = paramString;
  }

  public void setPicPath(String paramString)
  {
    this.picPath = paramString;
  }

  public void setPriceBid(String paramString)
  {
    this.priceBid = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setTransportID(String paramString)
  {
    this.transportID = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.PaySucItemBean
 * JD-Core Version:    0.6.2
 */