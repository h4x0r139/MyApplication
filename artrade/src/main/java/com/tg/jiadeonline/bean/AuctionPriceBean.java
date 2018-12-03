package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class AuctionPriceBean
  implements Serializable
{
  public static final String biddingIdc = "biddingId";
  public static final String biddingPricec = "biddingPrice";
  public static final String biddingTimec = "biddingTime";
  public static final String nicknamec = "nickname";
  private static final long serialVersionUID = 3223397832656333232L;
  private String biddingId;
  private String biddingPrice;
  private String biddingTime;
  private String nickname;

  public String getBiddingId()
  {
    return this.biddingId;
  }

  public String getBiddingPrice()
  {
    return this.biddingPrice;
  }

  public String getBiddingTime()
  {
    return this.biddingTime;
  }

  public String getNickname()
  {
    return this.nickname;
  }

  public void setBiddingId(String paramString)
  {
    this.biddingId = paramString;
  }

  public void setBiddingPrice(String paramString)
  {
    this.biddingPrice = paramString;
  }

  public void setBiddingTime(String paramString)
  {
    this.biddingTime = paramString;
  }

  public void setNickname(String paramString)
  {
    this.nickname = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.AuctionPriceBean
 * JD-Core Version:    0.6.2
 */