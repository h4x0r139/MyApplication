package com.tg.jiadeonline.layout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class TypeDialog
{
  private static TypeDialog mInstance;
  public MyProgressDialog dialog;
  public MyProgressDialog dialogUpdate;
  private Activity mActivity;
  private String typename;
  private View view;

  public static TypeDialog getInstance()
  {
    if (mInstance == null)
      mInstance = new TypeDialog();
    return mInstance;
  }

  private void initView(View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(2131427579);
    TextView localTextView2 = (TextView)paramView.findViewById(2131427583);
    TextView localTextView3 = (TextView)paramView.findViewById(2131427582);
    TextView localTextView4 = (TextView)paramView.findViewById(2131427581);
    TextView localTextView5 = (TextView)paramView.findViewById(2131427580);
    localTextView1.setOnClickListener(new TypeChance());
    localTextView2.setOnClickListener(new TypeChance());
    localTextView3.setOnClickListener(new TypeChance());
    localTextView4.setOnClickListener(new TypeChance());
    localTextView5.setOnClickListener(new TypeChance());
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

  public String getTypename()
  {
    return this.typename;
  }

  public void setTypename(String paramString)
  {
    this.typename = paramString;
  }

  public void showDialog(Activity paramActivity, boolean paramBoolean)
  {
    this.typename = null;
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
            this.view = LayoutInflater.from(this.mActivity).inflate(2130903089, null);
            initView(this.view);
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

  public class TypeChance
    implements View.OnClickListener
  {
    public TypeChance()
    {
    }

    public void onClick(View paramView)
    {
      TypeDialog.this.typename = ((TextView)paramView).getText().toString();
      TypeDialog.this.dismissDialog();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.TypeDialog
 * JD-Core Version:    0.6.2
 */