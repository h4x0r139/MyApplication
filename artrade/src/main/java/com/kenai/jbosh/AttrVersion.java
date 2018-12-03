package com.kenai.jbosh;

final class AttrVersion extends AbstractAttr<String>
  implements Comparable
{
  private static final AttrVersion DEFAULT;
  private final int major;
  private final int minor;

  static
  {
    try
    {
      DEFAULT = createFromString("1.8");
      return;
    }
    catch (BOSHException localBOSHException)
    {
      throw new IllegalStateException(localBOSHException);
    }
  }

  private AttrVersion(String paramString)
    throws BOSHException
  {
    super(paramString);
    int i = paramString.indexOf('.');
    if (i <= 0)
      throw new BOSHException("Illegal ver attribute value (not in major.minor form): " + paramString);
    String str1 = paramString.substring(0, i);
    try
    {
      this.major = Integer.parseInt(str1);
      if (this.major < 0)
        throw new BOSHException("Major version may not be < 0");
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      throw new BOSHException("Could not parse ver attribute value (major ver): " + str1, localNumberFormatException1);
    }
    String str2 = paramString.substring(i + 1);
    try
    {
      this.minor = Integer.parseInt(str2);
      if (this.minor < 0)
        throw new BOSHException("Minor version may not be < 0");
    }
    catch (NumberFormatException localNumberFormatException2)
    {
      throw new BOSHException("Could not parse ver attribute value (minor ver): " + str2, localNumberFormatException2);
    }
  }

  static AttrVersion createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrVersion(paramString);
  }

  static AttrVersion getSupportedVersion()
  {
    return DEFAULT;
  }

  public int compareTo(Object paramObject)
  {
    if ((paramObject instanceof AttrVersion))
    {
      AttrVersion localAttrVersion = (AttrVersion)paramObject;
      if (this.major < localAttrVersion.major);
      do
      {
        return -1;
        if (this.major > localAttrVersion.major)
          return 1;
      }
      while (this.minor < localAttrVersion.minor);
      if (this.minor > localAttrVersion.minor)
        return 1;
      return 0;
    }
    return 0;
  }

  int getMajor()
  {
    return this.major;
  }

  int getMinor()
  {
    return this.minor;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrVersion
 * JD-Core Version:    0.6.2
 */