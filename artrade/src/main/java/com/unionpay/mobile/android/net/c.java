package com.unionpay.mobile.android.net;

import com.unionpay.mobile.android.utils.g;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.ByteArrayBuffer;

public final class c
{
  private HttpClient a = null;
  private HttpResponse b = null;
  private HttpEntity c = null;
  private byte[] d = null;
  private InputStream e = null;
  private d f = null;

  public c(d paramd)
  {
    this.f = paramd;
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 20);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 60000);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    HttpClientParams.setRedirecting(localBasicHttpParams, true);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, "uppay");
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", new a(), 443));
    this.a = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
    ((AbstractHttpClient)this.a).setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
  }

  public final int a()
  {
    g.a("uppay", "HttpConn.connect() +++");
    if (this.f == null)
    {
      g.c("uppay", "params==null!!!");
      return 1;
    }
    if (this.f.a() == 1);
    for (Object localObject = new HttpPost(this.f.b()); ; localObject = new HttpGet(this.f.b()))
    {
      if (this.f.d() != null)
        ((HttpPost)localObject).setEntity(new ByteArrayEntity(this.f.d()));
      HashMap localHashMap = this.f.c();
      if (localHashMap == null)
        break;
      Iterator localIterator = localHashMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str4 = (String)localIterator.next();
        ((HttpUriRequest)localObject).addHeader(str4, (String)localHashMap.get(str4));
      }
    }
    int i;
    try
    {
      this.b = this.a.execute((HttpUriRequest)localObject);
      if (this.b.getStatusLine().getStatusCode() == 200)
      {
        this.c = this.b.getEntity();
        if (this.c == null)
          break label411;
        localByteArrayBuffer = new ByteArrayBuffer(2048);
        byte[] arrayOfByte = new byte[2048];
        this.e = this.c.getContent();
        while (true)
        {
          int j = this.e.read(arrayOfByte, 0, arrayOfByte.length);
          if (j == -1)
            break;
          if (j > 0)
            localByteArrayBuffer.append(arrayOfByte, 0, j);
        }
      }
    }
    catch (SSLHandshakeException localSSLHandshakeException)
    {
      ByteArrayBuffer localByteArrayBuffer;
      g.a("uppay", "e0:" + localSSLHandshakeException.getMessage());
      i = 4;
      while (true)
      {
        g.a("uppay", "HttpConn.connect() ---");
        return i;
        this.d = localByteArrayBuffer.toByteArray();
        i = 0;
        continue;
        if (this.b.getStatusLine().getStatusCode() == 401)
        {
          i = 8;
        }
        else
        {
          g.c("uppay", "http status code:" + this.b.getStatusLine().getStatusCode());
          i = 1;
        }
      }
    }
    catch (IOException localIOException)
    {
      if ("e1: " + localIOException != null);
      for (String str3 = localIOException.getMessage(); ; str3 = "e == null")
      {
        g.c("uppay", str3);
        i = 1;
        break;
      }
    }
    catch (IllegalStateException localIllegalStateException)
    {
      if ("e2: " + localIllegalStateException != null);
      for (String str2 = localIllegalStateException.getMessage(); ; str2 = "e == null")
      {
        g.c("uppay", str2);
        i = 1;
        break;
      }
    }
    catch (Exception localException)
    {
      label411: if ("e3: " + localException == null);
    }
    for (String str1 = localException.getMessage(); ; str1 = "e == null")
    {
      g.c("uppay", str1);
      i = 1;
      break;
    }
  }

  public final byte[] b()
  {
    return this.d;
  }

  // ERROR //
  public final String c()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 29	com/unionpay/mobile/android/net/c:d	[B
    //   4: ifnull +75 -> 79
    //   7: ldc 62
    //   9: aload_0
    //   10: getfield 29	com/unionpay/mobile/android/net/c:d	[B
    //   13: invokevirtual 281	java/lang/Object:toString	()Ljava/lang/String;
    //   16: invokestatic 130	com/unionpay/mobile/android/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   19: pop
    //   20: new 185	java/lang/String
    //   23: dup
    //   24: aload_0
    //   25: getfield 29	com/unionpay/mobile/android/net/c:d	[B
    //   28: ldc_w 283
    //   31: invokespecial 286	java/lang/String:<init>	([BLjava/lang/String;)V
    //   34: astore_2
    //   35: ldc 62
    //   37: new 242	java/lang/StringBuilder
    //   40: dup
    //   41: ldc_w 288
    //   44: invokespecial 245	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   47: aload_2
    //   48: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 254	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokestatic 130	com/unionpay/mobile/android/utils/g:a	(Ljava/lang/String;Ljava/lang/String;)I
    //   57: pop
    //   58: aload_2
    //   59: areturn
    //   60: astore_1
    //   61: aconst_null
    //   62: astore_2
    //   63: ldc 62
    //   65: ldc_w 290
    //   68: invokestatic 134	com/unionpay/mobile/android/utils/g:c	(Ljava/lang/String;Ljava/lang/String;)I
    //   71: pop
    //   72: aload_2
    //   73: areturn
    //   74: astore 5
    //   76: goto -13 -> 63
    //   79: aconst_null
    //   80: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   7	35	60	java/io/UnsupportedEncodingException
    //   35	58	74	java/io/UnsupportedEncodingException
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.net.c
 * JD-Core Version:    0.6.2
 */