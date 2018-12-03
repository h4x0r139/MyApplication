package com.kenai.jbosh;

final class AttrSessionID extends AbstractAttr<String>
{
  private AttrSessionID(String paramString)
  {
    super(paramString);
  }

  static AttrSessionID createFromString(String paramString)
  {
    return new AttrSessionID(paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrSessionID
 * JD-Core Version:    0.6.2
 */