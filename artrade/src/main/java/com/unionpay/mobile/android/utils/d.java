package com.unionpay.mobile.android.utils;

import android.content.res.ColorStateList;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import java.util.Arrays;

public final class d
{
  private static final int[] a = { 16842910 };
  private static final int[] b = { 16842908 };
  private static final int[] c = { -16842910 };
  private static final int[] d = { 16842910, 16842919 };
  private static final int[] e = { 16842910, 16842908 };
  private static final int[] f = { 16842910, 16842912 };

  public static ColorStateList a(int paramInt1, int paramInt2)
  {
    return a(paramInt1, paramInt2, paramInt2, paramInt1);
  }

  public static ColorStateList a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int[] arrayOfInt = { paramInt2, paramInt3, paramInt1, paramInt3, paramInt4, paramInt4 };
    return new ColorStateList(new int[][] { { 16842919, 16842910 }, { 16842910, 16842908 }, { 16842910 }, { 16842908 }, { 16842909 }, new int[0] }, arrayOfInt);
  }

  public static Drawable a(int paramInt, int[] paramArrayOfInt, float[] paramArrayOfFloat, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape(a(paramInt, 18.0F), null, null));
    LinearGradient localLinearGradient = new LinearGradient(paramFloat1, paramFloat2, paramFloat3, paramFloat4, paramArrayOfInt, paramArrayOfFloat, Shader.TileMode.CLAMP);
    localShapeDrawable.getPaint().setShader(localLinearGradient);
    return localShapeDrawable;
  }

  public static Drawable a(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(d, paramDrawable2);
    localStateListDrawable.addState(e, paramDrawable2);
    if (paramDrawable4 != null)
      localStateListDrawable.addState(f, paramDrawable4);
    localStateListDrawable.addState(a, paramDrawable1);
    localStateListDrawable.addState(b, paramDrawable2);
    if (paramDrawable3 != null)
      localStateListDrawable.addState(c, paramDrawable3);
    return localStateListDrawable;
  }

  public static ShapeDrawable a(int paramInt1, int paramInt2, float paramFloat)
  {
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape(a(paramInt2, paramFloat), null, null));
    localShapeDrawable.getPaint().setStrokeWidth(1.0F);
    localShapeDrawable.getPaint().setColor(paramInt1);
    localShapeDrawable.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
    return localShapeDrawable;
  }

  private static float[] a(int paramInt, float paramFloat)
  {
    float[] arrayOfFloat = new float[8];
    Arrays.fill(arrayOfFloat, 0.0F);
    if (paramInt == 0);
    do
    {
      return arrayOfFloat;
      if ((paramInt & 0x1) != 0)
      {
        arrayOfFloat[1] = paramFloat;
        arrayOfFloat[0] = paramFloat;
      }
      if ((paramInt & 0x2) != 0)
      {
        arrayOfFloat[3] = paramFloat;
        arrayOfFloat[2] = paramFloat;
      }
      if ((paramInt & 0x4) != 0)
      {
        arrayOfFloat[5] = paramFloat;
        arrayOfFloat[4] = paramFloat;
      }
    }
    while ((paramInt & 0x8) == 0);
    arrayOfFloat[7] = paramFloat;
    arrayOfFloat[6] = paramFloat;
    return arrayOfFloat;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.utils.d
 * JD-Core Version:    0.6.2
 */