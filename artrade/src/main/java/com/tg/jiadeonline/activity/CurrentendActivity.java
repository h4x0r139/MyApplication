package com.tg.jiadeonline.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.adapter.CurrentendAdapter;
import com.tg.jiadeonline.base.BaseActivity;
import com.tg.jiadeonline.bean.EndOfDayListBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.EndOfDayListManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.EndOfDaylist;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class CurrentendActivity extends BaseActivity
  implements XListView.IXListViewListener
{
  private static int pnum = CommonRef.pnum;
  private CurrentendAdapter aaaImageListAdapter;
  private TextView allgoods = null;
  private boolean allgoods_b = false;
  private ImageView allgoods_image;
  private LinearLayout allgoodslayout = null;
  private List<EndOfDayListBean> beanlists;
  private String cateId = "0";
  private TextView chancetype_dangdaigongyi = null;
  private TextView chancetype_gczx = null;
  private TextView chancetype_sfzk = null;
  private TextView chancetype_xihuadiaosu = null;
  private TextView chancetype_zghh = null;
  private TextView chanceyear_all = null;
  private String imei;
  boolean isFirst = true;
  private List<EndOfDayListBean> item = new ArrayList();
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
  private int start = CommonRef.start;
  private String userId = "";

  private void clean()
  {
    this.popright = null;
    this.chanceyear_all = null;
    this.chancetype_zghh = null;
    this.chancetype_sfzk = null;
    this.chancetype_xihuadiaosu = null;
    this.chancetype_gczx = null;
    this.chancetype_dangdaigongyi = null;
    this.allgoods = null;
    this.numbers = null;
    this.allgoods_image = null;
    this.numbers_image = null;
    this.allgoodslayout = null;
    this.numberslayout = null;
    this.listview = null;
    if (this.beanlists != null)
      this.beanlists.clear();
    this.layoit_tishi = null;
    this.aaaImageListAdapter = null;
  }

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
    if (paramInt == CommonRef.getBeanListtype_addmore)
    {
      if (((pnum == 1) || (this.item.size() < pnum * this.pqty)) && ((pnum != 1) || (this.item.size() < this.pqty)))
        break label183;
      pnum = 1 + pnum;
    }
    while (true)
    {
      this.start = (pnum * this.pqty);
      EndOfDaylist localEndOfDaylist = new EndOfDaylist(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          if (CurrentendActivity.this.item.size() > 0)
          {
            Utils.showDialog(CurrentendActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
            WaitLoadDialog.getInstance().dismissDialog();
          }
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          String str = (String)paramAnonymousJiaDeNetResponse.result;
          if ((!"".equals(str)) && (!"ok".equals(str)))
            Utils.showDialog(CurrentendActivity.this, "提示", str, 2130837514, null);
          while (true)
          {
            WaitLoadDialog.getInstance().dismissDialog();
            return;
            EndOfDayListManager localEndOfDayListManager = new EndOfDayListManager(CurrentendActivity.this);
            localEndOfDayListManager.openDataBase();
            List localList = localEndOfDayListManager.fetchAllDatas();
            localEndOfDayListManager.closeDataBase();
            if ((localList == null) || (localList.size() <= 0))
            {
              CurrentendActivity.this.errormsg("没有更多数据");
              CurrentendActivity.this.listview.setPullLoadEnable(false);
              return;
            }
            CurrentendActivity.this.beanlists = localList;
            if (paramInt == CommonRef.getBeanListtype_first)
            {
              CurrentendActivity.this.geneItems();
              CurrentendActivity.this.listview = ((XListView)CurrentendActivity.this.findViewById(2131427372));
              CurrentendActivity.this.listview.setPullLoadEnable(true);
              CurrentendActivity.this.listview.setPullRefreshEnable(true);
              CurrentendActivity.this.aaaImageListAdapter = new CurrentendAdapter(CurrentendActivity.this, CurrentendActivity.this, CurrentendActivity.this.item);
              CurrentendActivity.this.listview.setAdapter(CurrentendActivity.this.aaaImageListAdapter);
              CurrentendActivity.this.listview.setXListViewListener(CurrentendActivity.this);
              CurrentendActivity.this.mHandler = new Handler();
            }
            if (paramInt == CommonRef.getBeanListtype_refresh)
            {
              CurrentendActivity.this.listview.setPullLoadEnable(true);
              CurrentendActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CurrentendActivity.this.item.clear();
                  CurrentendActivity.this.geneItems();
                  CurrentendActivity.this.aaaImageListAdapter = new CurrentendAdapter(CurrentendActivity.this, CurrentendActivity.this, CurrentendActivity.this.item);
                  CurrentendActivity.this.listview.setAdapter(CurrentendActivity.this.aaaImageListAdapter);
                  CurrentendActivity.this.onLoad();
                }
              }
              , 0L);
            }
            if (paramInt == CommonRef.getBeanListtype_addmore)
              CurrentendActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CurrentendActivity.this.geneItems();
                  CurrentendActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  CurrentendActivity.this.onLoad();
                }
              }
              , 0L);
            if (paramInt == CommonRef.getBeanListtype_nomore)
              CurrentendActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CurrentendActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  CurrentendActivity.this.onLoad();
                }
              }
              , 0L);
            if ((CurrentendActivity.this.start != CommonRef.start) || ((CurrentendActivity.this.beanlists != null) && ((CurrentendActivity.this.start != CommonRef.start) || (CurrentendActivity.this.beanlists.size() >= CurrentendActivity.this.pqty))))
              CurrentendActivity.this.item.size();
          }
        }
      }
      , this, this.imei, this.userId, this.cateId, pnum, this.pqty, this.porder);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localEndOfDaylist.execute(new JiaDeNetRequest[0]);
      return;
      label183: paramInt = CommonRef.getBeanListtype_nomore;
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
    this.allgoods.setTextColor(this.r.getColor(2131034115));
    if (this.popright.isShowing())
      this.popright.dismiss();
    if (!this.numbers_b)
    {
      this.allgoods_b = false;
      this.numbers_b = true;
      changesImage(2);
    }
    for (this.porder = "td"; ; this.porder = "tu")
    {
      onRefresh();
      return;
      this.numbers_b = false;
      changesImage(2);
    }
  }

  public void changesImage(int paramInt)
  {
    Resources localResources = getBaseContext().getResources();
    Drawable localDrawable1 = localResources.getDrawable(2130837598);
    Drawable localDrawable2 = localResources.getDrawable(2130837556);
    Drawable localDrawable3 = localResources.getDrawable(2130837514);
    Drawable localDrawable4 = localResources.getDrawable(2130837546);
    Drawable localDrawable5 = localResources.getDrawable(2130837547);
    localDrawable1.setBounds(0, 0, localDrawable1.getMinimumWidth(), localDrawable1.getMinimumHeight());
    localDrawable2.setBounds(0, 0, localDrawable2.getMinimumWidth(), localDrawable2.getMinimumHeight());
    localDrawable3.setBounds(0, 0, localDrawable3.getMinimumWidth(), localDrawable3.getMinimumHeight());
    localDrawable4.setBounds(0, 0, localDrawable4.getMinimumWidth(), localDrawable4.getMinimumHeight());
    localDrawable5.setBounds(0, 0, localDrawable5.getMinimumWidth(), localDrawable5.getMinimumHeight());
    if (!this.allgoods_b)
    {
      this.allgoods_image.setImageDrawable(localDrawable5);
      if (this.numbers_b)
        break label192;
      this.numbers_image.setImageDrawable(localDrawable1);
    }
    while (true)
    {
      if (paramInt != 1)
        break label204;
      this.numbers_image.setImageDrawable(localDrawable3);
      return;
      this.allgoods_image.setImageDrawable(localDrawable4);
      break;
      label192: this.numbers_image.setImageDrawable(localDrawable2);
    }
    label204: this.allgoods_image.setImageDrawable(localDrawable5);
  }

  public void changesdown(TextView paramTextView, final int paramInt, ImageView paramImageView)
  {
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramInt == 1)
          CurrentendActivity.this.changetime();
        if (paramInt == 2)
          CurrentendActivity.this.changenumber();
      }
    });
    paramImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramInt == 1)
          CurrentendActivity.this.changetime();
        if (paramInt == 2)
          CurrentendActivity.this.changenumber();
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
          CurrentendActivity.this.changetime();
        if (paramInt == 2)
          CurrentendActivity.this.changenumber();
      }
    });
  }

  public void changetime()
  {
    this.allgoods.setTextColor(this.r.getColor(2131034290));
    this.numbers.setTextColor(this.r.getColor(2131034115));
    if (!this.allgoods_b)
    {
      this.allgoods_b = true;
      this.numbers_b = false;
      changesImage(1);
      this.popright.showAsDropDown(this.allgoodslayout);
      return;
    }
    this.allgoods_b = false;
    if (this.popright.isShowing())
      this.popright.dismiss();
    changesImage(1);
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected int getViewId()
  {
    return 2130903050;
  }

  public void init()
  {
    this.r = getResources();
    this.allgoods = ((TextView)super.findViewById(2131427394));
    this.numbers = ((TextView)super.findViewById(2131427388));
    this.allgoodslayout = ((LinearLayout)super.findViewById(2131427393));
    this.numberslayout = ((LinearLayout)super.findViewById(2131427387));
    this.allgoods_image = ((ImageView)super.findViewById(2131427395));
    this.numbers_image = ((ImageView)super.findViewById(2131427389));
    changesdown(this.allgoods, 1, this.allgoods_image);
    changesdown(this.numbers, 2, this.numbers_image);
    changeslinearLayout(this.allgoodslayout, 1);
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
        ((EndOfDayListBean)this.item.get(i)).setCurrentBid(str);
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
    init();
    popwindows();
  }

  public void onDestroy()
  {
    super.onDestroy();
    clean();
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

  public void popwindows()
  {
    View localView = LayoutInflater.from(this).inflate(2130903091, null);
    this.popright = new PopupWindow(localView, -1, -2, false);
    this.popright.setAnimationStyle(2131230730);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    this.popright.setOnDismissListener(new PopupWindow.OnDismissListener()
    {
      public void onDismiss()
      {
        CurrentendActivity.this.changetime();
      }
    });
    this.chanceyear_all = ((TextView)localView.findViewById(2131427579));
    setonclick(this.chanceyear_all, CommonRef.chanceyear_all);
    this.chancetype_zghh = ((TextView)localView.findViewById(2131427580));
    setonclick(this.chancetype_zghh, CommonRef.chancetype_zghh);
    this.chancetype_sfzk = ((TextView)localView.findViewById(2131427581));
    setonclick(this.chancetype_sfzk, CommonRef.chancetype_sfzk);
    this.chancetype_xihuadiaosu = ((TextView)localView.findViewById(2131427582));
    setonclick(this.chancetype_xihuadiaosu, CommonRef.chancetype_xihuadiaosu);
    this.chancetype_gczx = ((TextView)localView.findViewById(2131427583));
    setonclick(this.chancetype_gczx, CommonRef.chancetype_gczx);
    this.chancetype_dangdaigongyi = ((TextView)localView.findViewById(2131427584));
    setonclick(this.chancetype_dangdaigongyi, CommonRef.chancetype_dangdaigongyi);
  }

  public void setonclick(TextView paramTextView, final String paramString)
  {
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CurrentendActivity.this.cateId = paramString;
        if (CurrentendActivity.this.popright.isShowing())
          CurrentendActivity.this.popright.dismiss();
        CurrentendActivity.this.cateId = paramString;
        CurrentendActivity.this.onRefresh();
        TextView localTextView = (TextView)paramAnonymousView;
        CurrentendActivity.this.allgoods.setText(localTextView.getText());
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.CurrentendActivity
 * JD-Core Version:    0.6.2
 */