package com.tg.jiadeonline.imagescrollview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import android.widget.TextView;

public class ImageScrollView extends ViewGroup
{
  View.OnClickListener clickListener;
  private int currentScreenIndex = 0;
  private boolean fling = false;
  private GestureDetector gestureDetector = null;
  Handler handler;
  private TextView name;
  Runnable next = new Runnable()
  {
    public void run()
    {
      ImageScrollView.this.scrollToScreen((1 + ImageScrollView.this.currentScreenIndex) % ImageScrollView.this.getChildCount());
      ImageScrollView.this.handler.postDelayed(ImageScrollView.this.next, ImageScrollView.this.time);
    }
  };
  private TextView price;
  private ScrollToScreenCallback scrollToScreenCallback;
  private Scroller scroller = null;
  int time;

  public ImageScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(final Context paramContext)
  {
    this.scroller = new Scroller(paramContext);
    this.handler = new Handler();
    this.gestureDetector = new GestureDetector(new GestureDetector.OnGestureListener()
    {
      public boolean onDown(MotionEvent paramAnonymousMotionEvent)
      {
        return false;
      }

      public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (Math.abs(paramAnonymousFloat1) > ViewConfiguration.get(paramContext).getScaledMinimumFlingVelocity())
        {
          if ((paramAnonymousFloat1 > 0.0F) && (ImageScrollView.this.currentScreenIndex > 0))
          {
            ImageScrollView.this.fling = true;
            ImageScrollView.this.scrollToScreen(-1 + ImageScrollView.this.currentScreenIndex);
          }
          while ((paramAnonymousFloat1 >= 0.0F) || (ImageScrollView.this.currentScreenIndex >= -1 + ImageScrollView.this.getChildCount()))
            return true;
          ImageScrollView.this.fling = true;
          ImageScrollView.this.scrollToScreen(1 + ImageScrollView.this.currentScreenIndex);
          return true;
        }
        ImageScrollView.this.fling = true;
        ImageScrollView.this.scrollToScreen(ImageScrollView.this.currentScreenIndex);
        return true;
      }

      public void onLongPress(MotionEvent paramAnonymousMotionEvent)
      {
      }

      public boolean onScroll(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (((paramAnonymousFloat1 > 0.0F) && (ImageScrollView.this.getScrollX() < ImageScrollView.this.getWidth() * (-1 + ImageScrollView.this.getChildCount()))) || ((paramAnonymousFloat1 < 0.0F) && (ImageScrollView.this.getScrollX() > 0)))
          ImageScrollView.this.scrollBy((int)paramAnonymousFloat1, 0);
        return true;
      }

      public void onShowPress(MotionEvent paramAnonymousMotionEvent)
      {
      }

      public boolean onSingleTapUp(MotionEvent paramAnonymousMotionEvent)
      {
        if (ImageScrollView.this.clickListener != null)
          ImageScrollView.this.clickListener.onClick(ImageScrollView.this);
        return false;
      }
    });
  }

  private void snapToDestination()
  {
    scrollToScreen((getScrollX() + getWidth() / 2) / getWidth());
  }

  public void computeScroll()
  {
    if (this.scroller.computeScrollOffset())
    {
      scrollTo(this.scroller.getCurrX(), 0);
      postInvalidate();
    }
  }

  public int getCurrentScreenIndex()
  {
    return this.currentScreenIndex;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    for (int i = 0; ; i++)
    {
      if (i >= getChildCount())
        return;
      View localView = getChildAt(i);
      localView.setVisibility(0);
      localView.measure(paramInt3 - paramInt1, paramInt4 - paramInt2);
      localView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.gestureDetector.onTouchEvent(paramMotionEvent);
    if (paramMotionEvent.getAction() == 1)
    {
      this.handler.removeCallbacks(this.next);
      if (this.time >= 500)
        this.handler.postDelayed(this.next, this.time);
      if (!this.fling)
        snapToDestination();
      this.fling = false;
    }
    return true;
  }

  public void scrollToScreen(int paramInt)
  {
    if ((paramInt != this.currentScreenIndex) && (getFocusedChild() != null) && (getFocusedChild() == getChildAt(this.currentScreenIndex)))
      getFocusedChild().clearFocus();
    int i = paramInt * getWidth() - getScrollX();
    int j = Math.abs(i);
    int k = getWidth() * (-1 + getChildCount()) - getWidth() / 2;
    int m = 0;
    if (j < k)
      m = 2 * Math.abs(i);
    this.scroller.startScroll(getScrollX(), 0, i, 0, m);
    invalidate();
    this.currentScreenIndex = paramInt;
    if (this.scrollToScreenCallback != null)
      this.scrollToScreenCallback.callback(this.currentScreenIndex);
  }

  public void setClickListener(View.OnClickListener paramOnClickListener)
  {
    this.clickListener = paramOnClickListener;
  }

  public void setScrollToScreenCallback(ScrollToScreenCallback paramScrollToScreenCallback)
  {
    this.scrollToScreenCallback = paramScrollToScreenCallback;
  }

  public void start(int paramInt)
  {
    if (paramInt > 500);
    for (this.time = paramInt; ; this.time = 500)
    {
      this.handler.postDelayed(this.next, paramInt);
      return;
    }
  }

  public void stop()
  {
    this.handler.removeCallbacks(this.next);
  }

  static abstract interface ScrollToScreenCallback
  {
    public abstract void callback(int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.imagescrollview.ImageScrollView
 * JD-Core Version:    0.6.2
 */