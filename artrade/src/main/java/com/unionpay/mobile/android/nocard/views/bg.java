package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ac;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bg
{
  public static LinearLayout a(Context paramContext, JSONArray paramJSONArray, int paramInt1, int paramInt2)
  {
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    localLinearLayout.setBackgroundColor(-1);
    localLinearLayout.setOrientation(1);
    new LinearLayout.LayoutParams(-1, -2).topMargin = a.d;
    Object localObject = null;
    while (true)
      if ((paramInt1 < paramInt2) && (paramInt1 < paramJSONArray.length()))
        try
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(paramInt1);
          localObject = localJSONObject;
          localLinearLayout.addView(new ac(paramContext, a.I, localObject));
          paramInt1++;
        }
        catch (JSONException localJSONException)
        {
          while (true)
            localJSONException.printStackTrace();
        }
    return localLinearLayout;
  }

  public static String a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return String.format("\"first_pay_flag\":\"%s\",\"card\":\"%s\",\"pay_type\":\"%s\",\"pay_mode\":\"%s\"", new Object[] { paramString1, paramString2, paramString3, paramString4 });
  }

  public static String a(JSONObject paramJSONObject)
  {
    g.a("uppay", "action:" + f.a(paramJSONObject, "action"));
    return f.a(paramJSONObject, "action");
  }

  public static String b(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    return String.format("\"first_pay_flag\":\"%s\",%s,\"pay_type\":\"%s\",\"pay_mode\":\"%s\"", new Object[] { paramString1, paramString2, paramString3, paramString4 });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.bg
 * JD-Core Version:    0.6.2
 */