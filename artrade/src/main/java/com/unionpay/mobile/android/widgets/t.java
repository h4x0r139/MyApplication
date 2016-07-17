package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.c;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class t extends LinearLayout
  implements View.OnClickListener
{
  private Context a = null;
  private EditText b = null;
  private ImageView c = null;
  private boolean d = true;
  private b e = null;
  private a f = null;
  private int g;
  private Drawable h;
  private TextView i = null;
  private LinearLayout j;
  private View.OnClickListener k = new u(this);
  private TextWatcher l = new v(this);
  private View.OnFocusChangeListener m = new w(this);

  public t(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext;
    setOrientation(0);
    this.g = a.n;
    setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    setFocusable(true);
    TextView localTextView = new TextView(this.a);
    localTextView.setPadding(c.a(this.a, 10.0F), 0, 0, 0);
    localTextView.setEms(4);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -1);
    localLayoutParams1.gravity = 19;
    addView(localTextView, localLayoutParams1);
    localTextView.setGravity(19);
    localTextView.setTextSize(b.k);
    localTextView.setTextColor(-13421773);
    this.i = localTextView;
    this.i.setVisibility(8);
    RelativeLayout localRelativeLayout1 = new RelativeLayout(this.a);
    localRelativeLayout1.setGravity(21);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams1.gravity = 19;
    addView(localRelativeLayout1, localLayoutParams2);
    LinearLayout localLinearLayout = new LinearLayout(this.a);
    localLinearLayout.setGravity(21);
    localLinearLayout.setId(localLinearLayout.hashCode());
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, -1);
    localLayoutParams3.addRule(11, -1);
    localLayoutParams3.addRule(15, -1);
    localLayoutParams3.rightMargin = c.a(this.a, 10.0F);
    localLinearLayout.setVisibility(8);
    localRelativeLayout1.addView(localLinearLayout, localLayoutParams3);
    this.j = localLinearLayout;
    RelativeLayout localRelativeLayout2 = new RelativeLayout(this.a);
    localRelativeLayout2.setGravity(16);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams4.addRule(9, -1);
    localLayoutParams4.addRule(15, -1);
    localLayoutParams4.addRule(0, localLinearLayout.getId());
    localLayoutParams4.rightMargin = c.a(this.a, 10.0F);
    localRelativeLayout1.addView(localRelativeLayout2, localLayoutParams4);
    this.c = new ImageView(this.a);
    this.c.setId(this.c.hashCode());
    this.c.setBackgroundDrawable(this.h);
    this.c.setOnClickListener(this.k);
    this.c.setVisibility(8);
    this.c.setId(this.c.hashCode());
    this.c.setAdjustViewBounds(true);
    int n = c.a(this.a, 30.0F);
    RelativeLayout.LayoutParams localLayoutParams5 = new RelativeLayout.LayoutParams(n, n);
    localLayoutParams5.addRule(11, -1);
    localLayoutParams5.addRule(15, -1);
    localRelativeLayout2.addView(this.c, localLayoutParams5);
    this.b = new EditText(this.a);
    this.b.setSingleLine();
    this.b.setTextSize(b.k);
    this.b.setTextColor(-10066330);
    this.b.setHintTextColor(-6710887);
    this.b.setBackgroundDrawable(null);
    this.b.setGravity(16);
    this.b.setPadding(c.a(this.a, 10.0F), 0, 0, 0);
    this.b.addTextChangedListener(this.l);
    if (this.d)
      this.b.setOnFocusChangeListener(this.m);
    RelativeLayout.LayoutParams localLayoutParams6 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams6.addRule(0, this.c.getId());
    localLayoutParams6.addRule(15, -1);
    localLayoutParams6.addRule(9, -1);
    localRelativeLayout2.addView(this.b, localLayoutParams6);
  }

  public final TextView a(String paramString)
  {
    if ((this.i != null) && (!TextUtils.isEmpty(paramString)))
    {
      this.i.setVisibility(0);
      this.i.setText(paramString);
    }
    return this.i;
  }

  public final t a(Drawable paramDrawable)
  {
    if (paramDrawable != null)
      this.c.setBackgroundDrawable(paramDrawable);
    return this;
  }

  public final void a()
  {
    this.d = false;
    if (this.b != null)
    {
      this.b.setKeyListener(null);
      this.b.setFocusable(false);
      if ((this.c != null) && (this.c.getVisibility() == 0))
        this.c.setVisibility(8);
    }
  }

  public final void a(int paramInt)
  {
    if (this.b != null)
      this.b.setInputType(paramInt);
  }

  public final void a(InputFilter paramInputFilter)
  {
    InputFilter[] arrayOfInputFilter1 = { paramInputFilter };
    if (this.b == null)
      return;
    InputFilter[] arrayOfInputFilter2 = this.b.getFilters();
    if (arrayOfInputFilter2 == null)
    {
      this.b.setFilters(arrayOfInputFilter1);
      return;
    }
    InputFilter[] arrayOfInputFilter3 = new InputFilter[arrayOfInputFilter2.length + arrayOfInputFilter1.length];
    System.arraycopy(arrayOfInputFilter2, 0, arrayOfInputFilter3, 0, arrayOfInputFilter2.length);
    System.arraycopy(arrayOfInputFilter1, 0, arrayOfInputFilter3, arrayOfInputFilter2.length, arrayOfInputFilter1.length);
    this.b.setFilters(arrayOfInputFilter3);
  }

  public final void a(TextWatcher paramTextWatcher)
  {
    if ((this.b != null) && (paramTextWatcher != null))
      this.b.addTextChangedListener(paramTextWatcher);
  }

  public final void a(View paramView, LinearLayout.LayoutParams paramLayoutParams)
  {
    if ((paramView != null) && (this.j != null))
    {
      this.j.addView(paramView, paramLayoutParams);
      this.j.setVisibility(0);
    }
  }

  public final void a(TextView.OnEditorActionListener paramOnEditorActionListener)
  {
    if ((this.b != null) && (this.d))
      this.b.setOnEditorActionListener(paramOnEditorActionListener);
  }

  public final void a(a parama)
  {
    this.f = parama;
  }

  public final void a(b paramb)
  {
    this.e = paramb;
    if (this.b != null)
      this.b.setOnClickListener(this);
  }

  public final String b()
  {
    EditText localEditText = this.b;
    String str = null;
    if (localEditText != null)
      str = this.b.getText().toString();
    return str;
  }

  public final void b(int paramInt)
  {
    if (this.b != null)
      this.b.setSelection(paramInt);
  }

  public final void b(String paramString)
  {
    if ((this.b != null) && (paramString != null))
      this.b.setHint(paramString);
  }

  public final Editable c()
  {
    EditText localEditText = this.b;
    Editable localEditable = null;
    if (localEditText != null)
      localEditable = this.b.getText();
    return localEditable;
  }

  public final void c(String paramString)
  {
    if ((this.b != null) && (paramString != null))
      this.b.setText(paramString);
  }

  public final void d()
  {
    if (this.b != null)
      this.b.setLongClickable(false);
  }

  public final void e()
  {
    if (this.b == null);
    do
    {
      return;
      this.b.setText("");
    }
    while (this.e == null);
    this.e.d();
  }

  public final void f()
  {
    ((Activity)this.a).getWindow().setSoftInputMode(3);
    int n = Build.VERSION.SDK_INT;
    String str;
    if (n >= 16)
      str = "setShowSoftInputOnFocus";
    while (str == null)
    {
      this.b.setInputType(0);
      return;
      str = null;
      if (n >= 14)
        str = "setSoftInputShownOnFocus";
    }
    try
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Boolean.TYPE;
      Method localMethod = EditText.class.getMethod(str, arrayOfClass);
      localMethod.setAccessible(true);
      EditText localEditText = this.b;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(false);
      localMethod.invoke(localEditText, arrayOfObject);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      this.b.setInputType(0);
      localNoSuchMethodException.printStackTrace();
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
  }

  public final void onClick(View paramView)
  {
    if (this.e != null)
      this.e.a_();
  }

  public final void setOnTouchListener(View.OnTouchListener paramOnTouchListener)
  {
    if (this.b != null)
      this.b.setOnTouchListener(paramOnTouchListener);
  }

  public static abstract interface a
  {
    public abstract void a(boolean paramBoolean);
  }

  public static abstract interface b extends t.a
  {
    public abstract void a_();

    public abstract void d();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.t
 * JD-Core Version:    0.6.2
 */