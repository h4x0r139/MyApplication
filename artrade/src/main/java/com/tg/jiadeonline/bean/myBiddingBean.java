package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class myBiddingBean
  implements Serializable
{
  public static final String carePersc = "carePers";
  public static final String currentBidc = "currentBid";
  public static final String idc = "id";
  public static final String itemnumc = "itemnum";
  public static final String picPathc = "picPath";
  private static final long serialVersionUID = -220514642721550084L;
  public static final String timeEndc = "timeEnd";
  public static final String titlec = "title";
  public static final String totPgsc = "totPgs";
  public static final String totRecsc = "totRecs";
  private String carePers;
  private String currentBid;
  private String id;
  private String itemnum;
  private String picPath;
  private String timeEnd;
  private String title;
  private String totPgs;
  private String totRecs;

  public String getCarePers()
  {
    return this.carePers;
  }

  public String getCurrentBid()
  {
    return this.currentBid;
  }

  public String getId()
  {
    return this.id;
  }

  public String getItemnum()
  {
    return this.itemnum;
  }

  public String getPicPath()
  {
    return this.picPath;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getTotPgs()
  {
    return this.totPgs;
  }

  public String getTotRecs()
  {
    return this.totRecs;
  }

  public void setCarePers(String paramString)
  {
    this.carePers = paramString;
  }

  public void setCurrentBid(String paramString)
  {
    this.currentBid = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setItemnum(String paramString)
  {
    this.itemnum = paramString;
  }

  public void setPicPath(String paramString)
  {
    this.picPath = paramString;
  }

  public void setTimeEnd(String paramString)
  {
    this.timeEnd = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setTotPgs(String paramString)
  {
    this.totPgs = paramString;
  }

  public void setTotRecs(String paramString)
  {
    this.totRecs = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.myBiddingBean
 * JD-Core Version:    0.6.2
 */