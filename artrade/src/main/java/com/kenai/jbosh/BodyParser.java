package com.kenai.jbosh;

abstract interface BodyParser
{
  public abstract BodyParserResults parse(String paramString)
    throws BOSHException;
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BodyParser
 * JD-Core Version:    0.6.2
 */