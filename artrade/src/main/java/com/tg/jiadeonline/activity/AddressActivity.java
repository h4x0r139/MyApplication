package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.date.ToAddrListManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.ToAddrDetail;
import com.tg.jiadeonline.net.ToAddrList;
import com.tg.jiadeonline.net.ToAddrMod;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends Activity
  implements XListView.IXListViewListener
{
  private static int refreshCnt = CommonRef.refreshCnt;
  private ImageListAdapter aaaImageListAdapter;
  List<AddressBean> addBeanList = new ArrayList();
  private String addressid = "";
  private String adduseType = CommonRef.adduseType_false;
  private ImageView backImageView;
  Button but = null;
  private Context context;
  private ViewHolder holder = new ViewHolder();
  private LinearLayout imageListView;
  private String imei;
  private LayoutInflater inflater;
  private boolean isfast = true;
  private boolean isxiadan = false;
  private List<AddressBean> item = new ArrayList();
  ListView listView = null;
  private Handler mHandler;
  private XListView mmListView;
  private int pageSize = CommonRef.pageSize;
  private String pnum = "1";
  private String pqty = "20";
  private Resources r;
  private int start = CommonRef.start;
  private TextView title_add;
  private TextView titlename;
  private String userid = "";
  int viewid = 2130903077;

  private void geneItems()
  {
    if ((this.addBeanList != null) && (this.addBeanList.size() > 0))
      this.item.addAll(this.addBeanList);
  }

  private void getBeanList(final int paramInt)
  {
    if (paramInt == CommonRef.getBeanListtype_refresh)
    {
      this.start = CommonRef.start;
      refreshCnt = CommonRef.refreshCnt;
    }
    if (paramInt == CommonRef.getBeanListtype_addmore)
    {
      if (((refreshCnt == 0) || (this.item.size() < refreshCnt * this.pageSize)) && ((refreshCnt != 0) || (this.item.size() < this.pageSize)))
        break label151;
      refreshCnt = 1 + refreshCnt;
    }
    while (true)
    {
      this.start = (refreshCnt * this.pageSize);
      ToAddrList localToAddrList = new ToAddrList(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          if (AddressActivity.this.item.size() <= 0)
          {
            AddressActivity.this.toAddAddress();
            return;
          }
          Utils.showDialog(AddressActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837567, null);
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          AddressActivity.this.mHandler = new Handler();
          AddressActivity.this.mmListView = ((XListView)AddressActivity.this.findViewById(2131427551));
          AddressActivity.this.mmListView.setPullLoadEnable(false);
          AddressActivity.this.mmListView.setPullRefreshEnable(false);
          String str = (String)paramAnonymousJiaDeNetResponse.result;
          if ((!"".equals(str)) && (!"ok".equals(str)))
          {
            Utils.showDialog(AddressActivity.this.context, "提示", str, 2130837514, null);
            AddressActivity.this.mmListView.setVisibility(8);
          }
          do
          {
            return;
            ToAddrListManager localToAddrListManager = new ToAddrListManager(AddressActivity.this.context);
            localToAddrListManager.openDataBase();
            AddressActivity.this.addBeanList = localToAddrListManager.fetchAllDatas();
            localToAddrListManager.closeDataBase();
            if ((AddressActivity.this.addBeanList == null) || (AddressActivity.this.addBeanList.size() <= 0))
            {
              Utils.showDialog(AddressActivity.this.context, "提示", "未查询到数据", 2130837514, null);
              AddressActivity.this.mmListView.setVisibility(8);
              return;
            }
            AddressActivity.this.mmListView.setVisibility(0);
            if (paramInt == CommonRef.getBeanListtype_first)
            {
              AddressActivity.this.geneItems();
              AddressActivity.this.mmListView.setPullLoadEnable(false);
              if (AddressActivity.this.item.size() <= 0)
              {
                AddressActivity.this.mmListView.setVisibility(8);
                return;
              }
              AddressActivity.this.mmListView.setVisibility(0);
              AddressActivity.this.aaaImageListAdapter = new AddressActivity.ImageListAdapter(AddressActivity.this, AddressActivity.this, 2130903078, AddressActivity.this.item);
              AddressActivity.this.mmListView.setAdapter(AddressActivity.this.aaaImageListAdapter);
              AddressActivity.this.mmListView.setXListViewListener(AddressActivity.this);
            }
            if (paramInt == CommonRef.getBeanListtype_refresh)
              AddressActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  AddressActivity.this.item.clear();
                  AddressActivity.this.geneItems();
                  if (AddressActivity.this.item.size() <= 0)
                  {
                    AddressActivity.this.mmListView.setVisibility(8);
                    return;
                  }
                  AddressActivity.this.mmListView.setVisibility(0);
                  AddressActivity.this.aaaImageListAdapter = new AddressActivity.ImageListAdapter(AddressActivity.this, AddressActivity.this, 2130903078, AddressActivity.this.item);
                  AddressActivity.this.mmListView.setAdapter(AddressActivity.this.aaaImageListAdapter);
                  AddressActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  AddressActivity.this.onLoad();
                }
              }
              , 0L);
            if (paramInt == CommonRef.getBeanListtype_addmore)
              AddressActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  AddressActivity.this.geneItems();
                  if (AddressActivity.this.item.size() <= 0)
                  {
                    AddressActivity.this.mmListView.setVisibility(8);
                    return;
                  }
                  AddressActivity.this.mmListView.setVisibility(0);
                  AddressActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  AddressActivity.this.onLoad();
                }
              }
              , 0L);
            if (paramInt == CommonRef.getBeanListtype_nomore)
            {
              AddressActivity.this.mHandler.postDelayed(new Runnable()
              {
                public void run()
                {
                  AddressActivity.this.aaaImageListAdapter.notifyDataSetChanged();
                  AddressActivity.this.onLoad();
                }
              }
              , 0L);
              AddressActivity.this.alertDialog("已无更多数据");
            }
          }
          while ((AddressActivity.this.start != CommonRef.start) || (AddressActivity.this.addBeanList.size() > 0));
          AddressActivity.this.toAddAddress();
        }
      }
      , this.context, this.imei, this.userid, this.pnum, this.pqty);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localToAddrList.execute(new JiaDeNetRequest[0]);
      return;
      label151: paramInt = CommonRef.getBeanListtype_nomore;
    }
  }

  private void onLoad()
  {
    this.mmListView.stopRefresh();
    this.mmListView.stopLoadMore();
    this.mmListView.setRefreshTime("刚刚");
  }

  public void alertDialog(String paramString)
  {
    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage(paramString).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).show();
  }

  public void alertDialogOfUes(String paramString)
  {
    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage(paramString).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).show();
  }

  public void alertDialogOfdelectSuccess(String paramString)
  {
    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage(paramString).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AddressActivity.this.item.clear();
        AddressActivity.this.onRefresh();
      }
    }).show();
  }

  public void alertdeleteilog(final String paramString)
  {
    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("您确定要删除此条地址信息吗？").setIcon(2130837565).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AddressActivity.this.deleteAddress(paramString);
      }
    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).create().show();
  }

  public void deleteAddress(String paramString)
  {
    ToAddrMod localToAddrMod = new ToAddrMod(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(AddressActivity.this.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if (!"ok".equals(str))
        {
          Utils.showDialog(AddressActivity.this.context, "提示", str, 2130837514, null);
          return;
        }
        Utils.showDialog(AddressActivity.this.context, "提示", "删除成功", 2130837514, null);
        AddressActivity.this.onRefresh();
      }
    }
    , this.context, this.imei, this.userid, "del", paramString);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localToAddrMod.execute(new JiaDeNetRequest[0]);
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903077);
    this.context = this;
    this.imei = Utils.getPhoneId();
    this.titlename = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.title_add = ((TextView)findViewById(2131427562));
    this.title_add.setVisibility(0);
    this.r = getResources();
    Intent localIntent = getIntent();
    this.adduseType = localIntent.getStringExtra(CommonRef.Address_adduseType);
    if (localIntent.hasExtra(CommonRef.isxiadan))
      this.isxiadan = true;
    this.title_add.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AddressActivity.this.toNewAddAddress();
      }
    });
    this.titlename.setText("地址管理");
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AddressActivity.this.finish();
      }
    });
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
    {
      this.userid = ((LoginBean)localList.get(0)).getUserId();
      return;
    }
    new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AddressActivity.this.finish();
        Intent localIntent = new Intent(AddressActivity.this, LoginActivity.class);
        AddressActivity.this.startActivity(localIntent);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        AddressActivity.this.finish();
      }
    }).show();
  }

  protected void onDestroy()
  {
    this.but = null;
    this.listView = null;
    this.item = null;
    this.addBeanList = null;
    this.mmListView = null;
    this.aaaImageListAdapter = null;
    this.titlename = null;
    this.backImageView = null;
    this.title_add = null;
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

  protected void onResume()
  {
    onRefresh();
    super.onResume();
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void toAddAddress()
  {
    Intent localIntent = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putString(CommonRef.Address_adduseType, this.adduseType);
    localIntent.putExtras(localBundle);
    localIntent.setClass(this, AddressNoActivity.class);
    startActivity(localIntent);
  }

  public void toAddAddress(int paramInt, AddressBean paramAddressBean)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt(CommonRef.AddressAdd_Addresstype, paramInt);
    localBundle.putString(CommonRef.Address_adduseType, this.adduseType);
    localBundle.putSerializable(CommonRef.AddressAdd_AddressBean, paramAddressBean);
    Intent localIntent = new Intent();
    localIntent.putExtras(localBundle);
    localIntent.setClass(this, AddressAddActivity.class);
    startActivity(localIntent);
  }

  public void toNewAddAddress()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt(CommonRef.AddressAdd_Addresstype, CommonRef.Addresstype_add);
    localBundle.putString(CommonRef.Address_adduseType, this.adduseType);
    localBundle.putSerializable(CommonRef.AddressAdd_AddressBean, new AddressBean());
    Intent localIntent = new Intent();
    localIntent.putExtras(localBundle);
    localIntent.setClass(this, AddressAddActivity.class);
    startActivity(localIntent);
  }

  public class ImageListAdapter extends ArrayAdapter<AddressBean>
  {
    private int resource;

    public ImageListAdapter(int paramList, List<AddressBean> arg3)
    {
      super(i, localList);
      this.resource = i;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      AddressBean localAddressBean = (AddressBean)getItem(paramInt);
      final String str1 = localAddressBean.getAutoid();
      final String str2 = localAddressBean.getProv();
      final String str3 = localAddressBean.getCity();
      final String str4 = localAddressBean.getCounty();
      final String str5 = localAddressBean.getAddress();
      localAddressBean.getZipcode();
      final String str6 = localAddressBean.getTel();
      String str7 = localAddressBean.getIsDefault();
      final String str8 = localAddressBean.getName();
      localAddressBean.getIdentityCard();
      localAddressBean.getIcPicUp();
      localAddressBean.getIcPicDown();
      localAddressBean.getQq();
      AddressActivity.this.imageListView = new LinearLayout(getContext());
      AddressActivity.this.inflater = ((LayoutInflater)getContext().getSystemService("layout_inflater"));
      AddressActivity.this.inflater.inflate(this.resource, AddressActivity.this.imageListView, true);
      AddressActivity.this.holder.add_old_delete = ((ImageView)AddressActivity.this.imageListView.findViewById(2131427552));
      AddressActivity.this.holder.add_old_update = ((ImageView)AddressActivity.this.imageListView.findViewById(2131427553));
      AddressActivity.this.holder.address_old_address = ((TextView)AddressActivity.this.imageListView.findViewById(2131427555));
      AddressActivity.this.holder.address_old_accno = ((TextView)AddressActivity.this.imageListView.findViewById(2131427556));
      AddressActivity.this.holder.address_old_tel = ((TextView)AddressActivity.this.imageListView.findViewById(2131427557));
      AddressActivity.this.holder.add_old_but_default = ((Button)AddressActivity.this.imageListView.findViewById(2131427559));
      AddressActivity.this.holder.add_old_but_use = ((Button)AddressActivity.this.imageListView.findViewById(2131427558));
      AddressActivity.this.holder.address_old_address.setText(str2 + str3 + str4 + str5);
      AddressActivity.this.holder.address_old_accno.setText(str8);
      AddressActivity.this.holder.address_old_tel.setText(str6);
      AddressActivity.this.holder.add_old_delete.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AddressActivity.this.alertdeleteilog(str1);
        }
      });
      AddressActivity.this.holder.add_old_update.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ToAddrDetail localToAddrDetail = new ToAddrDetail(new JiaDeNetRequestTask.JiaDeNetCallback()
          {
            public void onCanceled()
            {
              WaitLoadDialog.getInstance().dismissDialog();
            }

            public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              if (AddressActivity.this.item.size() <= 0)
              {
                AddressActivity.this.toAddAddress();
                return;
              }
              Utils.showDialog(AddressActivity.this, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837567, null);
            }

            public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              String str = (String)paramAnonymous2JiaDeNetResponse.result;
              if ((!"".equals(str)) && (!"ok".equals(str)))
              {
                Utils.showDialog(AddressActivity.this.context, "提示", str, 2130837514, null);
                return;
              }
              ToAddrListManager localToAddrListManager = new ToAddrListManager(AddressActivity.this.context);
              localToAddrListManager.openDataBase();
              new ArrayList();
              AddressBean localAddressBean = (AddressBean)localToAddrListManager.fetchAllDatas().get(0);
              localToAddrListManager.closeDataBase();
              AddressActivity.this.toAddAddress(CommonRef.Addresstype_update, localAddressBean);
            }
          }
          , AddressActivity.this.context, AddressActivity.this.imei, AddressActivity.this.userid, str1);
          WaitLoadDialog.getInstance().showDialog(AddressActivity.this, null, true);
          localToAddrDetail.execute(new JiaDeNetRequest[0]);
        }
      });
      if (str7.equals("N"))
      {
        AddressActivity.this.holder.add_old_but_default.setText("设为默认");
        AddressActivity.this.holder.add_old_but_default.setBackgroundColor(AddressActivity.this.r.getColor(2131034225));
      }
      while (true)
      {
        AddressActivity.this.holder.add_old_but_default.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ToAddrMod localToAddrMod = new ToAddrMod(new JiaDeNetRequestTask.JiaDeNetCallback()
            {
              public void onCanceled()
              {
                WaitLoadDialog.getInstance().dismissDialog();
              }

              public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                Utils.showDialog(AddressActivity.this.context, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
              }

              public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                String str = paramAnonymous2JiaDeNetResponse.errMsg;
                if ((!"".equals(str)) && (!"ok".equals(str)))
                {
                  Utils.showDialog(AddressActivity.this.context, "提示", str, 2130837514, null);
                  return;
                }
                AddressActivity.this.getBeanList(CommonRef.getBeanListtype_refresh);
              }
            }
            , AddressActivity.this.context, AddressActivity.this.imei, AddressActivity.this.userid, "def", str1);
            WaitLoadDialog.getInstance().showDialog(AddressActivity.this, null, true);
            localToAddrMod.execute(new JiaDeNetRequest[0]);
          }
        });
        AddressActivity.this.holder.add_old_but_use.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (!AddressActivity.this.isxiadan)
            {
              AddressActivity.this.errormsg("您未处于订单处理流程,不能使用该功能!");
              return;
            }
            ToAddrMod localToAddrMod = new ToAddrMod(new JiaDeNetRequestTask.JiaDeNetCallback()
            {
              public void onCanceled()
              {
                WaitLoadDialog.getInstance().dismissDialog();
              }

              public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                Utils.showDialog(AddressActivity.this.context, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
              }

              public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                String str = paramAnonymous2JiaDeNetResponse.errMsg;
                if ((!"".equals(str)) && (!"ok".equals(str)))
                  Utils.showDialog(AddressActivity.this.context, "提示", str, 2130837514, null);
                CommonRef.useAddress = this.val$autoid;
                CommonRef.pay_name = this.val$name;
                CommonRef.pay_phone = this.val$tel;
                CommonRef.addmes = this.val$prov + this.val$city + this.val$county + this.val$address;
                AddressActivity.this.alertDialogOfUes("已使用");
                AddressActivity.this.finish();
              }
            }
            , AddressActivity.this.context, AddressActivity.this.imei, AddressActivity.this.userid, "use", str1);
            WaitLoadDialog.getInstance().showDialog(AddressActivity.this, null, true);
            localToAddrMod.execute(new JiaDeNetRequest[0]);
          }
        });
        return AddressActivity.this.imageListView;
        AddressActivity.this.holder.add_old_but_default.setText("默认");
      }
    }
  }

  class ViewHolder
  {
    Button add_old_but_default;
    Button add_old_but_use;
    ImageView add_old_delete;
    ImageView add_old_update;
    TextView address_old_accno;
    TextView address_old_address;
    TextView address_old_tel;

    ViewHolder()
    {
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.AddressActivity
 * JD-Core Version:    0.6.2
 */