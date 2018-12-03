package cn.yinxm.lib.interfaces;

import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.utils.log.LogUtil;


/**
 * Created by yinxm on 2016/11/19.
 * 功能: 音频临时暂停、恢复播放管理器
 * 暂停通常是由于外部原因
 */

public class AudioTempPauseManager {
    private static final int MUSIC_PAUSE_SERVICE = 0;//直接影响PlayService播放状态
    private static final int MUSIC_PAUSE_MEDIAPLAYER = 1;//不影PlayService响播放状态，只是改变MediaPlayer播放状态
    //暂停模式
    private static final int MUSIC_PAUSE_MODE = MUSIC_PAUSE_SERVICE;

    private IAudioPlayController playController;
    private MediaPlayer musicMedia;

    private int pauseId = 0;
    private Map<Integer, String> musicTempPauseMap = new HashMap<>();//音乐临时暂停
    private AudioTempPauseManager(){}
    public static AudioTempPauseManager getInstance() {
        return AudioTempPauseManagerFactory.instance;
    }
    private static class AudioTempPauseManagerFactory {
        private static AudioTempPauseManager instance = new AudioTempPauseManager();
    }
//
//    /**
//     *
//     * @param pauseEventName    暂停的原因，通常写对应的Activity Name
//     * @return pauseId  用于恢复音频的唯一标识
//     */
//    public int pauseAllAudio(String pauseEventName){
//
//        pauseId += 1;
//        return pauseId;
//    }
//
//    public void resumeAllAudio(int pauseId) {
//
//    }

    /**
     *  暂停播放
     * @param pauseEventName 用于标识本次暂停事件场景唯一性，通常写为XXX.class.getCanonicalName()，例如：ChatActivity.class.getCanonicalName()，
     *                       特别的，在一个场景中有多个不同事件，需要加额外的标识符例如：ChatActivity.class.getCanonicalName()+"_playIM"; ChatActivity.class.getCanonicalName()+"_recordVoice"
     * @return pauseId 用于下一次恢复的凭证
     */
    public int pauseMusic(String pauseEventName){
        LogUtil.d("[AudioTempPauseManager.pauseMusic] pauseEventName="+pauseEventName +", old-pauseId="+pauseId+", musicTempPauseMap="+musicTempPauseMap);
        pauseMusicLogic(pauseEventName);
        LogUtil.d("[AudioTempPauseManager.pauseMusic] new-pauseId="+pauseId +", musicTempPauseMap="+musicTempPauseMap);
        return pauseId;
    }



    /**
     * 恢复音乐播放
     * @param pauseId 恢复id
     */
    public void resumeMusic(int pauseId) {
        LogUtil.d("[AudioTempPauseManager.resumeMusic] pauseId="+pauseId+", musicTempPauseMap="+musicTempPauseMap );
        if (musicTempPauseMap.containsKey(pauseId)) {
            LogUtil.d("找到pauseId="+pauseId+"暂停事件");
            musicTempPauseMap.remove(pauseId);
            if (musicTempPauseMap.isEmpty()) {
                LogUtil.d("没有暂停音乐的事件了，恢复播放");
                continueMusicLogic();
            }
        } else {
            LogUtil.d("无pauseId="+pauseId+"暂停事件");
        }
        LogUtil.d("[AudioTempPauseManager.resumeMusic] musicTempPauseMap="+musicTempPauseMap );
    }

    public void clearTempPauseMusicEvent(int pauseId){
        LogUtil.d("[AudioTempPauseManager.clearTempPauseMusicEvent] pauseId="+pauseId+", musicTempPauseMap="+musicTempPauseMap );
        musicTempPauseMap.remove(pauseId);
    }

    public void clearAllTempPauseMusicEvent() {
        LogUtil.d("[AudioTempPauseManager.clearAllTempPauseMusicEvent] pauseId="+pauseId+", musicTempPauseMap="+musicTempPauseMap );
        musicTempPauseMap.clear();
    }

