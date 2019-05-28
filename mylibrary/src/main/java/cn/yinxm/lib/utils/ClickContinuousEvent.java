package cn.yinxm.lib.utils;

import android.os.SystemClock;

/**
 * Created by yinxm on 2017/2/7.
 * 功能: 连续点击事件处理
 */

public class ClickContinuousEvent {
    private int eventDurationTimes = 500;//ms 事件持续有效时间
    private int clickTimes = 2;//点击次数

    private long[] clickEventTimeArray = null;

    public ClickContinuousEvent() {
        this(2);
    }

    public ClickContinuousEvent(int clickTimes) {
        if (clickTimes < 2) {
            clickTimes = 2;
        }
        eventDurationTimes = clickTimes * 300;
        this.clickTimes = clickTimes;
        clickEventTimeArray = new long[clickTimes];
    }

    public void setEventDurationTimes(int eventDurationTimes) {
        if (eventDurationTimes < 10) {//最小分辨10ms
            eventDurationTimes = 10;
        }
        this.eventDurationTimes = eventDurationTimes;
    }

    public void setClickTimes(int clickTimes) {
        this.clickTimes = clickTimes;
    }

    public boolean click() {
        boolean flag = false;
        if (clickEventTimeArray != null) {
            //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于500，即双击
            System.arraycopy(clickEventTimeArray, 1, clickEventTimeArray, 0, clickEventTimeArray.length-1);
            clickEventTimeArray[clickEventTimeArray.length - 1] = SystemClock.uptimeMillis();
            long timeInternal = clickEventTimeArray[clickEventTimeArray.length-1] - clickEventTimeArray[0];
            /*Log.d(TAG, "timeInternal="+timeInternal+", "+ Arrays.toString(clickEventTimeArray));*/
            if (timeInternal > 10 && timeInternal <= eventDurationTimes ) {
                flag = true;
            }
        }
        return flag;
    }
}
