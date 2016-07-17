package com.kenai.jbosh;

final class AttrInactivity extends AbstractIntegerAttr
{
  private AttrInactivity(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(0);
  }

  static AttrInactivity createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrInactivity(paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrInactivity
 * JD-Core Version:    0.6.2
 */