package com.tg.jiadeonline.bean;

import java.io.Serializable;

public class LoginBean
  implements Serializable
{
  public static final String ErrorInfoc = "ErrorInfo";
  public static final String Successc = "Success";
  public static final String nicknamec = "nickname";
  public static final String passwordc = "password";
  private static final long serialVersionUID = 32233423323232L;
  public static final String userIdc = "userId";
  private String ErrorInfo;
  private String Success;
  private String nickname;
  private String password;
  private String userId;

  public String getErrorInfo()
  {
    return this.ErrorInfo;
  }

  public String getNickname()
  {
    return this.nickname;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getSuccess()
  {
    return this.Success;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public void setErrorInfo(String paramString)
  {
    this.ErrorInfo = paramString;
  }

  public void setNickname(String paramString)
  {
    this.nickname = paramString;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setSuccess(String paramString)
  {
    this.Success = paramString;
  }

  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.bean.LoginBean
 * JD-Core Version:    0.6.2
 */