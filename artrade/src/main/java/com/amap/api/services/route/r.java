package com.amap.api.services.route;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class r extends Handler
{
  r(RouteSearch paramRouteSearch)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (RouteSearch.a(this.a) == null);
    Bundle localBundle1;
    do
    {
      do
      {
        Bundle localBundle2;
        do
        {
          Bundle localBundle3;
          do
          {
            return;
            if (paramMessage.what != 10)
              break;
            localBundle3 = paramMessage.getData();
          }
          while (localBundle3 == null);
          int k = localBundle3.getInt("errorCode");
          RouteSearch.a(this.a).onBusRouteSearched((BusRouteResult)paramMessage.obj, k);
          return;
          if (paramMessage.what != 11)
            break;
          localBundle2 = paramMessage.getData();
        }
        while (localBundle2 == null);
        int j = localBundle2.getInt("errorCode");
        RouteSearch.a(this.a).onDriveRouteSearched((DriveRouteResult)paramMessage.obj, j);
        return;
      }
      while (paramMessage.what != 12);
      localBundle1 = paramMessage.getData();
    }
    while (localBundle1 == null);
    int i = localBundle1.getInt("errorCode");
    RouteSearch.a(this.a).onWalkRouteSearched((WalkRouteResult)paramMessage.obj, i);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.r
 * JD-Core Version:    0.6.2
 */