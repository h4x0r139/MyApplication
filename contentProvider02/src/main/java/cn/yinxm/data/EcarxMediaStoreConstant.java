package cn.yinxm.data;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by yinxm on 2017/2/23.
 * 功能: 媒体数据库枚举
 */

public class WorkEcMediaStoreConstant {

    private static final String CAR_SD_CARD = "external_sd";//外置sd卡
    private static final String CAR_UDISK = "udisk";//挂载的USB接口
    private static final String CAR_UDISK1 = "udisk1";
    private static final String CAR_UDISK2 = "udisk2";

    //图片地址
    public static Uri [] URI_IMAGES = {
        MediaStore.Images.Media.INTERNAL_CONTENT_URI,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        MediaStore.Images.Media.getContentUri(CAR_SD_CARD),
        MediaStore.Images.Media.getContentUri(CAR_UDISK),
        MediaStore.Images.Media.getContentUri(CAR_UDISK1),
        MediaStore.Images.Media.getContentUri(CAR_UDISK2),
    };

    //音频地址
    public static Uri [] URI_AUDIO = {
        MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        MediaStore.Audio.Media.getContentUri(CAR_SD_CARD),
        MediaStore.Audio.Media.getContentUri(CAR_UDISK),
        MediaStore.Audio.Media.getContentUri(CAR_UDISK1),
        MediaStore.Audio.Media.getContentUri(CAR_UDISK2),
    };
}
