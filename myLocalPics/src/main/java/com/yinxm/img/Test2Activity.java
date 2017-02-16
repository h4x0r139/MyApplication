package com.yinxm.img;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Test2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        getImagesInfo();
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
        Cursor c = resolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        Log.d("yinxm", "cursor="+c+", uri="+MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        if (c == null) {
//            c = resolver.query(MediaStore.Images.Media.INTERNAL_CONTENT_URI, projection, null, null, null);
//            Log.d("yinxm", "cursor="+c+", uri="+ Images.Media.INTERNAL_CONTENT_URI);
//        } else {
//            Log.d("yinxm", "count="+c.getCount());
//        }
        if (c == null) {
//            c = resolver.query(MediaStore.Images.Media.getContentUri("ecarx"), projection, null, null, null);
//            Log.d("yinxm", "cursor="+c+", url="+ MediaStore.Images.Media.getContentUri("ecarx"));
            Uri uri = Uri.parse("content://com.ecarx.providers.media");
            c = resolver.query(uri, projection, null, null, null);
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
