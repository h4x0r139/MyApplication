package mars.appwidget04;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider {
	private static final String UPDATE_ACTION = "mars.appwidget03.UPDATE_APP_WIDGET";

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {

	}

	@Override
	public void onDisabled(Context context) {
	}

	@Override
	public void onEnabled(Context context) {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (UPDATE_ACTION.equals(action)) {
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.example_appwidget);
			remoteViews.setImageViewResource(R.id.imageId, R.drawable.ku);
			remoteViews.setTextViewText(R.id.widgetTextId, "test");
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			ComponentName componentName = new ComponentName(context,ExampleAppWidgetProvider.class);
			appWidgetManager.updateAppWidget(componentName, remoteViews);
		} else {
			super.onReceive(context, intent);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Intent intent = new Intent();
		intent.setAction(UPDATE_ACTION);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, -1,
				intent, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.example_appwidget);
		remoteViews.setOnClickPendingIntent(R.id.widgetButtonId, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
	}

}
