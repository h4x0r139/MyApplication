package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.tg.jiadeonline.adapter.CategoriesTypeListAdapter;
import com.tg.jiadeonline.bean.ChannelListBean;
import com.tg.jiadeonline.bean.SpecialFieldlistBean;
import com.tg.jiadeonline.date.SpecialFieldlistManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.SpecialFieldlist;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class CategoriesTypeListActivity extends Activity
  implements XListView.IXListViewListener
{
  private static int pnum = 1;
  private CategoriesTypeListAdapter aaaImageListAdapter;
  private ImageView backImageView;
  private List<SpecialFieldlistBean> beanlists;
  private String cateId = "";
  private ChannelListBean clbean;
  private TextView downtime = null;
  private boolean downtime_b = false;
  private ImageView downtime_image;
  private LinearLayout downtimelayout = null;
  private String imei;
  boolean isFirst = true;
  private boolean ischeck = true;
  private List<SpecialFieldlistBean> item = new ArrayList();
  private LinearLayout layoit_tishi;
  private XListView listview;
  private Handler mHandler;
  private TextView numbers = null;
  private boolean numbers_b = false;
  private ImageView numbers_image;
  private LinearLayout numberslayout = null;
  private PopupWindow popright = null;
  private String porder = "";
  private int pqty = CommonRef.pqty;
  private Resources r;
  private int start = 0;
  private TextView titleName;
  private TextView typechange;
  private String userId = "";
  private String zcName = "";

  private void geneItems()
  {
    if ((this.beanlists != null) && (this.beanlists.size() > 0))
      this.item.addAll(this.beanlists);
  }

  private void getBeanList(final int paramInt)
  {
    this.userId = Utils.getUserId(this);
    if (paramInt == CommonRef.getBeanListtype_refresh)
    {
      this.start = CommonRef.start;
      pnum = CommonRef.pnum;
    }
    if (paramInt == CommonRef.getBeanListtype_addmore)
    {
      if (((pnum == 1) || (this.item.size() < pnum * this.pqty)) && ((pnum != 1) || (this.item.size() < this.pqty)))
        break label191;
      pnum = 1 + pnum;
    }
    while (true)
    {
      this.start = (pnum * this.pqty);
      SpecialFieldlist localSpecialFieldlist = new SpecialFieldlist(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          if (CategoriesTypeListActivity.this.listview != null)
            CategoriesTypeListActivity.this.listview.setPullLoadEnable(false);
          Utils.showDialog(CategoriesTypeListActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          if ((paramAnonymousJiaDeNetResponse.result == null) || (!"ok".equals(paramAnonymousJiaDeNetResponse.result)))
          {
            Utils.showDialog(CategoriesTypeListActivity.this, "提示", paramAnonymousJiaDeNetResponse.result.toString(), 2130837514, null);
            WaitLoadDialog.getInstance().dismissDialog();
            return;
          }
          SpecialFieldlistManager localSpecialFieldlistManager = new SpecialFieldlistManager(CategoriesTypeListActivity.this);
          localSpecialFieldlistManager.openDataBase();
          List localList = localSpecialFieldlistManager.fetchAllDatas();
          localSpecialFieldlistManager.closeDataBase();
          CategoriesTypeListActivity.this.beanlists = localList;
          if (paramInt == CommonRef.getBeanListtype_first)
          {
            CategoriesTypeListActivity.this.geneItems();
            CategoriesTypeListActivity.this.listview = ((XListView)CategoriesTypeListActivity.this.findViewById(2131427391));
            CategoriesTypeListActivity.this.listview.setPullLoadEnable(true);
            CategoriesTypeListActivity.this.listview.setPullRefreshEnable(true);
            CategoriesTypeListActivity.this.aaaImageListAdapter = new CategoriesTypeListAdapter(CategoriesTypeListActivity.this, CategoriesTypeListActivity.this, CategoriesTypeListActivity.this.item);
            CategoriesTypeListActivity.this.listview.setAdapter(CategoriesTypeListActivity.this.aaaImageListAdapter);
            CategoriesTypeListActivity.this.listview.setXListViewListener(CategoriesTypeListActivity.this);
            CategoriesTypeListActivity.this.mHandler = new Handler();
          }
          if (paramInt == CommonRef.getBeanListtype_refresh)
          {
            CategoriesTypeListActivity.this.listview.setPullLoadEnable(true);
            CategoriesTypeListActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                CategoriesTypeListActivity.this.item.clear();
                CategoriesTypeListActivity.this.geneItems();
                CategoriesTypeListActivity.this.aaaImageListAdapter = new CategoriesTypeListAdapter(CategoriesTypeListActivity.this, CategoriesTypeListActivity.this, CategoriesTypeListActivity.this.item);
                CategoriesTypeListActivity.this.listview.setAdapter(CategoriesTypeListActivity.this.aaaImageListAdapter);
                CategoriesTypeListActivity.this.onLoad();
                WaitLoadDialog.getInstance().dismissDialog();
              }
            }
            , 0L);
          }
          if (paramInt == CommonRef.getBeanListtype_addmore)
            CategoriesTypeListActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                CategoriesTypeListActivity.this.geneItems();
                CategoriesTypeListActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                CategoriesTypeListActivity.this.onLoad();
                WaitLoadDialog.getInstance().dismissDialog();
              }
            }
            , 0L);
          if (paramInt == CommonRef.getBeanListtype_nomore)
          {
            CategoriesTypeListActivity.this.mHandler.postDelayed(new Runnable()
            {
              public void run()
              {
                CategoriesTypeListActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                CategoriesTypeListActivity.this.onLoad();
                WaitLoadDialog.getInstance().dismissDialog();
              }
            }
            , 0L);
            CategoriesTypeListActivity.this.listview.setPullLoadEnable(false);
          }
          if ((CategoriesTypeListActivity.this.start == CommonRef.start) && (CategoriesTypeListActivity.this.beanlists == null))
            WaitLoadDialog.getInstance().dismissDialog();
          if (CategoriesTypeListActivity.this.beanlists != null)
          {
            if ((CategoriesTypeListActivity.this.start != CommonRef.start) || (CategoriesTypeListActivity.this.beanlists.size() >= CategoriesTypeListActivity.this.pqty))
              break label417;
            WaitLoadDialog.getInstance().dismissDialog();
          }
          while (true)
          {
            CategoriesTypeListActivity.this.ischeck = true;
            WaitLoadDialog.getInstance().dismissDialog();
            return;
            label417: CategoriesTypeListActivity.this.beanlists.size();
          }
        }
      }
      , this, this.imei, this.userId, this.cateId, pnum, this.pqty, this.porder);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localSpecialFieldlist.execute(new JiaDeNetRequest[0]);
      return;
      label191: paramInt = CommonRef.getBeanListtype_nomore;
    }
  }

  private void onLoad()
  {
    this.listview.stopRefresh();
    this.listview.stopLoadMore();
    this.listview.setRefreshTime("刚刚");
  }

  public void changenumber()
  {
    this.numbers.setTextColor(this.r.getColor(2131034290));
    this.downtime.setTextColor(this.r.getColor(2131034115));
    if (!this.numbers_b)
    {
      this.downtime_b = false;
      this.numbers_b = true;
      changesImage(2);
      return;
    }
    this.numbers_b = false;
    changesImage(2);
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
      if (this.numbers_b)
        break label139;
      this.numbers_image.setImageDrawable(localDrawable1);
    }
    while (true)
    {
      if (paramInt != 1)
        break label151;
      this.numbers_image.setImageDrawable(localDrawable3);
      return;
      this.downtime_image.setImageDrawable(localDrawable2);
      break;
      label139: this.numbers_image.setImageDrawable(localDrawable2);
    }
    label151: this.downtime_image.setImageDrawable(localDrawable3);
  }

  public void changesdown(TextView paramTextView, final int paramInt, ImageView paramImageView)
  {
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramInt == 1)
          CategoriesTypeListActivity.this.changetime();
        if (paramInt == 2)
          CategoriesTypeListActivity.this.changenumber();
        CategoriesTypeListActivity.this.ischeck = false;
        CategoriesTypeListActivity.this.getBeanList(CommonRef.getBeanListtype_refresh);
      }
    });
    paramImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!CategoriesTypeListActivity.this.ischeck)
          return;
        if (paramInt == 1)
          CategoriesTypeListActivity.this.changetime();
        if (paramInt == 2)
          CategoriesTypeListActivity.this.changenumber();
        CategoriesTypeListActivity.this.porder = "tu";
        CategoriesTypeListActivity.this.ischeck = false;
        CategoriesTypeListActivity.this.getBeanList(CommonRef.getBeanListtype_refresh);
      }
    });
  }

  public void changeslinearLayout(LinearLayout paramLinearLayout, final int paramInt)
  {
    paramLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!CategoriesTypeListActivity.this.ischeck)
          return;
        if (paramInt == 1)
          CategoriesTypeListActivity.this.changetime();
        if (paramInt == 2)
          CategoriesTypeListActivity.this.changenumber();
        CategoriesTypeListActivity.this.porder = "td";
        CategoriesTypeListActivity.this.ischeck = false;
        CategoriesTypeListActivity.this.getBeanList(CommonRef.getBeanListtype_refresh);
      }
    });
  }

  public void changetime()
  {
    this.downtime.setTextColor(this.r.getColor(2131034290));
    this.numbers.setTextColor(this.r.getColor(2131034115));
    if (!this.downtime_b)
    {
      this.downtime_b = true;
      this.numbers_b = false;
      changesImage(1);
      return;
    }
    this.downtime_b = false;
    changesImage(1);
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
    changesdown(this.downtime, 1, this.downtime_image);
    changesdown(this.numbers, 2, this.numbers_image);
    changeslinearLayout(this.downtimelayout, 1);
    changeslinearLayout(this.numberslayout, 2);
    this.imei = Utils.getPhoneId();
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
        ((SpecialFieldlistBean)this.item.get(i)).setCurrentBid(str);
        if (this.aaaImageListAdapter != null)
          this.aaaImageListAdapter.notifyDataSetChanged();
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903048);
    this.typechange = ((TextView)findViewById(2131427567));
    this.titleName = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.userId = Utils.getUserId(this);
    pnum = 1;
    LinearLayout localLinearLayout;
    if (getIntent().hasExtra("ChannelListBean"))
    {
      this.clbean = ((ChannelListBean)getIntent().getSerializableExtra("ChannelListBean"));
      this.cateId = this.clbean.getCatId();
      this.zcName = this.clbean.getCatName();
      if (!"".equals(this.zcName))
        this.titleName.setText(this.zcName);
    }
    else
    {
      init();
      View localView = LayoutInflater.from(this).inflate(2130903089, null);
      this.popright = new PopupWindow(localView, -2, -2, false);
      this.popright.setAnimationStyle(2131230726);
      this.popright.setBackgroundDrawable(new BitmapDrawable());
      this.popright.setOutsideTouchable(true);
      this.popright.setFocusable(true);
      localLinearLayout = (LinearLayout)localView.findViewById(2131427570);
      if (CommonRef.ztlistbean == null);
    }
    for (int i = 0; ; i++)
    {
      if (i >= CommonRef.ztlistbean.size())
      {
        this.typechange.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (CategoriesTypeListActivity.this.popright.isShowing())
            {
              CategoriesTypeListActivity.this.popright.dismiss();
              return;
            }
            CategoriesTypeListActivity.this.popright.showAsDropDown(paramAnonymousView);
          }
        });
        this.backImageView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            CategoriesTypeListActivity.this.finish();
          }
        });
        return;
        this.titleName.setText("专场列表");
        break;
      }
      final ChannelListBean localChannelListBean = (ChannelListBean)CommonRef.ztlistbean.get(i);
      TextView localTextView = new TextView(this);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
      localLayoutParams.topMargin = 15;
      localLayoutParams.bottomMargin = 15;
      localTextView.setLayoutParams(localLayoutParams);
      localTextView.setGravity(17);
      localTextView.setTextSize(15.0F);
      localTextView.setTextColor(Color.parseColor("#000000"));
      localTextView.setText(localChannelListBean.getCatName());
      localTextView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (CategoriesTypeListActivity.this.popright.isShowing())
            CategoriesTypeListActivity.this.popright.dismiss();
          Intent localIntent = new Intent();
          localIntent.setClass(CategoriesTypeListActivity.this, CategoriesTypeListActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("ChannelListBean", localChannelListBean);
          localIntent.putExtras(localBundle);
          CategoriesTypeListActivity.this.startActivity(localIntent);
          CategoriesTypeListActivity.this.finish();
        }
      });
      localLinearLayout.addView(localTextView);
    }
  }

  public void onDestroy()
  {
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
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.CategoriesTypeListActivity
 * JD-Core Version:    0.6.2
 */