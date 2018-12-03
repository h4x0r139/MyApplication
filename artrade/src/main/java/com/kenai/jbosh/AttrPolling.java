package com.kenai.jbosh;

import java.util.concurrent.TimeUnit;

final class AttrPolling extends AbstractIntegerAttr
{
  private AttrPolling(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(0);
  }

  static AttrPolling createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrPolling(paramString);
  }

  public int getInMilliseconds()
  {
    return (int)TimeUnit.MILLISECONDS.convert(intValue(), TimeUnit.SECONDS);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrPolling
 * JD-Core Version:    0.6.2
 */