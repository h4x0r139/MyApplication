package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import com.unionpay.mobile.android.utils.f;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ae extends z
{
  private TextWatcher n = new af(this);
  private ArrayList<a> o = null;

  public ae(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject, (byte)0);
    this.b.a(this.n);
    this.b.a(new InputFilter.LengthFilter(23));
    this.b.a(2);
    if (this.h)
      this.b.setEnabled(false);
    JSONArray localJSONArray = f.c(paramJSONObject, "regex");
    if (localJSONArray != null)
    {
      if (this.o == null)
        this.o = new ArrayList();
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        JSONObject localJSONObject = (JSONObject)f.b(localJSONArray, i);
        if (localJSONObject != null)
          this.o.add(new a(localJSONObject));
      }
    }
  }

  private static boolean b(String paramString)
  {
    int i = paramString.length();
    int j = i - 2;
    int k = 0;
    int m = j;
    int i1 = 0;
    while (m >= 0)
    {
      int i3 = '￐' + paramString.charAt(m);
      if (k % 2 == 0)
      {
        int i4 = i3 * 2;
        i3 = i4 / 10 + i4 % 10;
      }
      i1 += i3;
      m--;
      k++;
    }
    if (i1 % 10 == 0);
    for (int i2 = 48; i2 == paramString.charAt(i - 1); i2 = (char)(48 + (10 - i1 % 10)))
      return true;
    return false;
  }

  public final String a()
  {
    if (this.h);
    for (String str = h(); ; str = this.b.b())
      return str.replace(" ", "");
  }

  public final boolean b()
  {
    if (this.h);
    String str;
    do
    {
      return true;
      str = a();
      if ((this.o != null) && (this.o.size() > 0))
      {
        int i = 0;
        boolean bool1 = false;
        while (i < this.o.size())
        {
          a locala = (a)this.o.get(i);
          if (locala.a() != null)
            bool1 = str.matches(locala.a());
          if (bool1)
          {
            boolean bool2;
            if (locala.c())
              bool2 = b(locala.b() + str);
            while (true)
            {
              return bool2;
              if ((13 <= str.length()) && (19 >= str.length()))
                bool2 = true;
              else
                bool2 = false;
            }
          }
          i++;
        }
      }
    }
    while ((13 <= str.length()) && (19 >= str.length()) && (b(str)));
    return false;
  }

  final class a
  {
    private String b = null;
    private String c = null;
    private String d = null;

    public a(JSONObject arg2)
    {
      JSONObject localJSONObject;
      this.b = f.a(localJSONObject, "pattern");
      this.c = f.a(localJSONObject, "prefix");
      this.d = f.a(localJSONObject, "isCheck");
    }

    public final String a()
    {
      return this.b;
    }

    public final String b()
    {
      return this.c;
    }

    public final boolean c()
    {
      boolean bool = true;
      if ((this.d != null) && ("false".equalsIgnoreCase(this.d)))
        bool = false;
      return bool;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ae
 * JD-Core Version:    0.6.2
 */