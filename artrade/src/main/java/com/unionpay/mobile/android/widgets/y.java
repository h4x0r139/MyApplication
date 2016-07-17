package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

public abstract class y extends az
  implements az.a
{
  public y(Context paramContext, JSONObject paramJSONObject)
  {
    super(paramContext, paramJSONObject);
  }

  public String g()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if ((m() != null) && (a() != null))
    {
      localStringBuffer.append("\"");
      localStringBuffer.append(m());
      localStringBuffer.append("\":");
      localStringBuffer.append("\"");
      localStringBuffer.append(a());
      localStringBuffer.append("\"");
    }
    return localStringBuffer.toString();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.y
 * JD-Core Version:    0.6.2
 */