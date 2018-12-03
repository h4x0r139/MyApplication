package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView.BufferType;
import com.unionpay.mobile.android.languages.c;
import java.math.BigDecimal;

public class bc extends z
  implements t.a
{
  private static final String n = bc.class.getSimpleName();
  private String o;
  private String p;
  private String q;
  private String r;
  private String s;

  // ERROR //
  public bc(android.content.Context paramContext, int paramInt, org.json.JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: iload_2
    //   3: aload_3
    //   4: invokespecial 29	com/unionpay/mobile/android/widgets/z:<init>	(Landroid/content/Context;ILorg/json/JSONObject;)V
    //   7: aload_0
    //   8: aconst_null
    //   9: putfield 31	com/unionpay/mobile/android/widgets/bc:o	Ljava/lang/String;
    //   12: aload_0
    //   13: aconst_null
    //   14: putfield 33	com/unionpay/mobile/android/widgets/bc:p	Ljava/lang/String;
    //   17: aload_0
    //   18: aconst_null
    //   19: putfield 35	com/unionpay/mobile/android/widgets/bc:q	Ljava/lang/String;
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield 37	com/unionpay/mobile/android/widgets/bc:r	Ljava/lang/String;
    //   27: aload_0
    //   28: aconst_null
    //   29: putfield 39	com/unionpay/mobile/android/widgets/bc:s	Ljava/lang/String;
    //   32: aload_0
    //   33: aload_3
    //   34: ldc 41
    //   36: invokestatic 47	com/unionpay/mobile/android/utils/f:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   39: putfield 37	com/unionpay/mobile/android/widgets/bc:r	Ljava/lang/String;
    //   42: aload_0
    //   43: aload_3
    //   44: ldc 49
    //   46: invokestatic 47	com/unionpay/mobile/android/utils/f:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   49: putfield 35	com/unionpay/mobile/android/widgets/bc:q	Ljava/lang/String;
    //   52: aload_0
    //   53: aload_3
    //   54: ldc 51
    //   56: invokestatic 47	com/unionpay/mobile/android/utils/f:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   59: putfield 33	com/unionpay/mobile/android/widgets/bc:p	Ljava/lang/String;
    //   62: aload_0
    //   63: aload_3
    //   64: ldc 53
    //   66: invokestatic 47	com/unionpay/mobile/android/utils/f:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   69: putfield 39	com/unionpay/mobile/android/widgets/bc:s	Ljava/lang/String;
    //   72: aload_0
    //   73: aload_3
    //   74: ldc 55
    //   76: invokestatic 47	com/unionpay/mobile/android/utils/f:a	(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
    //   79: putfield 31	com/unionpay/mobile/android/widgets/bc:o	Ljava/lang/String;
    //   82: aload_0
    //   83: getfield 59	com/unionpay/mobile/android/widgets/bc:b	Lcom/unionpay/mobile/android/widgets/t;
    //   86: sipush 8194
    //   89: invokevirtual 64	com/unionpay/mobile/android/widgets/t:a	(I)V
    //   92: aload_0
    //   93: getfield 59	com/unionpay/mobile/android/widgets/bc:b	Lcom/unionpay/mobile/android/widgets/t;
    //   96: ldc 66
    //   98: invokestatic 72	android/text/method/DigitsKeyListener:getInstance	(Ljava/lang/String;)Landroid/text/method/DigitsKeyListener;
    //   101: invokevirtual 75	com/unionpay/mobile/android/widgets/t:a	(Landroid/text/InputFilter;)V
    //   104: new 77	java/math/BigDecimal
    //   107: dup
    //   108: aload_0
    //   109: getfield 33	com/unionpay/mobile/android/widgets/bc:p	Ljava/lang/String;
    //   112: invokespecial 80	java/math/BigDecimal:<init>	(Ljava/lang/String;)V
    //   115: astore 4
    //   117: aload 4
    //   119: iconst_2
    //   120: bipush 6
    //   122: invokevirtual 84	java/math/BigDecimal:setScale	(II)Ljava/math/BigDecimal;
    //   125: astore 5
    //   127: new 77	java/math/BigDecimal
    //   130: dup
    //   131: aload_0
    //   132: getfield 35	com/unionpay/mobile/android/widgets/bc:q	Ljava/lang/String;
    //   135: invokespecial 80	java/math/BigDecimal:<init>	(Ljava/lang/String;)V
    //   138: astore 6
    //   140: aload 6
    //   142: iconst_2
    //   143: bipush 6
    //   145: invokevirtual 84	java/math/BigDecimal:setScale	(II)Ljava/math/BigDecimal;
    //   148: astore 7
    //   150: aload_0
    //   151: getfield 59	com/unionpay/mobile/android/widgets/bc:b	Lcom/unionpay/mobile/android/widgets/t;
    //   154: new 86	com/unionpay/mobile/android/widgets/bc$a
    //   157: dup
    //   158: aload_0
    //   159: aload_0
    //   160: getfield 59	com/unionpay/mobile/android/widgets/bc:b	Lcom/unionpay/mobile/android/widgets/t;
    //   163: aload 5
    //   165: aload 7
    //   167: invokespecial 89	com/unionpay/mobile/android/widgets/bc$a:<init>	(Lcom/unionpay/mobile/android/widgets/bc;Lcom/unionpay/mobile/android/widgets/t;Ljava/math/BigDecimal;Ljava/math/BigDecimal;)V
    //   170: invokevirtual 75	com/unionpay/mobile/android/widgets/t:a	(Landroid/text/InputFilter;)V
    //   173: aload_0
    //   174: aconst_null
    //   175: aconst_null
    //   176: invokespecial 92	com/unionpay/mobile/android/widgets/bc:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   179: aload_0
    //   180: invokevirtual 94	com/unionpay/mobile/android/widgets/bc:p	()Ljava/lang/String;
    //   183: ifnull +13 -> 196
    //   186: aload_0
    //   187: invokevirtual 94	com/unionpay/mobile/android/widgets/bc:p	()Ljava/lang/String;
    //   190: invokevirtual 100	java/lang/String:length	()I
    //   193: ifne +48 -> 241
    //   196: aload_0
    //   197: invokevirtual 102	com/unionpay/mobile/android/widgets/bc:s	()V
    //   200: getstatic 108	com/unionpay/mobile/android/languages/c:by	Lcom/unionpay/mobile/android/languages/c;
    //   203: getfield 111	com/unionpay/mobile/android/languages/c:ay	Ljava/lang/String;
    //   206: astore 8
    //   208: iconst_2
    //   209: anewarray 113	java/lang/Object
    //   212: astore 9
    //   214: aload 9
    //   216: iconst_0
    //   217: aload_0
    //   218: getfield 37	com/unionpay/mobile/android/widgets/bc:r	Ljava/lang/String;
    //   221: aastore
    //   222: aload 9
    //   224: iconst_1
    //   225: aload_0
    //   226: getfield 39	com/unionpay/mobile/android/widgets/bc:s	Ljava/lang/String;
    //   229: aastore
    //   230: aload_0
    //   231: aload 8
    //   233: aload 9
    //   235: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   238: invokevirtual 120	com/unionpay/mobile/android/widgets/bc:c	(Ljava/lang/String;)V
    //   241: aload_0
    //   242: getfield 59	com/unionpay/mobile/android/widgets/bc:b	Lcom/unionpay/mobile/android/widgets/t;
    //   245: aload_0
    //   246: invokevirtual 123	com/unionpay/mobile/android/widgets/t:a	(Lcom/unionpay/mobile/android/widgets/t$a;)V
    //   249: return
    //   250: astore 16
    //   252: getstatic 127	java/math/BigDecimal:ZERO	Ljava/math/BigDecimal;
    //   255: astore 17
    //   257: aload 17
    //   259: iconst_2
    //   260: bipush 6
    //   262: invokevirtual 84	java/math/BigDecimal:setScale	(II)Ljava/math/BigDecimal;
    //   265: astore 5
    //   267: goto -140 -> 127
    //   270: astore 14
    //   272: aconst_null
    //   273: iconst_2
    //   274: bipush 6
    //   276: invokevirtual 84	java/math/BigDecimal:setScale	(II)Ljava/math/BigDecimal;
    //   279: pop
    //   280: aload 14
    //   282: athrow
    //   283: astore 12
    //   285: new 77	java/math/BigDecimal
    //   288: dup
    //   289: ldc2_w 128
    //   292: invokespecial 132	java/math/BigDecimal:<init>	(D)V
    //   295: astore 13
    //   297: aload 13
    //   299: iconst_2
    //   300: bipush 6
    //   302: invokevirtual 84	java/math/BigDecimal:setScale	(II)Ljava/math/BigDecimal;
    //   305: astore 7
    //   307: goto -157 -> 150
    //   310: astore 10
    //   312: aconst_null
    //   313: iconst_2
    //   314: bipush 6
    //   316: invokevirtual 84	java/math/BigDecimal:setScale	(II)Ljava/math/BigDecimal;
    //   319: pop
    //   320: aload 10
    //   322: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   104	117	250	java/lang/Exception
    //   104	117	270	finally
    //   252	257	270	finally
    //   127	140	283	java/lang/Exception
    //   127	140	310	finally
    //   285	297	310	finally
  }

  private void a(String paramString1, String paramString2)
  {
    SpannableString localSpannableString;
    if ((o() == null) || (o().length() == 0))
    {
      r();
      if (paramString1 != null)
        break label102;
      String str3 = c.by.aw;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.q;
      String str4 = String.format(str3, arrayOfObject2);
      localSpannableString = new SpannableString(str4);
      localSpannableString.setSpan(new ForegroundColorSpan(-15958150), 0, str4.length(), 0);
    }
    while (true)
    {
      t();
      a(localSpannableString, TextView.BufferType.SPANNABLE);
      return;
      label102: String str1 = c.by.aw + paramString1;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = this.q;
      arrayOfObject1[1] = paramString2;
      String str2 = String.format(str1, arrayOfObject1);
      localSpannableString = new SpannableString(str2);
      localSpannableString.setSpan(new ForegroundColorSpan(-15958150), 0, str2.length() - (paramString2 + "元").length(), 0);
      localSpannableString.setSpan(new ForegroundColorSpan(-44462), str2.length() - (paramString2 + "元").length(), str2.length(), 0);
    }
  }

  public final String a()
  {
    String str1 = super.a();
    if ((str1 == null) || (str1.length() == 0));
    while (true)
    {
      return null;
      try
      {
        BigDecimal localBigDecimal = new BigDecimal(str1);
        localBigDecimal.setScale(2, 6);
        if (localBigDecimal.compareTo(BigDecimal.ZERO) == 1)
        {
          String str2 = localBigDecimal.toString();
          return str2;
        }
      }
      catch (Exception localException)
      {
      }
    }
    return null;
  }

  public final void a(Editable paramEditable)
  {
    super.a(paramEditable);
    if ((o() == null) || (o().length() == 0));
    try
    {
      BigDecimal localBigDecimal1 = new BigDecimal(this.s).setScale(2, 6);
      BigDecimal localBigDecimal2 = new BigDecimal(this.o).setScale(2, 6).subtract(new BigDecimal(a()).setScale(2, 6).multiply(localBigDecimal1).setScale(2, 6));
      a(c.by.ax, localBigDecimal2.toString());
      return;
    }
    catch (Exception localException)
    {
      a(null, null);
    }
  }

  public final void a(boolean paramBoolean)
  {
    if (paramBoolean)
      a(this.k);
  }

  public final boolean b()
  {
    return true;
  }

  public final boolean c()
  {
    return true;
  }

  public final String g()
  {
    String str = a();
    if ((str == null) || (str.length() == 0))
      return null;
    return super.g();
  }

  public final class a
    implements InputFilter
  {
    private t b = null;
    private BigDecimal c = null;
    private BigDecimal d = null;

    public a(t paramBigDecimal1, BigDecimal paramBigDecimal2, BigDecimal arg4)
    {
      this.b = paramBigDecimal1;
      this.c = paramBigDecimal2;
      Object localObject;
      this.d = localObject;
      if (this.c.compareTo(BigDecimal.ZERO) == 1)
        c(this.c.toString());
    }

    private static int a(String paramString)
    {
      if ((paramString == null) || (!b(paramString)))
        return 0;
      return -1 + (paramString.length() - paramString.indexOf("."));
    }

    private CharSequence a(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      String str1 = this.d.toString();
      String str2 = this.c.toString();
      try
      {
        BigDecimal localBigDecimal = new BigDecimal(paramString);
        localBigDecimal.setScale(2, 6);
        if ((paramBoolean1) && (paramBoolean2) && (localBigDecimal.compareTo(this.c) == -1))
          c(str2);
        if (localBigDecimal.compareTo(this.d) == 1)
          c(str1);
        return null;
      }
      catch (Exception localException)
      {
      }
      return "";
    }

    private static boolean b(String paramString)
    {
      return (paramString != null) && (paramString.contains("."));
    }

    private void c(String paramString)
    {
      this.b.c(paramString);
      Selection.setSelection(this.b.c(), this.b.b().length());
    }

    public final CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      int i = 1;
      if ((paramCharSequence != null) && (paramCharSequence.equals("")))
      {
        String str2 = paramSpanned.subSequence(0, paramInt3).toString() + paramCharSequence.subSequence(paramInt1, paramInt2).toString() + paramSpanned.subSequence(paramInt4, paramSpanned.length());
        int m = paramInt4 - paramInt3;
        boolean bool3 = false;
        if (m != i)
          bool3 = i;
        return a(str2, i, bool3);
      }
      int k = paramInt2 - paramInt1;
      boolean bool1 = b(paramSpanned.toString());
      if (i == k)
      {
        if ((!bool1) && (paramSpanned.length() == i) && (paramSpanned.charAt(0) == '0'))
          return ".";
        if (('0' == paramCharSequence.charAt(0)) && (paramInt3 == 0) && (paramInt4 == 0) && (paramSpanned.length() != 0))
          return "";
        if ('.' == paramCharSequence.charAt(0))
        {
          if (paramSpanned.length() == 0)
            return "";
          if ((paramSpanned.length() != 0) && (paramSpanned.length() - paramInt3 > 2))
            return "";
        }
        if ((bool1) && (paramInt3 > paramSpanned.toString().indexOf(".")) && (a(paramSpanned.toString()) > 2 - k))
          return "";
      }
      else
      {
        boolean bool2 = b(paramCharSequence.toString());
        if ((bool2) && (bool1))
          return "";
        if ((bool2) && ((a(paramCharSequence.toString()) > 2) || (paramSpanned.toString().length() != paramInt4)))
          return "";
      }
      String str1 = paramSpanned.subSequence(0, paramInt3).toString() + paramCharSequence.subSequence(paramInt1, paramInt2).toString() + paramSpanned.subSequence(paramInt4, paramSpanned.length());
      if (a(str1) == 2);
      while (true)
      {
        return a(str1, false, i);
        int j = 0;
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.unionpay.mobile.android.widgets.bc
 * JD-Core Version:    0.6.2
 */