package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.activity.PeopleInfoActivity;
import com.tg.jiadeonline.adapter.UserAcctAdapter;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.UserAccBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.date.UserAccManager;
import com.tg.jiadeonline.layout.ScrollListView;
import com.tg.jiadeonline.net.IdCardIsValid;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.UserAcct;
import com.tg.jiadeonline.net.UserCredit;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class BondActivity extends Activity
{
  private static int refreshCnt = CommonRef.refreshCnt;
  private ImageView backImageView;
  private TextView baozhengjin;
  private TextView bond_bzj;
  private TextView bond_name;
  private Context context;
  private String imei = "";
  private List<UserAccBean> item = new ArrayList();
  private LinearLayout layout_line1;
  private LinearLayout layout_line2;
  private LinearLayout layout_line3;
  private int pageSize = CommonRef.pageSize;
  private EditText payMoney;
  private TextView pay_bond;
  private String pnum = "1";
  private PopupWindow popright = null;
  private String pqty = "20";
  private Resources r;
  private int start = CommonRef.start;
  private TextView text_line1;
  private TextView text_line2;
  private TextView text_line3;
  private TextView text_view1;
  private TextView text_view2;
  private TextView text_view3;
  private TextView titleName;
  private ScrollListView userAcctListView;
  private ScrollListView userAdvtListView;
  List<UserAccBean> userBeanList = new ArrayList();
  private String userId = "";
  private LoginBean userbean;

  private void geneItems()
  {
    if ((this.userBeanList != null) && (this.userBeanList.size() > 0))
      this.item.addAll(this.userBeanList);
  }

  private void initUser()
  {
    UserCredit localUserCredit = new UserCredit(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(BondActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str = paramAnonymousJiaDeNetResponse.result.toString();
        BondActivity.this.baozhengjin.setText(str);
        BondActivity.this.bond_bzj.setText("0");
        WaitLoadDialog.getInstance().dismissDialog();
      }
    }
    , this.context, this.imei, this.userId);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localUserCredit.execute(new JiaDeNetRequest[0]);
  }

  public void change(TextView paramTextView1, final TextView paramTextView2, final LinearLayout paramLinearLayout, final String paramString)
  {
    this.r = getResources();
    paramTextView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BondActivity.this.text_line1.setBackgroundColor(BondActivity.this.r.getColor(2131034291));
        BondActivity.this.text_line2.setBackgroundColor(BondActivity.this.r.getColor(2131034291));
        BondActivity.this.text_line3.setBackgroundColor(BondActivity.this.r.getColor(2131034291));
        BondActivity.this.layout_line1.setVisibility(8);
        BondActivity.this.layout_line2.setVisibility(8);
        BondActivity.this.layout_line3.setVisibility(8);
        paramTextView2.setBackgroundColor(BondActivity.this.r.getColor(2131034114));
        paramLinearLayout.setVisibility(0);
        if (!"".equals(paramString))
        {
          UserAcct localUserAcct = new UserAcct(new JiaDeNetRequestTask.JiaDeNetCallback()
          {
            public void onCanceled()
            {
              WaitLoadDialog.getInstance().dismissDialog();
            }

            public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              BondActivity.this.errormsg(paramAnonymous2JiaDeNetException.getLocalizedMessage());
            }

            public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              String str = (String)paramAnonymous2JiaDeNetResponse.result;
              if ((!"".equals(str)) && (!"ok".equals(str)))
                BondActivity.this.errormsg(str);
              do
              {
                return;
                UserAccManager localUserAccManager = new UserAccManager(BondActivity.this.context);
                localUserAccManager.openDataBase();
                BondActivity.this.userBeanList = localUserAccManager.fetchAllDatas();
                localUserAccManager.closeDataBase();
                if ((BondActivity.this.userBeanList == null) || (BondActivity.this.userBeanList.size() <= 0))
                {
                  BondActivity.this.errormsg("未查询到更多数据!");
                  return;
                }
                if (this.val$type.equals("ins"))
                {
                  BondActivity.this.userAcctListView.setVisibility(0);
                  BondActivity.this.userAdvtListView.setVisibility(8);
                  BondActivity.this.userAcctListView.setAdapter(new UserAcctAdapter(BondActivity.this, BondActivity.this, BondActivity.this.userBeanList));
                  return;
                }
              }
              while (!this.val$type.equals("adv"));
              BondActivity.this.userAcctListView.setVisibility(8);
              BondActivity.this.userAdvtListView.setVisibility(0);
              BondActivity.this.userAdvtListView.setAdapter(new UserAcctAdapter(BondActivity.this, BondActivity.this, BondActivity.this.userBeanList));
            }
          }
          , BondActivity.this, BondActivity.this.imei, BondActivity.this.userId, paramString, BondActivity.this.pnum, BondActivity.this.pqty);
          WaitLoadDialog.getInstance().showDialog(BondActivity.this, null, true);
          localUserAcct.execute(new JiaDeNetRequest[0]);
        }
      }
    });
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  public int getHeightPixels()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }

  public int getWidthPixels()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }

  public void init()
  {
    this.imei = Utils.getPhoneId();
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.text_view1 = ((TextView)findViewById(2131427345));
    this.text_view2 = ((TextView)findViewById(2131427346));
    this.text_view3 = ((TextView)findViewById(2131427347));
    this.pay_bond = ((TextView)findViewById(2131427356));
    this.payMoney = ((EditText)findViewById(2131427355));
    this.baozhengjin = ((TextView)findViewById(2131427343));
    this.text_view1.setFocusable(true);
    this.text_line1 = ((TextView)findViewById(2131427349));
    this.text_line2 = ((TextView)findViewById(2131427350));
    this.text_line3 = ((TextView)findViewById(2131427351));
    this.layout_line1 = ((LinearLayout)findViewById(2131427352));
    this.layout_line2 = ((LinearLayout)findViewById(2131427358));
    this.layout_line3 = ((LinearLayout)findViewById(2131427360));
    this.userAcctListView = ((ScrollListView)findViewById(2131427359));
    this.userAdvtListView = ((ScrollListView)findViewById(2131427361));
    change(this.text_view1, this.text_line1, this.layout_line1, "");
    change(this.text_view2, this.text_line2, this.layout_line2, "ins");
    change(this.text_view3, this.text_line3, this.layout_line3, "adv");
    this.bond_name = ((TextView)findViewById(2131427341));
    this.bond_bzj = ((TextView)findViewById(2131427342));
    this.bond_name.setText(this.userbean.getNickname());
    this.titleName.setText("保证金");
    initUser();
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BondActivity.this.finish();
      }
    });
    popwindows();
    ((TextView)findViewById(2131427357)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BondActivity.this.popright.showAsDropDown(BondActivity.this.getWindow().getDecorView(), -150 + BondActivity.this.getWidthPixels(), 40 + -BondActivity.this.getHeightPixels());
      }
    });
    this.pay_bond.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        IdCardIsValid localIdCardIsValid = new IdCardIsValid(new JiaDeNetRequestTask.JiaDeNetCallback()
        {
          public void onCanceled()
          {
            WaitLoadDialog.getInstance().dismissDialog();
          }

          public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
          {
            WaitLoadDialog.getInstance().dismissDialog();
          }

          public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
          {
            String str1 = paramAnonymous2JiaDeNetResponse.result.toString();
            WaitLoadDialog.getInstance().dismissDialog();
            if ("ok".equals(str1))
            {
              String str2 = BondActivity.this.payMoney.getText().toString();
              if ("".equals(str2))
              {
                Utils.showDialog(BondActivity.this, "提示", "请输入金额！", 2130837514, null);
                return;
              }
              if (str2.length() < 3)
              {
                Utils.showDialog(BondActivity.this, "提示", "请输入100的整数倍！！", 2130837514, null);
                return;
              }
              if (!"00".equals(str2.substring(-2 + str2.length(), str2.length())))
              {
                Utils.showDialog(BondActivity.this, "提示", "请输入100的整数倍！！", 2130837514, null);
                return;
              }
              Intent localIntent = new Intent(BondActivity.this, ChosePayActivity.class);
              localIntent.putExtra("type", "2");
              localIntent.putExtra("paymoney", str2);
              BondActivity.this.startActivity(localIntent);
              return;
            }
            new AlertDialog.Builder(BondActivity.this).setTitle("提示").setMessage("个人信息不完善,是否完善？").setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
                Intent localIntent = new Intent(BondActivity.this, PeopleInfoActivity.class);
                BondActivity.this.startActivity(localIntent);
              }
            }).setNegativeButton("返回", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
              {
              }
            }).show();
          }
        }
        , BondActivity.this, BondActivity.this.imei, BondActivity.this.userId);
        WaitLoadDialog.getInstance().showDialog(BondActivity.this, null, true);
        localIdCardIsValid.execute(new JiaDeNetRequest[0]);
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903042);
    this.context = this;
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if ((localList != null) && (localList.size() != 0))
    {
      this.userbean = ((LoginBean)localList.get(0));
      this.userId = this.userbean.getUserId();
      init();
      return;
    }
    new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        BondActivity.this.finish();
        Intent localIntent = new Intent(BondActivity.this, LoginActivity.class);
        BondActivity.this.startActivity(localIntent);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        BondActivity.this.finish();
      }
    }).show();
  }

  public final void popwindows()
  {
    View localView = LayoutInflater.from(this).inflate(2130903102, null);
    this.popright = new PopupWindow(localView, -1, -1, false);
    this.popright.setAnimationStyle(2131230730);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    ((ImageView)localView.findViewById(2131427396)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (BondActivity.this.popright.isShowing())
          BondActivity.this.popright.dismiss();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.BondActivity
 * JD-Core Version:    0.6.2
 */