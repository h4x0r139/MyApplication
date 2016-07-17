package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class SearchItemsBean
  implements Serializable
{
  public static final String carePersc = "carePers";
  public static final String currentBidc = "currentBid";
  public static final String itemnumc = "itemnum";
  public static final String minimumBidc = "minimumBid";
  public static final String picturec = "picture";
  private static final long serialVersionUID = 322333233093232L;
  public static final String timeEndc = "timeEnd";
  public static final String titlec = "title";
  public static final String totalc = "total";
  private String carePers;
  private String currentBid;
  private String itemnum;
  private String minimumBid;
  private String picture;
  private String timeEnd;
  private String title;
  private String total;

  public String getCarePers()
  {
    return this.carePers;
  }

  public String getCurrentBid()
  {
    return this.currentBid;
  }

  public String getItemnum()
  {
    return this.itemnum;
  }

  public String getMinimumBid()
  {
    return this.minimumBid;
  }

  public String getPicture()
  {
    return this.picture;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getTotal()
  {
    return this.total;
  }

  public void setCarePers(String paramString)
  {
    this.carePers = paramString;
  }

  public void setCurrentBid(String paramString)
  {
    this.currentBid = paramString;
  }

  public void setItemnum(String paramString)
  {
    this.itemnum = paramString;
  }

  public void setMinimumBid(String paramString)
  {
    this.minimumBid = paramString;
  }

  public void setPicture(String paramString)
  {
    this.picture = paramString;
  }

  public void setTimeEnd(String paramString)
  {
    this.timeEnd = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setTotal(String paramString)
  {
    this.total = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.SearchItemsBean
 * JD-Core Version:    0.6.2
 */