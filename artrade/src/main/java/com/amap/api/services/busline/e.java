package com.amap.api.services.busline;

import android.os.Handler;
import android.os.Message;

class e extends Handler
{
  e(BusStationSearch paramBusStationSearch)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (BusStationSearch.a(this.a) == null)
      return;
    int i = paramMessage.what;
    BusStationResult localBusStationResult = null;
    if (i == 0)
      localBusStationResult = (BusStationResult)paramMessage.obj;
    BusStationSearch.a(this.a).onBusStationSearched(localBusStationResult, paramMessage.what);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.e
 * JD-Core Version:    0.6.2
 */