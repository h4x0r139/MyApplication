package com.unionpay.mobile.android.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.m;
import java.util.ArrayList;

public abstract class BaseActivity extends Activity
  implements a, b
{
  public static String[][] a;
  public static IntentFilter[] b;
  private static int g = 0;
  private ArrayList<com.unionpay.mobile.android.nocard.views.b> c = null;
  private l d = null;
  private a e = null;
  private m f = null;

  static
  {
    try
    {
      String[][] arrayOfString; = new String[3][];
      String[] arrayOfString1 = new String[1];
      arrayOfString1[0] = IsoDep.class.getName();
      arrayOfString;[0] = arrayOfString1;
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = NfcV.class.getName();
      arrayOfString;[1] = arrayOfString2;
      String[] arrayOfString3 = new String[1];
      arrayOfString3[0] = NfcF.class.getName();
      arrayOfString;[2] = arrayOfString3;
      a = arrayOfString;;
      IntentFilter[] arrayOfIntentFilter = new IntentFilter[1];
      arrayOfIntentFilter[0] = new IntentFilter("android.nfc.action.TECH_DISCOVERED", "*/*");
      b = arrayOfIntentFilter;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public final Object a(String paramString)
  {
    com.unionpay.mobile.android.model.b localb;
    if (paramString == null)
      localb = this.e.a;
    boolean bool;
    do
    {
      return localb;
      if (paramString.equalsIgnoreCase(UPPayEngine.class.toString()))
        return this.e.b;
      bool = paramString.equalsIgnoreCase(m.class.toString());
      localb = null;
    }
    while (!bool);
    return this.f;
  }

  public final void a()
  {
    int i = this.c.size();
    if (i > 0)
    {
      this.c.remove(i - 1);
      if (this.c.size() != 0)
        setContentView((View)this.c.get(-1 + this.c.size()));
    }
  }

  public final void a(int paramInt)
  {
    for (int i = -1 + this.c.size(); ; i--)
    {
      if (i >= 0)
      {
        com.unionpay.mobile.android.nocard.views.b localb = (com.unionpay.mobile.android.nocard.views.b)this.c.get(i);
        if (localb.h() == paramInt)
          setContentView(localb);
      }
      else
      {
        return;
      }
      this.c.remove(i);
    }
  }

  public final void a(com.unionpay.mobile.android.nocard.views.b paramb)
  {
    this.c.add(paramb);
    setContentView(paramb);
  }

  public final void b()
  {
    this.c.clear();
    this.d.s();
    this.d = null;
    com.unionpay.mobile.android.languages.c.by = null;
    int i = -1 + g;
    g = i;
    if (i == 0)
      com.unionpay.mobile.android.resource.c.a(this).a();
    this.f.c();
    this.f = null;
    this.e.b = null;
    this.e.a = null;
    this.e = null;
    ((ViewGroup)getWindow().getDecorView().findViewById(16908290)).removeAllViews();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.d != null)
    {
      this.d.r();
      this.d = null;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    g.a("uppay", "PayActivityEx.onCreate() +++");
    com.unionpay.mobile.android.languages.c.a();
    com.unionpay.mobile.android.global.a.a(this);
    this.c = new ArrayList(1);
    this.e = new a(c());
    this.f = new m(this);
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    this.d = ((l)b(1));
    setContentView(this.d);
    g = 1 + g;
    g.a("uppay", "PayActivityEx.onCreate() ---");
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.c.size() > 0)
        ((com.unionpay.mobile.android.nocard.views.b)this.c.get(-1 + this.c.size())).l();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.f.a())
      this.f.b();
  }

  private final class a
  {
    public com.unionpay.mobile.android.model.b a = null;
    public UPPayEngine b = null;

    public a(UPPayEngine arg2)
    {
      Object localObject;
      this.b = localObject;
      this.b.a(this.a);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.plugin.BaseActivity
 * JD-Core Version:    0.6.2
 */