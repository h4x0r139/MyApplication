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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.CategoriesPListActivity;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.utils.Utils;
import java.util.List;

public class CategoriesAdapter extends BaseAdapter
{
  private Activity activity;
  List<ChannelList1Bean> beanlist;
  private LayoutInflater inflater;

  public CategoriesAdapter(Context paramContext, Activity paramActivity, List<ChannelList1Bean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
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
    final ChannelList1Bean localChannelList1Bean = (ChannelList1Bean)this.beanlist.get(paramInt);
    localChannelList1Bean.getCatId();
    String str1 = localChannelList1Bean.getCatTit();
    localChannelList1Bean.getCatSubTit();
    localChannelList1Bean.getTimeStart();
    localChannelList1Bean.getTimeEnd();
    String str2 = localChannelList1Bean.getCatPicpath();
    localChannelList1Bean.getOrderNum();
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903131, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      TextView localTextView = (TextView)paramView.findViewById(2131427878);
      ImageView localImageView = (ImageView)paramView.findViewById(2131427879);
      localTextView.setText(str1);
      Utils.loadImage(str2, localImageView, this.activity);
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(CategoriesAdapter.this.activity, CategoriesPListActivity.class);
          Bundle localBundle = new Bundle();
          if ("S".equals(localChannelList1Bean.getIspar()))
            localBundle.putString("type", "S");
          localBundle.putSerializable("ChannelList1Bean", localChannelList1Bean);
          localIntent.putExtras(localBundle);
          CategoriesAdapter.this.activity.startActivity(localIntent);
        }
      };
      paramView.setOnClickListener(local1);
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
 * Qualified Name:     com.tg.jiadeonline.adapter.CategoriesAdapter
 * JD-Core Version:    0.6.2
 */