package com.unionpay.mobile.android.data;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.unionpay.mobile.android.utils.e;
import java.util.HashMap;

public final class a
{
  public static final HashMap<String, Integer> a = new HashMap();

  public static SpannableString a(String paramString1, String paramString2)
  {
    int i = -10066330;
    SpannableString localSpannableString = new SpannableString(paramString1);
    localSpannableString.setSpan(new ForegroundColorSpan(i), 0, paramString1.length(), 33);
    if (paramString2 == null)
      return localSpannableString;
    String[] arrayOfString = paramString2.split(";");
    int j = arrayOfString.length;
    int k = 0;
    while (true)
    {
      String str;
      if (k < j)
      {
        str = arrayOfString[k];
        if (a(str));
      }
      try
      {
        int m = Color.parseColor("#" + str);
        i = m;
        label100: k++;
        continue;
        localSpannableString.setSpan(new ForegroundColorSpan(i), 0, paramString1.length(), 33);
        return localSpannableString;
      }
      catch (Exception localException)
      {
        break label100;
      }
    }
  }

  public static final boolean a(String paramString)
  {
    if (!e.b(paramString))
    {
      String[] arrayOfString = { "black", "darkgray", "gray", "lightgray", "white", "red", "green", "blue", "yellow", "cyan", "magenta" };
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
        if (arrayOfString[j].equalsIgnoreCase(paramString))
          return true;
    }
    return false;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.data.a
 * JD-Core Version:    0.6.2
 */