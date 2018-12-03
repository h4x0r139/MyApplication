package com.tg.jiadeonline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.adapter.CategoriexZtAdapter;
import com.tg.jiadeonline.bean.CategoryBean;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.CategoryManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.Category;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoriesZtActivity extends Activity
  implements XListView.IXListViewListener
{
  private CategoriexZtAdapter aaaImageListAdapter;
  private ImageView backText;
  private TextView categories_type;
  private XListView categories_zt_listview;
  private ChannelList1Bean clbean;
  private String imei = "";
  private boolean ischeck = true;
  private List<CategoryBean> listbean;
  private String pcateId = "";
  private int pnum = 1;
  private String porder = "";
  private int pqty = 10;
  private TextView titleName;
  private String userId = "";
  private String zcName;
  private TextView zclb_endTime;
  private TextView zclb_name;
  private TextView zclb_startTime;
  private ImageView zclp_toppc;

  private boolean getistime(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date localDate1 = localSimpleDateFormat.parse(paramString1);
      Date localDate2 = localSimpleDateFormat.parse(paramString2);
      long l1 = localDate1.getTime();
      long l2 = localDate2.getTime();
      if (l1 > l2)
        return true;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return false;
  }

  private void onLoad()
  {
    this.categories_zt_listview.stopRefresh();
    this.categories_zt_listview.stopLoadMore();
    this.categories_zt_listview.setRefreshTime("刚刚");
  }

  public void initDATA()
  {
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userId = ((LoginBean)localList.get(0)).getUserId();
    Category localCategory = new Category(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(CategoriesZtActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
        if (CategoriesZtActivity.this.categories_zt_listview != null)
          CategoriesZtActivity.this.categories_zt_listview.setPullLoadEnable(false);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str)) && (!"ok".equals(str)))
          Utils.showDialog(CategoriesZtActivity.this, "提示", str, 2130837514, null);
        while (true)
        {
          CategoriesZtActivity.this.onLoad();
          WaitLoadDialog.getInstance().dismissDialog();
          return;
          CategoryManager localCategoryManager = new CategoryManager(CategoriesZtActivity.this);
          localCategoryManager.openDataBase();
          new ArrayList();
          List localList = localCategoryManager.fetchAllDatas();
          localCategoryManager.closeDataBase();
          if ((localList != null) && (localList.size() > 0))
          {
            ((CategoryBean)localList.get(0));
            CategoriesZtActivity.this.listbean.addAll(localList);
            if (CategoriesZtActivity.this.pnum == 1)
            {
              CategoriesZtActivity.this.aaaImageListAdapter = new CategoriexZtAdapter(CategoriesZtActivity.this, CategoriesZtActivity.this, CategoriesZtActivity.this.listbean);
              CategoriesZtActivity.this.categories_zt_listview.setAdapter(CategoriesZtActivity.this.aaaImageListAdapter);
            }
            while (true)
            {
              CategoriesZtActivity localCategoriesZtActivity = CategoriesZtActivity.this;
              localCategoriesZtActivity.pnum = (1 + localCategoriesZtActivity.pnum);
              if (localList.size() >= CategoriesZtActivity.this.pqty)
                break;
              CategoriesZtActivity.this.categories_zt_listview.setPullLoadEnable(false);
              break;
              CategoriesZtActivity.this.aaaImageListAdapter.notifyDataSetChanged();
            }
          }
          CategoriesZtActivity.this.categories_zt_listview.setPullLoadEnable(false);
        }
      }
    }
    , this, this.imei, this.userId, this.pcateId, this.pnum, this.pqty, this.porder);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localCategory.execute(new JiaDeNetRequest[0]);
  }

  public void initID()
  {
    this.backText = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.categories_zt_listview = ((XListView)findViewById(2131427392));
    this.zclp_toppc = ((ImageView)findViewById(2131427376));
    this.categories_type = ((TextView)findViewById(2131427377));
    this.zclb_name = ((TextView)findViewById(2131427378));
    this.zclb_startTime = ((TextView)findViewById(2131427379));
    this.zclb_endTime = ((TextView)findViewById(2131427380));
    this.imei = Utils.getPhoneId();
    this.listbean = new ArrayList();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903049);
    initID();
    if (getIntent().hasExtra("ChannelList1Bean"))
    {
      this.clbean = ((ChannelList1Bean)getIntent().getSerializableExtra("ChannelList1Bean"));
      this.pcateId = this.clbean.getCatId();
      this.zcName = this.clbean.getCatTit();
      if ("".equals(this.zcName))
        break label192;
      this.titleName.setText(this.zcName);
    }
    while (true)
    {
      Utils.loadImage(this.clbean.getCatPicpath(), this.zclp_toppc, this);
      this.zclb_startTime.setText(this.clbean.getTimeStart());
      this.zclb_endTime.setText(this.clbean.getTimeEnd());
      this.zclb_name.setText(this.zcName);
      this.categories_zt_listview.setXListViewListener(this);
      initDATA();
      this.categories_zt_listview.setPullRefreshEnable(true);
      this.categories_zt_listview.setPullLoadEnable(true);
      this.backText.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CategoriesZtActivity.this.finish();
        }
      });
      return;
      label192: this.titleName.setText("专场列表");
    }
  }

  protected void onDestroy()
  {
    this.backText = null;
    this.titleName = null;
    this.categories_zt_listview = null;
    this.zclp_toppc = null;
    this.categories_type = null;
    this.zclb_name = null;
    this.zclb_startTime = null;
    this.zclb_endTime = null;
    if (this.listbean != null)
      this.listbean = null;
    super.onDestroy();
  }

  public void onLoadMore()
  {
    initDATA();
  }

  public void onRefresh()
  {
    this.pnum = 1;
    this.listbean.clear();
    initDATA();
    this.categories_zt_listview.setPullLoadEnable(true);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.CategoriesZtActivity
 * JD-Core Version:    0.6.2
 */