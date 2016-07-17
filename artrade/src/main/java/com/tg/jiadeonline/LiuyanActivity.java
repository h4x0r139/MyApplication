package com.tg.jiadeonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.utils.Exit;

public class LiuyanActivity extends Activity
{
  private ImageView backImageView;
  private TextView title_name = null;

  public void init()
  {
    this.title_name = ((TextView)super.findViewById(2131427561));
    this.title_name.setText("客户留言");
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LiuyanActivity.this.finish();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903040);
    Exit.getInstance().addActivity(this);
    init();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.LiuyanActivity
 * JD-Core Version:    0.6.2
 */