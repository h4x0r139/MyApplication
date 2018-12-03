package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import java.util.Calendar;
import org.json.JSONObject;

public final class au extends z
{
  public au(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject);
    this.b.a(new InputFilter.LengthFilter(4));
    this.b.a(2);
  }

  public final String a()
  {
    return this.b.b().trim();
  }

  public final boolean b()
  {
    String str = a();
    if (4 == str.length())
    {
      int i = Integer.parseInt(str.substring(0, 2));
      int j = Integer.parseInt(str.substring(2));
      int k = Calendar.getInstance().get(1) % 100;
      int m = 1 + Calendar.getInstance().get(2);
      if ((i > 0) && (i <= 12) && ((j > k) || ((j == k) && (i >= m))));
      for (int n = 1; n != 0; n = 0)
        return true;
    }
    return false;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.au
 * JD-Core Version:    0.6.2
 */