package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.nocard.views.bg;
import com.unionpay.mobile.android.utils.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ac;
import org.json.JSONArray;
import org.json.JSONObject;

public final class m extends RelativeLayout
{
  private Context a;
  private ImageView b;
  private LinearLayout c;
  private LinearLayout d;
  private Drawable e;
  private Drawable f;

  public m(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext;
    int i = c.a(paramContext, 10.0F);
    setPadding(i, i, i, i);
    setBackgroundColor(-1);
    setOnClickListener(new n(this));
    int j = c.a(paramContext, 15.0F);
    this.b = new ImageView(paramContext);
    this.b.setId(this.b.hashCode());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(j, j);
    localLayoutParams1.addRule(11, -1);
    localLayoutParams1.addRule(12, -1);
    addView(this.b, localLayoutParams1);
    this.c = new LinearLayout(paramContext);
    this.c.setOrientation(1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams2.rightMargin = i;
    localLayoutParams2.addRule(9, -1);
    localLayoutParams2.addRule(15, -1);
    localLayoutParams2.addRule(0, this.b.getId());
    addView(this.c, localLayoutParams2);
  }

  public final void a(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    this.e = paramDrawable1;
    this.f = paramDrawable2;
  }

  public final void a(boolean paramBoolean, JSONArray paramJSONArray, JSONObject paramJSONObject)
  {
    int i = 2;
    this.c.removeAllViews();
    if (this.f != null)
      this.b.setBackgroundDrawable(this.f);
    int j;
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
    {
      j = 0;
      if ((paramBoolean) || (paramJSONArray == null))
        break label253;
      if (paramJSONArray.length() <= i)
        break label83;
    }
    while (true)
    {
      if ((paramJSONArray == null) || (i == 0))
      {
        g.c("uppay", "init order detail = null!!!");
        return;
        j = 1;
        break;
        label83: i = paramJSONArray.length();
        continue;
      }
      LinearLayout localLinearLayout = bg.a(this.a, paramJSONArray, 0, i);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
      this.c.addView(localLinearLayout, localLayoutParams1);
      int k = paramJSONArray.length();
      this.d = bg.a(this.a, paramJSONArray, i, k);
      if (paramJSONObject != null)
      {
        ac localac = new ac(this.a, paramJSONObject);
        localac.f();
        localac.a(b.m);
        LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        localLayoutParams2.topMargin = c.a(this.a, 8.0F);
        this.d.addView(localac, localLayoutParams2);
      }
      this.d.setVisibility(8);
      LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
      this.c.addView(this.d, localLayoutParams3);
      return;
      label253: i = j;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.m
 * JD-Core Version:    0.6.2
 */