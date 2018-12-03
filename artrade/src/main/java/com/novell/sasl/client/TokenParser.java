package com.novell.sasl.client;

import org.apache.harmony.javax.security.sasl.SaslException;

class TokenParser
{
  private static final int STATE_DONE = 6;
  private static final int STATE_LOOKING_FOR_COMMA = 4;
  private static final int STATE_LOOKING_FOR_FIRST_TOKEN = 1;
  private static final int STATE_LOOKING_FOR_TOKEN = 2;
  private static final int STATE_PARSING_ERROR = 5;
  private static final int STATE_SCANNING_TOKEN = 3;
  private int m_curPos;
  private int m_scanStart;
  private int m_state;
  private String m_tokens;

  TokenParser(String paramString)
  {
    this.m_tokens = paramString;
    this.m_curPos = 0;
    this.m_scanStart = 0;
    this.m_state = 1;
  }

  boolean isValidTokenChar(char paramChar)
  {
    return ((paramChar < 0) || (paramChar > ' ')) && ((paramChar < ':') || (paramChar > '@')) && ((paramChar < '[') || (paramChar > ']')) && (',' != paramChar) && ('%' != paramChar) && ('(' != paramChar) && (')' != paramChar) && ('{' != paramChar) && ('}' != paramChar) && ('' != paramChar);
  }

  boolean isWhiteSpace(char paramChar)
  {
    return ('\t' == paramChar) || ('\n' == paramChar) || ('\r' == paramChar) || (' ' == paramChar);
  }

  String parseToken()
    throws SaslException
  {
    int i = this.m_state;
    String str1 = null;
    if (i == 6);
    label294: 
    do
    {
      return str1;
      if ((this.m_curPos < this.m_tokens.length()) && (str1 == null))
      {
        char c = this.m_tokens.charAt(this.m_curPos);
        switch (this.m_state)
        {
        default:
        case 1:
        case 2:
        case 3:
        case 4:
        }
        while (true)
        {
          this.m_curPos = (1 + this.m_curPos);
          break;
          if (!isWhiteSpace(c))
            if (isValidTokenChar(c))
            {
              this.m_scanStart = this.m_curPos;
              this.m_state = 3;
            }
            else
            {
              this.m_state = 5;
              throw new SaslException("Invalid token character at position " + this.m_curPos);
              if (!isValidTokenChar(c))
                if (isWhiteSpace(c))
                {
                  str1 = this.m_tokens.substring(this.m_scanStart, this.m_curPos);
                  this.m_state = 4;
                }
                else if (',' == c)
                {
                  str1 = this.m_tokens.substring(this.m_scanStart, this.m_curPos);
                  this.m_state = 2;
                }
                else
                {
                  this.m_state = 5;
                  throw new SaslException("Invalid token character at position " + this.m_curPos);
                  if (!isWhiteSpace(c))
                  {
                    if (c != ',')
                      break label294;
                    this.m_state = 2;
                  }
                }
            }
        }
        this.m_state = 5;
        throw new SaslException("Expected a comma, found '" + c + "' at postion " + this.m_curPos);
      }
    }
    while (str1 != null);
    switch (this.m_state)
    {
    case 1:
    case 4:
    default:
      return str1;
    case 2:
      throw new SaslException("Trialing comma");
    case 3:
    }
    String str2 = this.m_tokens.substring(this.m_scanStart);
    this.m_state = 6;
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.novell.sasl.client.TokenParser
 * JD-Core Version:    0.6.2
 */