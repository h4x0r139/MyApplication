package com.kenai.jbosh;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

final class HTTPExchange
{
  private static final Logger LOG = Logger.getLogger(HTTPExchange.class.getName());
  private final Lock lock = new ReentrantLock();
  private final Condition ready = this.lock.newCondition();
  private final AbstractBody request;
  private HTTPResponse response;

  HTTPExchange(AbstractBody paramAbstractBody)
  {
    if (paramAbstractBody == null)
      throw new IllegalArgumentException("Request body cannot be null");
    this.request = paramAbstractBody;
  }

  HTTPResponse getHTTPResponse()
  {
    this.lock.lock();
    try
    {
      while (true)
      {
        HTTPResponse localHTTPResponse1 = this.response;
        if (localHTTPResponse1 != null)
          break;
        try
        {
          this.ready.await();
        }
        catch (InterruptedException localInterruptedException)
        {
          LOG.log(Level.FINEST, "Interrupted", localInterruptedException);
        }
      }
    }
    finally
    {
      this.lock.unlock();
    }
    HTTPResponse localHTTPResponse2 = this.response;
    this.lock.unlock();
    return localHTTPResponse2;
  }

  AbstractBody getRequest()
  {
    return this.request;
  }

  void setHTTPResponse(HTTPResponse paramHTTPResponse)
  {
    this.lock.lock();
    try
    {
      if (this.response != null)
        throw new IllegalStateException("HTTPResponse was already set");
    }
    finally
    {
      this.lock.unlock();
    }
    this.response = paramHTTPResponse;
    this.ready.signalAll();
    this.lock.unlock();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.HTTPExchange
 * JD-Core Version:    0.6.2
 */