package com.tg.jiadeonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.utils.Exit;

public class FAQToPayActivity extends Activity
{
  private ImageView backImageView;
  private TextView titleName;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903053);
    Exit.getInstance().addActivity(this);
    this.titleName = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName.setText("FAQ");
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FAQToPayActivity.this.finish();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.FAQToPayActivity
 * JD-Core Version:    0.6.2
 */