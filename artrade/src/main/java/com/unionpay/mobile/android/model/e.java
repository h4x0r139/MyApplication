package com.unionpay.mobile.android.model;

import java.util.HashMap;
import org.json.JSONObject;

public final class e
  implements d
{
  private HashMap<String, Object> a = new HashMap();

  public final JSONObject a(String paramString)
  {
    Object localObject = this.a.get(paramString);
    if ((localObject != null) && ((localObject instanceof JSONObject)))
      return (JSONObject)localObject;
    return null;
  }

  public final void a(String paramString, Object paramObject)
  {
    this.a.put(paramString, paramObject);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.model.e
 * JD-Core Version:    0.6.2
 */