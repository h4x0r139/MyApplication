package com.tg.jiadeonline.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.CategoriesAllTypeActivity;
import com.tg.jiadeonline.CategoriesTypeListActivity;
import com.tg.jiadeonline.adapter.CategoriesAdapter;
import com.tg.jiadeonline.base.BaseActivity;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.bean.ChannelListBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.ChannelList1Manager;
import com.tg.jiadeonline.date.ChannelListManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.ChannelList;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends BaseActivity
  implements XListView.IXListViewListener
{
  private static int pnum = CommonRef.pnum;
  private CategoriesAdapter aaaImageListAdapter;
  private List<ChannelList1Bean> beanlists;
  private String cateId = "1";
  private TextView curTextView;
  private TextView dangdaigongyi;
  private TableLayout fl_biaoti;
  private TextView gucizaxiang;
  private String imei;
  boolean isFirst = true;
  private boolean ischeck = true;
  private List<ChannelList1Bean> item = new ArrayList();
  private LinearLayout layoit_tishi;
  private ListView listview;
  Handler mHandler = new Handler();
  private LinearLayout moreLinearLayout;
  private String porder = "";
  private int pqty = CommonRef.pqty;
  private ScrollView scrollview;
  private TextView shufazhuanke;
  private int start = CommonRef.start;
  private List<ChannelListBean> titebeanlist;
  private String userId = "";
  private TextView xihuadiaosu;
  private TextView yishuping;
  private TextView zhongguohuihua;

  private void checkTab(TextView paramTextView)
  {
    if (paramTextView == this.curTextView)
      return;
    paramTextView.setTextColor(getResources().getColor(2131034115));
    paramTextView.setBackgroundColor(getResources().getColor(2131034135));
    this.curTextView.setTextColor(getResources().getColor(2131034114));
    this.curTextView.setBackgroundColor(getResources().getColor(2131034139));
    this.curTextView = paramTextView;
    onRefresh();
  }

  private void geneItems()
  {
    if ((this.beanlists != null) && (this.beanlists.size() > 0))
    {
      this.item.clear();
      this.item.addAll(this.beanlists);
    }
  }

  private void getBeanList(final int paramInt)
  {
    if (paramInt == CommonRef.getBeanListtype_refresh)
    {
      this.start = CommonRef.start;
      pnum = CommonRef.pnum;
    }
    if (paramInt == CommonRef.getBeanListtype_addmore)
    {
      if (((pnum == 0) || (this.item.size() < pnum * this.pqty)) && ((pnum != 0) || (this.item.size() < this.pqty)))
        break label144;
      pnum = 1 + pnum;
    }
    while (true)
    {
      this.start = (pnum * this.pqty);
      ChannelList localChannelList = new ChannelList(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          if (CategoriesActivity.this.item.size() > 0)
            Utils.showDialog(CategoriesActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          ChannelListManager localChannelListManager = new ChannelListManager(CategoriesActivity.this);
          localChannelListManager.openDataBase();
          localChannelListManager.fetchAllDatas();
          CategoriesActivity.this.titebeanlist = null;
          CategoriesActivity.this.titebeanlist = new ArrayList();
          CategoriesActivity.this.titebeanlist = localChannelListManager.fetchAllDatas();
          localChannelListManager.closeDataBase();
          CategoriesActivity.this.settite();
          ChannelList1Manager localChannelList1Manager = new ChannelList1Manager(CategoriesActivity.this);
          localChannelList1Manager.openDataBase();
          List localList = localChannelList1Manager.fetchAllDatas();
          localChannelList1Manager.closeDataBase();
          CategoriesActivity.this.beanlists = localList;
          if (paramInt == CommonRef.getBeanListtype_first)
          {
            CategoriesActivity.this.geneItems();
            CategoriesActivity.this.scrollview = null;
            CategoriesActivity.this.scrollview = ((ScrollView)CategoriesActivity.this.findViewById(2131427369));
            CategoriesActivity.this.fl_biaoti = null;
            CategoriesActivity.this.fl_biaoti = ((TableLayout)CategoriesActivity.this.findViewById(2131427370));
            CategoriesActivity.this.listview = null;
            CategoriesActivity.this.listview = ((ListView)CategoriesActivity.this.findViewById(2131427372));
            CategoriesActivity.this.aaaImageListAdapter = null;
            CategoriesActivity.this.aaaImageListAdapter = new CategoriesAdapter(CategoriesActivity.this, CategoriesActivity.this, CategoriesActivity.this.item);
            CategoriesActivity.this.listview.setAdapter(CategoriesActivity.this.aaaImageListAdapter);
            Utils.setListViewHeightBasedOnChildren1(CategoriesActivity.this.listview);
            CategoriesActivity.this.mHandler = new Handler();
          }
          if (paramInt == CommonRef.getBeanListtype_refresh)
            CategoriesActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                CategoriesActivity.this.item.clear();
                CategoriesActivity.this.geneItems();
                CategoriesActivity.this.aaaImageListAdapter = new CategoriesAdapter(CategoriesActivity.this, CategoriesActivity.this, CategoriesActivity.this.item);
                CategoriesActivity.this.listview.setAdapter(CategoriesActivity.this.aaaImageListAdapter);
              }
            }
            , 0L);
          if (paramInt == CommonRef.getBeanListtype_addmore)
            CategoriesActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                CategoriesActivity.this.geneItems();
                CategoriesActivity.this.aaaImageListAdapter.notifyDataSetChanged();
              }
            }
            , 0L);
          if (paramInt == CommonRef.getBeanListtype_nomore)
            CategoriesActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                CategoriesActivity.this.aaaImageListAdapter.notifyDataSetChanged();
              }
            }
            , 0L);
          if ((CategoriesActivity.this.start != CommonRef.start) || ((CategoriesActivity.this.beanlists != null) && (CategoriesActivity.this.start == CommonRef.start)))
            CategoriesActivity.this.beanlists.size();
          CategoriesActivity.this.mHandler.post(new Runnable()
          {
            public void run()
            {
              CategoriesActivity.this.scrollview.scrollTo(0, 0);
            }
          });
          WaitLoadDialog.getInstance().dismissDialog();
        }
      }
      , this, this.imei, this.userId, this.cateId);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localChannelList.execute(new JiaDeNetRequest[0]);
      return;
      label144: paramInt = CommonRef.getBeanListtype_nomore;
    }
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected int getViewId()
  {
    return 2130903043;
  }

  public void init()
  {
    this.scrollview = ((ScrollView)findViewById(2131427369));
    this.zhongguohuihua = ((TextView)findViewById(2131427363));
    this.shufazhuanke = ((TextView)findViewById(2131427364));
    this.gucizaxiang = ((TextView)findViewById(2131427366));
    this.xihuadiaosu = ((TextView)findViewById(2131427365));
    this.dangdaigongyi = ((TextView)findViewById(2131427367));
    this.yishuping = ((TextView)findViewById(2131427368));
    this.moreLinearLayout = ((LinearLayout)findViewById(2131427371));
    this.fl_biaoti = ((TableLayout)findViewById(2131427370));
    this.curTextView = this.zhongguohuihua;
    this.moreLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (CategoriesActivity.this.ischeck)
        {
          Intent localIntent = new Intent(CategoriesActivity.this, CategoriesAllTypeActivity.class);
          localIntent.putExtra("titlename", CategoriesActivity.this.curTextView.getText().toString());
          CategoriesActivity.this.startActivity(localIntent);
        }
      }
    });
    this.zhongguohuihua.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesActivity.this.cateId = CommonRef.chancetype_zghh;
        CategoriesActivity.this.checkTab(CategoriesActivity.this.zhongguohuihua);
        CategoriesActivity.this.getBeanList(CommonRef.getBeanListtype_first);
      }
    });
    this.shufazhuanke.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesActivity.this.cateId = CommonRef.chancetype_sfzk;
        CategoriesActivity.this.checkTab(CategoriesActivity.this.shufazhuanke);
        CategoriesActivity.this.getBeanList(CommonRef.getBeanListtype_first);
      }
    });
    this.gucizaxiang.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesActivity.this.cateId = CommonRef.chancetype_gczx;
        CategoriesActivity.this.checkTab(CategoriesActivity.this.gucizaxiang);
        CategoriesActivity.this.getBeanList(CommonRef.getBeanListtype_first);
      }
    });
    this.xihuadiaosu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesActivity.this.cateId = CommonRef.chancetype_xihuadiaosu;
        CategoriesActivity.this.checkTab(CategoriesActivity.this.xihuadiaosu);
        CategoriesActivity.this.getBeanList(CommonRef.getBeanListtype_first);
      }
    });
    this.dangdaigongyi.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesActivity.this.cateId = CommonRef.chancetype_dangdaigongyi;
        CategoriesActivity.this.checkTab(CategoriesActivity.this.dangdaigongyi);
        CategoriesActivity.this.getBeanList(CommonRef.getBeanListtype_first);
      }
    });
    this.yishuping.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesActivity.this.cateId = CommonRef.chancetype_yishuping;
        CategoriesActivity.this.checkTab(CategoriesActivity.this.yishuping);
        CategoriesActivity.this.getBeanList(CommonRef.getBeanListtype_first);
      }
    });
    this.imei = Utils.getPhoneId();
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    if (localList != null)
      this.userId = ((LoginBean)localList.get(0)).getUserId();
    getBeanList(CommonRef.getBeanListtype_first);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView();
    createNavMenu();
    pnum = 1;
    init();
  }

  protected void onDestroy()
  {
    this.zhongguohuihua = null;
    this.shufazhuanke = null;
    this.gucizaxiang = null;
    this.xihuadiaosu = null;
    this.dangdaigongyi = null;
    this.yishuping = null;
    this.curTextView = null;
    this.moreLinearLayout = null;
    this.scrollview = null;
    super.onDestroy();
  }

  public void onLoadMore()
  {
    getBeanList(CommonRef.getBeanListtype_addmore);
  }

  public void onRefresh()
  {
    getBeanList(CommonRef.getBeanListtype_refresh);
  }

  public void onResume()
  {
    super.onResume();
    if (this.isFirst)
      this.isFirst = false;
  }

  public void settite()
  {
    this.fl_biaoti.removeAllViews();
    this.fl_biaoti.setStretchAllColumns(true);
    if ((this.titebeanlist == null) || (this.titebeanlist.size() == 0));
    int j;
    LayoutInflater localLayoutInflater;
    int k;
    label80: TableRow localTableRow;
    do
    {
      return;
      CommonRef.ztlistbean.clear();
      CommonRef.ztlistbean = this.titebeanlist;
      int i = (int)Math.ceil(this.titebeanlist.size() / 4.0F);
      j = 0;
      localLayoutInflater = LayoutInflater.from(this);
      k = 1;
      if (k > i)
        break;
      localTableRow = new TableRow(this);
    }
    while (j >= this.titebeanlist.size());
    int m = 0;
    label112: if (m >= 4);
    while (true)
    {
      this.fl_biaoti.addView(localTableRow, new TableLayout.LayoutParams(-1, -1));
      k++;
      break label80;
      break;
      if (j < this.titebeanlist.size())
      {
        final ChannelListBean localChannelListBean = (ChannelListBean)this.titebeanlist.get(j);
        View localView = localLayoutInflater.inflate(2130903047, localTableRow, false);
        ImageView localImageView = (ImageView)localView.findViewById(2131427381);
        ((TextView)localView.findViewById(2131427382)).setText(localChannelListBean.getCatName());
        Utils.loadImage(localChannelListBean.getCatPicpath(), localImageView, this);
        localView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Intent localIntent = new Intent();
            localIntent.setClass(CategoriesActivity.this, CategoriesTypeListActivity.class);
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("ChannelListBean", localChannelListBean);
            localIntent.putExtras(localBundle);
            CategoriesActivity.this.startActivity(localIntent);
          }
        });
        localTableRow.addView(localView, new TableRow.LayoutParams(1, -2));
        j++;
        m++;
        break label112;
      }
      localTableRow.addView(localLayoutInflater.inflate(2130903047, localTableRow, false), new TableRow.LayoutParams(1, -2));
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.CategoriesActivity
 * JD-Core Version:    0.6.2
 */