package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class LotsBean
  implements Serializable
{
  private static final long serialVersionUID = 4738350194548626132L;
  private String carePer;
  private String curBid;
  private String iPicpath;
  private String itemnum;
  private int orderid;
  private String timeEnd;
  private String title;

  public String getCarePer()
  {
    return this.carePer;
  }

  public String getCurBid()
  {
    return this.curBid;
  }

  public String getItemnum()
  {
    return this.itemnum;
  }

  public int getOrderid()
  {
    return this.orderid;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getiPicpath()
  {
    return this.iPicpath;
  }

  public void setCarePer(String paramString)
  {
    this.carePer = paramString;
  }

  public void setCurBid(String paramString)
  {
    this.curBid = paramString;
  }

  public void setItemnum(String paramString)
  {
    this.itemnum = paramString;
  }

  public void setOrderid(int paramInt)
  {
    this.orderid = paramInt;
  }

  public void setTimeEnd(String paramString)
  {
    this.timeEnd = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setiPicpath(String paramString)
  {
    this.iPicpath = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.LotsBean
 * JD-Core Version:    0.6.2
 */