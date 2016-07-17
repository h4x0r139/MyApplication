package com.amap.api.services.help;

import com.amap.api.services.core.h;
import java.net.Proxy;
import java.util.ArrayList;

public class b extends h<c, ArrayList<Tip>>
{
  public b(c paramc, Proxy paramProxy)
  {
    super(paramc, paramProxy);
  }

  protected String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("output=json").append("&keywords=").append(d(((c)this.b).a));
    String str1 = ((c)this.b).b;
    if (!e(str1))
    {
      String str2 = d(str1);
      localStringBuffer.append("&city=").append(str2);
    }
    localStringBuffer.append("&key=" + com.amap.api.services.core.b.a);
    return localStringBuffer.toString();
  }

  // ERROR //
  protected ArrayList<Tip> a(java.io.InputStream paramInputStream)
    throws com.amap.api.services.core.AMapException
  {
    // Byte code:
    //   0: new 70	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 71	java/util/ArrayList:<init>	()V
    //   7: astore_2
    //   8: new 73	java/lang/String
    //   11: dup
    //   12: aload_1
    //   13: invokestatic 78	com/amap/api/services/core/c:a	(Ljava/io/InputStream;)[B
    //   16: invokespecial 81	java/lang/String:<init>	([B)V
    //   19: astore_3
    //   20: aload_3
    //   21: ifnull +151 -> 172
    //   24: aload_3
    //   25: ldc 83
    //   27: invokevirtual 87	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   30: ifeq +6 -> 36
    //   33: goto +139 -> 172
    //   36: aload_3
    //   37: invokestatic 90	com/amap/api/services/core/c:b	(Ljava/lang/String;)V
    //   40: aload_3
    //   41: invokestatic 90	com/amap/api/services/core/c:b	(Ljava/lang/String;)V
    //   44: new 92	org/json/JSONObject
    //   47: dup
    //   48: aload_3
    //   49: invokespecial 94	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   52: ldc 96
    //   54: invokevirtual 100	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   57: astore 6
    //   59: iconst_0
    //   60: istore 7
    //   62: iload 7
    //   64: aload 6
    //   66: invokevirtual 106	org/json/JSONArray:length	()I
    //   69: if_icmpge +96 -> 165
    //   72: new 108	com/amap/api/services/help/Tip
    //   75: dup
    //   76: invokespecial 109	com/amap/api/services/help/Tip:<init>	()V
    //   79: astore 8
    //   81: aload 6
    //   83: iload 7
    //   85: invokevirtual 113	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   88: astore 9
    //   90: aload 8
    //   92: aload_0
    //   93: aload 9
    //   95: ldc 115
    //   97: invokevirtual 118	com/amap/api/services/help/b:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   100: invokevirtual 121	com/amap/api/services/help/Tip:setName	(Ljava/lang/String;)V
    //   103: aload 8
    //   105: aload_0
    //   106: aload 9
    //   108: ldc 123
    //   110: invokevirtual 118	com/amap/api/services/help/b:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   113: invokevirtual 126	com/amap/api/services/help/Tip:setDistrict	(Ljava/lang/String;)V
    //   116: aload 8
    //   118: aload_0
    //   119: aload 9
    //   121: ldc 128
    //   123: invokevirtual 118	com/amap/api/services/help/b:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   126: invokevirtual 131	com/amap/api/services/help/Tip:setAdcode	(Ljava/lang/String;)V
    //   129: aload_2
    //   130: aload 8
    //   132: invokevirtual 134	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   135: pop
    //   136: iinc 7 1
    //   139: goto -77 -> 62
    //   142: astore 11
    //   144: aconst_null
    //   145: astore_3
    //   146: aload 11
    //   148: astore 4
    //   150: aload 4
    //   152: invokevirtual 137	java/lang/Exception:printStackTrace	()V
    //   155: goto -115 -> 40
    //   158: astore 5
    //   160: aload 5
    //   162: invokevirtual 138	org/json/JSONException:printStackTrace	()V
    //   165: aload_2
    //   166: areturn
    //   167: astore 4
    //   169: goto -19 -> 150
    //   172: aconst_null
    //   173: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	20	142	java/lang/Exception
    //   44	59	158	org/json/JSONException
    //   62	136	158	org/json/JSONException
    //   24	33	167	java/lang/Exception
    //   36	40	167	java/lang/Exception
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/assistant/inputtips?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.help.b
 * JD-Core Version:    0.6.2
 */