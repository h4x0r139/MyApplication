package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.bean.ItemsBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.MunionpayBean;
import com.tg.jiadeonline.date.MunionpayManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.Mtenpay;
import com.tg.jiadeonline.net.MtenpayIU;
import com.tg.jiadeonline.net.Munionpay;
import com.tg.jiadeonline.net.MunionpayIU;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;
import java.util.ArrayList;
import java.util.List;

public class ChosePayActivity extends Activity
{
  List<AddressBean> addBeanList = new ArrayList();
  private ImageView backImageView;
  ArrayList<ItemsBean> beanList = new ArrayList();
  private String currinfo = "";
  private String imei = "";
  private String netOrderId = "";
  private LinearLayout pay_cft;
  private String paymoney = "";
  private String type = "";
  private String userid = "";
  private LinearLayout yinlian;

  private void cftpay()
  {
    Mtenpay localMtenpay = new Mtenpay(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(ChosePayActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str1 = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str1)) && (!"ok".equals(str1)))
          Utils.showDialog(ChosePayActivity.this, "提示", str1, 2130837514, null);
        List localList;
        do
        {
          return;
          MunionpayManager localMunionpayManager = new MunionpayManager(ChosePayActivity.this);
          localMunionpayManager.openDataBase();
          new ArrayList();
          localList = localMunionpayManager.fetchAllDatas();
        }
        while ((localList == null) || (localList.size() <= 0));
        String str2 = ((MunionpayBean)localList.get(0)).getPayTN();
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.tenpay.com/app/mpay/mp_gate.cgi?token_id=" + str2));
        ChosePayActivity.this.startActivity(localIntent);
      }
    }
    , this, this.imei, this.userid, this.netOrderId);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localMtenpay.execute(new JiaDeNetRequest[0]);
  }

  private void ylpay()
  {
    Munionpay localMunionpay = new Munionpay(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(ChosePayActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str1 = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str1)) && (!"ok".equals(str1)))
          Utils.showDialog(ChosePayActivity.this, "提示", str1, 2130837514, null);
        List localList;
        do
        {
          return;
          MunionpayManager localMunionpayManager = new MunionpayManager(ChosePayActivity.this);
          localMunionpayManager.openDataBase();
          new ArrayList();
          localList = localMunionpayManager.fetchAllDatas();
        }
        while ((localList == null) || (localList.size() <= 0));
        String str2 = ((MunionpayBean)localList.get(0)).getPayTN();
        ChosePayActivity.this.byYinLian(str2);
      }
    }
    , this, this.imei, this.userid, this.netOrderId, this.currinfo);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localMunionpay.execute(new JiaDeNetRequest[0]);
  }

  public void byYinLian(String paramString)
  {
    UPPayAssistEx.startPayByJAR(this, PayActivity.class, null, null, paramString, "00");
  }

  protected void cftpayUI()
  {
    MtenpayIU localMtenpayIU = new MtenpayIU(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(ChosePayActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str1 = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str1)) && (!"ok".equals(str1)))
          Utils.showDialog(ChosePayActivity.this, "提示", str1, 2130837514, null);
        List localList;
        do
        {
          return;
          MunionpayManager localMunionpayManager = new MunionpayManager(ChosePayActivity.this);
          localMunionpayManager.openDataBase();
          new ArrayList();
          localList = localMunionpayManager.fetchAllDatas();
        }
        while ((localList == null) || (localList.size() <= 0));
        String str2 = ((MunionpayBean)localList.get(0)).getPayTN();
        Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.tenpay.com/app/mpay/mp_gate.cgi?token_id=" + str2));
        ChosePayActivity.this.startActivity(localIntent);
      }
    }
    , this, this.imei, this.userid, this.paymoney);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localMtenpayIU.execute(new JiaDeNetRequest[0]);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903065);
    this.imei = Utils.getPhoneId();
    Exit.getInstance().addActivity(this);
    this.yinlian = ((LinearLayout)findViewById(2131427482));
    this.pay_cft = ((LinearLayout)findViewById(2131427480));
    this.currinfo = Utils.getVersion(this);
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userid = ((LoginBean)localList.get(0)).getUserId();
    while (true)
    {
      if (getIntent().hasExtra("netOrderId"))
        this.netOrderId = getIntent().getStringExtra("netOrderId");
      if (getIntent().hasExtra("type"))
        this.type = getIntent().getStringExtra("type");
      if (getIntent().hasExtra("paymoney"))
        this.paymoney = getIntent().getStringExtra("paymoney");
      this.backImageView = ((ImageView)findViewById(2131427568));
      this.backImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ChosePayActivity.this.finish();
        }
      });
      this.yinlian.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (ChosePayActivity.this.type.equals("1"))
          {
            ChosePayActivity.this.ylpay();
            return;
          }
          ChosePayActivity.this.ylpayUI();
        }
      });
      this.pay_cft.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (ChosePayActivity.this.type.equals("1"))
          {
            ChosePayActivity.this.cftpay();
            return;
          }
          ChosePayActivity.this.cftpayUI();
        }
      });
      return;
      new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ChosePayActivity.this.finish();
          Intent localIntent = new Intent(ChosePayActivity.this, LoginActivity.class);
          ChosePayActivity.this.startActivity(localIntent);
        }
      }).setNegativeButton("返回", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ChosePayActivity.this.finish();
        }
      }).show();
    }
  }

  protected void ylpayUI()
  {
    MunionpayIU localMunionpayIU = new MunionpayIU(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(ChosePayActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str1 = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str1)) && (!"ok".equals(str1)))
          Utils.showDialog(ChosePayActivity.this, "提示", str1, 2130837514, null);
        List localList;
        do
        {
          return;
          MunionpayManager localMunionpayManager = new MunionpayManager(ChosePayActivity.this);
          localMunionpayManager.openDataBase();
          new ArrayList();
          localList = localMunionpayManager.fetchAllDatas();
        }
        while ((localList == null) || (localList.size() <= 0));
        String str2 = ((MunionpayBean)localList.get(0)).getPayTN();
        ChosePayActivity.this.byYinLian(str2);
      }
    }
    , this, this.imei, this.userid, this.paymoney, this.currinfo);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localMunionpayIU.execute(new JiaDeNetRequest[0]);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.ChosePayActivity
 * JD-Core Version:    0.6.2
 */