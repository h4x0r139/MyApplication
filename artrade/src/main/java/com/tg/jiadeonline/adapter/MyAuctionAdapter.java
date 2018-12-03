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
import android.widget.TextView;
import com.tg.jiadeonline.AuctionActivity;
import com.tg.jiadeonline.bean.myBiddingBean;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import java.util.List;

public class MyAuctionAdapter extends BaseAdapter
{
  private Activity activity;
  private int count;
  private LayoutInflater inflater;
  List<myBiddingBean> listdata;

  public MyAuctionAdapter(Context paramContext, Activity paramActivity, List<myBiddingBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.listdata = paramList;
    this.count = ((int)Math.ceil(paramList.size() / 2.0F));
  }

  public int getCount()
  {
    return (int)Math.ceil(this.listdata.size() / 2.0F);
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
    if (paramView == null)
      paramView = this.inflater.inflate(2130903125, null);
    LinearLayout localLinearLayout1 = (LinearLayout)paramView.findViewById(2131427827);
    ImageView localImageView1 = (ImageView)paramView.findViewById(2131427736);
    TextView localTextView1 = (TextView)paramView.findViewById(2131427484);
    TextView localTextView2 = (TextView)paramView.findViewById(2131427828);
    TextView localTextView3 = (TextView)paramView.findViewById(2131427829);
    final myBiddingBean localmyBiddingBean1 = (myBiddingBean)this.listdata.get(paramInt * 2);
    Utils.loadImage(localmyBiddingBean1.getPicPath(), localImageView1, this.activity);
    localTextView1.setText(localmyBiddingBean1.getTitle());
    localTextView2.setText(localmyBiddingBean1.getTimeEnd());
    localTextView3.setText(localmyBiddingBean1.getCurrentBid());
    localLinearLayout1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(MyAuctionAdapter.this.activity, AuctionActivity.class);
        localIntent.putExtra(CommonRef.itemnum, localmyBiddingBean1.getItemnum());
        MyAuctionAdapter.this.activity.startActivity(localIntent);
      }
    });
    LinearLayout localLinearLayout2 = (LinearLayout)paramView.findViewById(2131427830);
    if (2 + paramInt * 2 <= this.listdata.size())
    {
      localLinearLayout2.setVisibility(0);
      ImageView localImageView2 = (ImageView)paramView.findViewById(2131427743);
      TextView localTextView4 = (TextView)paramView.findViewById(2131427744);
      TextView localTextView5 = (TextView)paramView.findViewById(2131427831);
      TextView localTextView6 = (TextView)paramView.findViewById(2131427833);
      final myBiddingBean localmyBiddingBean2 = (myBiddingBean)this.listdata.get(1 + paramInt * 2);
      Utils.loadImage(localmyBiddingBean2.getPicPath(), localImageView2, this.activity);
      localTextView4.setText(localmyBiddingBean2.getTitle());
      localTextView5.setText(localmyBiddingBean2.getTimeEnd());
      localTextView6.setText(localmyBiddingBean2.getCurrentBid());
      localLinearLayout2.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(MyAuctionAdapter.this.activity, AuctionActivity.class);
          localIntent.putExtra(CommonRef.itemnum, localmyBiddingBean2.getItemnum());
          MyAuctionAdapter.this.activity.startActivity(localIntent);
        }
      });
      return paramView;
    }
    localLinearLayout2.setVisibility(4);
    localLinearLayout2.setOnClickListener(null);
    return paramView;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.adapter.MyAuctionAdapter
 * JD-Core Version:    0.6.2
 */