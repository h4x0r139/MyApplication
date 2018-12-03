package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class u extends LinearLayout
{
  private String a = null;
  private v b = null;

  private u(Context paramContext, String paramString1, String paramString2, Drawable paramDrawable)
  {
    super(paramContext);
    setOrientation(0);
    this.a = paramString2;
    this.b = v.a(paramContext, paramDrawable);
    this.b.a(Html.fromHtml(String.format("<u>%s</u>", new Object[] { paramString1 })));
    ColorStateList localColorStateList = d.a(-13601621, -15909519);
    this.b.a(localColorStateList);
    addView(this.b);
  }

  public static final u a(Context paramContext, JSONObject paramJSONObject, Drawable paramDrawable)
  {
    u localu = null;
    if (paramJSONObject != null)
      localu = new u(paramContext, f.a(paramJSONObject, "label"), f.a(paramJSONObject, "href"), paramDrawable);
    return localu;
  }

  public final String a()
  {
    return this.a;
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    if (this.b != null)
      this.b.setOnClickListener(paramOnClickListener);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.u
 * JD-Core Version:    0.6.2
 */