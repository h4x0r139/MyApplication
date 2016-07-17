package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.activity.AddressActivity;
import com.tg.jiadeonline.activity.AddressAddActivity;
import com.tg.jiadeonline.bean.AddOrderBean;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.bean.ItemsBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.UserFsBean;
import com.tg.jiadeonline.date.AddOrderManager;
import com.tg.jiadeonline.date.ForSaleInfoManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.date.ToAddrListManager;
import com.tg.jiadeonline.net.AddOrder;
import com.tg.jiadeonline.net.ForSaleInfo;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.ToAddrList;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayActivity extends Activity
{
  List<AddressBean> addBeanList = new ArrayList();
  private TextView addmes;
  private ImageView backImageView;
  ArrayList<ItemsBean> beanList = new ArrayList();
  private Context context;
  private EditText ed_fapiao;
  private TextView fk_kd;
  private TextView fk_sps;
  private TextView fk_yhjxq;
  private TextView fk_yhqjg;
  private TextView fk_yj;
  private TextView fk_yjq;
  private TextView fk_zj;
  private TextView fk_zsf;
  private String forSaleId = "";
  private String forSaleMoney = "";
  private String imei = "";
  boolean isFirst = true;
  private JSONArray itemData;
  private String logistics_id;
  private PayAdapter pAdapter = null;
  private LinearLayout pay_address_msg;
  private TextView pay_allMoney;
  private TextView pay_name;
  private TextView pay_phone;
  private String pnum = "1";
  private String pqty = "20";
  private ListView scrollListView;
  private TextView text_pay;
  private String userId = "";
  private UserFsBean userfsbean;
  private CheckBox yhq_chekbox;
  private CheckBox yhq_chekbox1;
  private LinearLayout yhq_lin;
  private String youhuiquanid = "";

  private void initAddress()
  {
    ToAddrList localToAddrList = new ToAddrList(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(PayActivity.this.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str1 = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str1)) && (!"ok".equals(str1)))
        {
          PayActivity.this.logistics_id = "0";
          PayActivity.this.pay_name.setText("请先设置地址！");
          return;
        }
        ToAddrListManager localToAddrListManager = new ToAddrListManager(PayActivity.this.context);
        localToAddrListManager.openDataBase();
        PayActivity.this.addBeanList = localToAddrListManager.fetchAllDatas();
        localToAddrListManager.closeDataBase();
        if ((PayActivity.this.addBeanList == null) || (PayActivity.this.addBeanList.size() <= 0))
        {
          PayActivity.this.logistics_id = "0";
          PayActivity.this.pay_name.setText("请先设置地址！");
          return;
        }
        label640: for (int i = 0; ; i++)
        {
          if (i >= PayActivity.this.addBeanList.size());
          while ("".equals(PayActivity.this.addmes.getText().toString()))
          {
            PayActivity.this.logistics_id = ((AddressBean)PayActivity.this.addBeanList.get(0)).getAutoid();
            String str3 = ((AddressBean)PayActivity.this.addBeanList.get(0)).getAddress();
            PayActivity.this.pay_name.setText(((AddressBean)PayActivity.this.addBeanList.get(0)).getName());
            PayActivity.this.pay_phone.setText(((AddressBean)PayActivity.this.addBeanList.get(0)).getTel());
            PayActivity.this.addmes.setText(str3);
            CommonRef.useAddress = PayActivity.this.logistics_id;
            return;
            if ((PayActivity.this.logistics_id != null) && (!"".equals(PayActivity.this.logistics_id)))
              break label503;
            if ("N".equals(((AddressBean)PayActivity.this.addBeanList.get(i)).getIsDefault()))
              break label640;
            PayActivity.this.logistics_id = ((AddressBean)PayActivity.this.addBeanList.get(i)).getAutoid();
            String str2 = ((AddressBean)PayActivity.this.addBeanList.get(i)).getAddress();
            PayActivity.this.addmes.setText(str2);
            PayActivity.this.pay_name.setText(((AddressBean)PayActivity.this.addBeanList.get(i)).getName());
            PayActivity.this.pay_phone.setText(((AddressBean)PayActivity.this.addBeanList.get(i)).getTel());
            CommonRef.defaAddress = PayActivity.this.logistics_id;
            CommonRef.useAddress = PayActivity.this.logistics_id;
          }
          break;
          label503: if (PayActivity.this.logistics_id.equals(((AddressBean)PayActivity.this.addBeanList.get(i)).getAutoid()))
          {
            String str4 = ((AddressBean)PayActivity.this.addBeanList.get(i)).getAddress();
            PayActivity.this.pay_name.setText(((AddressBean)PayActivity.this.addBeanList.get(i)).getName());
            PayActivity.this.pay_phone.setText(((AddressBean)PayActivity.this.addBeanList.get(i)).getTel());
            PayActivity.this.addmes.setText(str4);
            CommonRef.useAddress = PayActivity.this.logistics_id;
          }
        }
      }
    }
    , this.context, this.imei, this.userId, this.pnum, this.pqty);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localToAddrList.execute(new JiaDeNetRequest[0]);
  }

  private void initYHQ()
  {
    ForSaleInfo localForSaleInfo = new ForSaleInfo(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(PayActivity.this.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str)) && (!"ok".equals(str)))
        {
          Utils.showDialog(PayActivity.this.context, "提示", str, 2130837514, null);
          return;
        }
        ForSaleInfoManager localForSaleInfoManager = new ForSaleInfoManager(PayActivity.this.context);
        localForSaleInfoManager.openDataBase();
        List localList = localForSaleInfoManager.fetchAllDatas();
        localForSaleInfoManager.closeDataBase();
        if ((localList == null) || (localList.size() <= 0))
        {
          PayActivity.this.yhq_lin.setVisibility(8);
          PayActivity.this.fk_yhqjg.setText("");
        }
        while (true)
        {
          PayActivity.this.yhq_chekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
          {
            public void onCheckedChanged(CompoundButton paramAnonymous2CompoundButton, boolean paramAnonymous2Boolean)
            {
              if (paramAnonymous2Boolean)
              {
                if (PayActivity.this.userfsbean != null)
                {
                  PayActivity.this.fk_yhqjg.setText("- " + PayActivity.this.userfsbean.getUserFSFee());
                  return;
                }
                PayActivity.this.yhq_chekbox.setChecked(false);
                return;
              }
              PayActivity.this.fk_yhqjg.setText("");
            }
          });
          PayActivity.this.yhq_chekbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
          {
            public void onCheckedChanged(CompoundButton paramAnonymous2CompoundButton, boolean paramAnonymous2Boolean)
            {
              if (paramAnonymous2Boolean)
              {
                PayActivity.this.ed_fapiao.setVisibility(0);
                return;
              }
              PayActivity.this.ed_fapiao.setText("");
              PayActivity.this.ed_fapiao.setVisibility(8);
            }
          });
          return;
          PayActivity.this.yhq_lin.setVisibility(0);
          PayActivity.this.userfsbean = ((UserFsBean)localList.get(0));
          PayActivity.this.fk_yjq.setText(PayActivity.this.userfsbean.getUserFSFee());
          PayActivity.this.fk_yjq.setText(PayActivity.this.userfsbean.getUserFSFee());
          PayActivity.this.fk_yhqjg.setText("- " + PayActivity.this.userfsbean.getUserFSFee());
          PayActivity.this.yhq_chekbox.setChecked(true);
        }
      }
    }
    , this.context, this.imei, this.userId);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localForSaleInfo.execute(new JiaDeNetRequest[0]);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903063);
    Exit.getInstance().addActivity(this);
    this.imei = Utils.getPhoneId();
    this.logistics_id = CommonRef.useAddress;
    this.context = this;
    this.beanList = ((ArrayList)getIntent().getSerializableExtra("payList"));
    this.fk_yhjxq = ((TextView)findViewById(2131427454));
    this.text_pay = ((TextView)findViewById(2131427460));
    this.fk_sps = ((TextView)findViewById(2131427446));
    this.fk_zj = ((TextView)findViewById(2131427447));
    this.fk_yj = ((TextView)findViewById(2131427448));
    this.fk_zsf = ((TextView)findViewById(2131427449));
    this.fk_kd = ((TextView)findViewById(2131427450));
    this.fk_yjq = ((TextView)findViewById(2131427453));
    this.yhq_chekbox = ((CheckBox)findViewById(2131427452));
    this.yhq_lin = ((LinearLayout)findViewById(2131427400));
    this.fk_yhqjg = ((TextView)findViewById(2131427459));
    this.yhq_chekbox1 = ((CheckBox)findViewById(2131427455));
    this.ed_fapiao = ((EditText)findViewById(2131427456));
    this.ed_fapiao.setVisibility(8);
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
    {
      this.userId = ((LoginBean)localList.get(0)).getUserId();
      initAddress();
    }
    while (true)
    {
      this.backImageView = ((ImageView)findViewById(2131427568));
      this.backImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PayActivity.this.finish();
        }
      });
      this.text_pay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ("0".equals(PayActivity.this.logistics_id))
          {
            Utils.showDialog(PayActivity.this, "提示", "请先设置地址", 2130837514, null);
            return;
          }
          if ((PayActivity.this.yhq_chekbox.isChecked()) && ("".equals(PayActivity.this.ed_fapiao.getText().toString())))
          {
            Utils.showDialog(PayActivity.this, "提示", "请填写发票抬头", 2130837514, null);
            return;
          }
          if ((PayActivity.this.pAdapter.getBeanList() != null) && (PayActivity.this.pAdapter.getBeanList().size() > 0))
          {
            PayActivity.this.itemData = new JSONArray();
            int i = 0;
            int j = PayActivity.this.pAdapter.getBeanList().size();
            label174: JiaDeNetRequestTask.JiaDeNetCallback local1;
            PayActivity localPayActivity;
            String str5;
            String str6;
            String str7;
            String str8;
            label280: String str9;
            label307: JSONArray localJSONArray;
            if (i >= j)
            {
              if (!PayActivity.this.yhq_chekbox1.isChecked())
                break label546;
              PayActivity.this.ed_fapiao.getText().toString();
              PayActivity.this.ed_fapiao.getText().toString();
              PayActivity.this.forSaleMoney = CommonRef.moneyfsfororder;
              PayActivity.this.forSaleId = CommonRef.myfsfororder;
              local1 = new JiaDeNetRequestTask.JiaDeNetCallback()
              {
                public void onCanceled()
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                }

                public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                  Utils.showDialog(PayActivity.this, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
                }

                public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
                {
                  if ((paramAnonymous2JiaDeNetResponse.result != null) && ("ok".equals(paramAnonymous2JiaDeNetResponse.result)))
                  {
                    AddOrderManager localAddOrderManager = new AddOrderManager(PayActivity.this);
                    localAddOrderManager.openDataBase();
                    new ArrayList();
                    List localList = localAddOrderManager.fetchAllDatas();
                    if ((localList != null) && (localList.size() > 0))
                    {
                      String str = ((AddOrderBean)localList.get(0)).getNetOrderId();
                      Intent localIntent = new Intent(PayActivity.this, ChosePayActivity.class);
                      localIntent.putExtra("netOrderId", str);
                      localIntent.putExtra("type", "1");
                      PayActivity.this.startActivity(localIntent);
                      PayActivity.this.finish();
                    }
                  }
                  while (true)
                  {
                    WaitLoadDialog.getInstance().dismissDialog();
                    return;
                    Utils.showDialog(PayActivity.this, "提示", paramAnonymous2JiaDeNetResponse.result.toString(), 2130837514, null);
                  }
                }
              };
              localPayActivity = PayActivity.this;
              str5 = PayActivity.this.imei;
              str6 = PayActivity.this.userId;
              str7 = PayActivity.this.logistics_id;
              if ((PayActivity.this.yhq_chekbox.isChecked()) && (PayActivity.this.userfsbean != null))
                break label549;
              str8 = "";
              if ((PayActivity.this.yhq_chekbox.isChecked()) && (PayActivity.this.userfsbean != null))
                break label564;
              str9 = "";
              localJSONArray = PayActivity.this.itemData;
              if (!PayActivity.this.yhq_chekbox1.isChecked())
                break label579;
            }
            label546: label549: label564: label579: for (String str10 = PayActivity.this.ed_fapiao.getText().toString(); ; str10 = "")
            {
              while (true)
              {
                AddOrder localAddOrder = new AddOrder(local1, localPayActivity, str5, str6, str7, "", str8, str9, localJSONArray, str10);
                WaitLoadDialog.getInstance().showDialog(PayActivity.this, null, true);
                localAddOrder.execute(new JiaDeNetRequest[0]);
                return;
                ItemsBean localItemsBean = (ItemsBean)PayActivity.this.pAdapter.getBeanList().get(i);
                String str1 = localItemsBean.getItemnum();
                String str2 = localItemsBean.getPriceBid();
                String str3 = localItemsBean.getCommision();
                String str4;
                JSONObject localJSONObject;
                if ("1".equals(localItemsBean.getIscheck()))
                {
                  str4 = "Y";
                  localItemsBean.getCertFee();
                  localJSONObject = new JSONObject();
                }
                try
                {
                  localJSONObject.put("itemnum", str1);
                  localJSONObject.put("priceBid", str2);
                  localJSONObject.put("commision", str3);
                  localJSONObject.put("isNeedCer", str4);
                  PayActivity.this.itemData.put(localJSONObject);
                  i++;
                  break;
                  str4 = "N";
                }
                catch (JSONException localJSONException)
                {
                  while (true)
                    localJSONException.printStackTrace();
                }
              }
              break label174;
              str8 = PayActivity.this.userfsbean.getUserFSId();
              break label280;
              str9 = PayActivity.this.userfsbean.getUserFSFee();
              break label307;
            }
          }
          Utils.showDialog(PayActivity.this, "提示", "没有订单需要付款！", 2130837514, null);
        }
      });
      this.pay_allMoney = ((TextView)findViewById(2131427458));
      this.scrollListView = ((ListView)findViewById(2131427443));
      if ((this.beanList != null) && (this.beanList.size() > 0))
      {
        this.pAdapter = new PayAdapter(this, this, this.beanList);
        this.scrollListView.setAdapter(this.pAdapter);
        Utils.setListViewHeightBasedOnChildren1(this.scrollListView);
      }
      this.pay_address_msg = ((LinearLayout)findViewById(2131427437));
      this.pay_address_msg = ((LinearLayout)findViewById(2131427437));
      this.addmes = ((TextView)findViewById(2131427441));
      this.pay_name = ((TextView)findViewById(2131427439));
      this.pay_phone = ((TextView)findViewById(2131427440));
      this.pay_address_msg.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if ("".equals(PayActivity.this.addmes.getText()))
          {
            Bundle localBundle = new Bundle();
            localBundle.putInt(CommonRef.AddressAdd_Addresstype, CommonRef.Addresstype_add);
            localBundle.putString(CommonRef.Address_adduseType, CommonRef.adduseType_false);
            localBundle.putSerializable(CommonRef.AddressAdd_AddressBean, new AddressBean());
            Intent localIntent1 = new Intent();
            localIntent1.putExtras(localBundle);
            localIntent1.setClass(PayActivity.this, AddressAddActivity.class);
            PayActivity.this.startActivity(localIntent1);
            return;
          }
          Intent localIntent2 = new Intent();
          localIntent2.putExtra(CommonRef.isxiadan, "1");
          localIntent2.setClass(PayActivity.this, AddressActivity.class);
          PayActivity.this.startActivity(localIntent2);
        }
      });
      this.fk_yhjxq.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PayActivity.this.pAdapter != null)
          {
            if (PayActivity.this.pAdapter.getAllMoney() > 1000.0D)
            {
              Intent localIntent = new Intent();
              localIntent.putExtra("useFs", "order");
              localIntent.setClass(PayActivity.this, MyFSActivity.class);
              PayActivity.this.startActivity(localIntent);
            }
          }
          else
            return;
          Utils.showDialog(PayActivity.this.context, "提示", "只有总价超过1000，才可以选择优惠券！", 2130837514, null);
        }
      });
      initYHQ();
      return;
      new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          PayActivity.this.finish();
          Intent localIntent = new Intent(PayActivity.this, LoginActivity.class);
          PayActivity.this.startActivity(localIntent);
        }
      }).setNegativeButton("返回", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          PayActivity.this.finish();
        }
      }).show();
    }
  }

  public void onResume()
  {
    super.onResume();
    if (this.isFirst)
    {
      this.isFirst = false;
      CommonRef.isnewadd = false;
      CommonRef.moneyfsfororder = "";
      CommonRef.moneyfsfororder2 = "0";
      CommonRef.moneyfsfororder = "0";
    }
    do
    {
      return;
      if (!"".equals(CommonRef.useAddress))
      {
        this.logistics_id = CommonRef.useAddress;
        if ((!"".equals(CommonRef.pay_name)) || (!"".equals(CommonRef.pay_phone)))
        {
          this.pay_name.setText(CommonRef.pay_name);
          this.pay_phone.setText(CommonRef.pay_phone);
          this.addmes.setText(CommonRef.addmes);
          CommonRef.pay_name = "";
          CommonRef.pay_phone = "";
          CommonRef.addmes = "";
        }
      }
    }
    while (!CommonRef.isnewadd);
    CommonRef.isnewadd = false;
    initAddress();
  }

  public class PayAdapter extends BaseAdapter
  {
    private Activity activity;
    private double allMoney = 0.0D;
    private ArrayList<ItemsBean> beanList;
    boolean boo = true;
    private double certFeeAll = 0.0D;
    private double commisionAll = 0.0D;
    private LayoutInflater inflater;
    private double priceAll = 0.0D;

    public PayAdapter(Activity paramArrayList, ArrayList<ItemsBean> arg3)
    {
      this.inflater = LayoutInflater.from(paramArrayList);
      Object localObject1;
      this.activity = localObject1;
      Object localObject2;
      this.beanList = localObject2;
      if ((localObject2 != null) && (localObject2.size() > 0))
        for (int i = 0; ; i++)
        {
          if (i >= localObject2.size())
          {
            this.allMoney = (this.priceAll + this.commisionAll + this.certFeeAll);
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = Double.valueOf(this.priceAll);
            String str3 = String.format("%.2f", arrayOfObject1);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Double.valueOf(this.commisionAll);
            String str4 = String.format("%.2f", arrayOfObject2);
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = Double.valueOf(this.certFeeAll);
            String str5 = String.format("%.2f", arrayOfObject3);
            Object[] arrayOfObject4 = new Object[1];
            arrayOfObject4[0] = Double.valueOf(this.allMoney);
            String str6 = String.format("%.2f", arrayOfObject4);
            PayActivity.this.fk_sps.setText(localObject2.size());
            PayActivity.this.fk_zj.setText(str3);
            PayActivity.this.fk_yj.setText(str4);
            PayActivity.this.fk_zsf.setText(str5);
            PayActivity.this.pay_allMoney.setText(str6);
            return;
          }
          String str1 = ((ItemsBean)localObject2.get(i)).getPriceBid();
          String str2 = ((ItemsBean)localObject2.get(i)).getCommision();
          this.priceAll += Double.parseDouble(str1);
          this.commisionAll += Double.parseDouble(str2);
        }
      PayActivity.this.fk_sps.setText("0");
      PayActivity.this.fk_zj.setText("0");
      PayActivity.this.fk_yj.setText("0");
      PayActivity.this.fk_zsf.setText("0");
      PayActivity.this.pay_allMoney.setText("0");
    }

    public double getAllMoney()
    {
      return this.allMoney;
    }

    public ArrayList<ItemsBean> getBeanList()
    {
      return this.beanList;
    }

    public double getCertFeeAll()
    {
      return this.certFeeAll;
    }

    public double getCommisionAll()
    {
      return this.commisionAll;
    }

    public int getCount()
    {
      return this.beanList.size();
    }

    public ArrayList<ItemsBean> getIList()
    {
      return this.beanList;
    }

    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public double getPriceAll()
    {
      return this.priceAll;
    }

    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      final String str1;
      final String str6;
      final CheckBox localCheckBox;
      if (paramView == null)
      {
        DocItem localDocItem = new DocItem();
        paramView = this.inflater.inflate(2130903127, null);
        paramView.setTag(localDocItem);
        ItemsBean localItemsBean = (ItemsBean)this.beanList.get(paramInt);
        str1 = localItemsBean.getItemnum();
        String str2 = localItemsBean.getTitle();
        String str3 = localItemsBean.getPicPath();
        String str4 = localItemsBean.getPriceBid();
        localItemsBean.getDateBid();
        String str5 = localItemsBean.getCommision();
        str6 = localItemsBean.getCertFee();
        ImageView localImageView = (ImageView)paramView.findViewById(2131427846);
        Utils.loadImage(str3, localImageView, this.activity);
        View.OnClickListener local1 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Intent localIntent = new Intent();
            localIntent.setClass(PayActivity.PayAdapter.this.activity, AuctionActivity.class);
            Bundle localBundle = new Bundle();
            localBundle.putString(CommonRef.itemnum, str1);
            localIntent.putExtras(localBundle);
            PayActivity.PayAdapter.this.activity.startActivity(localIntent);
          }
        };
        localImageView.setOnClickListener(local1);
        ((TextView)paramView.findViewById(2131427439)).setText(str2);
        ((TextView)paramView.findViewById(2131427847)).setText(":" + str4);
        ((TextView)paramView.findViewById(2131427848)).setText(":" + str5);
        ((TextView)paramView.findViewById(2131427851)).setText(":" + str6);
        localCheckBox = (CheckBox)paramView.findViewById(2131427849);
        localCheckBox.setOnCheckedChangeListener(null);
        if (!"1".equals(localItemsBean.getIscheck()))
          break label368;
        localCheckBox.setChecked(true);
      }
      while (true)
      {
        CompoundButton.OnCheckedChangeListener local2 = new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
          {
            if (!PayActivity.PayAdapter.this.boo)
            {
              PayActivity.PayAdapter.this.boo = true;
              return;
            }
            int i;
            label86: String str3;
            String str4;
            label199: String str5;
            PayActivity.PayAdapter localPayAdapter5;
            if (paramAnonymousBoolean)
            {
              ((ItemsBean)PayActivity.PayAdapter.this.beanList.get(paramInt)).setIscheck("1");
              PayActivity.PayAdapter.this.priceAll = 0.0D;
              PayActivity.PayAdapter.this.commisionAll = 0.0D;
              if ((PayActivity.PayAdapter.this.beanList == null) || (PayActivity.PayAdapter.this.beanList.size() <= 0))
                break label549;
              i = 0;
              if (i < PayActivity.PayAdapter.this.beanList.size())
                break label407;
              PayActivity.PayAdapter.this.allMoney = (PayActivity.PayAdapter.this.priceAll + PayActivity.PayAdapter.this.commisionAll + PayActivity.PayAdapter.this.certFeeAll);
              str3 = String.valueOf(PayActivity.PayAdapter.this.priceAll);
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = Double.valueOf(PayActivity.PayAdapter.this.commisionAll);
              str4 = String.format("%.2f", arrayOfObject1);
              if (!paramAnonymousBoolean)
                break label495;
              PayActivity.PayAdapter localPayAdapter6 = PayActivity.PayAdapter.this;
              localPayAdapter6.certFeeAll += Double.parseDouble(str6);
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = Double.valueOf(PayActivity.PayAdapter.this.certFeeAll);
              str5 = String.format("%.2f", arrayOfObject2);
              if (!paramAnonymousBoolean)
                break label522;
              localPayAdapter5 = PayActivity.PayAdapter.this;
            }
            label407: label495: PayActivity.PayAdapter localPayAdapter4;
            for (localPayAdapter5.allMoney += Double.parseDouble(str6); ; localPayAdapter4.allMoney -= Double.parseDouble(str6))
            {
              Object[] arrayOfObject3 = new Object[1];
              arrayOfObject3[0] = Double.valueOf(PayActivity.PayAdapter.this.allMoney);
              String str6 = String.format("%.2f", arrayOfObject3);
              PayActivity.this.fk_sps.setText(PayActivity.PayAdapter.this.beanList.size());
              PayActivity.this.fk_zj.setText(str3);
              PayActivity.this.fk_yj.setText(str4);
              PayActivity.this.fk_zsf.setText(str5);
              PayActivity.this.pay_allMoney.setText(str6);
              return;
              ((ItemsBean)PayActivity.PayAdapter.this.beanList.get(paramInt)).setIscheck("0");
              break;
              String str1 = ((ItemsBean)PayActivity.PayAdapter.this.beanList.get(i)).getPriceBid();
              String str2 = ((ItemsBean)PayActivity.PayAdapter.this.beanList.get(i)).getCommision();
              PayActivity.PayAdapter localPayAdapter1 = PayActivity.PayAdapter.this;
              localPayAdapter1.priceAll += Double.parseDouble(str1);
              PayActivity.PayAdapter localPayAdapter2 = PayActivity.PayAdapter.this;
              localPayAdapter2.commisionAll += Double.parseDouble(str2);
              i++;
              break label86;
              PayActivity.PayAdapter localPayAdapter3 = PayActivity.PayAdapter.this;
              localPayAdapter3.certFeeAll -= Double.parseDouble(str6);
              break label199;
              label522: localPayAdapter4 = PayActivity.PayAdapter.this;
            }
            label549: PayActivity.this.fk_sps.setText("0");
            PayActivity.this.fk_zj.setText("0");
            PayActivity.this.fk_yj.setText("0");
            PayActivity.this.fk_zsf.setText("0");
            PayActivity.this.pay_allMoney.setText(0);
          }
        };
        localCheckBox.setOnCheckedChangeListener(local2);
        TextView localTextView = (TextView)paramView.findViewById(2131427852);
        View.OnClickListener local3 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            PayActivity.PayAdapter.this.beanList.remove(paramInt);
            PayActivity.PayAdapter.this.priceAll = 0.0D;
            PayActivity.PayAdapter.this.commisionAll = 0.0D;
            if ((PayActivity.PayAdapter.this.beanList != null) && (PayActivity.PayAdapter.this.beanList.size() > 0))
              for (int i = 0; ; i++)
              {
                if (i >= PayActivity.PayAdapter.this.beanList.size())
                {
                  Object[] arrayOfObject1 = new Object[1];
                  arrayOfObject1[0] = Double.valueOf(PayActivity.PayAdapter.this.priceAll);
                  String str3 = String.format("%.2f", arrayOfObject1);
                  Object[] arrayOfObject2 = new Object[1];
                  arrayOfObject2[0] = Double.valueOf(PayActivity.PayAdapter.this.commisionAll);
                  String str4 = String.format("%.2f", arrayOfObject2);
                  if (localCheckBox.isChecked())
                  {
                    PayActivity.PayAdapter localPayAdapter3 = PayActivity.PayAdapter.this;
                    localPayAdapter3.certFeeAll -= Double.parseDouble(str6);
                    PayActivity.PayAdapter.this.boo = false;
                    localCheckBox.setChecked(false);
                  }
                  if (PayActivity.PayAdapter.this.certFeeAll != 0.0D)
                  {
                    Object[] arrayOfObject5 = new Object[1];
                    arrayOfObject5[0] = Double.valueOf(PayActivity.PayAdapter.this.certFeeAll);
                    String.format("%.2f", arrayOfObject5);
                  }
                  PayActivity.PayAdapter.this.allMoney = (PayActivity.PayAdapter.this.priceAll + PayActivity.PayAdapter.this.commisionAll + PayActivity.PayAdapter.this.certFeeAll);
                  Object[] arrayOfObject3 = new Object[1];
                  arrayOfObject3[0] = Double.valueOf(PayActivity.PayAdapter.this.certFeeAll);
                  String str5 = String.format("%.2f", arrayOfObject3);
                  Object[] arrayOfObject4 = new Object[1];
                  arrayOfObject4[0] = Double.valueOf(PayActivity.PayAdapter.this.allMoney);
                  String str6 = String.format("%.2f", arrayOfObject4);
                  PayActivity.this.fk_sps.setText(PayActivity.PayAdapter.this.beanList.size());
                  PayActivity.this.fk_zj.setText(str3);
                  PayActivity.this.fk_yj.setText(str4);
                  PayActivity.this.fk_zsf.setText(str5);
                  PayActivity.this.pay_allMoney.setText(str6);
                  PayActivity.PayAdapter.this.notifyDataSetChanged();
                  return;
                }
                String str1 = ((ItemsBean)PayActivity.PayAdapter.this.beanList.get(i)).getPriceBid();
                String str2 = ((ItemsBean)PayActivity.PayAdapter.this.beanList.get(i)).getCommision();
                PayActivity.PayAdapter localPayAdapter1 = PayActivity.PayAdapter.this;
                localPayAdapter1.priceAll += Double.parseDouble(str1);
                PayActivity.PayAdapter localPayAdapter2 = PayActivity.PayAdapter.this;
                localPayAdapter2.commisionAll += Double.parseDouble(str2);
              }
            PayActivity.PayAdapter.this.activity.finish();
          }
        };
        localTextView.setOnClickListener(local3);
        LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131427845);
        View.OnClickListener local4 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            Intent localIntent = new Intent();
            localIntent.setClass(PayActivity.PayAdapter.this.activity, AuctionActivity.class);
            Bundle localBundle = new Bundle();
            localBundle.putString(CommonRef.itemnum, str1);
            localIntent.putExtras(localBundle);
            PayActivity.PayAdapter.this.activity.startActivity(localIntent);
          }
        };
        localLinearLayout.setOnClickListener(local4);
        return paramView;
        ((DocItem)paramView.getTag());
        break;
        label368: localCheckBox.setChecked(false);
      }
    }

    public final class DocItem
    {
      TextView comtypeName;
      ImageView comtypePic;
      RelativeLayout layout;

      public DocItem()
      {
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.PayActivity
 * JD-Core Version:    0.6.2
 */