package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.activity.HomeActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.GetverInfo;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.LogOut;
import com.tg.jiadeonline.utils.Async;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SetUpActivity extends Activity
{
  private ImageView backImageView;
  private Async download;
  private LinearLayout gxjc_layout;
  private LinearLayout gywm_lin;
  private String imei = "";
  private LinearLayout qchc_layout;
  private TextView setup_tv_update;
  private TextView title_name = null;
  private LinearLayout tuichu_layout;
  private String userid = "";

  private Dialog AlertDialogUtil(View paramView, Context paramContext, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString);
    localBuilder.setIcon(2130837566);
    localBuilder.setView(paramView);
    localBuilder.create();
    return localBuilder.show();
  }

  private void checkversion()
  {
    new GetverInfo(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str1 = paramAnonymousJiaDeNetResponse.result.toString();
        String str2 = Utils.getVersion(SetUpActivity.this);
        try
        {
          JSONObject localJSONObject = new JSONObject(str1);
          localJSONObject.getString("auto_upd");
          localJSONObject.getString("upd_url");
          String str3 = localJSONObject.getString("version");
          localJSONObject.getString("upd_log");
          if (!str3.equals(str2))
          {
            if ("".equals(str3))
              return;
            SetUpActivity.this.setup_tv_update.setText("发现新版本:" + str3);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
    , this, Utils.getPhoneId()).execute(new JiaDeNetRequest[0]);
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  public void init()
  {
    this.gywm_lin = ((LinearLayout)findViewById(2131427523));
    this.title_name = ((TextView)super.findViewById(2131427561));
    this.title_name.setText("设置");
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.tuichu_layout = ((LinearLayout)findViewById(2131427527));
    this.qchc_layout = ((LinearLayout)findViewById(2131427524));
    this.gxjc_layout = ((LinearLayout)findViewById(2131427525));
    this.setup_tv_update = ((TextView)findViewById(2131427526));
    checkversion();
    this.gywm_lin.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(SetUpActivity.this, GuanYuWoMenActivity.class);
        SetUpActivity.this.startActivity(localIntent);
      }
    });
    this.gxjc_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new GetverInfo(new JiaDeNetRequestTask.JiaDeNetCallback()
        {
          public void onCanceled()
          {
          }

          public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
          {
          }

          public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
          {
            String str1 = paramAnonymous2JiaDeNetResponse.result.toString();
            final String str2 = Utils.getVersion(SetUpActivity.this);
            try
            {
              JSONObject localJSONObject = new JSONObject(str1);
              String str3 = localJSONObject.getString("auto_upd");
              final String str4 = localJSONObject.getString("upd_url");
              final String str5 = localJSONObject.getString("version");
              String str6 = localJSONObject.getString("upd_log");
              if ((str5.equals(str2)) || ("".equals(str5)))
              {
                SetUpActivity.this.errormsg("已经是最新版本!");
                return;
              }
              "Y".equals(str3);
              AlertDialog.Builder localBuilder = new AlertDialog.Builder(SetUpActivity.this, 2131230724);
              localBuilder.setTitle("发现新版本请下载！");
              localBuilder.setMessage(str6);
              localBuilder.setPositiveButton("立即下载", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  View localView = LayoutInflater.from(SetUpActivity.this).inflate(2130903092, null);
                  ProgressBar localProgressBar = (ProgressBar)localView.findViewById(2131427585);
                  ((TextView)localView.findViewById(2131427586));
                  final Dialog localDialog = SetUpActivity.this.AlertDialogUtil(localView, SetUpActivity.this, SetUpActivity.this.getString(2131165205));
                  SetUpActivity.this.download = new Async();
                  SetUpActivity.this.download.setPb(localProgressBar);
                  Async.dialog = localDialog;
                  Async.ctx = SetUpActivity.this;
                  if ((str2 == null) || ("".equals(str2)))
                    Toast.makeText(SetUpActivity.this, "获取服务器url异常！下载失败！", 1).show();
                  while (true)
                  {
                    ((Button)localView.findViewById(2131427587)).setOnClickListener(new View.OnClickListener()
                    {
                      public void onClick(View paramAnonymous4View)
                      {
                        localDialog.dismiss();
                        SetUpActivity.this.download.onCancelled();
                      }
                    });
                    return;
                    Async localAsync = SetUpActivity.this.download;
                    String[] arrayOfString = new String[3];
                    arrayOfString[0] = str4;
                    arrayOfString[1] = Environment.getExternalStorageDirectory();
                    arrayOfString[2] = str5;
                    localAsync.execute(arrayOfString);
                  }
                }
              });
              localBuilder.setNegativeButton("以后再说", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                }
              });
              localBuilder.setCancelable(false);
              localBuilder.setOnKeyListener(new DialogInterface.OnKeyListener()
              {
                public boolean onKey(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int, KeyEvent paramAnonymous3KeyEvent)
                {
                  return paramAnonymous3Int == 84;
                }
              });
              localBuilder.create().show();
              return;
            }
            catch (JSONException localJSONException)
            {
              localJSONException.printStackTrace();
            }
          }
        }
        , SetUpActivity.this, Utils.getPhoneId()).execute(new JiaDeNetRequest[0]);
      }
    });
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SetUpActivity.this.finish();
      }
    });
    this.qchc_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Utils.clearM())
        {
          Utils.showDialog(SetUpActivity.this, "提示", "清除成功", 2130837514, null);
          return;
        }
        Utils.showDialog(SetUpActivity.this, "提示", "已经清除", 2130837514, null);
      }
    });
    if ("".equals(this.userid))
      this.tuichu_layout.setVisibility(8);
    while (true)
    {
      this.tuichu_layout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          new AlertDialog.Builder(SetUpActivity.this).setTitle("提示").setMessage("确认退出吗?").setPositiveButton("确定", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              LogOut localLogOut = new LogOut(new JiaDeNetRequestTask.JiaDeNetCallback()
              {
                public void onCanceled()
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                }

                public void onException(JiaDeNetException paramAnonymous3JiaDeNetException)
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                  Utils.showDialog(SetUpActivity.this, "提示", paramAnonymous3JiaDeNetException.getLocalizedMessage(), 2130837514, null);
                }

                public void onFinished(JiaDeNetResponse paramAnonymous3JiaDeNetResponse)
                {
                  String str = paramAnonymous3JiaDeNetResponse.result.toString();
                  if ((!"".equals(str)) && (!"ok".equals(str)))
                    Utils.showDialog(SetUpActivity.this, "提示", str, 2130837514, null);
                  while (true)
                  {
                    WaitLoadDialog.getInstance().dismissDialog();
                    return;
                    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(SetUpActivity.this);
                    localRegisterOrLoginManager.openDataBase();
                    localRegisterOrLoginManager.deleteAllDatas();
                    com.tg.jiadeonline.base.BaseActivity.selectIndex = 1;
                    Intent localIntent = new Intent(SetUpActivity.this, HomeActivity.class);
                    SetUpActivity.this.startActivity(localIntent);
                  }
                }
              }
              , SetUpActivity.this, SetUpActivity.this.imei, SetUpActivity.this.userid);
              WaitLoadDialog.getInstance().showDialog(SetUpActivity.this, null, true);
              localLogOut.execute(new JiaDeNetRequest[0]);
            }
          }).setNegativeButton("返回", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
            }
          }).show();
        }
      });
      return;
      this.tuichu_layout.setVisibility(0);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903072);
    Exit.getInstance().addActivity(this);
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userid = ((LoginBean)localList.get(0)).getUserId();
    init();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.SetUpActivity
 * JD-Core Version:    0.6.2
 */