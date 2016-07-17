package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.utils.c;
import com.unionpay.mobile.android.utils.d;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractMethod extends LinearLayout
  implements a.b
{
  protected static final int a = b.a;
  protected Context b;
  protected String c;
  protected String d;
  protected b e;
  protected a f;
  private Button g;
  private RelativeLayout h;

  public AbstractMethod(Context paramContext)
  {
    this(paramContext, null);
  }

  public AbstractMethod(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public AbstractMethod(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext);
    this.b = paramContext;
    setOrientation(1);
  }

  protected static String a(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = null;
    if (paramJSONObject != null);
    try
    {
      String str = paramJSONObject.getString(paramString);
      localObject = str;
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }

  protected static void a(TextView paramTextView)
  {
    if (paramTextView == null)
      return;
    paramTextView.setTextSize(b.k);
    paramTextView.setTextColor(d.a(-10705958, -5846275, -5846275, -6710887));
  }

  protected static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public final void a()
  {
    this.h = new RelativeLayout(this.b);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
    addView(this.h, localLayoutParams1);
    a(this.h);
    RelativeLayout localRelativeLayout1 = new RelativeLayout(this.b);
    addView(localRelativeLayout1, new LinearLayout.LayoutParams(-1, -2));
    b(localRelativeLayout1);
    RelativeLayout localRelativeLayout2 = new RelativeLayout(this.b);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
    addView(localRelativeLayout2, localLayoutParams2);
    this.g = new Button(this.b);
    this.g.setText(e());
    this.g.setTextColor(d.a(b.b, b.c, b.c, b.d));
    this.g.setTextSize(b.i);
    this.g.setOnClickListener(new a(this));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, b.n);
    localLayoutParams.addRule(15, -1);
    localLayoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
    int i = c.a(this.b, 10.0F);
    localLayoutParams.rightMargin = i;
    localLayoutParams.leftMargin = i;
    localRelativeLayout2.addView(this.g, localLayoutParams);
    RelativeLayout localRelativeLayout3 = new RelativeLayout(this.b);
    addView(localRelativeLayout3, localLayoutParams2);
    c(localRelativeLayout3);
    this.g.setEnabled(f());
  }

  public final void a(Drawable paramDrawable)
  {
    if ((this.g != null) && (paramDrawable != null))
      this.g.setBackgroundDrawable(paramDrawable);
  }

  public abstract void a(RelativeLayout paramRelativeLayout);

  public final void a(a.a parama)
  {
  }

  public final void a(a parama)
  {
    this.f = parama;
  }

  public final void a(b paramb)
  {
    this.e = paramb;
  }

  public final void a(boolean paramBoolean)
  {
    Button localButton = this.g;
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      localButton.setEnabled(bool);
      return;
    }
  }

  public abstract int b();

  public abstract void b(RelativeLayout paramRelativeLayout);

  public final void b(String paramString1, String paramString2)
  {
  }

  public abstract a.a c();

  public abstract void c(RelativeLayout paramRelativeLayout);

  public final void c(String paramString)
  {
  }

  public int d()
  {
    return 0;
  }

  public abstract String e();

  public abstract boolean f();

  protected final void g()
  {
    this.h.setVisibility(8);
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt1, boolean paramBoolean, int paramInt2, a.a parama);
  }

  public static abstract interface b
  {
    public abstract void a(String paramString1, String paramString2);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.views.order.AbstractMethod
 * JD-Core Version:    0.6.2
 */