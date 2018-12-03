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
import android.widget.TextView;
import com.tg.jiadeonline.CategoriesPListActivity;
import com.tg.jiadeonline.bean.CategoryBean;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.utils.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CategoriexZtAdapter extends BaseAdapter
{
  private Activity activity;
  private LayoutInflater inflater;
  private List<CategoryBean> listbean;

  public CategoriexZtAdapter(Context paramContext, Activity paramActivity, List<CategoryBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.listbean = paramList;
  }

  private boolean getistime(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date localDate1 = localSimpleDateFormat.parse(paramString1);
      Date localDate2 = localSimpleDateFormat.parse(paramString2);
      long l1 = localDate1.getTime();
      long l2 = localDate2.getTime();
      if (l1 > l2)
        return true;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return false;
  }

  public int getCount()
  {
    return this.listbean.size();
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
    DocItem localDocItem;
    String str1;
    String str2;
    String str3;
    String str4;
    String str5;
    if (paramView == null)
    {
      localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903132, null);
      localDocItem.categories_type = ((TextView)paramView.findViewById(2131427377));
      localDocItem.comtypePic = ((ImageView)paramView.findViewById(2131427880));
      localDocItem.zzc_name = ((TextView)paramView.findViewById(2131427881));
      localDocItem.zzc_starttime = ((TextView)paramView.findViewById(2131427882));
      localDocItem.zzc_endtime = ((TextView)paramView.findViewById(2131427883));
      paramView.setTag(localDocItem);
      CategoryBean localCategoryBean = (CategoryBean)this.listbean.get(paramInt);
      str1 = localCategoryBean.getCatId();
      str2 = localCategoryBean.getCatName();
      str3 = localCategoryBean.getCdlETime();
      str4 = localCategoryBean.getCdlSTime();
      str5 = localCategoryBean.getCpicpath();
      String str6 = localCategoryBean.getSysTime();
      localDocItem.zzc_endtime.setText("结束时间:" + str3);
      localDocItem.zzc_name.setText(str2);
      localDocItem.zzc_starttime.setText("开始时间:" + str4);
      Utils.loadImage(str5, localDocItem.comtypePic, this.activity);
      if (!getistime(str3, str6))
        break label387;
      if (!getistime(str6, str4))
        break label355;
      localDocItem.categories_type.setText("进行中");
      localDocItem.categories_type.setTextColor(-1);
      localDocItem.categories_type.setBackgroundResource(2130837619);
    }
    while (true)
    {
      final ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
      localChannelList1Bean.setCatId(str1);
      localChannelList1Bean.setCatPicpath(str5);
      localChannelList1Bean.setCatTit(str2);
      localChannelList1Bean.setTimeEnd(str3);
      localChannelList1Bean.setTimeStart(str4);
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent();
          localIntent.setClass(CategoriexZtAdapter.this.activity, CategoriesPListActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putSerializable("ChannelList1Bean", localChannelList1Bean);
          localIntent.putExtras(localBundle);
          CategoriexZtAdapter.this.activity.startActivity(localIntent);
        }
      });
      return paramView;
      localDocItem = (DocItem)paramView.getTag();
      break;
      label355: localDocItem.categories_type.setText("尚未开始");
      localDocItem.categories_type.setTextColor(-1);
      localDocItem.categories_type.setBackgroundResource(2130837606);
      continue;
      label387: localDocItem.categories_type.setText("已结束");
      localDocItem.categories_type.setTextColor(-1);
      localDocItem.categories_type.setBackgroundResource(2130837612);
    }
  }

  public final class DocItem
  {
    TextView categories_type;
    ImageView comtypePic;
    TextView zzc_endtime;
    TextView zzc_name;
    TextView zzc_starttime;

    public DocItem()
    {
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.adapter.CategoriexZtAdapter
 * JD-Core Version:    0.6.2
 */