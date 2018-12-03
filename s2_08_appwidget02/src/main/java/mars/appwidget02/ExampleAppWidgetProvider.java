package mars.appwidget02;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider{

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
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++) {
			System.out.println(appWidgetIds[i]);
			//创建一个Intent对象
			Intent intent = new Intent(context,TargetActivity.class);
			//创建一个PendingIntent
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.example_appwidget);
			//为按钮绑定事件处理器
			//第一个参数用来指定被绑定处理器的控件的ID
			//第二个参数用来指定当事件发生时，哪个PendingIntent将会被执行
			remoteViews.setOnClickPendingIntent(R.id.widgetButtonId, pendingIntent);
			//更新AppWidget
			//第一个参数用于指定被更新AppWidget的ID
			appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
