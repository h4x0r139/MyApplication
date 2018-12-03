package com.tg.jiadeonline.imagescrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tg.jiadeonline.bean.HomeTopPicBean;
import java.util.List;

public class ScrollImage extends RelativeLayout
{
  private ImageScrollView imageScrollView = null;
  private PageControlView pageControlView = null;

  public ScrollImage(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(2130903134, this);
    this.imageScrollView = ((ImageScrollView)findViewById(2131427886));
    this.pageControlView = ((PageControlView)findViewById(2131427887));
  }

  public int getPosition()
  {
    return this.imageScrollView.getCurrentScreenIndex();
  }

  public void setBitmapList(List<HomeTopPicBean> paramList, ImageLoader paramImageLoader)
  {
    int i = paramList.size();
    this.imageScrollView.removeAllViews();
    int j = 0;
    if (j >= i)
    {
      this.pageControlView.setCount(this.imageScrollView.getChildCount());
      this.pageControlView.generatePageControl(0);
      this.imageScrollView.setScrollToScreenCallback(this.pageControlView);
      return;
    }
    ImageView localImageView = new ImageView(getContext());
    localImageView.setScaleType(ImageView.ScaleType.FIT_XY);
    HomeTopPicBean localHomeTopPicBean = (HomeTopPicBean)paramList.get(j);
    if (localHomeTopPicBean == null)
      localImageView.setImageResource(2130837541);
    while (true)
    {
      this.imageScrollView.addView(localImageView);
      j++;
      break;
      localHomeTopPicBean.getCatPicpath();
      localImageView.setImageResource(localHomeTopPicBean.getPicid());
    }
  }

  public void setClickListener(View.OnClickListener paramOnClickListener)
  {
    this.imageScrollView.setClickListener(paramOnClickListener);
  }

  public void setHeight(int paramInt)
  {
    getLayoutParams().height = paramInt;
    this.imageScrollView.getLayoutParams().height = paramInt;
  }

  public void setWidth(int paramInt)
  {
    getLayoutParams().width = paramInt;
    this.imageScrollView.getLayoutParams().width = paramInt;
  }

  public void start(int paramInt)
  {
    this.imageScrollView.start(paramInt);
  }

  public void stop()
  {
    this.imageScrollView.stop();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.imagescrollview.ScrollImage
 * JD-Core Version:    0.6.2
 */