package com.kenai.jbosh;

final class AttrAccept extends AbstractAttr<String>
{
  private final String[] encodings;

  private AttrAccept(String paramString)
  {
    super(paramString);
    this.encodings = paramString.split("[\\s,]+");
  }

  static AttrAccept createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrAccept(paramString);
  }

  boolean isAccepted(String paramString)
  {
    String[] arrayOfString = this.encodings;
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
 * Qualified Name:     com.kenai.jbosh.AttrAccept
 * JD-Core Version:    0.6.2
 */