package com.tg.jiadeonline.adapter;

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
import com.tg.jiadeonline.bean.SearchItemsBean;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import java.util.List;

public class SearchResultAdapter extends BaseAdapter
{
  private Activity activity;
  List<SearchItemsBean> beanlist;
  private LayoutInflater inflater;

  public SearchResultAdapter(Context paramContext, Activity paramActivity, List<SearchItemsBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
  }

  public int getCount()
  {
    return (int)Math.ceil(this.beanlist.size() / 2.0F);
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    SearchItemsBean localSearchItemsBean1 = (SearchItemsBean)this.beanlist.get(paramInt * 2);
    String str1 = localSearchItemsBean1.getPicture();
    String str2 = localSearchItemsBean1.getTitle();
    String str3 = localSearchItemsBean1.getTimeEnd();
    final String str4 = localSearchItemsBean1.getItemnum();
    final String str5 = localSearchItemsBean1.getCarePers();
    localSearchItemsBean1.getMinimumBid();
    String str6 = localSearchItemsBean1.getCurrentBid();
    localSearchItemsBean1.getTotal();
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903125, null);
      paramView.setTag(localDocItem);
    }
    LinearLayout localLinearLayout2;
    while (true)
    {
      LinearLayout localLinearLayout1 = (LinearLayout)paramView.findViewById(2131427827);
      Utils.loadImage(str1, (ImageView)paramView.findViewById(2131427736), this.activity);
      ((TextView)paramView.findViewById(2131427484)).setText(str2);
      ((TextView)paramView.findViewById(2131427828)).setText("起拍日期:" + str3);
      ((TextView)paramView.findViewById(2131427829)).setText(str6);
      ((TextView)paramView.findViewById(2131427742)).setText(str5 + "人围观");
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.putExtra(CommonRef.itemnum, str4);
          localIntent.putExtra(CommonRef.carePers, str5);
          localIntent.setClass(SearchResultAdapter.this.activity, AuctionActivity.class);
          SearchResultAdapter.this.activity.startActivity(localIntent);
        }
      };
      localLinearLayout1.setOnClickListener(local1);
      localLinearLayout2 = (LinearLayout)paramView.findViewById(2131427830);
      if (this.beanlist.size() <= 1 + paramInt * 2)
        break;
      SearchItemsBean localSearchItemsBean2 = (SearchItemsBean)this.beanlist.get(1 + paramInt * 2);
      String str7 = localSearchItemsBean2.getPicture();
      String str8 = localSearchItemsBean2.getTitle();
      String str9 = localSearchItemsBean2.getTimeEnd();
      final String str10 = localSearchItemsBean2.getItemnum();
      final String str11 = localSearchItemsBean2.getCarePers();
      localSearchItemsBean2.getMinimumBid();
      String str12 = localSearchItemsBean2.getCurrentBid();
      localSearchItemsBean2.getTotal();
      Utils.loadImage(str7, (ImageView)paramView.findViewById(2131427743), this.activity);
      ((TextView)paramView.findViewById(2131427744)).setText(str8);
      ((TextView)paramView.findViewById(2131427833)).setText(str12);
      ((TextView)paramView.findViewById(2131427831)).setText("起拍日期:" + str9);
      ((TextView)paramView.findViewById(2131427750)).setText(str11 + "人围观");
      View.OnClickListener local2 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.putExtra(CommonRef.itemnum, str10);
          localIntent.putExtra(CommonRef.carePers, str11);
          localIntent.setClass(SearchResultAdapter.this.activity, AuctionActivity.class);
          SearchResultAdapter.this.activity.startActivity(localIntent);
        }
      };
      localLinearLayout2.setOnClickListener(local2);
      return paramView;
      ((DocItem)paramView.getTag());
    }
    localLinearLayout2.setOnClickListener(null);
    localLinearLayout2.setVisibility(4);
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
 * Qualified Name:     com.tg.jiadeonline.adapter.SearchResultAdapter
 * JD-Core Version:    0.6.2
 */