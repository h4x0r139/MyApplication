package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.layout.DragImageView;
import com.tg.jiadeonline.utils.Utils;

public class FangDaActivity extends Activity
{
  private ImageView backImageView;
  private DragImageView dragImageView;
  private int state_height;
  private TextView title_name = null;
  private String url;
  private ViewTreeObserver viewTreeObserver;
  private int window_height;
  private int window_width;

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  public void init()
  {
    this.dragImageView = ((DragImageView)findViewById(2131427888));
    this.title_name = ((TextView)super.findViewById(2131427561));
    this.title_name.setText("图像浏览");
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FangDaActivity.this.finish();
      }
    });
    WindowManager localWindowManager = getWindowManager();
    this.window_width = localWindowManager.getDefaultDisplay().getWidth();
    this.window_height = localWindowManager.getDefaultDisplay().getHeight();
    Utils.loadImage(this.url, this.dragImageView, this);
    this.dragImageView.setmActivity(this);
    this.viewTreeObserver = this.dragImageView.getViewTreeObserver();
    this.viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        if (FangDaActivity.this.state_height == 0)
        {
          Rect localRect = new Rect();
          FangDaActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
          FangDaActivity.this.state_height = (-40 + localRect.top);
          FangDaActivity.this.dragImageView.setScreen_H(FangDaActivity.this.window_height - FangDaActivity.this.state_height);
          FangDaActivity.this.dragImageView.setScreen_W(FangDaActivity.this.window_width);
        }
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903137);
    if (getIntent().hasExtra("url"))
      this.url = getIntent().getStringExtra("url");
    init();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.FangDaActivity
 * JD-Core Version:    0.6.2
 */