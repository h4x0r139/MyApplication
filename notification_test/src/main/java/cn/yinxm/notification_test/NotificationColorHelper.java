package cn.yinxm.notification_test;

import android.app.Notification;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.utils.LogUtil;


/**
 * Created by kris on 16/4/14.
 * 获取通知栏颜色工具类
 *
 */
public class NotificationColorHelper {
    public static final String DUMMY = "DUMMY";
    public static final double COLOR_BASE = 180.0;
    public static int Titlecolor;

    /**
     * 判断是否是黑色，或者黑色透明
     * @param context
     * @return
     */
    public static boolean isDarkNotifiation(Context context) {
        return !isColorSimilar(Color.BLACK, getNotificationColor(context));
    }

    private static boolean isColorSimilar(int basecolor, int coloer) {
        LogUtil.d("[NotificationColorHelper] basecolor=" + basecolor + ", coloer=" + coloer);
        int i = basecolor | 0xff000000;
        int i1 = coloer | 0xff000000;
        int i2 = Color.red(i) - Color.red(i1);
        int i3 = Color.green(i) - Color.green(i1);
        int i4 = Color.blue(i) - Color.blue(i1);
        double sqrt = Math.sqrt(i2 * i2 + i3 * i3 + i4 * i4);
        LogUtil.d("sqrt=" + sqrt);
        if (sqrt < COLOR_BASE) {
            return true;
        }
        return false;
    }

    private static int getNotifitionColor(Context context) {
        LogUtil.d("[NotificationColorHelper.getNotifitionColor] ");
        android.support.v7.app.NotificationCompat.Builder buder = new android.support.v7.app.NotificationCompat.Builder(context);
        Notification build = buder.build();
        int layoutId = build.contentView.getLayoutId();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null);
        TextView viewById = (TextView) viewGroup.findViewById(android.R.id.title);
        LogUtil.d("viewById="+viewById);
        if (viewById == null) {
            iteraorView(viewGroup, new Filte() {
                @Override
                public void filter(View view) {
                    if (view instanceof TextView) {
                        TextView textview = (TextView) view;
                        if (DUMMY.equals(textview.getText().toString())) {
                            Titlecolor = textview.getCurrentTextColor();
                        }
                    }
                }
            });
            LogUtil.d("Titlecolor="+Titlecolor);
            return Titlecolor;
        } else {
            return viewById.getCurrentTextColor();
        }
    }


    private static int getNotificationColor(Context context) {
        if (context instanceof AppCompatActivity) {
            LogUtil.d("[NotificationColorHelper] AppCompatActivity");
            return getNotifitionColorCompat(context);
        } else {
            return getNotifitionColor(context);
        }
    }

    private static void iteraorView(View view, Filte filter) {
        if (view == null || filter == null) {
            return;
        }
        filter.filter(view);
        if (view instanceof ViewGroup) {
            ViewGroup view1 = (ViewGroup) view;
            for (int i = 0; i < view1.getChildCount(); i++) {
                View childAt = view1.getChildAt(i);
                iteraorView(childAt, filter);
            }
        }
    }


    private static int getNotifitionColorCompat(Context context) {
        android.support.v7.app.NotificationCompat.Builder buder = new android.support.v7.app.NotificationCompat.Builder(context);
        Notification build = buder.build();
        int layoutId = build.contentView.getLayoutId();
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(layoutId, null);
        TextView viewById = (TextView) viewGroup.findViewById(android.R.id.title);
        LogUtil.d("viewById=" + viewById);
        if (viewById == null) {
            //拿到所有的textview
            final List<TextView> textviews = new ArrayList<>();
            iteraorView(viewGroup, new Filte() {
                @Override
                public void filter(View view) {
                    if (view instanceof TextView) {
                        textviews.add((TextView) view);
                    }
                }
            });
            LogUtil.d("textviews.size=" + textviews.size());

            //字号最大的TextView就是Title
            float maxTextSize = Integer.MIN_VALUE;
            int index = 0;
            for (int i = 0; i < textviews.size(); i++) {
                float textSize = textviews.get(i).getTextSize();
                if (textSize > maxTextSize) {
                    maxTextSize = textSize;
                    index = i;
                }
            }
            LogUtil.d("textviews textSize max index=" + index);

            return textviews.get(index).getCurrentTextColor();
        } else {
            return viewById.getCurrentTextColor();
        }
    }

    private interface Filte {
        void filter(View view);
    }

}
