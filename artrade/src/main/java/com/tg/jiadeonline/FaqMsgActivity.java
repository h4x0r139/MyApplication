package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.utils.Exit;

public class FaqMsgActivity extends Activity
{
  Bundle bundle;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903052);
    Exit.getInstance().addActivity(this);
    ((TextView)findViewById(2131427397));
    this.bundle = getIntent().getExtras();
    if ((this.bundle != null) && (this.bundle.containsKey("name")))
      this.bundle.get("name").toString();
    ((ImageView)findViewById(2131427396)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FaqMsgActivity.this.finish();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.FaqMsgActivity
 * JD-Core Version:    0.6.2
 */