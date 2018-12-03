package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.f;
import org.json.JSONObject;

public final class ag extends z
{
  private a n = null;
  private String o = null;
  private String p = null;

  public ag(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    this(paramContext, paramInt, paramJSONObject, (byte)0);
  }

  private ag(Context paramContext, int paramInt, JSONObject paramJSONObject, byte paramByte)
  {
    super(paramContext, paramInt, paramJSONObject);
    this.o = f.a(paramJSONObject, "button_label");
    this.p = f.a(paramJSONObject, "button_action");
    this.b.a(new InputFilter.LengthFilter(11));
    this.b.a(2);
    if ((this.o == null) || (this.o.length() <= 0))
      return;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, b.n);
    localLayoutParams.addRule(9, -1);
    localLayoutParams.addRule(15, -1);
    this.b.setLayoutParams(localLayoutParams);
    TextView localTextView = new TextView(getContext());
    localTextView.setGravity(17);
    localTextView.setText(this.o);
    localTextView.setTextColor(-7829368);
    localTextView.setTextSize(b.k);
    localTextView.setOnClickListener(new ah(this));
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -1);
    this.b.a(localTextView, localLayoutParams1);
  }

  public final String a()
  {
    return this.b.b();
  }

  public final void a(a parama)
  {
    this.n = parama;
  }

  public final boolean b()
  {
    if (this.h);
    do
    {
      return true;
      if ((this.i == null) || (TextUtils.isEmpty(this.i)))
        break;
    }
    while (a().matches(this.i));
    while ((11 != a().length()) || (!a().startsWith("1")))
      return false;
    return true;
  }

  public static abstract interface a
  {
    public abstract void d(String paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ag
 * JD-Core Version:    0.6.2
 */