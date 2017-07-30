package cn.yinxm.lib.utils.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

import cn.yinxm.lib.R;
import cn.yinxm.lib.utils.log.LogUtil;

/**
 * 功能：SoundPool播放器
 * Created by yinxm on 2017/6/17.
 */

public class SoundPoolPlayer {
    public synchronized static SoundPoolPlayer getInstance(Context context) {
        if (instance == null) {
            instance = new SoundPoolPlayer();
        }
        mContext = context;
        return instance;
    }

    //提示音枚举
    public enum SoundPoolRes {
        LV_MSG_ICON_CLICK(R.raw.lv_msg_icon);
//        LV_MSG_START_RECORD(R.raw.lv_msg_start_record),
//        LV_MSG_SEND(R.raw.lv_msg_send),
//        LV_MSG_CANCEL(R.raw.lv_msg_cancel);

        private int mResId;
        SoundPoolRes(int resId) {
            mResId = resId;
        }
        public int getResId() {
            return mResId;
        }
    }

    /**
     * 播放声音
     *
     * @param soudPoolRes
     * @return  playStreamId
     */
    public int play(SoundPoolRes soudPoolRes) {
        LogUtil.d("play soudPoolRes="+soudPoolRes);
        try {
            if (soudPoolRes != null && resSoundIdMap.containsKey(soudPoolRes.getResId())) {
                int soundId = resSoundIdMap.get(soudPoolRes.getResId());
                LogUtil.d("fund soundId="+soundId);
                if (soundId != 0) {
                   return mSoundPlayer.play(soundId, 1, 1, 0, 0, 1);
                }
            }
        }catch (Exception e) {
            LogUtil.e(e);
        }
        return 0;
    }

    /**
     * 停止播放
     */
    public void stop(int playStreamId) {
        try {
            LogUtil.d("stop playStreamId="+playStreamId);
            mSoundPlayer.stop(playStreamId);
        }catch (Exception e) {
            LogUtil.e(e);
        }
    }

    private static SoundPoolPlayer instance;
    private SoundPool mSoundPlayer;
    //key：resId，vlaue：soundId
    private Map<Integer, Integer> resSoundIdMap = new HashMap<>();
    // 上下文
    private static Context mContext;

    private SoundPoolPlayer() {
        mSoundPlayer = new SoundPool(SoundPoolRes.values().length, AudioManager.STREAM_SYSTEM, 0);
        SoundPoolRes[] soundPoolRess = SoundPoolRes.values();
        LogUtil.d("res="+soundPoolRess+", length="+soundPoolRess.length);
        for (SoundPoolRes res : soundPoolRess) {
            int soundId = mSoundPlayer.load(mContext, res.getResId(), 1);// 1
            LogUtil.d("resId="+res.getResId()+", soudId="+soundId);
            resSoundIdMap.put(res.getResId(), soundId);
        }
        LogUtil.d("resSoundIdMap="+resSoundIdMap);
    }
}