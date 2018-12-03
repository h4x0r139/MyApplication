package com.tg.jiadeonline.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tg.jiadeonline.utils.AsyncQuXiao;

public class XiaZaiActivity extends Activity
{
  private Activity activity;
  private Context context;
  private String currinfo;
  private AsyncQuXiao download;
  private String upd_log;
  private String upd_url;
  private String versioninfo;

  private Dialog AlertDialogUtil(View paramView, Context paramContext, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString);
    localBuilder.setIcon(2130837566);
    localBuilder.setView(paramView);
    localBuilder.create();
    return localBuilder.show();
  }

  private void init()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this, 2131230724);
    localBuilder.setTitle("发现新版本请下载！");
    localBuilder.setMessage(this.upd_log);
    localBuilder.setPositiveButton("立即下载", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        View localView = LayoutInflater.from(XiaZaiActivity.this).inflate(2130903092, null);
        ProgressBar localProgressBar = (ProgressBar)localView.findViewById(2131427585);
        ((TextView)localView.findViewById(2131427586));
        final Dialog localDialog = XiaZaiActivity.this.AlertDialogUtil(localView, XiaZaiActivity.this, XiaZaiActivity.this.getString(2131165205));
        XiaZaiActivity.this.download = new AsyncQuXiao(XiaZaiActivity.this.activity);
        XiaZaiActivity.this.download.setPb(localProgressBar);
        com.tg.jiadeonline.utils.Async.dialog = localDialog;
        com.tg.jiadeonline.utils.Async.ctx = XiaZaiActivity.this;
        if ((XiaZaiActivity.this.currinfo == null) || ("".equals(XiaZaiActivity.this.currinfo)))
          Toast.makeText(XiaZaiActivity.this, "获取服务器url异常！下载失败！", 1).show();
        while (true)
        {
          ((Button)localView.findViewById(2131427587)).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              localDialog.dismiss();
              XiaZaiActivity.this.download.onCancelled();
              XiaZaiActivity.this.activity.finish();
            }
          });
          return;
          AsyncQuXiao localAsyncQuXiao = XiaZaiActivity.this.download;
          String[] arrayOfString = new String[3];
          arrayOfString[0] = XiaZaiActivity.this.upd_url;
          arrayOfString[1] = Environment.getExternalStorageDirectory();
          arrayOfString[2] = XiaZaiActivity.this.versioninfo;
          localAsyncQuXiao.execute(arrayOfString);
        }
      }
    });
    localBuilder.setNegativeButton("以后再说", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        XiaZaiActivity.this.activity.finish();
      }
    });
    localBuilder.setCancelable(false);
    localBuilder.setOnKeyListener(new DialogInterface.OnKeyListener()
    {
      public boolean onKey(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        return paramAnonymousInt == 84;
      }
    });
    localBuilder.create().show();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = this;
    this.activity = this;
    setContentView(2130903074);
    Intent localIntent = getIntent();
    this.upd_log = localIntent.getStringExtra("upd_log");
    this.currinfo = localIntent.getStringExtra("currinfo");
    this.upd_url = localIntent.getStringExtra("upd_url");
    this.versioninfo = localIntent.getStringExtra("versioninfo");
    init();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.activity.XiaZaiActivity
 * JD-Core Version:    0.6.2
 */