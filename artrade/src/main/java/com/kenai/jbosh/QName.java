package com.kenai.jbosh;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class QName
  implements Serializable
{
  private static final String emptyString = "".intern();
  private String localPart;
  private String namespaceURI;
  private String prefix;

  public QName(String paramString)
  {
    this(emptyString, paramString, emptyString);
  }

  public QName(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, emptyString);
  }

  public QName(String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null);
    for (String str = emptyString; ; str = paramString1.intern())
    {
      this.namespaceURI = str;
      if (paramString2 != null)
        break;
      throw new IllegalArgumentException("invalid QName local part");
    }
    this.localPart = paramString2.intern();
    if (paramString3 == null)
      throw new IllegalArgumentException("invalid QName prefix");
    this.prefix = paramString3.intern();
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.namespaceURI = this.namespaceURI.intern();
    this.localPart = this.localPart.intern();
    this.prefix = this.prefix.intern();
  }

  public static QName valueOf(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
      throw new IllegalArgumentException("invalid QName literal");
    if (paramString.charAt(0) == '{')
    {
      int i = paramString.indexOf('}');
      if (i == -1)
        throw new IllegalArgumentException("invalid QName literal");
      if (i == -1 + paramString.length())
        throw new IllegalArgumentException("invalid QName literal");
      return new QName(paramString.substring(1, i), paramString.substring(i + 1));
    }
    return new QName(paramString);
  }

  public final boolean equals(Object paramObject)
  {
    if (paramObject == this)
      return true;
    if (!(paramObject instanceof QName))
      return false;
    return (this.namespaceURI == ((QName)paramObject).namespaceURI) && (this.localPart == ((QName)paramObject).localPart);
  }

  public String getLocalPart()
  {
    return this.localPart;
  }

  public String getNamespaceURI()
  {
    return this.namespaceURI;
  }

  public String getPrefix()
  {
    return this.prefix;
  }

  public final int hashCode()
  {
    return this.namespaceURI.hashCode() ^ this.localPart.hashCode();
  }

  public String toString()
  {
    if (this.namespaceURI == emptyString)
      return this.localPart;
    return '{' + this.namespaceURI + '}' + this.localPart;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.QName
 * JD-Core Version:    0.6.2
 */