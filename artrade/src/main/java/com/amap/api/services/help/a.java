package com.amap.api.services.help;

import android.os.Handler;
import android.os.Message;
import java.util.List;

class a extends Handler
{
  a(Inputtips paramInputtips)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    if (Inputtips.b(this.a) == null)
      return;
    int i = paramMessage.what;
    List localList = null;
    if (i == 0)
      localList = (List)paramMessage.obj;
    Inputtips.b(this.a).onGetInputtips(localList, paramMessage.what);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.help.a
 * JD-Core Version:    0.6.2
 */