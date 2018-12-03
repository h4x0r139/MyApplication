package com.tg.jiadeonline.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.adapter.FavoritesAdapter;
import com.tg.jiadeonline.base.BaseActivity;
import com.tg.jiadeonline.bean.CollectAuctionlistBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.CollectAuctionlistManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.CollectAuctionlist;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends BaseActivity
  implements XListView.IXListViewListener
{
  private static int pnum = CommonRef.pnum;
  private FavoritesAdapter aaaImageListAdapter;
  private List<CollectAuctionlistBean> beanlists;
  private String cateId = "";
  private TextView downtime = null;
  private boolean downtime_b = false;
  private ImageView downtime_image;
  private LinearLayout downtimelayout = null;
  private String imei;
  private boolean isFirst = true;
  private boolean ischeck = true;
  private boolean isfast = true;
  private List<CollectAuctionlistBean> item = new ArrayList();
  private XListView listview;
  private Handler mHandler;
  private TextView numbers = null;
  private boolean numbers_b = false;
  private ImageView numbers_image;
  private LinearLayout numberslayout = null;
  private String porder = "";
  private int pqty = CommonRef.pqty;
  private Resources r;
  private int start = CommonRef.start;
  private String userId = "";

  private void geneItems()
  {
    if ((this.beanlists != null) && (this.beanlists.size() > 0))
      this.item.addAll(this.beanlists);
  }

  private void getBeanList(final int paramInt)
  {
    if (paramInt == CommonRef.getBeanListtype_refresh)
    {
      this.start = CommonRef.start;
      pnum = CommonRef.pnum;
    }
    CollectAuctionlist localCollectAuctionlist = new CollectAuctionlist(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(FavoritesActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        if ((!"ok".equals(paramAnonymousJiaDeNetResponse.result)) || (paramAnonymousJiaDeNetResponse.result == null))
        {
          Utils.showDialog(FavoritesActivity.this, "提示", paramAnonymousJiaDeNetResponse.result.toString(), 2130837514, null);
          return;
        }
        CollectAuctionlistManager localCollectAuctionlistManager = new CollectAuctionlistManager(FavoritesActivity.this);
        localCollectAuctionlistManager.openDataBase();
        List localList = localCollectAuctionlistManager.fetchAllDatas();
        localCollectAuctionlistManager.closeDataBase();
        if ((localList == null) || (localList.size() == 0))
        {
          FavoritesActivity.this.listview.setPullLoadEnable(false);
          FavoritesActivity.this.beanlists = localList;
          if (paramInt == CommonRef.getBeanListtype_first)
          {
            FavoritesActivity.this.item.clear();
            FavoritesActivity.this.geneItems();
            FavoritesActivity.this.listview.setPullLoadEnable(true);
            FavoritesActivity.this.listview.setPullRefreshEnable(true);
            FavoritesActivity.this.aaaImageListAdapter = new FavoritesAdapter(FavoritesActivity.this, FavoritesActivity.this, FavoritesActivity.this.item);
            FavoritesActivity.this.listview.setAdapter(FavoritesActivity.this.aaaImageListAdapter);
            FavoritesActivity.this.listview.setXListViewListener(FavoritesActivity.this);
            FavoritesActivity.this.mHandler = new Handler();
          }
          if (paramInt == CommonRef.getBeanListtype_refresh)
          {
            FavoritesActivity.this.listview.setPullLoadEnable(true);
            FavoritesActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                FavoritesActivity.this.item.clear();
                FavoritesActivity.this.geneItems();
                FavoritesActivity.this.aaaImageListAdapter = new FavoritesAdapter(FavoritesActivity.this, FavoritesActivity.this, FavoritesActivity.this.item);
                FavoritesActivity.this.listview.setAdapter(FavoritesActivity.this.aaaImageListAdapter);
                FavoritesActivity.this.onLoad();
              }
            }
            , 0L);
          }
          if (paramInt == CommonRef.getBeanListtype_addmore)
            FavoritesActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                FavoritesActivity.this.geneItems();
                FavoritesActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                FavoritesActivity.this.onLoad();
              }
            }
            , 0L);
          if (paramInt == CommonRef.getBeanListtype_nomore)
          {
            FavoritesActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                FavoritesActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                FavoritesActivity.this.onLoad();
              }
            }
            , 0L);
            FavoritesActivity.this.listview.setPullLoadEnable(false);
          }
          if ((FavoritesActivity.this.start == CommonRef.start) && (FavoritesActivity.this.beanlists == null))
            FavoritesActivity.this.listview.setPullLoadEnable(false);
          if (FavoritesActivity.this.beanlists != null)
          {
            if ((FavoritesActivity.this.start != CommonRef.start) || (FavoritesActivity.this.beanlists.size() >= FavoritesActivity.this.pqty))
              break label514;
            FavoritesActivity.this.listview.setPullLoadEnable(false);
          }
        }
        while (true)
        {
          FavoritesActivity.this.ischeck = true;
          return;
          if ((localList != null) && (localList.size() == FavoritesActivity.this.pqty))
          {
            FavoritesActivity.this.listview.setPullLoadEnable(true);
            FavoritesActivity.pnum = 1 + FavoritesActivity.pnum;
            break;
          }
          if ((localList == null) || (localList.size() >= FavoritesActivity.this.pqty))
            break;
          FavoritesActivity.this.listview.setPullLoadEnable(false);
          break;
          label514: if (FavoritesActivity.this.beanlists.size() < FavoritesActivity.this.start)
            FavoritesActivity.this.listview.setPullLoadEnable(false);
        }
      }
    }
    , this, this.imei, this.userId, this.cateId, pnum, this.pqty, this.porder);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localCollectAuctionlist.execute(new JiaDeNetRequest[0]);
  }

  private void initUser()
  {
    pnum = 1;
    init();
  }

  private void onLoad()
  {
    this.listview.stopRefresh();
    this.listview.stopLoadMore();
    this.listview.setRefreshTime("刚刚");
  }

  public void changenumber()
  {
    if (!this.ischeck)
      return;
    this.ischeck = false;
    this.numbers.setTextColor(this.r.getColor(2131034290));
    this.downtime.setTextColor(this.r.getColor(2131034115));
    if (!this.numbers_b)
    {
      this.downtime_b = false;
      this.numbers_b = true;
      changesImage(2);
      setguanzhu(1);
      return;
    }
    this.numbers_b = false;
    changesImage(2);
    setguanzhu(2);
  }

  public void changesImage(int paramInt)
  {
    Resources localResources = getBaseContext().getResources();
    Drawable localDrawable1 = localResources.getDrawable(2130837598);
    Drawable localDrawable2 = localResources.getDrawable(2130837556);
    Drawable localDrawable3 = localResources.getDrawable(2130837514);
    localDrawable1.setBounds(0, 0, localDrawable1.getMinimumWidth(), localDrawable1.getMinimumHeight());
    localDrawable2.setBounds(0, 0, localDrawable2.getMinimumWidth(), localDrawable2.getMinimumHeight());
    localDrawable3.setBounds(0, 0, localDrawable2.getMinimumWidth(), localDrawable2.getMinimumHeight());
    if (!this.downtime_b)
    {
      this.downtime_image.setImageDrawable(localDrawable1);
      this.porder = "td";
      if (this.numbers_b)
        break label150;
      this.numbers_image.setImageDrawable(localDrawable1);
    }
    while (true)
    {
      if (paramInt != 1)
        break label162;
      this.numbers_image.setImageDrawable(localDrawable3);
      return;
      this.downtime_image.setImageDrawable(localDrawable2);
      this.porder = "tu";
      break;
      label150: this.numbers_image.setImageDrawable(localDrawable2);
    }
    label162: this.downtime_image.setImageDrawable(localDrawable3);
  }

  public void changesdown(TextView paramTextView, final int paramInt, ImageView paramImageView)
  {
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramInt == 1)
          FavoritesActivity.this.changetime();
        if (paramInt == 2)
          FavoritesActivity.this.changenumber();
      }
    });
    paramImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramInt == 1)
          FavoritesActivity.this.changetime();
        if (paramInt == 2)
          FavoritesActivity.this.changenumber();
      }
    });
  }

  public void changeslinearLayout(LinearLayout paramLinearLayout, final int paramInt)
  {
    paramLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramInt == 1)
          FavoritesActivity.this.changetime();
        if (paramInt == 2)
          FavoritesActivity.this.changenumber();
      }
    });
  }

  public void changetime()
  {
    if (!this.ischeck)
      return;
    this.ischeck = false;
    this.downtime.setTextColor(this.r.getColor(2131034290));
    this.numbers.setTextColor(this.r.getColor(2131034115));
    if (!this.downtime_b)
    {
      this.downtime_b = true;
      this.numbers_b = false;
      changesImage(1);
      this.porder = "td";
      this.item.clear();
      getBeanList(CommonRef.getBeanListtype_first);
      return;
    }
    this.downtime_b = false;
    changesImage(1);
    this.porder = "tu";
    this.item.clear();
    getBeanList(CommonRef.getBeanListtype_first);
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected int getViewId()
  {
    return 2130903054;
  }

  public void init()
  {
    this.r = getResources();
    this.downtime = ((TextView)super.findViewById(2131427385));
    this.numbers = ((TextView)super.findViewById(2131427388));
    this.downtimelayout = ((LinearLayout)super.findViewById(2131427384));
    this.numberslayout = ((LinearLayout)super.findViewById(2131427387));
    this.downtime_image = ((ImageView)super.findViewById(2131427386));
    this.numbers_image = ((ImageView)super.findViewById(2131427389));
    this.listview = ((XListView)findViewById(2131427372));
    changesdown(this.downtime, 1, this.downtime_image);
    changesdown(this.numbers, 2, this.numbers_image);
    changeslinearLayout(this.downtimelayout, 1);
    changeslinearLayout(this.numberslayout, 2);
    this.imei = Utils.getPhoneId();
    if (!"".equals(this.userId))
      getBeanList(CommonRef.getBeanListtype_first);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      int i = paramIntent.getIntExtra("ppid", -1);
      String str = paramIntent.getStringExtra("jiage");
      if ((this.item != null) && (this.item.size() >= i + 1) && (i != -1) && (!"".equals(str)))
      {
        ((CollectAuctionlistBean)this.item.get(i)).setCurrentBid(str);
        if (this.aaaImageListAdapter != null)
          this.aaaImageListAdapter.notifyDataSetChanged();
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView();
    createNavMenu();
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userId = ((LoginBean)localList.get(0)).getUserId();
    initUser();
  }

  public void onLoadMore()
  {
    getBeanList(CommonRef.getBeanListtype_addmore);
  }

  public void onRefresh()
  {
    this.item.clear();
    pnum = 1;
    getBeanList(CommonRef.getBeanListtype_refresh);
  }

  public void onResume()
  {
    super.onResume();
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userId = ((LoginBean)localList.get(0)).getUserId();
  }

  public void setguanzhu(int paramInt)
  {
    if (paramInt == 1)
    {
      localCollectAuctionlistManager1 = new CollectAuctionlistManager(this);
      localCollectAuctionlistManager1.openDataBase();
      localList1 = localCollectAuctionlistManager1.fetchAllOrderDatas("carePers+1 desc");
      localCollectAuctionlistManager1.closeDataBase();
      this.item.clear();
      if (this.beanlists == null)
        this.ischeck = true;
    }
    while (paramInt != 2)
    {
      CollectAuctionlistManager localCollectAuctionlistManager1;
      List localList1;
      return;
      this.beanlists.clear();
      this.beanlists = localList1;
      geneItems();
      this.listview.setPullLoadEnable(true);
      this.listview.setPullRefreshEnable(true);
      this.aaaImageListAdapter = new FavoritesAdapter(this, this, this.item);
      this.listview.setAdapter(this.aaaImageListAdapter);
      this.listview.setXListViewListener(this);
      this.mHandler = new Handler();
      this.mHandler.postDelayed(new Runnable()
      {
        public void run()
        {
          FavoritesActivity.this.item.clear();
          FavoritesActivity.this.geneItems();
          FavoritesActivity.this.aaaImageListAdapter = new FavoritesAdapter(FavoritesActivity.this, FavoritesActivity.this, FavoritesActivity.this.item);
          FavoritesActivity.this.listview.setAdapter(FavoritesActivity.this.aaaImageListAdapter);
          FavoritesActivity.this.listview.setPullLoadEnable(false);
          FavoritesActivity.this.listview.setPullRefreshEnable(false);
          FavoritesActivity.this.onLoad();
          FavoritesActivity.this.ischeck = true;
        }
      }
      , 0L);
      return;
    }
    CollectAuctionlistManager localCollectAuctionlistManager2 = new CollectAuctionlistManager(this);
    localCollectAuctionlistManager2.openDataBase();
    List localList2 = localCollectAuctionlistManager2.fetchAllOrderDatas("carePers+1 asc");
    localCollectAuctionlistManager2.closeDataBase();
    this.item.clear();
    this.beanlists.clear();
    this.beanlists = localList2;
    geneItems();
    this.listview.setPullLoadEnable(false);
    this.listview.setPullRefreshEnable(false);
    this.aaaImageListAdapter = new FavoritesAdapter(this, this, this.item);
    this.listview.setAdapter(this.aaaImageListAdapter);
    this.listview.setXListViewListener(this);
    this.mHandler = new Handler();
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        FavoritesActivity.this.item.clear();
        FavoritesActivity.this.geneItems();
        FavoritesActivity.this.aaaImageListAdapter = new FavoritesAdapter(FavoritesActivity.this, FavoritesActivity.this, FavoritesActivity.this.item);
        FavoritesActivity.this.listview.setAdapter(FavoritesActivity.this.aaaImageListAdapter);
        FavoritesActivity.this.onLoad();
        FavoritesActivity.this.ischeck = true;
      }
    }
    , 0L);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.FavoritesActivity
 * JD-Core Version:    0.6.2
 */