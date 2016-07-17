package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tg.jiadeonline.AuctionActivity;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.SpecialFieldlistBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.CaredItemMod;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;

import java.util.List;

public class CategoriesPListAdapter extends BaseAdapter
{
  private Activity activity;
  private String imei;
  private LayoutInflater inflater;
  private List<SpecialFieldlistBean> item;
  private LinearLayout oldView;
  private String userid = "";

  public CategoriesPListAdapter(Context paramContext, Activity paramActivity, List<SpecialFieldlistBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.item = paramList;
    this.imei = Utils.getPhoneId();
  }

  public void changeCount(int paramInt)
  {
  }

  public int getCount()
  {
    return this.item.size();
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    SpecialFieldlistBean localSpecialFieldlistBean = (SpecialFieldlistBean)this.item.get(paramInt);
    final String str1 = localSpecialFieldlistBean.getItemnum();
    String str2 = localSpecialFieldlistBean.getTitle();
    String str3 = localSpecialFieldlistBean.getPicPath();
    String str4 = localSpecialFieldlistBean.getTimeStart();
    String str5 = localSpecialFieldlistBean.getTimeEnd();
    String str6 = localSpecialFieldlistBean.getSysTime();
    String str7 = localSpecialFieldlistBean.getCarePers();
    String str8 = localSpecialFieldlistBean.getMinimumBid();
    String str9 = localSpecialFieldlistBean.getCurrentBid();
    String str10 = localSpecialFieldlistBean.getBstatus();
    TextView localTextView1;
    label214: TextView localTextView2;
    label252: TextView localTextView3;
    label274: LinearLayout localLinearLayout1;
    long l1;
    long l2;
    long l3;
    label455: LinearLayout localLinearLayout2;
    final TextView localTextView4;
    final LinearLayout localLinearLayout3;
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903126, null);
      paramView.setTag(localDocItem);
      ImageView localImageView = (ImageView)paramView.findViewById(2131427835);
      Utils.loadImage(str3, localImageView, this.activity);
      ((TextView)paramView.findViewById(2131427838)).setText(str2);
      ((TextView)paramView.findViewById(2131427839)).setText("结束时间:" + str5);
      localTextView1 = (TextView)paramView.findViewById(2131427840);
      if ((str9 != null) && (!"".equals(str9)))
        break label603;
      localTextView1.setText("0.00");
      localTextView2 = (TextView)paramView.findViewById(2131427841);
      if (!str10.equals("np"))
        break label613;
      localTextView2.setTextColor(this.activity.getResources().getColor(2131034132));
      if ((str9 != null) && (!"".equals(str9)))
        break label634;
      localTextView2.setText("0");
      ((TextView)paramView.findViewById(2131427842)).setText(str7 + "人围观");
      localTextView3 = (TextView)paramView.findViewById(2131427837);
      localLinearLayout1 = (LinearLayout)paramView.findViewById(2131427836);
      l1 = Long.valueOf(str4.replace("-", "").replace(":", "").replace(" ", "")).longValue();
      l2 = Long.valueOf(str5.replace("-", "").replace(":", "").replace(" ", "")).longValue();
      l3 = Long.valueOf(str6.replace("-", "").replace(":", "").replace(" ", "")).longValue();
      if (!"".equals(this.userid))
        break label698;
      if (l3 >= l1)
        break label644;
      localTextView3.setText("未开始");
      localLinearLayout1.setBackgroundResource(2130837606);
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(CategoriesPListAdapter.this.activity, AuctionActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putString(CommonRef.itemnum, str1);
          localBundle.putInt("ppid", paramInt);
          localIntent.putExtras(localBundle);
          CategoriesPListAdapter.this.activity.startActivityForResult(localIntent, 1);
        }
      };
      localImageView.setOnClickListener(local1);
      localLinearLayout2 = (LinearLayout)paramView.findViewById(2131427414);
      View.OnClickListener local2 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(CategoriesPListAdapter.this.activity, AuctionActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putString(CommonRef.itemnum, str1);
          localBundle.putInt("ppid", paramInt);
          localIntent.putExtras(localBundle);
          CategoriesPListAdapter.this.activity.startActivityForResult(localIntent, 1);
        }
      };
      localLinearLayout2.setOnClickListener(local2);
      localTextView4 = (TextView)paramView.findViewById(2131427844);
      localLinearLayout3 = (LinearLayout)paramView.findViewById(2131427843);
      if (!localSpecialFieldlistBean.isIscheck())
        break label837;
      localLinearLayout3.setVisibility(0);
    }
    while (true)
    {
      localTextView4.setText("收藏");
      View.OnClickListener local3 = new View.OnClickListener()
      {
        private void choseCare(String paramAnonymousString)
        {
          RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(CategoriesPListAdapter.this.activity);
          localRegisterOrLoginManager.openDataBase();
          List localList = localRegisterOrLoginManager.fetchAllDatas();
          localRegisterOrLoginManager.closeDataBase();
          if (localList != null)
          {
            String str = ((LoginBean)localList.get(0)).getUserId();
            CaredItemMod localCaredItemMod = new CaredItemMod(new JiaDeNetRequestTask.JiaDeNetCallback()
            {
              public void onCanceled()
              {
                WaitLoadDialog.getInstance().dismissDialog();
              }

              public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
              {
                WaitLoadDialog.getInstance().dismissDialog();
                Utils.showDialog(CategoriesPListAdapter.this.activity, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
              }

              public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
              {
                String str = paramAnonymous2JiaDeNetResponse.result.toString();
                if ((!"".equals(str)) && (!"ok".equals(str)))
                  Utils.showDialog(CategoriesPListAdapter.this.activity, "提示", str, 2130837514, null);
                while (true)
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                  return;
                  Utils.showDialog(CategoriesPListAdapter.this.activity, "提示", "收藏成功", 2130837514, null);
                  this.val$shoucang_tv.setText("收藏");
                }
              }
            }
            , CategoriesPListAdapter.this.activity, CategoriesPListAdapter.this.imei, str, str1, paramAnonymousString);
            WaitLoadDialog.getInstance().showDialog(CategoriesPListAdapter.this.activity, null, true);
            localCaredItemMod.execute(new JiaDeNetRequest[0]);
            return;
          }
          new AlertDialog.Builder(CategoriesPListAdapter.this.activity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              Intent localIntent = new Intent(CategoriesPListAdapter.this.activity, LoginActivity.class);
              CategoriesPListAdapter.this.activity.startActivity(localIntent);
            }
          }).setNegativeButton("返回", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
            }
          }).show();
        }

        public void onClick(View paramAnonymousView)
        {
          if (!"收藏".equals(localTextView4.getText()));
          for (String str = "uncare"; ; str = "care")
          {
            choseCare(str);
            return;
          }
        }
      };
      localLinearLayout3.setOnClickListener(local3);
      View.OnTouchListener local4 = new View.OnTouchListener()
      {
        float oldX = 0.0F;
        float oldY = 0.0F;

        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          switch (paramAnonymousMotionEvent.getAction())
          {
          default:
          case 0:
          case 1:
          }
          float f;
          do
          {
            while (true)
            {
              return false;
              this.oldX = paramAnonymousMotionEvent.getX();
              this.oldY = paramAnonymousMotionEvent.getY();
            }
            if (CategoriesPListAdapter.this.oldView != null)
              CategoriesPListAdapter.this.oldView.setVisibility(8);
            f = paramAnonymousMotionEvent.getX();
            paramAnonymousMotionEvent.getY();
            if (this.oldX - f > 50.0F)
            {
              ((SpecialFieldlistBean)CategoriesPListAdapter.this.item.get(paramInt)).setIscheck(false);
              CategoriesPListAdapter.this.oldView = localLinearLayout3;
              localLinearLayout3.setVisibility(0);
              return true;
            }
          }
          while (f - this.oldX <= 50.0F);
          ((SpecialFieldlistBean)CategoriesPListAdapter.this.item.get(paramInt)).setIscheck(false);
          CategoriesPListAdapter.this.oldView = null;
          localLinearLayout3.setVisibility(8);
          return true;
        }
      };
      localLinearLayout2.setOnTouchListener(local4);
      return paramView;
      ((DocItem)paramView.getTag());
      break;
      label603: localTextView1.setText(str8);
      break label214;
      label613: localTextView2.setTextColor(this.activity.getResources().getColor(2131034131));
      break label252;
      label634: localTextView2.setText(str9);
      break label274;
      label644: if ((l1 <= l3) && (l3 <= l2))
      {
        localTextView3.setText("进行中");
        localLinearLayout1.setBackgroundResource(2130837619);
        break label455;
      }
      localTextView3.setText("已结束");
      localLinearLayout1.setBackgroundResource(2130837612);
      break label455;
      label698: if (l3 < l1)
      {
        localTextView3.setText("未开始");
        localLinearLayout1.setBackgroundResource(2130837606);
        break label455;
      }
      if ((l1 <= l3) && (l3 <= l2))
      {
        if (str10.equals("ch"))
        {
          localTextView3.setText("竞拍领先");
          localLinearLayout1.setBackgroundResource(2130837573);
          break label455;
        }
        if (str10.equals("ot"))
        {
          localTextView3.setText("竞拍出局");
          localLinearLayout1.setBackgroundResource(2130837572);
          break label455;
        }
        localTextView3.setText("进行中");
        localLinearLayout1.setBackgroundResource(2130837619);
        break label455;
      }
      localTextView3.setText("已结束");
      localLinearLayout1.setBackgroundResource(2130837612);
      break label455;
      label837: localLinearLayout3.setVisibility(8);
    }
  }

  public final class DocItem
  {
    TextView comtypeName;
    ImageView comtypePic;
    RelativeLayout layout;

    public DocItem()
    {
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.adapter.CategoriesPListAdapter
 * JD-Core Version:    0.6.2
 */