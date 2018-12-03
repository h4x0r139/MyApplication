package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow;
import com.unionpay.mobile.android.upwidget.c;
import java.util.List;

final class ad
  implements AdapterView.OnItemClickListener
{
  ad(o.b paramb)
  {
  }

  public final void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    o.b.a(this.a).dismiss();
    if ((o.b.b(this.a) != null) && (paramInt - o.b.c(this.a).c() < o.b.b(this.a).size()))
    {
      o.b.a(this.a, paramInt);
      o.b.c(this.a).a(o.b.d(this.a));
    }
    if (o.b.e(this.a) != null)
      o.b.e(this.a).a(paramInt - o.b.c(this.a).c());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.ad
 * JD-Core Version:    0.6.2
 */