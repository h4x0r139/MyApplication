package mars.appwidget03;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
	//定义一个常量字符串，该常量用于命名Action
	private static final String UPDATE_ACTION = "mars.appwidget03.UPDATE_APP_WIDGET";

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		String action = intent.getAction();
		if (UPDATE_ACTION.equals(action)) {
			System.out.println("onReceive--->" + UPDATE_ACTION);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		//创建一个Intent对象
		Intent intent = new Intent();
		//为Intent对象设置Action
		intent.setAction(UPDATE_ACTION);
		//使用getBroadcast方法，得到一个PendingIntent对象，当该对象执行时，会发送一个广播
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,intent, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),	R.layout.example_appwidget);
		remoteViews.setOnClickPendingIntent(R.id.widgetButtonId, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

}
