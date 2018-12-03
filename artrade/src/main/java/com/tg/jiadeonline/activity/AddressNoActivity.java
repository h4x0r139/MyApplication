package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tg.jiadeonline.bean.AddressBean;
import com.tg.jiadeonline.utils.CommonRef;

public class AddressNoActivity extends Activity
{
  private String adduseType = CommonRef.adduseType_false;
  Button but = null;
  TextView text = null;
  int viewid = 2130903076;

  private void clean()
  {
    this.but = null;
    this.text = null;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.adduseType = getIntent().getStringExtra(CommonRef.Address_adduseType);
    setContentView(2130903076);
    this.but = ((Button)super.findViewById(2131427549));
    this.but.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AddressNoActivity.this.toNewAddAddress();
      }
    });
  }

  public void onDestroy()
  {
    super.onDestroy();
    clean();
  }

  public void toNewAddAddress()
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt(CommonRef.AddressAdd_Addresstype, CommonRef.Addresstype_add);
    localBundle.putString(CommonRef.Address_adduseType, this.adduseType);
    localBundle.putSerializable(CommonRef.AddressAdd_AddressBean, new AddressBean());
    Intent localIntent = new Intent();
    localIntent.putExtras(localBundle);
    localIntent.setClass(this, AddressAddActivity.class);
    startActivity(localIntent);
    finish();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.AddressNoActivity
 * JD-Core Version:    0.6.2
 */