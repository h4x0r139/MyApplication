package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class as extends z
{
  private int n = 0;

  public as(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject);
    String str = f.a(paramJSONObject, "maxLength");
    if ((str != null) && (str.length() > 0));
    for (this.n = Integer.getInteger(str).intValue(); ; this.n = 23)
    {
      this.b.a(new InputFilter.LengthFilter(this.n));
      return;
    }
  }

  public final boolean b()
  {
    if (this.h);
    while (this.n >= a().length())
      return true;
    return false;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.as
 * JD-Core Version:    0.6.2
 */