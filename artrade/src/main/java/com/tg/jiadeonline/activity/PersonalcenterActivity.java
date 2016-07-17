package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.tg.jiadeonline.BondActivity;
import com.tg.jiadeonline.LiuyanActivity;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.MyAuctionActivity;
import com.tg.jiadeonline.MyFSActivity;
import com.tg.jiadeonline.PaySuccessActivity;
import com.tg.jiadeonline.SetUpActivity;
import com.tg.jiadeonline.base.BaseActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.UserCredit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class PersonalcenterActivity extends BaseActivity
{
  private PopupWindow MyPopupWindow;
  private Activity activity;
  private ImageView bagback_bagImage1 = null;
  private LinearLayout bzj_layout;
  private LinearLayout cjjl_layout;
  private String imei;
  private LayoutInflater inflate = null;
  private boolean isfast = true;
  private LinearLayout jpcj_layout;
  private LinearLayout jplx_layout;
  private LinearLayout layoutss = null;
  private LinearLayout liuyan_layout;
  private Button login;
  private LinearLayout loginlayout;
  private boolean miiss = false;
  private TextView money;
  private LinearLayout msgLayout;
  private LinearLayout myauction_layout;
  private LinearLayout mysale_layout;
  private TextView name;
  private PopupWindow popright = null;
  private TextView relativeLayout = null;
  private LinearLayout title_address = null;
  private LinearLayout title_changepwd = null;
  private LinearLayout title_info = null;
  private LinearLayout title_setup = null;
  ImageView touxiang;

  private void iniPopupWindow(View paramView)
  {
    this.MyPopupWindow = new PopupWindow(paramView, -1, -2);
    this.MyPopupWindow.setFocusable(true);
    this.MyPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    this.MyPopupWindow.setOutsideTouchable(true);
    this.MyPopupWindow.setBackgroundDrawable(getResources().getDrawable(2131034122));
    this.MyPopupWindow.setOutsideTouchable(true);
    this.MyPopupWindow.showAtLocation(paramView, 80, 0, 0);
  }

  private void initUser()
  {
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this.activity);
    localRegisterOrLoginManager.openDataBase();
    new ArrayList();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList == null)
    {
      this.name.setVisibility(8);
      this.msgLayout.setVisibility(8);
      this.loginlayout.setVisibility(0);
      return;
    }
    this.name.setVisibility(0);
    this.msgLayout.setVisibility(0);
    this.loginlayout.setVisibility(8);
    LoginBean localLoginBean = (LoginBean)localList.get(0);
    String str1 = localLoginBean.getNickname();
    this.name.setText(str1);
    String str2 = localLoginBean.getUserId();
    UserCredit localUserCredit = new UserCredit(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        Utils.showDialog(PersonalcenterActivity.this.activity, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        String str = paramAnonymousJiaDeNetResponse.result.toString();
        if ("".equals(str))
          str = "0";
        PersonalcenterActivity.this.money.setText(str);
      }
    }
    , this.activity, this.imei, str2);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localUserCredit.execute(new JiaDeNetRequest[0]);
  }

  public void addShare()
  {
    this.relativeLayout = ((TextView)this.activity.findViewById(2131427565));
    this.inflate = LayoutInflater.from(this.activity);
    View localView = this.inflate.inflate(2130903140, null);
    this.layoutss = ((LinearLayout)localView.findViewById(2131427570));
    this.popright = new PopupWindow(localView, -2, -2, false);
    this.title_info = ((LinearLayout)localView.findViewById(2131427571));
    this.title_info.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          if (PersonalcenterActivity.this.popright.isShowing())
          {
            PersonalcenterActivity.this.popright.dismiss();
            PersonalcenterActivity.this.miiss = false;
          }
          Intent localIntent = new Intent();
          localIntent.setClass(PersonalcenterActivity.this.activity, PeopleInfoActivity.class);
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.title_changepwd = ((LinearLayout)localView.findViewById(2131427573));
    this.title_changepwd.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          if (PersonalcenterActivity.this.popright.isShowing())
          {
            PersonalcenterActivity.this.popright.dismiss();
            PersonalcenterActivity.this.miiss = false;
          }
          Intent localIntent = new Intent();
          localIntent.setClass(PersonalcenterActivity.this.activity, PasswordChangetActivity.class);
          PersonalcenterActivity.this.startActivity(localIntent);
          PersonalcenterActivity.this.activity.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.title_address = ((LinearLayout)localView.findViewById(2131427575));
    this.title_address.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          if (PersonalcenterActivity.this.popright.isShowing())
          {
            PersonalcenterActivity.this.popright.dismiss();
            PersonalcenterActivity.this.miiss = false;
          }
          Intent localIntent = new Intent();
          localIntent.setClass(PersonalcenterActivity.this.activity, AddressActivity.class);
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.title_setup = ((LinearLayout)localView.findViewById(2131427577));
    this.title_setup.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (PersonalcenterActivity.this.popright.isShowing())
        {
          PersonalcenterActivity.this.popright.dismiss();
          PersonalcenterActivity.this.miiss = false;
        }
        Intent localIntent = new Intent();
        localIntent.setClass(PersonalcenterActivity.this.activity, SetUpActivity.class);
        PersonalcenterActivity.this.startActivity(localIntent);
      }
    });
    this.popright.setAnimationStyle(2131230726);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    this.relativeLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (!PersonalcenterActivity.this.miiss)
        {
          PersonalcenterActivity.this.popright.showAsDropDown(paramAnonymousView);
          PersonalcenterActivity.this.miiss = true;
          return;
        }
        PersonalcenterActivity.this.popright.dismiss();
        PersonalcenterActivity.this.miiss = false;
      }
    });
  }

  protected int getViewId()
  {
    return 2130903066;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView();
    createNavMenu();
    this.activity = this;
    this.imei = Utils.getPhoneId();
    addShare();
    this.touxiang = ((ImageView)findViewById(2131427486));
    Bitmap localBitmap = BitmapFactory.decodeResource(getResources(), 2130837542);
    this.touxiang.setImageBitmap(Utils.toRoundBitmap1(this, localBitmap));
    this.name = ((TextView)findViewById(2131427484));
    this.login = ((Button)findViewById(2131427491));
    this.msgLayout = ((LinearLayout)findViewById(2131427485));
    this.money = ((TextView)findViewById(2131427488));
    this.loginlayout = ((LinearLayout)findViewById(2131427490));
    this.myauction_layout = ((LinearLayout)findViewById(2131427492));
    this.cjjl_layout = ((LinearLayout)findViewById(2131427493));
    this.liuyan_layout = ((LinearLayout)findViewById(2131427495));
    this.bzj_layout = ((LinearLayout)findViewById(2131427494));
    this.jplx_layout = ((LinearLayout)findViewById(2131427496));
    this.jpcj_layout = ((LinearLayout)findViewById(2131427497));
    this.mysale_layout = ((LinearLayout)findViewById(2131427498));
    initUser();
    this.login.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
        PersonalcenterActivity.this.startActivity(localIntent);
      }
    });
    this.myauction_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          Intent localIntent = new Intent(PersonalcenterActivity.this.activity, MyAuctionActivity.class);
          localIntent.putExtra("type1", "myjp");
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.cjjl_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          Intent localIntent = new Intent(PersonalcenterActivity.this.activity, PaySuccessActivity.class);
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.liuyan_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LiuyanActivity.class);
        PersonalcenterActivity.this.startActivity(localIntent);
      }
    });
    this.bzj_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          Intent localIntent = new Intent(PersonalcenterActivity.this.activity, BondActivity.class);
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.jplx_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          Intent localIntent = new Intent(PersonalcenterActivity.this.activity, MyAuctionActivity.class);
          localIntent.putExtra("type1", "ling");
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.jpcj_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          Intent localIntent = new Intent(PersonalcenterActivity.this.activity, MyAuctionActivity.class);
          localIntent.putExtra("type1", "yi");
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
    this.mysale_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(PersonalcenterActivity.this.activity);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          Intent localIntent = new Intent(PersonalcenterActivity.this.activity, MyFSActivity.class);
          PersonalcenterActivity.this.startActivity(localIntent);
          return;
        }
        new AlertDialog.Builder(PersonalcenterActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            Intent localIntent = new Intent(PersonalcenterActivity.this.activity, LoginActivity.class);
            PersonalcenterActivity.this.activity.startActivity(localIntent);
          }
        }).setNegativeButton("返回", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
          }
        }).show();
      }
    });
  }

  protected void onResume()
  {
    if (this.isfast)
      this.isfast = false;
    while (true)
    {
      super.onResume();
      return;
      initUser();
    }
  }

  protected void onStart()
  {
    super.onStart();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.PersonalcenterActivity
 * JD-Core Version:    0.6.2
 */