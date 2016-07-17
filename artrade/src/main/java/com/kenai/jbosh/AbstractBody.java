package com.kenai.jbosh;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class AbstractBody
{
  static BodyQName getBodyQName()
  {
    return BodyQName.createBOSH("body");
  }

  public final String getAttribute(BodyQName paramBodyQName)
  {
    return (String)getAttributes().get(paramBodyQName);
  }

  public final Set<BodyQName> getAttributeNames()
  {
    return Collections.unmodifiableSet(getAttributes().keySet());
  }

  public abstract Map<BodyQName, String> getAttributes();

  public abstract String toXML();
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AbstractBody
 * JD-Core Version:    0.6.2
 */