package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class HomeTopPicBean
  implements Serializable
{
  private static final long serialVersionUID = 7105834955922296499L;
  private String catDesc;
  private String catId;
  private String catPicpath;
  private String catSubTit;
  private String catTit;
  private String ispar;
  private int orderid;
  private int picid;
  private String timeEnd;
  private String timeStart;

  public String getCatDesc()
  {
    return this.catDesc;
  }

  public String getCatId()
  {
    return this.catId;
  }

  public String getCatPicpath()
  {
    return this.catPicpath;
  }

  public String getCatSubTit()
  {
    return this.catSubTit;
  }

  public String getCatTit()
  {
    return this.catTit;
  }

  public String getIspar()
  {
    return this.ispar;
  }

  public int getOrderid()
  {
    return this.orderid;
  }

  public int getPicid()
  {
    return this.picid;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTimeStart()
  {
    return this.timeStart;
  }

  public void setCatDesc(String paramString)
  {
    this.catDesc = paramString;
  }

  public void setCatId(String paramString)
  {
    this.catId = paramString;
  }

  public void setCatPicpath(String paramString)
  {
    this.catPicpath = paramString;
  }

  public void setCatSubTit(String paramString)
  {
    this.catSubTit = paramString;
  }

  public void setCatTit(String paramString)
  {
    this.catTit = paramString;
  }

  public void setIspar(String paramString)
  {
    this.ispar = paramString;
  }

  public void setOrderid(int paramInt)
  {
    this.orderid = paramInt;
  }

  public void setPicid(int paramInt)
  {
    this.picid = paramInt;
  }

  public void setTimeEnd(String paramString)
  {
    this.timeEnd = paramString;
  }

  public void setTimeStart(String paramString)
  {
    this.timeStart = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.HomeTopPicBean
 * JD-Core Version:    0.6.2
 */