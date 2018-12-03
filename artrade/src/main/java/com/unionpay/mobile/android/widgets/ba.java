package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ba
{
  private static int a = a.r;
  private static int b = 40;
  private static List<Integer> i = new ArrayList(10);
  private Context c = null;
  private View.OnClickListener d = null;
  private PopupWindow e = null;
  private View f = null;
  private ScrollView g = null;
  private int h = -1;
  private PopupWindow.OnDismissListener j = new bb(this);

  static
  {
    for (int k = 0; k < 10; k++)
      i.add(Integer.valueOf(k));
  }

  public ba(Context paramContext, View.OnClickListener paramOnClickListener, View paramView)
  {
    this.c = paramContext;
    this.d = paramOnClickListener;
    a = com.unionpay.mobile.android.utils.c.a(this.c, 55.0F);
    b = com.unionpay.mobile.android.utils.c.a(this.c, 40.0F);
    for (ViewParent localViewParent = (ViewParent)paramView; ; localViewParent = localViewParent.getParent())
      if (localViewParent != null)
      {
        if ((localViewParent instanceof ScrollView))
        {
          this.g = ((ScrollView)localViewParent);
          g.a("UPWidgetKeyBoard", "mSV : " + this.g.toString());
          g.a("UPWidgetKeyBoard", "mSV H:" + this.g.getHeight());
          this.f = ((ScrollView)localViewParent).getChildAt(0);
        }
      }
      else
      {
        RelativeLayout localRelativeLayout1 = new RelativeLayout(paramContext);
        new RelativeLayout.LayoutParams(-1, -2).setMargins(0, 0, 0, 0);
        localRelativeLayout1.setBackgroundColor(-1342177280);
        RelativeLayout localRelativeLayout2 = new RelativeLayout(paramContext);
        localRelativeLayout2.setBackgroundColor(-13290188);
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
        localLayoutParams.setMargins(0, 0, 0, 0);
        localRelativeLayout1.addView(localRelativeLayout2, localLayoutParams);
        localRelativeLayout2.addView(new b(this.c), localLayoutParams);
        this.e = new PopupWindow(localRelativeLayout1, -1, -2, true);
        new RelativeLayout.LayoutParams(-1, -2);
        this.e.setBackgroundDrawable(new BitmapDrawable());
        this.e.setOutsideTouchable(false);
        this.e.setFocusable(false);
        this.e.setOnDismissListener(this.j);
        return;
      }
  }

  private static int d()
  {
    int k = 4 * a + b;
    g.c("UPWidgetKeyBoard", "kbH=" + k);
    return k;
  }

  public final void a()
  {
    if (this.e != null)
      this.e.dismiss();
  }

  public final void a(View paramView)
  {
    if (this.e != null)
    {
      this.e.showAtLocation(paramView, 80, 0, 0);
      this.e.update();
      if (this.f != null)
      {
        paramView.setVisibility(0);
        ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)this.g.getLayoutParams();
        this.h = localMarginLayoutParams.height;
        Rect localRect = new Rect();
        paramView.getWindowVisibleDisplayFrame(localRect);
        localMarginLayoutParams.height = (a.t - localRect.top - a.k - d());
        g.a("UPWidgetKeyBoard", "height = " + localMarginLayoutParams.height);
        localMarginLayoutParams.bottomMargin = d();
        this.g.setLayoutParams(localMarginLayoutParams);
      }
    }
  }

  public final boolean b()
  {
    return this.e.isShowing();
  }

  private final class a extends BaseAdapter
  {
    private a()
    {
    }

    public final int getCount()
    {
      return 2 + ba.c().size();
    }

    public final Object getItem(int paramInt)
    {
      return null;
    }

    public final long getItemId(int paramInt)
    {
      return paramInt;
    }

    public final View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int i = 10;
      LinearLayout localLinearLayout = new LinearLayout(ba.c(ba.this));
      Drawable localDrawable1 = com.unionpay.mobile.android.resource.c.a(ba.c(ba.this)).a(1022);
      Drawable localDrawable2 = com.unionpay.mobile.android.resource.c.a(ba.c(ba.this)).a(1023);
      localLinearLayout.setBackgroundDrawable(d.a(localDrawable1, localDrawable2, localDrawable2, localDrawable1));
      localLinearLayout.setMinimumHeight(com.unionpay.mobile.android.utils.c.a(ba.c(ba.this), 55.0F));
      localLinearLayout.setClickable(true);
      localLinearLayout.setOnClickListener(ba.d(ba.this));
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      localLayoutParams.gravity = 17;
      if ((paramInt == 9) || (paramInt == 11))
      {
        ImageView localImageView = new ImageView(ba.c(ba.this));
        if (paramInt == 9)
          if (paramInt != 9)
            break label229;
        label229: for (int j = 1024; ; j = 1025)
        {
          localImageView.setImageDrawable(com.unionpay.mobile.android.resource.c.a(ba.c(ba.this)).a(j, -1, com.unionpay.mobile.android.utils.c.a(ba.c(ba.this), 20.0F)));
          localLinearLayout.setId(i);
          localLinearLayout.addView(localImageView, localLayoutParams);
          return localLinearLayout;
          i = 20;
          break;
        }
      }
      TextView localTextView = new TextView(ba.c(ba.this));
      localTextView.setTextColor(-1);
      localTextView.getPaint().setFakeBoldText(true);
      localTextView.setTextSize(30.0F);
      localTextView.setGravity(17);
      if (paramInt == i)
        paramInt = 9;
      int k = ((Integer)ba.c().get(paramInt)).intValue();
      localLinearLayout.setId(k);
      localTextView.setText(k);
      localLinearLayout.addView(localTextView, localLayoutParams);
      return localLinearLayout;
    }
  }

  private final class b extends LinearLayout
  {
    public b(Context arg2)
    {
      super();
      setOrientation(1);
      setBackgroundColor(-11316397);
      LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams1.gravity = 17;
      LinearLayout localLinearLayout = new LinearLayout(localContext);
      int i = com.unionpay.mobile.android.utils.c.a(localContext, 5.0F);
      localLinearLayout.setPadding(0, i, 0, i);
      localLinearLayout.setGravity(17);
      localLinearLayout.setBackgroundColor(-13816531);
      localLinearLayout.setOrientation(0);
      int j = com.unionpay.mobile.android.utils.c.a(localContext, 24.0F);
      Drawable localDrawable = com.unionpay.mobile.android.resource.c.a(localContext).a(1020, -1, j);
      ImageView localImageView = new ImageView(localContext);
      localImageView.setImageDrawable(localDrawable);
      localLinearLayout.addView(localImageView);
      addView(localLinearLayout, localLayoutParams1);
      Collections.shuffle(ba.c());
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams2.gravity = 17;
      GridView localGridView = new GridView(localContext);
      localGridView.setNumColumns(3);
      localGridView.setAdapter(new ba.a(ba.this, (byte)0));
      localGridView.setGravity(17);
      localGridView.setStretchMode(2);
      localGridView.setHorizontalScrollBarEnabled(false);
      localGridView.setVerticalScrollBarEnabled(false);
      localGridView.setEnabled(true);
      int k = com.unionpay.mobile.android.utils.c.a(ba.c(ba.this), 1.0F);
      localGridView.setHorizontalSpacing(k);
      localGridView.setVerticalSpacing(k);
      int m = -k;
      localGridView.setPadding(m, k, m, m);
      addView(localGridView, localLayoutParams2);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.ba
 * JD-Core Version:    0.6.2
 */