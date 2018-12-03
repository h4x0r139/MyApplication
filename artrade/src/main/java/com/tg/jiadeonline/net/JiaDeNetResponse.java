package com.tg.jiadeonline.net;

public class JiaDeNetResponse
{
  public String errMsg = "";
  public boolean hasError = false;
  public Object result;

  public JiaDeNetResponse(Object paramObject)
  {
    this.result = paramObject;
  }

  public JiaDeNetResponse(String paramString)
  {
    setErrMsg(paramString);
  }

  public boolean hasError()
  {
    return this.hasError;
  }

  public void setErrMsg(String paramString)
  {
    this.hasError = true;
    this.errMsg = paramString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.JiaDeNetResponse
 * JD-Core Version:    0.6.2
 */