package cn.yinxm.tv;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

public class SimpleTextImgActivity extends FragmentActivity {
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_text_img);

        Fresco.initialize(getApplicationContext());

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        initTv1();
//        initTv2();
        initTv3();
    }

    private void initTv1() {
        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.ic_launcher);
        SpannableString spannableString = new SpannableString("默认图片！");
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(spannableString);
    }

    private void initTv2() {
        SpannableString spannableString = new SpannableString("a");//占位符，后续通过setSpan替换
        //方式1 读取图片：
//        ImageSpan imageSpan = new ImageSpan(this, R.mipmap.demo_image);
        //方式2 读取图片：
        Drawable imgDraw = getResources().getDrawable(R.mipmap.demo_image);
        imgDraw.setBounds(10, 10, imgDraw.getIntrinsicWidth(), imgDraw.getIntrinsicHeight());//距离左上边距10像素
        ImageSpan imageSpan = new ImageSpan(imgDraw);
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tv2.setText(spannableString);
    }

    //直接将layout文件中的布局通过TextView展示出来
    private void initTv3() {
        SpannableString spannableString = new SpannableString("a");//占位符，后续通过setSpan替换

//        Drawable imgDraw = getResources().getDrawable(R.layout.item_leave_msg_wall);//报错
        Drawable imgDraw = LayoutToDrawable(R.layout.item_leave_msg_wall);
        Log.d("yinxm", "getIntrinsicWidth=" + imgDraw.getIntrinsicWidth() + ", getIntrinsicHeight=" + imgDraw.getIntrinsicHeight());
        imgDraw.setBounds(10, 10, imgDraw.getIntrinsicWidth(), imgDraw.getIntrinsicHeight());//距离左上边距10像素
        ImageSpan imageSpan = new ImageSpan(imgDraw);
        spannableString.setSpan(imageSpan, 0, 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        tv3.setText(spannableString);
    }

    /**
     * 将布局文件实例化转化为Drawable对象
     * @param layout_id
     * @return
     */
    public Drawable LayoutToDrawable(int layout_id) {

        LayoutInflater inflator = getLayoutInflater();
        View viewHelp = inflator.inflate(layout_id, null);

        //可以对布局中的控件设置具体的值
        TextView textView = (TextView) viewHelp.findViewById(R.id.tv);
//        textView.setText("今天天气很好啊");
//        int size = (int)textView.getText().length();
        Bitmap snapshot = convertViewToBitmap(viewHelp);
        Log.d("yinxm", "snapshot=" + snapshot + ", getWidth=" + snapshot.getWidth() + ", getHeight=" + snapshot.getHeight());

        Drawable drawable =  new BitmapDrawable(snapshot);//注意，bitmap的宽度和高度到Drawable时将根据屏幕密度转化一次，原来300的宽度，在xxhdpi下，变成100，mdpi时不会有变化
        return drawable;
    }

    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        Log.d("yinxm", "view getMeasuredWidth=" + view.getMeasuredWidth() + ", getMeasuredHeight=" + view.getMeasuredHeight());
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());  //根据字符串的长度显示view的宽度时，设置width为text.length*textSizePx
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }
}
