package com.tg.jiadeonline.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class DragImageView extends ImageView
{
  private int MAX_H;
  private int MAX_W;
  private int MIN_H;
  private int MIN_W;
  private float afterLenght;
  private float beforeLenght;
  private int bitmap_H;
  private int bitmap_W;
  private int current_Bottom;
  private int current_Left;
  private int current_Right;
  private int current_Top;
  private int current_x;
  private int current_y;
  private boolean isControl_H = false;
  private boolean isControl_V = false;
  private boolean isScaleAnim = false;
  private Activity mActivity;
  private MODE mode = MODE.NONE;
  private MyAsyncTask myAsyncTask;
  private ScaleAnimation scaleAnimation;
  private float scale_temp;
  private int screen_H;
  private int screen_W;
  private int start_Bottom = -1;
  private int start_Left = -1;
  private int start_Right = -1;
  private int start_Top = -1;
  private int start_x;
  private int start_y;

  public DragImageView(Context paramContext)
  {
    super(paramContext);
  }

  public DragImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    layout(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void doScaleAnim()
  {
    this.myAsyncTask = new MyAsyncTask(this.screen_W, getWidth(), getHeight());
    this.myAsyncTask.setLTRB(getLeft(), getTop(), getRight(), getBottom());
    this.myAsyncTask.execute(new Void[0]);
    this.isScaleAnim = false;
  }

  float getDistance(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
    float f2 = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
    return FloatMath.sqrt(f1 * f1 + f2 * f2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.start_Top == -1)
    {
      this.start_Top = paramInt2;
      this.start_Left = paramInt1;
      this.start_Bottom = paramInt4;
      this.start_Right = paramInt3;
    }
  }

  void onPointerDown(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getPointerCount() == 2)
    {
      this.mode = MODE.ZOOM;
      this.beforeLenght = getDistance(paramMotionEvent);
    }
  }

  void onTouchDown(MotionEvent paramMotionEvent)
  {
    this.mode = MODE.DRAG;
    this.current_x = ((int)paramMotionEvent.getRawX());
    this.current_y = ((int)paramMotionEvent.getRawY());
    this.start_x = ((int)paramMotionEvent.getX());
    this.start_y = (this.current_y - getTop());
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (0xFF & paramMotionEvent.getAction())
    {
    case 3:
    case 4:
    default:
    case 0:
    case 5:
    case 2:
    case 1:
    case 6:
    }
    while (true)
    {
      return true;
      onTouchDown(paramMotionEvent);
      continue;
      onPointerDown(paramMotionEvent);
      continue;
      onTouchMove(paramMotionEvent);
      continue;
      this.mode = MODE.NONE;
      continue;
      this.mode = MODE.NONE;
      if (this.isScaleAnim)
        doScaleAnim();
    }
  }

  void onTouchMove(MotionEvent paramMotionEvent)
  {
    int i;
    int j;
    int k;
    int m;
    if (this.mode == MODE.DRAG)
    {
      i = this.current_x - this.start_x;
      j = this.current_x + getWidth() - this.start_x;
      k = this.current_y - this.start_y;
      m = this.current_y - this.start_y + getHeight();
      if (this.isControl_H)
      {
        if (i >= 0)
        {
          i = 0;
          j = getWidth();
        }
        if (j <= this.screen_W)
        {
          i = this.screen_W - getWidth();
          j = this.screen_W;
        }
        if (!this.isControl_V)
          break label206;
        if (k >= 0)
        {
          k = 0;
          m = getHeight();
        }
        if (m <= this.screen_H)
        {
          k = this.screen_H - getHeight();
          m = this.screen_H;
        }
        label150: if ((this.isControl_H) || (this.isControl_V))
          setPosition(i, k, j, m);
        this.current_x = ((int)paramMotionEvent.getRawX());
        this.current_y = ((int)paramMotionEvent.getRawY());
      }
    }
    label206: 
    do
    {
      do
      {
        return;
        i = getLeft();
        j = getRight();
        break;
        k = getTop();
        m = getBottom();
        break label150;
      }
      while (this.mode != MODE.ZOOM);
      this.afterLenght = getDistance(paramMotionEvent);
    }
    while (Math.abs(this.afterLenght - this.beforeLenght) <= 5.0F);
    this.scale_temp = (this.afterLenght / this.beforeLenght);
    setScale(this.scale_temp);
    this.beforeLenght = this.afterLenght;
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    this.bitmap_W = paramBitmap.getWidth();
    this.bitmap_H = paramBitmap.getHeight();
    this.MAX_W = (3 * this.bitmap_W);
    this.MAX_H = (3 * this.bitmap_H);
    this.MIN_W = (this.bitmap_W / 2);
    this.MIN_H = (this.bitmap_H / 2);
  }

  void setScale(float paramFloat)
  {
    int i = (int)(getWidth() * Math.abs(1.0F - paramFloat)) / 4;
    int j = (int)(getHeight() * Math.abs(1.0F - paramFloat)) / 4;
    if ((paramFloat > 1.0F) && (getWidth() <= this.MAX_W))
    {
      this.current_Left = (getLeft() - i);
      this.current_Top = (getTop() - j);
      this.current_Right = (i + getRight());
      this.current_Bottom = (j + getBottom());
      setFrame(this.current_Left, this.current_Top, this.current_Right, this.current_Bottom);
      if ((this.current_Top <= 0) && (this.current_Bottom >= this.screen_H))
      {
        this.isControl_V = true;
        if ((this.current_Left > 0) || (this.current_Right < this.screen_W))
          break label165;
        this.isControl_H = true;
      }
    }
    label165: 
    while ((paramFloat >= 1.0F) || (getWidth() < this.MIN_W))
    {
      while (true)
      {
        return;
        this.isControl_V = false;
      }
      this.isControl_H = false;
      return;
    }
    this.current_Left = (i + getLeft());
    this.current_Top = (j + getTop());
    this.current_Right = (getRight() - i);
    this.current_Bottom = (getBottom() - j);
    if ((this.isControl_V) && (this.current_Top > 0))
    {
      this.current_Top = 0;
      this.current_Bottom = (getBottom() - j * 2);
      if (this.current_Bottom < this.screen_H)
      {
        this.current_Bottom = this.screen_H;
        this.isControl_V = false;
      }
    }
    if ((this.isControl_V) && (this.current_Bottom < this.screen_H))
    {
      this.current_Bottom = this.screen_H;
      this.current_Top = (getTop() + j * 2);
      if (this.current_Top > 0)
      {
        this.current_Top = 0;
        this.isControl_V = false;
      }
    }
    if ((this.isControl_H) && (this.current_Left >= 0))
    {
      this.current_Left = 0;
      this.current_Right = (getRight() - i * 2);
      if (this.current_Right <= this.screen_W)
      {
        this.current_Right = this.screen_W;
        this.isControl_H = false;
      }
    }
    if ((this.isControl_H) && (this.current_Right <= this.screen_W))
    {
      this.current_Right = this.screen_W;
      this.current_Left = (getLeft() + i * 2);
      if (this.current_Left >= 0)
      {
        this.current_Left = 0;
        this.isControl_H = false;
      }
    }
    if ((this.isControl_H) || (this.isControl_V))
    {
      setFrame(this.current_Left, this.current_Top, this.current_Right, this.current_Bottom);
      return;
    }
    setFrame(this.current_Left, this.current_Top, this.current_Right, this.current_Bottom);
    this.isScaleAnim = true;
  }

  public void setScreen_H(int paramInt)
  {
    this.screen_H = paramInt;
  }

  public void setScreen_W(int paramInt)
  {
    this.screen_W = paramInt;
  }

  public void setmActivity(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  private static enum MODE
  {
    static
    {
      DRAG = new MODE("DRAG", 1);
      ZOOM = new MODE("ZOOM", 2);
      MODE[] arrayOfMODE = new MODE[3];
      arrayOfMODE[0] = NONE;
      arrayOfMODE[1] = DRAG;
      arrayOfMODE[2] = ZOOM;
    }
  }

  class MyAsyncTask extends AsyncTask<Void, Integer, Void>
  {
    private float STEP = 8.0F;
    private int bottom;
    private int current_Height;
    private int current_Width;
    private int left;
    private int right;
    private float scale_WH;
    private int screen_W;
    private float step_H;
    private float step_V;
    private int top;

    public MyAsyncTask(int paramInt1, int paramInt2, int arg4)
    {
      this.screen_W = paramInt1;
      this.current_Width = paramInt2;
      int i;
      this.current_Height = i;
      this.scale_WH = (i / paramInt2);
      this.step_H = this.STEP;
      this.step_V = (this.scale_WH * this.STEP);
    }

    protected Void doInBackground(Void[] paramArrayOfVoid)
    {
      while (true)
      {
        if (this.current_Width > this.screen_W)
          return null;
        this.left = ((int)(this.left - this.step_H));
        this.top = ((int)(this.top - this.step_V));
        this.right = ((int)(this.right + this.step_H));
        this.bottom = ((int)(this.bottom + this.step_V));
        this.current_Width = ((int)(this.current_Width + 2.0F * this.step_H));
        this.left = Math.max(this.left, DragImageView.this.start_Left);
        this.top = Math.max(this.top, DragImageView.this.start_Top);
        this.right = Math.min(this.right, DragImageView.this.start_Right);
        this.bottom = Math.min(this.bottom, DragImageView.this.start_Bottom);
        Log.e("jj", "top=" + this.top + ",bottom=" + this.bottom + ",left=" + this.left + ",right=" + this.right);
        Integer[] arrayOfInteger = new Integer[4];
        arrayOfInteger[0] = Integer.valueOf(this.left);
        arrayOfInteger[1] = Integer.valueOf(this.top);
        arrayOfInteger[2] = Integer.valueOf(this.right);
        arrayOfInteger[3] = Integer.valueOf(this.bottom);
        onProgressUpdate(arrayOfInteger);
        try
        {
          Thread.sleep(10L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }

    protected void onProgressUpdate(final Integer[] paramArrayOfInteger)
    {
      super.onProgressUpdate(paramArrayOfInteger);
      DragImageView.this.mActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          DragImageView.this.setFrame(paramArrayOfInteger[0].intValue(), paramArrayOfInteger[1].intValue(), paramArrayOfInteger[2].intValue(), paramArrayOfInteger[3].intValue());
        }
      });
    }

    public void setLTRB(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.left = paramInt1;
      this.top = paramInt2;
      this.right = paramInt3;
      this.bottom = paramInt4;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.layout.DragImageView
 * JD-Core Version:    0.6.2
 */