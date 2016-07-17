package com.kenai.jbosh;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ServiceLib
{
  private static final Logger LOG = Logger.getLogger(ServiceLib.class.getName());

  private static <T> T attemptLoad(Class<T> paramClass, String paramString)
  {
    if (LOG.isLoggable(Level.FINEST))
      LOG.finest("Attempting service load: " + paramString);
    try
    {
      Class localClass = Class.forName(paramString);
      if (!paramClass.isAssignableFrom(localClass))
      {
        if (LOG.isLoggable(Level.WARNING))
          LOG.warning(localClass.getName() + " is not assignable to " + paramClass.getName());
      }
      else
      {
        Object localObject = paramClass.cast(localClass.newInstance());
        return localObject;
      }
    }
    catch (LinkageError localLinkageError)
    {
      localLevel = Level.FINEST;
      LOG.log(localLevel, "Could not load " + paramClass.getSimpleName() + " instance: " + paramString, localLinkageError);
      return null;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
        localLevel = Level.FINEST;
    }
    catch (InstantiationException localInstantiationException)
    {
      while (true)
        localLevel = Level.WARNING;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        Level localLevel = Level.WARNING;
    }
    return null;
  }

  private static void finalClose(Closeable paramCloseable)
  {
    if (paramCloseable != null);
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException)
    {
      LOG.log(Level.FINEST, "Could not close: " + paramCloseable, localIOException);
    }
  }

  static <T> T loadService(Class<T> paramClass)
  {
    Iterator localIterator = loadServicesImplementations(paramClass).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = attemptLoad(paramClass, (String)localIterator.next());
      if (localObject != null)
      {
        if (LOG.isLoggable(Level.FINEST))
          LOG.finest("Selected " + paramClass.getSimpleName() + " implementation: " + localObject.getClass().getName());
        return localObject;
      }
    }
    throw new IllegalStateException("Could not load " + paramClass.getName() + " implementation");
  }

  // ERROR //
  private static List<String> loadServicesImplementations(Class paramClass)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 151	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 152	java/util/ArrayList:<init>	()V
    //   9: astore_2
    //   10: aload_0
    //   11: invokevirtual 14	java/lang/Class:getName	()Ljava/lang/String;
    //   14: invokestatic 158	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   17: astore_3
    //   18: aload_3
    //   19: ifnull +11 -> 30
    //   22: aload_2
    //   23: aload_3
    //   24: invokeinterface 162 2 0
    //   29: pop
    //   30: ldc 2
    //   32: invokevirtual 166	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   35: new 47	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   42: ldc 168
    //   44: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_0
    //   48: invokevirtual 14	java/lang/Class:getName	()Ljava/lang/String;
    //   51: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: invokevirtual 174	java/lang/ClassLoader:getResource	(Ljava/lang/String;)Ljava/net/URL;
    //   60: astore 4
    //   62: aload 4
    //   64: ifnonnull +5 -> 69
    //   67: aload_2
    //   68: areturn
    //   69: aload 4
    //   71: invokevirtual 180	java/net/URL:openStream	()Ljava/io/InputStream;
    //   74: astore 9
    //   76: new 182	java/io/InputStreamReader
    //   79: dup
    //   80: aload 9
    //   82: invokespecial 185	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   85: astore 10
    //   87: new 187	java/io/BufferedReader
    //   90: dup
    //   91: aload 10
    //   93: invokespecial 190	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   96: astore 6
    //   98: aload 6
    //   100: invokevirtual 193	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   103: astore 11
    //   105: aload 11
    //   107: ifnull +87 -> 194
    //   110: aload 11
    //   112: ldc 195
    //   114: invokevirtual 199	java/lang/String:matches	(Ljava/lang/String;)Z
    //   117: ifne -19 -> 98
    //   120: aload_2
    //   121: aload 11
    //   123: invokevirtual 202	java/lang/String:trim	()Ljava/lang/String;
    //   126: invokeinterface 162 2 0
    //   131: pop
    //   132: goto -34 -> 98
    //   135: astore 5
    //   137: aload 10
    //   139: astore_1
    //   140: aload 9
    //   142: astore 7
    //   144: getstatic 22	com/kenai/jbosh/ServiceLib:LOG	Ljava/util/logging/Logger;
    //   147: getstatic 72	java/util/logging/Level:WARNING	Ljava/util/logging/Level;
    //   150: new 47	java/lang/StringBuilder
    //   153: dup
    //   154: invokespecial 48	java/lang/StringBuilder:<init>	()V
    //   157: ldc 204
    //   159: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: aload 4
    //   164: invokevirtual 205	java/net/URL:toString	()Ljava/lang/String;
    //   167: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: aload 5
    //   175: invokevirtual 96	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   178: aload 6
    //   180: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   183: aload_1
    //   184: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   187: aload 7
    //   189: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   192: aload_2
    //   193: areturn
    //   194: aload 6
    //   196: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   199: aload 10
    //   201: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   204: aload 9
    //   206: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   209: aload_2
    //   210: areturn
    //   211: astore 8
    //   213: aconst_null
    //   214: astore 6
    //   216: aconst_null
    //   217: astore 7
    //   219: aload 6
    //   221: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   224: aload_1
    //   225: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   228: aload 7
    //   230: invokestatic 207	com/kenai/jbosh/ServiceLib:finalClose	(Ljava/io/Closeable;)V
    //   233: aload 8
    //   235: athrow
    //   236: astore 8
    //   238: aload 9
    //   240: astore 7
    //   242: aconst_null
    //   243: astore 6
    //   245: aconst_null
    //   246: astore_1
    //   247: goto -28 -> 219
    //   250: astore 8
    //   252: aload 10
    //   254: astore_1
    //   255: aload 9
    //   257: astore 7
    //   259: aconst_null
    //   260: astore 6
    //   262: goto -43 -> 219
    //   265: astore 8
    //   267: aload 10
    //   269: astore_1
    //   270: aload 9
    //   272: astore 7
    //   274: goto -55 -> 219
    //   277: astore 8
    //   279: goto -60 -> 219
    //   282: astore 5
    //   284: aconst_null
    //   285: astore 6
    //   287: aconst_null
    //   288: astore_1
    //   289: aconst_null
    //   290: astore 7
    //   292: goto -148 -> 144
    //   295: astore 5
    //   297: aload 9
    //   299: astore 7
    //   301: aconst_null
    //   302: astore 6
    //   304: aconst_null
    //   305: astore_1
    //   306: goto -162 -> 144
    //   309: astore 5
    //   311: aload 10
    //   313: astore_1
    //   314: aload 9
    //   316: astore 7
    //   318: aconst_null
    //   319: astore 6
    //   321: goto -177 -> 144
    //
    // Exception table:
    //   from	to	target	type
    //   98	105	135	java/io/IOException
    //   110	132	135	java/io/IOException
    //   69	76	211	finally
    //   76	87	236	finally
    //   87	98	250	finally
    //   98	105	265	finally
    //   110	132	265	finally
    //   144	178	277	finally
    //   69	76	282	java/io/IOException
    //   76	87	295	java/io/IOException
    //   87	98	309	java/io/IOException
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.ServiceLib
 * JD-Core Version:    0.6.2
 */