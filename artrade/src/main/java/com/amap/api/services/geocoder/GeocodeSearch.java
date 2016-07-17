package com.amap.api.services.geocoder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import java.util.List;

public final class GeocodeSearch
{
  public static String AMAP = "autonavi";
  public static String GPS = "gps";
  Handler a = new b(this);
  private Context b;
  private OnGeocodeSearchListener c;

  public GeocodeSearch(Context paramContext)
  {
    com.amap.api.services.core.b.a(paramContext);
    this.b = paramContext.getApplicationContext();
  }

  public RegeocodeAddress getFromLocation(RegeocodeQuery paramRegeocodeQuery)
    throws AMapException
  {
    return (RegeocodeAddress)new f(paramRegeocodeQuery, com.amap.api.services.core.c.a(this.b)).i();
  }

  public void getFromLocationAsyn(final RegeocodeQuery paramRegeocodeQuery)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Message localMessage = new Message();
        try
        {
          localMessage.what = 101;
          RegeocodeAddress localRegeocodeAddress = GeocodeSearch.this.getFromLocation(paramRegeocodeQuery);
          localMessage.arg1 = 0;
          localMessage.obj = new RegeocodeResult(paramRegeocodeQuery, localRegeocodeAddress);
          return;
        }
        catch (AMapException localAMapException)
        {
          localMessage.arg1 = localAMapException.getErrorCode();
          return;
        }
        finally
        {
          GeocodeSearch.this.a.sendMessage(localMessage);
        }
      }
    }).start();
  }

  public List<GeocodeAddress> getFromLocationName(GeocodeQuery paramGeocodeQuery)
    throws AMapException
  {
    return (List)new c(paramGeocodeQuery, com.amap.api.services.core.c.a(this.b)).i();
  }

  public void getFromLocationNameAsyn(final GeocodeQuery paramGeocodeQuery)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        Message localMessage = new Message();
        try
        {
          localMessage.what = 100;
          List localList = GeocodeSearch.this.getFromLocationName(paramGeocodeQuery);
          localMessage.arg1 = 0;
          localMessage.obj = new GeocodeResult(paramGeocodeQuery, localList);
          return;
        }
        catch (AMapException localAMapException)
        {
          localMessage.arg1 = localAMapException.getErrorCode();
          return;
        }
        finally
        {
          GeocodeSearch.this.a.sendMessage(localMessage);
        }
      }
    }).start();
  }

  public void setOnGeocodeSearchListener(OnGeocodeSearchListener paramOnGeocodeSearchListener)
  {
    this.c = paramOnGeocodeSearchListener;
  }

  public static abstract interface OnGeocodeSearchListener
  {
    public abstract void onGeocodeSearched(GeocodeResult paramGeocodeResult, int paramInt);

    public abstract void onRegeocodeSearched(RegeocodeResult paramRegeocodeResult, int paramInt);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.GeocodeSearch
 * JD-Core Version:    0.6.2
 */