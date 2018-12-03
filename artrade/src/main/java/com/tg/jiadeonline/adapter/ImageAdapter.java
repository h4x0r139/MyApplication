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
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.AuctionActivity;
import com.tg.jiadeonline.bean.LotsBean;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import java.util.List;

public class ImageAdapter extends BaseAdapter
{
  private Activity activity;
  List<LotsBean> beanlist;
  private LayoutInflater inflater;

  public ImageAdapter(Context paramContext, Activity paramActivity, List<LotsBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
  }

  public int getCount()
  {
    return this.beanlist.size() / 2;
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
    LotsBean localLotsBean1 = (LotsBean)this.beanlist.get(paramInt * 2);
    String str1 = localLotsBean1.getiPicpath();
    String str2 = localLotsBean1.getTitle();
    String str3 = localLotsBean1.getTimeEnd();
    String str4 = localLotsBean1.getCurBid();
    String str5 = localLotsBean1.getCarePer();
    final String str6 = localLotsBean1.getItemnum();
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903110, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      ImageView localImageView1 = (ImageView)paramView.findViewById(2131427736);
      Utils.loadImage(str1, localImageView1, this.activity);
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(ImageAdapter.this.activity, AuctionActivity.class);
          localIntent.putExtra(CommonRef.itemnum, str6);
          ImageAdapter.this.activity.startActivity(localIntent);
        }
      };
      localImageView1.setOnClickListener(local1);
      ((TextView)paramView.findViewById(2131427484)).setText(str2);
      ((TextView)paramView.findViewById(2131427738)).setText(str3);
      ((TextView)paramView.findViewById(2131427741)).setText(str4);
      ((TextView)paramView.findViewById(2131427742)).setText(str5 + "人围观");
      if (this.beanlist.size() <= 1 + paramInt * 2)
        break;
      LotsBean localLotsBean2 = (LotsBean)this.beanlist.get(1 + paramInt * 2);
      String str7 = localLotsBean2.getiPicpath();
      String str8 = localLotsBean2.getTitle();
      String str9 = localLotsBean2.getTimeEnd();
      String str10 = localLotsBean2.getCurBid();
      String str11 = localLotsBean2.getCarePer();
      final String str12 = localLotsBean2.getItemnum();
      ImageView localImageView2 = (ImageView)paramView.findViewById(2131427743);
      Utils.loadImage(str7, localImageView2, this.activity);
      View.OnClickListener local2 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent(ImageAdapter.this.activity, AuctionActivity.class);
          localIntent.putExtra(CommonRef.itemnum, str12);
          ImageAdapter.this.activity.startActivity(localIntent);
        }
      };
      localImageView2.setOnClickListener(local2);
      ((TextView)paramView.findViewById(2131427744)).setText(str8);
      ((TextView)paramView.findViewById(2131427747)).setText(str9);
      ((TextView)paramView.findViewById(2131427749)).setText(":" + str10);
      ((TextView)paramView.findViewById(2131427750)).setText(str11 + "人围观");
      return paramView;
      ((DocItem)paramView.getTag());
    }
    ((ImageView)paramView.findViewById(2131427743)).setVisibility(4);
    ((TextView)paramView.findViewById(2131427744)).setText("");
    ((TextView)paramView.findViewById(2131427747)).setText("");
    ((TextView)paramView.findViewById(2131427749)).setText("");
    ((TextView)paramView.findViewById(2131427740)).setVisibility(4);
    ((TextView)paramView.findViewById(2131427750)).setText("");
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
 * Qualified Name:     com.tg.jiadeonline.adapter.ImageAdapter
 * JD-Core Version:    0.6.2
 */