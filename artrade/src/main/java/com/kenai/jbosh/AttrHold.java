package com.kenai.jbosh;

final class AttrHold extends AbstractIntegerAttr
{
  private AttrHold(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(0);
  }

  static AttrHold createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrHold(paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrHold
 * JD-Core Version:    0.6.2
 */