package com.yinxm.img;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

import static java.lang.System.getenv;

public class Test2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        getImagesInfo();
        testEcarx();
        getStoragePath(this, true);

        Log.d("yinxm", ""+EcaxMediaStoreUtil.getLocalImgs(this));
    }

    public void testEcarx() {
        //内置sd卡路径
        String sdcardPath = getenv("EXTERNAL_STORAGE");
        Log.d("yinxm", "sdcardPath1="+sdcardPath);//    /storage/emulated/legacy
        //内置sd卡路径
        sdcardPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.d("yinxm", "sdcardPath2="+sdcardPath);//    /storage/emulated/0
        //外置置sd卡路径
        String extSdcardPath = getenv("SECONDARY_STORAGE");
        Log.d("yinxm", "extSdcardPath="+extSdcardPath);//    /storage/sdcard1

//        StorageManager.

        Log.d("yinxm", "MediaStore.Audio.Media.INTERNAL_CONTENT_URI="+MediaStore.Audio.Media.INTERNAL_CONTENT_URI.toString());
        Log.d("yinxm", "MediaStore.Audio.Media.EXTERNAL_CONTENT_URI="+MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString());
        Log.d("yinxm", "MediaStore.Images.Media.INTERNAL_CONTENT_URI="+ MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());
        Log.d("yinxm", "MediaStore.Images.Media.EXTERNAL_CONTENT_URI="+MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());


        ContentResolver resolver = getContentResolver();
        Log.d("yinxm", "getMediaScannerUri="+MediaStore.getMediaScannerUri()
                +"， EXTERNAL_CONTENT_URI="+MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                +", "+MediaStore.Audio.Media.getContentUri("external")
                );
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,null,null);
        Log.d("yinxm", "cursor1="+cursor);
        cursor = resolver.query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, null, null,null,null);
        if (cursor != null) {
            Log.d("yinxm", "cursor2="+cursor+", count="+cursor.getCount()+",  getColumnNames="+ Arrays.toString(cursor.getColumnNames()));
        }

        cursor = resolver.query(MediaStore.Audio.Media.getContentUriForPath("external"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor3="+cursor+", count="+cursor.getCount()+", path="+MediaStore.Audio.Media.getContentUriForPath("external").getPath()
            +", "+MediaStore.Audio.Media.getContentUriForPath("external").toString());

        cursor = resolver.query(MediaStore.Audio.Media.getContentUri("external"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor4="+cursor+", count="+cursor.getCount()+", path="+MediaStore.Audio.Media.getContentUri("external").getPath()
            +", "+MediaStore.Audio.Media.getContentUri("external").toString());

        cursor = resolver.query(MediaStore.Audio.Media.getContentUri("external_sd"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor5="+cursor+", count="+cursor.getCount()+", path="+MediaStore.Audio.Media.getContentUri("external").getPath()
            +", "+MediaStore.Audio.Media.getContentUri("external").toString());
        cursor = resolver.query(MediaStore.Audio.Media.getContentUriForPath("internal"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor6="+cursor+", count="+cursor.getCount()+", "+MediaStore.Audio.Media.getContentUriForPath("internal").toString());

        cursor = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor7="+cursor+", count="+cursor.getCount()+", "+MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
        cursor = resolver.query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor8="+cursor+", count="+cursor.getCount()+", "+MediaStore.Images.Media.INTERNAL_CONTENT_URI.toString());

        cursor = resolver.query(MediaStore.Images.Media.getContentUri("external"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor9="+cursor+", count="+cursor.getCount()+", "+MediaStore.Images.Media.getContentUri("external").toString());

        cursor = resolver.query(MediaStore.Images.Media.getContentUri("external-1"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor10="+cursor+", count="+cursor.getCount()+", "+MediaStore.Images.Media.getContentUri("external").toString());

        cursor = resolver.query(MediaStore.Images.Media.getContentUri("external_sd"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor11="+cursor+", count="+cursor.getCount()+", "+MediaStore.Images.Media.getContentUri("external_sd").toString());

        cursor = resolver.query(Uri.parse("content://media/external_sd"), null, null,null,null);
        if (cursor != null)
            Log.d("yinxm", "cursor12="+cursor+", count="+cursor.getCount()+", "+MediaStore.Images.Media.getContentUri("external_sd").toString());
    }

    /**
     * 遍历sd卡路径
     * @param mContext
     * @param is_removale
     * @return
     */
    private static String getStoragePath(Context mContext, boolean is_removale) {
        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                Log.d("yinxm", "path="+path+", removable="+removable);
                if (is_removale == removable) {
                    return path;
                }
            }
//车机
//        path=/storage/emulated/0, removable=false
//        path=/storage/sdcard1, removable=false
//        path=/storage/udisk, removable=true
//手机
//        path=/storage/emulated/0, removable=false
//        path=/storage/sdcard1, removable=true

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void getImagesInfo() {
        ContentResolver resolver = getContentResolver();
        // MediaStore.Images.Media.EXTERNAL_CONTENT_URI可以让用户访问SD卡

        String[] projection = {
                Images.Media._ID,
                Images.Media.DATA,
                Images.Media.WIDTH,
                Images.Media.HEIGHT,
                Images.Media.SIZE
        };
        Cursor c = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);//content://media/external/images/media
        Log.d("yinxm", "cursor="+c+", uri="+MediaStore.Images.Media.EXTERNAL_CONTENT_URI+", realpath="+MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
//        if (c == null) {
//            c = resolver.query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null, null, null);
//            Log.d("yinxm", "cursor="+c+", uri="+ Images.Media.INTERNAL_CONTENT_URI);
//        } else {
//            Log.d("yinxm", "count="+c.getCount());
//        }
        if (c == null) {
//            c = resolver.query(MediaStore.Images.Media.getContentUri("ecarx"), projection, null, null, null);
//            Log.d("yinxm", "cursor="+c+", url="+ MediaStore.Images.Media.getContentUri("ecarx"));
//            Uri uri = Uri.parse("content://media");
            Uri uri = Uri.parse("content://media/external/audio/albumart");
            Log.d("yinxm", "realpath="+uri.getPath());
            c = resolver.query(uri, null, null, null, null);
//            c = resolver.query(uri, projection, null, null, null);
            Log.d("yinxm", "cursor="+c+", uri="+ uri);
            if (c != null) {
                Log.d("yinxm", "count="+c.getCount());
            }
        } else {
            Log.d("yinxm", "count="+c.getCount());
        }

        while(c != null && c.moveToNext()){
            int id = c.getInt(c.getColumnIndex(Images.Media._ID));      // 通过列的索引拿到ID的值
            String path = c.getString(c.getColumnIndex(Images.Media.DATA));
            double width = c.getDouble(c.getColumnIndex(Images.Media.WIDTH));
            double height = c.getDouble(c.getColumnIndex(Images.Media.HEIGHT));
            double size = c.getDouble(c.getColumnIndex(Images.Media.SIZE));
            StringBuilder sb = new StringBuilder();
            sb.append("id=").append(id)
                    .append(",path=").append(path)
                    .append(",width=").append(width)
                    .append(",height=").append(height)
                    .append(",size=").append(size);
            Log.i("yinxm", sb.toString());
        }
    }
}
