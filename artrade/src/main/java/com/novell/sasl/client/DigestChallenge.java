package com.novell.sasl.client;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

class DigestChallenge
{
  private static final int CIPHER_3DES = 1;
  private static final int CIPHER_DES = 2;
  private static final int CIPHER_RC4 = 8;
  private static final int CIPHER_RC4_40 = 4;
  private static final int CIPHER_RC4_56 = 16;
  private static final int CIPHER_RECOGNIZED_MASK = 31;
  private static final int CIPHER_UNRECOGNIZED = 32;
  public static final int QOP_AUTH = 1;
  public static final int QOP_AUTH_CONF = 4;
  public static final int QOP_AUTH_INT = 2;
  public static final int QOP_UNRECOGNIZED = 8;
  private String m_algorithm = null;
  private String m_characterSet = null;
  private int m_cipherOptions = 0;
  private int m_maxBuf = -1;
  private String m_nonce = null;
  private int m_qop = 0;
  private ArrayList m_realms = new ArrayList(5);
  private boolean m_staleFlag = false;

  DigestChallenge(byte[] paramArrayOfByte)
    throws SaslException
  {
    DirectiveList localDirectiveList = new DirectiveList(paramArrayOfByte);
    try
    {
      localDirectiveList.parseDirectives();
      checkSemantics(localDirectiveList);
      return;
    }
    catch (SaslException localSaslException)
    {
    }
  }

  void checkSemantics(DirectiveList paramDirectiveList)
    throws SaslException
  {
    Iterator localIterator = paramDirectiveList.getIterator();
    while (localIterator.hasNext())
    {
      ParsedDirective localParsedDirective = (ParsedDirective)localIterator.next();
      String str = localParsedDirective.getName();
      if (str.equals("realm"))
        handleRealm(localParsedDirective);
      else if (str.equals("nonce"))
        handleNonce(localParsedDirective);
      else if (str.equals("qop"))
        handleQop(localParsedDirective);
      else if (str.equals("maxbuf"))
        handleMaxbuf(localParsedDirective);
      else if (str.equals("charset"))
        handleCharset(localParsedDirective);
      else if (str.equals("algorithm"))
        handleAlgorithm(localParsedDirective);
      else if (str.equals("cipher"))
        handleCipher(localParsedDirective);
      else if (str.equals("stale"))
        handleStale(localParsedDirective);
    }
    if (-1 == this.m_maxBuf)
      this.m_maxBuf = 65536;
    if (this.m_qop == 0)
      this.m_qop = 1;
    do
    {
      return;
      if ((0x1 & this.m_qop) != 1)
        throw new SaslException("Only qop-auth is supported by client");
      if (((0x4 & this.m_qop) == 4) && ((0x1F & this.m_cipherOptions) == 0))
        throw new SaslException("Invalid cipher options");
      if (this.m_nonce == null)
        throw new SaslException("Missing nonce directive");
      if (this.m_staleFlag)
        throw new SaslException("Unexpected stale flag");
    }
    while (this.m_algorithm != null);
    throw new SaslException("Missing algorithm directive");
  }

  public String getAlgorithm()
  {
    return this.m_algorithm;
  }

  public String getCharacterSet()
  {
    return this.m_characterSet;
  }

  public int getCipherOptions()
  {
    return this.m_cipherOptions;
  }

  public int getMaxBuf()
  {
    return this.m_maxBuf;
  }

  public String getNonce()
  {
    return this.m_nonce;
  }

  public int getQop()
  {
    return this.m_qop;
  }

  public ArrayList getRealms()
  {
    return this.m_realms;
  }

  public boolean getStaleFlag()
  {
    return this.m_staleFlag;
  }

  void handleAlgorithm(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (this.m_algorithm != null)
      throw new SaslException("Too many algorithm directives.");
    this.m_algorithm = paramParsedDirective.getValue();
    if (!"md5-sess".equals(this.m_algorithm))
      throw new SaslException("Invalid algorithm directive value: " + this.m_algorithm);
  }

  void handleCharset(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (this.m_characterSet != null)
      throw new SaslException("Too many charset directives.");
    this.m_characterSet = paramParsedDirective.getValue();
    if (!this.m_characterSet.equals("utf-8"))
      throw new SaslException("Invalid character encoding directive");
  }

  void handleCipher(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (this.m_cipherOptions != 0)
      throw new SaslException("Too many cipher directives.");
    TokenParser localTokenParser = new TokenParser(paramParsedDirective.getValue());
    localTokenParser.parseToken();
    String str = localTokenParser.parseToken();
    if (str != null)
    {
      if ("3des".equals(str))
        this.m_cipherOptions = (0x1 | this.m_cipherOptions);
      while (true)
      {
        str = localTokenParser.parseToken();
        break;
        if ("des".equals(str))
          this.m_cipherOptions = (0x2 | this.m_cipherOptions);
        else if ("rc4-40".equals(str))
          this.m_cipherOptions = (0x4 | this.m_cipherOptions);
        else if ("rc4".equals(str))
          this.m_cipherOptions = (0x8 | this.m_cipherOptions);
        else if ("rc4-56".equals(str))
          this.m_cipherOptions = (0x10 | this.m_cipherOptions);
        else
          this.m_cipherOptions = (0x20 | this.m_cipherOptions);
      }
    }
    if (this.m_cipherOptions == 0)
      this.m_cipherOptions = 32;
  }

  void handleMaxbuf(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (-1 != this.m_maxBuf)
      throw new SaslException("Too many maxBuf directives.");
    this.m_maxBuf = Integer.parseInt(paramParsedDirective.getValue());
    if (this.m_maxBuf == 0)
      throw new SaslException("Max buf value must be greater than zero.");
  }

  void handleNonce(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (this.m_nonce != null)
      throw new SaslException("Too many nonce values.");
    this.m_nonce = paramParsedDirective.getValue();
  }

  void handleQop(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (this.m_qop != 0)
      throw new SaslException("Too many qop directives.");
    TokenParser localTokenParser = new TokenParser(paramParsedDirective.getValue());
    String str = localTokenParser.parseToken();
    if (str != null)
    {
      if (str.equals("auth"))
        this.m_qop = (0x1 | this.m_qop);
      while (true)
      {
        str = localTokenParser.parseToken();
        break;
        if (str.equals("auth-int"))
          this.m_qop = (0x2 | this.m_qop);
        else if (str.equals("auth-conf"))
          this.m_qop = (0x4 | this.m_qop);
        else
          this.m_qop = (0x8 | this.m_qop);
      }
    }
  }

  void handleRealm(ParsedDirective paramParsedDirective)
  {
    this.m_realms.add(paramParsedDirective.getValue());
  }

  void handleStale(ParsedDirective paramParsedDirective)
    throws SaslException
  {
    if (this.m_staleFlag)
      throw new SaslException("Too many stale directives.");
    if ("true".equals(paramParsedDirective.getValue()))
    {
      this.m_staleFlag = true;
      return;
    }
    throw new SaslException("Invalid stale directive value: " + paramParsedDirective.getValue());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.novell.sasl.client.DigestChallenge
 * JD-Core Version:    0.6.2
 */