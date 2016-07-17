package com.unionpay.mobile.android.nocard.utils;

import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class b
{
  public static void a(JSONObject paramJSONObject, com.unionpay.mobile.android.model.b paramb)
  {
    paramb.an = f.a(paramJSONObject, "red_packet_url");
  }

  public static void b(JSONObject paramJSONObject, com.unionpay.mobile.android.model.b paramb)
  {
    paramb.aH = f.a(paramJSONObject, "pay_msg");
    paramb.aI = f.a(paramJSONObject, "promotion_msg");
    paramb.aJ = f.a(paramJSONObject, "instalment_msg");
    paramb.aM = f.a(paramJSONObject, "temporary_pay_flag");
    if ("0".equalsIgnoreCase(paramb.aM))
      paramb.aN = f.a(paramJSONObject, "temporary_pay_info");
    if ("0".equalsIgnoreCase(f.a(paramJSONObject, "luck_draw_flag")))
      paramb.aL = true;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.utils.b
 * JD-Core Version:    0.6.2
 */