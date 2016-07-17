package com.amap.api.services.route;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.c;
import java.util.List;

public class RouteSearch
{
  public static final int BusComfortable = 4;
  public static final int BusDefault = 0;
  public static final int BusLeaseChange = 2;
  public static final int BusLeaseWalk = 3;
  public static final int BusNoSubway = 5;
  public static final int BusSaveMoney = 1;
  public static final int DrivingAvoidCongestion = 4;
  public static final int DrivingDefault = 0;
  public static final int DrivingMultiStrategy = 5;
  public static final int DrivingNoExpressways = 3;
  public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
  public static final int DrivingNoHighWay = 6;
  public static final int DrivingNoHighWaySaveMoney = 7;
  public static final int DrivingSaveMoney = 1;
  public static final int DrivingSaveMoneyAvoidCongestion = 8;
  public static final int DrivingShortDistance = 2;
  public static final int WalkDefault = 0;
  public static final int WalkMultipath = 1;
  Handler a = new r(this);
  private OnRouteSearchListener b;
  private Context c;

  public RouteSearch(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
  }

  private static boolean b(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return true;
    if ((paramString1 != null) && (paramString2 != null))
      return paramString1.equals(paramString2);
    return false;
  }

  public BusRouteResult calculateBusRoute(BusRouteQuery paramBusRouteQuery)
    throws AMapException
  {
    com.amap.api.services.core.b.a(this.c);
    BusRouteQuery localBusRouteQuery = paramBusRouteQuery.clone();
    BusRouteResult localBusRouteResult = (BusRouteResult)new d(new b(localBusRouteQuery.getFromAndTo(), localBusRouteQuery.getMode(), "" + localBusRouteQuery.getNightFlag(), localBusRouteQuery.getCity()), c.a(this.c)).i();
    if (localBusRouteResult != null)
      localBusRouteResult.setBusQuery(localBusRouteQuery);
    return localBusRouteResult;
  }

