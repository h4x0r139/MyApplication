package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class ChannelListBean
  implements Serializable
{
  public static final String catIdc = "catId";
  public static final String catNamec = "catName";
  public static final String catPicpathc = "catPicpath";
  public static final String isparc = "ispar";
  public static final String orderNumc = "orderNum";
  private static final long serialVersionUID = 322334334323232L;
  public static final String sysTimec = "sysTime";
  private String catId;
  private String catName;
  private String catPicpath;
  private String ispar;
  private String orderNum;
  private String sysTime;

  public String getCatId()
  {
    return this.catId;
  }

  public String getCatName()
  {
    return this.catName;
  }

  public String getCatPicpath()
  {
    return this.catPicpath;
  }

  public String getIspar()
  {
    return this.ispar;
  }

  public String getOrderNum()
  {
    return this.orderNum;
  }

  public String getSysTime()
  {
    return this.sysTime;
  }

  public void setCatId(String paramString)
  {
    this.catId = paramString;
  }

  public void setCatName(String paramString)
  {
    this.catName = paramString;
  }

  public void setCatPicpath(String paramString)
  {
    this.catPicpath = paramString;
  }

  public void setIspar(String paramString)
  {
    this.ispar = paramString;
  }

  public void setOrderNum(String paramString)
  {
    this.orderNum = paramString;
  }

  public void setSysTime(String paramString)
  {
    this.sysTime = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.ChannelListBean
 * JD-Core Version:    0.6.2
 */