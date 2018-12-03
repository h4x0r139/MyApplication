package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.unionpay.mobile.android.global.b;
import java.util.ArrayList;

public final class e extends LinearLayout
{
  private Context a;
  private c b;
  private ArrayList<AdapterView.OnItemClickListener> c = new ArrayList();
  private ArrayList<View.OnClickListener> d = new ArrayList();
  private AdapterView.OnItemClickListener e = new f(this);
  private View.OnClickListener f = new g(this);

  public e(Context paramContext, c paramc)
  {
    super(paramContext);
    this.a = paramContext;
    this.b = paramc;
    RelativeLayout localRelativeLayout = new RelativeLayout(this.a);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams1.addRule(12, -1);
    LinearLayout localLinearLayout1 = new LinearLayout(this.a);
    localLinearLayout1.setOrientation(1);
    localLinearLayout1.setBackgroundColor(-1);
    localLinearLayout1.setId(localLinearLayout1.hashCode());
    localRelativeLayout.addView(localLinearLayout1, localLayoutParams1);
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
    LinearLayout localLinearLayout2 = new LinearLayout(this.a);
    localLayoutParams2.addRule(10, -1);
    localLayoutParams2.addRule(2, localLinearLayout1.getId());
    localRelativeLayout.addView(localLinearLayout2, localLayoutParams2);
    localLinearLayout2.setOnClickListener(this.f);
    int i = com.unionpay.mobile.android.utils.c.a(this.a, 1.0F);
    LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(-1, -2);
    int j = b.a;
    localLayoutParams3.bottomMargin = j;
    localLayoutParams3.topMargin = j;
    LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(-1, i);
    LinearLayout localLinearLayout3 = new LinearLayout(this.a);
    localLinearLayout3.setBackgroundColor(-3355444);
    localLinearLayout1.addView(localLinearLayout3, localLayoutParams4);
    new LinearLayout.LayoutParams(-1, -2);
    ListView localListView = new ListView(this.a);
    localListView.setDivider(null);
    localListView.setAdapter(this.b);
    localListView.setOnItemClickListener(this.e);
    new LinearLayout.LayoutParams(-1, -2);
    localLinearLayout1.addView(localListView);
    addView(localRelativeLayout);
  }

  public final void a(View.OnClickListener paramOnClickListener)
  {
    this.d.add(paramOnClickListener);
  }

  public final void a(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.c.add(paramOnItemClickListener);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upwidget.e
 * JD-Core Version:    0.6.2
 */