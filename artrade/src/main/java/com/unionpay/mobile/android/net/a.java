package com.unionpay.mobile.android.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class a
  implements LayeredSocketFactory, SocketFactory
{
  private SSLContext a = null;

  private static SSLContext a()
    throws IOException
  {
    try
    {
      SSLContext localSSLContext = SSLContext.getInstance("TLS");
      TrustManager[] arrayOfTrustManager = new TrustManager[1];
      arrayOfTrustManager[0] = new b();
      localSSLContext.init(null, arrayOfTrustManager, null);
      return localSSLContext;
    }
    catch (Exception localException)
    {
      throw new IOException(localException.getMessage());
    }
  }

  private SSLContext b()
    throws IOException
  {
    if (this.a == null)
      this.a = a();
    return this.a;
  }

  public Socket connectSocket(Socket paramSocket, String paramString, int paramInt1, InetAddress paramInetAddress, int paramInt2, HttpParams paramHttpParams)
    throws IOException, UnknownHostException, ConnectTimeoutException
  {
    int i = HttpConnectionParams.getConnectionTimeout(paramHttpParams);
    int j = HttpConnectionParams.getSoTimeout(paramHttpParams);
    InetSocketAddress localInetSocketAddress = new InetSocketAddress(paramString, paramInt1);
    if (paramSocket != null);
    for (Socket localSocket = paramSocket; ; localSocket = createSocket())
    {
      SSLSocket localSSLSocket = (SSLSocket)localSocket;
      if ((paramInetAddress != null) || (paramInt2 > 0))
      {
        if (paramInt2 < 0)
          paramInt2 = 0;
        localSSLSocket.bind(new InetSocketAddress(paramInetAddress, paramInt2));
      }
      localSSLSocket.connect(localInetSocketAddress, i);
      localSSLSocket.setSoTimeout(j);
      return localSSLSocket;
    }
  }

  public Socket createSocket()
    throws IOException
  {
    return b().getSocketFactory().createSocket();
  }

  public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    throws IOException, UnknownHostException
  {
    return b().getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
  }

  public boolean equals(Object paramObject)
  {
    return (paramObject != null) && (paramObject.getClass().equals(a.class));
  }

  public int hashCode()
  {
    return a.class.hashCode();
  }

  public boolean isSecure(Socket paramSocket)
    throws IllegalArgumentException
  {
    return true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.net.a
 * JD-Core Version:    0.6.2
 */