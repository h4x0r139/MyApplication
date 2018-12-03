package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.adapter.MyAuctionAdapter;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.myBiddingBean;
import com.tg.jiadeonline.date.GetmyBiddingManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.net.GetmyBidding;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Exit;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.List;

public class MyAuctionActivity extends Activity
  implements XListView.IXListViewListener
{
  private MyAuctionAdapter ListAdapter;
  private ImageView backImageView;
  private TextView back_title_paixu_paixu;
  private String cxlx = "leading";
  boolean isFirst = true;
  private TextView jpcj_line;
  private TextView jpcj_text;
  private TextView jplx_line;
  private TextView jplx_text;
  private List<myBiddingBean> listdata;
  private XListView listview;
  private Handler mHandler;
  private LinearLayout pay_success_tab;
  private int pnum = 1;
  private PopupWindow popright = null;
  private int pqty = 20;
  private TextView titlename;
  private LoginBean userbean;

  private void clean()
  {
  }

  private void onLoad()
  {
    this.listview.stopRefresh();
    this.listview.stopLoadMore();
    this.listview.setRefreshTime("刚刚");
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  public void init()
  {
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if ((localList != null) && (localList.size() != 0))
    {
      this.userbean = ((LoginBean)localList.get(0));
      GetmyBidding localGetmyBidding = new GetmyBidding(new JiaDeNetRequestTask.JiaDeNetCallback()
      {
        public void onCanceled()
        {
          WaitLoadDialog.getInstance().dismissDialog();
        }

        public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
        {
          WaitLoadDialog.getInstance().dismissDialog();
          Toast.makeText(MyAuctionActivity.this, paramAnonymousJiaDeNetException.getMessage(), 1).show();
        }

        public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
        {
          GetmyBiddingManager localGetmyBiddingManager = new GetmyBiddingManager(MyAuctionActivity.this);
          localGetmyBiddingManager.openDataBase();
          List localList = localGetmyBiddingManager.fetchAllDatas();
          localGetmyBiddingManager.deleteAllDatas();
          localGetmyBiddingManager.closeDataBase();
          if ((localList != null) && (localList.size() > 0))
          {
            MyAuctionActivity.this.listdata.addAll(localList);
            MyAuctionActivity localMyAuctionActivity = MyAuctionActivity.this;
            localMyAuctionActivity.pnum = (1 + localMyAuctionActivity.pnum);
            MyAuctionActivity.this.listview.setVisibility(0);
            MyAuctionActivity.this.showdata();
            if ((localList == null) || (localList.size() >= 20))
              break label187;
            MyAuctionActivity.this.listview.setPullLoadEnable(false);
            MyAuctionActivity.this.listview.setPullRefreshEnable(true);
          }
          while (true)
          {
            WaitLoadDialog.getInstance().dismissDialog();
            return;
            MyAuctionActivity.this.errormsg("未获取到数据");
            if (MyAuctionActivity.this.ListAdapter != null)
              MyAuctionActivity.this.ListAdapter.notifyDataSetChanged();
            MyAuctionActivity.this.listview.setPullLoadEnable(false);
            MyAuctionActivity.this.onLoad();
            break;
            label187: MyAuctionActivity.this.listview.setPullLoadEnable(true);
            MyAuctionActivity.this.listview.setPullRefreshEnable(true);
          }
        }
      }
      , this, Utils.getPhoneId(), this.userbean.getUserId(), this.cxlx, this.pnum, this.pqty);
      WaitLoadDialog.getInstance().showDialog(this, null, true);
      localGetmyBidding.execute(new JiaDeNetRequest[0]);
      return;
    }
    new AlertDialog.Builder(this).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MyAuctionActivity.this.finish();
        Intent localIntent = new Intent(MyAuctionActivity.this, LoginActivity.class);
        MyAuctionActivity.this.startActivity(localIntent);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MyAuctionActivity.this.finish();
      }
    }).show();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      int i = paramIntent.getIntExtra("ppid", -1);
      String str = paramIntent.getStringExtra("jiage");
      if ((this.listdata != null) && (this.listdata.size() >= i + 1) && (i != -1) && (!"".equals(str)))
      {
        ((myBiddingBean)this.listdata.get(i)).setCurrentBid(str);
        if (this.ListAdapter != null)
          this.ListAdapter.notifyDataSetChanged();
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903061);
    Exit.getInstance().addActivity(this);
    this.titlename = ((TextView)findViewById(2131427561));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.jpcj_text = ((TextView)findViewById(2131427426));
    this.jplx_text = ((TextView)findViewById(2131427424));
    this.jpcj_line = ((TextView)findViewById(2131427427));
    this.jplx_line = ((TextView)findViewById(2131427425));
    this.listview = ((XListView)findViewById(2131427428));
    this.pay_success_tab = ((LinearLayout)findViewById(2131427423));
    this.pay_success_tab.setVisibility(8);
    this.titlename.setText("我的竞拍");
    this.backImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MyAuctionActivity.this.finish();
      }
    });
    String str = getIntent().getExtras().getString("type1");
    if (str.equals("ling"))
    {
      this.titlename.setText("竞拍领先");
      this.jplx_text.setTextColor(getResources().getColor(2131034290));
      this.jplx_line.setBackgroundColor(getResources().getColor(2131034290));
      this.jpcj_text.setTextColor(getResources().getColor(2131034115));
      this.jpcj_line.setBackgroundColor(getResources().getColor(2131034114));
      this.cxlx = "leading";
    }
    while (true)
    {
      this.jpcj_text.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyAuctionActivity.this.jpcj_text.setTextColor(MyAuctionActivity.this.getResources().getColor(2131034290));
          MyAuctionActivity.this.jpcj_line.setBackgroundColor(MyAuctionActivity.this.getResources().getColor(2131034290));
          MyAuctionActivity.this.jplx_text.setTextColor(MyAuctionActivity.this.getResources().getColor(2131034115));
          MyAuctionActivity.this.jplx_line.setBackgroundColor(MyAuctionActivity.this.getResources().getColor(2131034114));
          MyAuctionActivity.this.cxlx = "outing";
          MyAuctionActivity.this.onRefresh();
        }
      });
      this.jplx_text.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MyAuctionActivity.this.jplx_text.setTextColor(MyAuctionActivity.this.getResources().getColor(2131034290));
          MyAuctionActivity.this.jplx_line.setBackgroundColor(MyAuctionActivity.this.getResources().getColor(2131034290));
          MyAuctionActivity.this.jpcj_text.setTextColor(MyAuctionActivity.this.getResources().getColor(2131034115));
          MyAuctionActivity.this.jpcj_line.setBackgroundColor(MyAuctionActivity.this.getResources().getColor(2131034114));
          MyAuctionActivity.this.cxlx = "leading";
          MyAuctionActivity.this.listview.setVisibility(8);
          MyAuctionActivity.this.onRefresh();
        }
      });
      this.listdata = new ArrayList();
      this.listview.setPullLoadEnable(true);
      this.listview.setPullRefreshEnable(true);
      this.mHandler = new Handler();
      init();
      return;
      if (str.equals("yi"))
      {
        this.titlename.setText("竞拍出局");
        this.jpcj_text.setTextColor(getResources().getColor(2131034290));
        this.jpcj_line.setBackgroundColor(getResources().getColor(2131034290));
        this.jplx_text.setTextColor(getResources().getColor(2131034115));
        this.jplx_line.setBackgroundColor(getResources().getColor(2131034114));
        this.cxlx = "outing";
      }
      else
      {
        this.titlename.setText("我的竞拍");
        this.pay_success_tab.setVisibility(0);
        this.cxlx = "leading";
      }
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
    clean();
  }

  public void onLoadMore()
  {
    init();
  }

  public void onRefresh()
  {
    this.pnum = 1;
    this.listdata.clear();
    init();
  }

  public void onResume()
  {
    super.onResume();
    if (this.isFirst)
      this.isFirst = false;
  }

  public void paixu()
  {
    this.back_title_paixu_paixu = ((TextView)findViewById(2131427566));
    View localView = LayoutInflater.from(this).inflate(2130903090, null);
    this.popright = new PopupWindow(localView, -2, -2, false);
    this.popright.setAnimationStyle(2131230726);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    this.back_title_paixu_paixu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyAuctionActivity.this.popright.isShowing())
        {
          MyAuctionActivity.this.popright.dismiss();
          return;
        }
        MyAuctionActivity.this.popright.showAsDropDown(paramAnonymousView);
      }
    });
    final TextView localTextView1 = (TextView)localView.findViewById(2131427572);
    final TextView localTextView2 = (TextView)localView.findViewById(2131427574);
    final TextView localTextView3 = (TextView)localView.findViewById(2131427576);
    final TextView localTextView4 = (TextView)localView.findViewById(2131427578);
    localTextView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyAuctionActivity.this.popright.isShowing())
          MyAuctionActivity.this.popright.dismiss();
        Toast.makeText(MyAuctionActivity.this, localTextView1.getText().toString(), 1).show();
      }
    });
    localTextView2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyAuctionActivity.this.popright.isShowing())
          MyAuctionActivity.this.popright.dismiss();
        Toast.makeText(MyAuctionActivity.this, localTextView2.getText().toString(), 1).show();
      }
    });
    localTextView3.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyAuctionActivity.this.popright.isShowing())
          MyAuctionActivity.this.popright.dismiss();
        Toast.makeText(MyAuctionActivity.this, localTextView3.getText().toString(), 1).show();
      }
    });
    localTextView4.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MyAuctionActivity.this.popright.isShowing())
          MyAuctionActivity.this.popright.dismiss();
        Toast.makeText(MyAuctionActivity.this, localTextView4.getText().toString(), 1).show();
      }
    });
  }

  public void showdata()
  {
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (MyAuctionActivity.this.ListAdapter != null)
          MyAuctionActivity.this.ListAdapter.notifyDataSetChanged();
        while (true)
        {
          MyAuctionActivity.this.onLoad();
          return;
          MyAuctionActivity.this.ListAdapter = new MyAuctionAdapter(MyAuctionActivity.this, MyAuctionActivity.this, MyAuctionActivity.this.listdata);
          MyAuctionActivity.this.listview.setAdapter(MyAuctionActivity.this.ListAdapter);
          MyAuctionActivity.this.listview.setXListViewListener(MyAuctionActivity.this);
        }
      }
    }
    , 0L);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.MyAuctionActivity
 * JD-Core Version:    0.6.2
 */