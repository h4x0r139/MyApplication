package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.GetUserBaseInfo;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.updateUserBaseInfo;
import com.tg.jiadeonline.utils.IDCard;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class PeopleInfoActivity extends Activity
{
  Activity activity;
  Context context;
  EditText email;
  LinearLayout grzx_lin_tijiao;
  EditText nickName;
  EditText telPhone;
  String userid = "";
  EditText zip;

  public void init()
  {
    this.nickName = ((EditText)findViewById(2131427531));
    this.telPhone = ((EditText)findViewById(2131427533));
    this.email = ((EditText)findViewById(2131427535));
    this.zip = ((EditText)findViewById(2131427537));
    this.grzx_lin_tijiao = ((LinearLayout)findViewById(2131427538));
    ImageView localImageView = (ImageView)findViewById(2131427560);
    ((TextView)findViewById(2131427561)).setText("个人信息");
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PeopleInfoActivity.this.finish();
      }
    });
    this.context = this;
    this.activity = this;
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this.activity);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if ((localList != null) && (localList.size() > 0))
    {
      ((LoginBean)localList.get(0)).getPassword();
      String str = ((LoginBean)localList.get(0)).getUserId();
      GetUserBaseInfo localGetUserBaseInfo = new GetUserBaseInfo(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          Utils.showDialog(PeopleInfoActivity.this.activity, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          String str1 = paramAnonymousJiaDeNetResponse.result.toString();
          if ((!"".equals(str1)) && (!"ok".equals(str1)));
          try
          {
            JSONObject localJSONObject = new JSONObject(str1);
            String str2 = localJSONObject.getString("name");
            String str3 = localJSONObject.getString("personalId");
            String str4 = localJSONObject.getString("address");
            String str5 = localJSONObject.getString("postal_code");
            PeopleInfoActivity.this.nickName.setText(str2);
            PeopleInfoActivity.this.telPhone.setText(str3);
            PeopleInfoActivity.this.email.setText(str4);
            PeopleInfoActivity.this.zip.setText(str5);
            return;
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      }
      , this.activity, Utils.getPhoneId(), str);
      WaitLoadDialog.getInstance().showDialog(this.activity, null, true);
      localGetUserBaseInfo.execute(new JiaDeNetRequest[0]);
    }
    while (true)
    {
      this.grzx_lin_tijiao.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str1 = PeopleInfoActivity.this.nickName.getText().toString();
          String str2 = PeopleInfoActivity.this.telPhone.getText().toString();
          String str3 = PeopleInfoActivity.this.email.getText().toString();
          String str4 = PeopleInfoActivity.this.zip.getText().toString();
          if ("".equals(str1))
          {
            Utils.showDialog(PeopleInfoActivity.this.context, "提示", "请输入真实姓名！", 2130837567, null);
            return;
          }
          if ("".equals(str2))
          {
            Utils.showDialog(PeopleInfoActivity.this.context, "提示", "请输入真实身份证", 2130837567, null);
            return;
          }
          if (!Boolean.valueOf(new IDCard().verify(str2)).booleanValue())
          {
            Utils.showDialog(PeopleInfoActivity.this.context, "提示", "请输入真实身份证！", 2130837567, null);
            return;
          }
          RegisterOrLoginManager localRegisterOrLoginManager1 = new RegisterOrLoginManager(PeopleInfoActivity.this.activity);
          localRegisterOrLoginManager1.openDataBase();
          List localList = localRegisterOrLoginManager1.fetchAllDatas();
          localRegisterOrLoginManager1.closeDataBase();
          if ((localList != null) && (localList.size() > 0))
          {
            String str5 = ((LoginBean)localList.get(0)).getPassword();
            String str6 = ((LoginBean)localList.get(0)).getUserId();
            if (("".equals(str5)) || (str5 == null))
            {
              RegisterOrLoginManager localRegisterOrLoginManager2 = new RegisterOrLoginManager(PeopleInfoActivity.this.activity);
              localRegisterOrLoginManager2.openDataBase();
              localRegisterOrLoginManager2.deleteAllDatas();
              localRegisterOrLoginManager2.closeDataBase();
              new AlertDialog.Builder(PeopleInfoActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  Intent localIntent = new Intent(PeopleInfoActivity.this.activity, LoginActivity.class);
                  PeopleInfoActivity.this.activity.startActivity(localIntent);
                }
              }).setNegativeButton("返回", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                }
              }).show();
              return;
            }
            updateUserBaseInfo localupdateUserBaseInfo = new updateUserBaseInfo(new JiaDeNetRequestTask.JiaDeNetCallback()
            {
              public void onCanceled()
              {
                WaitLoadDialog.getInstance().dismissDialog();
              }

              public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                Utils.showDialog(PeopleInfoActivity.this.activity, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
              }

              public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                String str = paramAnonymous2JiaDeNetResponse.result.toString();
                if ("ok".equals(str))
                {
                  Utils.showDialog(PeopleInfoActivity.this.activity, "提示", "保存成功!", 2130837514, null);
                  PeopleInfoActivity.this.activity.finish();
                  return;
                }
                Utils.showDialog(PeopleInfoActivity.this.activity, "提示", str, 2130837514, null);
              }
            }
            , PeopleInfoActivity.this.activity, Utils.getPhoneId(), str6, str1, str5, str2, str3, str4);
            WaitLoadDialog.getInstance().showDialog(PeopleInfoActivity.this.activity, null, true);
            localupdateUserBaseInfo.execute(new JiaDeNetRequest[0]);
            return;
          }
          new AlertDialog.Builder(PeopleInfoActivity.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              Intent localIntent = new Intent(PeopleInfoActivity.this.activity, LoginActivity.class);
              PeopleInfoActivity.this.activity.startActivity(localIntent);
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
      new AlertDialog.Builder(this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Intent localIntent = new Intent(PeopleInfoActivity.this.activity, LoginActivity.class);
          PeopleInfoActivity.this.activity.startActivity(localIntent);
        }
      }).setNegativeButton("返回", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
        }
      }).show();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903073);
    init();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.PeopleInfoActivity
 * JD-Core Version:    0.6.2
 */