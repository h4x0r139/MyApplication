package com.tg.jiadeonline.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tg.jiadeonline.R.styleable;

public class SlidingTextView extends LinearLayout
{
  Runnable appear = new Runnable()
  {
    public void run()
    {
      int i = SlidingTextView.this.textWidth;
      int j = SlidingTextView.this.mDuration * Math.abs(0 - i) / SlidingTextView.this.textWidth;
      TranslateAnimation localTranslateAnimation = new TranslateAnimation(0, 0, i, 0);
      localTranslateAnimation.setDuration(j);
      localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymous2Animation)
        {
          SlidingTextView.this.mHandler.postDelayed(SlidingTextView.this.hide, 2500L);
        }

        public void onAnimationRepeat(Animation paramAnonymous2Animation)
        {
        }

        public void onAnimationStart(Animation paramAnonymous2Animation)
        {
          if (SlidingTextView.this.showTexts.length != 0)
          {
            SlidingTextView.this.count = ((1 + SlidingTextView.this.count) % SlidingTextView.this.showTexts.length);
            SlidingTextView.this.text.setText(SlidingTextView.this.showTexts[SlidingTextView.this.count]);
          }
          SlidingTextView.this.text.setVisibility(0);
        }
      });
      SlidingTextView.this.startAnimation(localTranslateAnimation);
    }
  };
  private int count = 0;
  Runnable hide = new Runnable()
  {
    public void run()
    {
      int i = -1 * SlidingTextView.this.textWidth;
      int j = SlidingTextView.this.mDuration * Math.abs(i - 0) / SlidingTextView.this.textWidth;
      TranslateAnimation localTranslateAnimation = new TranslateAnimation(0, 0, 0, i);
      localTranslateAnimation.setDuration(j);
      localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymous2Animation)
        {
          SlidingTextView.this.mHandler.postDelayed(SlidingTextView.this.appear, 500L);
          SlidingTextView.this.text.setVisibility(4);
        }

        public void onAnimationRepeat(Animation paramAnonymous2Animation)
        {
        }

        public void onAnimationStart(Animation paramAnonymous2Animation)
        {
        }
      });
      SlidingTextView.this.startAnimation(localTranslateAnimation);
    }
  };
  private int mDuration;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.arg1)
      {
      default:
        return;
      case 1:
        SlidingTextView.this.doAnimationOpen();
        return;
      case 2:
      }
      SlidingTextView.this.doAnimationClose();
    }
  };
  private String[] showTexts = { " ", "  " };
  private TextView text;
  private int textWidth = 200;

  public SlidingTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mDuration = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SlidingText).getInteger(0, 750);
  }

  public void doAnimationClose()
  {
    post(this.hide);
  }

  public void doAnimationOpen()
  {
    post(this.appear);
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.text = ((TextView)getChildAt(0));
    this.mHandler.postDelayed(this.appear, 1000L);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.textWidth = this.text.getHeight();
  }

  public void setShowText(String[] paramArrayOfString)
  {
    this.showTexts = paramArrayOfString;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.SlidingTextView
 * JD-Core Version:    0.6.2
 */