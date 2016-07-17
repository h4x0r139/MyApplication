package com.novell.sasl.client;

import java.util.Iterator;
import org.apache.harmony.javax.security.sasl.SaslException;

class ResponseAuth
{
  private String m_responseValue = null;

  ResponseAuth(byte[] paramArrayOfByte)
    throws SaslException
  {
    DirectiveList localDirectiveList = new DirectiveList(paramArrayOfByte);
    try
    {
      localDirectiveList.parseDirectives();
      checkSemantics(localDirectiveList);
      return;
    }
    catch (SaslException localSaslException)
    {
    }
  }

  void checkSemantics(DirectiveList paramDirectiveList)
    throws SaslException
  {
    Iterator localIterator = paramDirectiveList.getIterator();
    while (localIterator.hasNext())
    {
      ParsedDirective localParsedDirective = (ParsedDirective)localIterator.next();
      if (localParsedDirective.getName().equals("rspauth"))
        this.m_responseValue = localParsedDirective.getValue();
    }
    if (this.m_responseValue == null)
      throw new SaslException("Missing response-auth directive.");
  }

  public String getResponseValue()
  {
    return this.m_responseValue;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.novell.sasl.client.ResponseAuth
 * JD-Core Version:    0.6.2
 */