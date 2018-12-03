package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class av
  implements View.OnClickListener
{
  av(as paramas)
  {
  }

  public final void onClick(View paramView)
  {
    String str = (String)paramView.getTag();
    as.b(this.a, str);
    as.f(this.a);
    as.b(this.a, str, "");
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.views.av
 * JD-Core Version:    0.6.2
 */