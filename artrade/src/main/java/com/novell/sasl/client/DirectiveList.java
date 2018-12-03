package com.novell.sasl.client;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

class DirectiveList
{
  private static final int STATE_LOOKING_FOR_COMMA = 6;
  private static final int STATE_LOOKING_FOR_DIRECTIVE = 2;
  private static final int STATE_LOOKING_FOR_EQUALS = 4;
  private static final int STATE_LOOKING_FOR_FIRST_DIRECTIVE = 1;
  private static final int STATE_LOOKING_FOR_VALUE = 5;
  private static final int STATE_NO_UTF8_SUPPORT = 9;
  private static final int STATE_SCANNING_NAME = 3;
  private static final int STATE_SCANNING_QUOTED_STRING_VALUE = 7;
  private static final int STATE_SCANNING_TOKEN_VALUE = 8;
  private String m_curName;
  private int m_curPos = 0;
  private ArrayList m_directiveList = new ArrayList(10);
  private String m_directives;
  private int m_errorPos = -1;
  private int m_scanStart = 0;
  private int m_state = 1;

  DirectiveList(byte[] paramArrayOfByte)
  {
    try
    {
      this.m_directives = new String(paramArrayOfByte, "UTF-8");
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      this.m_state = 9;
    }
  }

  void addDirective(String paramString, boolean paramBoolean)
  {
    String str;
    if (!paramBoolean)
    {
      str = this.m_directives.substring(this.m_scanStart, this.m_curPos);
      if (this.m_state != 7)
        break label143;
    }
    label143: for (int k = 1; ; k = 2)
    {
      this.m_directiveList.add(new ParsedDirective(paramString, str, k));
      return;
      StringBuffer localStringBuffer = new StringBuffer(this.m_curPos - this.m_scanStart);
      int i = 0;
      for (int j = this.m_scanStart; j < this.m_curPos; j++)
      {
        if ('\\' == this.m_directives.charAt(j))
          j++;
        localStringBuffer.setCharAt(i, this.m_directives.charAt(j));
        i++;
      }
      str = new String(localStringBuffer);
      break;
    }
  }

  Iterator getIterator()
  {
    return this.m_directiveList.iterator();
  }

  boolean isValidTokenChar(char paramChar)
  {
    return ((paramChar < 0) || (paramChar > ' ')) && ((paramChar < ':') || (paramChar > '@')) && ((paramChar < '[') || (paramChar > ']')) && (',' != paramChar) && ('%' != paramChar) && ('(' != paramChar) && (')' != paramChar) && ('{' != paramChar) && ('}' != paramChar) && ('' != paramChar);
  }

  boolean isWhiteSpace(char paramChar)
  {
    return ('\t' == paramChar) || ('\n' == paramChar) || ('\r' == paramChar) || (' ' == paramChar);
  }

  void parseDirectives()
    throws SaslException
  {
    String str = "<no name>";
    if (this.m_state == 9)
      throw new SaslException("No UTF-8 support on platform");
    boolean bool = false;
    int i = 0;
    if (this.m_curPos < this.m_directives.length())
    {
      char c = this.m_directives.charAt(this.m_curPos);
      switch (this.m_state)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 8:
      case 7:
      case 6:
      }
      while (true)
      {
        this.m_curPos = (1 + this.m_curPos);
        i = c;
        break;
        if (!isWhiteSpace(c))
          if (isValidTokenChar(c))
          {
            this.m_scanStart = this.m_curPos;
            this.m_state = 3;
          }
          else
          {
            this.m_errorPos = this.m_curPos;
            throw new SaslException("Parse error: Invalid name character");
            if (!isValidTokenChar(c))
              if (isWhiteSpace(c))
              {
                str = this.m_directives.substring(this.m_scanStart, this.m_curPos);
                this.m_state = 4;
              }
              else if ('=' == c)
              {
                str = this.m_directives.substring(this.m_scanStart, this.m_curPos);
                this.m_state = 5;
              }
              else
              {
                this.m_errorPos = this.m_curPos;
                throw new SaslException("Parse error: Invalid name character");
                if (!isWhiteSpace(c))
                  if ('=' == c)
                  {
                    this.m_state = 5;
                  }
                  else
                  {
                    this.m_errorPos = this.m_curPos;
                    throw new SaslException("Parse error: Expected equals sign '='.");
                    if (!isWhiteSpace(c))
                      if ('"' == c)
                      {
                        this.m_scanStart = (1 + this.m_curPos);
                        this.m_state = 7;
                      }
                      else if (isValidTokenChar(c))
                      {
                        this.m_scanStart = this.m_curPos;
                        this.m_state = 8;
                      }
                      else
                      {
                        this.m_errorPos = this.m_curPos;
                        throw new SaslException("Parse error: Unexpected character");
                        if (!isValidTokenChar(c))
                          if (isWhiteSpace(c))
                          {
                            addDirective(str, false);
                            this.m_state = 6;
                          }
                          else if (',' == c)
                          {
                            addDirective(str, false);
                            this.m_state = 2;
                          }
                          else
                          {
                            this.m_errorPos = this.m_curPos;
                            throw new SaslException("Parse error: Invalid value character");
                            if ('\\' == c)
                              bool = true;
                            if (('"' == c) && (92 != i))
                            {
                              addDirective(str, bool);
                              this.m_state = 6;
                              bool = false;
                              continue;
                              if (!isWhiteSpace(c))
                              {
                                if (c != ',')
                                  break label519;
                                this.m_state = 2;
                              }
                            }
                          }
                      }
                  }
              }
          }
      }
      label519: this.m_errorPos = this.m_curPos;
      throw new SaslException("Parse error: Expected a comma.");
    }
    switch (this.m_state)
    {
    case 1:
    case 6:
    default:
      return;
    case 8:
      addDirective(str, false);
      return;
    case 2:
      throw new SaslException("Parse error: Trailing comma.");
    case 3:
    case 4:
    case 5:
      throw new SaslException("Parse error: Missing value.");
    case 7:
    }
    throw new SaslException("Parse error: Missing closing quote.");
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.novell.sasl.client.DirectiveList
 * JD-Core Version:    0.6.2
 */