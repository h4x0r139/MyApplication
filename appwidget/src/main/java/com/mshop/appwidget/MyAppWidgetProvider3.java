package com.mshop.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.UUID;

/**
 * Created by yinxm on 2015/12/16.
 */
public class MyAppWidgetProvider3 extends AppWidgetProvider{
    //定义一个常量字符串，该常量用于命名Action
    private static final String WIDGET3_UPDATE_ACTION = "com.h4x0r.UPDATE_WIDGET";
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("onDeleted MyAppWidgetProvider3");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        System.out.println("onDisabled MyAppWidgetProvider3");
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        System.out.println("onEnabled MyAppWidgetProvider3");
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive MyAppWidgetProvider3");
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (WIDGET3_UPDATE_ACTION.equals(action)) {
            System.out.println("接收到来自UPDATE_ACTION的广播");

            //得到RemoveViews、AppWidgetManager
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.appwidget_layout3);
            //更新控件状态
            remoteViews.setImageViewResource(R.id.imageId3,R.drawable.ku);
            remoteViews.setTextViewText(R.id.widgetTextId3,"这是更新后的Text");
            //获得AppWidgetManager
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context,MyAppWidgetProvider3.class);
            appWidgetManager.updateAppWidget(componentName,remoteViews);
        } else {
            //系统默认的广播，默认也要转发出去
            super.onReceive(context,intent);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        System.out.println("widget length=" + appWidgetIds.length);
        //点击更新Action
        Intent intent = new Intent();
        intent.setAction(WIDGET3_UPDATE_ACTION);

        //创建一个PendingIntent
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, UUID.randomUUID().hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.appwidget_layout3);
        //为按钮绑定事件处理器
        //第一个参数用来指定被绑定处理器的控件的ID
        //第二个参数用来指定当事件发生时，哪个PendingIntent将会被执行
        remoteViews.setOnClickPendingIntent(R.id.widgetButtonId3, pendingIntent);
        //更新AppWidget
        //第一个参数用于指定被更新AppWidget的ID
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
        System.out.println("onUpdate MyAppWidgetProvider3 "+PendingIntent.FLAG_UPDATE_CURRENT);

    }

//    @Override
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
//                         int[] appWidgetIds) {
//        Intent intent = new Intent();
//        intent.setAction(WIDGET3_UPDATE_ACTION);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
//                intent, 0);
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
//                R.layout.appwidget_layout3);
//        remoteViews.setOnClickPendingIntent(R.id.widgetButtonId3, pendingIntent);
//        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
//    }
}
