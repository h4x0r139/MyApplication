package com.kenai.jbosh;

public final class BodyQName
{
  static final String BOSH_NS_URI = "http://jabber.org/protocol/httpbind";
  private final QName qname;

  private BodyQName(QName paramQName)
  {
    this.qname = paramQName;
  }

  public static BodyQName create(String paramString1, String paramString2)
  {
    return createWithPrefix(paramString1, paramString2, null);
  }

  static BodyQName createBOSH(String paramString)
  {
    return createWithPrefix("http://jabber.org/protocol/httpbind", paramString, null);
  }

  public static BodyQName createWithPrefix(String paramString1, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.length() == 0))
      throw new IllegalArgumentException("URI is required and may not be null/empty");
    if ((paramString2 == null) || (paramString2.length() == 0))
      throw new IllegalArgumentException("Local arg is required and may not be null/empty");
    if ((paramString3 == null) || (paramString3.length() == 0))
      return new BodyQName(new QName(paramString1, paramString2));
    return new BodyQName(new QName(paramString1, paramString2, paramString3));
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof BodyQName))
    {
      BodyQName localBodyQName = (BodyQName)paramObject;
      return this.qname.equals(localBodyQName.qname);
    }
    return false;
  }

  boolean equalsQName(QName paramQName)
  {
    return this.qname.equals(paramQName);
  }

  public String getLocalPart()
  {
    return this.qname.getLocalPart();
  }

  public String getNamespaceURI()
  {
    return this.qname.getNamespaceURI();
  }

  public String getPrefix()
  {
    return this.qname.getPrefix();
  }

  public int hashCode()
  {
    return this.qname.hashCode();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BodyQName
 * JD-Core Version:    0.6.2
 */