  public void calculateBusRouteAsyn(final BusRouteQuery paramBusRouteQuery)
  {
    new Thread()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 10;
        Bundle localBundle = new Bundle();
        BusRouteResult localBusRouteResult = null;
        try
        {
          localBusRouteResult = RouteSearch.this.calculateBusRoute(paramBusRouteQuery);
          localBundle.putInt("errorCode", 0);
          return;
        }
        catch (AMapException localAMapException)
        {
          localBundle.putInt("errorCode", localAMapException.getErrorCode());
          return;
        }
        finally
        {
          localMessage.obj = localBusRouteResult;
          localMessage.setData(localBundle);
          RouteSearch.this.a.sendMessage(localMessage);
        }
      }
    }
    .start();
  }

  public DriveRouteResult calculateDriveRoute(DriveRouteQuery paramDriveRouteQuery)
    throws AMapException
  {
    com.amap.api.services.core.b.a(this.c);
    DriveRouteQuery localDriveRouteQuery = paramDriveRouteQuery.clone();
    DriveRouteResult localDriveRouteResult = (DriveRouteResult)new k(new i(localDriveRouteQuery.getFromAndTo(), localDriveRouteQuery.getMode(), localDriveRouteQuery.getPassedByPoints(), localDriveRouteQuery.getAvoidpolygons(), localDriveRouteQuery.getAvoidRoad()), c.a(this.c)).i();
    if (localDriveRouteResult != null)
      localDriveRouteResult.setDriveQuery(localDriveRouteQuery);
    return localDriveRouteResult;
  }

  public void calculateDriveRouteAsyn(final DriveRouteQuery paramDriveRouteQuery)
  {
    new Thread()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 11;
        Bundle localBundle = new Bundle();
        DriveRouteResult localDriveRouteResult = null;
        try
        {
          localDriveRouteResult = RouteSearch.this.calculateDriveRoute(paramDriveRouteQuery);
          localBundle.putInt("errorCode", 0);
          return;
        }
        catch (AMapException localAMapException)
        {
          localBundle.putInt("errorCode", localAMapException.getErrorCode());
          return;
        }
        finally
        {
          localMessage.obj = localDriveRouteResult;
          localMessage.setData(localBundle);
          RouteSearch.this.a.sendMessage(localMessage);
        }
      }
    }
    .start();
  }

  public WalkRouteResult calculateWalkRoute(WalkRouteQuery paramWalkRouteQuery)
    throws AMapException
  {
    com.amap.api.services.core.b.a(this.c);
    WalkRouteQuery localWalkRouteQuery = paramWalkRouteQuery.clone();
    WalkRouteResult localWalkRouteResult = (WalkRouteResult)new y(new w(localWalkRouteQuery.getFromAndTo(), localWalkRouteQuery.getMode(), "0"), c.a(this.c)).i();
    if (localWalkRouteResult != null)
      localWalkRouteResult.setWalkQuery(localWalkRouteQuery);
    return localWalkRouteResult;
  }

  public void calculateWalkRouteAsyn(final WalkRouteQuery paramWalkRouteQuery)
  {
    new Thread()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 12;
        Bundle localBundle = new Bundle();
        WalkRouteResult localWalkRouteResult = null;
        try
        {
          localWalkRouteResult = RouteSearch.this.calculateWalkRoute(paramWalkRouteQuery);
          localBundle.putInt("errorCode", 0);
          return;
        }
        catch (AMapException localAMapException)
        {
          localBundle.putInt("errorCode", localAMapException.getErrorCode());
          return;
        }
        finally
        {
          localMessage.obj = localWalkRouteResult;
          localMessage.setData(localBundle);
          RouteSearch.this.a.sendMessage(localMessage);
        }
      }
    }
    .start();
  }

  public void setRouteSearchListener(OnRouteSearchListener paramOnRouteSearchListener)
  {
    this.b = paramOnRouteSearchListener;
  }

  public static class BusRouteQuery
  {
    private RouteSearch.FromAndTo a;
    private int b;
    private String c;
    private int d;

    public BusRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt1, String paramString, int paramInt2)
    {
      this.a = paramFromAndTo;
      this.b = paramInt1;
      this.c = paramString;
      this.d = paramInt2;
    }

    public BusRouteQuery clone()
    {
      return new BusRouteQuery(this.a, this.b, this.c, this.b);
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if ((paramObject == null) || (!(paramObject instanceof BusRouteQuery)))
        bool = false;
      BusRouteQuery localBusRouteQuery;
      do
      {
        do
          return bool;
        while (paramObject == this);
        localBusRouteQuery = (BusRouteQuery)paramObject;
      }
      while ((localBusRouteQuery.a.equals(this.a)) && (localBusRouteQuery.b == this.b) && (RouteSearch.a(localBusRouteQuery.c, this.c)) && (localBusRouteQuery.d == this.d));
      return false;
    }

    public String getCity()
    {
      return this.c;
    }

    public RouteSearch.FromAndTo getFromAndTo()
    {
      return this.a;
    }

    public int getMode()
    {
      return this.b;
    }

    public int getNightFlag()
    {
      return this.d;
    }
  }

  public static class DriveRouteQuery
  {
    private RouteSearch.FromAndTo a;
    private int b;
    private List<LatLonPoint> c;
    private List<List<LatLonPoint>> d;
    private String e;

    public DriveRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt, List<LatLonPoint> paramList, List<List<LatLonPoint>> paramList1, String paramString)
    {
      this.a = paramFromAndTo;
      this.b = paramInt;
      this.c = paramList;
      this.d = paramList1;
      this.e = paramString;
    }

    public DriveRouteQuery clone()
    {
      return new DriveRouteQuery(this.a, this.b, this.c, this.d, this.e);
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if ((paramObject == null) || ((paramObject instanceof DriveRouteQuery)))
        bool = false;
      DriveRouteQuery localDriveRouteQuery;
      do
      {
        do
          return bool;
        while (paramObject == this);
        localDriveRouteQuery = (DriveRouteQuery)paramObject;
      }
      while ((localDriveRouteQuery.a.equals(this.a)) && (localDriveRouteQuery.b == this.b) && (localDriveRouteQuery.d.containsAll(this.d)) && (localDriveRouteQuery.c.containsAll(this.c)) && (RouteSearch.a(localDriveRouteQuery.e, this.e)));
      return false;
    }

    public String getAvoidRoad()
    {
      return this.e;
    }

    public List<List<LatLonPoint>> getAvoidpolygons()
    {
      return this.d;
    }

    public RouteSearch.FromAndTo getFromAndTo()
    {
      return this.a;
    }

    public int getMode()
    {
      return this.b;
    }

    public List<LatLonPoint> getPassedByPoints()
    {
      return this.c;
    }
  }

  public static class FromAndTo
  {
    private LatLonPoint a;
    private LatLonPoint b;
    private String c;
    private String d;

    public FromAndTo(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
    {
      this.a = paramLatLonPoint1;
      this.b = paramLatLonPoint2;
    }

    public FromAndTo clone()
    {
      FromAndTo localFromAndTo = new FromAndTo(this.a, this.b);
      localFromAndTo.setStartPoiID(this.c);
      localFromAndTo.setDestinationPoiID(this.d);
      return localFromAndTo;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if ((paramObject == null) || (!(paramObject instanceof FromAndTo)))
        bool = false;
      FromAndTo localFromAndTo;
      do
      {
        do
          return bool;
        while (paramObject == this);
        localFromAndTo = (FromAndTo)paramObject;
      }
      while ((localFromAndTo.a.equals(this.a)) && (localFromAndTo.b.equals(this.b)) && (RouteSearch.a(localFromAndTo.c, this.c)) && (RouteSearch.a(localFromAndTo.d, this.d)));
      return false;
    }

    public String getDestinationPoiID()
    {
      return this.d;
    }

    public LatLonPoint getFrom()
    {
      return this.a;
    }

    public String getStartPoiID()
    {
      return this.c;
    }

    public LatLonPoint getTo()
    {
      return this.b;
    }

    public void setDestinationPoiID(String paramString)
    {
      this.d = paramString;
    }

    public void setStartPoiID(String paramString)
    {
      this.c = paramString;
    }
  }

  public static abstract interface OnRouteSearchListener
  {
    public abstract void onBusRouteSearched(BusRouteResult paramBusRouteResult, int paramInt);

    public abstract void onDriveRouteSearched(DriveRouteResult paramDriveRouteResult, int paramInt);

    public abstract void onWalkRouteSearched(WalkRouteResult paramWalkRouteResult, int paramInt);
  }

  public static class WalkRouteQuery
  {
    private RouteSearch.FromAndTo a;
    private int b;

    public WalkRouteQuery(RouteSearch.FromAndTo paramFromAndTo, int paramInt)
    {
      this.a = paramFromAndTo;
      this.b = paramInt;
    }

    public WalkRouteQuery clone()
    {
      return new WalkRouteQuery(this.a, this.b);
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if ((paramObject == null) || (!(paramObject instanceof WalkRouteQuery)))
        bool = false;
      WalkRouteQuery localWalkRouteQuery;
      do
      {
        do
          return bool;
        while (paramObject == this);
        localWalkRouteQuery = (WalkRouteQuery)paramObject;
      }
      while ((localWalkRouteQuery.a.equals(this.a)) && (localWalkRouteQuery.b == this.b));
      return false;
    }

    public RouteSearch.FromAndTo getFromAndTo()
    {
      return this.a;
    }

    public int getMode()
    {
      return this.b;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.RouteSearch
 * JD-Core Version:    0.6.2
 */