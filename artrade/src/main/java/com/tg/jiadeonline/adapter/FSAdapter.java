package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.bean.UserFsBean;
import java.util.List;

public class FSAdapter extends BaseAdapter
{
  private Activity activity;
  List<UserFsBean> beanlist;
  private LayoutInflater inflater;
  private String useFs;

  public FSAdapter(Context paramContext, Activity paramActivity, List<UserFsBean> paramList, String paramString)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.beanlist = paramList;
    this.useFs = paramString;
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
    UserFsBean localUserFsBean = (UserFsBean)this.beanlist.get(paramInt);
    String str1 = localUserFsBean.getForSaleId();
    String str2 = localUserFsBean.getUserFSId();
    String str3 = localUserFsBean.getUserFSFee();
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903105, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      ((LinearLayout)paramView.findViewById(2131427667));
      ((TextView)paramView.findViewById(2131427670)).setText("促销售活动编号：" + str1);
      ((TextView)paramView.findViewById(2131427671)).setText("优惠券编号：" + str2);
      ((TextView)paramView.findViewById(2131427672)).setText("优惠券金额：" + str3);
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
 * Qualified Name:     com.tg.jiadeonline.adapter.FSAdapter
 * JD-Core Version:    0.6.2
 */