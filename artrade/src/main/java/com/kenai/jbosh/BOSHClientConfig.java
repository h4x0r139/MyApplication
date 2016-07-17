package com.kenai.jbosh;

import java.net.URI;
import javax.net.ssl.SSLContext;

public final class BOSHClientConfig
{
  private final boolean compressionEnabled;
  private final String from;
  private final String lang;
  private final String proxyHost;
  private final int proxyPort;
  private final String route;
  private final SSLContext sslContext;
  private final String to;
  private final URI uri;

  private BOSHClientConfig(URI paramURI, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt, SSLContext paramSSLContext, boolean paramBoolean)
  {
    this.uri = paramURI;
    this.to = paramString1;
    this.from = paramString2;
    this.lang = paramString3;
    this.route = paramString4;
    this.proxyHost = paramString5;
    this.proxyPort = paramInt;
    this.sslContext = paramSSLContext;
    this.compressionEnabled = paramBoolean;
  }

  public String getFrom()
  {
    return this.from;
  }

  public String getLang()
  {
    return this.lang;
  }

  public String getProxyHost()
  {
    return this.proxyHost;
  }

  public int getProxyPort()
  {
    return this.proxyPort;
  }

  public String getRoute()
  {
    return this.route;
  }

  public SSLContext getSSLContext()
  {
    return this.sslContext;
  }

  public String getTo()
  {
    return this.to;
  }

  public URI getURI()
  {
    return this.uri;
  }

  boolean isCompressionEnabled()
  {
    return this.compressionEnabled;
  }

  public static final class Builder
  {
    private Boolean bCompression;
    private final String bDomain;
    private String bFrom;
    private String bLang;
    private String bProxyHost;
    private int bProxyPort;
    private String bRoute;
    private SSLContext bSSLContext;
    private final URI bURI;

    private Builder(URI paramURI, String paramString)
    {
      this.bURI = paramURI;
      this.bDomain = paramString;
    }

    public static Builder create(BOSHClientConfig paramBOSHClientConfig)
    {
      Builder localBuilder = new Builder(paramBOSHClientConfig.getURI(), paramBOSHClientConfig.getTo());
      localBuilder.bFrom = paramBOSHClientConfig.getFrom();
      localBuilder.bLang = paramBOSHClientConfig.getLang();
      localBuilder.bRoute = paramBOSHClientConfig.getRoute();
      localBuilder.bProxyHost = paramBOSHClientConfig.getProxyHost();
      localBuilder.bProxyPort = paramBOSHClientConfig.getProxyPort();
      localBuilder.bSSLContext = paramBOSHClientConfig.getSSLContext();
      localBuilder.bCompression = Boolean.valueOf(paramBOSHClientConfig.isCompressionEnabled());
      return localBuilder;
    }

    public static Builder create(URI paramURI, String paramString)
    {
      if (paramURI == null)
        throw new IllegalArgumentException("Connection manager URI must not be null");
      if (paramString == null)
        throw new IllegalArgumentException("Target domain must not be null");
      String str = paramURI.getScheme();
      if ((!"http".equals(str)) && (!"https".equals(str)))
        throw new IllegalArgumentException("Only 'http' and 'https' URI are allowed");
      return new Builder(paramURI, paramString);
    }

    public BOSHClientConfig build()
    {
      String str;
      int i;
      if (this.bLang == null)
      {
        str = "en";
        if (this.bProxyHost != null)
          break label72;
        i = 0;
        label19: if (this.bCompression != null)
          break label80;
      }
      label72: label80: for (boolean bool = false; ; bool = this.bCompression.booleanValue())
      {
        return new BOSHClientConfig(this.bURI, this.bDomain, this.bFrom, str, this.bRoute, this.bProxyHost, i, this.bSSLContext, bool, null);
        str = this.bLang;
        break;
        i = this.bProxyPort;
        break label19;
      }
    }

    public Builder setCompressionEnabled(boolean paramBoolean)
    {
      this.bCompression = Boolean.valueOf(paramBoolean);
      return this;
    }

    public Builder setFrom(String paramString)
    {
      if (paramString == null)
        throw new IllegalArgumentException("Client ID must not be null");
      this.bFrom = paramString;
      return this;
    }

    public Builder setProxy(String paramString, int paramInt)
    {
      if ((paramString == null) || (paramString.length() == 0))
        throw new IllegalArgumentException("Proxy host name cannot be null or empty");
      if (paramInt <= 0)
        throw new IllegalArgumentException("Proxy port must be > 0");
      this.bProxyHost = paramString;
      this.bProxyPort = paramInt;
      return this;
    }

    public Builder setRoute(String paramString1, String paramString2, int paramInt)
    {
      if (paramString1 == null)
        throw new IllegalArgumentException("Protocol cannot be null");
      if (paramString1.contains(":"))
        throw new IllegalArgumentException("Protocol cannot contain the ':' character");
      if (paramString2 == null)
        throw new IllegalArgumentException("Host cannot be null");
      if (paramString2.contains(":"))
        throw new IllegalArgumentException("Host cannot contain the ':' character");
      if (paramInt <= 0)
        throw new IllegalArgumentException("Port number must be > 0");
      this.bRoute = (paramString1 + ":" + paramString2 + ":" + paramInt);
      return this;
    }

    public Builder setSSLContext(SSLContext paramSSLContext)
    {
      if (paramSSLContext == null)
        throw new IllegalArgumentException("SSL context cannot be null");
      this.bSSLContext = paramSSLContext;
      return this;
    }

    public Builder setXMLLang(String paramString)
    {
      if (paramString == null)
        throw new IllegalArgumentException("Default language ID must not be null");
      this.bLang = paramString;
      return this;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BOSHClientConfig
 * JD-Core Version:    0.6.2
 */