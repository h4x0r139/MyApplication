package com.yinxm.webview_test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.yinxm.lib.utils.log.LogUtil;

public class WebViewActivity extends AppCompatActivity {

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String URL = "url";
    /**
     * 通过判断url中的参数，来关闭页面
     */
    public static final String CLOSE_URL = "close_url";
    private Toolbar mToolbar;
    private WebView mWebView;

    private String mCloseUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);

        mToolbar = findViewById(R.id.tb_toolbar);

        setSupportActionBar(mToolbar);

        mWebView = findViewById(R.id.webView);

        initWebViewSetting(mWebView);

        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra(URL);
            LogUtil.d("url=" + url);
            mCloseUrl = intent.getStringExtra(CLOSE_URL);
            mWebView.loadUrl(url);
        }
    }

    private void initWebViewSetting(WebView webView) {

        WebSettings webSettings = webView.getSettings();
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        //允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        //禁用放缩
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //10M缓存，api 18后，系统自动管理。
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getApplication().getDir("appcache", 0).getPath());
        //允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        //不保存密码
        webSettings.setSavePassword(false);
        //设置UA
//        webSettings.setUserAgentString(webSettings.getUserAgentString() + " kaolaApp/" + AppUtils.getVersionName());
        //移除部分系统JavaScript接口
//        KaolaWebViewSecurity.removeJavascriptInterfaces(webView);
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);

//        NORMAL：正常显示，没有渲染变化。
//        SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
//        NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        // 设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtil.d("onPageStarted ——》 " + url);
                mToolbar.setTitle(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                LogUtil.d("onPageFinished ——》 " + url);
                if (url != null && mCloseUrl != null && url.startsWith(mCloseUrl)) {
                    Intent intent = getIntent();
                    intent.putExtra(CLOSE_URL, url);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }


        });
        // 设置WebChromeClient类
        webView.setWebChromeClient(new WebChromeClient() {

        });

    }

    /**
     * 返回上一页面而不是退出浏览器
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 销毁WebView
     */
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", DEFAULT_ENCODING, null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
