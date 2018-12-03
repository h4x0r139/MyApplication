package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.f;
import java.util.ArrayList;
import org.json.JSONObject;

public final class k extends z
{
  private final String n = "[A-Za-z0-9]{8,32}";
  private ArrayList<View.OnClickListener> o = new ArrayList();
  private ArrayList<View.OnClickListener> p = new ArrayList();
  private TextView q = null;
  private boolean r = true;
  private String s = null;
  private String t = null;
  private View.OnClickListener u = new l(this);

  public k(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject);
    this.s = f.a(paramJSONObject, "button_label");
    this.t = f.a(paramJSONObject, "button_action");
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, b.n);
    localLayoutParams.addRule(9, -1);
    localLayoutParams.addRule(15, -1);
    this.b.setLayoutParams(localLayoutParams);
    this.q = new TextView(getContext());
    this.q.setGravity(17);
    this.q.setText(this.s);
    this.q.setTextColor(d.a(-10705958, -5846275, -5846275, -6710887));
    this.q.setTextSize(b.k);
    this.q.setOnClickListener(this.u);
    a(false);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -1);
    this.b.a(this.q, localLayoutParams1);
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    this.o.add(paramOnClickListener);
  }

  public final void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.q.setText(c.by.B);
      this.r = false;
      return;
    }
    this.q.setText(this.s);
    this.r = true;
  }

  public final void b(View.OnClickListener paramOnClickListener)
  {
    this.p.add(paramOnClickListener);
  }

  public final boolean b()
  {
    if (this.h);
    while (6 == a().length())
      return true;
    return false;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.k
 * JD-Core Version:    0.6.2
 */