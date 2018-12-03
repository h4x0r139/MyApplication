package com.tg.jiadeonline.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XListView extends ListView
  implements AbsListView.OnScrollListener
{
  private static final float OFFSET_RADIO = 1.8F;
  private static final int PULL_LOAD_MORE_DELTA = 50;
  private static final int SCROLLBACK_FOOTER = 1;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 400;
  private boolean mEnablePullLoad;
  private boolean mEnablePullRefresh = true;
  private XListViewFooter mFooterView;
  private TextView mHeaderTimeView;
  private XListViewHeader mHeaderView;
  private RelativeLayout mHeaderViewContent;
  private int mHeaderViewHeight;
  private boolean mIsFooterReady = false;
  private float mLastY = -1.0F;
  private IXListViewListener mListViewListener;
  private boolean mPullLoading;
  private boolean mPullRefreshing = false;
  private int mScrollBack;
  private AbsListView.OnScrollListener mScrollListener;
  private Scroller mScroller;
  private int mTotalItemCount;
  private float xDistance;
  private float xLast;
  private float yDistance;
  private float yLast;

  public XListView(Context paramContext)
  {
    super(paramContext);
    initWithContext(paramContext);
  }

  public XListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initWithContext(paramContext);
  }

  public XListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initWithContext(paramContext);
  }

  private void initWithContext(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    super.setOnScrollListener(this);
    this.mHeaderView = new XListViewHeader(paramContext);
    this.mHeaderViewContent = ((RelativeLayout)this.mHeaderView.findViewById(2131427895));
    this.mHeaderTimeView = ((TextView)this.mHeaderView.findViewById(2131427898));
    addHeaderView(this.mHeaderView);
    this.mFooterView = new XListViewFooter(paramContext);
    this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        XListView.this.mHeaderViewHeight = XListView.this.mHeaderViewContent.getHeight();
        XListView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }

  private void invokeOnScrolling()
  {
    if ((this.mScrollListener instanceof OnXScrollListener))
      ((OnXScrollListener)this.mScrollListener).onXScrolling(this);
  }

  private void resetFooterHeight()
  {
    int i = this.mFooterView.getBottomMargin();
    if (i > 0)
    {
      this.mScrollBack = 1;
      this.mScroller.startScroll(0, i, 0, -i, 400);
      invalidate();
    }
  }

  private void resetHeaderHeight()
  {
    int i = this.mHeaderView.getVisiableHeight();
    if (i == 0);
    while ((this.mPullRefreshing) && (i <= this.mHeaderViewHeight))
      return;
    boolean bool = this.mPullRefreshing;
    int j = 0;
    if (bool)
    {
      int k = this.mHeaderViewHeight;
      j = 0;
      if (i > k)
        j = this.mHeaderViewHeight;
    }
    this.mScrollBack = 0;
    this.mScroller.startScroll(0, i, 0, j - i, 400);
    invalidate();
  }

  private void startLoadMore()
  {
    this.mPullLoading = true;
    this.mFooterView.setState(2);
    if (this.mListViewListener != null)
      this.mListViewListener.onLoadMore();
  }

  private void updateFooterHeight(float paramFloat)
  {
    int i = this.mFooterView.getBottomMargin() + (int)paramFloat;
    if ((this.mEnablePullLoad) && (!this.mPullLoading))
    {
      if (i <= 50)
        break label48;
      this.mFooterView.setState(1);
    }
    while (true)
    {
      this.mFooterView.setBottomMargin(i);
      return;
      label48: this.mFooterView.setState(0);
    }
  }

  private void updateHeaderHeight(float paramFloat)
  {
    this.mHeaderView.setVisiableHeight((int)paramFloat + this.mHeaderView.getVisiableHeight());
    if ((this.mEnablePullRefresh) && (!this.mPullRefreshing))
    {
      if (this.mHeaderView.getVisiableHeight() <= this.mHeaderViewHeight)
        break label59;
      this.mHeaderView.setState(1);
    }
    while (true)
    {
      setSelection(0);
      return;
      label59: this.mHeaderView.setState(0);
    }
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (this.mScrollBack != 0)
        break label44;
      this.mHeaderView.setVisiableHeight(this.mScroller.getCurrY());
    }
    while (true)
    {
      postInvalidate();
      invokeOnScrolling();
      super.computeScroll();
      return;
      label44: this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    do
    {
      while (true)
      {
        return super.onInterceptTouchEvent(paramMotionEvent);
        this.yDistance = 0.0F;
        this.xDistance = 0.0F;
        this.xLast = paramMotionEvent.getX();
        this.yLast = paramMotionEvent.getY();
      }
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.xDistance += Math.abs(f1 - this.xLast);
      this.yDistance += Math.abs(f2 - this.yLast);
      this.xLast = f1;
      this.yLast = f2;
    }
    while (this.xDistance <= this.yDistance);
    return false;
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mTotalItemCount = paramInt3;
    if (this.mScrollListener != null)
      this.mScrollListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (this.mScrollListener != null)
      this.mScrollListener.onScrollStateChanged(paramAbsListView, paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mLastY == -1.0F)
      this.mLastY = paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
      this.mLastY = -1.0F;
      if (getFirstVisiblePosition() == 0)
      {
        if ((this.mEnablePullRefresh) && (this.mHeaderView.getVisiableHeight() > this.mHeaderViewHeight))
        {
          this.mPullRefreshing = true;
          this.mHeaderView.setState(2);
          if (this.mListViewListener != null)
            this.mListViewListener.onRefresh();
        }
        resetHeaderHeight();
      }
      if (getLastVisiblePosition() == -1 + this.mTotalItemCount)
      {
        if ((this.mEnablePullLoad) && (this.mFooterView.getBottomMargin() > 50))
          startLoadMore();
        resetFooterHeight();
      }
      break;
    case 0:
    case 2:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      this.mLastY = paramMotionEvent.getRawY();
      continue;
      this.yDistance = 0.0F;
      this.xDistance = 0.0F;
      this.xLast = paramMotionEvent.getX();
      this.yLast = paramMotionEvent.getY();
      float f = paramMotionEvent.getRawY() - this.mLastY;
      this.mLastY = paramMotionEvent.getRawY();
      System.out.println("数据监测：" + getFirstVisiblePosition() + "---->" + getLastVisiblePosition());
      if ((getFirstVisiblePosition() == 0) && ((this.mHeaderView.getVisiableHeight() > 0) || (f > 0.0F)))
      {
        updateHeaderHeight(f / 1.8F);
        invokeOnScrolling();
      }
      else if ((getLastVisiblePosition() == -1 + this.mTotalItemCount) && ((this.mFooterView.getBottomMargin() > 0) || (f < 0.0F)))
      {
        updateFooterHeight(-f / 1.8F);
      }
    }
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (!this.mIsFooterReady)
    {
      this.mIsFooterReady = true;
      addFooterView(this.mFooterView);
    }
    super.setAdapter(paramListAdapter);
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.mScrollListener = paramOnScrollListener;
  }

  public void setPullLoadEnable(boolean paramBoolean)
  {
    this.mEnablePullLoad = paramBoolean;
    if (!this.mEnablePullLoad)
    {
      this.mFooterView.hide();
      this.mFooterView.setOnClickListener(null);
      return;
    }
    this.mPullLoading = false;
    this.mFooterView.show();
    this.mFooterView.setState(0);
    this.mFooterView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        XListView.this.startLoadMore();
      }
    });
  }

  public void setPullRefreshEnable(boolean paramBoolean)
  {
    this.mEnablePullRefresh = paramBoolean;
    if (!this.mEnablePullRefresh)
    {
      this.mHeaderViewContent.setVisibility(4);
      return;
    }
    this.mHeaderViewContent.setVisibility(0);
  }

  @SuppressLint({"SimpleDateFormat"})
  public void setRefreshTime(String paramString)
  {
    String str = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss     ").format(new Date(System.currentTimeMillis()));
    this.mHeaderTimeView.setText(str);
  }

  public void setXListViewListener(IXListViewListener paramIXListViewListener)
  {
    this.mListViewListener = paramIXListViewListener;
  }

  public void stopLoadMore()
  {
    if (this.mPullLoading)
    {
      this.mPullLoading = false;
      this.mFooterView.setState(0);
    }
  }

  public void stopRefresh()
  {
    if (this.mPullRefreshing)
    {
      this.mPullRefreshing = false;
      resetHeaderHeight();
    }
  }

  public static abstract interface IXListViewListener
  {
    public abstract void onLoadMore();

    public abstract void onRefresh();
  }

  public static abstract interface OnXScrollListener extends AbsListView.OnScrollListener
  {
    public abstract void onXScrolling(View paramView);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.XListView
 * JD-Core Version:    0.6.2
 */