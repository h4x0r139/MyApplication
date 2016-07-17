package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class LogoutBean
  implements Serializable
{
  public static final String ErrorInfoc = "ErrorInfo";
  public static final String Successc = "Success";
  private static final long serialVersionUID = 32237423323232L;
  private String ErrorInfo;
  private String Success;

  public String getErrorInfo()
  {
    return this.ErrorInfo;
  }

  public String getSuccess()
  {
    return this.Success;
  }

  public void setErrorInfo(String paramString)
  {
    this.ErrorInfo = paramString;
  }

  public void setSuccess(String paramString)
  {
    this.Success = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.LogoutBean
 * JD-Core Version:    0.6.2
 */