package com.tg.jiadeonline.utils;

import java.io.PrintStream;
import java.util.HashMap;

public class IDCard
{
  private static String[] _areaCode;
  private static HashMap<String, String> areaCodeMap;
  private static HashMap<String, Integer> dateMap;
  private String _codeError;
  private int[] ai;
  final int[] vi;
  final int[] wi = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };

  static
  {
    int i = 0;
    _areaCode = new String[] { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91" };
    dateMap = new HashMap();
    dateMap.put("01", Integer.valueOf(31));
    dateMap.put("02", null);
    dateMap.put("03", Integer.valueOf(31));
    dateMap.put("04", Integer.valueOf(30));
    dateMap.put("05", Integer.valueOf(31));
    dateMap.put("06", Integer.valueOf(30));
    dateMap.put("07", Integer.valueOf(31));
    dateMap.put("08", Integer.valueOf(31));
    dateMap.put("09", Integer.valueOf(30));
    dateMap.put("10", Integer.valueOf(31));
    dateMap.put("11", Integer.valueOf(30));
    dateMap.put("12", Integer.valueOf(31));
    areaCodeMap = new HashMap();
    String[] arrayOfString = _areaCode;
    int j = arrayOfString.length;
    while (true)
    {
      if (i >= j)
        return;
      String str = arrayOfString[i];
      areaCodeMap.put(str, null);
      i++;
    }
  }

  public IDCard()
  {
    int[] arrayOfInt = new int[11];
    arrayOfInt[0] = 1;
    arrayOfInt[2] = 88;
    arrayOfInt[3] = 9;
    arrayOfInt[4] = 8;
    arrayOfInt[5] = 7;
    arrayOfInt[6] = 6;
    arrayOfInt[7] = 5;
    arrayOfInt[8] = 4;
    arrayOfInt[9] = 3;
    arrayOfInt[10] = 2;
    this.vi = arrayOfInt;
    this.ai = new int[18];
  }

  public boolean containsAllNumber(String paramString)
  {
    String str = "";
    char[] arrayOfChar;
    if (paramString.length() == 15)
    {
      str = paramString.substring(0, 15);
      arrayOfChar = str.toCharArray();
    }
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfChar.length)
      {
        return true;
        if (paramString.length() != 18)
          break;
        str = paramString.substring(0, 17);
        break;
      }
      if ((arrayOfChar[i] < '0') || (arrayOfChar[i] > '9'))
      {
        this._codeError = ("错误：输入的身份证号第" + (i + 1) + "位包含字母");
        return false;
      }
    }
  }

  public String getCodeError()
  {
    return this._codeError;
  }

  public String getVerify(String paramString)
  {
    if (paramString.length() == 18)
      paramString = paramString.substring(0, 17);
    int i = paramString.length();
    int j = 0;
    int k;
    int m;
    if (i == 17)
    {
      k = 0;
      m = 0;
      if (m < 17)
        break label67;
    }
    for (int n = 0; ; n++)
    {
      if (n >= 17)
      {
        j = k % 11;
        if (j != 2)
          break label123;
        return "X";
        label67: String str = paramString.substring(m, m + 1);
        this.ai[m] = Integer.parseInt(str);
        m++;
        break;
      }
      k += this.wi[n] * this.ai[n];
    }
    label123: return String.valueOf(this.vi[j]);
  }

  public String uptoeighteen(String paramString)
  {
    String str = new StringBuilder(String.valueOf(paramString.substring(0, 6))).append("19").toString() + paramString.substring(6, 15);
    return str + getVerify(str);
  }

  public boolean verify(String paramString)
  {
    this._codeError = "";
    if (!verifyLength(paramString))
    {
      System.out.println(this._codeError);
      return false;
    }
    if (!containsAllNumber(paramString))
    {
      System.out.println(this._codeError);
      return false;
    }
    if (paramString.length() == 15);
    for (String str = uptoeighteen(paramString); !verifyAreaCode(str); str = paramString)
    {
      System.out.println(this._codeError);
      return false;
    }
    if (!verifyBirthdayCode(str))
    {
      System.out.println(this._codeError);
      return false;
    }
    if (!verifyMOD(str))
    {
      System.out.println(this._codeError);
      return false;
    }
    System.out.println(this._codeError);
    return true;
  }

  public boolean verifyAreaCode(String paramString)
  {
    String str = paramString.substring(0, 2);
    if (areaCodeMap.containsKey(str))
      return true;
    this._codeError = ("错误：输入的身份证号的地区码(1-2位)[" + str + "]不符合中国行政区划分代码规定(GB/T2260-1999)");
    return false;
  }

  public boolean verifyBirthdayCode(String paramString)
  {
    String str1 = paramString.substring(10, 12);
    int i;
    StringBuilder localStringBuilder1;
    if (18 == paramString.length())
    {
      i = 1;
      if (dateMap.containsKey(str1))
        break label91;
      localStringBuilder1 = new StringBuilder("错误：输入的身份证号");
      if (i == 0)
        break label84;
    }
    label84: for (String str2 = "(11-12位)"; ; str2 = "(9-10位)")
    {
      this._codeError = (str2 + "不存在[" + str1 + "]月份,不符合要求(GB/T7408)");
      return false;
      i = 0;
      break;
    }
    label91: String str3 = paramString.substring(12, 14);
    Integer localInteger1 = (Integer)dateMap.get(str1);
    Integer localInteger2 = Integer.valueOf(paramString.substring(6, 10));
    if (localInteger1 != null)
    {
      if ((Integer.valueOf(str3).intValue() > localInteger1.intValue()) || (Integer.valueOf(str3).intValue() < 1))
      {
        StringBuilder localStringBuilder4 = new StringBuilder("错误：输入的身份证号");
        if (i != 0);
        for (String str6 = "(13-14位)"; ; str6 = "(11-13位)")
        {
          this._codeError = (str6 + "[" + str3 + "]号不符合小月1-30天大月1-31天的规定(GB/T7408)");
          return false;
        }
      }
    }
    else if (((localInteger2.intValue() % 4 == 0) && (localInteger2.intValue() % 100 != 0)) || (localInteger2.intValue() % 400 == 0))
    {
      if ((Integer.valueOf(str3).intValue() > 29) || (Integer.valueOf(str3).intValue() < 1))
      {
        StringBuilder localStringBuilder3 = new StringBuilder("错误：输入的身份证号");
        if (i != 0);
        for (String str5 = "(13-14位)"; ; str5 = "(11-13位)")
        {
          this._codeError = (str5 + "[" + str3 + "]号在" + localInteger2 + "闰年的情况下未符合1-29号的规定(GB/T7408)");
          return false;
        }
      }
    }
    else if ((Integer.valueOf(str3).intValue() > 28) || (Integer.valueOf(str3).intValue() < 1))
    {
      StringBuilder localStringBuilder2 = new StringBuilder("错误：输入的身份证号");
      if (i != 0);
      for (String str4 = "(13-14位)"; ; str4 = "(11-13位)")
      {
        this._codeError = (str4 + "[" + str3 + "]号在" + localInteger2 + "平年的情况下未符合1-28号的规定(GB/T7408)");
        return false;
      }
    }
    return true;
  }

  public boolean verifyLength(String paramString)
  {
    int i = paramString.length();
    if ((i == 15) || (i == 18))
      return true;
    this._codeError = "错误：输入的身份证号不是15位和18位的";
    return false;
  }

  public boolean verifyMOD(String paramString)
  {
    String str = paramString.substring(17, 18);
    if ("x".equals(str))
    {
      paramString = paramString.replaceAll("x", "X");
      str = "X";
    }
    if (str.equals(getVerify(paramString)))
    {
      System.out.println(this._codeError);
      return true;
    }
    this._codeError = "错误：输入的身份证号最末尾的数字验证码错误";
    System.out.println(this._codeError);
    return false;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.IDCard
 * JD-Core Version:    0.6.2
 */