package com.kenai.jbosh;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

final class ApacheHTTPSender
  implements HTTPSender
{
  private BOSHClientConfig cfg;
  private HttpClient httpClient;
  private final Lock lock = new ReentrantLock();

  ApacheHTTPSender()
  {
    HttpClient.class.getName();
  }

  private HttpClient initHttpClient(BOSHClientConfig paramBOSHClientConfig)
  {
    try
    {
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 100);
      HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
      HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, false);
      if ((paramBOSHClientConfig != null) && (paramBOSHClientConfig.getProxyHost() != null) && (paramBOSHClientConfig.getProxyPort() != 0))
        localBasicHttpParams.setParameter("http.route.default-proxy", new HttpHost(paramBOSHClientConfig.getProxyHost(), paramBOSHClientConfig.getProxyPort()));
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      SSLSocketFactory localSSLSocketFactory = SSLSocketFactory.getSocketFactory();
      localSSLSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      localSchemeRegistry.register(new Scheme("https", localSSLSocketFactory, 443));
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
      return localDefaultHttpClient;
    }
    finally
    {
    }
  }

  public void destroy()
  {
    this.lock.lock();
    try
    {
      if (this.httpClient != null)
        this.httpClient.getConnectionManager().shutdown();
      return;
    }
    finally
    {
      this.cfg = null;
      this.httpClient = null;
      this.lock.unlock();
    }
  }

  public void init(BOSHClientConfig paramBOSHClientConfig)
  {
    this.lock.lock();
    try
    {
      this.cfg = paramBOSHClientConfig;
      this.httpClient = initHttpClient(paramBOSHClientConfig);
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public HTTPResponse send(CMSessionParams paramCMSessionParams, AbstractBody paramAbstractBody)
  {
    this.lock.lock();
    try
    {
      if (this.httpClient == null)
        this.httpClient = initHttpClient(this.cfg);
      HttpClient localHttpClient = this.httpClient;
      BOSHClientConfig localBOSHClientConfig = this.cfg;
      return new ApacheHTTPResponse(localHttpClient, localBOSHClientConfig, paramCMSessionParams, paramAbstractBody);
    }
    finally
    {
      this.lock.unlock();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.ApacheHTTPSender
 * JD-Core Version:    0.6.2
 */