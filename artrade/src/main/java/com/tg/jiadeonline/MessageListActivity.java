package com.tg.jiadeonline;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tg.jiadeonline.adapter.MsgListAdapter;
import com.tg.jiadeonline.layout.XListView;
import com.tg.jiadeonline.layout.XListView.IXListViewListener;
import com.tg.jiadeonline.utils.CommonRef;
import com.tg.jiadeonline.utils.Exit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MessageListActivity extends Activity
  implements XListView.IXListViewListener
{
  private ImageView backImageView;
  private TextView delTextView;
  ArrayList<Map<String, String>> infoList = new ArrayList();
  private boolean isShowDel = false;
  private Handler mHandler;
  private TextView message_del_canbtn;
  private TextView message_del_delbtn;
  private LinearLayout message_list_layout;
  private XListView message_list_listview;
  private TextView message_view;
  private MsgListAdapter msgListAdapter;
  private TextView titleName;

  private void onLoad()
  {
    this.message_list_listview.stopRefresh();
    this.message_list_listview.stopLoadMore();
    this.message_list_listview.setRefreshTime("刚刚");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(2130903060);
    Exit.getInstance().addActivity(this);
    this.message_list_layout = ((LinearLayout)findViewById(2131427420));
    this.backImageView = ((ImageView)findViewById(2131427560));
    this.titleName = ((TextView)findViewById(2131427561));
    this.delTextView = ((TextView)findViewById(2131427564));
    this.delTextView.setVisibility(8);
    this.message_del_canbtn = ((TextView)findViewById(2131427421));
    this.message_del_delbtn = ((TextView)findViewById(2131427422));
    this.message_list_listview = ((XListView)findViewById(2131427419));
    this.message_del_delbtn.setVisibility(8);
    Iterator localIterator;
    if (getIntent().hasExtra("mes"))
    {
      this.infoList.clear();
      localIterator = CommonRef.msgmap.keySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.msgListAdapter = new MsgListAdapter(this, this, this.infoList, false);
        this.message_view = ((TextView)findViewById(2131427398));
        this.msgListAdapter.changetext(this.titleName);
        this.message_list_listview.setAdapter(this.msgListAdapter);
        this.message_list_listview.setPullLoadEnable(false);
        this.message_list_listview.setPullRefreshEnable(false);
        this.message_list_listview.setXListViewListener(this);
        this.mHandler = new Handler();
        this.backImageView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MessageListActivity.this.finish();
          }
        });
        this.titleName.setText("消息");
        this.delTextView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if (MessageListActivity.this.isShowDel)
            {
              MessageListActivity.this.isShowDel = false;
              MessageListActivity.this.message_list_layout.setVisibility(8);
            }
            while (true)
            {
              MessageListActivity.this.msgListAdapter = new MsgListAdapter(MessageListActivity.this, MessageListActivity.this, MessageListActivity.this.infoList, MessageListActivity.this.isShowDel);
              MessageListActivity.this.message_list_listview.setAdapter(MessageListActivity.this.msgListAdapter);
              return;
              MessageListActivity.this.isShowDel = true;
              MessageListActivity.this.message_list_layout.setVisibility(0);
            }
          }
        });
        this.message_del_canbtn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            MessageListActivity.this.isShowDel = false;
            MessageListActivity.this.message_list_layout.setVisibility(8);
            MessageListActivity.this.msgListAdapter = new MsgListAdapter(MessageListActivity.this, MessageListActivity.this, MessageListActivity.this.infoList, MessageListActivity.this.isShowDel);
            MessageListActivity.this.message_list_listview.setAdapter(MessageListActivity.this.msgListAdapter);
          }
        });
        this.message_del_delbtn.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            new AlertDialog.Builder(MessageListActivity.this).setTitle("删除提示框").setMessage("确认删除该数据？").setPositiveButton("确定", new DialogInterface.OnClickListener()
            {
              public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                List localList = MessageListActivity.this.msgListAdapter.getCheckBoxList();
                if ((localList != null) && (!"".equals(Integer.valueOf(localList.size()))));
                for (int i = 0; ; i++)
                {
                  if (i >= localList.size())
                    return;
                  if (((CheckBox)localList.get(i)).isChecked())
                  {
                    MessageListActivity.this.infoList.remove(i);
                    MessageListActivity.this.msgListAdapter = new MsgListAdapter(MessageListActivity.this, MessageListActivity.this, MessageListActivity.this.infoList, MessageListActivity.this.isShowDel);
                    MessageListActivity.this.message_list_listview.setAdapter(MessageListActivity.this.msgListAdapter);
                  }
                }
              }
            }).setNegativeButton("取消", null).show();
          }
        });
        return;
      }
      String str = (String)localIterator.next();
      ArrayMap localArrayMap = new ArrayMap();
      localArrayMap.put(str, (String)CommonRef.msgmap.get(str));
      this.infoList.add(localArrayMap);
    }
  }

  public void onLoadMore()
  {
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        MessageListActivity.this.msgListAdapter.changeCount(5);
        MessageListActivity.this.msgListAdapter.notifyDataSetChanged();
        MessageListActivity.this.onLoad();
      }
    }
    , 2000L);
  }

  public void onRefresh()
  {
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        MessageListActivity.this.message_list_listview.setAdapter(new MsgListAdapter(MessageListActivity.this, MessageListActivity.this, MessageListActivity.this.infoList, false));
        MessageListActivity.this.onLoad();
      }
    }
    , 2000L);
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        MessageListActivity.this.msgListAdapter = new MsgListAdapter(MessageListActivity.this, MessageListActivity.this, MessageListActivity.this.infoList, false);
        MessageListActivity.this.message_list_listview.setAdapter(MessageListActivity.this.msgListAdapter);
        MessageListActivity.this.onLoad();
      }
    }
    , 2000L);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.MessageListActivity
 * JD-Core Version:    0.6.2
 */