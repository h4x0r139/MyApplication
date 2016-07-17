package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public final class o extends View
{
  private Paint a = new Paint();
  private int b;

  public o(Context paramContext)
  {
    this(paramContext, -7829368, 0);
  }

  public o(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext);
    this.a.setStyle(Paint.Style.STROKE);
    this.a.setStrokeWidth(4.0F);
    this.a.setColor(paramInt1);
    this.a.setPathEffect(null);
    this.b = paramInt2;
  }

  protected final void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.b == 0)
    {
      f2 = getHeight() >> 1;
      paramCanvas.drawLine(0.0F, f2, getWidth(), f2, this.a);
    }
    while (1 != this.b)
    {
      float f2;
      return;
    }
    float f1 = getWidth() >> 1;
    paramCanvas.drawLine(f1, 0.0F, f1, getHeight(), this.a);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.o
 * JD-Core Version:    0.6.2
 */