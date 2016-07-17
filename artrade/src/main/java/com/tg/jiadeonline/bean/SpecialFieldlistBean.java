package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class SpecialFieldlistBean
  implements Serializable
{
  public static final String bstatusc = "bstatus";
  public static final String carePersc = "carePers";
  public static final String catPicc = "catPic";
  public static final String currentBidc = "currentBid";
  public static final String itemnumc = "itemnum";
  public static final String minimumBidc = "minimumBid";
  public static final String picPathc = "picPath";
  private static final long serialVersionUID = 32234453323232L;
  public static final String sysTimec = "sysTime";
  public static final String timeEndc = "timeEnd";
  public static final String timeStartc = "timeStart";
  public static final String titlec = "title";
  private String bstatus;
  private String carePers;
  private String catPic;
  private String currentBid;
  private boolean ischeck;
  private String itemnum;
  private String minimumBid;
  private String picPath;
  private String sysTime;
  private String timeEnd;
  private String timeStart;
  private String title;

  public String getBstatus()
  {
    return this.bstatus;
  }

  public String getCarePers()
  {
    return this.carePers;
  }

  public String getCatPic()
  {
    return this.catPic;
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

  public String getPicPath()
  {
    return this.picPath;
  }

  public String getSysTime()
  {
    return this.sysTime;
  }

  public String getTimeEnd()
  {
    return this.timeEnd;
  }

  public String getTimeStart()
  {
    return this.timeStart;
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean isIscheck()
  {
    return this.ischeck;
  }

  public void setBstatus(String paramString)
  {
    this.bstatus = paramString;
  }

  public void setCarePers(String paramString)
  {
    this.carePers = paramString;
  }

  public void setCatPic(String paramString)
  {
    this.catPic = paramString;
  }

  public void setCurrentBid(String paramString)
  {
    this.currentBid = paramString;
  }

  public void setIscheck(boolean paramBoolean)
  {
    this.ischeck = paramBoolean;
  }

  public void setItemnum(String paramString)
  {
    this.itemnum = paramString;
  }

  public void setMinimumBid(String paramString)
  {
    this.minimumBid = paramString;
  }

  public void setPicPath(String paramString)
  {
    this.picPath = paramString;
  }

  public void setSysTime(String paramString)
  {
    this.sysTime = paramString;
  }

  public void setTimeEnd(String paramString)
  {
    this.timeEnd = paramString;
  }

  public void setTimeStart(String paramString)
  {
    this.timeStart = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.SpecialFieldlistBean
 * JD-Core Version:    0.6.2
 */