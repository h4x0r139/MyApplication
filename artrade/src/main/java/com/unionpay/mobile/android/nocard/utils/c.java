package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.views.order.l;

public final class c
{
  public static int a(Context paramContext, b paramb)
  {
    String str = PreferenceUtils.b(paramContext);
    int i = l.b.intValue();
    if ((!TextUtils.isEmpty(str)) && (TextUtils.isDigitsOnly(str)))
      i = Integer.valueOf(str).intValue();
    boolean bool = "0".equalsIgnoreCase(paramb.ai);
    if ((paramb.ar) && ((0x11101 & paramb.aj) == 0))
      bool = true;
    if (bool)
      i = l.a.intValue();
    do
    {
      return i;
      if (!TextUtils.isEmpty(paramb.p))
        i = l.b.intValue();
    }
    while (!f.a(paramb.o));
    return l.b.intValue();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.utils.c
 * JD-Core Version:    0.6.2
 */