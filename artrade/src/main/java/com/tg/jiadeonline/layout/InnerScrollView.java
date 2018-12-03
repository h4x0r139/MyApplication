package com.tg.jiadeonline.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class InnerScrollView extends ScrollView
{
  int currentY;
  private int lastScrollDelta = 0;
  int mTop = 10;
  public ScrollView parentScrollView;

  public InnerScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private int getScrollRange()
  {
    int i = getChildCount();
    int j = 0;
    if (i > 0)
      j = Math.max(0, getChildAt(0).getHeight() - getHeight());
    return j;
  }

  private void setParentScrollAble(boolean paramBoolean)
  {
    ScrollView localScrollView = this.parentScrollView;
    if (paramBoolean);
    for (boolean bool = false; ; bool = true)
    {
      localScrollView.requestDisallowInterceptTouchEvent(bool);
      return;
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.parentScrollView == null)
      return super.onInterceptTouchEvent(paramMotionEvent);
    if (paramMotionEvent.getAction() == 0)
    {
      this.currentY = ((int)paramMotionEvent.getY());
      setParentScrollAble(false);
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    if (paramMotionEvent.getAction() == 1)
      setParentScrollAble(true);
    while (true)
    {
      return super.onInterceptTouchEvent(paramMotionEvent);
      paramMotionEvent.getAction();
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    View localView = getChildAt(0);
    int i;
    int j;
    int k;
    if ((this.parentScrollView != null) && (paramMotionEvent.getAction() == 2))
    {
      i = localView.getMeasuredHeight() - getMeasuredHeight();
      j = getScrollY();
      k = (int)paramMotionEvent.getY();
      if (this.currentY >= k)
        break label82;
      if (j <= 0)
      {
        setParentScrollAble(true);
        return false;
      }
      setParentScrollAble(false);
    }
    while (true)
    {
      this.currentY = k;
      return super.onTouchEvent(paramMotionEvent);
      label82: if (this.currentY > k)
      {
        if (j >= i)
        {
          setParentScrollAble(true);
          return false;
        }
        setParentScrollAble(false);
      }
    }
  }

  public void resume()
  {
    overScrollBy(0, -this.lastScrollDelta, 0, getScrollY(), 0, getScrollRange(), 0, 0, true);
    this.lastScrollDelta = 0;
  }

  public void scrollTo(View paramView)
  {
    int i = getScrollY();
    int j = paramView.getTop() - this.mTop - i;
    this.lastScrollDelta = j;
    overScrollBy(0, j, 0, getScrollY(), 0, getScrollRange(), 0, 0, true);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.InnerScrollView
 * JD-Core Version:    0.6.2
 */