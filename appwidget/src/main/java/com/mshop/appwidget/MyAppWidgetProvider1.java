package com.mshop.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yinxm on 2015/12/16.
 */
public class MyAppWidgetProvider1 extends AppWidgetProvider{

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        System.out.println("onupdate");
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("onDeleted");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        System.out.println("onDisabled");
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        System.out.println("onEnabled");
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive");
        super.onReceive(context, intent);
    }
}