    public boolean clearThisEvent(String pauseEventName) {
        LogUtil.d("[AudioTempPauseManager.clearThisEvent] pauseEventName="+pauseEventName);
        boolean flag = false;
        Iterator<Map.Entry<Integer, String>>  iterator = musicTempPauseMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            LogUtil.d(entry.getKey()+"="+entry.getValue());
            if (StringUtil.isNotBlank(pauseEventName) && pauseEventName.equals(entry.getValue())) {
                LogUtil.e("[AudioTempPauseManager.clearThisEvent] same pauseEventName pauseId="+entry.getKey());
                iterator.remove();
                flag = true;
            }
        }
        return  flag;
    }

    public boolean isMusicPausedByOther(){
        LogUtil.d("[AudioTempPauseManager.isMusicPausedByOther] pauseId="+pauseId+", musicTempPauseMap="+musicTempPauseMap );
        boolean flag = false;
        if (!musicTempPauseMap.isEmpty()) {
            flag = true;
        }
        LogUtil.d("[AudioTempPauseManager.isMusicPausedByOther] flag="+flag);
        return flag;
    }

    //恢复音乐播放具体逻辑
    private void pauseMusicLogic(String pauseEventName){
        boolean isSamePauseEventBefore = clearThisEvent(pauseEventName);
        LogUtil.d("pauseMusicLogic MUSIC_PAUSE_MODE="+MUSIC_PAUSE_MODE+", isSamePauseEventBefore="+isSamePauseEventBefore);
        if (MUSIC_PAUSE_MODE == MUSIC_PAUSE_SERVICE) {
            //Service 暂停播放
            LogUtil.d("AudioPlayService暂停播放，playController="+playController);
            if (playController != null && playController.isPlaying()) {
                LogUtil.d("isPlaying");
                pauseId += 1;
                playController.pausePlay();
                musicTempPauseMap.put(pauseId, pauseEventName);
            } else {
                LogUtil.d("no need pause");
                if (!musicTempPauseMap.isEmpty() || (musicTempPauseMap.isEmpty() && isSamePauseEventBefore)) {
                    LogUtil.d("pause by other or isSamePauseEventBefore="+isSamePauseEventBefore);
                    pauseId += 1;
                    musicTempPauseMap.put(pauseId, pauseEventName);
                }
            }
        } else {
            // TODO: 2016/12/3 待测试
            //MediaPlayer 暂停播放
            LogUtil.d("MediaPlayer 暂停播放，musicMedia="+musicMedia);
            if (musicMedia != null) {
                try {
                    if (musicMedia.isPlaying()) {
                        LogUtil.d("暂停播放start");
                        musicMedia.pause();
                        pauseId += 1;
                        musicTempPauseMap.put(pauseId, pauseEventName);
                        LogUtil.d("暂停播放end");
                    } else {
                        LogUtil.e("MediaPlayer 没有播放，不需要暂停");
                        if (!musicTempPauseMap.isEmpty() || (musicTempPauseMap.isEmpty() && isSamePauseEventBefore)) {
                            LogUtil.d("pause by other or isSamePauseEventBefore="+isSamePauseEventBefore);
                            pauseId += 1;
                            musicTempPauseMap.put(pauseId, pauseEventName);
                        }
                    }
                } catch (Exception e) {
                    LogUtil.e("continueMusicLogic MediaPlayer恢复播放异常",e);
                }
            }
        }
    }

    //恢复音乐播放具体逻辑
    private void continueMusicLogic() {
        LogUtil.d("continueMusicLogic MUSIC_PAUSE_MODE="+MUSIC_PAUSE_MODE);
        if (MUSIC_PAUSE_MODE == MUSIC_PAUSE_SERVICE) {
            //Service 恢复
            LogUtil.d("AudioPlayService 恢复播放，playController="+playController);
            if (playController != null) {
                playController.continuePlay();
            }
        } else {
            //MediaPlayer 恢复
            LogUtil.d("MediaPlayer 恢复播放，musicMedia="+musicMedia);
            if (musicMedia != null) {
                try {
                    if (musicMedia.isPlaying()) {
                        LogUtil.e("MediaPlayer 已经在播放了，不需要恢复");
                    } else {
                        LogUtil.d("恢复播放start");
                        musicMedia.start();
                        LogUtil.d("恢复播放end");
                    }
                } catch (Exception e) {
                    LogUtil.e("continueMusicLogic MediaPlayer恢复播放异常",e);
                }
            }
        }
    }

    public void setPlayController(IAudioPlayController playController) {
        this.playController = playController;
    }

    public void setMusicMedia(MediaPlayer musicMedia) {
        this.musicMedia = musicMedia;
    }
}
