package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.bean.AuctionPriceBean;
import com.tg.jiadeonline.bean.AuctiondetailsBean;
import java.util.List;

public class AuctionPriceAdapter extends BaseAdapter
{
  private Activity activity;
  private List<AuctionPriceBean> beanlist;
  private LayoutInflater inflater;
  private AuctiondetailsBean quanjuBean;

  public AuctionPriceAdapter(Context paramContext, Activity paramActivity, List<AuctionPriceBean> paramList, AuctiondetailsBean paramAuctiondetailsBean)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
    this.quanjuBean = paramAuctiondetailsBean;
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

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    AuctionPriceBean localAuctionPriceBean = (AuctionPriceBean)this.beanlist.get(paramInt);
    localAuctionPriceBean.getBiddingId();
    String str1 = localAuctionPriceBean.getBiddingPrice();
    String str2 = localAuctionPriceBean.getBiddingTime();
    String str3 = localAuctionPriceBean.getNickname();
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903100, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      ((WebView)paramView.findViewById(2131427659)).loadDataWithBaseURL(null, "用户： " + str3 + "<br>" + "竞买： " + str1 + "<br>时间： " + str2, "text/html", "utf-8", null);
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
 * Qualified Name:     com.tg.jiadeonline.adapter.AuctionPriceAdapter
 * JD-Core Version:    0.6.2
 */