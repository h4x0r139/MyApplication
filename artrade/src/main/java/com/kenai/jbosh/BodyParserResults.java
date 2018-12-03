package com.kenai.jbosh;

import java.util.HashMap;
import java.util.Map;

final class BodyParserResults
{
  private final Map<BodyQName, String> attrs = new HashMap();

  void addBodyAttributeValue(BodyQName paramBodyQName, String paramString)
  {
    this.attrs.put(paramBodyQName, paramString);
  }

  Map<BodyQName, String> getAttributes()
  {
    return this.attrs;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BodyParserResults
 * JD-Core Version:    0.6.2
 */