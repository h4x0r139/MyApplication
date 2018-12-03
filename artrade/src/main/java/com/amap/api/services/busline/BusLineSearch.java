package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import java.util.ArrayList;

public class BusLineSearch
{
  Handler a = new b(this);
  private Context b;
  private OnBusLineSearchListener c;
  private BusLineQuery d;
  private BusLineQuery e;
  private int f;
  private ArrayList<BusLineResult> g;

  public BusLineSearch(Context paramContext, BusLineQuery paramBusLineQuery)
  {
    com.amap.api.services.core.b.a(paramContext);
    this.b = paramContext.getApplicationContext();
    this.d = paramBusLineQuery;
    this.e = paramBusLineQuery.clone();
  }

  private void a(BusLineResult paramBusLineResult)
  {
    this.g = new ArrayList();
    for (int i = 0; i < this.f; i++)
      this.g.add(null);
    if ((this.f >= 0) && (a(this.d.getPageNumber())))
      this.g.set(this.d.getPageNumber(), paramBusLineResult);
  }

  private boolean a(int paramInt)
  {
    return (paramInt < this.f) && (paramInt >= 0);
  }

  private BusLineResult b(int paramInt)
  {
    if (!a(paramInt))
      throw new IllegalArgumentException("page out of range");
    return (BusLineResult)this.g.get(paramInt);
  }

  public BusLineQuery getQuery()
  {
    return this.d;
  }

  public BusLineResult searchBusLine()
    throws AMapException
  {
    if (!this.d.weakEquals(this.e))
    {
      this.e = this.d.clone();
      this.f = 0;
      if (this.g != null)
        this.g.clear();
    }
    BusLineResult localBusLineResult1;
    if (this.f == 0)
    {
      c localc1 = new c(this.d.clone(), com.amap.api.services.core.c.a(this.b));
      localBusLineResult1 = BusLineResult.a(localc1, (ArrayList)localc1.i());
      this.f = localBusLineResult1.getPageCount();
      a(localBusLineResult1);
    }
    do
    {
      return localBusLineResult1;
      localBusLineResult1 = b(this.d.getPageNumber());
    }
    while (localBusLineResult1 != null);
    c localc2 = new c(this.d, com.amap.api.services.core.c.a(this.b));
    BusLineResult localBusLineResult2 = BusLineResult.a(localc2, (ArrayList)localc2.i());
    this.g.set(this.d.getPageNumber(), localBusLineResult2);
    return localBusLineResult2;
  }

  public void searchBusLineAsyn()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Message localMessage = new Message();
        try
        {
          BusLineResult localBusLineResult = BusLineSearch.this.searchBusLine();
          localMessage.what = 0;
          localMessage.obj = localBusLineResult;
          return;
        }
        catch (AMapException localAMapException)
        {
          localMessage.what = localAMapException.getErrorCode();
          return;
        }
        finally
        {
          BusLineSearch.this.a.sendMessage(localMessage);
        }
      }
    }).start();
  }

  public void setOnBusLineSearchListener(OnBusLineSearchListener paramOnBusLineSearchListener)
  {
    this.c = paramOnBusLineSearchListener;
  }

  public void setQuery(BusLineQuery paramBusLineQuery)
  {
    if (!this.d.weakEquals(paramBusLineQuery))
    {
      this.d = paramBusLineQuery;
      this.e = paramBusLineQuery.clone();
    }
  }

  public static abstract interface OnBusLineSearchListener
  {
    public abstract void onBusLineSearched(BusLineResult paramBusLineResult, int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.BusLineSearch
 * JD-Core Version:    0.6.2
 */