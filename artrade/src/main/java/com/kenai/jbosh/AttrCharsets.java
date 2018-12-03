package com.kenai.jbosh;

final class AttrCharsets extends AbstractAttr<String>
{
  private final String[] charsets;

  private AttrCharsets(String paramString)
  {
    super(paramString);
    this.charsets = paramString.split("\\ +");
  }

  static AttrCharsets createFromString(String paramString)
  {
    if (paramString == null)
      return null;
    return new AttrCharsets(paramString);
  }

  boolean isAccepted(String paramString)
  {
    String[] arrayOfString = this.charsets;
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      boolean bool = false;
      if (j < i)
      {
        if (arrayOfString[j].equalsIgnoreCase(paramString))
          bool = true;
      }
      else
        return bool;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrCharsets
 * JD-Core Version:    0.6.2
 */