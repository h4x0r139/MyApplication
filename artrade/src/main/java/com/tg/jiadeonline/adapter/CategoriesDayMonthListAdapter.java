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
import com.tg.jiadeonline.bean.CategoryBean;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDayMonthListAdapter extends BaseAdapter
{
  private Activity activity;
  private List<CategoryBean> caBeanList = new ArrayList();
  private LayoutInflater inflater;

  public CategoriesDayMonthListAdapter(Context paramContext, Activity paramActivity, List<CategoryBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.caBeanList = paramList;
  }

  public void changeCount(int paramInt)
  {
  }

  public int getCount()
  {
    return this.caBeanList.size();
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
    CategoryBean localCategoryBean = (CategoryBean)this.caBeanList.get(paramInt);
    String str1 = localCategoryBean.getCatId();
    String str2 = localCategoryBean.getCatName();
    String str3 = localCategoryBean.getCpicpath();
    String str4 = localCategoryBean.getCdlSTime();
    String str5 = localCategoryBean.getCdlETime();
    localCategoryBean.getIsDisp();
    final ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
    localChannelList1Bean.setCatId(str1);
    localChannelList1Bean.setCatPicpath(str3);
    localChannelList1Bean.setCatSubTit(str2);
    localChannelList1Bean.setTimeEnd(str5);
    localChannelList1Bean.setTimeStart(str4);
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903103, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      Utils.loadImage(str3, (ImageView)paramView.findViewById(2131427663), this.activity);
      ((TextView)paramView.findViewById(2131427664)).setText(str2);
      ((TextView)paramView.findViewById(2131427665)).setText(str4);
      ((TextView)paramView.findViewById(2131427666)).setText(str5);
      ((TextView)paramView.findViewById(2131427377));
      View.OnClickListener local1 = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(CategoriesDayMonthListAdapter.this.activity, CategoriesPListActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("ChannelList1Bean", localChannelList1Bean);
          localIntent.putExtras(localBundle);
          CategoriesDayMonthListAdapter.this.activity.startActivity(localIntent);
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
 * Qualified Name:     com.tg.jiadeonline.adapter.CategoriesDayMonthListAdapter
 * JD-Core Version:    0.6.2
 */