package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class ZhuCeBean
  implements Serializable
{
  public static final String countc = "count";
  public static final String oldtimec = "oldtime";
  private static final long serialVersionUID = -8135305850158748910L;
  private String count;
  private String oldtime;

  public String getCount()
  {
    return this.count;
  }

  public String getOldtime()
  {
    return this.oldtime;
  }

  public void setCount(String paramString)
  {
    this.count = paramString;
  }

  public void setOldtime(String paramString)
  {
    this.oldtime = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.ZhuCeBean
 * JD-Core Version:    0.6.2
 */