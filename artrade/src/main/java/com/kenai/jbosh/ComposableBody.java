package com.kenai.jbosh;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ComposableBody extends AbstractBody
{
  private static final Pattern BOSH_START = Pattern.compile("<body(?:[\t\n\r ][^>]*?)?(/>|>)", 64);
  private final Map<BodyQName, String> attrs;
  private final AtomicReference<String> computed = new AtomicReference();
  private final String payload;

  private ComposableBody(Map<BodyQName, String> paramMap, String paramString)
  {
    this.attrs = paramMap;
    this.payload = paramString;
  }

  public static Builder builder()
  {
    return new Builder(null);
  }

  private String computeXML()
  {
    BodyQName localBodyQName1 = getBodyQName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<");
    localStringBuilder.append(localBodyQName1.getLocalPart());
    Iterator localIterator = this.attrs.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      localStringBuilder.append(" ");
      BodyQName localBodyQName2 = (BodyQName)localEntry.getKey();
      String str = localBodyQName2.getPrefix();
      if ((str != null) && (str.length() > 0))
      {
        localStringBuilder.append(str);
        localStringBuilder.append(":");
      }
      localStringBuilder.append(localBodyQName2.getLocalPart());
      localStringBuilder.append("='");
      localStringBuilder.append(escape((String)localEntry.getValue()));
      localStringBuilder.append("'");
    }
    localStringBuilder.append(" ");
    localStringBuilder.append("xmlns");
    localStringBuilder.append("='");
    localStringBuilder.append(localBodyQName1.getNamespaceURI());
    localStringBuilder.append("'>");
    if (this.payload != null)
      localStringBuilder.append(this.payload);
    localStringBuilder.append("</body>");
    return localStringBuilder.toString();
  }

  private String escape(String paramString)
  {
    return paramString.replace("'", "&apos;");
  }

  static ComposableBody fromStaticBody(StaticBody paramStaticBody)
    throws BOSHException
  {
    String str1 = paramStaticBody.toXML();
    Matcher localMatcher = BOSH_START.matcher(str1);
    if (!localMatcher.find())
      throw new BOSHException("Could not locate 'body' element in XML.  The raw XML did not match the pattern: " + BOSH_START);
    int i;
    int j;
    if (">".equals(localMatcher.group(1)))
    {
      i = localMatcher.end();
      j = str1.lastIndexOf("</");
      if (j < i)
        j = i;
    }
    for (String str2 = str1.substring(i, j); ; str2 = "")
      return new ComposableBody(paramStaticBody.getAttributes(), str2);
  }

  public Map<BodyQName, String> getAttributes()
  {
    return Collections.unmodifiableMap(this.attrs);
  }

  public String getPayloadXML()
  {
    return this.payload;
  }

  public Builder rebuild()
  {
    return Builder.fromBody(this);
  }

  public String toXML()
  {
    String str = (String)this.computed.get();
    if (str == null)
    {
      str = computeXML();
      this.computed.set(str);
    }
    return str;
  }

  public static final class Builder
  {
    private boolean doMapCopy;
    private Map<BodyQName, String> map;
    private String payloadXML;

    private static Builder fromBody(ComposableBody paramComposableBody)
    {
      Builder localBuilder = new Builder();
      localBuilder.map = paramComposableBody.getAttributes();
      localBuilder.doMapCopy = true;
      localBuilder.payloadXML = paramComposableBody.payload;
      return localBuilder;
    }

    public ComposableBody build()
    {
      if (this.map == null)
        this.map = new HashMap();
      if (this.payloadXML == null)
        this.payloadXML = "";
      return new ComposableBody(this.map, this.payloadXML, null);
    }

    public Builder setAttribute(BodyQName paramBodyQName, String paramString)
    {
      if (this.map == null)
        this.map = new HashMap();
      while (paramString == null)
      {
        this.map.remove(paramBodyQName);
        return this;
        if (this.doMapCopy)
        {
          this.map = new HashMap(this.map);
          this.doMapCopy = false;
        }
      }
      this.map.put(paramBodyQName, paramString);
      return this;
    }

    public Builder setNamespaceDefinition(String paramString1, String paramString2)
    {
      return setAttribute(BodyQName.createWithPrefix("http://www.w3.org/XML/1998/namespace", paramString1, "xmlns"), paramString2);
    }

    public Builder setPayloadXML(String paramString)
    {
      if (paramString == null)
        throw new IllegalArgumentException("payload XML argument cannot be null");
      this.payloadXML = paramString;
      return this;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.ComposableBody
 * JD-Core Version:    0.6.2
 */