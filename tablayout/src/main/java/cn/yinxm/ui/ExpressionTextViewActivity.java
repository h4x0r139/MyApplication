package cn.yinxm.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;


/**
 * TextView 显示表情
 */
public class ExpressionTextViewActivity extends Activity {
    private TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expression_text_view);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        showExpression1();
        showExpression2();
        showExpression3();
    }

    void showExpression1() {
        ImageSpan imageSpan = new ImageSpan(this, R.drawable.collect);
        SpannableString spannableString = new SpannableString("收藏图标！ ");
        spannableString.setSpan(imageSpan, spannableString.length() - 1, spannableString.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv1.setText(spannableString);
    }


    //环绕效果
    void showExpression2() {
// 设置被环绕的文字
        tv2.setText("=^_^=");

// 为了看到效果而设置一些属性
        tv2.setPadding(20, 10, 20, 10);
        tv2.setBackgroundColor(Color.GRAY);
        tv2.setGravity(Gravity.CENTER);

// 设置环绕的图片
        Drawable d = getResources().getDrawable(R.drawable.collect);
        d.setBounds(0, 0, 60, 60); //必须设置图片大小，否则不显示
        tv2.setCompoundDrawables(d , d, d, d);
    }

    /**
     * SpannableStringBuilder append追加
     */
    void showExpression3() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();

        ImageSpan imageSpan1 = new ImageSpan(this, R.drawable.collect);
        SpannableString spannableString1 = new SpannableString("一棵参天大树！");
        spannableString1.setSpan(imageSpan1, spannableString1.length() - 1, spannableString1.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        ImageSpan imageSpan = new ImageSpan(this, R.drawable.main);
        SpannableString spannableString2 = new SpannableString(" ");
        spannableString2.setSpan(imageSpan, spannableString2.length() - 1, spannableString2.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        spannableStringBuilder.append(spannableString1);
        spannableStringBuilder.append("=^_^=");
        spannableStringBuilder.append(spannableString2);

        tv3.setText(spannableStringBuilder);
    }

    /**
     * 通过Html.ImageGetter 访问assets目录下的图片
     */
    void showExpression4() {
        String html = "(:<img src='main.png'/>AND<img src='main.png'/>:)";
        CharSequence richText = Html.fromHtml(html, assetsImageGetter, null);
        tv4.setText(richText);
    }

    private Html.ImageGetter assetsImageGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            InputStream inputStream = null;
            try {
                inputStream = getAssets().open(source);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                drawable = new BitmapDrawable(bitmap);
                drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                bitmap = null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return drawable;
        }
    };
}
