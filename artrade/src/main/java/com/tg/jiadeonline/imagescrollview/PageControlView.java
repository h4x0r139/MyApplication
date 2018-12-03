package com.tg.jiadeonline.imagescrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class PageControlView extends LinearLayout
  implements ImageScrollView.ScrollToScreenCallback
{
  private Context context;
  private int count;

  public PageControlView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
  }

  public void callback(int paramInt)
  {
    generatePageControl(paramInt);
  }

  public void generatePageControl(int paramInt)
  {
    removeAllViews();
    int i = 0;
    if (i >= this.count)
      return;
    ImageView localImageView = new ImageView(this.context);
    if (paramInt == i)
      localImageView.setImageResource(2130837544);
    while (true)
    {
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(15, 15);
      localLayoutParams.leftMargin = 8;
      localLayoutParams.rightMargin = 8;
      localImageView.setLayoutParams(localLayoutParams);
      addView(localImageView);
      i++;
      break;
      localImageView.setImageResource(2130837545);
    }
  }

  public void setCount(int paramInt)
  {
    this.count = paramInt;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.imagescrollview.PageControlView
 * JD-Core Version:    0.6.2
 */