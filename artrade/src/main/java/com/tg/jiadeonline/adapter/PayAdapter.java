package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.AuctionActivity;
import com.tg.jiadeonline.bean.ItemsBean;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import java.util.ArrayList;

public class PayAdapter extends BaseAdapter
{
  private Activity activity;
  private double allMoney = 0.0D;
  private ArrayList<ItemsBean> beanList;
  boolean boo = true;
  private double certFeeAll = 0.0D;
  private double commisionAll = 0.0D;
  private LayoutInflater inflater;
  private TextView pay_allMoney;
  private TextView pay_mes;
  private double priceAll = 0.0D;

  public PayAdapter(Context paramContext, Activity paramActivity, ArrayList<ItemsBean> paramArrayList, TextView paramTextView1, TextView paramTextView2)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanList = paramArrayList;
    this.pay_mes = paramTextView1;
    this.pay_allMoney = paramTextView2;
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
      for (int i = 0; ; i++)
      {
        if (i >= paramArrayList.size())
        {
          this.allMoney = (this.priceAll + this.commisionAll + this.certFeeAll);
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Double.valueOf(this.priceAll);
          String str3 = String.format("%.2f", arrayOfObject1);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Double.valueOf(this.commisionAll);
          String str4 = String.format("%.2f", arrayOfObject2);
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = Double.valueOf(this.certFeeAll);
          String str5 = String.format("%.2f", arrayOfObject3);
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Double.valueOf(this.allMoney);
          String str6 = String.format("%.2f", arrayOfObject4);
          paramTextView2.setText("总款￥" + str6);
          paramTextView1.setText("共" + paramArrayList.size() + "件拍品" + "\n" + "\n" + "拍品" + str3 + "+佣金" + str4 + "+证书费" + str5 + "+快递费0");
          return;
        }
        String str1 = ((ItemsBean)paramArrayList.get(i)).getPriceBid();
        String str2 = ((ItemsBean)paramArrayList.get(i)).getCommision();
        this.priceAll += Double.parseDouble(str1);
        this.commisionAll += Double.parseDouble(str2);
      }
    paramTextView2.setText("总款￥0");
    paramTextView1.setText("共0件拍品\n\n拍品0+佣金0+证书费0+快递费0");
  }

  public double getAllMoney()
  {
    return this.allMoney;
  }

  public ArrayList<ItemsBean> getBeanList()
  {
    return this.beanList;
  }

  public double getCertFeeAll()
  {
    return this.certFeeAll;
  }

  public double getCommisionAll()
  {
    return this.commisionAll;
  }

  public int getCount()
  {
    return this.beanList.size();
  }

  public ArrayList<ItemsBean> getIList()
  {
    return this.beanList;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public double getPriceAll()
  {
    return this.priceAll;
  }

  public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    final String str6;
    final CheckBox localCheckBox;
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903127, null);
      paramView.setTag(localDocItem);
      ItemsBean localItemsBean = (ItemsBean)this.beanList.get(paramInt);
      final String str1 = localItemsBean.getItemnum();
      String str2 = localItemsBean.getTitle();
      String str3 = localItemsBean.getPicPath();
      String str4 = localItemsBean.getPriceBid();
      localItemsBean.getDateBid();
      String str5 = localItemsBean.getCommision();
      str6 = localItemsBean.getCertFee();
      ImageView localImageView = (ImageView)paramView.findViewById(2131427846);
      Utils.loadImage(str3, localImageView, this.activity);
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(PayAdapter.this.activity, AuctionActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putString(CommonRef.itemnum, str1);
          localIntent.putExtras(localBundle);
          PayAdapter.this.activity.startActivity(localIntent);
        }
      };
      localImageView.setOnClickListener(local1);
      ((TextView)paramView.findViewById(2131427439)).setText(str2);
      ((TextView)paramView.findViewById(2131427847)).setText(":" + str4);
      ((TextView)paramView.findViewById(2131427848)).setText(":" + str5);
      ((TextView)paramView.findViewById(2131427851)).setText(":" + str6);
      localCheckBox = (CheckBox)paramView.findViewById(2131427849);
      localCheckBox.setOnCheckedChangeListener(null);
      if (!"1".equals(localItemsBean.getIscheck()))
        break label367;
      localCheckBox.setChecked(true);
    }
    while (true)
    {
      CompoundButton.OnCheckedChangeListener local2 = new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          if (!PayAdapter.this.boo)
          {
            PayAdapter.this.boo = true;
            return;
          }
          int i;
          label86: String str3;
          String str4;
          label199: String str5;
          PayAdapter localPayAdapter5;
          if (paramAnonymousBoolean)
          {
            ((ItemsBean)PayAdapter.this.beanList.get(paramInt)).setIscheck("1");
            PayAdapter.this.priceAll = 0.0D;
            PayAdapter.this.commisionAll = 0.0D;
            if ((PayAdapter.this.beanList == null) || (PayAdapter.this.beanList.size() <= 0))
              break label565;
            i = 0;
            if (i < PayAdapter.this.beanList.size())
              break label423;
            PayAdapter.this.allMoney = (PayAdapter.this.priceAll + PayAdapter.this.commisionAll + PayAdapter.this.certFeeAll);
            str3 = String.valueOf(PayAdapter.this.priceAll);
            Object[] arrayOfObject1 = new Object[1];
            arrayOfObject1[0] = Double.valueOf(PayAdapter.this.commisionAll);
            str4 = String.format("%.2f", arrayOfObject1);
            if (!paramAnonymousBoolean)
              break label511;
            PayAdapter localPayAdapter6 = PayAdapter.this;
            localPayAdapter6.certFeeAll += Double.parseDouble(str6);
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = Double.valueOf(PayAdapter.this.certFeeAll);
            str5 = String.format("%.2f", arrayOfObject2);
            if (!paramAnonymousBoolean)
              break label538;
            localPayAdapter5 = PayAdapter.this;
          }
          label423: label511: PayAdapter localPayAdapter4;
          for (localPayAdapter5.allMoney += Double.parseDouble(str6); ; localPayAdapter4.allMoney -= Double.parseDouble(str6))
          {
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = Double.valueOf(PayAdapter.this.allMoney);
            String str6 = String.format("%.2f", arrayOfObject3);
            PayAdapter.this.pay_allMoney.setText("总款￥" + str6);
            PayAdapter.this.pay_mes.setText("共" + PayAdapter.this.beanList.size() + "件拍品" + "\n" + "\n" + "拍品" + str3 + "+佣金" + str4 + "+证书费" + str5 + "+快递费0");
            return;
            ((ItemsBean)PayAdapter.this.beanList.get(paramInt)).setIscheck("0");
            break;
            String str1 = ((ItemsBean)PayAdapter.this.beanList.get(i)).getPriceBid();
            String str2 = ((ItemsBean)PayAdapter.this.beanList.get(i)).getCommision();
            PayAdapter localPayAdapter1 = PayAdapter.this;
            localPayAdapter1.priceAll += Double.parseDouble(str1);
            PayAdapter localPayAdapter2 = PayAdapter.this;
            localPayAdapter2.commisionAll += Double.parseDouble(str2);
            i++;
            break label86;
            PayAdapter localPayAdapter3 = PayAdapter.this;
            localPayAdapter3.certFeeAll -= Double.parseDouble(str6);
            break label199;
            label538: localPayAdapter4 = PayAdapter.this;
          }
          label565: PayAdapter.this.pay_allMoney.setText("总款￥0");
          PayAdapter.this.pay_mes.setText("共0件拍品\n\n拍品0+佣金0+证书费0+快递费0");
        }
      };
      localCheckBox.setOnCheckedChangeListener(local2);
      TextView localTextView = (TextView)paramView.findViewById(2131427852);
      View.OnClickListener local3 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PayAdapter.this.beanList.remove(paramInt);
          PayAdapter.this.priceAll = 0.0D;
          PayAdapter.this.commisionAll = 0.0D;
          int i;
          if ((PayAdapter.this.beanList != null) && (PayAdapter.this.beanList.size() > 0))
          {
            i = 0;
            if (i >= PayAdapter.this.beanList.size())
            {
              Object[] arrayOfObject1 = new Object[1];
              arrayOfObject1[0] = Double.valueOf(PayAdapter.this.priceAll);
              String str3 = String.format("%.2f", arrayOfObject1);
              Object[] arrayOfObject2 = new Object[1];
              arrayOfObject2[0] = Double.valueOf(PayAdapter.this.commisionAll);
              String str4 = String.format("%.2f", arrayOfObject2);
              if (localCheckBox.isChecked())
              {
                PayAdapter localPayAdapter3 = PayAdapter.this;
                localPayAdapter3.certFeeAll -= Double.parseDouble(str6);
                PayAdapter.this.boo = false;
                localCheckBox.setChecked(false);
              }
              if (PayAdapter.this.certFeeAll != 0.0D)
              {
                Object[] arrayOfObject5 = new Object[1];
                arrayOfObject5[0] = Double.valueOf(PayAdapter.this.certFeeAll);
                String.format("%.2f", arrayOfObject5);
              }
              PayAdapter.this.allMoney = (PayAdapter.this.priceAll + PayAdapter.this.commisionAll + PayAdapter.this.certFeeAll);
              Object[] arrayOfObject3 = new Object[1];
              arrayOfObject3[0] = Double.valueOf(PayAdapter.this.certFeeAll);
              String str5 = String.format("%.2f", arrayOfObject3);
              Object[] arrayOfObject4 = new Object[1];
              arrayOfObject4[0] = Double.valueOf(PayAdapter.this.allMoney);
              String str6 = String.format("%.2f", arrayOfObject4);
              PayAdapter.this.pay_allMoney.setText("总款￥" + str6);
              PayAdapter.this.pay_mes.setText("共" + PayAdapter.this.beanList.size() + "件拍品" + "\n" + "\n" + "拍品" + str3 + "+佣金" + str4 + "+证书费" + str5 + "+快递费0");
            }
          }
          while (true)
          {
            PayAdapter.this.notifyDataSetChanged();
            return;
            String str1 = ((ItemsBean)PayAdapter.this.beanList.get(i)).getPriceBid();
            String str2 = ((ItemsBean)PayAdapter.this.beanList.get(i)).getCommision();
            PayAdapter localPayAdapter1 = PayAdapter.this;
            localPayAdapter1.priceAll += Double.parseDouble(str1);
            PayAdapter localPayAdapter2 = PayAdapter.this;
            localPayAdapter2.commisionAll += Double.parseDouble(str2);
            i++;
            break;
            PayAdapter.this.pay_allMoney.setText("总款￥0");
            PayAdapter.this.pay_mes.setText("共0件拍品\n\n拍品0+佣金0+证书费0+快递费0");
          }
        }
      };
      localTextView.setOnClickListener(local3);
      LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131427845);
      View.OnClickListener local4 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(PayAdapter.this.activity, AuctionActivity.class);
          PayAdapter.this.activity.startActivity(localIntent);
        }
      };
      localLinearLayout.setOnClickListener(local4);
      return paramView;
      ((DocItem)paramView.getTag());
      break;
      label367: localCheckBox.setChecked(false);
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
 * Qualified Name:     com.tg.jiadeonline.adapter.PayAdapter
 * JD-Core Version:    0.6.2
 */