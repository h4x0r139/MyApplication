package com.kenai.jbosh;

import java.util.concurrent.TimeUnit;

final class AttrMaxPause extends AbstractIntegerAttr
{
  private AttrMaxPause(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(1);
  }

  static AttrMaxPause createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrMaxPause(paramString);
  }

  public int getInMilliseconds()
  {
    return (int)TimeUnit.MILLISECONDS.convert(intValue(), TimeUnit.SECONDS);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrMaxPause
 * JD-Core Version:    0.6.2
 */