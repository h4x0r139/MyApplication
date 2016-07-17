package com.tg.jiadeonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.utils.Exit;

public class CustomerActivity extends Activity
{
  private ImageView backImageView;
  private TextView titleName;

  public void init()
  {
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.titleName.setText("客服留言");
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CustomerActivity.this.finish();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903051);
    Exit.getInstance().addActivity(this);
    init();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.CustomerActivity
 * JD-Core Version:    0.6.2
 */