package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class CategoryBean
  implements Serializable
{
  public static final String catIdc = "catId";
  public static final String catNamec = "catName";
  public static final String cdlETimec = "cdlETime";
  public static final String cdlSTimec = "cdlSTime";
  public static final String cpicpathc = "cpicpath";
  public static final String isDispc = "isDisp";
  private static final long serialVersionUID = -5205808204623231821L;
  public static final String sysTimec = "sysTime";
  private String catId;
  private String catName;
  private String cdlETime;
  private String cdlSTime;
  private String cpicpath;
  private String isDisp;
  private String sysTime;

  public String getCatId()
  {
    return this.catId;
  }

  public String getCatName()
  {
    return this.catName;
  }

  public String getCdlETime()
  {
    return this.cdlETime;
  }

  public String getCdlSTime()
  {
    return this.cdlSTime;
  }

  public String getCpicpath()
  {
    return this.cpicpath;
  }

  public String getIsDisp()
  {
    return this.isDisp;
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

  public void setCdlETime(String paramString)
  {
    this.cdlETime = paramString;
  }

  public void setCdlSTime(String paramString)
  {
    this.cdlSTime = paramString;
  }

  public void setCpicpath(String paramString)
  {
    this.cpicpath = paramString;
  }

  public void setIsDisp(String paramString)
  {
    this.isDisp = paramString;
  }

  public void setSysTime(String paramString)
  {
    this.sysTime = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.CategoryBean
 * JD-Core Version:    0.6.2
 */