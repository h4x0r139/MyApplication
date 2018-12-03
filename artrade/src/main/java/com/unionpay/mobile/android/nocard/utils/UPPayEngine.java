package com.unionpay.mobile.android.nocard.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.net.d;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

public class UPPayEngine
  implements Handler.Callback, Runnable
{
  private d a = null;
  private String b = null;
  private String c = null;
  private Context d = null;
  private Handler e = null;
  private WeakReference<a> f = null;
  private com.unionpay.mobile.android.model.b g = null;
  private long h = 0L;

  public UPPayEngine(Context paramContext)
  {
    this.d = paramContext;
    this.e = new Handler(this);
  }

  private native String commonMessage(long paramLong, String paramString1, String paramString2);

  private native String decryptResponse(long paramLong, String paramString);

  private native String followRulesMessage(long paramLong, String paramString);

  private native String getServerUrl(int paramInt1, int paramInt2, int paramInt3);

  private native String getUserInfo(long paramLong, String paramString);

  private void i(String paramString)
  {
    new Thread(this, paramString).start();
  }

  private native String initMessage(long paramLong, String paramString);

  private native String openupgradeMessage(long paramLong, String paramString);

  private native String payingMessage(long paramLong, String paramString1, String paramString2, String paramString3, String paramString4);

  private native String retrieveInitializeKey(long paramLong);

  private native String ruleMessage(long paramLong, String paramString);

  private native void setSessionKey(long paramLong, String paramString);

  private native String unBoundMessage(long paramLong, String paramString);

  public final void a()
  {
    int i = 1;
    int j;
    if (this.g.D.c.equalsIgnoreCase("01"))
      j = i;
    while (true)
    {
      g.a("uppay", "idx  is : " + j + ", isNewTypeTn :" + this.g.c);
      if (this.g.c);
      while (true)
      {
        String str = getServerUrl(i, j, this.g.aE);
        g.a("uppay", "url  is : " + str);
        this.a = new d(str);
        return;
        if (this.g.D.c.equalsIgnoreCase("02"))
        {
          j = 2;
          break;
        }
        if (this.g.D.c.equalsIgnoreCase("98"))
        {
          j = 98;
          break;
        }
        if (this.g.D.c.equalsIgnoreCase("99"))
        {
          j = 99;
          break;
        }
        if (!"95".equalsIgnoreCase(this.g.D.c))
          break label221;
        j = 95;
        break;
        i = 0;
      }
      label221: j = 0;
    }
  }

  public final void a(long paramLong)
  {
    this.h = paramLong;
  }

  public final void a(com.unionpay.mobile.android.model.b paramb)
  {
    if ((this.g == null) || (this.g != paramb))
      this.g = paramb;
  }

  public final void a(a parama)
  {
    this.f = new WeakReference(parama);
  }

  public final void a(String paramString)
  {
    this.b = paramString;
  }

  public final void a(String paramString1, String paramString2)
  {
    long l = this.h;
    Context localContext = this.d;
    String str1 = this.g.a();
    String str2 = this.g.d;
    g.c("functionEx", PreferenceUtils.b(localContext));
    Object[] arrayOfObject = new Object[19];
    arrayOfObject[0] = paramString1;
    int i;
    String str3;
    label82: String str4;
    label149: NetworkInfo localNetworkInfo;
    String str6;
    label362: StringBuffer localStringBuffer;
    if (new File("/system/bin/su").exists())
    {
      i = 1;
      if (i == 0)
        break label640;
      str3 = com.unionpay.mobile.android.utils.b.b(localContext);
      if ((str3 == null) || (str3.length() == 0))
        str3 = PreferenceUtils.a(localContext);
      g.a("uppay", "user=" + str3);
      arrayOfObject[1] = str3;
      if (!Locale.getDefault().toString().startsWith("zh"))
        break label659;
      str4 = "zh_CN";
      arrayOfObject[2] = str4;
      arrayOfObject[3] = com.unionpay.mobile.android.utils.b.a(localContext);
      arrayOfObject[4] = (a.I + "*" + a.t).trim();
      arrayOfObject[5] = "android";
      arrayOfObject[6] = Build.VERSION.RELEASE.trim();
      String str5 = Build.MODEL.trim();
      if (str5 != null)
        str5.replace(" ", "");
      arrayOfObject[7] = str5;
      arrayOfObject[8] = str1;
      arrayOfObject[9] = str2;
      arrayOfObject[10] = PreferenceUtils.a(localContext);
      arrayOfObject[11] = com.unionpay.mobile.android.utils.b.b(localContext);
      arrayOfObject[12] = TimeZone.getDefault().getDisplayName(false, 0);
      localNetworkInfo = ((ConnectivityManager)localContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()))
        break label700;
      if (localNetworkInfo.getType() != 0)
        break label675;
      if (localNetworkInfo.getState() != NetworkInfo.State.CONNECTED)
        break label667;
      str6 = "mobile:" + localNetworkInfo.getExtraInfo();
      arrayOfObject[13] = str6;
      String str7 = ((TelephonyManager)localContext.getSystemService("phone")).getSubscriberId();
      if (str7 == null)
        str7 = "";
      arrayOfObject[14] = str7;
      arrayOfObject[15] = com.unionpay.mobile.android.utils.b.a();
      arrayOfObject[16] = com.unionpay.mobile.android.utils.b.b();
      localStringBuffer = new StringBuffer("000");
      if (!"000".equals(paramString2))
        localStringBuffer.setCharAt(2, '1');
      if (Build.VERSION.SDK_INT >= 10)
      {
        NfcAdapter localNfcAdapter = ((NfcManager)localContext.getSystemService("nfc")).getDefaultAdapter();
        if (localNfcAdapter != null)
        {
          if (!localNfcAdapter.isEnabled())
            break label708;
          localStringBuffer.setCharAt(0, '1');
        }
      }
    }
    while (true)
    {
      if ((Build.VERSION.SDK_INT >= 19) && (localContext.getPackageManager().hasSystemFeature("android.hardware.nfc.hce")))
        localStringBuffer.setCharAt(1, '1');
      arrayOfObject[17] = localStringBuffer.toString();
      arrayOfObject[18] = paramString2;
      String str8 = String.format("\"tn\":\"%s\",\"user\":\"%s\",\"locale\":\"%s\",\"terminal_version\":\"%s\",\"terminal_resolution\":\"%s\",\"os_name\":\"%s\",\"os_version\":\"%s\",\"device_model\":\"%s\",\"terminal_type\":\"%s\",\"appId\":\"%s\", \"uid\":\"%s\",\"mac\":\"%s\",\"time_zone\":\"%s\",\"network_mode\":\"%s\",\"imsi\":\"%s\",\"baseband_version\":\"%s\",\"kernel_version\":\"%s\",\"support_map\":\"%s\",\"se_map\":\"%s\"", arrayOfObject);
      g.a("uppay", "init: " + str8);
      String str9 = initMessage(l, str8);
      this.a.a(str9);
      HashMap localHashMap = new HashMap(1);
      localHashMap.put("secret", retrieveInitializeKey(this.h));
      this.a.a(localHashMap);
      i("init");
      return;
      i = 0;
      break;
      label640: str3 = ((TelephonyManager)localContext.getSystemService("phone")).getDeviceId();
      break label82;
      label659: str4 = "en_US";
      break label149;
      label667: str6 = "mobile";
      break label362;
      label675: if (localNetworkInfo.getType() == 1)
      {
        str6 = "wifi";
        break label362;
      }
      str6 = "other";
      break label362;
      label700: str6 = "disConnect";
      break label362;
      label708: localStringBuffer.setCharAt(0, '2');
    }
  }

  public final void a(String paramString1, String paramString2, int paramInt)
  {
    String str = commonMessage(this.h, paramString1, paramString2);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    if (paramInt <= 0)
    {
      i(paramString1);
      return;
    }
    Message localMessage = this.e.obtainMessage(1, paramString1);
    this.e.sendMessageDelayed(localMessage, paramInt * 1000);
  }

  public final void a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    String str = payingMessage(this.h, paramString1, paramString2, paramString3, paramString4);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    i("pay");
  }

  public final long b()
  {
    return this.h;
  }

  public final void b(String paramString)
  {
    this.c = paramString;
  }

  public final void b(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, 0);
  }

  public final void c()
  {
    this.d = null;
    this.e.removeCallbacksAndMessages(null);
    this.e = null;
    this.a = null;
    this.g = null;
  }

  public final boolean c(String paramString)
  {
    setSessionKey(this.h, paramString);
    return true;
  }

  public final void d(String paramString)
  {
    String str = ruleMessage(this.h, paramString);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    i("rule");
  }

  public final void e(String paramString)
  {
    String str = followRulesMessage(this.h, paramString);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    i("followRule");
  }

  public final void f(String paramString)
  {
    String str = openupgradeMessage(this.h, paramString);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    i("openupgrade");
  }

  public final void g(String paramString)
  {
    String str = unBoundMessage(this.h, paramString);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    i("unbindcard");
  }

  public final void h(String paramString)
  {
    String str = getUserInfo(this.h, paramString);
    g.a("uppay", "actEntrust msg:" + str);
    this.a.a(str);
    HashMap localHashMap = new HashMap(1);
    localHashMap.put("sid", this.b);
    this.a.a(localHashMap);
    i("getuserinfo");
  }

  public boolean handleMessage(Message paramMessage)
  {
    b localb;
    String str2;
    if (paramMessage.what == 0)
    {
      localb = (b)paramMessage.obj;
      if (localb.a != 0)
        break label200;
      str2 = decryptResponse(this.h, localb.b);
      g.a("uppay", "resp is:" + str2);
    }
    label200: for (String str1 = str2; ; str1 = null)
    {
      if ((this.f != null) && (this.f.get() != null))
      {
        ((a)this.f.get()).a(localb.a, str1);
        g.b("uppayEx", "UPPayEngine:" + this.f.toString());
      }
      do
      {
        return true;
        if (paramMessage.what == 1)
        {
          i((String)paramMessage.obj);
          return true;
        }
      }
      while ((paramMessage.what != 2) || (this.f == null) || (this.f.get() == null));
      ((a)this.f.get()).a(paramMessage.arg1, null);
      return true;
    }
  }

  public native long initJNIEnv(Activity paramActivity, int paramInt1, int paramInt2, boolean paramBoolean, String paramString, int paramInt3);

  public void run()
  {
    if ((this.g.aE > 0) && (this.g.aE <= 5))
      this.a.c().put("magic_number", "20150423");
    while (true)
    {
      com.unionpay.mobile.android.net.c localc = new com.unionpay.mobile.android.net.c(this.a);
      b localb = new b(localc.a(), localc.c());
      if (this.e != null)
      {
        Message localMessage = this.e.obtainMessage();
        localMessage.what = 0;
        localMessage.obj = localb;
        this.e.sendMessage(localMessage);
      }
      return;
      this.a.c().put("magic_number", "20131120");
    }
  }

  public static abstract interface a
  {
    public abstract void a(int paramInt, String paramString);
  }

  final class b
  {
    public int a;
    public String b;

    public b(int paramString, String arg3)
    {
      this.a = paramString;
      Object localObject;
      this.b = localObject;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.nocard.utils.UPPayEngine
 * JD-Core Version:    0.6.2
 */