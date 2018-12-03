package com.unionpay.mobile.android.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

public final class e
{
  public static SpannableString a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = "";
    if (!b(paramString1))
      str = str + paramString1;
    if (!b(paramString3))
      str = str + paramString3;
    if (!b(paramString4))
      str = str + paramString4;
    SpannableString localSpannableString = new SpannableString(str);
    int i;
    int j;
    label119: int k;
    if (b(paramString1))
    {
      i = 0;
      if (!b(paramString3))
        break label205;
      j = 0;
      k = j + i;
      localSpannableString.setSpan(new ForegroundColorSpan(-16777216), 0, str.length(), 33);
      if (!b(paramString2))
        if ((paramString2.length() == 6) || (!c(paramString2)))
          break label214;
    }
    while (true)
    {
      localSpannableString.setSpan(new ForegroundColorSpan(Color.parseColor(paramString2)), i, k, 33);
      return localSpannableString;
      i = paramString1.length();
      break;
      label205: j = paramString3.length();
      break label119;
      label214: paramString2 = "#" + paramString2;
    }
  }

  public static final String a(String paramString)
  {
    String str = "";
    if ((paramString != null) || (paramString.length() > 2))
      str = paramString.substring(1, -1 + paramString.length());
    return str;
  }

  public static final boolean b(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  private static final boolean c(String paramString)
  {
    if (!b(paramString))
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
 * Qualified Name:     com.unionpay.mobile.android.utils.e
 * JD-Core Version:    0.6.2
 */