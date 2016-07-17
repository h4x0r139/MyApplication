package com.tg.jiadeonline;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.adapter.QueryResultAdapter;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.utils.Exit;

public class QueryReslutActivity extends Activity
  implements XListView.IXListViewListener
{
  private ImageView backImage;
  private XListView query_result_listview;
  private TextView titleName;

  private void onLoad()
  {
    this.query_result_listview.stopRefresh();
    this.query_result_listview.stopLoadMore();
    this.query_result_listview.setRefreshTime("刚刚");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903067);
    Exit.getInstance().addActivity(this);
    this.query_result_listview = ((XListView)findViewById(2131427499));
    this.query_result_listview.setAdapter(new QueryResultAdapter(this, this, 6));
    this.backImage = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.titleName.setText("搜索结果");
    this.backImage.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        QueryReslutActivity.this.finish();
      }
    });
  }

  public void onLoadMore()
  {
    this.query_result_listview.setAdapter(new QueryResultAdapter(this, this, 7));
    onLoad();
  }

  public void onRefresh()
  {
    this.query_result_listview.setAdapter(new QueryResultAdapter(this, this, 6));
    onLoad();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.QueryReslutActivity
 * JD-Core Version:    0.6.2
 */