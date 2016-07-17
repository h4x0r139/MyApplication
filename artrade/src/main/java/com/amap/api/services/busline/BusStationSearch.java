package com.amap.api.services.busline;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.b;
import java.util.ArrayList;

public class BusStationSearch
{
  Handler a = new e(this);
  private Context b;
  private OnBusStationSearchListener c;
  private BusStationQuery d;
  private BusStationQuery e;
  private ArrayList<BusStationResult> f;
  private int g;

  public BusStationSearch(Context paramContext, BusStationQuery paramBusStationQuery)
  {
    b.a(paramContext);
    this.b = paramContext.getApplicationContext();
    this.d = paramBusStationQuery;
    this.e = paramBusStationQuery.clone();
  }

  private void a(BusStationResult paramBusStationResult)
  {
    this.f = new ArrayList();
    for (int i = 0; i <= this.g; i++)
      this.f.add(null);
    if (this.g > 0)
      this.f.set(this.d.getPageNumber(), paramBusStationResult);
  }

  private boolean a(int paramInt)
  {
    return (paramInt <= this.g) && (paramInt > 0);
  }

  private BusStationResult b(int paramInt)
  {
    if (!a(paramInt))
      throw new IllegalArgumentException("page out of range");
    return (BusStationResult)this.f.get(paramInt);
  }

  public BusStationQuery getQuery()
  {
    return this.d;
  }

  public BusStationResult searchBusStation()
    throws AMapException
  {
    if (!this.d.weakEquals(this.e))
    {
      this.e = this.d.clone();
      this.g = 0;
      if (this.f != null)
        this.f.clear();
    }
    BusStationResult localBusStationResult1;
    if (this.g == 0)
    {
      c localc1 = new c(this.d, com.amap.api.services.core.c.a(this.b));
      localBusStationResult1 = BusStationResult.a(localc1, (ArrayList)localc1.i());
      this.g = localBusStationResult1.getPageCount();
      a(localBusStationResult1);
    }
    do
    {
      return localBusStationResult1;
      localBusStationResult1 = b(this.d.getPageNumber());
    }
    while (localBusStationResult1 != null);
    c localc2 = new c(this.d, com.amap.api.services.core.c.a(this.b));
    BusStationResult localBusStationResult2 = BusStationResult.a(localc2, (ArrayList)localc2.i());
    this.f.set(this.d.getPageNumber(), localBusStationResult2);
    return localBusStationResult2;
  }

  public void searchBusStationAsyn()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Message localMessage = new Message();
        try
        {
          BusStationResult localBusStationResult = BusStationSearch.this.searchBusStation();
          localMessage.what = 0;
          localMessage.obj = localBusStationResult;
          return;
        }
        catch (AMapException localAMapException)
        {
          localMessage.what = localAMapException.getErrorCode();
          return;
        }
        finally
        {
          BusStationSearch.this.a.sendMessage(localMessage);
        }
      }
    }).start();
  }

  public void setOnBusStationSearchListener(OnBusStationSearchListener paramOnBusStationSearchListener)
  {
    this.c = paramOnBusStationSearchListener;
  }

  public void setQuery(BusStationQuery paramBusStationQuery)
  {
    if (!paramBusStationQuery.weakEquals(this.d))
    {
      this.d = paramBusStationQuery;
      this.e = paramBusStationQuery.clone();
    }
  }

  public static abstract interface OnBusStationSearchListener
  {
    public abstract void onBusStationSearched(BusStationResult paramBusStationResult, int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.BusStationSearch
 * JD-Core Version:    0.6.2
 */