package com.kenai.jbosh;

import java.util.EventObject;

public final class BOSHMessageEvent extends EventObject
{
  private static final long serialVersionUID = 1L;
  private final AbstractBody body;

  private BOSHMessageEvent(Object paramObject, AbstractBody paramAbstractBody)
  {
    super(paramObject);
    if (paramAbstractBody == null)
      throw new IllegalArgumentException("message body may not be null");
    this.body = paramAbstractBody;
  }

  static BOSHMessageEvent createRequestSentEvent(BOSHClient paramBOSHClient, AbstractBody paramAbstractBody)
  {
    return new BOSHMessageEvent(paramBOSHClient, paramAbstractBody);
  }

  static BOSHMessageEvent createResponseReceivedEvent(BOSHClient paramBOSHClient, AbstractBody paramAbstractBody)
  {
    return new BOSHMessageEvent(paramBOSHClient, paramAbstractBody);
  }

  public AbstractBody getBody()
  {
    return this.body;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BOSHMessageEvent
 * JD-Core Version:    0.6.2
 */