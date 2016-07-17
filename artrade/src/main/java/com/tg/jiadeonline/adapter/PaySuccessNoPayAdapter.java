package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.AuctionActivity;
import com.tg.jiadeonline.bean.ItemsBean;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PaySuccessNoPayAdapter extends BaseAdapter
{
  private static HashMap<Integer, Boolean> isSelected;
  private Activity activity;
  List<ItemsBean> beanlist;
  private LayoutInflater inflater;
  private TextView pay_nosuc_num;

  public PaySuccessNoPayAdapter(Context paramContext, Activity paramActivity, List<ItemsBean> paramList, TextView paramTextView)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
    this.pay_nosuc_num = paramTextView;
    isSelected = new HashMap();
    initDate();
  }

  public static HashMap<Integer, Boolean> getIsSelected()
  {
    return isSelected;
  }

  private void initDate()
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.beanlist.size())
        return;
      getIsSelected().put(Integer.valueOf(i), Boolean.valueOf(false));
    }
  }

  public static void setIsSelected(HashMap<Integer, Boolean> paramHashMap)
  {
    isSelected = paramHashMap;
  }

  public int getCount()
  {
    return this.beanlist.size();
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
    ItemsBean localItemsBean = (ItemsBean)this.beanlist.get(paramInt);
    String str1 = localItemsBean.getPicPath();
    String str2 = localItemsBean.getTitle();
    localItemsBean.getCertFee();
    localItemsBean.getCommision();
    localItemsBean.getDateBid();
    final String str3 = localItemsBean.getItemnum();
    String str4 = localItemsBean.getPriceBid();
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903128, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      ImageView localImageView = (ImageView)paramView.findViewById(2131427854);
      Utils.loadImage(str1, localImageView, this.activity);
      ((CheckBox)paramView.findViewById(2131427853)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          int i;
          Iterator localIterator;
          if (((Boolean)PaySuccessNoPayAdapter.isSelected.get(Integer.valueOf(paramInt))).booleanValue())
          {
            PaySuccessNoPayAdapter.getIsSelected().put(Integer.valueOf(paramInt), Boolean.valueOf(false));
            i = 0;
            localIterator = PaySuccessNoPayAdapter.isSelected.keySet().iterator();
          }
          while (true)
          {
            if (!localIterator.hasNext())
            {
              PaySuccessNoPayAdapter.this.pay_nosuc_num.setText("选中" + i + "件");
              return;
              PaySuccessNoPayAdapter.getIsSelected().put(Integer.valueOf(paramInt), Boolean.valueOf(true));
              break;
            }
            Integer localInteger = (Integer)localIterator.next();
            if (((Boolean)PaySuccessNoPayAdapter.isSelected.get(localInteger)).booleanValue())
              i++;
          }
        }
      });
      localImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(PaySuccessNoPayAdapter.this.activity, AuctionActivity.class);
          localIntent.putExtra(CommonRef.itemnum, str3);
          localIntent.putExtra(CommonRef.carePers, "100");
          PaySuccessNoPayAdapter.this.activity.startActivity(localIntent);
        }
      });
      ((TextView)paramView.findViewById(2131427855)).setText(str2);
      ((TextView)paramView.findViewById(2131427856)).setText("成交价：￥" + str4);
      return paramView;
      ((DocItem)paramView.getTag());
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
 * Qualified Name:     com.tg.jiadeonline.adapter.PaySuccessNoPayAdapter
 * JD-Core Version:    0.6.2
 */