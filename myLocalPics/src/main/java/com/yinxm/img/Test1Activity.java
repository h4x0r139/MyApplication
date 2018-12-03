package com.yinxm.img;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Test1Activity extends Activity implements OnClickListener {

    private TextView backTx, sureTx;
    private ImageView imageView;
    private Button getPic, getPic2Cut;
    private final int REQUEST = 11;
    private final int HEAD_IMAGE_ZOOM = 12;
    private Uri uri;
    private String TAG = Test1Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_test1);
        init();
        setclickListener();
    }

    private void init() {
        backTx = (TextView) findViewById(R.id.cancle);
        sureTx = (TextView) findViewById(R.id.ok);
        imageView = (ImageView) findViewById(R.id.image);
        getPic = (Button) findViewById(R.id.get_pic);
        getPic2Cut = (Button) findViewById(R.id.get_pic_to_cut);
    }

    private void setclickListener() {
        backTx.setOnClickListener(this);
        sureTx.setOnClickListener(this);
        getPic.setOnClickListener(this);
        getPic2Cut.setOnClickListener(this);
    }

    private void loadData() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancle:
                finish();
                break;
            case R.id.ok:
                Toast.makeText(Test1Activity.this, "图片已经获取到", Toast.LENGTH_SHORT).show();
                break;
            case R.id.get_pic:
                loadData();
                break;
            case R.id.get_pic_to_cut:
                startPhotoZoom(uri);
                break;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST && resultCode == RESULT_OK) {
            uri = data.getData();


            /**
             * 方法一：
             * 直接使用ImageView的方法设置图片地址URI即可。
             * 不过这种方式当图片很大的时候，容易出现OOM异常，慎用！
             */
            imageView.setImageURI(uri);


            /**
             * 方法二：
             * 根据获取的URI进行查询，获取到图片后进行缩放，不会出现OOM异常
             */
            Cursor cursor = managedQuery(uri, null, null, null, null);
            if (cursor != null) {
                Log.i(TAG, "记录数=" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndexOrThrow(Media.DATA);
                    String path = cursor.getString(index);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    imageView.setImageBitmap(bitmap);
                }
            } else {
                Toast.makeText(Test1Activity.this, "无法获取图片数据", Toast.LENGTH_SHORT).show();
            }

            /**
             * 方法三：
             * 由于managedQuery这个方法已经不再推荐使用
             * 故使用CursorLoader类完成查询操作
             */
            CursorLoader cursorLoader = new CursorLoader(Test1Activity.this,
                    uri, null, null, null, null);
            Cursor cursor2 = cursorLoader.loadInBackground();
            if (cursor2 != null) {
                Log.i(TAG, "记录数=" + cursor2.getCount());
                if (cursor2.moveToFirst()) {
                    int index = cursor2.getColumnIndexOrThrow(Media.DATA);
                    String path = cursor2.getString(index);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                    imageView.setImageBitmap(bitmap);
                }
            } else {
                Toast.makeText(Test1Activity.this, "无法获取图片数据", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == HEAD_IMAGE_ZOOM && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bitmap = bundle.getParcelable("data");
                imageView.setImageBitmap(bitmap);
            } else {
                Toast.makeText(Test1Activity.this, "图片剪切失败", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == HEAD_IMAGE_ZOOM) {
            Toast.makeText(Test1Activity.this, "图片取消剪切", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 调用系统的程序进行图片的剪切操作
     * 获取指定大小的剪切图片，方便做成头像
     *
     * @param uri
     */
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, HEAD_IMAGE_ZOOM);
    }
}
