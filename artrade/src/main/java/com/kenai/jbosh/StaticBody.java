package com.kenai.jbosh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

final class StaticBody extends AbstractBody
{
  private static final int BUFFER_SIZE = 1024;
  private static final BodyParser PARSER = new BodyParserXmlPull();
  private final Map<BodyQName, String> attrs;
  private final String raw;

  private StaticBody(Map<BodyQName, String> paramMap, String paramString)
  {
    this.attrs = paramMap;
    this.raw = paramString;
  }

  public static StaticBody fromStream(InputStream paramInputStream)
    throws BOSHException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      byte[] arrayOfByte = new byte[1024];
      int i;
      do
      {
        i = paramInputStream.read(arrayOfByte);
        if (i > 0)
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      while (i >= 0);
      return fromString(localByteArrayOutputStream.toString());
    }
    catch (IOException localIOException)
    {
      throw new BOSHException("Could not read body data", localIOException);
    }
  }

  public static StaticBody fromString(String paramString)
    throws BOSHException
  {
    return new StaticBody(PARSER.parse(paramString).getAttributes(), paramString);
  }

  public Map<BodyQName, String> getAttributes()
  {
    return Collections.unmodifiableMap(this.attrs);
  }

  public String toXML()
  {
    return this.raw;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.StaticBody
 * JD-Core Version:    0.6.2
 */