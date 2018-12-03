package com.yika.viewpager_play;

import java.io.Serializable;


/**
 * @Description: 音频播放实体
 * Author: yinxm
 * Date: 2016/5/2 19:04
 */
public class AudioBean implements Serializable {
    private static final long serialVersionUID = -7110768193221076285L;

    public AudioBean() {}

    public AudioBean(String aid, String artist, String title, String playurl) {
        this.aid = aid;
        this.artist = artist;
        this.title = title;
        this.playurl = playurl;
    }

    //必须数据
    public String aid;  //音频id，【必须】，目前workEc server用于唯一标识一首音频，但是app用aid和albumId唯一标识一首音频
    public String albumId;//专辑id
    public String albumName;//专辑名称
    public String artist;//作者，【必须】
    public String title;//标题，【必须】
    public String playurl;//播放地址，【必须】,广播的回放地址
    public String cover;//封面,音频logo
    public int duration;//总时长（单位毫秒mS）
    public int currentPlayTime;//当前播放长度（单位毫秒mS）
    public String categoryId;//分类id, 当酷我音乐为搜索出来的是默认为AudioBeanFormat.KUWO_CATEGROY_ID
    public String categoryName;//分类名称
    public String description;//描述，最长40个汉字
    //    public String from = AudioBeanFormat.FROM_KAOLA;//音频来源：kaola【默认】,
//    AudioBeanFormat.FROM_KAOLA = "0";//考拉
//    AudioBeanFormat.FROM_KUWO = "1";//酷我
//    AudioBeanFormat.FROM_BROADCAST = "2";//广播
//    AudioBeanFormat.FROM_LOCAL = "10";//本地
//    AudioBeanFormat.FROM_CUSTOM_BROADCAST;//自制电台频道，相当于考拉广播
//    AudioBeanFormat.FROM_CUSTOM_PROGRAM;//自制节目，相当于考拉电台
    public String from_type;//音频类型来源，AudioBeanFormat.AudioFromType
    public String local_playUrl;//本地音乐播放路径
    public boolean isSlected;//是否选中


    //非必须数据
    public String playfrom;
    public String shareurl;
    public int id;//自增主键
    public String publishDate;//更新时间
    public int times;//过滤次数

    public int number;//期数
    public int listenNum;//收听量
    public int likeNum;//喜欢量

    public boolean isCheck = false;//音乐是否被选中
    public long down_progress;//下载进度字段
    public int downloadstate = 0;//下载状态   0未下载 1正在下载 2下载完成 3 暂停下载

    //广播独有
    public String broadcastId;//广播id
    public String livePlayUrl;//直播地址，对于广播，playUrl默认为回放地址
    public boolean notHaveProgram = false;//是否有节目单，默认都有。
    public String beginTime;//00:00:00
    public String endTime;//01:59:59
    public long startTime;//1481644800000
    public long finishTime;//1481651999000

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AudioBean{");
        sb.append("aid='").append(aid).append('\'');
        sb.append(", albumId='").append(albumId).append('\'');
        sb.append(", albumName='").append(albumName).append('\'');
        sb.append(", artist='").append(artist).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", playurl='").append(playurl).append('\'');
        sb.append(", cover='").append(cover).append('\'');
        sb.append(", duration=").append(duration);
        sb.append(", currentPlayTime=").append(currentPlayTime);
        sb.append(", categoryId='").append(categoryId).append('\'');
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", description='").append(description).append('\'');
//        sb.append(", from='").append(from).append('\'');
        sb.append(", from_type='").append(from_type).append('\'');
        sb.append(", local_playUrl='").append(local_playUrl).append('\'');
        sb.append(", isSlected=").append(isSlected);
        sb.append(", broadcastId='").append(broadcastId).append('\'');
        sb.append(", playfrom='").append(playfrom).append('\'');
        sb.append(", shareurl='").append(shareurl).append('\'');
        sb.append(", id=").append(id);
        sb.append(", publishDate='").append(publishDate).append('\'');
        sb.append(", times=").append(times);
        sb.append(", number=").append(number);
        sb.append(", listenNum=").append(listenNum);
        sb.append(", likeNum=").append(likeNum);
        sb.append(", isCheck=").append(isCheck);
        sb.append(", down_progress=").append(down_progress);
        sb.append(", downloadstate=").append(downloadstate);
        sb.append(", livePlayUrl='").append(livePlayUrl).append('\'');
        sb.append(", notHaveProgram=").append(notHaveProgram);
        sb.append(", beginTime='").append(beginTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", startTime=").append(startTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append('}');
        return sb.toString();
    }
}
