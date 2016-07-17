package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.upwidget.u;
import com.unionpay.mobile.android.widgets.ax;
import com.unionpay.mobile.android.widgets.m;
import org.json.JSONObject;

public final class af extends b
  implements a.b
{
  private TextView p = null;
  private View.OnClickListener q = null;
  private com.unionpay.mobile.android.upviews.a r = null;
  private int s = 0;

  public af(Context paramContext)
  {
    super(paramContext);
    this.f = 12;
    setBackgroundColor(-1052684);
    e();
  }

  public final void a(a.a parama)
  {
  }

  public final void a(JSONObject paramJSONObject)
  {
    switch (this.s)
    {
    default:
      return;
    case 1:
    }
    this.b.c();
    f.c(this.a, paramJSONObject);
    int i = f.b(this.a, paramJSONObject);
    if (i != 0)
    {
      b(i);
      return;
    }
    if (this.r != null)
      this.r.d();
    d(13);
  }

  public final void a(boolean paramBoolean)
  {
    TextView localTextView;
    if (this.p != null)
    {
      localTextView = this.p;
      if (paramBoolean)
        break label24;
    }
    label24: for (boolean bool = true; ; bool = false)
    {
      localTextView.setEnabled(bool);
      return;
    }
  }

  protected final void b()
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    String str = com.unionpay.mobile.android.languages.c.by.m;
    ax localax = new ax(this.d, str, this);
    localLayoutParams.addRule(13, -1);
    this.j.addView(localax, localLayoutParams);
  }

  public final void b(String paramString1, String paramString2)
  {
  }

  protected final void c()
  {
    int i = 1;
    this.n.a(this);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams1.addRule(10, -1);
    localLayoutParams1.topMargin = com.unionpay.mobile.android.global.a.f;
    this.r = new com.unionpay.mobile.android.upviews.a(this.d, this.a.S, this);
    this.r.setOrientation(i);
    this.r.setId(this.r.hashCode());
    this.l.addView(this.r, localLayoutParams1);
    u localu = u.a(this.d, this.a.T, this.c.a(1017));
    if (localu != null)
    {
      localu.setId(localu.hashCode());
      localu.a(new ah(this, localu.a()));
      RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
      localLayoutParams3.addRule(3, this.r.getId());
      int n = com.unionpay.mobile.android.global.a.d;
      localLayoutParams3.bottomMargin = n;
      localLayoutParams3.topMargin = n;
      localLayoutParams3.leftMargin = com.unionpay.mobile.android.global.a.d;
      this.l.addView(localu, localLayoutParams3);
    }
    this.p = new TextView(this.d);
    this.p.setText(com.unionpay.mobile.android.languages.c.by.n);
    this.p.setTextSize(com.unionpay.mobile.android.global.b.i);
    this.p.setTextColor(p());
    this.p.setGravity(17);
    TextView localTextView = this.p;
    RelativeLayout.LayoutParams localLayoutParams2;
    if ((this.r == null) || (this.r.c()))
    {
      localTextView.setEnabled(i);
      int k = com.unionpay.mobile.android.global.a.n;
      Drawable localDrawable = this.c.a(2008);
      this.p.setBackgroundDrawable(localDrawable);
      this.p.setOnClickListener(this.q);
      localLayoutParams2 = new RelativeLayout.LayoutParams(-1, k);
      if (localu == null)
        break label387;
    }
    label387: for (int m = localu.getId(); ; m = this.r.getId())
    {
      localLayoutParams2.addRule(3, m);
      localLayoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
      this.l.addView(this.p, localLayoutParams2);
      return;
      int j = 0;
      break;
    }
  }

  public final void c(String paramString)
  {
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.af
 * JD-Core Version:    0.6.2
 */