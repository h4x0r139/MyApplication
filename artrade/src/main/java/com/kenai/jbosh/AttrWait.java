package com.kenai.jbosh;

final class AttrWait extends AbstractIntegerAttr
{
  private AttrWait(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(1);
  }

  static AttrWait createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrWait(paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrWait
 * JD-Core Version:    0.6.2
 */