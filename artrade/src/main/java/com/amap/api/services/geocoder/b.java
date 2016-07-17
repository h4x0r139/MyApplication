package com.amap.api.services.geocoder;

import android.os.Handler;
import android.os.Message;

class b extends Handler
{
  b(GeocodeSearch paramGeocodeSearch)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (GeocodeSearch.a(this.a) == null)
      return;
    if (paramMessage.what == 101)
    {
      int j = paramMessage.arg2;
      RegeocodeResult localRegeocodeResult = null;
      if (j == 0)
        localRegeocodeResult = (RegeocodeResult)paramMessage.obj;
      GeocodeSearch.a(this.a).onRegeocodeSearched(localRegeocodeResult, paramMessage.arg1);
      return;
    }
    int i = paramMessage.arg2;
    GeocodeResult localGeocodeResult = null;
    if (i == 0)
      localGeocodeResult = (GeocodeResult)paramMessage.obj;
    GeocodeSearch.a(this.a).onGeocodeSearched(localGeocodeResult, paramMessage.arg1);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.geocoder.b
 * JD-Core Version:    0.6.2
 */