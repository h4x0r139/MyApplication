package com.unionpay.mobile.android.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.NinePatchDrawable;
import com.unionpay.mobile.android.utils.d;
import com.unionpay.mobile.android.utils.g;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public final class c
{
  private static c c = null;
  private HashMap<Integer, WeakReference<Drawable.ConstantState>> a = new HashMap();
  private Context b = null;

  private c(Context paramContext)
  {
    this.b = paramContext;
  }

  public static c a(Context paramContext)
  {
    if (c == null)
      c = new c(paramContext);
    return c;
  }

  public final Drawable a(int paramInt)
  {
    return a(paramInt, -1, -1);
  }

  public final Drawable a(int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject;
    if (paramInt1 < 0)
      localObject = null;
    while (true)
    {
      return localObject;
      WeakReference localWeakReference = (WeakReference)this.a.get(Integer.valueOf(paramInt1));
      if (localWeakReference != null)
      {
        Drawable.ConstantState localConstantState = (Drawable.ConstantState)localWeakReference.get();
        if (localConstantState != null)
          return localConstantState.newDrawable();
        this.a.remove(Integer.valueOf(paramInt1));
      }
      int i = 1000 * (paramInt1 / 1000);
      int j = paramInt1 - i;
      label119: InputStream localInputStream;
      DataInputStream localDataInputStream;
      int k;
      int m;
      label161: long l1;
      switch (i)
      {
      default:
        localObject = null;
        if (localObject == null)
        {
          localInputStream = a.class.getClassLoader().getResourceAsStream("assets/data.bin");
          localDataInputStream = new DataInputStream(localInputStream);
          k = paramInt1 - 1000;
          m = k * 8;
          l1 = m;
        }
        break;
      case 3000:
      case 2000:
      case 4000:
      }
      try
      {
        long l2 = localDataInputStream.skip(l1);
        if (l2 < m)
        {
          m = (int)(m - l2);
          break label161;
          int i4 = b.e[j];
          int[] arrayOfInt2 = b.d[j];
          float[] arrayOfFloat1 = b.b[j];
          float[] arrayOfFloat2 = b.c[j];
          localObject = d.a(i4, arrayOfInt2, arrayOfFloat1, arrayOfFloat2[0], arrayOfFloat2[1], arrayOfFloat2[2], arrayOfFloat2[3]);
          break label119;
          int[] arrayOfInt1 = b.a[j];
          localObject = d.a(a(arrayOfInt1[0], paramInt2, paramInt3), a(arrayOfInt1[1], paramInt2, paramInt3), a(arrayOfInt1[2], paramInt2, paramInt3), a(arrayOfInt1[3], paramInt2, paramInt3));
          break label119;
          localObject = d.a(b.f[j], b.g[j], b.h[j]);
          break label119;
        }
        int n = localDataInputStream.readInt();
        int i1 = localDataInputStream.readInt();
        long l3;
        for (int i2 = n - (8 + k * 8); ; i2 = (int)(i2 - l3))
        {
          l3 = localDataInputStream.skip(i2);
          if (l3 >= i2)
            break;
        }
        localDataInputStream.mark(i1);
        localBitmap1 = BitmapFactory.decodeStream(localDataInputStream);
        localRect = new Rect();
        if (localBitmap1.getNinePatchChunk() == null)
        {
          Bitmap localBitmap4;
          if ((-1 != paramInt3) && (-1 != paramInt2))
          {
            localBitmap4 = Bitmap.createScaledBitmap(localBitmap1, paramInt2, paramInt3, true);
            if (localBitmap4 != localBitmap1)
              localBitmap1.recycle();
          }
          Bitmap localBitmap3;
          for (localObject = new BitmapDrawable(this.b.getResources(), localBitmap4); ; localObject = new BitmapDrawable(this.b.getResources(), localBitmap3))
          {
            localDataInputStream.close();
            localInputStream.close();
            if (localObject == null)
              break;
            this.a.put(Integer.valueOf(paramInt1), new WeakReference(((Drawable)localObject).getConstantState()));
            return localObject;
            if ((-1 == paramInt3) || (-1 != paramInt2))
              break label639;
            int i3 = (int)(localBitmap1.getWidth() / localBitmap1.getHeight() * paramInt3);
            g.a("img", "w=" + i3 + ",h=" + paramInt3);
            localBitmap3 = Bitmap.createScaledBitmap(localBitmap1, i3, paramInt3, true);
            if (localBitmap3 != localBitmap1)
              localBitmap1.recycle();
          }
        }
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          Bitmap localBitmap1;
          Rect localRect;
          localIOException.printStackTrace();
          localObject = null;
          continue;
          label639: if ((-1 != paramInt2) && (-1 == paramInt3))
          {
            Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, paramInt2, (int)(localBitmap1.getHeight() / localBitmap1.getWidth() * paramInt2), true);
            if (localBitmap2 != localBitmap1)
              localBitmap1.recycle();
            localObject = new BitmapDrawable(this.b.getResources(), localBitmap2);
          }
          else
          {
            localObject = new BitmapDrawable(this.b.getResources(), localBitmap1);
            continue;
            localObject = new NinePatchDrawable(this.b.getResources(), localBitmap1, localBitmap1.getNinePatchChunk(), localRect, null);
          }
        }
      }
    }
  }

  public final void a()
  {
    Iterator localIterator = this.a.values().iterator();
    while (localIterator.hasNext())
    {
      Drawable.ConstantState localConstantState = (Drawable.ConstantState)((WeakReference)localIterator.next()).get();
      if (localConstantState != null)
      {
        Drawable localDrawable = localConstantState.newDrawable();
        if ((localDrawable instanceof BitmapDrawable))
          ((BitmapDrawable)localDrawable).getBitmap().recycle();
      }
    }
    this.a.clear();
    c = null;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.resource.c
 * JD-Core Version:    0.6.2
 */