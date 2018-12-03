package com.kenai.jbosh;

final class AttrAck extends AbstractAttr<String>
{
  private AttrAck(String paramString)
    throws BOSHException
  {
    super(paramString);
  }

  static AttrAck createFromString(String paramString)
    throws BOSHException
  {
    if (paramString == null)
      return null;
    return new AttrAck(paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AttrAck
 * JD-Core Version:    0.6.2
 */