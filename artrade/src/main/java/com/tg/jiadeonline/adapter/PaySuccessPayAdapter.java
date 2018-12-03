package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.PaySuccessActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.PaySucItemBean;
import com.tg.jiadeonline.bean.PaymentOrderBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.net.DelOrder;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaySuccessPayAdapter extends BaseAdapter
{
  private Activity activity;
  private Dialog alertDialog;
  private Context context;
  private String imei = "";
  private LayoutInflater inflater;
  private List<PaymentOrderBean> paymentOrderBeanList;
  private String userId = "";
  private XListView xListView;

  public PaySuccessPayAdapter(Context paramContext, final Activity paramActivity, List<PaymentOrderBean> paramList)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.context = paramContext;
    this.activity = paramActivity;
    this.paymentOrderBeanList = paramList;
    this.imei = Utils.getPhoneId();
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(paramActivity);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
    {
      this.userId = ((LoginBean)localList.get(0)).getUserId();
      return;
    }
    new AlertDialog.Builder(paramActivity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramActivity.finish();
        Intent localIntent = new Intent(paramActivity, LoginActivity.class);
        paramActivity.startActivity(localIntent);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramActivity.finish();
      }
    }).show();
  }

  public int getCount()
  {
    return this.paymentOrderBeanList.size();
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
      paramView = this.inflater.inflate(2130903129, null);
      paramView.setTag(localDocItem);
    }
    while (true)
    {
      PaymentOrderBean localPaymentOrderBean = (PaymentOrderBean)this.paymentOrderBeanList.get(paramInt);
      String str1 = localPaymentOrderBean.getItemData();
      ArrayList localArrayList = new ArrayList();
      String str2 = localPaymentOrderBean.getTotalAamount();
      final String str3 = localPaymentOrderBean.getOrderId();
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        int i = 0;
        label90: PaySuccessItemAdapter localPaySuccessItemAdapter;
        int j;
        int k;
        label150: LinearLayout localLinearLayout;
        if (i >= localJSONArray.length())
        {
          this.xListView = ((XListView)paramView.findViewById(2131427862));
          localPaySuccessItemAdapter = new PaySuccessItemAdapter(this.context, this.activity, localArrayList, localPaymentOrderBean, 2);
          this.xListView.setAdapter(localPaySuccessItemAdapter);
          j = 0;
          k = 0;
          if (k < localPaySuccessItemAdapter.getCount())
            break label641;
          ViewGroup.LayoutParams localLayoutParams = this.xListView.getLayoutParams();
          localLayoutParams.height = (j + this.xListView.getDividerHeight() * (-1 + this.xListView.getCount()));
          this.xListView.setLayoutParams(localLayoutParams);
          localLinearLayout = (LinearLayout)paramView.findViewById(2131427863);
          if (!"".equals(localPaymentOrderBean.getInvoice()))
            break label678;
          localLinearLayout.setVisibility(8);
        }
        while (true)
        {
          ((TextView)paramView.findViewById(2131427865)).setText("共" + localArrayList.size() + "件|总金额：" + str2 + "元");
          TextView localTextView1 = (TextView)paramView.findViewById(2131427860);
          TextView localTextView2 = (TextView)paramView.findViewById(2131427858);
          String str13 = localPaymentOrderBean.getOrderId();
          localTextView2.setText("订单编号：" + str13);
          ((LinearLayout)paramView.findViewById(2131427867)).setVisibility(8);
          ((TextView)paramView.findViewById(2131427866)).setText("佣金：" + localPaymentOrderBean.getTotalCommission() + "元|快递费" + localPaymentOrderBean.getTransportFee() + "元");
          localTextView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
            }
          });
          ((TextView)paramView.findViewById(2131427860)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              new AlertDialog.Builder(PaySuccessPayAdapter.this.activity).setTitle("提示").setMessage("确认删除订单?").setPositiveButton("确定", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  DelOrder localDelOrder = new DelOrder(new JiaDeNetRequestTask.JiaDeNetCallback()
                  {
                    public void onCanceled()
                    {
                      WaitLoadDialog.getInstance().dismissDialog();
                    }

                    public void onException(JiaDeNetException paramAnonymous3JiaDeNetException)
                    {
                      WaitLoadDialog.getInstance().dismissDialog();
                      Utils.showDialog(PaySuccessPayAdapter.this.activity, "提示", paramAnonymous3JiaDeNetException.getLocalizedMessage(), 2130837514, null);
                    }

                    public void onFinished(JiaDeNetResponse paramAnonymous3JiaDeNetResponse)
                    {
                      WaitLoadDialog.getInstance().dismissDialog();
                      String str = paramAnonymous3JiaDeNetResponse.result.toString();
                      if ((!"".equals(str)) && (!"ok".equals(str)))
                      {
                        Utils.showDialog(PaySuccessPayAdapter.this.activity, "提示", str, 2130837514, null);
                        return;
                      }
                      Intent localIntent = new Intent(PaySuccessPayAdapter.this.activity, PaySuccessActivity.class);
                      localIntent.putExtra("ordernum", "2");
                      PaySuccessPayAdapter.this.activity.startActivity(localIntent);
                      PaySuccessPayAdapter.this.activity.finish();
                    }
                  }
                  , PaySuccessPayAdapter.this.activity, PaySuccessPayAdapter.this.imei, PaySuccessPayAdapter.this.userId, this.val$orderId);
                  WaitLoadDialog.getInstance().showDialog(PaySuccessPayAdapter.this.activity, null, true);
                  localDelOrder.execute(new JiaDeNetRequest[0]);
                }
              }).setNegativeButton("返回", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                }
              }).show();
            }
          });
          return paramView;
          ((DocItem)paramView.getTag());
          break;
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          PaySucItemBean localPaySucItemBean = new PaySucItemBean();
          String str4 = localJSONObject.getString("title");
          String str5 = localJSONObject.getString("itemnum");
          String str6 = localJSONObject.getString("picPath");
          String str7 = localJSONObject.getString("priceBid");
          String str8 = localJSONObject.getString("commision");
          String str9 = localJSONObject.getString("cerFee");
          String str10 = localJSONObject.getString("isNeedCer");
          String str11 = localJSONObject.getString("transportID");
          String str12 = localJSONObject.getString("carrier");
          localPaySucItemBean.setTitle(str4);
          localPaySucItemBean.setItemnum(str5);
          localPaySucItemBean.setPicPath(str6);
          localPaySucItemBean.setPriceBid(str7);
          localPaySucItemBean.setCommision(str8);
          localPaySucItemBean.setCertFee(str9);
          localPaySucItemBean.setIsNeedCer(str10);
          localPaySucItemBean.setTransportID(str11);
          localPaySucItemBean.setCarrier(str12);
          localArrayList.add(localPaySucItemBean);
          i++;
          break label90;
          label641: View localView = localPaySuccessItemAdapter.getView(k, null, this.xListView);
          localView.measure(0, 0);
          j += localView.getMeasuredHeight();
          k++;
          break label150;
          label678: localLinearLayout.setVisibility(0);
          ((TextView)paramView.findViewById(2131427864)).setText(localPaymentOrderBean.getInvoice());
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
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
 * Qualified Name:     com.tg.jiadeonline.adapter.PaySuccessPayAdapter
 * JD-Core Version:    0.6.2
 */