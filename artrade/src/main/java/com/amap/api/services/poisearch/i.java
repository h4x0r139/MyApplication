package com.amap.api.services.poisearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

class i extends Handler
{
  i(PoiSearch paramPoiSearch)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (PoiSearch.a(this.a) == null);
    Bundle localBundle1;
    do
    {
      do
      {
        Bundle localBundle2;
        do
        {
          return;
          if (paramMessage.what != 100)
            break;
          localBundle2 = paramMessage.getData();
        }
        while (localBundle2 == null);
        int j = localBundle2.getInt("errorCode");
        PoiSearch.a(this.a).onPoiSearched((PoiResult)paramMessage.obj, j);
        return;
      }
      while (paramMessage.what != 101);
      localBundle1 = paramMessage.getData();
    }
    while (localBundle1 == null);
    int i = localBundle1.getInt("errorCode");
    PoiSearch.a(this.a).onPoiItemDetailSearched((PoiItemDetail)paramMessage.obj, i);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.i
 * JD-Core Version:    0.6.2
 */