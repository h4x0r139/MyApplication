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
import com.tg.jiadeonline.bean.UserAccBean;
import java.util.List;

public class UserAcctAdapter extends BaseAdapter
{
  private Activity activity;
  List<UserAccBean> beanlist;
  private LayoutInflater inflater;

  public UserAcctAdapter(Context paramContext, Activity paramActivity, List<UserAccBean> paramList)
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
    UserAccBean localUserAccBean = (UserAccBean)this.beanlist.get(paramInt);
    String str1 = localUserAccBean.getAmount();
    String str2 = localUserAccBean.getPayTime();
    String str3 = localUserAccBean.getOp_type();
    TextView localTextView;
    if (paramView == null)
    {
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903101, null);
      paramView.setTag(localDocItem);
      ((TextView)paramView.findViewById(2131427660)).setText("金额：" + str1);
      ((TextView)paramView.findViewById(2131427661)).setText("支付时间：" + str2);
      localTextView = (TextView)paramView.findViewById(2131427662);
      if (!"I".equals(str3))
        break label185;
    }
    label185: for (String str4 = "在线支付"; ; str4 = "公司柜台交易")
    {
      localTextView.setText("支付方式：" + str4);
      return paramView;
      ((DocItem)paramView.getTag());
      break;
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
 * Qualified Name:     com.tg.jiadeonline.adapter.UserAcctAdapter
 * JD-Core Version:    0.6.2
 */