package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unionpay.mobile.android.languages.c;
import java.util.ArrayList;
import java.util.Timer;

public final class d extends WebView
  implements Handler.Callback
{
  private WebSettings a = null;
  private Handler b = null;
  private a c = null;
  private Timer d = new Timer();
  private boolean e = false;
  private ArrayList<String> f = null;

  public d(Context paramContext, a parama)
  {
    super(paramContext);
    this.c = parama;
    setScrollBarStyle(33554432);
    this.a = getSettings();
    this.a.setJavaScriptEnabled(true);
    setWebChromeClient(new c((byte)0));
    setWebViewClient(new d((byte)0));
  }

  private final void a()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = c.by.t;
    loadData(String.format("<div align=\"center\">%s</div>", arrayOfObject), "text/html", "utf-8");
  }

  public final void a(String paramString)
  {
    Message localMessage = this.b.obtainMessage(0);
    localMessage.obj = paramString;
    this.b.sendMessage(localMessage);
  }

  public final boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 3:
    case 1:
    case 2:
    case 4:
    }
    do
    {
      do
      {
        return true;
        if (this.c != null)
          this.c.r();
        String str = "";
        if (paramMessage.obj != null)
          str = (String)paramMessage.obj;
        Log.e("uppay", "url = " + str);
        loadUrl(str);
        return true;
        a();
        if (paramMessage.what == 1)
          this.e = true;
      }
      while (this.c == null);
      this.c.s();
      return true;
    }
    while ((this.c == null) || (!(this.c instanceof b)));
    return true;
  }

  public static abstract interface a
  {
    public abstract void r();

    public abstract void s();
  }

  public static abstract interface b extends d.a
  {
  }

  private final class c extends WebChromeClient
  {
    private c()
    {
    }

    public final void onProgressChanged(WebView paramWebView, int paramInt)
    {
      if (paramInt == 100)
        d.a(d.this).sendEmptyMessage(1);
    }
  }

  private final class d extends WebViewClient
  {
    private d()
    {
    }

    public final void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      d.c(d.this).cancel();
      d.c(d.this).purge();
    }

    public final void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      d.a(d.this, new Timer());
      e locale = new e(this);
      d.c(d.this).schedule(locale, 30000L);
    }

    public final void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      d.d(d.this);
    }

    public final boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      String str;
      if ((d.e(d.this) == null) || (d.e(d.this).size() == 0))
        str = null;
      while (true)
      {
        if (str != null)
        {
          Message localMessage = d.a(d.this).obtainMessage(4);
          localMessage.obj = paramString;
          d.a(d.this).sendMessage(localMessage);
          return true;
          if ((paramString != null) && (paramString.length() > 0))
            for (int i = 0; ; i++)
            {
              if ((d.e(d.this) == null) || (i >= d.e(d.this).size()))
                break label156;
              if (paramString.startsWith((String)d.e(d.this).get(i)))
              {
                str = (String)d.e(d.this).get(i);
                break;
              }
            }
        }
        else
        {
          paramWebView.loadUrl(paramString);
          return true;
        }
        label156: str = null;
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.upviews.d
 * JD-Core Version:    0.6.2
 */