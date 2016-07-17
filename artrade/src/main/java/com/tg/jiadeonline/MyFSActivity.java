package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.UserFsBean;
import com.tg.jiadeonline.date.ForSaleInfoManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.ForSaleInfo;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.List;

public class MyFSActivity extends Activity
{
  private ImageView backImageView;
  private TextView back_title_del_del;
  private Context context;
  private String imei = "";
  private TextView titleName;
  private String useFs = "";
  private String userId = "";
  private LinearLayout yhq_lin;

  private void initData()
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
        Utils.showDialog(MyFSActivity.this.context, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str1 = (String)paramAnonymousJiaDeNetResponse.result;
        if ((!"".equals(str1)) && (!"ok".equals(str1)))
          Utils.showDialog(MyFSActivity.this.context, "提示", str1, 2130837514, null);
        while (true)
        {
          return;
          ForSaleInfoManager localForSaleInfoManager = new ForSaleInfoManager(MyFSActivity.this.context);
          localForSaleInfoManager.openDataBase();
          List localList = localForSaleInfoManager.fetchAllDatas();
          localForSaleInfoManager.closeDataBase();
          if ((localList == null) || (localList.size() <= 0))
          {
            Utils.showDialog(MyFSActivity.this.context, "提示", "没有优惠券", 2130837514, null);
            return;
          }
          MyFSActivity.this.yhq_lin.removeAllViews();
          LayoutInflater localLayoutInflater = LayoutInflater.from(MyFSActivity.this.context);
          for (int i = 0; i < localList.size(); i++)
          {
            View localView = localLayoutInflater.inflate(2130903105, null);
            UserFsBean localUserFsBean = (UserFsBean)localList.get(i);
            String str2 = localUserFsBean.getForSaleId();
            String str3 = localUserFsBean.getUserFSId();
            String str4 = localUserFsBean.getUserFSFee();
            ((TextView)localView.findViewById(2131427670)).setText("促销售活动编号：" + str2);
            ((TextView)localView.findViewById(2131427671)).setText("优惠券编号：" + str3);
            ((TextView)localView.findViewById(2131427672)).setText("优惠券金额：" + str4);
            MyFSActivity.this.yhq_lin.addView(localView);
          }
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
    setContentView(2130903055);
    this.imei = Utils.getPhoneId();
    this.context = this;
    Exit.getInstance().addActivity(this);
    if (getIntent().hasExtra("useFs"))
      this.useFs = getIntent().getStringExtra("useFs");
    this.titleName = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.back_title_del_del = ((TextView)findViewById(2131427564));
    this.back_title_del_del.setVisibility(8);
    this.titleName.setText("我的优惠券");
    this.yhq_lin = ((LinearLayout)findViewById(2131427400));
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
    {
      this.userId = ((LoginBean)localList.get(0)).getUserId();
      initData();
    }
    while (true)
    {
      this.backImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyFSActivity.this.finish();
        }
      });
      return;
      new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyFSActivity.this.finish();
          Intent localIntent = new Intent(MyFSActivity.this, LoginActivity.class);
          MyFSActivity.this.startActivity(localIntent);
        }
      }).setNegativeButton("返回", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MyFSActivity.this.finish();
        }
      }).show();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.MyFSActivity
 * JD-Core Version:    0.6.2
 */