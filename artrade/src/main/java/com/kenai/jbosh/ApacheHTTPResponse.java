package com.kenai.jbosh;

import java.net.URI;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

final class ApacheHTTPResponse
  implements HTTPResponse
{
  private static final String ACCEPT_ENCODING = "Accept-Encoding";
  private static final String ACCEPT_ENCODING_VAL = ZLIBCodec.getID() + ", " + GZIPCodec.getID();
  private static final String CHARSET = "UTF-8";
  private static final String CONTENT_TYPE = "text/xml; charset=utf-8";
  private AbstractBody body;
  private final HttpClient client;
  private final HttpContext context;
  private final Lock lock = new ReentrantLock();
  private final HttpPost post;
  private boolean sent;
  private int statusCode;
  private BOSHException toThrow;

  ApacheHTTPResponse(HttpClient paramHttpClient, BOSHClientConfig paramBOSHClientConfig, CMSessionParams paramCMSessionParams, AbstractBody paramAbstractBody)
  {
    this.client = paramHttpClient;
    this.context = new BasicHttpContext();
    this.post = new HttpPost(paramBOSHClientConfig.getURI().toString());
    this.sent = false;
    try
    {
      Object localObject = paramAbstractBody.toXML().getBytes("UTF-8");
      boolean bool1 = paramBOSHClientConfig.isCompressionEnabled();
      String str = null;
      AttrAccept localAttrAccept;
      if (bool1)
      {
        str = null;
        if (paramCMSessionParams != null)
        {
          localAttrAccept = paramCMSessionParams.getAccept();
          str = null;
          if (localAttrAccept != null)
          {
            if (!localAttrAccept.isAccepted(ZLIBCodec.getID()))
              break label183;
            str = ZLIBCodec.getID();
            localObject = ZLIBCodec.encode((byte[])localObject);
          }
        }
      }
      while (true)
      {
        ByteArrayEntity localByteArrayEntity = new ByteArrayEntity((byte[])localObject);
        localByteArrayEntity.setContentType("text/xml; charset=utf-8");
        if (str != null)
          localByteArrayEntity.setContentEncoding(str);
        this.post.setEntity(localByteArrayEntity);
        if (!paramBOSHClientConfig.isCompressionEnabled())
          break;
        this.post.setHeader("Accept-Encoding", ACCEPT_ENCODING_VAL);
        return;
        label183: boolean bool2 = localAttrAccept.isAccepted(GZIPCodec.getID());
        str = null;
        if (bool2)
        {
          str = GZIPCodec.getID();
          byte[] arrayOfByte = GZIPCodec.encode((byte[])localObject);
          localObject = arrayOfByte;
        }
      }
    }
    catch (Exception localException)
    {
      this.toThrow = new BOSHException("Could not generate request", localException);
    }
  }

  // ERROR //
  private void awaitResponse()
    throws BOSHException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 71	com/kenai/jbosh/ApacheHTTPResponse:client	Lorg/apache/http/client/HttpClient;
    //   6: aload_0
    //   7: getfield 92	com/kenai/jbosh/ApacheHTTPResponse:post	Lorg/apache/http/client/methods/HttpPost;
    //   10: aload_0
    //   11: getfield 76	com/kenai/jbosh/ApacheHTTPResponse:context	Lorg/apache/http/protocol/HttpContext;
    //   14: invokeinterface 165 3 0
    //   19: astore 4
    //   21: aload 4
    //   23: invokeinterface 171 1 0
    //   28: astore 5
    //   30: aload 5
    //   32: invokestatic 177	org/apache/http/util/EntityUtils:toByteArray	(Lorg/apache/http/HttpEntity;)[B
    //   35: astore 6
    //   37: aload 5
    //   39: invokeinterface 183 1 0
    //   44: ifnull +77 -> 121
    //   47: aload 5
    //   49: invokeinterface 183 1 0
    //   54: invokeinterface 188 1 0
    //   59: astore 7
    //   61: invokestatic 46	com/kenai/jbosh/ZLIBCodec:getID	()Ljava/lang/String;
    //   64: aload 7
    //   66: invokevirtual 191	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   69: ifeq +58 -> 127
    //   72: aload 6
    //   74: invokestatic 194	com/kenai/jbosh/ZLIBCodec:decode	([B)[B
    //   77: astore 6
    //   79: aload_0
    //   80: new 101	java/lang/String
    //   83: dup
    //   84: aload 6
    //   86: ldc 14
    //   88: invokespecial 197	java/lang/String:<init>	([BLjava/lang/String;)V
    //   91: invokestatic 203	com/kenai/jbosh/StaticBody:fromString	(Ljava/lang/String;)Lcom/kenai/jbosh/StaticBody;
    //   94: putfield 205	com/kenai/jbosh/ApacheHTTPResponse:body	Lcom/kenai/jbosh/AbstractBody;
    //   97: aload_0
    //   98: aload 4
    //   100: invokeinterface 209 1 0
    //   105: invokeinterface 215 1 0
    //   110: putfield 217	com/kenai/jbosh/ApacheHTTPResponse:statusCode	I
    //   113: aload_0
    //   114: iconst_1
    //   115: putfield 94	com/kenai/jbosh/ApacheHTTPResponse:sent	Z
    //   118: aload_0
    //   119: monitorexit
    //   120: return
    //   121: aconst_null
    //   122: astore 7
    //   124: goto -63 -> 61
    //   127: invokestatic 55	com/kenai/jbosh/GZIPCodec:getID	()Ljava/lang/String;
    //   130: aload 7
    //   132: invokevirtual 191	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   135: ifeq -56 -> 79
    //   138: aload 6
    //   140: invokestatic 218	com/kenai/jbosh/GZIPCodec:decode	([B)[B
    //   143: astore 8
    //   145: aload 8
    //   147: astore 6
    //   149: goto -70 -> 79
    //   152: astore_3
    //   153: aload_0
    //   154: invokevirtual 221	com/kenai/jbosh/ApacheHTTPResponse:abort	()V
    //   157: aload_0
    //   158: new 147	com/kenai/jbosh/BOSHException
    //   161: dup
    //   162: ldc 223
    //   164: aload_3
    //   165: invokespecial 152	com/kenai/jbosh/BOSHException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   168: putfield 154	com/kenai/jbosh/ApacheHTTPResponse:toThrow	Lcom/kenai/jbosh/BOSHException;
    //   171: aload_0
    //   172: getfield 154	com/kenai/jbosh/ApacheHTTPResponse:toThrow	Lcom/kenai/jbosh/BOSHException;
    //   175: athrow
    //   176: astore_2
    //   177: aload_0
    //   178: monitorexit
    //   179: aload_2
    //   180: athrow
    //   181: astore_1
    //   182: aload_0
    //   183: invokevirtual 221	com/kenai/jbosh/ApacheHTTPResponse:abort	()V
    //   186: aload_1
    //   187: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	61	152	java/io/IOException
    //   61	79	152	java/io/IOException
    //   79	118	152	java/io/IOException
    //   127	145	152	java/io/IOException
    //   2	61	176	finally
    //   61	79	176	finally
    //   79	118	176	finally
    //   127	145	176	finally
    //   153	176	176	finally
    //   182	188	176	finally
    //   2	61	181	java/lang/RuntimeException
    //   61	79	181	java/lang/RuntimeException
    //   79	118	181	java/lang/RuntimeException
    //   127	145	181	java/lang/RuntimeException
  }

  public void abort()
  {
    if (this.post != null)
    {
      this.post.abort();
      this.toThrow = new BOSHException("HTTP request aborted");
    }
  }

  public AbstractBody getBody()
    throws InterruptedException, BOSHException
  {
    if (this.toThrow != null)
      throw this.toThrow;
    this.lock.lock();
    try
    {
      if (!this.sent)
        awaitResponse();
      return this.body;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public int getHTTPStatus()
    throws InterruptedException, BOSHException
  {
    if (this.toThrow != null)
      throw this.toThrow;
    this.lock.lock();
    try
    {
      if (!this.sent)
        awaitResponse();
      return this.statusCode;
    }
    finally
    {
      this.lock.unlock();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.ApacheHTTPResponse
 * JD-Core Version:    0.6.2
 */