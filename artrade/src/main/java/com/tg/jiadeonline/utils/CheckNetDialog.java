package com.tg.jiadeonline.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class CheckNetDialog
{
  private Context context;
  private PopupWindow popright = null;

  public void checkNet(Context paramContext)
  {
    this.context = paramContext;
    CheckNet.isNetworkAvailable(paramContext);
  }

  public final void popwindows()
  {
    View localView = LayoutInflater.from(this.context).inflate(2130903052, null);
    this.popright = new PopupWindow(localView, -1, -1, false);
    this.popright.setAnimationStyle(2131230730);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    ((ImageView)localView.findViewById(2131427396)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CheckNetDialog.this.popright.isShowing())
          CheckNetDialog.this.popright.dismiss();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.CheckNetDialog
 * JD-Core Version:    0.6.2
 */