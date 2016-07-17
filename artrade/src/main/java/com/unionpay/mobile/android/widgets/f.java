package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import org.json.JSONObject;

public final class f extends z
{
  public f(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject);
    this.b.a(new InputFilter.LengthFilter(32));
  }

  public final boolean b()
  {
    return (a().length() != 0) && (a().length() <= 32);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.f
 * JD-Core Version:    0.6.2
 */