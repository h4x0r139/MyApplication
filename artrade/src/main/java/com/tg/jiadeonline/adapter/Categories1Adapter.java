package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import java.util.List;

public class Categories1Adapter extends BaseAdapter
{
  private Activity activity;
  List<ChannelList1Bean> beanlist;
  private LayoutInflater inflater;

  public Categories1Adapter(Context paramContext, Activity paramActivity, List<ChannelList1Bean> paramList)
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
    ((ChannelList1Bean)this.beanlist.get(paramInt));
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903099, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      ((TextView)paramView.findViewById(2131427484));
      ((TextView)paramView.findViewById(2131427656));
      ((TextView)paramView.findViewById(2131427657));
      ((TextView)paramView.findViewById(2131427658));
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
 * Qualified Name:     com.tg.jiadeonline.adapter.Categories1Adapter
 * JD-Core Version:    0.6.2
 */