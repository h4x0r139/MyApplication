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

public class QueryResultAdapter extends BaseAdapter
{
  private Activity activity;
  private int count;
  private LayoutInflater inflater;

  public QueryResultAdapter(Context paramContext, Activity paramActivity, int paramInt)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.count = paramInt;
  }

  public int getCount()
  {
    return this.count;
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
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903110, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(QueryResultAdapter.this.activity, AuctionActivity.class);
          QueryResultAdapter.this.activity.startActivity(localIntent);
        }
      });
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
 * Qualified Name:     com.tg.jiadeonline.adapter.QueryResultAdapter
 * JD-Core Version:    0.6.2
 */