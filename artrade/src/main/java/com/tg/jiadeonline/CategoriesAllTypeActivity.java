package com.tg.jiadeonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tg.jiadeonline.adapter.CategoriesAllTypeAdapter;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.utils.Exit;

public class CategoriesAllTypeActivity extends Activity
{
  private XListView alltype_listview;
  private ImageView backImageView;
  private TextView titleName;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903044);
    Exit.getInstance().addActivity(this);
    this.alltype_listview = ((XListView)findViewById(2131427373));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    String str = getIntent().getExtras().getString("titlename");
    this.titleName.setText(str);
    this.alltype_listview.setPullLoadEnable(false);
    this.alltype_listview.setPullRefreshEnable(false);
    this.alltype_listview.setAdapter(new CategoriesAllTypeAdapter(this, this, 5));
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesAllTypeActivity.this.finish();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.CategoriesAllTypeActivity
 * JD-Core Version:    0.6.2
 */