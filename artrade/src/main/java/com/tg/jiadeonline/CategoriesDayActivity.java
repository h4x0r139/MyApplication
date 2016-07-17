package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.tg.jiadeonline.adapter.CategoriesDayMonthListAdapter;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.CategoryManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.net.Category;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDayActivity extends Activity
{
  CategoriesDayMonthListAdapter aaaImageListAdapter;
  private ImageView backImageView;
  private String catId = "";
  private XListView categories_plist_listview;
  private TextView categories_type;
  private Context context;
  private String imei = "";
  private Handler mHandler;
  private String pnum = "1";
  private String pqty = "20";
  private TextView titleName;
  private String userId = "";

  private void init()
  {
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.categories_type = ((TextView)findViewById(2131427377));
    this.categories_plist_listview = ((XListView)findViewById(2131427375));
    this.titleName.setText("周度拍卖会");
    this.categories_type.setVisibility(8);
    this.categories_plist_listview.setPullLoadEnable(true);
    this.categories_plist_listview.setPullRefreshEnable(true);
    this.mHandler = new Handler();
    Bundle localBundle = getIntent().getExtras();
    if (localBundle.containsKey("catId"))
      this.catId = localBundle.getString("catId");
    Category localCategory = new Category(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(CategoriesDayActivity.this.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str)) && (!"ok".equals(str)))
          Utils.showDialog(CategoriesDayActivity.this.context, "提示", str, 2130837514, null);
        List localList;
        do
        {
          return;
          CategoryManager localCategoryManager = new CategoryManager(CategoriesDayActivity.this.context);
          localCategoryManager.openDataBase();
          new ArrayList();
          localList = localCategoryManager.fetchAllDatas();
          localCategoryManager.closeDataBase();
        }
        while ((localList == null) || (localList.size() <= 0));
        CategoriesDayActivity.this.categories_plist_listview.setAdapter(new CategoriesDayMonthListAdapter(CategoriesDayActivity.this.context, CategoriesDayActivity.this, localList));
      }
    }
    , this.context, Utils.getPhoneId(), this.userId, this.catId, this.pnum, this.pqty, "");
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localCategory.execute(new JiaDeNetRequest[0]);
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoriesDayActivity.this.finish();
      }
    });
  }

  private void onLoad()
  {
    this.categories_plist_listview.stopRefresh();
    this.categories_plist_listview.stopLoadMore();
    this.categories_plist_listview.setRefreshTime("刚刚");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903045);
    Exit.getInstance().addActivity(this);
    this.imei = Utils.getPhoneId();
    this.context = this;
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
    {
      this.userId = ((LoginBean)localList.get(0)).getUserId();
      init();
      return;
    }
    new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        CategoriesDayActivity.this.finish();
        Intent localIntent = new Intent(CategoriesDayActivity.this, LoginActivity.class);
        CategoriesDayActivity.this.startActivity(localIntent);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        CategoriesDayActivity.this.finish();
      }
    }).show();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.CategoriesDayActivity
 * JD-Core Version:    0.6.2
 */