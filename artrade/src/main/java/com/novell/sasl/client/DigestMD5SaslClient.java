package com.novell.sasl.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;
import org.apache.harmony.javax.security.auth.callback.Callback;
import org.apache.harmony.javax.security.auth.callback.CallbackHandler;
import org.apache.harmony.javax.security.auth.callback.NameCallback;
import org.apache.harmony.javax.security.auth.callback.PasswordCallback;
import org.apache.harmony.javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.harmony.javax.security.sasl.RealmCallback;
import org.apache.harmony.javax.security.sasl.RealmChoiceCallback;
import org.apache.harmony.javax.security.sasl.SaslClient;
import org.apache.harmony.javax.security.sasl.SaslException;

public class DigestMD5SaslClient
  implements SaslClient
{
  private static final String DIGEST_METHOD = "AUTHENTICATE";
  private static final int NONCE_BYTE_COUNT = 32;
  private static final int NONCE_HEX_COUNT = 64;
  private static final int STATE_DIGEST_RESPONSE_SENT = 1;
  private static final int STATE_DISPOSED = 4;
  private static final int STATE_INITIAL = 0;
  private static final int STATE_INVALID_SERVER_RESPONSE = 3;
  private static final int STATE_VALID_SERVER_RESPONSE = 2;
  private char[] m_HA1 = null;
  private String m_authorizationId = "";
  private CallbackHandler m_cbh;
  private String m_clientNonce = "";
  private DigestChallenge m_dc;
  private String m_digestURI;
  private String m_name = "";
  private Map m_props;
  private String m_protocol = "";
  private String m_qopValue = "";
  private String m_realm = "";
  private String m_serverName = "";
  private int m_state;

  private DigestMD5SaslClient(String paramString1, String paramString2, String paramString3, Map paramMap, CallbackHandler paramCallbackHandler)
  {
    this.m_authorizationId = paramString1;
    this.m_protocol = paramString2;
    this.m_serverName = paramString3;
    this.m_props = paramMap;
    this.m_cbh = paramCallbackHandler;
    this.m_state = 0;
  }

  private String createDigestResponse(byte[] paramArrayOfByte)
    throws SaslException
  {
    StringBuffer localStringBuffer = new StringBuffer(512);
    this.m_dc = new DigestChallenge(paramArrayOfByte);
    this.m_digestURI = (this.m_protocol + "/" + this.m_serverName);
    Callback[] arrayOfCallback;
    ArrayList localArrayList;
    int i;
    if ((0x1 & this.m_dc.getQop()) == 1)
    {
      this.m_qopValue = "auth";
      arrayOfCallback = new Callback[3];
      localArrayList = this.m_dc.getRealms();
      i = localArrayList.size();
      if (i != 0)
        break label274;
      arrayOfCallback[0] = new RealmCallback("Realm");
      arrayOfCallback[1] = new PasswordCallback("Password", false);
      if ((this.m_authorizationId != null) && (this.m_authorizationId.length() != 0))
        break label337;
      arrayOfCallback[2] = new NameCallback("Name");
    }
    while (true)
    {
      try
      {
        this.m_cbh.handle(arrayOfCallback);
        if (i <= 1)
          break label402;
        int[] arrayOfInt = ((RealmChoiceCallback)arrayOfCallback[0]).getSelectedIndexes();
        if (arrayOfInt.length > 0)
        {
          this.m_realm = ((RealmChoiceCallback)arrayOfCallback[0]).getChoices()[arrayOfInt[0]];
          this.m_clientNonce = getClientNonce();
          this.m_name = ((NameCallback)arrayOfCallback[2]).getName();
          if (this.m_name == null)
            this.m_name = ((NameCallback)arrayOfCallback[2]).getDefaultName();
          if (this.m_name != null)
            break label418;
          throw new SaslException("No user name was specified.");
          throw new SaslException("Client only supports qop of 'auth'");
          label274: if (i == 1)
          {
            arrayOfCallback[0] = new RealmCallback("Realm", (String)localArrayList.get(0));
            break;
          }
          arrayOfCallback[0] = new RealmChoiceCallback("Realm", (String[])(String[])localArrayList.toArray(new String[i]), 0, false);
          break;
          label337: arrayOfCallback[2] = new NameCallback("Name", this.m_authorizationId);
          continue;
        }
      }
      catch (UnsupportedCallbackException localUnsupportedCallbackException)
      {
        throw new SaslException("Handler does not support necessary callbacks", localUnsupportedCallbackException);
      }
      catch (IOException localIOException)
      {
        throw new SaslException("IO exception in CallbackHandler.", localIOException);
      }
      this.m_realm = ((RealmChoiceCallback)arrayOfCallback[0]).getChoices()[0];
      continue;
      label402: this.m_realm = ((RealmCallback)arrayOfCallback[0]).getText();
    }
    label418: this.m_HA1 = DigestCalcHA1(this.m_dc.getAlgorithm(), this.m_name, this.m_realm, new String(((PasswordCallback)arrayOfCallback[1]).getPassword()), this.m_dc.getNonce(), this.m_clientNonce);
    char[] arrayOfChar = DigestCalcResponse(this.m_HA1, this.m_dc.getNonce(), "00000001", this.m_clientNonce, this.m_qopValue, "AUTHENTICATE", this.m_digestURI, true);
    localStringBuffer.append("username=\"");
    localStringBuffer.append(this.m_authorizationId);
    if (this.m_realm.length() != 0)
    {
      localStringBuffer.append("\",realm=\"");
      localStringBuffer.append(this.m_realm);
    }
    localStringBuffer.append("\",cnonce=\"");
    localStringBuffer.append(this.m_clientNonce);
    localStringBuffer.append("\",nc=");
    localStringBuffer.append("00000001");
    localStringBuffer.append(",qop=");
    localStringBuffer.append(this.m_qopValue);
    localStringBuffer.append(",digest-uri=\"");
    localStringBuffer.append(this.m_digestURI);
    localStringBuffer.append("\",response=");
    localStringBuffer.append(arrayOfChar);
    localStringBuffer.append(",charset=utf-8,nonce=\"");
    localStringBuffer.append(this.m_dc.getNonce());
    localStringBuffer.append("\"");
    return localStringBuffer.toString();
  }

  public static SaslClient getClient(String paramString1, String paramString2, String paramString3, Map paramMap, CallbackHandler paramCallbackHandler)
  {
    String str1 = (String)paramMap.get("javax.security.sasl.qop");
    ((String)paramMap.get("javax.security.sasl.strength"));
    String str2 = (String)paramMap.get("javax.security.sasl.server.authentication");
    if ((str1 != null) && (!"auth".equals(str1)))
      return null;
    if ((str2 != null) && (!"false".equals(str2)))
      return null;
    if (paramCallbackHandler == null)
      return null;
    return new DigestMD5SaslClient(paramString1, paramString2, paramString3, paramMap, paramCallbackHandler);
  }

  private static char getHexChar(byte paramByte)
  {
    switch (paramByte)
    {
    default:
      return 'Z';
    case 0:
      return '0';
    case 1:
      return '1';
    case 2:
      return '2';
    case 3:
      return '3';
    case 4:
      return '4';
    case 5:
      return '5';
    case 6:
      return '6';
    case 7:
      return '7';
    case 8:
      return '8';
    case 9:
      return '9';
    case 10:
      return 'a';
    case 11:
      return 'b';
    case 12:
      return 'c';
    case 13:
      return 'd';
    case 14:
      return 'e';
    case 15:
    }
    return 'f';
  }

  char[] DigestCalcHA1(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
    throws SaslException
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString2.getBytes("UTF-8"));
      localMessageDigest.update(":".getBytes("UTF-8"));
      localMessageDigest.update(paramString3.getBytes("UTF-8"));
      localMessageDigest.update(":".getBytes("UTF-8"));
      localMessageDigest.update(paramString4.getBytes("UTF-8"));
      Object localObject = localMessageDigest.digest();
      if ("md5-sess".equals(paramString1))
      {
        localMessageDigest.update((byte[])localObject);
        localMessageDigest.update(":".getBytes("UTF-8"));
        localMessageDigest.update(paramString5.getBytes("UTF-8"));
        localMessageDigest.update(":".getBytes("UTF-8"));
        localMessageDigest.update(paramString6.getBytes("UTF-8"));
        byte[] arrayOfByte = localMessageDigest.digest();
        localObject = arrayOfByte;
      }
      return convertToHex((byte[])localObject);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new SaslException("No provider found for MD5 hash", localNoSuchAlgorithmException);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new SaslException("UTF-8 encoding not supported by platform.", localUnsupportedEncodingException);
    }
  }

  char[] DigestCalcResponse(char[] paramArrayOfChar, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, boolean paramBoolean)
    throws SaslException
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      if (paramBoolean)
        localMessageDigest.update(paramString5.getBytes("UTF-8"));
      localMessageDigest.update(":".getBytes("UTF-8"));
      localMessageDigest.update(paramString6.getBytes("UTF-8"));
      if ("auth-int".equals(paramString4))
      {
        localMessageDigest.update(":".getBytes("UTF-8"));
        localMessageDigest.update("00000000000000000000000000000000".getBytes("UTF-8"));
      }
      char[] arrayOfChar = convertToHex(localMessageDigest.digest());
      localMessageDigest.update(new String(paramArrayOfChar).getBytes("UTF-8"));
      localMessageDigest.update(":".getBytes("UTF-8"));
      localMessageDigest.update(paramString1.getBytes("UTF-8"));
      localMessageDigest.update(":".getBytes("UTF-8"));
      if (paramString4.length() > 0)
      {
        localMessageDigest.update(paramString2.getBytes("UTF-8"));
        localMessageDigest.update(":".getBytes("UTF-8"));
        localMessageDigest.update(paramString3.getBytes("UTF-8"));
        localMessageDigest.update(":".getBytes("UTF-8"));
        localMessageDigest.update(paramString4.getBytes("UTF-8"));
        localMessageDigest.update(":".getBytes("UTF-8"));
      }
      localMessageDigest.update(new String(arrayOfChar).getBytes("UTF-8"));
      byte[] arrayOfByte = localMessageDigest.digest();
      return convertToHex(arrayOfByte);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new SaslException("No provider found for MD5 hash", localNoSuchAlgorithmException);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new SaslException("UTF-8 encoding not supported by platform.", localUnsupportedEncodingException);
    }
  }

  boolean checkServerResponseAuth(byte[] paramArrayOfByte)
    throws SaslException
  {
    ResponseAuth localResponseAuth = new ResponseAuth(paramArrayOfByte);
    return new String(DigestCalcResponse(this.m_HA1, this.m_dc.getNonce(), "00000001", this.m_clientNonce, this.m_qopValue, "AUTHENTICATE", this.m_digestURI, false)).equals(localResponseAuth.getResponseValue());
  }

  char[] convertToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[32];
    for (int i = 0; i < 16; i++)
    {
      arrayOfChar[(i * 2)] = getHexChar((byte)((0xF0 & paramArrayOfByte[i]) >> 4));
      arrayOfChar[(1 + i * 2)] = getHexChar((byte)(0xF & paramArrayOfByte[i]));
    }
    return arrayOfChar;
  }

  public void dispose()
    throws SaslException
  {
    if (this.m_state != 4)
      this.m_state = 4;
  }

  public byte[] evaluateChallenge(byte[] paramArrayOfByte)
    throws SaslException
  {
    switch (this.m_state)
    {
    default:
      throw new SaslException("Unknown client state.");
    case 0:
      if (paramArrayOfByte.length == 0)
        throw new SaslException("response = byte[0]");
      try
      {
        byte[] arrayOfByte = createDigestResponse(paramArrayOfByte).getBytes("UTF-8");
        this.m_state = 1;
        return arrayOfByte;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new SaslException("UTF-8 encoding not suppported by platform", localUnsupportedEncodingException);
      }
    case 1:
      if (checkServerResponseAuth(paramArrayOfByte))
      {
        this.m_state = 2;
        return null;
      }
      this.m_state = 3;
      throw new SaslException("Could not validate response-auth value from server");
    case 2:
    case 3:
      throw new SaslException("Authentication sequence is complete");
    case 4:
    }
    throw new SaslException("Client has been disposed");
  }

  String getClientNonce()
    throws SaslException
  {
    byte[] arrayOfByte = new byte[32];
    char[] arrayOfChar = new char[64];
    try
    {
      SecureRandom.getInstance("SHA1PRNG").nextBytes(arrayOfByte);
      for (int i = 0; i < 32; i++)
      {
        arrayOfChar[(i * 2)] = getHexChar((byte)(0xF & arrayOfByte[i]));
        arrayOfChar[(1 + i * 2)] = getHexChar((byte)((0xF0 & arrayOfByte[i]) >> 4));
      }
      String str = new String(arrayOfChar);
      return str;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new SaslException("No random number generator available", localNoSuchAlgorithmException);
    }
  }

  public String getMechanismName()
  {
    return "DIGEST-MD5";
  }

  public Object getNegotiatedProperty(String paramString)
  {
    if (this.m_state != 2)
      throw new IllegalStateException("getNegotiatedProperty: authentication exchange not complete.");
    if ("javax.security.sasl.qop".equals(paramString))
      return "auth";
    return null;
  }

  public boolean hasInitialResponse()
  {
    return false;
  }

  public boolean isComplete()
  {
    return (this.m_state == 2) || (this.m_state == 3) || (this.m_state == 4);
  }

  public byte[] unwrap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SaslException
  {
    throw new IllegalStateException("unwrap: QOP has neither integrity nor privacy>");
  }

  public byte[] wrap(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SaslException
  {
    throw new IllegalStateException("wrap: QOP has neither integrity nor privacy>");
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.novell.sasl.client.DigestMD5SaslClient
 * JD-Core Version:    0.6.2
 */