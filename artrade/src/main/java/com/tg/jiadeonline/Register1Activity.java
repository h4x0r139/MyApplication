package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.activity.PersonalcenterActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.LoginManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.Register;
import com.tg.jiadeonline.utils.SharedPreferencesUtil;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.List;

public class Register1Activity extends Activity
{
  private ImageView backImageView;
  private Context context;
  private String imei = "";
  private boolean ischeck = true;
  private String phone;
  private PopupWindow popright = null;
  private TextView titleName;
  private String yanzhengma = "";
  private Button zhuce_button2;
  private Button zhuce_button3;
  private EditText zhuce_email;
  private EditText zhuce_mima;
  private EditText zhuce_mima2;
  private EditText zhuce_username;
  private ImageButton zhuce_xieyi;
  private TextView zhuce_yuedu;

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    this.imei = Utils.getPhoneId();
    requestWindowFeature(1);
    setContentView(2130903069);
    if ((getIntent().hasExtra("phone")) && (getIntent().hasExtra("yanzhengma")))
    {
      this.phone = getIntent().getStringExtra("phone");
      this.yanzhengma = getIntent().getStringExtra("yanzhengma");
    }
    while (true)
    {
      this.zhuce_button2 = ((Button)findViewById(2131427509));
      this.titleName = ((TextView)findViewById(2131427561));
      this.backImageView = ((ImageView)findViewById(2131427560));
      this.zhuce_username = ((EditText)findViewById(2131427512));
      this.zhuce_email = ((EditText)findViewById(2131427514));
      this.zhuce_mima = ((EditText)findViewById(2131427516));
      this.zhuce_mima2 = ((EditText)findViewById(2131427518));
      this.zhuce_button3 = ((Button)findViewById(2131427510));
      this.zhuce_yuedu = ((TextView)findViewById(2131427521));
      this.zhuce_button3.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(Register1Activity.this, LoginActivity.class);
          Register1Activity.this.startActivity(localIntent);
          Register1Activity.this.finish();
        }
      });
      this.zhuce_yuedu.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Register1Activity.this.popwindows("");
          Register1Activity.this.popright.showAsDropDown(Register1Activity.this.titleName, 300, -200);
        }
      });
      this.zhuce_username.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          EditText localEditText = (EditText)paramAnonymousView;
          if (!paramAnonymousBoolean)
          {
            localEditText.setHint(localEditText.getTag().toString());
            return;
          }
          localEditText.setTag(localEditText.getHint().toString());
          localEditText.setHint("");
        }
      });
      this.zhuce_email.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          EditText localEditText = (EditText)paramAnonymousView;
          if (!paramAnonymousBoolean)
          {
            localEditText.setHint(localEditText.getTag().toString());
            return;
          }
          localEditText.setTag(localEditText.getHint().toString());
          localEditText.setHint("");
        }
      });
      this.zhuce_mima.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          EditText localEditText = (EditText)paramAnonymousView;
          if (!paramAnonymousBoolean)
          {
            localEditText.setHint(localEditText.getTag().toString());
            return;
          }
          localEditText.setTag(localEditText.getHint().toString());
          localEditText.setHint("");
        }
      });
      this.zhuce_mima2.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          EditText localEditText = (EditText)paramAnonymousView;
          if (!paramAnonymousBoolean)
          {
            localEditText.setHint(localEditText.getTag().toString());
            return;
          }
          localEditText.setTag(localEditText.getHint().toString());
          localEditText.setHint("");
        }
      });
      this.zhuce_xieyi = ((ImageButton)findViewById(2131427519));
      this.zhuce_xieyi.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (Register1Activity.this.ischeck)
          {
            Register1Activity.this.ischeck = false;
            Register1Activity.this.zhuce_xieyi.setBackgroundResource(2130837617);
            return;
          }
          Register1Activity.this.ischeck = true;
          Register1Activity.this.zhuce_xieyi.setBackgroundResource(2130837618);
        }
      });
      this.titleName.setText("注册");
      this.backImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Register1Activity.this.finish();
        }
      });
      this.zhuce_button2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str1 = Register1Activity.this.zhuce_username.getText().toString().trim();
          String str2 = Register1Activity.this.zhuce_email.getText().toString().trim();
          String str3 = Register1Activity.this.zhuce_mima.getText().toString().trim();
          String str4 = Register1Activity.this.zhuce_mima2.getText().toString().trim();
          if (str2.equals(""))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请输入用户名或者邮箱！", 2130837567, null);
            return;
          }
          if (str3.equals(""))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请输入密码！", 2130837567, null);
            return;
          }
          if (str4.equals(""))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请输入确认密码！", 2130837567, null);
            return;
          }
          if (!str3.equals(str4))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "两次密码不一样", 2130837567, null);
            return;
          }
          if (Register1Activity.this.phone.equals(""))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请输入手机号", 2130837567, null);
            return;
          }
          if (Register1Activity.this.yanzhengma.equals(""))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请先获取验证码", 2130837567, null);
            return;
          }
          if (Register1Activity.this.yanzhengma.equals(""))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请输入验证码", 2130837567, null);
            return;
          }
          if (!Register1Activity.this.yanzhengma.equals(Register1Activity.this.yanzhengma))
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "请输入正确验证码", 2130837567, null);
            return;
          }
          if (!Register1Activity.this.ischeck)
          {
            Utils.showDialog(Register1Activity.this.context, "提示", "尚未同意服务条款!", 2130837567, null);
            return;
          }
          Register localRegister = new Register(new JiaDeNetRequestTask.JiaDeNetCallback()
          {
            public void onCanceled()
            {
              WaitLoadDialog.getInstance().dismissDialog();
            }

            public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              Utils.showDialog(Register1Activity.this, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
            }

            public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              String str1 = paramAnonymous2JiaDeNetResponse.result.toString();
              if ((!"".equals(str1)) && (!"ok".equals(str1)))
              {
                Utils.showDialog(Register1Activity.this, "提示", str1, 2130837514, null);
                return;
              }
              RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(Register1Activity.this.context);
              localRegisterOrLoginManager.openDataBase();
              List localList = localRegisterOrLoginManager.fetchAllDatas();
              localRegisterOrLoginManager.closeDataBase();
              String str2 = ((LoginBean)localList.get(0)).getNickname();
              String str3 = ((LoginBean)localList.get(0)).getUserId();
              LoginManager localLoginManager = new LoginManager(Register1Activity.this.context);
              localLoginManager.openDataBase();
              LoginBean localLoginBean = new LoginBean();
              localLoginBean.setNickname(str2);
              localLoginBean.setUserId(str3);
              localLoginManager.insertData(localLoginBean);
              localLoginManager.closeDataBase();
              new AlertDialog.Builder(Register1Activity.this).setTitle(" ").setMessage("注册成功!").setPositiveButton("确定", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous3DialogInterface, int paramAnonymous3Int)
                {
                  SharedPreferencesUtil.writeIsFirstUse(Register1Activity.this);
                  Intent localIntent = new Intent(Register1Activity.this, PersonalcenterActivity.class);
                  Register1Activity.this.startActivity(localIntent);
                }
              }).show();
            }
          }
          , Register1Activity.this, Register1Activity.this.imei, str1, str3, str2, Register1Activity.this.phone, Register1Activity.this.yanzhengma);
          WaitLoadDialog.getInstance().showDialog(Register1Activity.this, null, true);
          localRegister.execute(new JiaDeNetRequest[0]);
        }
      });
      return;
      errormsg("请先验证手机号码!");
    }
  }

  public final void popwindows(String paramString)
  {
    View localView = LayoutInflater.from(this.context).inflate(2130903070, null);
    this.popright = new PopupWindow(localView, -1, -1, false);
    ((TextView)localView.findViewById(2131427522));
    this.popright.setAnimationStyle(2131230730);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    ((ImageView)localView.findViewById(2131427396)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (Register1Activity.this.popright.isShowing())
          Register1Activity.this.popright.dismiss();
      }
    });
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.Register1Activity
 * JD-Core Version:    0.6.2
 */