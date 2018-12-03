package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.utils.d;
import org.json.JSONObject;

public final class ao extends z
  implements Handler.Callback
{
  private a n = null;
  private TextView o = null;
  private Handler p = null;
  private int q = 0;

  public ao(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    super(paramContext, paramInt, paramJSONObject);
    i();
    this.n = null;
  }

  public ao(Context paramContext, int paramInt, JSONObject paramJSONObject, byte paramByte)
  {
    super(paramContext, paramInt, paramJSONObject);
    i();
  }

  private void a(boolean paramBoolean, String paramString)
  {
    this.o.setText(paramString);
    this.o.setEnabled(paramBoolean);
  }

  private void i()
  {
    this.b.a(new InputFilter.LengthFilter(6));
    this.b.a(2);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, b.n);
    localLayoutParams.addRule(9, -1);
    localLayoutParams.addRule(15, -1);
    this.b.setLayoutParams(localLayoutParams);
    LinearLayout localLinearLayout = new LinearLayout(this.c);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setBackgroundColor(-3419943);
    new LinearLayout.LayoutParams(1, -1);
    this.o = new TextView(getContext());
    this.o.setGravity(17);
    this.o.setText(c.by.w);
    this.o.setTextColor(d.a(-10705958, -5846275, -5846275, -6710887));
    this.o.setTextSize(b.k);
    this.o.setOnClickListener(new ap(this));
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -1);
    this.b.a(this.o, localLayoutParams1);
  }

  public final void a(int paramInt)
  {
    this.p = new Handler(this);
    aq localaq = new aq(this, paramInt);
    String str = c.by.x;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    a(false, String.format(str, arrayOfObject));
    localaq.start();
  }

  public final void a(a parama)
  {
    this.n = parama;
  }

  public final boolean b()
  {
    if (this.h);
    while (6 == a().length())
      return true;
    return false;
  }

  public final boolean handleMessage(Message paramMessage)
  {
    boolean bool = true;
    switch (paramMessage.what)
    {
    default:
      bool = false;
    case 1:
    case 0:
    }
    do
    {
      return bool;
      if (c.by != null)
        a(bool, c.by.y);
      this.p = null;
      return bool;
      this.q = paramMessage.arg1;
    }
    while (c.by == null);
    String str = c.by.x;
    Object[] arrayOfObject = new Object[bool];
    arrayOfObject[0] = Integer.valueOf(paramMessage.arg1);
    a(false, String.format(str, arrayOfObject));
    return bool;
  }

  public static abstract interface a
  {
    public abstract void a(y paramy);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ao
 * JD-Core Version:    0.6.2
 */