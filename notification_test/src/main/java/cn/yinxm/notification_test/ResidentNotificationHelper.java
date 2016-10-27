package cn.yinxm.notification_test;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by kris on 16/4/14.
 * 常驻通知帮助类
 */
public class ResidentNotificationHelper {
    public static final String DUMMY="DUMMY";
    public static final double COLOR_BASE=180.0;
    public static int Titlecolor;
    public static final String NOTICE_ID_KEY = "NOTICE_ID";
    public static final String ACTION_CLOSE_NOTICE = "cn.campusapp.action.closenotice";
    public static final int NOTICE_ID_TYPE_0 = R.string.app_name;
    public static  Notification notification;
//    @TargetApi(16)
//    public static void sendResidentNoticeType0(Context context, String title, String content, @DrawableRes int res) {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setOngoing(true);
//        builder.setPriority(NotificationCompat.PRIORITY_MAX);
//        RemoteViews remoteViews;
//        if(isDarkNotifiation(context)){
////            view_notification_type_0
//            remoteViews = new RemoteViews(context.getPackageName(), R.layout.draw);
//        }else {
//             remoteViews = new RemoteViews(context.getPackageName(), R.layout.whiles);
//        }
//
//        remoteViews.setTextViewText(R.id.title_tv, title);
//        remoteViews.setTextViewText(R.id.content_tv, content);
//        remoteViews.setTextViewText(R.id.time_tv, getTime());
//        remoteViews.setImageViewResource(R.id.icon_iv, R.drawable.logo);
//        remoteViews.setInt(R.id.close_iv, "setColorFilter", getIconColor());
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.putExtra(NOTICE_ID_KEY, NOTICE_ID_TYPE_0);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        int requestCode = (int) SystemClock.uptimeMillis();
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.notice_view_type_0, pendingIntent);
//        int requestCode1 = (int) SystemClock.uptimeMillis();
//        Intent intent1 = new Intent(ACTION_CLOSE_NOTICE);
//        intent1.putExtra(NOTICE_ID_KEY, NOTICE_ID_TYPE_0);
//        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, requestCode1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.close_iv, pendingIntent1);
//        builder.setSmallIcon(R.drawable.logo);
//
//
//         notification = builder.build();
//
//
//        if(android.os.Build.VERSION.SDK_INT >= 16) {
//            notification = builder.build();
//            notification.bigContentView = remoteViews;
//        }
//
//
//
//        notification.contentView = remoteViews;
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(NOTICE_ID_TYPE_0, notification);
//    }

//    public static Notification getReceivedNotification(){
//        return notification;
//    }
//    public static void sendDefaultNotice(Context context, String title, String content, @DrawableRes int res) {
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
//        builder.setOngoing(true);
//        builder.setPriority(NotificationCompat.PRIORITY_MAX);
//
//
//
//        Notification notification = builder
//                .setContentTitle("Campus")
//                .setContentText("It's a default notification")
//                .setSmallIcon(R.drawable.logo)
//                .setSmallIcon(R.drawable.logo)
//                .build();
//
//
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(NOTICE_ID_TYPE_0, notification);
//    }


    public static int getIconColor(){
        return Color.parseColor("#999999");

    }


    private static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.SIMPLIFIED_CHINESE);
        return format.format(new Date());
    }


    public static void clearNotification(Context context, int noticeId) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(noticeId);
    }

    public static boolean isDarkNotifiation(Context context){
        return !isColorSimilar(Color.BLACK,getNotificationColor(context));
    }
    public static boolean isColorSimilar(int basecolor,int coloer){
        int i = basecolor | 0xff000000;
        int i1 = coloer | 0xff000000;
        int i2 = Color.red(i) - Color.red(i1);
        int i3 = Color.green(i) - Color.green(i1);
        int i4 = Color.blue(i) - Color.blue(i1);
        double sqrt = Math.sqrt(i2 * i2 + i3 * i3 + i4 * i4);
        if(sqrt<COLOR_BASE){

            return true;
        }
        return false;
    }

    public static int getNotifitionColor(Context context) {
        android.support.v7.app.NotificationCompat.Builder buder = new android.support.v7.app.NotificationCompat.Builder(context);
        Notification build = buder.build();
        int layoutId = build.contentView.getLayoutId();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null);
        TextView viewById = (TextView) viewGroup.findViewById(android.R.id.title);
        if (viewById == null) {
            iteraorView(viewGroup, new Filte() {
                @Override
                public void filter(View view) {
                    if(view instanceof TextView){
                        TextView textview=(TextView)view;
                        if(DUMMY.equals(textview.getText().toString())){
                            Titlecolor=textview.getCurrentTextColor();
                        }
                    }
                }
            });
            return Titlecolor;
        }else {
            return viewById.getCurrentTextColor();
        }
    }




    public static int getNotificationColor(Context context){
        if(context instanceof AppCompatActivity){

            return getNotifitionColorCompat(context);
        }else {
            return getNotifitionColor(context);
        }
    }

    public static void iteraorView (View view , Filte filter){

        if(view==null||filter==null){
            return;
        }
        filter.filter(view);
        if(view instanceof ViewGroup){
            ViewGroup view1 = (ViewGroup) view;
            for (int i=0;i<view1.getChildCount();i++){
                View childAt = view1.getChildAt(i);
                iteraorView(childAt,filter);
            }
        }
    }


    public static int getNotifitionColorCompat(Context context){
        android.support.v7.app.NotificationCompat.Builder buder = new android.support.v7.app.NotificationCompat.Builder(context);
        Notification build = buder.build();
        int layoutId = build.contentView.getLayoutId();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null);
        TextView viewById = (TextView) viewGroup.findViewById(android.R.id.title);
        if(viewById==null){
            //拿到所有的textview
            final List<TextView> textviews=new ArrayList<>();
            iteraorView(viewGroup, new Filte() {
                @Override
                public void filter(View view) {
                    if(view instanceof TextView){
                        textviews.add((TextView)view);
                    }
                }
            });

            float maxValue = Integer.MAX_VALUE;
            int idex=0;
            for (int i=0;i<textviews.size();i++){
                float textSize = textviews.get(i).getTextSize();
                if(textSize>maxValue){
                    maxValue=textSize;
                    idex=1;
                }
            }
            return textviews.get(idex).getCurrentTextColor();
        }else{
            return viewById.getCurrentTextColor();
        }
    }


    private interface Filte{
        void filter(View view);
    }

}
