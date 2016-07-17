package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class ChannelList1Bean
  implements Serializable
{
  public static final String catIdc = "catId";
  public static final String catPicpathc = "catPicpath";
  public static final String catSubTitc = "catSubTit";
  public static final String catTitc = "catTit";
  public static final String isparc = "ispar";
  public static final String orderNumc = "orderNum";
  private static final long serialVersionUID = 322434323232L;
  public static final String timeEndc = "timeEnd";
  public static final String timeStartc = "timeStart";
  private String catId;
  private String catPicpath;
  private String catSubTit;
  private String catTit;
  private String ispar;
  private String orderNum;
  private String timeEnd;
  private String timeStart;

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

  public String getOrderNum()
  {
    return this.orderNum;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTimeStart()
  {
    return this.timeStart;
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

  public void setOrderNum(String paramString)
  {
    this.orderNum = paramString;
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
 * Qualified Name:     com.tg.jiadeonline.bean.ChannelList1Bean
 * JD-Core Version:    0.6.2
 */