package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.unionpay.mobile.android.global.a;
import org.json.JSONObject;

public abstract class z extends y
{
  protected int a;
  protected t b = null;
  private a n = null;

  public z(Context paramContext, int paramInt, JSONObject paramJSONObject)
  {
    this(paramContext, paramInt, paramJSONObject, (byte)0);
  }

  public z(Context paramContext, int paramInt, JSONObject paramJSONObject, byte paramByte)
  {
    super(paramContext, paramJSONObject);
    this.a = paramInt;
    com.unionpay.mobile.android.resource.c.a(this.c);
    Context localContext = getContext();
    this.b = new t(localContext);
    if (this.h)
    {
      this.b.a();
      this.b.d();
    }
    this.b.c(h());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, a.n);
    localLayoutParams.addRule(15, -1);
    this.l.addView(this.b, localLayoutParams);
    this.b.b(com.unionpay.mobile.android.utils.f.a(paramJSONObject, "placeholder"));
    this.b.setFocusable(true);
    this.b.a(new aa(this));
    this.b.a(new ab(this));
    Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(this.c).a(2000, -1, a.v);
    this.b.a(localDrawable);
    if ((this instanceof ae))
      if (!this.h)
        this.b.a(com.unionpay.mobile.android.languages.c.by.aQ);
    while ((this instanceof k))
    {
      this.b.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1011));
      return;
      this.b.c(this.g + " " + this.f);
      continue;
      if ((this instanceof ao))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aR);
      else if ((this instanceof ag))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aT);
      else if ((this instanceof UPWidget))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aS);
      else if ((this instanceof at))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aU);
      else if ((this instanceof an))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aV);
      else if ((this instanceof e))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aW);
      else if ((this instanceof ad))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aX);
      else if ((this instanceof bc))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aY);
      else if ((this instanceof as))
        this.b.a(com.unionpay.mobile.android.languages.c.by.aZ);
      else if ((this instanceof au))
        this.b.a(com.unionpay.mobile.android.languages.c.by.ba);
      else if ((this instanceof f))
        this.b.a(com.unionpay.mobile.android.languages.c.by.bb);
    }
    this.b.setBackgroundDrawable(com.unionpay.mobile.android.resource.c.a(this.c).a(1013));
  }

  public String a()
  {
    return this.b.b();
  }

  public void a(Editable paramEditable)
  {
  }

  public final void a(a parama)
  {
    this.n = parama;
  }

  protected final boolean a(View paramView)
  {
    boolean bool = true;
    if (paramView == null)
      throw new NullPointerException();
    Rect localRect1 = new Rect();
    paramView.getGlobalVisibleRect(localRect1);
    Log.e("uppay", "v getGlobalVisibleRect():" + localRect1.toString());
    Rect localRect2 = new Rect();
    ((Activity)this.c).getWindow().getDecorView().findViewById(16908290).getGlobalVisibleRect(localRect2);
    int[] arrayOfInt1 = new int[2];
    paramView.getLocationInWindow(arrayOfInt1);
    Log.e("uppay", " locationW = [" + arrayOfInt1[0] + "," + arrayOfInt1[bool] + "]");
    int[] arrayOfInt2 = new int[2];
    paramView.getLocationOnScreen(arrayOfInt2);
    Log.e("uppay", " locationS = [" + arrayOfInt2[0] + "," + arrayOfInt2[bool] + "]");
    if (10 + (arrayOfInt1[bool] + paramView.getHeight()) > localRect2.bottom);
    while (true)
    {
      View localView = ((Activity)this.c).getWindow().getDecorView().findViewById(16908290);
      Rect localRect3 = new Rect();
      localView.getLocalVisibleRect(localRect3);
      Log.e("uppay", " getLocalVisibleRect = " + localRect3.toString());
      Rect localRect4 = new Rect();
      localView.getGlobalVisibleRect(localRect4);
      Log.e("uppay", " getGlobalVisibleRect = " + localRect4.toString());
      return bool;
      bool = false;
    }
  }

  public final boolean a(t paramt)
  {
    return (paramt != null) && (this.b == paramt);
  }

  public boolean c()
  {
    return (a() != null) && (a().length() != 0);
  }

  public final void f()
  {
    if ((this.b != null) && (!this.h))
      this.b.e();
  }

  public static abstract interface a
  {
    public abstract void a(t paramt, String paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.z
 * JD-Core Version:    0.6.2
 */