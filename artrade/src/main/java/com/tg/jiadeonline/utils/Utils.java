package com.tg.jiadeonline.utils;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tg.jiadeonline.LoginActivity;
import com.tg.jiadeonline.bean.LoginBean;
import com.tg.jiadeonline.date.RegisterOrLoginManager;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils
{
  static int HEIGHT = 0;
  public static long INTERVALTIMEOFANIMATION = 500L;
  static int WIDTH = 0;
  private static ImageLoader imageLoader;
  private static long oldTime;

  public static boolean IsNormalOperation()
  {
    long l = System.currentTimeMillis() - oldTime;
    Log.d("两次事件间隔毫秒：", String.valueOf(l));
    if (l < INTERVALTIMEOFANIMATION)
      return false;
    oldTime = System.currentTimeMillis();
    return true;
  }

  public static void alertDialog(Context paramContext, String paramString)
  {
    new AlertDialog.Builder(paramContext).setTitle("温馨提示").setMessage(paramString).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
      }
    }).show();
  }

  public static boolean checkdate(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    try
    {
      long l1 = localSimpleDateFormat.parse(paramString1).getTime();
      long l2 = localSimpleDateFormat.parse(paramString2).getTime();
      boolean bool1 = l1 < l2;
      boolean bool2 = false;
      if (bool1)
        bool2 = true;
      return bool2;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return false;
  }

  public static boolean clearM()
  {
    if (imageLoader != null)
    {
      imageLoader.clearDiscCache();
      imageLoader.clearMemoryCache();
      return true;
    }
    return false;
  }

  public static boolean compare_date(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    try
    {
      Date localDate1 = localSimpleDateFormat.parse(paramString1);
      Date localDate2 = localSimpleDateFormat.parse(paramString2);
      if (localDate1.getTime() > localDate2.getTime())
        return true;
      long l1 = localDate1.getTime();
      long l2 = localDate2.getTime();
      if (l1 < l2)
        return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public static File getAlbumDir()
  {
    File localFile = new File(Environment.getExternalStorageDirectory() + "/" + "Pics", getAlbumName());
    if (!localFile.exists())
      localFile.mkdirs();
    return localFile;
  }

  public static String getAlbumName()
  {
    return "htsoftcache";
  }

  public static int getHeightPixels(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getHeight();
  }

  public static String getPhoneId()
  {
    return "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10;
  }

  public static int[] getScreenMetrics(Activity paramActivity)
  {
    if ((WIDTH == 0) || (HEIGHT == 0))
    {
      Display localDisplay = paramActivity.getWindowManager().getDefaultDisplay();
      WIDTH = localDisplay.getWidth();
      HEIGHT = localDisplay.getHeight();
    }
    int[] arrayOfInt = new int[2];
    arrayOfInt[0] = WIDTH;
    arrayOfInt[1] = HEIGHT;
    return arrayOfInt;
  }

  public static String getUserId(Activity paramActivity)
  {
    RegisterOrLoginManager localRegisterOrLoginManager = new RegisterOrLoginManager(paramActivity);
    localRegisterOrLoginManager.openDataBase();
    new ArrayList();
    List localList = localRegisterOrLoginManager.fetchAllDatas();
    localRegisterOrLoginManager.closeDataBase();
    if ((localList != null) && (localList.size() > 0))
      return ((LoginBean)localList.get(0)).getUserId();
    return "";
  }

  public static String getVersion(Activity paramActivity)
  {
    try
    {
      String str = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "获取出错";
  }

  public static int getWidthPixels(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
  }

  public static String get_date(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long l1 = 0L;
    long l2 = 0L;
    long l3 = 0L;
    long l4 = 0L;
    try
    {
      Date localDate1 = localSimpleDateFormat.parse(paramString1);
      Date localDate2 = localSimpleDateFormat.parse(paramString2);
      long l5 = localDate1.getTime();
      long l6 = localDate2.getTime();
      if (l5 < l6);
      for (long l7 = l6 - l5; ; l7 = l5 - l6)
      {
        l1 = l7 / 86400000L;
        l2 = l7 / 3600000L - 24L * l1;
        l3 = l7 / 60000L - 60L * (24L * l1) - 60L * l2;
        long l8 = l7 / 1000L;
        l4 = l8 - 60L * (60L * (24L * l1)) - 60L * (60L * l2) - 60L * l3;
        if (l1 <= 0L)
          break;
        return new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("")).append(l1).append("天").toString())).append(l2).append("小时").toString())).append(l3).append("分").toString() + l4 + "秒";
      }
    }
    catch (ParseException localParseException)
    {
      while (true)
        localParseException.printStackTrace();
      if (l2 > 0L)
        return new StringBuilder(String.valueOf("")).append(l2).append("小时").toString() + l3 + "分";
      if (l3 > 0L)
        return new StringBuilder(String.valueOf("")).append(l3).append("分").toString() + l4 + "秒";
      if (l4 > 0L)
        return "" + l4 + "秒";
    }
    return "" + "0秒";
  }

  public static boolean isValidDate(String paramString)
  {
    if (paramString == null);
    SimpleDateFormat localSimpleDateFormat;
    do
    {
      return false;
      localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    while (paramString.trim().length() != localSimpleDateFormat.toPattern().length());
    localSimpleDateFormat.setLenient(false);
    try
    {
      localSimpleDateFormat.parse(paramString.trim());
      return true;
    }
    catch (ParseException localParseException)
    {
    }
    return false;
  }

  public static void loadImage(String paramString, ImageView paramImageView, Activity paramActivity)
  {
    if (("".equals(paramString)) || ("null".equals(paramString)))
    {
      paramImageView.setImageResource(2130837574);
      return;
    }
    DisplayImageOptions localDisplayImageOptions = ImageUtil.getDisplayImageOptions();
    ImageUtil.AnimateFirstDisplayListener localAnimateFirstDisplayListener = new ImageUtil.AnimateFirstDisplayListener();
    if (imageLoader == null)
      imageLoader = ImageLoader.getInstance();
    if (!imageLoader.isInited())
      imageLoader.init(ImageLoaderConfiguration.createDefault(paramActivity));
    imageLoader.displayImage(paramString, paramImageView, localDisplayImageOptions, localAnimateFirstDisplayListener);
  }

  public static void loadImagechang(String paramString, ImageView paramImageView, Activity paramActivity)
  {
    if (("".equals(paramString)) || ("null".equals(paramString)))
    {
      paramImageView.setImageResource(2130837574);
      return;
    }
    DisplayImageOptions localDisplayImageOptions = ImageUtil.getDisplayImageOptions1();
    ImageUtil.AnimateFirstDisplayListener localAnimateFirstDisplayListener = new ImageUtil.AnimateFirstDisplayListener();
    if (imageLoader == null)
      imageLoader = ImageLoader.getInstance();
    if (!imageLoader.isInited())
    {
      ImageLoaderConfiguration localImageLoaderConfiguration = ImageUtil.getImageLoaderConfiguration(paramActivity);
      imageLoader.init(localImageLoaderConfiguration);
    }
    imageLoader.displayImage(paramString, paramImageView, localDisplayImageOptions, localAnimateFirstDisplayListener);
  }

  public static void setListViewHeightBasedOnChildren(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    for (int j = 0; ; j++)
    {
      if (j >= localListAdapter.getCount())
      {
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (i + 200 + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
        paramListView.setLayoutParams(localLayoutParams);
        return;
      }
      View localView = localListAdapter.getView(j, null, paramListView);
      localView.measure(0, 0);
      i += localView.getMeasuredHeight();
    }
  }

  public static void setListViewHeightBasedOnChildren1(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    for (int j = 0; ; j++)
    {
      if (j >= localListAdapter.getCount())
      {
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (i + 100 + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
        paramListView.setLayoutParams(localLayoutParams);
        return;
      }
      View localView = localListAdapter.getView(j, null, paramListView);
      localView.measure(0, 0);
      i += localView.getMeasuredHeight();
    }
  }

  public static void setListViewHeightBasedOnChildren2(ListView paramListView)
  {
    ListAdapter localListAdapter = paramListView.getAdapter();
    if (localListAdapter == null)
      return;
    int i = 0;
    for (int j = 0; ; j++)
    {
      if (j >= localListAdapter.getCount())
      {
        ViewGroup.LayoutParams localLayoutParams = paramListView.getLayoutParams();
        localLayoutParams.height = (i + 200 + paramListView.getDividerHeight() * (-1 + localListAdapter.getCount()));
        paramListView.setLayoutParams(localLayoutParams);
        return;
      }
      i += localListAdapter.getView(j, null, paramListView).getMeasuredHeight();
    }
  }

  public static void setOldTime(long paramLong)
  {
    oldTime = paramLong;
  }

  public static void showDialog(Context paramContext, String paramString1, String paramString2, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setIcon(paramInt);
    localBuilder.setTitle(paramString1);
    localBuilder.setMessage(paramString2);
    localBuilder.setPositiveButton("确定", paramOnClickListener);
    localBuilder.show();
  }

  public static Bitmap toRoundBitmap1(Context paramContext, Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1;
    float f3;
    float f5;
    float f4;
    float f6;
    if (i <= j)
    {
      f1 = i / 2;
      f3 = 0.0F;
      f5 = i;
      f4 = i;
      j = i;
      f6 = i;
    }
    for (float f7 = i; ; f7 = j)
    {
      Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
      Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect1 = new Rect((int)f3, (int)0.0F, (int)f4, (int)f5);
      Rect localRect2 = new Rect((int)0.0F, (int)0.0F, (int)f6, (int)f7);
      RectF localRectF = new RectF(localRect2);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-1);
      localPaint.setStrokeWidth(4.0F);
      localCanvas.drawRoundRect(localRectF, f1, f1, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
      return localBitmap;
      f1 = j / 2;
      float f2 = (i - j) / 2;
      f3 = f2;
      f4 = i - f2;
      f5 = j;
      i = j;
      f6 = j;
    }
  }

  public static Bitmap toRoundBitmap1(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1;
    float f3;
    float f5;
    float f4;
    float f6;
    if (i <= j)
    {
      f1 = i / 2;
      f3 = 0.0F;
      f5 = i;
      f4 = i;
      j = i;
      f6 = i;
    }
    for (float f7 = i; ; f7 = j)
    {
      Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
      Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect1 = new Rect((int)f3, (int)0.0F, (int)f4, (int)f5);
      Rect localRect2 = new Rect((int)0.0F, (int)0.0F, (int)f6, (int)f7);
      RectF localRectF = new RectF(localRect2);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-1);
      localPaint.setStrokeWidth(4.0F);
      localCanvas.drawRoundRect(localRectF, f1, f1, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect1, localRect2, localPaint);
      localPaint.reset();
      return localBitmap;
      f1 = j / 2;
      float f2 = (i - j) / 2;
      f3 = f2;
      f4 = i - f2;
      f5 = j;
      i = j;
      f6 = j;
    }
  }

  public static void tologin(Activity paramActivity)
  {
    if ("".equals(getUserId(paramActivity)))
      new AlertDialog.Builder(paramActivity).setTitle("提示").setMessage("未登录,是否登录?").setPositiveButton("确定", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Intent localIntent = new Intent(Utils.this, LoginActivity.class);
          Utils.this.startActivity(localIntent);
        }
      }).setNegativeButton("返回", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
        }
      }).show();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.Utils
 * JD-Core Version:    0.6.2
 */