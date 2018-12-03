package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class UserFsBean
  implements Serializable
{
  public static final String forSaleIdc = "forSaleId";
  private static final long serialVersionUID = -7449133233872688675L;
  public static final String userFSFeec = "userFSFee";
  public static final String userFSIdc = "userFSId";
  private String forSaleId;
  private String userFSFee;
  private String userFSId;

  public String getForSaleId()
  {
    return this.forSaleId;
  }

  public String getUserFSFee()
  {
    return this.userFSFee;
  }

  public String getUserFSId()
  {
    return this.userFSId;
  }

  public void setForSaleId(String paramString)
  {
    this.forSaleId = paramString;
  }

  public void setUserFSFee(String paramString)
  {
    this.userFSFee = paramString;
  }

  public void setUserFSId(String paramString)
  {
    this.userFSId = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.UserFsBean
 * JD-Core Version:    0.6.2
 */