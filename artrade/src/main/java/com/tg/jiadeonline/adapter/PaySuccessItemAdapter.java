package com.tg.jiadeonline.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.AuctionActivity;
import com.tg.jiadeonline.LogisticsActivity;
import com.tg.jiadeonline.bean.PaySucItemBean;
import com.tg.jiadeonline.bean.PaymentOrderBean;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import java.util.List;

public class PaySuccessItemAdapter extends BaseAdapter
{
  private Activity activity;
  List<PaySucItemBean> beanlist;
  private Context context;
  private int i;
  private LayoutInflater inflater;
  private PaymentOrderBean titebean;

  public PaySuccessItemAdapter(Context paramContext, Activity paramActivity, List<PaySucItemBean> paramList, PaymentOrderBean paramPaymentOrderBean, int paramInt)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
    this.context = paramContext;
    this.titebean = paramPaymentOrderBean;
    this.i = paramInt;
  }

  public int getCount()
  {
    if (this.beanlist.size() % 2 == 0)
      return this.beanlist.size() / 2;
    return (1 + this.beanlist.size()) / 2;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  @SuppressLint({"ResourceAsColor"})
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    final PaySucItemBean localPaySucItemBean1 = (PaySucItemBean)this.beanlist.get(paramInt * 2);
    String str1 = localPaySucItemBean1.getTitle();
    final String str2 = localPaySucItemBean1.getItemnum();
    String str3 = localPaySucItemBean1.getPicPath();
    String str4 = localPaySucItemBean1.getPriceBid();
    localPaySucItemBean1.getCommision();
    String str5 = localPaySucItemBean1.getCertFee();
    localPaySucItemBean1.getIsNeedCer();
    localPaySucItemBean1.getTransportID();
    localPaySucItemBean1.getCarrier();
    TextView localTextView1;
    label245: final PaySucItemBean localPaySucItemBean2;
    TextView localTextView2;
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903130, null);
      paramView.setTag(localDocItem);
      ImageView localImageView1 = (ImageView)paramView.findViewById(2131427869);
      Utils.loadImage(str3, localImageView1, this.activity);
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(PaySuccessItemAdapter.this.activity, AuctionActivity.class);
          localIntent.putExtra(CommonRef.itemnum, str2);
          PaySuccessItemAdapter.this.activity.startActivity(localIntent);
        }
      };
      localImageView1.setOnClickListener(local1);
      ((TextView)paramView.findViewById(2131427870)).setText(str1);
      ((TextView)paramView.findViewById(2131427871)).setText("成交价:" + str4 + "元\n证书费:" + str5 + "元");
      localTextView1 = (TextView)paramView.findViewById(2131427872);
      if (this.i != 1)
        break label504;
      localTextView1.setText("未付款");
      localTextView1.setTextColor(-7829368);
      localTextView1.setOnClickListener(null);
      if (this.beanlist.size() <= 1 + paramInt * 2)
        break label563;
      localPaySucItemBean2 = (PaySucItemBean)this.beanlist.get(1 + paramInt * 2);
      String str6 = localPaySucItemBean2.getTitle();
      final String str7 = localPaySucItemBean2.getItemnum();
      String str8 = localPaySucItemBean2.getPicPath();
      String str9 = localPaySucItemBean2.getPriceBid();
      localPaySucItemBean2.getCommision();
      String str10 = localPaySucItemBean2.getCertFee();
      localPaySucItemBean2.getIsNeedCer();
      localPaySucItemBean2.getTransportID();
      localPaySucItemBean2.getCarrier();
      ((LinearLayout)paramView.findViewById(2131427873)).setVisibility(0);
      ImageView localImageView2 = (ImageView)paramView.findViewById(2131427874);
      Utils.loadImage(str8, localImageView2, this.activity);
      View.OnClickListener local3 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(PaySuccessItemAdapter.this.activity, AuctionActivity.class);
          localIntent.putExtra(CommonRef.itemnum, str7);
          PaySuccessItemAdapter.this.activity.startActivity(localIntent);
        }
      };
      localImageView2.setOnClickListener(local3);
      ((TextView)paramView.findViewById(2131427875)).setText(str6);
      ((TextView)paramView.findViewById(2131427876)).setText("成交价：" + str9 + "元\n证书费:" + str10 + "元");
      localTextView2 = (TextView)paramView.findViewById(2131427877);
      if (this.i != 1)
        break label534;
      localTextView2.setText("未付款");
      localTextView2.setTextColor(-7829368);
      localTextView2.setOnClickListener(null);
    }
    label504: 
    while (this.i != 2)
    {
      return paramView;
      ((DocItem)paramView.getTag());
      break;
      if (this.i != 2)
        break label245;
      View.OnClickListener local2 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.putExtra("tite", localPaySucItemBean1);
          localIntent.setClass(PaySuccessItemAdapter.this.activity, LogisticsActivity.class);
          PaySuccessItemAdapter.this.activity.startActivity(localIntent);
        }
      };
      localTextView1.setOnClickListener(local2);
      break label245;
    }
    label534: View.OnClickListener local4 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent();
        localIntent.putExtra("tite", localPaySucItemBean2);
        localIntent.setClass(PaySuccessItemAdapter.this.activity, LogisticsActivity.class);
        PaySuccessItemAdapter.this.activity.startActivity(localIntent);
      }
    };
    localTextView2.setOnClickListener(local4);
    return paramView;
    label563: LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131427873);
    localLinearLayout.setVisibility(4);
    localLinearLayout.setOnClickListener(null);
    return paramView;
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
 * Qualified Name:     com.tg.jiadeonline.adapter.PaySuccessItemAdapter
 * JD-Core Version:    0.6.2
 */