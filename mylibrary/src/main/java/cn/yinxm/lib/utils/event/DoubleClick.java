package cn.yinxm.lib.utils.event;

/**
 * 功能：判断是否是双击，重复点击
 * Created by yinxm on 2017/8/3.
 */

public class DoubleClick {
    //判断两次点击为双击的间隔
    private int mInterval;
    //最后一次点击时间
    private long lastClickTime;

    public DoubleClick(int interval) {
        if (interval > 0) {
            mInterval = interval;
        } else {
            mInterval = 500;//默认500ms
        }
    }

    /**
     * 是否是双击、重复点击
     * @return
     */
    public boolean clickIsDouble() {
        boolean flag = false;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime <= mInterval) {
            flag = true;
        }
        lastClickTime = currentTime;
        return flag;
    }

    /**
     * 是否是双击、重复点击
     * @return
     */
    public boolean clickIsNotDouble() {
        return !clickIsDouble();
    }
}
