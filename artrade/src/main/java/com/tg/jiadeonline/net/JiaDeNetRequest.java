package com.tg.jiadeonline.net;

import android.content.Context;
import java.util.Map;

public class JiaDeNetRequest
{
  public String APIName;
  public boolean canCanceled = false;
  public Map<String, Object> entityParmas;
  public Context mContext;
  public String methodName;

  public JiaDeNetRequest(String paramString1, String paramString2, boolean paramBoolean, Map<String, Object> paramMap, Context paramContext)
  {
    this.entityParmas = paramMap;
    this.canCanceled = paramBoolean;
    this.APIName = paramString2;
    this.methodName = paramString1;
    this.mContext = paramContext;
  }

  public Map<String, Object> getEntity()
  {
    return this.entityParmas;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.JiaDeNetRequest
 * JD-Core Version:    0.6.2
 */