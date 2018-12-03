package com.tg.jiadeonline.layout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tg.jiadeonline.FAQToPayActivity;
import com.tg.jiadeonline.utils.Utils;

public class PayDialog
{
  private static PayDialog mInstance;
  public MyProgressDialog dialog;
  public MyProgressDialog dialogUpdate;
  private Activity mActivity;
  private View view;

  public static PayDialog getInstance()
  {
    if (mInstance == null)
      mInstance = new PayDialog();
    return mInstance;
  }

  private void initView(View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131427655);
    final TextView localTextView2 = (TextView)paramView.findViewById(2131427651);
    final TextView localTextView3 = (TextView)paramView.findViewById(2131427653);
    TextView localTextView4 = (TextView)paramView.findViewById(2131427396);
    final LinearLayout localLinearLayout1 = (LinearLayout)paramView.findViewById(2131427652);
    final LinearLayout localLinearLayout2 = (LinearLayout)paramView.findViewById(2131427650);
    ((TextView)paramView.findViewById(2131427654)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(PayDialog.this.mActivity, FAQToPayActivity.class);
        PayDialog.this.mActivity.startActivity(localIntent);
      }
    });
    localTextView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PayDialog.this.dismissDialog();
      }
    });
    localTextView4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PayDialog.this.dismissDialog();
      }
    });
    localTextView2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localLinearLayout1.setBackgroundColor(PayDialog.this.mActivity.getResources().getColor(2131034123));
        localLinearLayout2.setBackgroundColor(PayDialog.this.mActivity.getResources().getColor(2131034290));
        localTextView2.setTextColor(PayDialog.this.mActivity.getResources().getColor(2131034290));
        localTextView3.setTextColor(PayDialog.this.mActivity.getResources().getColor(2131034123));
      }
    });
    localTextView3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localLinearLayout1.setBackgroundColor(PayDialog.this.mActivity.getResources().getColor(2131034290));
        localLinearLayout2.setBackgroundColor(PayDialog.this.mActivity.getResources().getColor(2131034123));
        localTextView2.setTextColor(PayDialog.this.mActivity.getResources().getColor(2131034123));
        localTextView3.setTextColor(PayDialog.this.mActivity.getResources().getColor(2131034290));
      }
    });
  }

  public void dismissDialog()
  {
    if ((this.mActivity == null) || (this.mActivity.isFinishing()))
      return;
    try
    {
      if (this.dialog != null)
        this.dialog.dismiss();
      label32: if (this.dialogUpdate != null)
        this.dialogUpdate.dismiss();
      mInstance = null;
      return;
    }
    catch (Exception localException)
    {
      break label32;
    }
  }

  public void showDialog(Activity paramActivity, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramActivity == null);
    while (true)
    {
      return;
      try
      {
        this.mActivity = paramActivity.getParent();
        if (this.mActivity == null)
          this.mActivity = paramActivity;
        if ((this.mActivity != null) && (!this.mActivity.isFinishing()))
        {
          if (this.view == null)
          {
            this.view = LayoutInflater.from(this.mActivity).inflate(2130903098, null);
            initView(this.view);
            Utils.getScreenMetrics(this.mActivity);
            Window localWindow = this.dialog.getWindow();
            WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
            localWindow.setGravity(51);
            localLayoutParams.x = 0;
            localLayoutParams.y = 0;
            localLayoutParams.width = paramInt1;
            localLayoutParams.height = paramInt2;
            localWindow.setAttributes(localLayoutParams);
          }
          if (this.dialog == null)
          {
            this.dialog = new MyProgressDialog(this.mActivity);
            this.dialog.setCancelable(paramBoolean);
            this.dialog.setContentView(this.view);
            this.dialog.setCanceledOnTouchOutside(false);
          }
          if ((this.mActivity != null) && (!this.mActivity.isFinishing()))
          {
            this.dialog.show();
            return;
          }
        }
      }
      catch (Exception localException)
      {
      }
    }
  }

  private class MyProgressDialog extends Dialog
  {
    public MyProgressDialog(Context arg2)
    {
      super(2131230724);
    }

    public void cancel()
    {
      super.cancel();
    }

    public void dismiss()
    {
      if (isShowing());
      try
      {
        super.dismiss();
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.PayDialog
 * JD-Core Version:    0.6.2
 */