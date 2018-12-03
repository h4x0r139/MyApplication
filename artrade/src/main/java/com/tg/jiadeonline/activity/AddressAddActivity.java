package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.net.ToAddrAdd;
import com.tg.jiadeonline.net.ToAddrDetailMod;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Constants;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.List;

public class AddressAddActivity extends Activity
{
  private static int provincePosition = 3;
  private int Addresstype = CommonRef.Addresstype;
  private EditText addaccname = null;
  private EditText addedittext = null;
  private EditText addemail = null;
  private EditText addidcard = null;
  private String addressId = "";
  private String addressid = "";
  private EditText addtel = null;
  private String adduseType = CommonRef.adduseType_false;
  private EditText addzipcood = null;
  private ImageView backImageView;
  private LinearLayout backLayout = null;
  private Bitmap bitmap;
  private Button button = null;
  private String[][] city = Constants.pandc;
  private ArrayAdapter<String> cityAdapter = null;
  private Spinner citySpinner = null;
  private Context context;
  int imageType = 1;
  private ImageView imagea = null;
  private ImageView imageb = null;
  private String imei;
  private boolean isadd = true;
  private String[] province = Constants.province;
  private ArrayAdapter<String> provinceAdapter = null;
  private Spinner provinceSpinner = null;
  private TextView text = null;
  private String title = "添加地址";
  private TextView titlename;
  private String userid = "";

  private void clean()
  {
    if (this.bitmap != null)
      this.bitmap.recycle();
    this.bitmap = null;
    this.text = null;
    this.addedittext = null;
    this.addemail = null;
    this.addaccname = null;
    this.addtel = null;
    this.addidcard = null;
    this.button = null;
    this.imagea = null;
    this.imageb = null;
    this.provinceSpinner = null;
    this.citySpinner = null;
    this.provinceAdapter = null;
    this.cityAdapter = null;
    this.title = null;
    this.userid = null;
    this.addressid = null;
    this.adduseType = null;
    this.province = null;
    this.city = null;
  }

