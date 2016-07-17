package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.bean.PaySucItemBean;
import com.tg.jiadeonline.utils.Exit;

public class LogisticsActivity extends Activity
{
  private ImageView backImageView;
  private PaySucItemBean bean;
  private TextView title_name = null;
  private WebView webview;
  private TextView wl_tv_gs;
  private TextView wl_tv_je;
  private TextView wl_tv_name;
  private TextView wl_tv_wldh;

  public void init()
  {
    this.wl_tv_wldh = ((TextView)findViewById(2131427417));
    this.wl_tv_wldh.setText(this.bean.getTransportID());
    this.wl_tv_name = ((TextView)findViewById(2131427415));
    this.wl_tv_name.setText(this.bean.getTitle());
    this.wl_tv_gs = ((TextView)findViewById(2131427413));
    this.wl_tv_gs.setText(this.bean.getCarrier());
    this.wl_tv_je = ((TextView)findViewById(2131427416));
    this.wl_tv_je.setText(this.bean.getPriceBid());
    this.webview = ((WebView)findViewById(2131427418));
    this.webview.getSettings().setJavaScriptEnabled(false);
    this.webview.loadUrl("http://wap.kuaidi100.com/wap_result.jsp?rand=20120517&id=" + this.bean.getCarrier() + "&fromWeb=null&&postid=" + this.bean.getTransportID());
    this.webview.setWebViewClient(new MyWebViewClient());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903059);
    Exit.getInstance().addActivity(this);
    this.title_name = ((TextView)super.findViewById(2131427561));
    this.title_name.setText("物流详情");
    if (getIntent().hasExtra("tite"))
    {
      this.bean = ((PaySucItemBean)getIntent().getSerializableExtra("tite"));
      init();
    }
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LogisticsActivity.this.finish();
      }
    });
  }

  private class MyWebViewClient extends WebViewClient
  {
    public MyWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      paramWebView.loadUrl(paramString);
      return true;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.LogisticsActivity
 * JD-Core Version:    0.6.2
 */