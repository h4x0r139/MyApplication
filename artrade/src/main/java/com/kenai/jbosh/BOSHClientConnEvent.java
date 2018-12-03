package com.kenai.jbosh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;

public final class BOSHClientConnEvent extends EventObject
{
  private static final long serialVersionUID = 1L;
  private final Throwable cause;
  private final boolean connected;
  private final List<ComposableBody> requests;

  private BOSHClientConnEvent(BOSHClient paramBOSHClient, boolean paramBoolean, List<ComposableBody> paramList, Throwable paramThrowable)
  {
    super(paramBOSHClient);
    this.connected = paramBoolean;
    this.cause = paramThrowable;
    if (this.connected)
    {
      if (paramThrowable != null)
        throw new IllegalStateException("Cannot be connected and have a cause");
      if ((paramList != null) && (paramList.size() > 0))
        throw new IllegalStateException("Cannot be connected and have outstanding requests");
    }
    if (paramList == null)
    {
      this.requests = Collections.emptyList();
      return;
    }
    this.requests = Collections.unmodifiableList(new ArrayList(paramList));
  }

  static BOSHClientConnEvent createConnectionClosedEvent(BOSHClient paramBOSHClient)
  {
    return new BOSHClientConnEvent(paramBOSHClient, false, null, null);
  }

  static BOSHClientConnEvent createConnectionClosedOnErrorEvent(BOSHClient paramBOSHClient, List<ComposableBody> paramList, Throwable paramThrowable)
  {
    return new BOSHClientConnEvent(paramBOSHClient, false, paramList, paramThrowable);
  }

  static BOSHClientConnEvent createConnectionEstablishedEvent(BOSHClient paramBOSHClient)
  {
    return new BOSHClientConnEvent(paramBOSHClient, true, null, null);
  }

  public BOSHClient getBOSHClient()
  {
    return (BOSHClient)getSource();
  }

  public Throwable getCause()
  {
    return this.cause;
  }

  public List<ComposableBody> getOutstandingRequests()
  {
    return this.requests;
  }

  public boolean isConnected()
  {
    return this.connected;
  }

  public boolean isError()
  {
    return this.cause != null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BOSHClientConnEvent
 * JD-Core Version:    0.6.2
 */