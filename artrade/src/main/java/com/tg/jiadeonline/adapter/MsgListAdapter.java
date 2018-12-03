package com.tg.jiadeonline.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import com.tg.jiadeonline.net.GethomeMsgContent;
import com.tg.jiadeonline.net.JiaDeNetException;
import com.tg.jiadeonline.net.JiaDeNetRequest;
import com.tg.jiadeonline.net.JiaDeNetRequestTask.JiaDeNetCallback;
import com.tg.jiadeonline.net.JiaDeNetResponse;
import com.tg.jiadeonline.utils.Utils;
import com.tg.jiadeonline.utils.WaitLoadDialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MsgListAdapter extends BaseAdapter
{
  private Activity activity;
  private List<CheckBox> checkBoxList;
  private Context context;
  private int count;
  private String id = "";
  private LayoutInflater inflater;
  List<Map<String, String>> infoList = new ArrayList();
  private boolean ischeck = true;
  private PopupWindow popright = null;
  private boolean showDel;
  private TextView titleName;

  public MsgListAdapter(Context paramContext, Activity paramActivity, List<Map<String, String>> paramList, boolean paramBoolean)
  {
    this.inflater = LayoutInflater.from(paramContext);
    this.activity = paramActivity;
    this.context = paramContext;
    this.showDel = paramBoolean;
    this.checkBoxList = new ArrayList();
    this.infoList = paramList;
  }

  public void changeCount(int paramInt)
  {
    this.count = (paramInt + this.count);
  }

  public void changetext(TextView paramTextView)
  {
    this.titleName = paramTextView;
  }

  public List<CheckBox> getCheckBoxList()
  {
    return this.checkBoxList;
  }

  public int getCount()
  {
    return this.infoList.size();
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
    final Map localMap = (Map)this.infoList.get(paramInt);
    String str1 = "";
    Iterator localIterator = localMap.keySet().iterator();
    label74: CheckBox localCheckBox;
    if (!localIterator.hasNext())
    {
      if (paramView != null)
        break label175;
      DocItem localDocItem = new DocItem();
      paramView = this.inflater.inflate(2130903123, null);
      paramView.setTag(localDocItem);
      localCheckBox = (CheckBox)paramView.findViewById(2131427669);
      this.checkBoxList.add(localCheckBox);
      if (!this.showDel)
        break label186;
      localCheckBox.setVisibility(0);
    }
    while (true)
    {
      ((TextView)paramView.findViewById(2131427825)).setText(str1);
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          String str1;
          Iterator localIterator;
          if (MsgListAdapter.this.ischeck)
          {
            MsgListAdapter.this.ischeck = false;
            str1 = "";
            localIterator = localMap.keySet().iterator();
          }
          while (true)
          {
            if (!localIterator.hasNext())
            {
              RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(MsgListAdapter.this.activity);
              localRegisterOrLoginManager.openDataBase();
              List localList = localRegisterOrLoginManager.fetchAllDatas();
              localRegisterOrLoginManager.closeDataBase();
              String str2 = "";
              if (localList != null)
                str2 = ((LoginBean)localList.get(0)).getUserId();
              new GethomeMsgContent(new JiaDeNetRequestTask.JiaDeNetCallback()
              {
                public void onCanceled()
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                }

                public void onException(JiaDeNetException paramAnonymous2JiaDeNetException)
                {
                  WaitLoadDialog.getInstance().dismissDialog();
                  Utils.showDialog(MsgListAdapter.this.activity, "提示", paramAnonymous2JiaDeNetException.getLocalizedMessage(), 2130837514, null);
                }

                public void onFinished(JiaDeNetResponse paramAnonymous2JiaDeNetResponse)
                {
                  MsgListAdapter.this.popwindows(paramAnonymous2JiaDeNetResponse.result.toString());
                  MsgListAdapter.this.popright.showAsDropDown(MsgListAdapter.this.titleName, 300, -200);
                  WaitLoadDialog.getInstance().dismissDialog();
                }
              }
              , MsgListAdapter.this.activity, Utils.getPhoneId(), str2, str1).execute(new JiaDeNetRequest[0]);
              WaitLoadDialog.getInstance().showDialog(MsgListAdapter.this.activity, null, true);
              return;
            }
            str1 = (String)localIterator.next();
          }
        }
      });
      return paramView;
      String str2 = (String)localIterator.next();
      this.id = str2;
      str1 = (String)localMap.get(str2);
      break;
      label175: ((DocItem)paramView.getTag());
      break label74;
      label186: localCheckBox.setVisibility(8);
    }
  }

  public final void popwindows(String paramString)
  {
    View localView = LayoutInflater.from(this.context).inflate(2130903052, null);
    this.popright = new PopupWindow(localView, -1, -1, false);
    ((TextView)localView.findViewById(2131427397)).setText(paramString);
    this.popright.setAnimationStyle(2131230730);
    this.popright.setBackgroundDrawable(new BitmapDrawable());
    this.popright.setOutsideTouchable(true);
    this.popright.setFocusable(true);
    ((ImageView)localView.findViewById(2131427396)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MsgListAdapter.this.popright.isShowing())
          MsgListAdapter.this.popright.dismiss();
      }
    });
    this.ischeck = true;
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
 * Qualified Name:     com.tg.jiadeonline.adapter.MsgListAdapter
 * JD-Core Version:    0.6.2
 */