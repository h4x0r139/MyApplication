package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import org.json.JSONObject;

public final class e extends z
{
  public e(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject);
    this.b.a(new InputFilter.LengthFilter(3));
    this.b.a(18);
  }

  public final boolean b()
  {
    return 3 == a().length();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.e
 * JD-Core Version:    0.6.2
 */