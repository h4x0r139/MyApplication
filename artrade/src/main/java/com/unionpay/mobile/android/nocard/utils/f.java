package com.unionpay.mobile.android.nocard.utils;

import android.text.TextUtils;
import com.unionpay.mobile.android.model.a;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.model.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f
{
  public static int a(b paramb, JSONObject paramJSONObject)
  {
    paramb.u = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "rules");
    paramb.Y = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "available_area_codes");
    paramb.y = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "entrust_rules");
    paramb.z = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "pre_cmd");
    paramb.v = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "title");
    paramb.x = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "rules_button");
    paramb.ag = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "service_checkbox");
    paramb.ah = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "bind_card_checkbox");
    String str1 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "timeout_msg");
    if ((str1 != null) && (!TextUtils.isEmpty(str1)))
      paramb.af = str1;
    paramb.k = new HashMap();
    JSONObject localJSONObject = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "f55");
    String str2 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "order_amount");
    HashMap localHashMap1 = paramb.k;
    String str3;
    label185: String str4;
    label222: String str5;
    HashMap localHashMap4;
    if ((str2 != null) && (str2.length() > 0))
    {
      localHashMap1.put("trans_amt", str2);
      str3 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "order_currency");
      HashMap localHashMap2 = paramb.k;
      if ((str3 == null) || (str3.length() <= 0))
        break label350;
      localHashMap2.put("trans currcy code", str3);
      str4 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "trans_type");
      HashMap localHashMap3 = paramb.k;
      if ((str4 == null) || (str4.length() <= 0))
        break label357;
      localHashMap3.put("trans_type", str4);
      str5 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "mer_name");
      localHashMap4 = paramb.k;
      if ((str5 == null) || (str5.length() <= 0))
        break label364;
    }
    while (true)
    {
      localHashMap4.put("mer_name", str5);
      paramb.al = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "pan");
      String str6 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "fail_continue");
      if ((str6 != null) && (str6.equalsIgnoreCase("0")))
        paramb.A = true;
      if (((paramb.u != null) && (paramb.u.length() > 0)) || ((paramb.y != null) && (paramb.y.length() > 0)))
        break label371;
      return 2;
      str2 = "000000000000";
      break;
      label350: str3 = "0156";
      break label185;
      label357: str4 = "00";
      break label222;
      label364: str5 = "";
    }
    label371: return 0;
  }

  public static int a(b paramb, JSONObject paramJSONObject, boolean paramBoolean)
  {
    if (!paramBoolean)
      paramb.B = paramJSONObject;
    return a(paramb, paramJSONObject);
  }

  public static d a(JSONObject paramJSONObject)
  {
    e locale = new e();
    locale.a("promotion", com.unionpay.mobile.android.utils.f.b(paramJSONObject, "promotion"));
    locale.a("instalment", com.unionpay.mobile.android.utils.f.b(paramJSONObject, "instalment"));
    locale.a("promotion_instalment_msgbox", com.unionpay.mobile.android.utils.f.b(paramJSONObject, "promotion_instalment_msgbox"));
    return locale;
  }

  public static boolean a(JSONArray paramJSONArray)
  {
    boolean bool1 = false;
    int i;
    if (paramJSONArray != null)
      i = 0;
    while (true)
    {
      int j = paramJSONArray.length();
      bool1 = false;
      if (i < j);
      try
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        String str1 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "type");
        String str2 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "readonly");
        if ("pan".equalsIgnoreCase(str1))
        {
          boolean bool2 = "true".equalsIgnoreCase(str2);
          bool1 = false;
          if (bool2)
            bool1 = true;
          return bool1;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        i++;
      }
    }
  }

  public static int b(b paramb, JSONObject paramJSONObject)
  {
    if (paramJSONObject == null);
    for (int i = 2; ; i = 0)
    {
      if (paramb.W == null)
        paramb.W = new ArrayList();
      paramb.W.clear();
      List localList = com.unionpay.mobile.android.utils.f.d(paramJSONObject, "user_cards");
      if ((localList != null) && (localList.size() > 0))
        for (int j = 0; (localList != null) && (j < localList.size()); j++)
        {
          a locala = new a(com.unionpay.mobile.android.utils.f.a((JSONArray)localList.get(j), 0), com.unionpay.mobile.android.utils.f.a((JSONArray)localList.get(j), 1), com.unionpay.mobile.android.utils.f.a((JSONArray)localList.get(j), 2), '\000');
          paramb.W.add(locala);
        }
      paramb.Y = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "available_area_codes");
      paramb.X = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "user_info");
      paramb.u = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "rules");
      paramb.U = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "service_url");
      paramb.V = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "bind_url");
      paramb.Z = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "empty_info");
      paramb.aO = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "add_card_tip");
      paramb.al = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "pan");
      return i;
    }
  }

  public static boolean c(b paramb, JSONObject paramJSONObject)
  {
    paramb.av = null;
    paramb.av = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "cardExpireMsgBox");
    if (paramb.av == null)
      paramb.av = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "openByCounterMsgBox");
    if (paramb.av == null)
      paramb.av = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "restrictPayMsgBox");
    JSONObject localJSONObject1 = paramb.av;
    boolean bool = false;
    if (localJSONObject1 != null)
    {
      paramb.aw = com.unionpay.mobile.android.utils.f.a(paramb.av, "title");
      paramb.ax = com.unionpay.mobile.android.utils.f.a(paramb.av, "text");
      JSONObject localJSONObject2 = com.unionpay.mobile.android.utils.f.b(paramb.av, "func");
      JSONObject localJSONObject3 = com.unionpay.mobile.android.utils.f.b(paramb.av, "cancel");
      paramb.aA = com.unionpay.mobile.android.utils.f.a(localJSONObject2, "label");
      paramb.aB = com.unionpay.mobile.android.utils.f.a(localJSONObject2, "action");
      paramb.ay = com.unionpay.mobile.android.utils.f.a(localJSONObject3, "label");
      paramb.az = com.unionpay.mobile.android.utils.f.a(localJSONObject3, "action");
      bool = true;
    }
    return bool;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.utils.f
 * JD-Core Version:    0.6.2
 */