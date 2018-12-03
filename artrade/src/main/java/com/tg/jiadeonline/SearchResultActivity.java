package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.adapter.SearchResultAdapter;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.SearchItemsBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.date.SearchItemsManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.SearchItems;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends Activity
  implements XListView.IXListViewListener
{
  private ImageView backImageView;
  private Context context;
  private int count;
  private String imei = "";
  private TextView jplx_text;
  private XListView listview;
  private SearchResultAdapter mydapter;
  private int pnum = 1;
  private int pqty = 20;
  private String query = "";
  private List<SearchItemsBean> siBeanList;
  private TextView title_name = null;
  private String userId = "";

  private void initDate()
  {
    SearchItems localSearchItems = new SearchItems(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
        SearchResultActivity.this.onLoad();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(SearchResultActivity.this.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        SearchResultActivity.this.onLoad();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str = paramAnonymousJiaDeNetResponse.result.toString();
        if ((!"".equals(str)) && (!"ok".equals(str)))
          Utils.showDialog(SearchResultActivity.this.context, "提示", str, 2130837514, null);
        while (true)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          SearchResultActivity.this.onLoad();
          return;
          SearchItemsManager localSearchItemsManager = new SearchItemsManager(SearchResultActivity.this.context);
          localSearchItemsManager.openDataBase();
          List localList = localSearchItemsManager.fetchAllDatas();
          localSearchItemsManager.closeDataBase();
          if ((localList == null) || (SearchResultActivity.this.pqty > localList.size()))
            SearchResultActivity.this.listview.setPullLoadEnable(false);
          SearchResultActivity localSearchResultActivity = SearchResultActivity.this;
          localSearchResultActivity.pnum = (1 + localSearchResultActivity.pnum);
          if (SearchResultActivity.this.siBeanList.size() == 0)
          {
            SearchResultActivity.this.siBeanList.addAll(localList);
            SearchResultActivity.this.listview.setPullLoadEnable(true);
            SearchResultActivity.this.listview.setPullRefreshEnable(true);
            SearchResultActivity.this.mydapter = new SearchResultAdapter(SearchResultActivity.this.context, SearchResultActivity.this, SearchResultActivity.this.siBeanList);
            SearchResultActivity.this.listview.setAdapter(SearchResultActivity.this.mydapter);
            SearchResultActivity.this.listview.setXListViewListener(SearchResultActivity.this);
          }
          while (true)
          {
            if ((SearchResultActivity.this.siBeanList == null) || (SearchResultActivity.this.siBeanList.size() <= 0))
              break label367;
            SearchResultActivity.this.jplx_text.setVisibility(0);
            SearchResultActivity.this.jplx_text.setText("共搜索出" + ((SearchItemsBean)SearchResultActivity.this.siBeanList.get(0)).getTotal() + "条记录");
            break;
            SearchResultActivity.this.siBeanList.addAll(localList);
            SearchResultActivity.this.mydapter.notifyDataSetChanged();
          }
          label367: SearchResultActivity.this.jplx_text.setVisibility(8);
        }
      }
    }
    , this.context, this.imei, this.userId, this.query, this.pnum, this.pqty, "");
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localSearchItems.execute(new JiaDeNetRequest[0]);
  }

  private void onLoad()
  {
    this.listview.stopRefresh();
    this.listview.stopLoadMore();
    this.listview.setRefreshTime("刚刚");
  }

  public void init()
  {
    this.siBeanList = new ArrayList();
    this.title_name = ((TextView)super.findViewById(2131427561));
    this.title_name.setText("拍品搜索");
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.listview = ((XListView)findViewById(2131427428));
    this.jplx_text = ((TextView)findViewById(2131427424));
    this.query = getIntent().getExtras().getString(CommonRef.querymsg);
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchResultActivity.this.finish();
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      int i = paramIntent.getIntExtra("ppid", -1);
      String str = paramIntent.getStringExtra("jiage");
      if ((this.siBeanList != null) && (this.siBeanList.size() >= i + 1) && (i != -1) && (!"".equals(str)))
      {
        ((SearchItemsBean)this.siBeanList.get(i)).setCurrentBid(str);
        if (this.mydapter != null)
          this.mydapter.notifyDataSetChanged();
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903071);
    Exit.getInstance().addActivity(this);
    this.context = this;
    this.imei = Utils.getPhoneId();
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if ((localList != null) && (localList.size() > 0))
      this.userId = ((LoginBean)localList.get(0)).getUserId();
    init();
    initDate();
  }

  public void onLoadMore()
  {
    initDate();
  }

  public void onRefresh()
  {
    this.pnum = 1;
    this.siBeanList.clear();
    initDate();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.SearchResultActivity
 * JD-Core Version:    0.6.2
 */