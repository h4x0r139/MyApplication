package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tg.jiadeonline.adapter.CategoriesPListAdapter;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.bean.SpecialFieldlistBean;
import com.tg.jiadeonline.date.SpecialFieldlistManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.SpecialFieldlist;
import com.tg.jiadeonline.net.SpecialFieldlist1;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class CategoriesPListActivity extends Activity
  implements XListView.IXListViewListener
{
  private static int pnum = 1;
  private CategoriesPListAdapter aaaImageListAdapter;
  private String atype = "";
  private ImageView backImageView;
  private List<SpecialFieldlistBean> beanlists = new ArrayList();
  private String catTit = "";
  private String cateId = "";
  private XListView categories_plist_listview;
  private TextView categories_type;
  private ChannelList1Bean clbean = new ChannelList1Bean();
  private String imei = "";
  private boolean isfast = true;
  private List<SpecialFieldlistBean> item = new ArrayList();
  private Handler mHandler;
  private String porder = "";
  private int pqty = CommonRef.pqty;
  private int start = CommonRef.getBeanListtype_first;
  private TextView titleName;
  private LinearLayout title_fenlei;
  private String userId = "";
  private String zcName = "";
  private TextView zclb_endTime;
  private TextView zclb_name;
  private TextView zclb_startTime;
  private ImageView zclp_toppc;

  private void geneItems()
  {
    if ((this.beanlists != null) && (this.beanlists.size() > 0))
      this.item.addAll(this.beanlists);
  }

  private void getBeanList(int paramInt)
  {
    if ("S".equals(this.atype))
    {
      getBeanList1(paramInt);
      return;
    }
    getBeanList2(paramInt);
  }

  private void getBeanList1(final int paramInt)
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
      SpecialFieldlist1 localSpecialFieldlist1 = new SpecialFieldlist1(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          if (CategoriesPListActivity.this.categories_plist_listview != null)
            CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
          WaitLoadDialog.getInstance().dismissDialog();
          Utils.showDialog(CategoriesPListActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          String str = (String)paramAnonymousJiaDeNetResponse.result;
          if ((!"".equals(str)) && (!"ok".equals(str)))
          {
            Utils.showDialog(CategoriesPListActivity.this, "提示", str, 2130837514, null);
            if (CategoriesPListActivity.this.categories_plist_listview != null)
              CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
            WaitLoadDialog.getInstance().dismissDialog();
            return;
          }
          SpecialFieldlistManager localSpecialFieldlistManager = new SpecialFieldlistManager(CategoriesPListActivity.this);
          localSpecialFieldlistManager.openDataBase();
          List localList = localSpecialFieldlistManager.fetchAllDatas();
          localSpecialFieldlistManager.closeDataBase();
          CategoriesPListActivity.this.beanlists = localList;
          if ((localList == null) || (localList.size() < 10))
            CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
          if (paramInt == CommonRef.getBeanListtype_first)
          {
            CategoriesPListActivity.this.geneItems();
            if ((localList == null) || (localList.size() <= 0))
              break label491;
            Utils.loadImage(((SpecialFieldlistBean)localList.get(0)).getCatPic(), CategoriesPListActivity.this.zclp_toppc, CategoriesPListActivity.this);
          }
          while (true)
          {
            CategoriesPListActivity.this.categories_plist_listview = ((XListView)CategoriesPListActivity.this.findViewById(2131427375));
            CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(true);
            CategoriesPListActivity.this.categories_plist_listview.setPullRefreshEnable(true);
            CategoriesPListActivity.this.aaaImageListAdapter = new CategoriesPListAdapter(CategoriesPListActivity.this, CategoriesPListActivity.this, CategoriesPListActivity.this.item);
            CategoriesPListActivity.this.categories_plist_listview.setAdapter(CategoriesPListActivity.this.aaaImageListAdapter);
            CategoriesPListActivity.this.categories_plist_listview.setXListViewListener(CategoriesPListActivity.this);
            CategoriesPListActivity.this.mHandler = new Handler();
            if (paramInt == CommonRef.getBeanListtype_refresh)
            {
              CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(true);
              CategoriesPListActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CategoriesPListActivity.this.item.clear();
                  CategoriesPListActivity.this.geneItems();
                  CategoriesPListActivity.this.aaaImageListAdapter = new CategoriesPListAdapter(CategoriesPListActivity.this, CategoriesPListActivity.this, CategoriesPListActivity.this.item);
                  CategoriesPListActivity.this.categories_plist_listview.setAdapter(CategoriesPListActivity.this.aaaImageListAdapter);
                  CategoriesPListActivity.this.onLoad();
                }
              }
              , 0L);
            }
            if (paramInt == CommonRef.getBeanListtype_addmore)
              CategoriesPListActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CategoriesPListActivity.this.geneItems();
                  CategoriesPListActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  CategoriesPListActivity.this.onLoad();
                }
              }
              , 0L);
            if (paramInt == CommonRef.getBeanListtype_nomore)
            {
              CategoriesPListActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CategoriesPListActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  CategoriesPListActivity.this.onLoad();
                }
              }
              , 0L);
              CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
            }
            if ((CategoriesPListActivity.this.start == CommonRef.start) && ((CategoriesPListActivity.this.beanlists == null) || ((CategoriesPListActivity.this.start == CommonRef.start) && (CategoriesPListActivity.this.beanlists.size() < CategoriesPListActivity.this.pqty))))
              break;
            CategoriesPListActivity.this.beanlists.size();
            break;
            label491: Utils.loadImage("", CategoriesPListActivity.this.zclp_toppc, CategoriesPListActivity.this);
          }
        }
      }
      , this, this.imei, this.userId, this.cateId, pnum, this.pqty, this.porder);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localSpecialFieldlist1.execute(new JiaDeNetRequest[0]);
      return;
      label183: paramInt = CommonRef.getBeanListtype_nomore;
    }
  }

  private void getBeanList2(final int paramInt)
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
      SpecialFieldlist localSpecialFieldlist = new SpecialFieldlist(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          if (CategoriesPListActivity.this.categories_plist_listview != null)
            CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
          WaitLoadDialog.getInstance().dismissDialog();
          Utils.showDialog(CategoriesPListActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          String str = (String)paramAnonymousJiaDeNetResponse.result;
          if ((!"".equals(str)) && (!"ok".equals(str)))
          {
            Utils.showDialog(CategoriesPListActivity.this, "提示", str, 2130837514, null);
            if (CategoriesPListActivity.this.categories_plist_listview != null)
              CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
            WaitLoadDialog.getInstance().dismissDialog();
            return;
          }
          SpecialFieldlistManager localSpecialFieldlistManager = new SpecialFieldlistManager(CategoriesPListActivity.this);
          localSpecialFieldlistManager.openDataBase();
          List localList = localSpecialFieldlistManager.fetchAllDatas();
          localSpecialFieldlistManager.closeDataBase();
          CategoriesPListActivity.this.beanlists = localList;
          if ((localList == null) || (localList.size() < 10))
            CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
          if (paramInt == CommonRef.getBeanListtype_first)
          {
            CategoriesPListActivity.this.geneItems();
            if ((localList == null) || (localList.size() <= 0))
              break label491;
            Utils.loadImage(((SpecialFieldlistBean)localList.get(0)).getCatPic(), CategoriesPListActivity.this.zclp_toppc, CategoriesPListActivity.this);
          }
          while (true)
          {
            CategoriesPListActivity.this.categories_plist_listview = ((XListView)CategoriesPListActivity.this.findViewById(2131427375));
            CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(true);
            CategoriesPListActivity.this.categories_plist_listview.setPullRefreshEnable(true);
            CategoriesPListActivity.this.aaaImageListAdapter = new CategoriesPListAdapter(CategoriesPListActivity.this, CategoriesPListActivity.this, CategoriesPListActivity.this.item);
            CategoriesPListActivity.this.categories_plist_listview.setAdapter(CategoriesPListActivity.this.aaaImageListAdapter);
            CategoriesPListActivity.this.categories_plist_listview.setXListViewListener(CategoriesPListActivity.this);
            CategoriesPListActivity.this.mHandler = new Handler();
            if (paramInt == CommonRef.getBeanListtype_refresh)
            {
              CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(true);
              CategoriesPListActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CategoriesPListActivity.this.item.clear();
                  CategoriesPListActivity.this.geneItems();
                  CategoriesPListActivity.this.aaaImageListAdapter = new CategoriesPListAdapter(CategoriesPListActivity.this, CategoriesPListActivity.this, CategoriesPListActivity.this.item);
                  CategoriesPListActivity.this.categories_plist_listview.setAdapter(CategoriesPListActivity.this.aaaImageListAdapter);
                  CategoriesPListActivity.this.onLoad();
                }
              }
              , 0L);
            }
            if (paramInt == CommonRef.getBeanListtype_addmore)
              CategoriesPListActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CategoriesPListActivity.this.geneItems();
                  CategoriesPListActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  CategoriesPListActivity.this.onLoad();
                }
              }
              , 0L);
            if (paramInt == CommonRef.getBeanListtype_nomore)
            {
              CategoriesPListActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  CategoriesPListActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  CategoriesPListActivity.this.onLoad();
                }
              }
              , 0L);
              CategoriesPListActivity.this.categories_plist_listview.setPullLoadEnable(false);
            }
            if ((CategoriesPListActivity.this.start == CommonRef.start) && ((CategoriesPListActivity.this.beanlists == null) || ((CategoriesPListActivity.this.start == CommonRef.start) && (CategoriesPListActivity.this.beanlists.size() < CategoriesPListActivity.this.pqty))))
              break;
            CategoriesPListActivity.this.beanlists.size();
            break;
            label491: Utils.loadImage("", CategoriesPListActivity.this.zclp_toppc, CategoriesPListActivity.this);
          }
        }
      }
      , this, this.imei, this.userId, this.cateId, pnum, this.pqty, this.porder);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localSpecialFieldlist.execute(new JiaDeNetRequest[0]);
      return;
      label183: paramInt = CommonRef.getBeanListtype_nomore;
    }
  }

  private void init()
  {
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.categories_type = ((TextView)findViewById(2131427377));
    this.categories_plist_listview = ((XListView)findViewById(2131427375));
    if (!"".equals(this.zcName))
      this.titleName.setText(this.zcName);
    while (true)
    {
      this.categories_type.setVisibility(8);
      this.imei = Utils.getPhoneId();
      this.categories_plist_listview.setPullLoadEnable(true);
      this.categories_plist_listview.setPullRefreshEnable(true);
      this.categories_plist_listview.setAdapter(this.aaaImageListAdapter);
      this.categories_plist_listview.setXListViewListener(this);
      this.mHandler = new Handler();
      this.backImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CategoriesPListActivity.this.finish();
        }
      });
      this.title_fenlei.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
        }
      });
      return;
      this.titleName.setText("专场列表");
    }
  }

  private void onLoad()
  {
    this.categories_plist_listview.stopRefresh();
    this.categories_plist_listview.stopLoadMore();
    this.categories_plist_listview.setRefreshTime("刚刚");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      int i = paramIntent.getIntExtra("ppid", -1);
      String str = paramIntent.getStringExtra("jiage");
      if ((this.item != null) && (this.item.size() > i + 1) && (i != -1) && (!"".equals(str)))
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
    setContentView(2130903046);
    this.title_fenlei = ((LinearLayout)findViewById(2131427563));
    this.zclp_toppc = ((ImageView)findViewById(2131427376));
    this.zclb_name = ((TextView)findViewById(2131427378));
    this.zclb_startTime = ((TextView)findViewById(2131427379));
    this.zclb_endTime = ((TextView)findViewById(2131427380));
    init();
    String str1;
    String str2;
    if (getIntent().hasExtra("ChannelList1Bean"))
    {
      this.clbean = ((ChannelList1Bean)getIntent().getSerializableExtra("ChannelList1Bean"));
      this.cateId = this.clbean.getCatId();
      this.zcName = this.clbean.getCatTit();
      str1 = this.clbean.getTimeStart();
      str2 = this.clbean.getTimeEnd();
      this.clbean.getCatPicpath();
      if ("".equals(this.zcName))
        break label293;
      this.titleName.setText(this.zcName);
    }
    while (true)
    {
      this.zclb_name.setText(this.zcName);
      this.zclb_startTime.setText("上线时间：" + str1);
      this.zclb_endTime.setText("下线时间：" + str2);
      this.atype = "";
      if (getIntent().hasExtra("type"))
        this.atype = getIntent().getStringExtra("type");
      getBeanList(CommonRef.getBeanListtype_first);
      return;
      label293: this.titleName.setText("专场列表");
    }
  }

  protected void onDestroy()
  {
    this.atype = "";
    this.backImageView = null;
    this.titleName = null;
    this.categories_type = null;
    this.categories_plist_listview = null;
    this.mHandler = null;
    this.catTit = "";
    this.zcName = "";
    pnum = 1;
    this.cateId = "";
    this.zclp_toppc = null;
    this.zclb_name = null;
    this.zclb_startTime = null;
    this.zclb_endTime = null;
    this.title_fenlei = null;
    this.clbean = null;
    this.aaaImageListAdapter = null;
    this.beanlists = null;
    this.item = null;
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
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.CategoriesPListActivity
 * JD-Core Version:    0.6.2
 */