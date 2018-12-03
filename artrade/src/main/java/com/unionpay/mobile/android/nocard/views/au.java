package com.unionpay.mobile.android.nocard.views;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

final class au
  implements View.OnClickListener
{
  au(as paramas)
  {
  }

  public final void onClick(View paramView)
  {
    JSONObject localJSONObject = (JSONObject)paramView.getTag();
    String str1 = f.a(localJSONObject, "errMsg");
    if ((str1 != null) && (!TextUtils.isEmpty(str1)))
    {
      this.a.a(str1);
      return;
    }
    String str2 = f.a(localJSONObject, "action");
    String str3 = f.a(localJSONObject, "value");
    as.a(this.a, str2, str3);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.au
 * JD-Core Version:    0.6.2
 */