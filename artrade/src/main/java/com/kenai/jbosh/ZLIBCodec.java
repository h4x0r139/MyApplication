package com.kenai.jbosh;

final class ZLIBCodec
{
  private static final int BUFFER_SIZE = 512;

  // ERROR //
  public static byte[] decode(byte[] paramArrayOfByte)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 17	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 20	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_1
    //   9: new 22	java/io/ByteArrayOutputStream
    //   12: dup
    //   13: invokespecial 23	java/io/ByteArrayOutputStream:<init>	()V
    //   16: astore_2
    //   17: new 25	java/util/zip/InflaterInputStream
    //   20: dup
    //   21: aload_1
    //   22: invokespecial 28	java/util/zip/InflaterInputStream:<init>	(Ljava/io/InputStream;)V
    //   25: astore_3
    //   26: sipush 512
    //   29: newarray byte
    //   31: astore 5
    //   33: aload_3
    //   34: aload 5
    //   36: invokevirtual 32	java/util/zip/InflaterInputStream:read	([B)I
    //   39: istore 6
    //   41: iload 6
    //   43: ifle +12 -> 55
    //   46: aload_2
    //   47: aload 5
    //   49: iconst_0
    //   50: iload 6
    //   52: invokevirtual 36	java/io/ByteArrayOutputStream:write	([BII)V
    //   55: iload 6
    //   57: ifge -24 -> 33
    //   60: aload_2
    //   61: invokevirtual 40	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   64: astore 7
    //   66: aload_3
    //   67: invokevirtual 43	java/util/zip/InflaterInputStream:close	()V
    //   70: aload_2
    //   71: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   74: aload 7
    //   76: areturn
    //   77: astore 4
    //   79: aconst_null
    //   80: astore_3
    //   81: aload_3
    //   82: invokevirtual 43	java/util/zip/InflaterInputStream:close	()V
    //   85: aload_2
    //   86: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   89: aload 4
    //   91: athrow
    //   92: astore 4
    //   94: goto -13 -> 81
    //
    // Exception table:
    //   from	to	target	type
    //   17	26	77	finally
    //   26	33	92	finally
    //   33	41	92	finally
    //   46	55	92	finally
    //   60	66	92	finally
  }

  // ERROR //
  public static byte[] encode(byte[] paramArrayOfByte)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 22	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 23	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: new 47	java/util/zip/DeflaterOutputStream
    //   11: dup
    //   12: aload_1
    //   13: invokespecial 50	java/util/zip/DeflaterOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   16: astore_2
    //   17: aload_2
    //   18: aload_0
    //   19: invokevirtual 52	java/util/zip/DeflaterOutputStream:write	([B)V
    //   22: aload_2
    //   23: invokevirtual 53	java/util/zip/DeflaterOutputStream:close	()V
    //   26: aload_1
    //   27: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   30: aload_1
    //   31: invokevirtual 40	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   34: astore 4
    //   36: aload_2
    //   37: invokevirtual 53	java/util/zip/DeflaterOutputStream:close	()V
    //   40: aload_1
    //   41: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   44: aload 4
    //   46: areturn
    //   47: astore_3
    //   48: aconst_null
    //   49: astore_2
    //   50: aload_2
    //   51: invokevirtual 53	java/util/zip/DeflaterOutputStream:close	()V
    //   54: aload_1
    //   55: invokevirtual 44	java/io/ByteArrayOutputStream:close	()V
    //   58: aload_3
    //   59: athrow
    //   60: astore_3
    //   61: goto -11 -> 50
    //
    // Exception table:
    //   from	to	target	type
    //   8	17	47	finally
    //   17	36	60	finally
  }

  public static String getID()
  {
    return "deflate";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.ZLIBCodec
 * JD-Core Version:    0.6.2
 */