package com.kfb.c;

import java.io.IOException;

public final class b
{
  private static final byte[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
  private static final byte[] b = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
  private static final byte[] c = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
  private static final byte[] d = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
  private static final byte[] e = { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
  private static final byte[] f = { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };

  public static byte[] a(String paramString)
  {
    return b(paramString);
  }

  private static byte[] a(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramArrayOfByte == null)
      throw new NullPointerException();
    if (paramInt + 0 > paramArrayOfByte.length)
      throw new IllegalArgumentException();
    if (paramInt == 0)
      return new byte[0];
    if (paramInt < 4)
      throw new IllegalArgumentException();
    byte[] arrayOfByte1 = b;
    byte[] arrayOfByte2 = new byte[paramInt * 3 / 4];
    byte[] arrayOfByte3 = new byte[4];
    int i = 0;
    int j = 0;
    int k = 0;
    int i1;
    byte[] arrayOfByte5;
    int i4;
    label220: int m;
    if (i < paramInt + 0)
    {
      int n = arrayOfByte1[(0xFF & paramArrayOfByte[i])];
      if (n >= -5)
      {
        if (n < -1)
          break label469;
        i1 = j + 1;
        arrayOfByte3[j] = paramArrayOfByte[i];
        if (i1 <= 3)
          break label462;
        if (3 >= arrayOfByte3.length)
          throw new IllegalArgumentException();
        if ((k < 0) || (k + 2 >= arrayOfByte2.length))
          throw new IllegalArgumentException();
        arrayOfByte5 = b;
        if (arrayOfByte3[2] == 61)
        {
          arrayOfByte2[k] = ((byte)(((0xFF & arrayOfByte5[arrayOfByte3[0]]) << 18 | (0xFF & arrayOfByte5[arrayOfByte3[1]]) << 12) >>> 16));
          i4 = 1;
          m = i4 + k;
          if (paramArrayOfByte[i] != 61)
            break label441;
        }
      }
    }
    while (true)
    {
      byte[] arrayOfByte4 = new byte[m];
      System.arraycopy(arrayOfByte2, 0, arrayOfByte4, 0, m);
      return arrayOfByte4;
      if (arrayOfByte3[3] == 61)
      {
        int i5 = (0xFF & arrayOfByte5[arrayOfByte3[0]]) << 18 | (0xFF & arrayOfByte5[arrayOfByte3[1]]) << 12 | (0xFF & arrayOfByte5[arrayOfByte3[2]]) << 6;
        arrayOfByte2[k] = ((byte)(i5 >>> 16));
        arrayOfByte2[(k + 1)] = ((byte)(i5 >>> 8));
        i4 = 2;
        break label220;
      }
      int i3 = (0xFF & arrayOfByte5[arrayOfByte3[0]]) << 18 | (0xFF & arrayOfByte5[arrayOfByte3[1]]) << 12 | (0xFF & arrayOfByte5[arrayOfByte3[2]]) << 6 | 0xFF & arrayOfByte5[arrayOfByte3[3]];
      arrayOfByte2[k] = ((byte)(i3 >> 16));
      arrayOfByte2[(k + 1)] = ((byte)(i3 >> 8));
      arrayOfByte2[(k + 2)] = ((byte)i3);
      i4 = 3;
      break label220;
      throw new IOException();
      label441: int i2 = m;
      i1 = 0;
      while (true)
      {
        i++;
        k = i2;
        j = i1;
        break;
        label462: i2 = k;
        continue;
        label469: i1 = j;
        i2 = k;
      }
      m = k;
    }
  }

  // ERROR //
  private static byte[] b(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +13 -> 16
    //   6: new 154	java/lang/NullPointerException
    //   9: dup
    //   10: ldc 173
    //   12: invokespecial 176	java/lang/NullPointerException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: aload_0
    //   17: ldc 178
    //   19: invokevirtual 183	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   22: astore 23
    //   24: aload 23
    //   26: astore_3
    //   27: aload_3
    //   28: aload_3
    //   29: arraylength
    //   30: invokestatic 185	com/kfb/c/b:a	([BI)[B
    //   33: astore 4
    //   35: aload 4
    //   37: ifnull +127 -> 164
    //   40: aload 4
    //   42: arraylength
    //   43: iconst_4
    //   44: if_icmplt +120 -> 164
    //   47: ldc 186
    //   49: sipush 255
    //   52: aload 4
    //   54: iconst_0
    //   55: baload
    //   56: iand
    //   57: ldc 187
    //   59: aload 4
    //   61: iconst_1
    //   62: baload
    //   63: bipush 8
    //   65: ishl
    //   66: iand
    //   67: ior
    //   68: if_icmpne +96 -> 164
    //   71: sipush 2048
    //   74: newarray byte
    //   76: astore 5
    //   78: new 189	java/io/ByteArrayOutputStream
    //   81: dup
    //   82: invokespecial 190	java/io/ByteArrayOutputStream:<init>	()V
    //   85: astore 6
    //   87: new 192	java/io/ByteArrayInputStream
    //   90: dup
    //   91: aload 4
    //   93: invokespecial 195	java/io/ByteArrayInputStream:<init>	([B)V
    //   96: astore 7
    //   98: new 197	java/util/zip/GZIPInputStream
    //   101: dup
    //   102: aload 7
    //   104: invokespecial 200	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   107: astore 8
    //   109: aload 8
    //   111: aload 5
    //   113: invokevirtual 204	java/util/zip/GZIPInputStream:read	([B)I
    //   116: istore 18
    //   118: iload 18
    //   120: iflt +56 -> 176
    //   123: aload 6
    //   125: aload 5
    //   127: iconst_0
    //   128: iload 18
    //   130: invokevirtual 208	java/io/ByteArrayOutputStream:write	([BII)V
    //   133: goto -24 -> 109
    //   136: astore 13
    //   138: aload 8
    //   140: astore_1
    //   141: aload 7
    //   143: astore 14
    //   145: aload 13
    //   147: invokevirtual 211	java/io/IOException:printStackTrace	()V
    //   150: aload 6
    //   152: invokevirtual 214	java/io/ByteArrayOutputStream:close	()V
    //   155: aload_1
    //   156: invokevirtual 215	java/util/zip/GZIPInputStream:close	()V
    //   159: aload 14
    //   161: invokevirtual 216	java/io/ByteArrayInputStream:close	()V
    //   164: aload 4
    //   166: areturn
    //   167: astore_2
    //   168: aload_0
    //   169: invokevirtual 219	java/lang/String:getBytes	()[B
    //   172: astore_3
    //   173: goto -146 -> 27
    //   176: aload 6
    //   178: invokevirtual 222	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   181: astore 19
    //   183: aload 6
    //   185: invokevirtual 214	java/io/ByteArrayOutputStream:close	()V
    //   188: aload 8
    //   190: invokevirtual 215	java/util/zip/GZIPInputStream:close	()V
    //   193: aload 7
    //   195: invokevirtual 216	java/io/ByteArrayInputStream:close	()V
    //   198: aload 19
    //   200: areturn
    //   201: astore 22
    //   203: aload 19
    //   205: areturn
    //   206: astore 9
    //   208: aconst_null
    //   209: astore 6
    //   211: aconst_null
    //   212: astore 7
    //   214: aload 6
    //   216: invokevirtual 214	java/io/ByteArrayOutputStream:close	()V
    //   219: aload_1
    //   220: invokevirtual 215	java/util/zip/GZIPInputStream:close	()V
    //   223: aload 7
    //   225: invokevirtual 216	java/io/ByteArrayInputStream:close	()V
    //   228: aload 9
    //   230: athrow
    //   231: astore 20
    //   233: goto -45 -> 188
    //   236: astore 21
    //   238: goto -45 -> 193
    //   241: astore 15
    //   243: goto -88 -> 155
    //   246: astore 16
    //   248: goto -89 -> 159
    //   251: astore 17
    //   253: aload 4
    //   255: areturn
    //   256: astore 10
    //   258: goto -39 -> 219
    //   261: astore 11
    //   263: goto -40 -> 223
    //   266: astore 12
    //   268: goto -40 -> 228
    //   271: astore 9
    //   273: aconst_null
    //   274: astore_1
    //   275: aconst_null
    //   276: astore 7
    //   278: goto -64 -> 214
    //   281: astore 9
    //   283: aconst_null
    //   284: astore_1
    //   285: goto -71 -> 214
    //   288: astore 9
    //   290: aload 8
    //   292: astore_1
    //   293: goto -79 -> 214
    //   296: astore 9
    //   298: aload 14
    //   300: astore 7
    //   302: goto -88 -> 214
    //   305: astore 13
    //   307: aconst_null
    //   308: astore 6
    //   310: aconst_null
    //   311: astore_1
    //   312: aconst_null
    //   313: astore 14
    //   315: goto -170 -> 145
    //   318: astore 13
    //   320: aconst_null
    //   321: astore_1
    //   322: aconst_null
    //   323: astore 14
    //   325: goto -180 -> 145
    //   328: astore 13
    //   330: aload 7
    //   332: astore 14
    //   334: aconst_null
    //   335: astore_1
    //   336: goto -191 -> 145
    //
    // Exception table:
    //   from	to	target	type
    //   109	118	136	java/io/IOException
    //   123	133	136	java/io/IOException
    //   176	183	136	java/io/IOException
    //   16	24	167	java/io/UnsupportedEncodingException
    //   193	198	201	java/lang/Exception
    //   78	87	206	finally
    //   183	188	231	java/lang/Exception
    //   188	193	236	java/lang/Exception
    //   150	155	241	java/lang/Exception
    //   155	159	246	java/lang/Exception
    //   159	164	251	java/lang/Exception
    //   214	219	256	java/lang/Exception
    //   219	223	261	java/lang/Exception
    //   223	228	266	java/lang/Exception
    //   87	98	271	finally
    //   98	109	281	finally
    //   109	118	288	finally
    //   123	133	288	finally
    //   176	183	288	finally
    //   145	150	296	finally
    //   78	87	305	java/io/IOException
    //   87	98	318	java/io/IOException
    //   98	109	328	java/io/IOException
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kfb.c.b
 * JD-Core Version:    0.6.2
 */