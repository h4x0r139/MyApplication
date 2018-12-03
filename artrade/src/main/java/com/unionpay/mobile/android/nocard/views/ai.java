package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.widgets.ax;
import org.json.JSONArray;
import org.json.JSONObject;

public final class ai extends b
{
  private TextView p = null;
  private View.OnClickListener q = new aj(this);

  public ai(Context paramContext)
  {
    super(paramContext);
    this.f = 11;
    this.j = a();
    b();
    super.d();
    c();
  }

  private void r()
  {
    this.a.D.f = "success";
    k();
  }

  public final void a(JSONObject paramJSONObject)
  {
    j();
    this.a.O = f.c(paramJSONObject, "open_rules");
    if ((this.a.O == null) || (this.a.O.length() <= 0))
    {
      b(2);
      return;
    }
    d(10);
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    ax localax = new ax(getContext(), this.a.v, this);
    localLayoutParams.addRule(13, -1);
    this.j.addView(localax, localLayoutParams);
  }

  protected final void c()
  {
    int i = a.d;
    LinearLayout localLinearLayout1 = new LinearLayout(this.d);
    localLinearLayout1.setBackgroundColor(-1114114);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setPadding(0, i, 0, i);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    TextView localTextView1 = new TextView(this.d);
    localTextView1.setText(this.a.w);
    localTextView1.setTextSize(24.0F);
    localTextView1.setTextColor(-15365480);
    localTextView1.setGravity(1);
    localTextView1.getPaint().setFakeBoldText(true);
    localLinearLayout1.addView(localTextView1);
    LinearLayout localLinearLayout2 = new LinearLayout(this.d);
    localLinearLayout2.setOrientation(0);
    localLinearLayout2.setBackgroundColor(-6958338);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, 2);
    localLayoutParams1.addRule(14, -1);
    int j = a.d;
    localLayoutParams1.bottomMargin = j;
    localLayoutParams1.topMargin = j;
    localLinearLayout1.addView(localLinearLayout2);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams2.addRule(10, -1);
    this.l.addView(localLinearLayout1, localLayoutParams2);
    LinearLayout localLinearLayout3 = new LinearLayout(this.d);
    localLinearLayout3.setPadding(i, i, i, i);
    localLinearLayout3.setOrientation(1);
    localLinearLayout3.setId(localLinearLayout3.hashCode());
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams3.addRule(3, localLinearLayout1.getId());
    this.l.addView(localLinearLayout3, localLayoutParams3);
    TextView localTextView2 = new TextView(this.d);
    localTextView2.setTextSize(18.0F);
    localTextView2.setText(this.a.N);
    localTextView2.setTextColor(-10066330);
    localTextView2.setGravity(3);
    localLinearLayout3.addView(localTextView2, new RelativeLayout.LayoutParams(-1, -2));
    this.p = new TextView(this.d);
    this.p.setText(com.unionpay.mobile.android.languages.c.by.E);
    this.p.setTextSize(22.0F);
    this.p.setTextColor(d.a(-1, -730710, -730710, -6745));
    this.p.setGravity(17);
    this.p.setOnClickListener(this.q);
    int k = a.n;
    Drawable localDrawable = this.c.a(2008);
    this.p.setBackgroundDrawable(localDrawable);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, k);
    localLayoutParams4.addRule(3, localLinearLayout3.getId());
    localLayoutParams4.addRule(12, -1);
    localLayoutParams4.bottomMargin = a.b;
    localLayoutParams4.topMargin = a.b;
    int m = a.d;
    localLayoutParams4.rightMargin = m;
    localLayoutParams4.leftMargin = m;
    this.l.addView(this.p, localLayoutParams4);
  }

  public final void l()
  {
    r();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.ai
 * JD-Core Version:    0.6.2
 */