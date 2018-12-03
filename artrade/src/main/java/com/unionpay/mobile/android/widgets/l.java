package com.unionpay.mobile.android.widgets;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.languages.c;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

final class l
  implements View.OnClickListener
{
  l(k paramk)
  {
  }

  public final void onClick(View paramView)
  {
    if (k.a(this.a))
    {
      JSONObject localJSONObject = new JSONObject();
      try
      {
        if (!TextUtils.isEmpty(this.a.a()))
        {
          if (this.a.a().matches("[A-Za-z0-9]{8,32}"))
          {
            this.a.a(true);
            localJSONObject.put("value", this.a.g());
            localJSONObject.put("action", k.b(this.a));
          }
          while (true)
          {
            paramView.setTag(localJSONObject);
            Iterator localIterator1 = k.c(this.a).iterator();
            while (localIterator1.hasNext())
              ((View.OnClickListener)localIterator1.next()).onClick(paramView);
            String str2 = c.by.aD;
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = c.by.C;
            localJSONObject.put("errMsg", String.format(str2, arrayOfObject2));
          }
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          localJSONException.printStackTrace();
          continue;
          String str1 = c.by.aC;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = c.by.C;
          localJSONObject.put("errMsg", String.format(str1, arrayOfObject1));
        }
      }
    }
    else
    {
      this.a.b.e();
      this.a.a(false);
      Iterator localIterator2 = k.d(this.a).iterator();
      while (localIterator2.hasNext())
        ((View.OnClickListener)localIterator2.next()).onClick(paramView);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.l
 * JD-Core Version:    0.6.2
 */