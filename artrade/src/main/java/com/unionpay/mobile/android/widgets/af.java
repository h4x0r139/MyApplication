package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;

final class af
  implements TextWatcher
{
  private boolean b = true;
  private int c;
  private boolean d = false;

  af(ae paramae)
  {
  }

  private String a(CharSequence paramCharSequence, int paramInt)
  {
    int i = 0;
    int j = paramCharSequence.length();
    StringBuffer localStringBuffer = new StringBuffer();
    for (int k = 0; k < j; k++)
    {
      char c1 = paramCharSequence.charAt(k);
      if (c1 != ' ')
      {
        i++;
        if ((k != 0) && ((i & 0x3) == 1))
          localStringBuffer.append(' ');
      }
      if (k == paramInt)
        this.c = localStringBuffer.length();
      if (c1 != ' ')
        localStringBuffer.append(c1);
    }
    if (paramInt == j)
      this.c = localStringBuffer.length();
    return localStringBuffer.toString();
  }

  public final void afterTextChanged(Editable paramEditable)
  {
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 == 1) && (paramInt3 == 0) && (paramCharSequence.charAt(paramInt1) == ' '))
      this.d = true;
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (!this.b)
      return;
    Object localObject;
    if (this.d)
    {
      localObject = paramCharSequence.subSequence(0, paramInt1 - 1);
      if (paramInt1 < paramCharSequence.length())
        localObject = ((CharSequence)localObject).toString() + paramCharSequence.subSequence(paramInt1, paramCharSequence.length());
      paramInt1--;
      this.d = false;
    }
    while (true)
    {
      this.b = false;
      String str = a((CharSequence)localObject, paramInt1 + paramInt3);
      this.a.b.c(str);
      t localt = this.a.b;
      if (this.c <= 23);
      for (int i = this.c; ; i = 23)
      {
        localt.b(i);
        this.b = true;
        return;
      }
      localObject = paramCharSequence;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.af
 * JD-Core Version:    0.6.2
 */