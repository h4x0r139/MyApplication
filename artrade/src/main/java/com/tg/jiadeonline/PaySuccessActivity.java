package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.adapter.PayNoSuccessPayAdapter;
import com.tg.jiadeonline.adapter.PaySuccessNoPayAdapter;
import com.tg.jiadeonline.adapter.PaySuccessPayAdapter;
import com.tg.jiadeonline.bean.ItemsBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.ItemsManager;
import com.tg.jiadeonline.date.PaymentOrderManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.ScrollListView;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.PaymentOrder;
import com.tg.jiadeonline.net.UnPayedItem;
import com.tg.jiadeonline.net.UnPayedOrder;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PaySuccessActivity extends Activity
{
  Dialog alertDialog = null;
  private ImageView backImage;
  private TextView back_title_paixu_paixu;
  private Context context;
  private String duration = "";
  private LinearLayout footer_pay_success_no;
  List<ItemsBean> iBeanList = new ArrayList();
  private String imei = "";
  private boolean isfast = true;
  private ScrollListView itemlistview;
  private ScrollListView itemlistview2;
  private ScrollListView itemlistview3;
  private TextView line1;
  private TextView line2;
  private TextView line3;
  private LinearLayout noPay;
  private TextView noshuju;
  private String ordernum = "";
  PaySuccessNoPayAdapter pSNPAdapter = null;
  private LinearLayout pay;
  private TextView pay_nopay_item;
  private TextView pay_nosuc_num;
  private LinearLayout pay_success_nopayItem;
  private TextView pay_success_nopay_title;
  private TextView pay_success_pay_title;
  private TextView pay_success_topay;
  private String pnum = "1";
  private PopupWindow popright = null;
  private String pqty = "20";
  private TextView titleName;
  private String userId = "";

  private void getNoPay()
  {
    this.footer_pay_success_no.setVisibility(0);
    this.pay_nosuc_num.setVisibility(0);
    this.itemlistview.setVisibility(0);
    this.itemlistview2.setVisibility(8);
    this.itemlistview3.setVisibility(8);
    this.itemlistview.setVisibility(0);
    this.line1.setBackgroundColor(getResources().getColor(2131034290));
    this.pay_success_nopay_title.setTextColor(getResources().getColor(2131034290));
    this.line2.setBackgroundColor(getResources().getColor(2131034114));
    this.pay_success_pay_title.setTextColor(getResources().getColor(2131034115));
    this.line3.setBackgroundColor(getResources().getColor(2131034114));
    this.pay_nopay_item.setTextColor(getResources().getColor(2131034115));
    UnPayedItem localUnPayedItem = new UnPayedItem(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(PaySuccessActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str)) && (!"ok".equals(str)))
          PaySuccessActivity.this.noshuju.setVisibility(0);
        while (true)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          return;
          ItemsManager localItemsManager = new ItemsManager(PaySuccessActivity.this.context);
          localItemsManager.openDataBase();
          PaySuccessActivity.this.iBeanList = localItemsManager.fetchAllDatas();
          localItemsManager.closeDataBase();
          if ((PaySuccessActivity.this.iBeanList != null) && (PaySuccessActivity.this.iBeanList.size() > 0))
          {
            PaySuccessActivity.this.noshuju.setVisibility(8);
            PaySuccessActivity.this.pSNPAdapter = new PaySuccessNoPayAdapter(PaySuccessActivity.this, PaySuccessActivity.this, PaySuccessActivity.this.iBeanList, PaySuccessActivity.this.pay_nosuc_num);
            PaySuccessActivity.this.itemlistview.setAdapter(PaySuccessActivity.this.pSNPAdapter);
          }
        }
      }
    }
    , this, this.imei, this.userId, this.duration, this.pnum, this.pqty);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localUnPayedItem.execute(new JiaDeNetRequest[0]);
  }

  private void getNoPayItem()
  {
    this.footer_pay_success_no.setVisibility(8);
    this.line3.setBackgroundColor(getResources().getColor(2131034290));
    this.pay_nopay_item.setTextColor(getResources().getColor(2131034290));
    this.line1.setBackgroundColor(getResources().getColor(2131034114));
    this.pay_success_nopay_title.setTextColor(getResources().getColor(2131034115));
    this.line2.setBackgroundColor(getResources().getColor(2131034114));
    this.pay_success_pay_title.setTextColor(getResources().getColor(2131034115));
    alertdeleteilog();
    this.itemlistview.setVisibility(8);
    this.pay_nosuc_num.setVisibility(8);
    this.pay_nosuc_num.setText("选中0件");
    this.itemlistview3.setVisibility(0);
    this.itemlistview2.setVisibility(8);
    this.footer_pay_success_no.setVisibility(8);
    UnPayedOrder localUnPayedOrder = new UnPayedOrder(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(PaySuccessActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str)) && (!"ok".equals(str)))
          PaySuccessActivity.this.noshuju.setVisibility(0);
        while (true)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          return;
          PaymentOrderManager localPaymentOrderManager = new PaymentOrderManager(PaySuccessActivity.this.context);
          localPaymentOrderManager.openDataBase();
          new ArrayList();
          List localList = localPaymentOrderManager.fetchAllDatas();
          localPaymentOrderManager.closeDataBase();
          if ((localList != null) && (localList.size() > 0))
          {
            PaySuccessActivity.this.noshuju.setVisibility(8);
            PaySuccessActivity.this.itemlistview3.setAdapter(new PayNoSuccessPayAdapter(PaySuccessActivity.this, PaySuccessActivity.this, localList));
          }
        }
      }
    }
    , this, this.imei, this.userId, this.duration, this.pnum, this.pqty);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localUnPayedOrder.execute(new JiaDeNetRequest[0]);
  }

  private void getPayItem()
  {
    this.footer_pay_success_no.setVisibility(8);
    this.pay_nosuc_num.setText("选中0件");
    this.itemlistview.setVisibility(8);
    this.itemlistview3.setVisibility(8);
    this.itemlistview2.setVisibility(0);
    this.line2.setBackgroundColor(getResources().getColor(2131034290));
    this.pay_success_pay_title.setTextColor(getResources().getColor(2131034290));
    this.line1.setBackgroundColor(getResources().getColor(2131034114));
    this.pay_success_nopay_title.setTextColor(getResources().getColor(2131034115));
    this.line3.setBackgroundColor(getResources().getColor(2131034114));
    this.pay_nopay_item.setTextColor(getResources().getColor(2131034115));
    alertdeleteilog();
    PaymentOrder localPaymentOrder = new PaymentOrder(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(PaySuccessActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str)) && (!"ok".equals(str)))
          PaySuccessActivity.this.noshuju.setVisibility(0);
        while (true)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          return;
          PaymentOrderManager localPaymentOrderManager = new PaymentOrderManager(PaySuccessActivity.this.context);
          localPaymentOrderManager.openDataBase();
          new ArrayList();
          List localList = localPaymentOrderManager.fetchAllDatas();
          localPaymentOrderManager.closeDataBase();
          if ((localList != null) && (localList.size() > 0))
          {
            PaySuccessActivity.this.noshuju.setVisibility(8);
            PaySuccessActivity.this.itemlistview2.setAdapter(new PaySuccessPayAdapter(PaySuccessActivity.this, PaySuccessActivity.this, localList));
          }
        }
      }
    }
    , this, this.imei, this.userId, this.duration, this.pnum, this.pqty);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localPaymentOrder.execute(new JiaDeNetRequest[0]);
  }

  public void alertdeleteilog()
  {
    this.alertDialog = new AlertDialog.Builder(this).setTitle("温馨提示").setMessage("您确定要删除此订单吗？").setIcon(2130837565).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).setNegativeButton("取消", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).create();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903064);
    Exit.getInstance().addActivity(this);
    this.noshuju = ((TextView)findViewById(2131427475));
    this.noshuju.setVisibility(8);
    this.context = this;
    this.imei = Utils.getPhoneId();
    this.noPay = ((LinearLayout)findViewById(2131427462));
    this.pay_success_nopayItem = ((LinearLayout)findViewById(2131427465));
    this.pay = ((LinearLayout)findViewById(2131427468));
    this.line1 = ((TextView)findViewById(2131427464));
    this.line2 = ((TextView)findViewById(2131427470));
    this.line3 = ((TextView)findViewById(2131427467));
    this.pay_success_topay = ((TextView)findViewById(2131427477));
    this.pay_success_nopay_title = ((TextView)findViewById(2131427463));
    this.pay_nopay_item = ((TextView)findViewById(2131427466));
    this.pay_success_pay_title = ((TextView)findViewById(2131427469));
    this.footer_pay_success_no = ((LinearLayout)findViewById(2131427471));
    this.pay_nosuc_num = ((TextView)findViewById(2131427476));
    this.itemlistview = ((ScrollListView)findViewById(2131427474));
    this.itemlistview2 = ((ScrollListView)findViewById(2131427472));
    this.itemlistview3 = ((ScrollListView)findViewById(2131427473));
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
    {
      this.userId = ((LoginBean)localList.get(0)).getUserId();
      if (getIntent().hasExtra("ordernum"))
        this.ordernum = getIntent().getStringExtra("ordernum");
      if ("1".equals(this.ordernum))
      {
        getNoPayItem();
        this.ordernum = "";
      }
    }
    while (true)
    {
      this.pay_success_topay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PaySuccessActivity.this.pSNPAdapter != null)
          {
            Intent localIntent = new Intent(PaySuccessActivity.this, PayActivity.class);
            HashMap localHashMap = PaySuccessNoPayAdapter.getIsSelected();
            ArrayList localArrayList = new ArrayList();
            Iterator localIterator = localHashMap.keySet().iterator();
            while (true)
            {
              if (!localIterator.hasNext())
              {
                localIntent.putExtra("payList", localArrayList);
                PaySuccessActivity.this.startActivity(localIntent);
                return;
              }
              Integer localInteger = (Integer)localIterator.next();
              if (((Boolean)localHashMap.get(localInteger)).booleanValue())
                localArrayList.add((ItemsBean)PaySuccessActivity.this.iBeanList.get(localInteger.intValue()));
            }
          }
          Utils.showDialog(PaySuccessActivity.this, "提示", "没有选中拍品！", 2130837514, null);
        }
      });
      this.noPay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PaySuccessActivity.this.getNoPay();
        }
      });
      this.pay.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PaySuccessActivity.this.getPayItem();
        }
      });
      this.pay_success_nopayItem.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PaySuccessActivity.this.getNoPayItem();
        }
      });
      this.backImage = ((ImageView)findViewById(2131427560));
      this.titleName = ((TextView)findViewById(2131427561));
      this.titleName.setText("成交付款");
      this.backImage.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PaySuccessActivity.this.finish();
        }
      });
      this.back_title_paixu_paixu = ((TextView)findViewById(2131427566));
      View localView = LayoutInflater.from(this).inflate(2130903090, null);
      this.popright = new PopupWindow(localView, -2, -2, false);
      this.popright.setAnimationStyle(2131230726);
      this.popright.setBackgroundDrawable(new BitmapDrawable());
      this.popright.setOutsideTouchable(true);
      this.popright.setFocusable(true);
      this.back_title_paixu_paixu.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PaySuccessActivity.this.popright.isShowing())
          {
            PaySuccessActivity.this.popright.dismiss();
            return;
          }
          PaySuccessActivity.this.popright.showAsDropDown(paramAnonymousView);
        }
      });
      final TextView localTextView1 = (TextView)localView.findViewById(2131427572);
      final TextView localTextView2 = (TextView)localView.findViewById(2131427574);
      final TextView localTextView3 = (TextView)localView.findViewById(2131427576);
      final TextView localTextView4 = (TextView)localView.findViewById(2131427578);
      localTextView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PaySuccessActivity.this.popright.isShowing())
            PaySuccessActivity.this.popright.dismiss();
          Toast.makeText(PaySuccessActivity.this, localTextView1.getText().toString(), 1).show();
        }
      });
      localTextView2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PaySuccessActivity.this.popright.isShowing())
            PaySuccessActivity.this.popright.dismiss();
          Toast.makeText(PaySuccessActivity.this, localTextView2.getText().toString(), 1).show();
        }
      });
      localTextView3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PaySuccessActivity.this.popright.isShowing())
            PaySuccessActivity.this.popright.dismiss();
          Toast.makeText(PaySuccessActivity.this, localTextView3.getText().toString(), 1).show();
        }
      });
      localTextView4.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (PaySuccessActivity.this.popright.isShowing())
            PaySuccessActivity.this.popright.dismiss();
          Toast.makeText(PaySuccessActivity.this, localTextView4.getText().toString(), 1).show();
        }
      });
      return;
      if ("2".equals(this.ordernum))
      {
        getPayItem();
        this.ordernum = "";
      }
      else
      {
        getNoPay();
        continue;
        new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            PaySuccessActivity.this.finish();
            Intent localIntent = new Intent(PaySuccessActivity.this, LoginActivity.class);
            PaySuccessActivity.this.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
          {
            PaySuccessActivity.this.finish();
          }
        }).show();
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.PaySuccessActivity
 * JD-Core Version:    0.6.2
 */