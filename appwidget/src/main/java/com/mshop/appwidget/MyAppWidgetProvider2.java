package com.mshop.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by yinxm on 2015/12/16.
 */
public class MyAppWidgetProvider2  extends AppWidgetProvider{
    //定义一个常量字符串，该常量用于命名Action
    private static final String UPDATE_ACTION = "UPDATE_APP_WIDGET";
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        System.out.println("onDeleted MyAppWidgetProvider2");
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        System.out.println("onDisabled MyAppWidgetProvider2");
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context) {
        System.out.println("onEnabled MyAppWidgetProvider2");
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("onReceive MyAppWidgetProvider2");
        super.onReceive(context, intent);

        String action = intent.getAction();
        if (UPDATE_ACTION.equals(action)) {
            System.out.println("接收到来自UPDATE_ACTION的广播");
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {
            System.out.println(appWidgetIds[i]);

            //创建一个Intent对象，点击启动Activity
            Intent intent = new Intent(context,TargetActivity.class);

            //点击更新Action
//            Intent intent = new Intent();
//            intent.setAction(UPDATE_ACTION);
            //创建一个PendingIntent
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.appwidget_layout2);
            //为按钮绑定事件处理器
            //第一个参数用来指定被绑定处理器的控件的ID
            //第二个参数用来指定当事件发生时，哪个PendingIntent将会被执行
            remoteViews.setOnClickPendingIntent(R.id.widgetButtonId2, pendingIntent);
            //更新AppWidget
            //第一个参数用于指定被更新AppWidget的ID
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        System.out.println("onUpdate MyAppWidgetProvider2");

    }

}

