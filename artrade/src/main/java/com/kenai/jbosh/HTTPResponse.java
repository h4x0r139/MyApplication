package com.kenai.jbosh;

abstract interface HTTPResponse
{
  public abstract void abort();

  public abstract AbstractBody getBody()
    throws InterruptedException, BOSHException;

  public abstract int getHTTPStatus()
    throws InterruptedException, BOSHException;
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.HTTPResponse
 * JD-Core Version:    0.6.2
 */