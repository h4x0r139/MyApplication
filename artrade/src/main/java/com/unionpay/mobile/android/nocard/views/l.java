package com.unionpay.mobile.android.nocard.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.plugin.BaseActivity;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.i.a;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class l extends b
  implements i.a
{
  private Activity A;
  private boolean B;
  private boolean C = false;
  public List<com.unionpay.mobile.android.model.c> p = null;
  public List<com.unionpay.mobile.android.model.c> q = null;
  private ProgressBar r = null;
  private String s = null;
  private int t = 0;
  private volatile int u = 0;
  private boolean v = false;
  private boolean w = false;
  private JSONArray x;
  private d y;
  private long z;

  public l(Context paramContext)
  {
    super(paramContext);
    this.f = 1;
    d();
    this.A = ((Activity)paramContext);
    this.B = com.unionpay.mobile.android.nocard.utils.a.a(this.A.getIntent(), this.a);
    if (this.a.aC)
    {
      setVisibility(8);
      this.b.a(com.unionpay.mobile.android.languages.c.by.c);
    }
    boolean bool1;
    if (!this.C)
    {
      this.C = true;
      this.u = (1 + this.u);
      u();
      this.w = false;
      Activity localActivity = this.A;
      bool1 = this.B;
      this.e.a();
      UPPayEngine localUPPayEngine = this.e;
      boolean bool2 = this.a.c;
      int i = 0;
      if (bool2)
        i = 1;
      this.z = localUPPayEngine.initJNIEnv(localActivity, i, Integer.decode(this.a.D.c).intValue(), this.a.D.d, this.a.a, this.a.aE);
      if ((!bool1) || (this.z == 0L) || (this.z == -1L))
        break label310;
      this.t = 1;
      this.e.a(this.z);
      this.e.a(this.a.b, "000");
      this.e.a(this);
    }
    label310: 
    do
    {
      return;
      if (this.z == -1L)
      {
        b(7, null);
        return;
      }
    }
    while (bool1);
    b(5, null);
  }

  private void a(String paramString1, String paramString2, String paramString3)
  {
    m localm = new m(this, paramString3);
    n localn = new n(this);
    if (paramString1.equalsIgnoreCase("01"));
    for (int i = 0; i != 0; i = 1)
    {
      this.b.a(localm, localn);
      this.b.a(com.unionpay.mobile.android.languages.c.by.ae, paramString2, com.unionpay.mobile.android.languages.c.by.af, com.unionpay.mobile.android.languages.c.by.ag);
      return;
    }
    this.b.a(localm, localn);
    this.b.a(com.unionpay.mobile.android.languages.c.by.Y, paramString2, com.unionpay.mobile.android.languages.c.by.af, com.unionpay.mobile.android.languages.c.by.ag);
  }

  private void b(int paramInt, String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
      this.a.D.f = paramString;
    this.r.setVisibility(4);
    a(c(paramInt), true);
  }

  private boolean f(int paramInt)
  {
    if ((paramInt & 0x2) != 0)
    {
      this.a.ar = true;
      return true;
    }
    return false;
  }

  private final boolean t()
  {
    try
    {
      if (this.x != null);
      for (String str = this.x.getString(3); ; str = null)
      {
        boolean bool1 = false;
        if (str != null)
        {
          int i = str.length();
          bool1 = false;
          if (i > 0)
          {
            boolean bool2 = "null".equalsIgnoreCase(str);
            bool1 = false;
            if (!bool2)
              bool1 = true;
          }
        }
        return bool1;
      }
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return false;
  }

  private final void u()
  {
    int i = 1;
    while (true)
    {
      int j;
      try
      {
        g.c("uppay", "showContentView() +++" + this.u);
        if (this.u != 2)
        {
          int k;
          if (!this.w)
          {
            k = i;
            if ((this.w) && (!"1".equalsIgnoreCase(this.a.ai)))
            {
              int m = this.a.aj;
              boolean bool2 = f(m);
              if ((!bool2) || ((m & 0x11101) != 0))
                break label852;
              n = i;
              if (n == 0)
                if ((k == 0) && (i == 0))
                  continue;
            }
          }
          else
          {
            k = 0;
            continue;
          }
          i = 0;
          continue;
        }
        if (this.v)
          continue;
        this.v = true;
        if (this.a.aC)
          this.b.c();
        j = this.a.aj;
        if (!"1".equalsIgnoreCase(this.a.ai))
          break label322;
        if (this.a.au)
        {
          this.a.l = null;
          if ((!"0".equalsIgnoreCase(this.a.ai)) || ((this.a.l != null) && (this.a.l.size() > 0)) || (f(j)))
            break label542;
          String str6 = this.a.ak;
          if ("fail".length() > 0)
            this.a.D.f = "fail";
          this.r.setVisibility(4);
          a(str6, true);
          continue;
        }
      }
      finally
      {
      }
      this.a.l = this.p;
      continue;
      label322: if ("0".equalsIgnoreCase(this.a.ai))
        this.a.l = this.q;
      while (true)
      {
        if ((this.a.l == null) || (this.a.l.size() <= 0))
          break label540;
        Iterator localIterator1 = this.a.l.iterator();
        while (localIterator1.hasNext())
        {
          com.unionpay.mobile.android.model.c localc1 = (com.unionpay.mobile.android.model.c)localIterator1.next();
          if ((localc1.c() != 0) && ((j & localc1.c()) == 0))
            localIterator1.remove();
        }
        break;
        if (this.a.au)
        {
          this.a.l = this.q;
        }
        else
        {
          if ((this.q != null) && (this.q.size() > 0))
            this.a.l.addAll(this.q);
          if ((this.p != null) && (this.p.size() > 0))
            this.a.l.addAll(this.p);
        }
      }
      label540: continue;
      label542: boolean bool1 = "1".equalsIgnoreCase(this.a.ai);
      if (!bool1);
      while (true)
      {
        try
        {
          if (this.x == null)
            break label780;
          str1 = this.x.getString(0);
          if (this.x == null)
            break label791;
          str2 = this.x.getString(1);
          if (this.x == null)
            break label802;
          str3 = this.x.getString(2);
          if (!t())
            break label813;
          str4 = this.x.getString(3);
          if (this.a.l != null)
          {
            Iterator localIterator2 = this.a.l.iterator();
            if (localIterator2.hasNext())
            {
              com.unionpay.mobile.android.model.c localc2 = (com.unionpay.mobile.android.model.c)localIterator2.next();
              if (localc2.c() == 0)
                continue;
              str5 = "";
              switch (localc2.c())
              {
              default:
                localc2.a(str5);
                continue;
              case 16:
              case 8:
              case 4:
              case 1:
              }
            }
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
        a(2, this.y);
        g.c("uppay", "showContentView() ---");
        break;
        label780: String str1 = com.unionpay.mobile.android.languages.c.by.ak;
        continue;
        label791: String str2 = com.unionpay.mobile.android.languages.c.by.aj;
        continue;
        label802: String str3 = com.unionpay.mobile.android.languages.c.by.ai;
        continue;
        label813: String str4 = com.unionpay.mobile.android.languages.c.by.al;
        continue;
        String str5 = str3;
        continue;
        str5 = str1;
        continue;
        str5 = str2;
        continue;
        str5 = str4;
      }
      label852: int n = 0;
    }
  }

  public final void a(int paramInt, byte[] paramArrayOfByte)
  {
    j();
    if (paramInt != 0)
      b(paramInt, null);
    g.a("uppay", "status = " + paramInt);
    if (paramArrayOfByte != null)
    {
      boolean bool = "mounted".equals(Environment.getExternalStorageState());
      int i = 0;
      if (bool)
        i = 1;
      if (i == 0)
        b(9, null);
    }
    else
    {
      return;
    }
    if (h.a(paramArrayOfByte) == true)
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      String str = Environment.getExternalStorageDirectory() + File.separator + "UPPay" + File.separator + "UPPayPluginEx.apk";
      g.a("uppay", "apk path:" + str);
      localIntent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
      ((BaseActivity)this.d).startActivityForResult(localIntent, 100);
      return;
    }
    b(21, null);
  }

  public final void a(JSONObject paramJSONObject)
  {
    g.a("uppay", "init.parserParamJsonObj() +++");
    if (paramJSONObject == null)
    {
      b(2);
      return;
    }
    switch (this.t)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      g.a("uppay", "init.parserParamJsonObj() ---");
      return;
      String str1 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "secret");
      com.unionpay.mobile.android.utils.f.a(paramJSONObject, "sec_sign");
      this.e.c(str1);
      JSONObject localJSONObject = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "upgrade_info");
      this.s = com.unionpay.mobile.android.utils.f.a(localJSONObject, "type");
      String str2 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "desc");
      String str3 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "url");
      if (this.s.equalsIgnoreCase("02"))
      {
        a(this.s, str2, str3);
      }
      else
      {
        this.a.j = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "title");
        this.a.g = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "init_button");
        this.a.e = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "order");
        this.a.f = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "risk_info");
        List localList = com.unionpay.mobile.android.utils.f.d(paramJSONObject, "cards");
        if ((localList != null) && (localList.size() > 0))
          this.p = new ArrayList(localList.size());
        for (int j = 0; (localList != null) && (j < localList.size()); j++)
        {
          com.unionpay.mobile.android.model.a locala = new com.unionpay.mobile.android.model.a(com.unionpay.mobile.android.utils.f.a((JSONArray)localList.get(j), 0), com.unionpay.mobile.android.utils.f.a((JSONArray)localList.get(j), 1), com.unionpay.mobile.android.utils.f.a((JSONArray)localList.get(j), 2), '\000');
          this.p.add(locala);
        }
        this.a.n = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "bank_url");
        this.a.o = com.unionpay.mobile.android.utils.f.c(paramJSONObject, "input_info");
        this.a.q = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "account_info");
        this.a.r = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "other_card_info");
        this.a.p = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "user_id");
        this.e.a(com.unionpay.mobile.android.utils.f.a(paramJSONObject, "sid"));
        this.e.b(com.unionpay.mobile.android.utils.f.a(paramJSONObject, "secret"));
        String str4 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "secret");
        if (!TextUtils.isEmpty(str4))
          this.a.i = str4;
        String str5 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "uid");
        if ((str5 != null) && (!TextUtils.isEmpty(str5)))
          PreferenceUtils.a(this.d, str5);
        this.a.am = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "ad");
        this.a.ao = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "pay_tip");
        String str6 = com.unionpay.mobile.android.utils.f.a(paramJSONObject, "sup_pay_method");
        if (!TextUtils.isEmpty(str6))
          this.a.au = "01".equals(str6);
        this.a.ap = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "find_pwd_url");
        this.a.T = com.unionpay.mobile.android.utils.f.b(paramJSONObject, "reg_url");
        com.unionpay.mobile.android.model.b localb = this.a;
        if (!"0".equals(com.unionpay.mobile.android.utils.f.a(paramJSONObject, "sup_nfc")));
        for (boolean bool = true; ; bool = false)
        {
          localb.at = bool;
          if (this.s.equalsIgnoreCase("00"))
            break label652;
          a(this.s, str2, str3);
          break;
        }
        label652: if (b(this.a.p))
        {
          this.t = 2;
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = this.a.p;
          String str7 = String.format("\"user_id\":\"%s\"", arrayOfObject);
          this.e.h(str7);
        }
        else
        {
          r();
          continue;
          com.unionpay.mobile.android.nocard.utils.f.c(this.a, paramJSONObject);
          int i = com.unionpay.mobile.android.nocard.utils.f.b(this.a, paramJSONObject);
          if (i != 0)
          {
            b(i);
          }
          else
          {
            this.y = com.unionpay.mobile.android.nocard.utils.f.a(paramJSONObject);
            r();
          }
        }
      }
    }
  }

  public final void b(int paramInt)
  {
    g.a("uppay", toString() + "doErrHappended() +++");
    b(paramInt, "fail");
    g.a("uppay", toString() + "doErrHappended() ---");
  }

  public final void c(String paramString)
  {
    this.b.a(com.unionpay.mobile.android.languages.c.by.U);
    new i(paramString, this).a();
  }

  protected final void d()
  {
    super.d();
    this.l.setBackgroundColor(-1);
    setBackgroundDrawable(this.c.a(3008));
    int i = com.unionpay.mobile.android.global.a.I / 2;
    ImageView localImageView = new ImageView(getContext());
    localImageView.setImageDrawable(this.c.a(1027, i, -1));
    localImageView.setId(localImageView.hashCode());
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(i, -2);
    localLayoutParams1.addRule(14);
    localLayoutParams1.leftMargin = com.unionpay.mobile.android.global.a.j;
    localLayoutParams1.topMargin = ((int)(0.3F * com.unionpay.mobile.android.global.a.t));
    addView(localImageView, localLayoutParams1);
    this.r = new ProgressBar(getContext(), null, 16843399);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(14, -1);
    localLayoutParams2.addRule(3, localImageView.getId());
    localLayoutParams2.topMargin = (3 * com.unionpay.mobile.android.global.a.d);
    addView(this.r, localLayoutParams2);
    LinearLayout localLinearLayout = new LinearLayout(this.d);
    localLinearLayout.setOrientation(1);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams3.addRule(14, -1);
    localLayoutParams3.addRule(12, -1);
    localLayoutParams3.bottomMargin = com.unionpay.mobile.android.global.a.b;
    addView(localLinearLayout, localLayoutParams3);
    TextView localTextView1 = new TextView(this.d);
    localTextView1.setText(com.unionpay.mobile.android.languages.c.by.a);
    localTextView1.setTextColor(-1);
    localTextView1.setTextSize(14.0F);
    localTextView1.setGravity(1);
    new LinearLayout.LayoutParams(-2, -2).gravity = 14;
    localLinearLayout.addView(localTextView1, localLayoutParams3);
    TextView localTextView2 = new TextView(getContext());
    localTextView2.setText(com.unionpay.mobile.android.languages.c.by.b);
    localTextView2.setTextColor(-1);
    localTextView2.setTextSize(16.0F);
    localTextView2.setGravity(1);
    new LinearLayout.LayoutParams(-2, -2).gravity = 14;
    localLinearLayout.addView(localTextView2, localLayoutParams3);
  }

  public final void l()
  {
  }

  public final void r()
  {
    if (this.s.equalsIgnoreCase("02"))
    {
      k();
      return;
    }
    this.u = (1 + this.u);
    this.w = true;
    u();
  }

  public final void s()
  {
    removeAllViews();
    this.r = null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.l
 * JD-Core Version:    0.6.2
 */