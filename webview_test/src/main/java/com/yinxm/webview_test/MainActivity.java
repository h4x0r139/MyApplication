package com.yinxm.webview_test;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {
    private WebView mwebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mwebView = (WebView) findViewById(R.id.webView);
//        NORMAL：正常显示，没有渲染变化。
//        SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
//        NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。
        mwebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mwebView.loadUrl("http://m.pahaoche.com/quanguo/");
    }
}
