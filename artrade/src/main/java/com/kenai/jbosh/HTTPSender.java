package com.kenai.jbosh;

abstract interface HTTPSender
{
  public abstract void destroy();

  public abstract void init(BOSHClientConfig paramBOSHClientConfig);

  public abstract HTTPResponse send(CMSessionParams paramCMSessionParams, AbstractBody paramAbstractBody);
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.HTTPSender
 * JD-Core Version:    0.6.2
 */