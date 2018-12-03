package com.tg.jiadeonline.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class WaitLoadDialog
{
  public static final int GUIDER_ACTION = 1;
  public static final int GUIDER_CINEMALIST = 4;
  public static final int GUIDER_FILMINFO = 3;
  public static final int GUIDER_FILMMAIN = 2;
  public static final int GUIDER_MYCENTER = 5;
  public static final int GUIDER_SCREENINGPLAN = 9;
  public static final int GUIDER_SEAT = 7;
  public static final int GUIDER_SUBMITORDER = 6;
  public static final int GUIDER_VSCODETYPE = 8;
  private static WaitLoadDialog mInstance;
  public MyProgressDialog dialog;
  public MyProgressDialog dialogUpdate;
  private Activity mActivity;
  private View view;

  public static WaitLoadDialog getInstance()
  {
    if (mInstance == null)
      mInstance = new WaitLoadDialog();
    return mInstance;
  }

  private void initView(View paramView)
  {
    int i = Utils.getScreenMetrics(this.mActivity)[0];
    ((LinearLayout)paramView.findViewById(2131427889)).setLayoutParams(new LinearLayout.LayoutParams(i / 2, i / 4));
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

  public void showDialog(Activity paramActivity, AsyncTask paramAsyncTask, boolean paramBoolean)
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
            this.view = LayoutInflater.from(this.mActivity).inflate(2130903141, null);
            initView(this.view);
          }
          if (this.dialog == null)
          {
            this.dialog = new MyProgressDialog(this.mActivity);
            this.dialog.setCancelable(paramBoolean);
            this.dialog.setContentView(this.view);
            this.dialog.setCanceledOnTouchOutside(false);
          }
          if (paramAsyncTask != null)
            this.dialog.setTask(paramAsyncTask);
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
    AsyncTask task;

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
        label11: if ((this.task != null) && (this.task.cancel(true)))
          this.task.cancel(true);
        return;
      }
      catch (Exception localException)
      {
        break label11;
      }
    }

    public void setTask(AsyncTask paramAsyncTask)
    {
      this.task = paramAsyncTask;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.WaitLoadDialog
 * JD-Core Version:    0.6.2
 */