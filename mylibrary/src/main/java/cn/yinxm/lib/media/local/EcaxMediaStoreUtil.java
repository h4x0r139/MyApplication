package cn.yinxm.lib.media.local;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import cn.yinxm.lib.utils.log.LogUtil;


/**
 * Created by yinxm on 2017/2/23.
 * 功能: 本地多媒工具类：读取图片，读取音频
 */

public class EcaxMediaStoreUtil {
    private EcaxMediaStoreUtil() {
    }
    private static Object object = new Object();


    /**
     * 读取本地图片列表
     * @param context
     * @return
     */
    public static List<LocalImage> getLocalImgs(Context context) {
        List<LocalImage> list = new ArrayList<>();
        if (context == null) {
            return list;
        }
        try {
            ContentResolver contentResolver = context.getContentResolver();
            if (contentResolver == null) {
                return list;
            }
            String[] projection =
                    {MediaStore.Images.ImageColumns._ID,
                            MediaStore.Images.ImageColumns.DATA,
                            MediaStore.Images.ImageColumns.TITLE,
                            MediaStore.Images.ImageColumns.DISPLAY_NAME,
                            MediaStore.Images.ImageColumns.SIZE,
                            MediaStore.Images.ImageColumns.WIDTH,
                            MediaStore.Images.ImageColumns.HEIGHT
                    };
            synchronized (object) {
                for (int i = 0; i < WorkEcMediaStoreConstant.URI_IMAGES.length; i++) {
                    Uri uri = WorkEcMediaStoreConstant.URI_IMAGES[i];
                    LogUtil.d("uri=" + uri.toString());

                    Cursor cursor = contentResolver.query(uri, projection, null, null, null);
                    if (cursor != null) {
                        LogUtil.d("uri=" + uri.toString() + ", count=" + cursor.getCount());
                        try {
                            while (cursor.moveToNext()) {
                                LocalImage localImage = new LocalImage();
                                localImage._id = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID));
                                localImage._data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));//路径
                                localImage.title = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.TITLE));//名称
                                localImage.display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
                                localImage._size = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.SIZE));//大小
                                localImage.width = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH));
                                localImage.height = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT));
                                list.add(localImage);
                            }
                        } catch (Exception e) {
                            LogUtil.d("getLocalImgs异常" + e.getMessage());
                        } finally {
                            cursor.close();
                        }
                    }

                }
            }

        } catch (Exception e) {
            LogUtil.e("getLocalImgs异常", e);
        } finally {
            return list;
        }
    }
}
