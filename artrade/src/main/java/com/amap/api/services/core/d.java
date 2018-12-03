package com.amap.api.services.core;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class d
{
  public static byte[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };

  public static String a()
  {
    String str = "";
    for (int i = 0; i < 8; i++)
    {
      int j = (int)(33.0D + 94.0D * Math.random());
      str = str + (char)j;
    }
    return str;
  }

  public static PublicKey a(Context paramContext)
    throws Exception
  {
    AssetManager localAssetManager = paramContext.getAssets();
    try
    {
      InputStream localInputStream = localAssetManager.open("search_public_key.der");
      CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
      KeyFactory localKeyFactory = KeyFactory.getInstance("RSA");
      Certificate localCertificate = localCertificateFactory.generateCertificate(localInputStream);
      localInputStream.close();
      PublicKey localPublicKey = localKeyFactory.generatePublic(new X509EncodedKeySpec(localCertificate.getPublicKey().getEncoded()));
      return localPublicKey;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new Exception("无此算法");
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      throw new Exception("公钥非法");
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new Exception("公钥数据为空");
    }
    catch (CertificateException localCertificateException)
    {
      return null;
    }
    catch (IOException localIOException)
    {
      label105: break label105;
    }
  }

  // ERROR //
  public static byte[] a(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 131	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 132	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: new 134	java/util/zip/GZIPOutputStream
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 137	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_2
    //   18: aload_0
    //   19: invokevirtual 140	java/util/zip/GZIPOutputStream:write	([B)V
    //   22: aload_2
    //   23: invokevirtual 143	java/util/zip/GZIPOutputStream:finish	()V
    //   26: aload_2
    //   27: invokevirtual 144	java/util/zip/GZIPOutputStream:close	()V
    //   30: aload_1
    //   31: invokevirtual 147	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   34: astore 6
    //   36: aload 6
    //   38: astore 4
    //   40: aload_1
    //   41: invokevirtual 148	java/io/ByteArrayOutputStream:close	()V
    //   44: aload 4
    //   46: areturn
    //   47: astore_3
    //   48: aconst_null
    //   49: astore 4
    //   51: aload_3
    //   52: astore 5
    //   54: aload 5
    //   56: invokevirtual 151	java/lang/Exception:printStackTrace	()V
    //   59: aload 4
    //   61: areturn
    //   62: astore 5
    //   64: goto -10 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   0	36	47	java/lang/Exception
    //   40	44	62	java/lang/Exception
  }

  public static byte[] a(byte[] paramArrayOfByte, Key paramKey)
    throws Exception
  {
    Cipher localCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    localCipher.init(1, paramKey);
    return localCipher.doFinal(paramArrayOfByte);
  }

  public static byte[] a(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws Exception
  {
    IvParameterSpec localIvParameterSpec = new IvParameterSpec(a);
    SecretKeySpec localSecretKeySpec = new SecretKeySpec(paramArrayOfByte1, "DES");
    Cipher localCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    localCipher.init(1, localSecretKeySpec, localIvParameterSpec);
    return localCipher.doFinal(paramArrayOfByte2);
  }

  public static String b(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
      return a.a(paramArrayOfByte);
    return "";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.d
 * JD-Core Version:    0.6.2
 */