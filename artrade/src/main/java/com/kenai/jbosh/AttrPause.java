package com.kenai.jbosh;

import java.util.concurrent.TimeUnit;

final class AttrPause extends AbstractIntegerAttr
{
  private AttrPause(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(1);
  }

  static AttrPause createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrPause(paramString);
  }

  public int getInMilliseconds()
  {
    return (int)TimeUnit.MILLISECONDS.convert(intValue(), TimeUnit.SECONDS);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrPause
 * JD-Core Version:    0.6.2
 */