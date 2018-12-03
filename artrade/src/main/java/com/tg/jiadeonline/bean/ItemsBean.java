package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class ItemsBean
  implements Serializable
{
  public static final String certFeec = "certFee";
  public static final String commisionc = "commision";
  public static final String dateBidc = "dateBid";
  public static final String itemnumc = "itemnum";
  public static final String picPathc = "picPath";
  public static final String priceBidc = "priceBid";
  private static final long serialVersionUID = 1L;
  public static final String titlec = "title";
  private String certFee;
  private String commision;
  private String dateBid;
  private String ischeck;
  private String itemnum;
  private String picPath;
  private String priceBid;
  private String title;

  public String getCertFee()
  {
    return this.certFee;
  }

  public String getCommision()
  {
    return this.commision;
  }

  public String getDateBid()
  {
    return this.dateBid;
  }

  public String getIscheck()
  {
    return this.ischeck;
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

  public void setCertFee(String paramString)
  {
    this.certFee = paramString;
  }

  public void setCommision(String paramString)
  {
    this.commision = paramString;
  }

  public void setDateBid(String paramString)
  {
    this.dateBid = paramString;
  }

  public void setIscheck(String paramString)
  {
    this.ischeck = paramString;
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
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.ItemsBean
 * JD-Core Version:    0.6.2
 */