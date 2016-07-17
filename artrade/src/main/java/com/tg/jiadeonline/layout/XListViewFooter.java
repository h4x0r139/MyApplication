package com.tg.jiadeonline.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class XListViewFooter extends LinearLayout
{
  public static final int STATE_LOADING = 2;
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  private View mContentView;
  private Context mContext;
  private TextView mHintView;
  private View mProgressBar;
  private ImageView xlistview_header_refresh;

  public XListViewFooter(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public XListViewFooter(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this.mContext).inflate(2130903143, null);
    addView(localLinearLayout);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.xlistview_header_refresh = ((ImageView)localLinearLayout.findViewById(2131427893));
    this.mContentView = localLinearLayout.findViewById(2131427891);
    this.mProgressBar = localLinearLayout.findViewById(2131427892);
    this.mHintView = ((TextView)localLinearLayout.findViewById(2131427894));
  }

  public int getBottomMargin()
  {
    return ((LinearLayout.LayoutParams)this.mContentView.getLayoutParams()).bottomMargin;
  }

  public void hide()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = 0;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void loading()
  {
    this.mHintView.setVisibility(8);
    this.xlistview_header_refresh.setVisibility(0);
    this.mProgressBar.setVisibility(8);
  }

  public void normal()
  {
    this.mHintView.setVisibility(0);
    this.xlistview_header_refresh.setVisibility(8);
    this.mProgressBar.setVisibility(8);
  }

  public void setBottomMargin(int paramInt)
  {
    if (paramInt < 0)
      return;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.bottomMargin = paramInt;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void setState(int paramInt)
  {
    this.mHintView.setVisibility(4);
    this.mProgressBar.setVisibility(4);
    this.mHintView.setVisibility(4);
    this.xlistview_header_refresh.setVisibility(4);
    if (paramInt == 1)
    {
      this.mHintView.setVisibility(0);
      this.mHintView.setText(2131165202);
      return;
    }
    if (paramInt == 2)
    {
      this.mProgressBar.setVisibility(8);
      this.xlistview_header_refresh.setVisibility(0);
      return;
    }
    this.mHintView.setVisibility(0);
    this.mHintView.setText(2131165201);
  }

  public void show()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = -2;
    this.mContentView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.XListViewFooter
 * JD-Core Version:    0.6.2
 */