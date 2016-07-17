package com.tg.jiadeonline.utils;

import android.app.Activity;
import android.app.Application;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Exit extends Application
{
  private static Exit instance;
  private List<Activity> activityList = new LinkedList();

  public static Exit getInstance()
  {
    if (instance == null)
      instance = new Exit();
    return instance;
  }

  public void addActivity(Activity paramActivity)
  {
    this.activityList.add(paramActivity);
  }

  public void exit()
  {
    Iterator localIterator = this.activityList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        System.exit(0);
        return;
      }
      ((Activity)localIterator.next()).finish();
    }
  }

  public void exitHome()
  {
    Iterator localIterator = this.activityList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((Activity)localIterator.next()).finish();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.Exit
 * JD-Core Version:    0.6.2
 */