package com.amap.api.services.core;

public class a
{
  private static final char[] a = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = paramArrayOfByte.length;
    int j = 0;
    while (true)
    {
      int k;
      int m;
      if (j < i)
      {
        k = j + 1;
        m = 0xFF & paramArrayOfByte[j];
        if (k != i)
          break label78;
        localStringBuffer.append(a[(m >>> 2)]);
        localStringBuffer.append(a[((m & 0x3) << 4)]);
        localStringBuffer.append("==");
      }
      label78: int n;
      int i1;
      while (true)
      {
        return localStringBuffer.toString();
        n = k + 1;
        i1 = 0xFF & paramArrayOfByte[k];
        if (n != i)
          break;
        localStringBuffer.append(a[(m >>> 2)]);
        localStringBuffer.append(a[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
        localStringBuffer.append(a[((i1 & 0xF) << 2)]);
        localStringBuffer.append("=");
      }
      j = n + 1;
      int i2 = 0xFF & paramArrayOfByte[n];
      localStringBuffer.append(a[(m >>> 2)]);
      localStringBuffer.append(a[((m & 0x3) << 4 | (i1 & 0xF0) >>> 4)]);
      localStringBuffer.append(a[((i1 & 0xF) << 2 | (i2 & 0xC0) >>> 6)]);
      localStringBuffer.append(a[(i2 & 0x3F)]);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.core.a
 * JD-Core Version:    0.6.2
 */