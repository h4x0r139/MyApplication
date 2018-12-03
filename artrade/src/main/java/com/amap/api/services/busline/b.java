package com.amap.api.services.busline;

import android.os.Handler;
import android.os.Message;

class b extends Handler
{
  b(BusLineSearch paramBusLineSearch)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (BusLineSearch.a(this.a) == null)
      return;
    int i = paramMessage.what;
    BusLineResult localBusLineResult = null;
    if (i == 0)
      localBusLineResult = (BusLineResult)paramMessage.obj;
    BusLineSearch.a(this.a).onBusLineSearched(localBusLineResult, paramMessage.what);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.busline.b
 * JD-Core Version:    0.6.2
 */