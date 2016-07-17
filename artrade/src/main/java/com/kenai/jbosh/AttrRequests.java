package com.kenai.jbosh;

final class AttrRequests extends AbstractIntegerAttr
{
  private AttrRequests(String paramString)
    throws BOSHException
  {
    super(paramString);
    checkMinValue(1);
  }

  static AttrRequests createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrRequests(paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrRequests
 * JD-Core Version:    0.6.2
 */