package com.tg.jiadeonline.base;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.activity.CategoriesActivity;
import com.tg.jiadeonline.activity.CurrentendActivity;
import com.tg.jiadeonline.activity.FavoritesActivity;
import com.tg.jiadeonline.activity.HomeActivity;
import com.tg.jiadeonline.activity.PersonalcenterActivity;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import java.util.List;

public abstract class BaseActivity extends Activity
{
  private static final long EXIT_INTERVAL_TIME = 2000L;
  public static int selectIndex = 0;
  private View.OnClickListener clickListener1 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((Utils.IsNormalOperation()) && (BaseActivity.selectIndex != 0))
      {
        BaseActivity.selectIndex = 0;
        Intent localIntent = new Intent();
        localIntent.addFlags(603979776);
        localIntent.setClass(BaseActivity.this, HomeActivity.class);
        BaseActivity.this.startActivity(localIntent);
        BaseActivity.this.finish();
      }
    }
  };
  private View.OnClickListener clickListener2 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((Utils.IsNormalOperation()) && (1 != BaseActivity.selectIndex))
      {
        BaseActivity.selectIndex = 1;
        Intent localIntent = new Intent();
        localIntent.addFlags(603979776);
        localIntent.setClass(BaseActivity.this, CategoriesActivity.class);
        BaseActivity.this.startActivity(localIntent);
        BaseActivity.this.finish();
      }
    }
  };
  private View.OnClickListener clickListener3 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((Utils.IsNormalOperation()) && (2 != BaseActivity.selectIndex))
      {
        BaseActivity.selectIndex = 2;
        Intent localIntent = new Intent();
        localIntent.addFlags(603979776);
        localIntent.setClass(BaseActivity.this, CurrentendActivity.class);
        BaseActivity.this.startActivity(localIntent);
        BaseActivity.this.finish();
      }
    }
  };
  private View.OnClickListener clickListener4 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((Utils.IsNormalOperation()) && (3 != BaseActivity.selectIndex))
      {
        RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(BaseActivity.this);
        localRegisterOrLoginManager.openDataBase();
        List localList = localRegisterOrLoginManager.fetchAllDatas();
        localRegisterOrLoginManager.closeDataBase();
        if (localList != null)
        {
          BaseActivity.selectIndex = 3;
          Intent localIntent = new Intent();
          localIntent.addFlags(603979776);
          localIntent.setClass(BaseActivity.this, FavoritesActivity.class);
          BaseActivity.this.startActivity(localIntent);
          BaseActivity.this.finish();
        }
      }
      else
      {
        return;
      }
      new AlertDialog.Builder(BaseActivity.this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
        {
          Intent localIntent = new Intent(BaseActivity.this, LoginActivity.class);
          BaseActivity.this.startActivity(localIntent);
        }
      }).setNegativeButton("返回", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
        {
        }
      }).show();
    }
  };
  private View.OnClickListener clickListener5 = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if ((Utils.IsNormalOperation()) && (4 != BaseActivity.selectIndex))
      {
        BaseActivity.selectIndex = 4;
        Intent localIntent = new Intent();
        localIntent.addFlags(603979776);
        localIntent.setClass(BaseActivity.this, PersonalcenterActivity.class);
        BaseActivity.this.startActivity(localIntent);
        BaseActivity.this.finish();
      }
    }
  };
  private ImageView img1;
  private ImageView img2;
  private ImageView img3;
  private ImageView img4;
  private ImageView img5;
  InputMethodManager imm;
  private LinearLayout layout1;
  private LinearLayout layout2;
  private LinearLayout layout3;
  private LinearLayout layout4;
  private LinearLayout layout5;
  public boolean mExecutionOnCreate = false;
  public TextView text1;
  public TextView text2;
  public TextView text3;
  public TextView text4;
  public TextView text5;
  private long touchTime = 0L;

  private void toastMsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void createNavMenu()
  {
    this.text1 = ((TextView)findViewById(2131427590));
    this.text2 = ((TextView)findViewById(2131427593));
    this.text3 = ((TextView)findViewById(2131427596));
    this.text4 = ((TextView)findViewById(2131427599));
    this.text5 = ((TextView)findViewById(2131427602));
    this.layout1 = ((LinearLayout)findViewById(2131427588));
    this.layout1.setOnClickListener(this.clickListener1);
    this.layout2 = ((LinearLayout)findViewById(2131427591));
    this.layout2.setOnClickListener(this.clickListener2);
    this.layout3 = ((LinearLayout)findViewById(2131427594));
    this.layout3.setOnClickListener(this.clickListener3);
    this.layout4 = ((LinearLayout)findViewById(2131427597));
    this.layout4.setOnClickListener(this.clickListener4);
    this.layout5 = ((LinearLayout)findViewById(2131427600));
    this.layout5.setOnClickListener(this.clickListener5);
    this.img1 = ((ImageView)findViewById(2131427589));
    this.img2 = ((ImageView)findViewById(2131427592));
    this.img3 = ((ImageView)findViewById(2131427595));
    this.img4 = ((ImageView)findViewById(2131427598));
    this.img5 = ((ImageView)findViewById(2131427601));
    switch (getViewId())
    {
    default:
      this.layout1.setSelected(true);
      this.img1.setBackgroundResource(2130837561);
      this.text1.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903057:
      this.layout1.setSelected(true);
      this.img1.setBackgroundResource(2130837561);
      this.text1.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903043:
      this.layout2.setSelected(true);
      this.img2.setBackgroundResource(2130837528);
      this.text2.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903050:
      this.layout3.setSelected(true);
      this.img3.setBackgroundResource(2130837539);
      this.text3.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903054:
      this.layout4.setSelected(true);
      this.img4.setBackgroundResource(2130837551);
      this.text4.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903066:
    }
    this.layout5.setSelected(true);
    this.img5.setBackgroundResource(2130837589);
    this.text5.setTextColor(getResources().getColor(2131034130));
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (getCurrentFocus() != null) && (getCurrentFocus().getWindowToken() != null))
    {
      if (this.imm == null)
        this.imm = ((InputMethodManager)getSystemService("input_method"));
      this.imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected abstract int getViewId();

  protected Intent newIntent()
  {
    return new Intent();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Exit.getInstance().exitHome();
    Exit.getInstance().addActivity(this);
    this.mExecutionOnCreate = true;
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      long l = System.currentTimeMillis();
      if (l - this.touchTime >= 2000L)
      {
        toastMsg("再按一次退出程序。");
        this.touchTime = l;
      }
      while (true)
      {
        return false;
        Exit.getInstance().exit();
      }
    }
    return true;
  }

  public void onRefresh()
  {
  }

  protected void onRestart()
  {
    super.onRestart();
    switch (getViewId())
    {
    default:
      this.layout1.setSelected(true);
      this.img1.setBackgroundResource(2130837561);
      this.text1.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903057:
      this.layout1.setSelected(true);
      this.img1.setBackgroundResource(2130837561);
      this.text1.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903043:
      this.layout2.setSelected(true);
      this.img2.setBackgroundResource(2130837528);
      this.text2.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903050:
      this.layout3.setSelected(true);
      this.img3.setBackgroundResource(2130837539);
      this.text3.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903054:
      this.layout4.setSelected(true);
      this.img4.setBackgroundResource(2130837551);
      this.text4.setTextColor(getResources().getColor(2131034130));
      return;
    case 2130903066:
    }
    this.layout5.setSelected(true);
    this.img5.setBackgroundResource(2130837589);
    this.text5.setTextColor(getResources().getColor(2131034130));
  }

  protected void setContentView()
  {
    setContentView(getViewId());
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.base.BaseActivity
 * JD-Core Version:    0.6.2
 */