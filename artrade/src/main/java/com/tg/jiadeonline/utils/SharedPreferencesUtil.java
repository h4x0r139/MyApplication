package com.tg.jiadeonline.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil
{
  public static boolean readIsFirstUse(Context paramContext)
  {
    return paramContext.getSharedPreferences("jiade", 0).getBoolean("islogin", false);
  }

  public static void writeIsFirstUse(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("jiade", 0).edit();
    localEditor.putBoolean("islogin", true);
    localEditor.commit();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.SharedPreferencesUtil
 * JD-Core Version:    0.6.2
 */