  private void initUser()
  {
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if ((localList != null) && (localList.size() > 0))
    {
      this.userid = ((LoginBean)localList.get(0)).getUserId();
      return;
    }
    new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Intent localIntent = new Intent(AddressAddActivity.this, LoginActivity.class);
        AddressAddActivity.this.startActivity(localIntent);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).show();
  }

  private void setSpinner(String paramString1, String paramString2)
  {
    this.provinceSpinner = ((Spinner)findViewById(2131427539));
    this.citySpinner = ((Spinner)findViewById(2131427540));
    this.provinceSpinner.setMinimumWidth((-50 + getWidthPixels()) / 3);
    this.citySpinner.setMinimumWidth((-50 + getWidthPixels()) / 3);
    this.provinceAdapter = new ArrayAdapter(this, 17367048, this.province);
    this.provinceSpinner.setAdapter(this.provinceAdapter);
    this.provinceSpinner.setSelection(0, true);
    this.cityAdapter = new ArrayAdapter(this, 17367048, this.city[0]);
    this.citySpinner.setAdapter(this.cityAdapter);
    this.citySpinner.setSelection(0, true);
    this.provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        AddressAddActivity.this.cityAdapter = new ArrayAdapter(AddressAddActivity.this, 17367048, AddressAddActivity.this.city[paramAnonymousInt]);
        AddressAddActivity.this.citySpinner.setAdapter(AddressAddActivity.this.cityAdapter);
        AddressAddActivity.provincePosition = paramAnonymousInt;
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
      }
    });
    if (paramString1 != "")
    {
      int j = this.provinceAdapter.getPosition(paramString1);
      this.provinceSpinner.setSelection(j, true);
    }
    if (paramString2 != "")
    {
      int i = this.cityAdapter.getPosition(paramString2);
      this.citySpinner.setSelection(i, true);
    }
  }

  public void alertDialog(String paramString, final EditText paramEditText)
  {
    new AlertDialog.Builder(this).setTitle("温馨提示").setMessage(paramString).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramEditText.setFocusable(true);
      }
    }).show();
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  public int getWidthPixels()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903075);
    this.context = this;
    this.imei = Utils.getPhoneId();
    initUser();
    CommonRef.isnewadd = true;
    this.titlename = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AddressAddActivity.this.finish();
      }
    });
    Intent localIntent = getIntent();
    this.adduseType = localIntent.getStringExtra(CommonRef.Address_adduseType);
    this.Addresstype = localIntent.getIntExtra(CommonRef.AddressAdd_Addresstype, 0);
    if (this.Addresstype == CommonRef.Addresstype_update)
    {
      this.title = "编辑地址";
      this.isadd = false;
    }
    while (true)
    {
      this.titlename.setText(this.title);
      AddressBean localAddressBean = (AddressBean)localIntent.getSerializableExtra(CommonRef.AddressAdd_AddressBean);
      this.addressId = localAddressBean.getAutoid();
      this.addedittext = ((EditText)super.findViewById(2131427541));
      setOnfocus(this.addedittext, getString(2131165251));
      this.addzipcood = ((EditText)super.findViewById(2131427542));
      this.addzipcood.setInputType(2);
      this.addzipcood.setWidth((-50 + getWidthPixels()) / 2);
      setOnfocus(this.addzipcood, getString(2131165252));
      this.addaccname = ((EditText)super.findViewById(2131427543));
      this.addaccname.setWidth((-50 + getWidthPixels()) / 2);
      setOnfocus(this.addaccname, getString(2131165253));
      this.addidcard = ((EditText)super.findViewById(2131427546));
      this.addtel = ((EditText)super.findViewById(2131427544));
      this.addtel.setInputType(3);
      setOnfocus(this.addtel, getString(2131165254));
      this.addemail = ((EditText)super.findViewById(2131427545));
      setOnfocus(this.addemail, getString(2131165255));
      this.button = ((Button)super.findViewById(2131427547));
      this.button.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str1 = AddressAddActivity.this.provinceSpinner.getSelectedItem().toString();
          String str2 = AddressAddActivity.this.citySpinner.getSelectedItem().toString();
          String str3 = AddressAddActivity.this.addedittext.getText().toString();
          String str4 = AddressAddActivity.this.addemail.getText().toString();
          String str5 = AddressAddActivity.this.addzipcood.getText().toString();
          String str6 = AddressAddActivity.this.addaccname.getText().toString();
          String str7 = AddressAddActivity.this.addtel.getText().toString();
          String str8 = AddressAddActivity.this.addidcard.getText().toString();
          if ((str3 == null) || (str3.equals("")))
          {
            AddressAddActivity.this.alertDialog("请输入地址", AddressAddActivity.this.addedittext);
            return;
          }
          if ((str6 == null) || (str6.equals("")))
          {
            AddressAddActivity.this.alertDialog("请输入收货人姓名", AddressAddActivity.this.addaccname);
            return;
          }
          if ((str7 == null) || (str7.equals("")))
          {
            AddressAddActivity.this.alertDialog("请输入联系电话", AddressAddActivity.this.addtel);
            return;
          }
          if (AddressAddActivity.this.isadd)
          {
            ToAddrAdd localToAddrAdd = new ToAddrAdd(new JiaDeNetRequestTask.JiaDeNetCallback()
            {
              public void onCanceled()
              {
                WaitLoadDialog.getInstance().dismissDialog();
              }

              public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                Utils.showDialog(AddressAddActivity.this.context, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
              }

              public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                String str = (String)paramAnonymous2JiaDeNetResponse.result;
                if ((!"".equals(str)) && (!"ok".equals(str)))
                {
                  Utils.showDialog(AddressAddActivity.this.context, "提示", str, 2130837514, null);
                  return;
                }
                AddressAddActivity.this.errormsg("添加地址成功!");
                AddressAddActivity.this.finish();
              }
            }
            , AddressAddActivity.this.context, AddressAddActivity.this.imei, AddressAddActivity.this.userid, "", str1, str2, str3, str6, str7, str5, str4, str8);
            WaitLoadDialog.getInstance().showDialog(AddressAddActivity.this, null, true);
            localToAddrAdd.execute(new JiaDeNetRequest[0]);
            return;
          }
          JiaDeNetRequestTask.JiaDeNetCallback local2 = new JiaDeNetRequestTask.JiaDeNetCallback()
          {
            public void onCanceled()
            {
              WaitLoadDialog.getInstance().dismissDialog();
            }

            public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              Utils.showDialog(AddressAddActivity.this.context, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
            }

            public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
            {
              WaitLoadDialog.getInstance().dismissDialog();
              String str = (String)paramAnonymous2JiaDeNetResponse.result;
              if ((!"".equals(str)) && (!"ok".equals(str)))
              {
                Utils.showDialog(AddressAddActivity.this.context, "提示", str, 2130837514, null);
                return;
              }
              AddressAddActivity.this.errormsg("修改地址成功!");
              AddressAddActivity.this.finish();
            }
          };
          ToAddrDetailMod localToAddrDetailMod = new ToAddrDetailMod(local2, AddressAddActivity.this.context, AddressAddActivity.this.imei, AddressAddActivity.this.userid, AddressAddActivity.this.addressId, "", str1, str2, str3, str6, str7, str5, str4, str8);
          WaitLoadDialog.getInstance().showDialog(AddressAddActivity.this, null, true);
          localToAddrDetailMod.execute(new JiaDeNetRequest[0]);
        }
      });
      if (this.Addresstype != CommonRef.Addresstype_update)
        break;
      this.addressid = localAddressBean.getAutoid();
      String str1 = localAddressBean.getProv();
      String str2 = localAddressBean.getCity();
      localAddressBean.getCounty();
      String str3 = localAddressBean.getAddress();
      String str4 = localAddressBean.getZipcode();
      String str5 = localAddressBean.getEmail();
      String str6 = localAddressBean.getTel();
      String str7 = localAddressBean.getName();
      String str8 = localAddressBean.getIdentityCard();
      this.addedittext.setText(str3);
      this.addzipcood.setText(str4);
      this.addemail.setText(str5);
      this.addaccname.setText(str7);
      this.addtel.setText(str6);
      this.addidcard.setText(str8);
      setSpinner(str1, str2);
      return;
      if (this.Addresstype == CommonRef.Addresstype_add)
      {
        this.title = "新增地址";
        this.isadd = true;
      }
    }
    setSpinner("", "");
  }

  public void onDestroy()
  {
    super.onDestroy();
    clean();
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void setOnfocus(final EditText paramEditText, final String paramString)
  {
    paramEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          paramEditText.setHint(paramString);
          return;
        }
        paramEditText.setHint(paramString);
      }
    });
  }

  public String uri2Path(Uri paramUri)
  {
    String[] arrayOfString = { "_data" };
    Cursor localCursor = getContentResolver().query(paramUri, arrayOfString, null, null, null);
    int i = localCursor.getColumnIndexOrThrow("_data");
    localCursor.moveToFirst();
    return localCursor.getString(i);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.AddressAddActivity
 * JD-Core Version:    0.6.2
 */