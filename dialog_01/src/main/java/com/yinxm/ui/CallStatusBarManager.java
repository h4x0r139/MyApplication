package com.yinxm.ui;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yinxm on 2016/7/26.
 */
public class CallStatusBarManager {
    private CallStatusBarManager() {
    }

    public static CallStatusBarManager getInstance() {
        return CallStatusBarManagerFactory.instance;
    }

    private static class CallStatusBarManagerFactory {
        private static CallStatusBarManager instance = new CallStatusBarManager();
    }
    private Map<String, DialogStore> callStatusBarDialogMap = new HashMap<>();

    public void addCallStatusBar(Activity activity, View titleLayout) {
        Log.d("yinxm", "[CallStatusBarManager.addCallStatusBar] activity=" + activity+", name="+activity.getClass().getName());
        CallStatusBarDialog dialog = new CallStatusBarDialog(activity, titleLayout);

        DialogStore dialogStore = new DialogStore();
        dialogStore.setActivity(activity);
        dialogStore.setCallStatusBarDialog(dialog);

        String name = activity.getClass().getName();
        callStatusBarDialogMap.put(name, dialogStore);
    }

    /**
     * 更新显示状态
     */
    public void updateVisibleCallStatusBar(Activity activity) {
        if (activity == null) {
            return;
        }
        String className = activity.getClass().getName();
        Log.d("yinxm", "[CallStatusBarManager.updateVisibleCallStatusBar] activity=" + activity +", className="+className+ ", map=" + callStatusBarDialogMap);
        DialogStore dialogStore = callStatusBarDialogMap.get(className);
        if (dialogStore != null) {
            CallStatusBarDialog dialog = dialogStore.getCallStatusBarDialog();
            if (dialog != null) {
                Log.d("yinxm", "dialog=" + dialog);
                dialog.show();
            }
        }

    }


    /**
     * 更新显示状态
     */
    public void updateVisibleCallStatusBar() {
//        LogUtil.d("[CallStatusBarManager.updateVisibleCallStatusBar]  map=" + callStatusBarDialogMap);
        Iterator<Map.Entry<String, DialogStore>> iterator = callStatusBarDialogMap.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, DialogStore> entry = iterator.next();
            String className = entry.getKey();
            DialogStore dialogStore = entry.getValue();
            Activity activity = dialogStore.getActivity();
            CallStatusBarDialog dialog = dialogStore.getCallStatusBarDialog();
//            LogUtil.d("className=" + className+", activity="+activity+", isFinish="+activity.isFinishing());
            if (activity.isFinishing()) {
                iterator.remove();
            } else {
                dialog.show();
            }
        }
//        LogUtil.d("map=" + callStatusBarDialogMap);

    }

    class DialogStore {
        private CallStatusBarDialog callStatusBarDialog;
        private Activity activity;

        public CallStatusBarDialog getCallStatusBarDialog() {
            return callStatusBarDialog;
        }

        public void setCallStatusBarDialog(CallStatusBarDialog callStatusBarDialog) {
            this.callStatusBarDialog = callStatusBarDialog;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }
    }
}
