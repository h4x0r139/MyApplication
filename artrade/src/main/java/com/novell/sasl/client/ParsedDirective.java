package com.novell.sasl.client;

class ParsedDirective
{
  public static final int QUOTED_STRING_VALUE = 1;
  public static final int TOKEN_VALUE = 2;
  private String m_name;
  private String m_value;
  private int m_valueType;

  ParsedDirective(String paramString1, String paramString2, int paramInt)
  {
    this.m_name = paramString1;
    this.m_value = paramString2;
    this.m_valueType = paramInt;
  }

  String getName()
  {
    return this.m_name;
  }

  String getValue()
  {
    return this.m_value;
  }

  int getValueType()
  {
    return this.m_valueType;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.novell.sasl.client.ParsedDirective
 * JD-Core Version:    0.6.2
 */