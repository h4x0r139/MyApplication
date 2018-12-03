package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.unionpay.mobile.android.upwidget.o.a;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.ac;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.ae;
import com.unionpay.mobile.android.widgets.ag;
import com.unionpay.mobile.android.widgets.ag.a;
import com.unionpay.mobile.android.widgets.ai;
import com.unionpay.mobile.android.widgets.an;
import com.unionpay.mobile.android.widgets.ao;
import com.unionpay.mobile.android.widgets.ao.a;
import com.unionpay.mobile.android.widgets.as;
import com.unionpay.mobile.android.widgets.at;
import com.unionpay.mobile.android.widgets.au;
import com.unionpay.mobile.android.widgets.bc;
import com.unionpay.mobile.android.widgets.e;
import com.unionpay.mobile.android.widgets.m;
import com.unionpay.mobile.android.widgets.p;
import com.unionpay.mobile.android.widgets.t;
import com.unionpay.mobile.android.widgets.x;
import com.unionpay.mobile.android.widgets.y;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.mobile.android.widgets.z.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a extends LinearLayout
  implements o.a, ag.a, ao.a, z.a
{
  private Context a = null;
  private m b = null;
  private ArrayList<y> c = null;
  private long d = 0L;
  private b e = null;
  private boolean f = false;
  private boolean g = true;
  private JSONObject h = null;

  public a(Context paramContext, JSONArray paramJSONArray, long paramLong, b paramb, String paramString)
  {
    this(paramContext, paramJSONArray, paramLong, paramb, paramString, (byte)0);
  }

  private a(Context paramContext, JSONArray paramJSONArray, long paramLong, b paramb, String paramString, byte paramByte)
  {
    this(paramContext, paramJSONArray, paramLong, paramb, paramString, '\000');
  }

  private a(Context paramContext, JSONArray paramJSONArray, long paramLong, b paramb, String paramString, char paramChar)
  {
    this(paramContext, paramJSONArray, paramLong, paramb, paramString, false, null, null);
  }

  public a(Context paramContext, JSONArray paramJSONArray1, long paramLong, b paramb, String paramString, boolean paramBoolean, y paramy, JSONArray paramJSONArray2)
  {
    super(paramContext);
    this.a = paramContext;
    this.d = paramLong;
    this.e = paramb;
    this.f = paramBoolean;
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    int i = com.unionpay.mobile.android.global.a.f;
    localLayoutParams.bottomMargin = i;
    localLayoutParams.topMargin = i;
    setLayoutParams(localLayoutParams);
    setPadding(0, 0, 0, 0);
    setOrientation(1);
    setBackgroundColor(0);
    a(paramJSONArray1, paramString, true, paramy, paramJSONArray2);
  }

  public a(Context paramContext, JSONArray paramJSONArray, b paramb)
  {
    this(paramContext, paramJSONArray, -1L, paramb, null);
  }

  private static y a(List<y> paramList, String paramString)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      y localy = (y)localIterator.next();
      if (localy.m().equalsIgnoreCase(paramString))
        return localy;
    }
    return null;
  }

  public final a a()
  {
    a locala = new a("");
    y localy;
    if (this.c != null)
    {
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        localy = (y)localIterator.next();
        if ((localy instanceof ae))
        {
          if (!localy.c())
          {
            String str5 = com.unionpay.mobile.android.languages.c.by.aC;
            Object[] arrayOfObject4 = new Object[1];
            arrayOfObject4[0] = com.unionpay.mobile.android.languages.c.by.aE;
            locala.a(-1, String.format(str5, arrayOfObject4));
          }
          else if (!localy.b())
          {
            String str4 = com.unionpay.mobile.android.languages.c.by.aD;
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = com.unionpay.mobile.android.languages.c.by.aE;
            locala.a(-1, String.format(str4, arrayOfObject3));
          }
        }
        else
        {
          if (localy.c())
            break label203;
          String str3 = com.unionpay.mobile.android.languages.c.by.aC;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = localy.q();
          locala.a(-1, String.format(str3, arrayOfObject2));
        }
      }
    }
    while (true)
    {
      if (locala.a())
        break label249;
      return locala;
      label203: if (localy.b())
        break;
      String str2 = com.unionpay.mobile.android.languages.c.by.aD;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = localy.q();
      locala.a(-1, String.format(str2, arrayOfObject1));
    }
    label249: StringBuffer localStringBuffer = new StringBuffer();
    if (this.c != null)
      for (int i = 0; i < this.c.size(); i++)
        if ((!((y)this.c.get(i) instanceof ac)) && (!TextUtils.isEmpty(((y)this.c.get(i)).g())) && (((y)this.c.get(i)).e()))
        {
          localStringBuffer.append(",");
          localStringBuffer.append(((y)this.c.get(i)).g());
        }
    String str1 = localStringBuffer.toString();
    if (str1.length() > 1)
      str1 = str1.substring(1);
    locala.a(0, str1);
    return locala;
  }

  public final String a(String paramString)
  {
    y localy = a(this.c, paramString);
    String str = "";
    if (localy != null)
      str = localy.g();
    com.unionpay.mobile.android.utils.g.a("uppay", " name:" + paramString + ", value:" + str);
    return str;
  }

  public final void a(int paramInt)
  {
    y localy = a(this.c, "sms");
    if (localy != null)
      ((ao)localy).a(paramInt);
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    y localy = c("promotion");
    if ((localy != null) && ((localy instanceof ai)))
      ((ai)localy).b(paramOnClickListener);
  }

  public final void a(m paramm, JSONObject paramJSONObject)
  {
    this.b = paramm;
    this.h = paramJSONObject;
  }

  public final void a(t paramt, String paramString)
  {
    boolean bool;
    if (this.e != null)
    {
      if ((paramString != null) && (paramString.length() > 0))
        break label31;
      bool = true;
    }
    while (true)
    {
      this.e.a(bool);
      return;
      label31: if (this.c != null)
      {
        Iterator localIterator = this.c.iterator();
        while (true)
          if (localIterator.hasNext())
          {
            y localy = (y)localIterator.next();
            if (((localy instanceof z)) && (!((z)localy).a(paramt)) && (!((z)localy).c()))
            {
              bool = true;
              break;
            }
          }
      }
      bool = false;
    }
  }

  public final void a(y paramy)
  {
    boolean bool = paramy instanceof ao;
    if ((this.e == null) || (!bool))
      return;
    b();
    a locala = new a("");
    y localy1 = a(this.c, "mobile");
    y localy2 = a(this.c, "pan");
    y localy3 = a(this.c, "card");
    y localy4 = a(this.c, "area_code");
    String str1 = "";
    if (localy2 != null)
    {
      if (localy2.c())
        break label156;
      String str11 = com.unionpay.mobile.android.languages.c.by.aC;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = com.unionpay.mobile.android.languages.c.by.aE;
      locala.a(-1, String.format(str11, arrayOfObject4));
    }
    while (!locala.a())
    {
      this.e.a(locala);
      return;
      label156: if (!localy2.b())
      {
        String str10 = com.unionpay.mobile.android.languages.c.by.aD;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = com.unionpay.mobile.android.languages.c.by.aE;
        locala.a(-1, String.format(str10, arrayOfObject3));
      }
      else
      {
        str1 = str1 + localy2.g();
      }
    }
    if (localy1 != null)
    {
      if (localy1.c())
        break label301;
      String str9 = com.unionpay.mobile.android.languages.c.by.aC;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localy1.q();
      locala.a(-1, String.format(str9, arrayOfObject2));
    }
    while (!locala.a())
    {
      this.e.a(locala);
      return;
      label301: if (!localy1.b())
      {
        String str8 = com.unionpay.mobile.android.languages.c.by.aD;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localy1.q();
        locala.a(-1, String.format(str8, arrayOfObject1));
      }
      else
      {
        StringBuilder localStringBuilder3 = new StringBuilder().append(str1);
        if (str1.length() == 0);
        for (String str6 = ""; ; str6 = ",")
        {
          String str7 = str6;
          str1 = str7 + localy1.g();
          break;
        }
      }
    }
    String str4;
    StringBuilder localStringBuilder1;
    if ((localy3 != null) && (localy3.g().length() > 0))
    {
      StringBuilder localStringBuilder2 = new StringBuilder().append(str1);
      if (str1.length() == 0)
      {
        str4 = "";
        String str5 = str4;
        str1 = str5 + localy3.g();
      }
    }
    else if ((localy4 != null) && (localy4.g().length() > 0))
    {
      localStringBuilder1 = new StringBuilder().append(str1);
      if (str1.length() != 0)
        break label606;
    }
    label606: for (String str2 = ""; ; str2 = ",")
    {
      String str3 = str2;
      str1 = str3 + localy4.g();
      locala.a(0, str1);
      this.e.a(locala);
      return;
      str4 = ",";
      break;
    }
  }

  public final void a(String paramString1, String paramString2)
  {
    if (this.e != null)
    {
      b();
      this.e.b(paramString1, paramString2);
    }
  }

  public final void a(String paramString, boolean paramBoolean)
  {
    if ("promotion".equalsIgnoreCase(paramString));
    y localy1;
    y localy2;
    for (String str = "instalment"; ; str = "promotion")
    {
      localy1 = c(paramString);
      localy2 = c(str);
      if (localy1 != null)
        break;
      return;
    }
    boolean bool1;
    if (localy2 != null)
      if ((localy2 instanceof p))
        bool1 = ((p)localy2).f();
    while (true)
    {
      if ((!paramBoolean) || (localy2 == null) || (!bool1))
      {
        if ((localy1 instanceof p))
          ((p)localy1).a(paramBoolean);
        if (!(localy1 instanceof ai))
          break;
        ((ai)localy1).a(paramBoolean);
        return;
        if (!(localy2 instanceof ai))
          break label342;
        bool1 = ((ai)localy2).f();
        continue;
      }
      if (localy2 == null)
        break;
      this.g = "instalment".equalsIgnoreCase(paramString);
      y localy3;
      if ("promotion".equalsIgnoreCase(paramString))
      {
        localy3 = localy1;
        label161: if (!"promotion".equalsIgnoreCase(paramString))
          break label298;
      }
      while (true)
      {
        if (!this.g)
          break label305;
        b localb = new b(this, localy3, localy2);
        c localc = new c(this, localy3, localy2);
        this.b.a(localb, localc);
        JSONObject localJSONObject1 = com.unionpay.mobile.android.utils.f.b(this.h, "func");
        JSONObject localJSONObject2 = com.unionpay.mobile.android.utils.f.b(this.h, "cancel");
        if (this.h == null)
          break;
        this.b.a(com.unionpay.mobile.android.utils.f.a(this.h, "title"), com.unionpay.mobile.android.utils.f.a(this.h, "text"), com.unionpay.mobile.android.utils.f.a(localJSONObject2, "label"), com.unionpay.mobile.android.utils.f.a(localJSONObject1, "label"));
        return;
        localy3 = localy2;
        break label161;
        label298: localy2 = localy1;
      }
      label305: ((ai)localy3).a(paramBoolean);
      p localp = (p)localy2;
      if (!paramBoolean);
      for (boolean bool2 = true; ; bool2 = false)
      {
        localp.a(bool2);
        return;
      }
      label342: bool1 = false;
    }
  }

  public final void a(JSONArray paramJSONArray)
  {
    y localy = c("promotion");
    if ((localy != null) && ((localy instanceof ai)))
      ((ai)localy).a(paramJSONArray);
  }

  public final void a(JSONArray paramJSONArray, String paramString)
  {
    y localy = c("promotion");
    if ((localy != null) && ((localy instanceof ai)))
      ((ai)localy).a(paramJSONArray, paramString);
  }

  public final void a(JSONArray paramJSONArray1, String paramString, boolean paramBoolean, y paramy, JSONArray paramJSONArray2)
  {
    if ((paramJSONArray1 == null) || (paramJSONArray1.length() <= 0))
      return;
    if (this.c == null)
      this.c = new ArrayList(1);
    while (true)
    {
      removeAllViews();
      setBackgroundColor(-1);
      new LinearLayout.LayoutParams(-1, -2);
      int i = com.unionpay.mobile.android.global.a.I - 4 * com.unionpay.mobile.android.global.a.f;
      Object localObject1 = null;
      int j = 0;
      Object localObject2 = "";
      label76: if (j >= paramJSONArray1.length())
        break;
      try
      {
        localJSONObject = paramJSONArray1.getJSONObject(j);
        str1 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "type");
      }
      catch (JSONException localJSONException2)
      {
        try
        {
          localContext = this.a;
          str2 = com.unionpay.mobile.android.utils.f.a(localJSONObject, "type");
          if ("pan".equalsIgnoreCase(str2))
          {
            ae localae = new ae(localContext, i, localJSONObject);
            localObject4 = localae;
          }
        }
        catch (JSONException localJSONException2)
        {
          while (true)
            try
            {
              JSONObject localJSONObject;
              String str1;
              Context localContext;
              String str2;
              Object localObject4;
              Object localObject5 = new LinearLayout.LayoutParams(-1, -2);
              Object localObject7 = localObject4;
              Object localObject6 = str1;
              LinearLayout localLinearLayout1;
              LinearLayout.LayoutParams localLayoutParams;
              Object localObject3;
              if (localObject7 != null)
              {
                if ((localObject7 instanceof UPWidget))
                {
                  ((UPWidget)localObject7).a(this.d);
                  ((UPWidget)localObject7).b(paramString);
                  ((UPWidget)localObject7).b(true);
                  if (((localObject7 instanceof z)) && (!(localObject7 instanceof bc)))
                    ((z)localObject7).a(this);
                  if (!"instalment".equals(localObject6))
                  {
                    localLinearLayout1 = new LinearLayout(this.a);
                    localLinearLayout1.setBackgroundColor(-3419943);
                    localLayoutParams = new LinearLayout.LayoutParams(-1, 1);
                    if (j == 0)
                      continue;
                    localLayoutParams.leftMargin = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
                    if ((!this.f) || (j != 0) || (paramy == null))
                      continue;
                    addView(paramy, (ViewGroup.LayoutParams)localObject5);
                    if (localObject7.getVisibility() == 0)
                      addView(localLinearLayout1, localLayoutParams);
                    addView(localObject7, (ViewGroup.LayoutParams)localObject5);
                    if ((j == -1 + paramJSONArray1.length()) || ((localObject7 instanceof ai)))
                    {
                      LinearLayout localLinearLayout2 = new LinearLayout(this.a);
                      localLinearLayout2.setBackgroundColor(-3419943);
                      addView(localLinearLayout2, new LinearLayout.LayoutParams(-1, 1));
                    }
                  }
                  this.c.add(localObject7);
                }
              }
              else
              {
                j++;
                localObject2 = localObject6;
                localObject1 = localObject5;
                break label76;
                this.c.clear();
                break;
                if ("mobile".equalsIgnoreCase(str2))
                {
                  localObject4 = new ag(localContext, i, localJSONObject);
                  continue;
                }
                if ("sms".equalsIgnoreCase(str2))
                {
                  localObject4 = new ao(localContext, i, localJSONObject, (byte)0);
                  continue;
                }
                if ("cvn2".equalsIgnoreCase(str2))
                {
                  localObject4 = new e(localContext, i, localJSONObject);
                  continue;
                }
                if ("expire".equalsIgnoreCase(str2))
                {
                  localObject4 = new au(localContext, i, localJSONObject);
                  continue;
                }
                if ("pwd".equalsIgnoreCase(str2))
                {
                  localObject4 = new UPWidget(localContext, i, localJSONObject);
                  continue;
                }
                if ("text".equalsIgnoreCase(str2))
                {
                  localObject4 = new as(localContext, i, localJSONObject);
                  continue;
                }
                if ("string".equalsIgnoreCase(str2))
                {
                  localObject4 = new ac(localContext, localJSONObject);
                  continue;
                }
                if ("cert_id".equalsIgnoreCase(str2))
                {
                  localObject4 = new com.unionpay.mobile.android.widgets.f(localContext, i, localJSONObject);
                  continue;
                }
                if ("cert_type".equalsIgnoreCase(str2))
                {
                  localObject4 = new com.unionpay.mobile.android.widgets.g(localContext, localJSONObject);
                  continue;
                }
                if ("name".equalsIgnoreCase(str2))
                {
                  localObject4 = new ad(localContext, i, localJSONObject);
                  continue;
                }
                if ("hidden".equalsIgnoreCase(str2))
                {
                  localObject4 = new x(localContext, localJSONObject);
                  continue;
                }
                if ("user_name".equalsIgnoreCase(str2))
                {
                  localObject4 = new at(localContext, i, localJSONObject);
                  continue;
                }
                if ("password".equalsIgnoreCase(str2))
                {
                  localObject4 = new an(localContext, i, localJSONObject);
                  continue;
                }
                if ("point".equalsIgnoreCase(str2))
                {
                  localObject4 = new bc(localContext, i, localJSONObject);
                  continue;
                }
                if ("instalment".equalsIgnoreCase(str2))
                {
                  p localp = new p(this.a, localJSONObject);
                  ((p)localp).a(this);
                  localObject4 = localp;
                  continue;
                }
                if ("promotion".equalsIgnoreCase(str2))
                {
                  ai localai = new ai(this.a, localJSONObject);
                  ((ai)localai).a(this);
                  localObject4 = localai;
                  continue;
                }
                if (!"area_code".equalsIgnoreCase(str2))
                  continue;
                com.unionpay.mobile.android.widgets.a locala = new com.unionpay.mobile.android.widgets.a(this.a, localJSONObject, paramJSONArray2);
                localObject4 = locala;
                continue;
                localJSONException1 = localJSONException1;
                localObject3 = localObject2;
                localObject4 = null;
                com.unionpay.mobile.android.utils.g.c("uppay", "json parser exception!!! - UPRuleView");
                localObject5 = localObject1;
                localObject6 = localObject3;
                localObject7 = localObject4;
                continue;
              }
              if ((localObject7 instanceof ao))
              {
                ((ao)localObject7).a(this);
              }
              else if ((localObject7 instanceof ag))
              {
                ((ag)localObject7).a(this);
                continue;
                if (this.f)
                {
                  localLayoutParams.leftMargin = com.unionpay.mobile.android.utils.c.a(this.a, 10.0F);
                  setPadding(0, 0, 0, 0);
                  continue;
                  if (localObject7.getVisibility() == 0)
                  {
                    addView(localLinearLayout1, localLayoutParams);
                    addView(localObject7, (ViewGroup.LayoutParams)localObject5);
                    if (((localObject7 instanceof ae)) && (paramy != null))
                    {
                      addView(paramy, (ViewGroup.LayoutParams)localObject5);
                      continue;
                      localJSONException2 = localJSONException2;
                      localObject3 = str1;
                      localObject4 = null;
                    }
                  }
                }
              }
            }
            catch (JSONException localJSONException3)
            {
              localObject3 = str1;
              continue;
              localObject4 = null;
            }
        }
      }
    }
  }

  public final String b(String paramString)
  {
    y localy = a(this.c, paramString);
    String str = "";
    if (localy != null)
      str = localy.a();
    return str;
  }

  public final void b(View.OnClickListener paramOnClickListener)
  {
    y localy = c("promotion");
    if ((localy != null) && ((localy instanceof ai)))
      ((ai)localy).c(paramOnClickListener);
  }

  public final boolean b()
  {
    if (this.c != null)
    {
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        y localy = (y)localIterator.next();
        if (((localy instanceof UPWidget)) && (((UPWidget)localy).i()))
          ((UPWidget)localy).j();
      }
    }
    for (boolean bool = true; ; bool = false)
    {
      ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
      return bool;
    }
  }

  public final y c(String paramString)
  {
    if ((this.c == null) || (this.c.size() <= 0) || (TextUtils.isEmpty(paramString)))
      return null;
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
    {
      y localy = (y)localIterator.next();
      if (localy.n().equalsIgnoreCase(paramString))
        return localy;
    }
    return null;
  }

  public final void c(View.OnClickListener paramOnClickListener)
  {
    y localy = c("promotion");
    if ((localy != null) && ((localy instanceof ai)))
      ((ai)localy).a(paramOnClickListener);
  }

  public final boolean c()
  {
    if (this.c != null)
    {
      Iterator localIterator = this.c.iterator();
      y localy;
      do
      {
        if (!localIterator.hasNext())
          break;
        localy = (y)localIterator.next();
      }
      while ((!(localy instanceof z)) || (((z)localy).c()));
    }
    for (int i = 1; i == 0; i = 0)
      return true;
    return false;
  }

  public final void d()
  {
    if ((this.c == null) || (this.c.size() <= 0));
    while (true)
    {
      return;
      Iterator localIterator = this.c.iterator();
      while (localIterator.hasNext())
      {
        y localy = (y)localIterator.next();
        if (((localy instanceof UPWidget)) || ((localy instanceof e)) || ((localy instanceof au)))
          ((z)localy).f();
      }
    }
  }

  public final void d(String paramString)
  {
    if (this.e != null)
      this.e.c(paramString);
  }

  public final class a
  {
    public int a = 0;
    public String b;

    a(String arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    public final void a(int paramInt, String paramString)
    {
      this.b = paramString;
      this.a = paramInt;
    }

    public final boolean a()
    {
      return this.a == 0;
    }
  }

  public static abstract interface b
  {
    public abstract void a(a.a parama);

    public abstract void a(boolean paramBoolean);

    public abstract void b(String paramString1, String paramString2);

    public abstract void c(String paramString);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upviews.a
 * JD-Core Version:    0.6.2
 */