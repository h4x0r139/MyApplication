package com.tg.jiadeonline.activity;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.CategoriesPListActivity;
import com.tg.jiadeonline.CategoriesZtActivity;
import com.tg.jiadeonline.MessageListActivity;
import com.tg.jiadeonline.SearchResultActivity;
import com.tg.jiadeonline.adapter.ImageAdapter;
import com.tg.jiadeonline.base.BaseActivity;
import com.tg.jiadeonline.bean.ChannelList1Bean;
import com.tg.jiadeonline.bean.HomePageBean;
import com.tg.jiadeonline.bean.HomeTopPicBean;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.bean.LotsBean;
import com.tg.jiadeonline.date.HomePageManager;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.imagescrollview.ScrollImageHomeTop;
import com.tg.jiadeonline.layout.ScrollListView;
import com.tg.jiadeonline.layout.SlidingTextView;
import com.tg.jiadeonline.net.GetverInfo;
import com.tg.jiadeonline.net.HomePage;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Async;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity
{
  static Handler guanggaohandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      String str1 = i / 1000 / 60 / 60;
      String str2 = i / 1000 / 60 % 60;
      String str3 = i / 1000 % 60;
      if (str1.length() > 2)
      {
        str1 = str1.substring(0, 2);
        if (str2.length() <= 2)
          break label187;
        str2 = str2.substring(0, 2);
        label112: if (str3.length() <= 2)
          break label218;
        str3 = str3.substring(0, 2);
      }
      while (true)
      {
        HomeActivity.hour.setText(str1);
        HomeActivity.time_min.setText(str2);
        HomeActivity.time_sec.setText(str3);
        super.handleMessage(paramAnonymousMessage);
        return;
        if (str1.length() >= 2)
          break;
        str1 = "0" + str1;
        break;
        label187: if (str2.length() >= 2)
          break label112;
        str2 = "0" + str2;
        break label112;
        label218: if (str3.length() < 2)
          str3 = "0" + str3;
      }
    }
  };
  private static TextView hour;
  private static String systime;
  private static String timeStart;
  private static TextView time_min;
  private static TextView time_sec;
  private static String timeend;
  private Context context;
  private Async download;
  private LinearLayout home_lin_full;
  private LinearLayout home_sfjc_jishi;
  private LinearLayout home_title_msg_layout;
  private EditText home_title_query;
  private ImageView home_title_search;
  private HomePageBean hpBean = new HomePageBean();
  private String imei = "";
  private String info = "";
  final ArrayList<String> infoList = new ArrayList();
  private List<HomePageBean> listbean;
  private ImageView sfjc_iv_2;
  private LinearLayout sfjc_lin_1;
  private List<HomeTopPicBean> topPicList = new ArrayList();
  private String userId = "";

  private Dialog AlertDialogUtil(View paramView, Context paramContext, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString);
    localBuilder.setIcon(2130837566);
    localBuilder.setView(paramView);
    localBuilder.create();
    return localBuilder.show();
  }

  private static ActivityInfo getBrowserApp(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addCategory("android.intent.category.BROWSABLE");
    localIntent.setDataAndType(Uri.parse("http://"), null);
    List localList = paramContext.getPackageManager().queryIntentActivities(localIntent, 32);
    int i = localList.size();
    ActivityInfo localActivityInfo = null;
    if (i > 0)
      localActivityInfo = ((ResolveInfo)localList.get(0)).activityInfo;
    return localActivityInfo;
  }

  private static Intent getBrowserAppIntent(Context paramContext, String paramString)
  {
    ActivityInfo localActivityInfo = getBrowserApp(paramContext);
    if (localActivityInfo != null)
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      localIntent.setClassName(localActivityInfo.packageName, localActivityInfo.name);
      localIntent.setFlags(268435456);
      return localIntent;
    }
    return null;
  }

  private void initDATA()
  {
    HomePage localHomePage = new HomePage(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
        WaitLoadDialog.getInstance().dismissDialog();
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
        WaitLoadDialog.getInstance().dismissDialog();
        Utils.showDialog(HomeActivity.this, "提示", paramAnonymousJiaDeNetException.getLocalizedMessage(), 2130837514, null);
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        if ((paramAnonymousJiaDeNetResponse.result == null) || (!"ok".equals(paramAnonymousJiaDeNetResponse.result)))
        {
          Utils.showDialog(HomeActivity.this, "提示", paramAnonymousJiaDeNetResponse.result.toString(), 2130837514, null);
          return;
        }
        HomePageManager localHomePageManager = new HomePageManager(HomeActivity.this);
        localHomePageManager.openDataBase();
        HomeActivity.this.listbean = localHomePageManager.fetchAllDatas();
        localHomePageManager.closeDataBase();
        if ((HomeActivity.this.listbean != null) && (HomeActivity.this.listbean.size() > 0))
        {
          HomeActivity.this.showDATA();
          HomeActivity.this.initListener();
        }
        WaitLoadDialog.getInstance().dismissDialog();
      }
    }
    , this, this.imei, this.userId);
    WaitLoadDialog.getInstance().showDialog(this, null, true);
    localHomePage.execute(new JiaDeNetRequest[0]);
  }

  private void initID()
  {
    this.home_lin_full = ((LinearLayout)findViewById(2131427403));
    this.home_title_query = ((EditText)findViewById(2131427604));
    this.home_title_search = ((ImageView)findViewById(2131427603));
    this.home_title_msg_layout = ((LinearLayout)findViewById(2131427606));
  }

  private void initListener()
  {
    this.home_title_msg_layout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent();
        localIntent.putStringArrayListExtra("mes", HomeActivity.this.infoList);
        localIntent.setClass(HomeActivity.this, MessageListActivity.class);
        HomeActivity.this.startActivity(localIntent);
      }
    });
    this.home_title_query.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        EditText localEditText = (EditText)paramAnonymousView;
        if (!paramAnonymousBoolean)
        {
          localEditText.setHint(localEditText.getTag().toString());
          localEditText.clearFocus();
          return;
        }
        localEditText.setTag(localEditText.getHint().toString());
        localEditText.setHint("");
        localEditText.requestFocus();
      }
    });
    this.home_title_search.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        String str = HomeActivity.this.home_title_query.getText().toString();
        if ((str != null) && (!str.equals("")))
        {
          HomeActivity.this.home_title_query.clearFocus();
          HomeActivity.this.home_title_query.setText("");
          Intent localIntent = new Intent();
          localIntent.setClass(HomeActivity.this, SearchResultActivity.class);
          Bundle localBundle = new Bundle();
          localBundle.putString(CommonRef.querymsg, str);
          localIntent.putExtras(localBundle);
          HomeActivity.this.startActivity(localIntent);
          return;
        }
        Utils.alertDialog(HomeActivity.this, "请输入搜索关键词");
      }
    });
  }

  private void initVersion()
  {
    new GetverInfo(new JiaDeNetRequestTask.JiaDeNetCallback()
    {
      public void onCanceled()
      {
      }

      public void onException(JiaDeNetException paramAnonymousJiaDeNetException)
      {
      }

      public void onFinished(JiaDeNetResponse paramAnonymousJiaDeNetResponse)
      {
        String str1 = paramAnonymousJiaDeNetResponse.result.toString();
        String str2 = Utils.getVersion(HomeActivity.this);
        try
        {
          JSONObject localJSONObject = new JSONObject(str1);
          String str3 = localJSONObject.getString("auto_upd");
          String str4 = localJSONObject.getString("upd_url");
          String str5 = localJSONObject.getString("version");
          localJSONObject.getString("upd_log");
          if (!str5.equals(str2))
          {
            if ("".equals(str5))
              return;
            CommonRef.checkupdate = true;
            if (!"N".equals(str3))
            {
              HomeActivity.showNotification(HomeActivity.this.context, str4, str2);
              return;
            }
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
    , this, Utils.getPhoneId()).execute(new JiaDeNetRequest[0]);
  }

  private void showBann(HomePageBean paramHomePageBean)
  {
    View localView = LayoutInflater.from(this).inflate(2130903116, this.home_lin_full, false);
    final ScrollImageHomeTop localScrollImageHomeTop = (ScrollImageHomeTop)localView.findViewById(2131427770);
    try
    {
      JSONArray localJSONArray = new JSONArray(paramHomePageBean.getBann());
      if ((localJSONArray != null) && (localJSONArray.length() > 0))
      {
        int i = 0;
        if (i >= localJSONArray.length())
        {
          localScrollImageHomeTop.setHomeBitmapList(this.topPicList, null, this);
          localScrollImageHomeTop.start(3000);
          localScrollImageHomeTop.setClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeTopPicBean localHomeTopPicBean = (HomeTopPicBean)HomeActivity.this.topPicList.get(localScrollImageHomeTop.getPosition());
              ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
              localChannelList1Bean.setCatId(localHomeTopPicBean.getCatId());
              localChannelList1Bean.setTimeEnd(localHomeTopPicBean.getTimeEnd());
              localChannelList1Bean.setTimeStart(localHomeTopPicBean.getTimeStart());
              localChannelList1Bean.setCatPicpath(localHomeTopPicBean.getCatPicpath());
              localChannelList1Bean.setCatTit(localHomeTopPicBean.getCatTit());
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, localHomeTopPicBean.getIspar());
            }
          });
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        HomeTopPicBean localHomeTopPicBean = new HomeTopPicBean();
        String str1;
        label138: String str2;
        label160: String str3;
        label182: String str4;
        label204: String str5;
        label226: String str6;
        label248: String str7;
        if (localJSONObject.getString("catDesc") == null)
        {
          str1 = "";
          localHomeTopPicBean.setCatDesc(str1);
          if (localJSONObject.getString("catId") != null)
            break label349;
          str2 = "";
          localHomeTopPicBean.setCatId(str2);
          if (localJSONObject.getString("catPicpath") != null)
            break label362;
          str3 = "";
          localHomeTopPicBean.setCatPicpath(str3);
          if (localJSONObject.getString("catSubTit") != null)
            break label375;
          str4 = "";
          localHomeTopPicBean.setCatSubTit(str4);
          if (localJSONObject.getString("catTit") != null)
            break label388;
          str5 = "";
          localHomeTopPicBean.setCatTit(str5);
          if (localJSONObject.getString("ispar") != null)
            break label401;
          str6 = "";
          localHomeTopPicBean.setIspar(str6);
          localHomeTopPicBean.setOrderid(Integer.valueOf(localJSONObject.getString("orderid")).intValue());
          if (localJSONObject.getString("timeEnd") != null)
            break label414;
          str7 = "";
          label289: localHomeTopPicBean.setTimeEnd(str7);
          if (localJSONObject.getString("timeStart") != null)
            break label427;
        }
        label388: label401: label414: label427: for (String str8 = ""; ; str8 = localJSONObject.getString("timeStart"))
        {
          localHomeTopPicBean.setTimeStart(str8);
          this.topPicList.add(localHomeTopPicBean);
          i++;
          break;
          str1 = localJSONObject.getString("catDesc");
          break label138;
          label349: str2 = localJSONObject.getString("catId");
          break label160;
          label362: str3 = localJSONObject.getString("catPicpath");
          break label182;
          label375: str4 = localJSONObject.getString("catSubTit");
          break label204;
          str5 = localJSONObject.getString("catTit");
          break label226;
          str6 = localJSONObject.getString("ispar");
          break label248;
          str7 = localJSONObject.getString("timeEnd");
          break label289;
        }
      }
      localScrollImageHomeTop.setVisibility(8);
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }

  private void showCat1(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      int i;
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        View localView = LayoutInflater.from(this).inflate(2130903117, null);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        final ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
        localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
        localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
        localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
        localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
        localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
        final String str2 = localJSONObject.getString("ispar");
        if (i == 0)
        {
          ImageView localImageView1 = (ImageView)localView.findViewById(2131427772);
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView1, this);
          localImageView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
        else if (i == 1)
        {
          TextView localTextView1 = (TextView)localView.findViewById(2131427774);
          TextView localTextView2 = (TextView)localView.findViewById(2131427775);
          ImageView localImageView2 = (ImageView)localView.findViewById(2131427776);
          localTextView1.setText(localChannelList1Bean.getCatTit());
          localTextView2.setText(localChannelList1Bean.getCatSubTit());
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
          ((LinearLayout)localView.findViewById(2131427773)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
      return;
      i++;
    }
  }

  private void showCat2(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      View localView;
      int i;
      final ChannelList1Bean localChannelList1Bean;
      final String str2;
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        localView = LayoutInflater.from(this).inflate(2130903114, null);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
        localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
        localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
        localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
        localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
        localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
        str2 = localJSONObject.getString("ispar");
        if (i == 0)
        {
          ImageView localImageView1 = (ImageView)localView.findViewById(2131427760);
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView1, this);
          localImageView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
        else if (i == 1)
        {
          TextView localTextView1 = (TextView)localView.findViewById(2131427762);
          TextView localTextView2 = (TextView)localView.findViewById(2131427763);
          ImageView localImageView2 = (ImageView)localView.findViewById(2131427764);
          localTextView1.setText(localChannelList1Bean.getCatTit());
          localTextView2.setText(localChannelList1Bean.getCatSubTit());
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
          ((LinearLayout)localView.findViewById(2131427761)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      if (i == 2)
      {
        TextView localTextView3 = (TextView)localView.findViewById(2131427766);
        TextView localTextView4 = (TextView)localView.findViewById(2131427767);
        ImageView localImageView3 = (ImageView)localView.findViewById(2131427768);
        localTextView3.setText(localChannelList1Bean.getCatTit());
        localTextView4.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView3, this);
        ((LinearLayout)localView.findViewById(2131427765)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
        break label441;
        return;
      }
      label441: i++;
    }
  }

  private void showCat4(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      View localView;
      int i;
      final ChannelList1Bean localChannelList1Bean;
      final String str2;
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        localView = LayoutInflater.from(this).inflate(2130903120, null);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
        localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
        localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
        localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
        localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
        localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
        str2 = localJSONObject.getString("ispar");
        if (i == 0)
        {
          ImageView localImageView1 = (ImageView)localView.findViewById(2131427804);
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView1, this);
          localImageView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
        else if (i == 1)
        {
          TextView localTextView1 = (TextView)localView.findViewById(2131427806);
          TextView localTextView2 = (TextView)localView.findViewById(2131427807);
          ImageView localImageView2 = (ImageView)localView.findViewById(2131427808);
          localTextView1.setText(localChannelList1Bean.getCatTit());
          localTextView2.setText(localChannelList1Bean.getCatSubTit());
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
          ((LinearLayout)localView.findViewById(2131427805)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      if (i == 2)
      {
        TextView localTextView7 = (TextView)localView.findViewById(2131427810);
        TextView localTextView8 = (TextView)localView.findViewById(2131427811);
        ImageView localImageView5 = (ImageView)localView.findViewById(2131427812);
        localTextView7.setText(localChannelList1Bean.getCatTit());
        localTextView8.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView5, this);
        ((LinearLayout)localView.findViewById(2131427809)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 3)
      {
        TextView localTextView3 = (TextView)localView.findViewById(2131427814);
        TextView localTextView4 = (TextView)localView.findViewById(2131427815);
        ImageView localImageView3 = (ImageView)localView.findViewById(2131427816);
        localTextView3.setText(localChannelList1Bean.getCatTit());
        localTextView4.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView3, this);
        ((LinearLayout)localView.findViewById(2131427813)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 4)
      {
        TextView localTextView5 = (TextView)localView.findViewById(2131427818);
        TextView localTextView6 = (TextView)localView.findViewById(2131427819);
        ImageView localImageView4 = (ImageView)localView.findViewById(2131427820);
        localTextView5.setText(localChannelList1Bean.getCatTit());
        localTextView6.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView4, this);
        ((LinearLayout)localView.findViewById(2131427817)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
        break label651;
        return;
      }
      label651: i++;
    }
  }

  private void showCat5(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      View localView;
      int i;
      final ChannelList1Bean localChannelList1Bean;
      final String str2;
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        localView = LayoutInflater.from(this).inflate(2130903106, null);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
        localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
        localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
        localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
        localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
        localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
        str2 = localJSONObject.getString("ispar");
        if (i == 0)
        {
          ImageView localImageView1 = (ImageView)localView.findViewById(2131427674);
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView1, this);
          localImageView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
        else if (i == 1)
        {
          TextView localTextView1 = (TextView)localView.findViewById(2131427676);
          TextView localTextView2 = (TextView)localView.findViewById(2131427677);
          ImageView localImageView2 = (ImageView)localView.findViewById(2131427678);
          localTextView1.setText(localChannelList1Bean.getCatTit());
          localTextView2.setText(localChannelList1Bean.getCatSubTit());
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
          ((LinearLayout)localView.findViewById(2131427675)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      if (i == 2)
      {
        TextView localTextView9 = (TextView)localView.findViewById(2131427680);
        TextView localTextView10 = (TextView)localView.findViewById(2131427681);
        ImageView localImageView6 = (ImageView)localView.findViewById(2131427682);
        localTextView9.setText(localChannelList1Bean.getCatTit());
        localTextView10.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView6, this);
        ((LinearLayout)localView.findViewById(2131427679)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 3)
      {
        TextView localTextView3 = (TextView)localView.findViewById(2131427684);
        TextView localTextView4 = (TextView)localView.findViewById(2131427685);
        ImageView localImageView3 = (ImageView)localView.findViewById(2131427686);
        localTextView3.setText(localChannelList1Bean.getCatTit());
        localTextView4.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView3, this);
        ((LinearLayout)localView.findViewById(2131427683)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 4)
      {
        TextView localTextView5 = (TextView)localView.findViewById(2131427688);
        TextView localTextView6 = (TextView)localView.findViewById(2131427689);
        ImageView localImageView4 = (ImageView)localView.findViewById(2131427690);
        localTextView5.setText(localChannelList1Bean.getCatTit());
        localTextView6.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView4, this);
        ((LinearLayout)localView.findViewById(2131427687)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 5)
      {
        TextView localTextView7 = (TextView)localView.findViewById(2131427692);
        TextView localTextView8 = (TextView)localView.findViewById(2131427693);
        ImageView localImageView5 = (ImageView)localView.findViewById(2131427694);
        localTextView7.setText(localChannelList1Bean.getCatTit());
        localTextView8.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView5, this);
        ((LinearLayout)localView.findViewById(2131427691)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
        break label756;
        return;
      }
      label756: i++;
    }
  }

  private void showCat6(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      View localView;
      int i;
      final ChannelList1Bean localChannelList1Bean;
      final String str2;
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        localView = LayoutInflater.from(this).inflate(2130903119, null);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
        localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
        localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
        localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
        localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
        localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
        str2 = localJSONObject.getString("ispar");
        if (i == 0)
        {
          ImageView localImageView1 = (ImageView)localView.findViewById(2131427779);
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView1, this);
          localImageView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
        else if (i == 1)
        {
          TextView localTextView1 = (TextView)localView.findViewById(2131427781);
          TextView localTextView2 = (TextView)localView.findViewById(2131427782);
          ImageView localImageView2 = (ImageView)localView.findViewById(2131427783);
          localTextView1.setText(localChannelList1Bean.getCatTit());
          localTextView2.setText(localChannelList1Bean.getCatSubTit());
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
          ((LinearLayout)localView.findViewById(2131427780)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      if (i == 2)
      {
        TextView localTextView11 = (TextView)localView.findViewById(2131427785);
        TextView localTextView12 = (TextView)localView.findViewById(2131427786);
        ImageView localImageView7 = (ImageView)localView.findViewById(2131427787);
        localTextView11.setText(localChannelList1Bean.getCatTit());
        localTextView12.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView7, this);
        ((LinearLayout)localView.findViewById(2131427784)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 3)
      {
        TextView localTextView3 = (TextView)localView.findViewById(2131427789);
        TextView localTextView4 = (TextView)localView.findViewById(2131427790);
        ImageView localImageView3 = (ImageView)localView.findViewById(2131427791);
        localTextView3.setText(localChannelList1Bean.getCatTit());
        localTextView4.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView3, this);
        ((LinearLayout)localView.findViewById(2131427788)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 4)
      {
        TextView localTextView5 = (TextView)localView.findViewById(2131427793);
        TextView localTextView6 = (TextView)localView.findViewById(2131427794);
        ImageView localImageView4 = (ImageView)localView.findViewById(2131427795);
        localTextView5.setText(localChannelList1Bean.getCatTit());
        localTextView6.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView4, this);
        ((LinearLayout)localView.findViewById(2131427792)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 5)
      {
        TextView localTextView7 = (TextView)localView.findViewById(2131427797);
        TextView localTextView8 = (TextView)localView.findViewById(2131427798);
        ImageView localImageView5 = (ImageView)localView.findViewById(2131427799);
        localTextView7.setText(localChannelList1Bean.getCatTit());
        localTextView8.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView5, this);
        ((LinearLayout)localView.findViewById(2131427796)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 6)
      {
        TextView localTextView9 = (TextView)localView.findViewById(2131427801);
        TextView localTextView10 = (TextView)localView.findViewById(2131427802);
        ImageView localImageView6 = (ImageView)localView.findViewById(2131427803);
        localTextView9.setText(localChannelList1Bean.getCatTit());
        localTextView10.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView6, this);
        ((LinearLayout)localView.findViewById(2131427800)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
        break label862;
        return;
      }
      label862: i++;
    }
  }

  private void showDATA()
  {
    int i = 0;
    if (i >= this.listbean.size())
      return;
    HomePageBean localHomePageBean = (HomePageBean)this.listbean.get(i);
    String str = localHomePageBean.getBannnum();
    if ("Bann".equals(str))
      showBann(localHomePageBean);
    while (true)
    {
      i++;
      break;
      if ("Info".equals(str))
        showInfo(localHomePageBean);
      else if ("FCol".equals(str))
        showFCol(localHomePageBean);
      else if ("Cat1".equals(str))
        showCat1(localHomePageBean);
      else if ("Cat2".equals(str))
        showCat2(localHomePageBean);
      else if ("Cat4".equals(str))
        showCat4(localHomePageBean);
      else if ("Cat5".equals(str))
        showCat5(localHomePageBean);
      else if ("Cat6".equals(str))
        showCat6(localHomePageBean);
      else if ("Prim".equals(str))
        showPrim(localHomePageBean);
      else if ("Recm".equals(str))
        showRecm(localHomePageBean);
      else if ("Lots".equals(str))
        showLots(localHomePageBean);
      else if ("Vwnd".equals(str))
        showVwnd(localHomePageBean);
    }
  }

  private void showFCol(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      View localView;
      int i;
      final ChannelList1Bean localChannelList1Bean;
      final String str2;
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        localView = LayoutInflater.from(this).inflate(2130903109, this.home_lin_full, false);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localChannelList1Bean = new ChannelList1Bean();
        localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
        localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
        localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
        localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
        localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
        localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
        str2 = localJSONObject.getString("ispar");
        if (i == 0)
        {
          ImageView localImageView1 = (ImageView)localView.findViewById(2131427723);
          Utils.loadImagechang(localChannelList1Bean.getCatPicpath(), localImageView1, this);
          localImageView1.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
        else if (i == 1)
        {
          TextView localTextView1 = (TextView)localView.findViewById(2131427725);
          TextView localTextView2 = (TextView)localView.findViewById(2131427726);
          ImageView localImageView2 = (ImageView)localView.findViewById(2131427727);
          localTextView1.setText(localChannelList1Bean.getCatTit());
          localTextView2.setText(localChannelList1Bean.getCatSubTit());
          Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
          ((LinearLayout)localView.findViewById(2131427724)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
            }
          });
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      if (i == 2)
      {
        TextView localTextView5 = (TextView)localView.findViewById(2131427729);
        TextView localTextView6 = (TextView)localView.findViewById(2131427730);
        ImageView localImageView4 = (ImageView)localView.findViewById(2131427731);
        localTextView5.setText(localChannelList1Bean.getCatTit());
        localTextView6.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView4, this);
        ((LinearLayout)localView.findViewById(2131427728)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 3)
      {
        TextView localTextView3 = (TextView)localView.findViewById(2131427733);
        TextView localTextView4 = (TextView)localView.findViewById(2131427734);
        ImageView localImageView3 = (ImageView)localView.findViewById(2131427735);
        localTextView3.setText(localChannelList1Bean.getCatTit());
        localTextView4.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView3, this);
        ((LinearLayout)localView.findViewById(2131427732)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
        break label550;
        return;
      }
      label550: i++;
    }
  }

  private void showInfo(HomePageBean paramHomePageBean)
  {
    View localView = LayoutInflater.from(this).inflate(2130903118, this.home_lin_full, false);
    SlidingTextView localSlidingTextView = (SlidingTextView)localView.findViewById(2131427777);
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      try
      {
        JSONArray localJSONArray = new JSONArray(paramHomePageBean.getBann());
        HashMap localHashMap = new HashMap();
        String[] arrayOfString = new String[localJSONArray.length()];
        int i = 0;
        Iterator localIterator;
        if (i >= localJSONArray.length())
          localIterator = localHashMap.keySet().iterator();
        while (true)
        {
          if (!localIterator.hasNext())
          {
            CommonRef.msgmap.clear();
            CommonRef.msgmap = localHashMap;
            localSlidingTextView.setShowText(arrayOfString);
            View.OnClickListener local41 = new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                Intent localIntent = new Intent();
                localIntent.putStringArrayListExtra("mes", HomeActivity.this.infoList);
                localIntent.setClass(HomeActivity.this, MessageListActivity.class);
                HomeActivity.this.startActivity(localIntent);
              }
            };
            localSlidingTextView.setOnClickListener(local41);
            this.home_lin_full.addView(localView);
            return;
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            String str1 = localJSONObject.getString("msgTit");
            Integer.valueOf(localJSONObject.getString("orderid")).intValue();
            localHashMap.put(localJSONObject.getString("msgId"), str1);
            this.infoList.add(str1);
            arrayOfString[i] = str1;
            i++;
            break;
          }
          String str2 = (String)localIterator.next();
          StringBuilder localStringBuilder = new StringBuilder(String.valueOf(this.info));
          this.info = ((String)localHashMap.get(str2) + "      ");
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
  }

  private void showLots(HomePageBean paramHomePageBean)
  {
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
    {
      String str1 = paramHomePageBean.getBann();
      try
      {
        JSONArray localJSONArray = new JSONArray(str1);
        ArrayList localArrayList = new ArrayList();
        View localView = LayoutInflater.from(this).inflate(2130903111, null);
        ScrollListView localScrollListView = (ScrollListView)localView.findViewById(2131427752);
        for (int i = 0; ; i++)
        {
          if (i >= localJSONArray.length())
          {
            ImageAdapter localImageAdapter = new ImageAdapter(this, this, localArrayList);
            localScrollListView.setAdapter(localImageAdapter);
            this.home_lin_full.addView(localView);
            return;
          }
          JSONObject localJSONObject = localJSONArray.getJSONObject(i);
          LotsBean localLotsBean = new LotsBean();
          String str2 = localJSONObject.getString("iPicpath");
          int j = Integer.valueOf(localJSONObject.getString("orderid")).intValue();
          String str3 = localJSONObject.getString("curBid");
          String str4 = localJSONObject.getString("carePer");
          String str5 = localJSONObject.getString("title");
          String str6 = localJSONObject.getString("timeEnd");
          String str7 = localJSONObject.getString("itemnum");
          localLotsBean.setiPicpath(str2);
          localLotsBean.setOrderid(j);
          localLotsBean.setCurBid(str3);
          localLotsBean.setCarePer(str4);
          localLotsBean.setTitle(str5);
          localLotsBean.setTimeEnd(str6);
          localLotsBean.setItemnum(str7);
          localArrayList.add(localLotsBean);
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }

  public static void showNotification(Context paramContext, String paramString1, String paramString2)
  {
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Notification localNotification = new Notification();
    localNotification.audioStreamType = -1;
    localNotification.flags = (0x10 | localNotification.flags);
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramContext, 0, getBrowserAppIntent(paramContext, paramString1), 268435456);
    localNotification.tickerText = "嘉德在线提示您";
    localNotification.icon = 2130837515;
    localNotification.setLatestEventInfo(paramContext, paramContext.getString(2131165184), paramString2, localPendingIntent);
    localNotificationManager.notify(999, localNotification);
  }

  private void showPrim(HomePageBean paramHomePageBean)
  {
    String str1;
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
      str1 = paramHomePageBean.getBann();
    while (true)
    {
      JSONArray localJSONArray;
      View localView;
      int i;
      try
      {
        localJSONArray = new JSONArray(str1);
        if (localJSONArray == null)
          break label882;
        if (localJSONArray.length() <= 0)
          return;
        localView = LayoutInflater.from(this).inflate(2130903108, null);
        i = 0;
        if (i >= localJSONArray.length())
        {
          this.home_lin_full.addView(localView);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return;
      }
      JSONObject localJSONObject = localJSONArray.getJSONObject(i);
      final ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
      localChannelList1Bean.setCatId(localJSONObject.getString("catId"));
      localChannelList1Bean.setCatPicpath(localJSONObject.getString("catPicpath"));
      localChannelList1Bean.setTimeEnd(localJSONObject.getString("timeEnd"));
      localChannelList1Bean.setTimeStart(localJSONObject.getString("timeStart"));
      localChannelList1Bean.setCatTit(localJSONObject.getString("catTit"));
      localChannelList1Bean.setCatSubTit(localJSONObject.getString("catSubTit"));
      final String str2 = localJSONObject.getString("ispar");
      if (i == 0)
      {
        ImageView localImageView1 = (ImageView)localView.findViewById(2131427697);
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView1, this);
        ((LinearLayout)localView.findViewById(2131427696)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 1)
      {
        TextView localTextView1 = (TextView)localView.findViewById(2131427699);
        TextView localTextView2 = (TextView)localView.findViewById(2131427700);
        ImageView localImageView2 = (ImageView)localView.findViewById(2131427701);
        localTextView1.setText(localChannelList1Bean.getCatTit());
        localTextView2.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView2, this);
        ((LinearLayout)localView.findViewById(2131427698)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 2)
      {
        TextView localTextView3 = (TextView)localView.findViewById(2131427703);
        TextView localTextView4 = (TextView)localView.findViewById(2131427704);
        ImageView localImageView3 = (ImageView)localView.findViewById(2131427705);
        localTextView3.setText(localChannelList1Bean.getCatTit());
        localTextView4.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView3, this);
        ((LinearLayout)localView.findViewById(2131427702)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 3)
      {
        TextView localTextView5 = (TextView)localView.findViewById(2131427707);
        TextView localTextView6 = (TextView)localView.findViewById(2131427708);
        ImageView localImageView4 = (ImageView)localView.findViewById(2131427709);
        localTextView5.setText(localChannelList1Bean.getCatTit());
        localTextView6.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView4, this);
        ((LinearLayout)localView.findViewById(2131427706)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 4)
      {
        TextView localTextView7 = (TextView)localView.findViewById(2131427711);
        TextView localTextView8 = (TextView)localView.findViewById(2131427712);
        ImageView localImageView5 = (ImageView)localView.findViewById(2131427713);
        localTextView7.setText(localChannelList1Bean.getCatTit());
        localTextView8.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView5, this);
        ((LinearLayout)localView.findViewById(2131427710)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 5)
      {
        TextView localTextView9 = (TextView)localView.findViewById(2131427715);
        TextView localTextView10 = (TextView)localView.findViewById(2131427716);
        ImageView localImageView6 = (ImageView)localView.findViewById(2131427717);
        localTextView9.setText(localChannelList1Bean.getCatTit());
        localTextView10.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView6, this);
        ((LinearLayout)localView.findViewById(2131427714)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
      }
      else if (i == 6)
      {
        TextView localTextView11 = (TextView)localView.findViewById(2131427719);
        TextView localTextView12 = (TextView)localView.findViewById(2131427720);
        ImageView localImageView7 = (ImageView)localView.findViewById(2131427721);
        localTextView11.setText(localChannelList1Bean.getCatTit());
        localTextView12.setText(localChannelList1Bean.getCatSubTit());
        Utils.loadImage(localChannelList1Bean.getCatPicpath(), localImageView7, this);
        ((LinearLayout)localView.findViewById(2131427718)).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str2);
          }
        });
        break label883;
        label882: return;
      }
      label883: i++;
    }
  }

  private void showRecm(HomePageBean paramHomePageBean)
  {
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
    {
      String str1 = paramHomePageBean.getBann();
      while (true)
      {
        JSONArray localJSONArray;
        LinearLayout localLinearLayout;
        int i;
        try
        {
          localJSONArray = new JSONArray(str1);
          if (localJSONArray == null)
            break;
          if (localJSONArray.length() <= 0)
            return;
          View localView1 = LayoutInflater.from(this).inflate(2130903115, null);
          localLinearLayout = (LinearLayout)localView1.findViewById(2131427769);
          i = 0;
          if (i >= (int)Math.ceil(localJSONArray.length() / 2.0F))
          {
            this.home_lin_full.addView(localView1);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        int j = i * 2;
        JSONObject localJSONObject1 = localJSONArray.getJSONObject(j);
        final ChannelList1Bean localChannelList1Bean1 = new ChannelList1Bean();
        localChannelList1Bean1.setCatId(localJSONObject1.getString("catId"));
        localChannelList1Bean1.setCatPicpath(localJSONObject1.getString("catPicpath"));
        localChannelList1Bean1.setTimeEnd(localJSONObject1.getString("timeEnd"));
        localChannelList1Bean1.setTimeStart(localJSONObject1.getString("timeStart"));
        localChannelList1Bean1.setCatTit(localJSONObject1.getString("catTit"));
        localChannelList1Bean1.setCatSubTit(localJSONObject1.getString("catSubTit"));
        final String str2 = localJSONObject1.getString("ispar");
        View localView2 = LayoutInflater.from(this).inflate(2130903121, null);
        ImageView localImageView1 = (ImageView)localView2.findViewById(2131427821);
        TextView localTextView1 = (TextView)localView2.findViewById(2131427822);
        Utils.loadImage(localChannelList1Bean1.getCatPicpath(), localImageView1, this);
        localTextView1.setText(localChannelList1Bean1.getCatTit());
        View.OnClickListener local5 = new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean1, str2);
          }
        };
        localImageView1.setOnClickListener(local5);
        if (2 + i * 2 <= localJSONArray.length())
        {
          JSONObject localJSONObject2 = localJSONArray.getJSONObject(1 + i * 2);
          final ChannelList1Bean localChannelList1Bean2 = new ChannelList1Bean();
          localChannelList1Bean2.setCatId(localJSONObject2.getString("catId"));
          localChannelList1Bean2.setCatPicpath(localJSONObject2.getString("catPicpath"));
          localChannelList1Bean2.setTimeEnd(localJSONObject2.getString("timeEnd"));
          localChannelList1Bean2.setTimeStart(localJSONObject2.getString("timeStart"));
          localChannelList1Bean2.setCatTit(localJSONObject2.getString("catTit"));
          localChannelList1Bean2.setCatSubTit(localJSONObject2.getString("catSubTit"));
          final String str3 = localJSONObject2.getString("ispar");
          ImageView localImageView2 = (ImageView)localView2.findViewById(2131427823);
          TextView localTextView2 = (TextView)localView2.findViewById(2131427824);
          Utils.loadImage(localChannelList1Bean2.getCatPicpath(), localImageView2, this);
          localTextView2.setText(localChannelList1Bean2.getCatTit());
          View.OnClickListener local6 = new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean2, str3);
            }
          };
          localImageView2.setOnClickListener(local6);
        }
        localLinearLayout.addView(localView2);
        i++;
      }
    }
  }

  private void showVwnd(HomePageBean paramHomePageBean)
  {
    if ((paramHomePageBean.getBann() != null) && (!"".equals(paramHomePageBean.getBann())))
    {
      String str1 = paramHomePageBean.getBann();
      while (true)
      {
        long l1;
        long l3;
        try
        {
          systime = paramHomePageBean.getSysTime();
          if ((str1 == null) || ("".equals(str1)) || (!Utils.isValidDate(systime)))
            break;
          JSONArray localJSONArray = new JSONArray(str1);
          if (localJSONArray == null)
            break;
          if (localJSONArray.length() <= 0)
            return;
          View localView1 = LayoutInflater.from(this).inflate(2130903113, null);
          hour = (TextView)localView1.findViewById(2131427755);
          time_min = (TextView)localView1.findViewById(2131427756);
          time_sec = (TextView)localView1.findViewById(2131427757);
          ImageView localImageView = (ImageView)localView1.findViewById(2131427758);
          this.home_sfjc_jishi = ((LinearLayout)localView1.findViewById(2131427754));
          JSONObject localJSONObject = localJSONArray.getJSONObject(0);
          timeend = localJSONObject.getString("timeEnd");
          String str2 = localJSONObject.getString("catPicpath");
          Integer.valueOf(localJSONObject.getString("orderid")).intValue();
          String str3 = localJSONObject.getString("catTit");
          final String str4 = localJSONObject.getString("ispar");
          timeStart = localJSONObject.getString("timeStart");
          localJSONObject.getString("catDesc");
          String str5 = localJSONObject.getString("catSubTit");
          String str6 = localJSONObject.getString("catId");
          final ChannelList1Bean localChannelList1Bean = new ChannelList1Bean();
          localChannelList1Bean.setCatId(str6);
          localChannelList1Bean.setCatPicpath(str2);
          localChannelList1Bean.setCatSubTit(str5);
          localChannelList1Bean.setTimeEnd(timeend);
          localChannelList1Bean.setTimeStart(timeStart);
          localChannelList1Bean.setCatTit(str3);
          Utils.loadImage(str2, localImageView, this);
          if (("".equals(timeend)) || ("".equals(systime)))
            break label573;
          l1 = Long.valueOf(timeend.replace("-", "").replace(":", "").replace(" ", "")).longValue();
          long l2 = Long.valueOf(timeStart.replace("-", "").replace(":", "").replace(" ", "")).longValue();
          l3 = Long.valueOf(systime.replace("-", "").replace(":", "").replace(" ", "")).longValue();
          if (l2 > l3)
          {
            setAutoGuangGao();
            View.OnClickListener local4 = new View.OnClickListener()
            {
              public void onClick(View paramAnonymousView)
              {
                HomeActivity.this.toCategorieDayMonthList(localChannelList1Bean, str4);
              }
            };
            localView1.setOnClickListener(local4);
            this.home_lin_full.addView(localView1);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          return;
        }
        if (l3 < l1)
        {
          this.home_sfjc_jishi.removeAllViews();
          View localView4 = LayoutInflater.from(this).inflate(2130903136, null);
          this.home_sfjc_jishi.addView(localView4);
        }
        else
        {
          this.home_sfjc_jishi.removeAllViews();
          View localView3 = LayoutInflater.from(this).inflate(2130903136, null);
          this.home_sfjc_jishi.addView(localView3);
          continue;
          label573: this.home_sfjc_jishi.removeAllViews();
          View localView2 = LayoutInflater.from(this).inflate(2130903136, null);
          this.home_sfjc_jishi.addView(localView2);
        }
      }
    }
  }

  public void errormsg(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected int getViewId()
  {
    return 2130903057;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView();
    createNavMenu();
    this.context = this;
    this.imei = Utils.getPhoneId();
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(this);
    localRegisterOrLoginManager.openDataBase();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if (localList != null)
      this.userId = ((LoginBean)localList.get(0)).getUserId();
    initID();
    initDATA();
    initVersion();
  }

  protected void onDestroy()
  {
    this.home_title_query = null;
    this.home_title_search = null;
    this.home_title_msg_layout = null;
    systime = null;
    timeend = null;
    this.hpBean = null;
    this.home_lin_full = null;
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void setAutoGuangGao()
  {
    if (!CommonRef.fast)
      return;
    new Thread(new Runnable()
    {
      public void run()
      {
        CommonRef.fast = false;
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while (true)
        {
          long l;
          try
          {
            Date localDate1 = localSimpleDateFormat.parse(HomeActivity.timeStart);
            Date localDate2 = localSimpleDateFormat.parse(HomeActivity.systime);
            l = localDate1.getTime() - localDate2.getTime();
            if (l > 1000L)
              break label179;
            HomeActivity.this.home_sfjc_jishi.removeAllViews();
            View localView1 = LayoutInflater.from(HomeActivity.this).inflate(2130903136, null);
            HomeActivity.this.home_sfjc_jishi.addView(localView1);
            return;
            if (i != 0)
            {
              HomeActivity.guanggaohandler.sendEmptyMessage((int)l);
              try
              {
                Thread.sleep(1000L);
                if (l >= 1000L)
                  break label185;
                HomeActivity.this.home_sfjc_jishi.removeAllViews();
                View localView2 = LayoutInflater.from(HomeActivity.this).inflate(2130903136, null);
                HomeActivity.this.home_sfjc_jishi.addView(localView2);
                i = 0;
              }
              catch (InterruptedException localInterruptedException)
              {
                localInterruptedException.printStackTrace();
                continue;
              }
            }
          }
          catch (ParseException localParseException)
          {
            localParseException.printStackTrace();
          }
          return;
          label179: int i = 1;
          continue;
          label185: l -= 1000L;
        }
      }
    }).start();
  }

  public void setOnfocus(final EditText paramEditText, final String paramString)
  {
    paramEditText.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          paramEditText.setHint("");
          return;
        }
        paramEditText.setHint(paramString);
      }
    });
  }

  public void toCategorieDayMonthList(ChannelList1Bean paramChannelList1Bean, String paramString)
  {
    if ((paramChannelList1Bean.getCatId() == null) || ("".equals(paramChannelList1Bean.getCatId())) || ("null".equals(paramChannelList1Bean.getCatId())))
      errormsg("无对应的此专题");
    String str;
    do
    {
      return;
      str = paramChannelList1Bean.getCatId();
    }
    while (("0".equals(str)) || ("".equals(str)) || ("null".equals(str)));
    Intent localIntent = new Intent();
    Bundle localBundle = new Bundle();
    localBundle.putSerializable("ChannelList1Bean", paramChannelList1Bean);
    if ("C".equals(paramString))
      localIntent.setClass(this, CategoriesPListActivity.class);
    while (true)
    {
      localIntent.putExtras(localBundle);
      startActivity(localIntent);
      return;
      if ("P".equals(paramString))
      {
        localIntent.setClass(this, CategoriesZtActivity.class);
      }
      else if ("S".equals(paramString))
      {
        localIntent.setClass(this, CategoriesPListActivity.class);
        localBundle.putString("type", "S");
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.HomeActivity
 * JD-Core Version:    0.6.2
 */