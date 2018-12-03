package com.unionpay.mobile.android.net;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

public final class b
  implements X509TrustManager
{
  private X509TrustManager a = null;

  public b()
    throws NoSuchAlgorithmException, KeyStoreException
  {
    TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    localTrustManagerFactory.init(null);
    TrustManager[] arrayOfTrustManager = localTrustManagerFactory.getTrustManagers();
    if (arrayOfTrustManager.length == 0)
      throw new NoSuchAlgorithmException("no trust manager found");
    this.a = ((X509TrustManager)arrayOfTrustManager[0]);
  }

  public final void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
    this.a.checkClientTrusted(paramArrayOfX509Certificate, paramString);
  }

  public final void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    throws CertificateException
  {
    this.a.checkServerTrusted(paramArrayOfX509Certificate, paramString);
    try
    {
      X500Principal localX500Principal1 = paramArrayOfX509Certificate[0].getIssuerX500Principal();
      if ((!localX500Principal1.getName().equals(HttpNative.a().getIssuer(0))) && (!localX500Principal1.getName().equals(HttpNative.a().getIssuer(1))) && (!localX500Principal1.getName().equals(HttpNative.a().getIssuer(2))))
        throw new CertificateException();
    }
    catch (Exception localException)
    {
      throw new CertificateException();
    }
    X500Principal localX500Principal2 = paramArrayOfX509Certificate[0].getSubjectX500Principal();
    if ((!localX500Principal2.getName().equals(HttpNative.a().getSubject(0))) && (!localX500Principal2.getName().equals(HttpNative.a().getSubject(1))) && (!localX500Principal2.getName().equals(HttpNative.a().getSubject(2))))
      throw new CertificateException();
  }

  public final X509Certificate[] getAcceptedIssuers()
  {
    return this.a.getAcceptedIssuers();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.net.b
 * JD-Core Version:    0.6.2
 */